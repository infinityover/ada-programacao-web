package br.com.ada.programacaoweb.service.impl;

import br.com.ada.programacaoweb.model.dto.CategoriaDTO;
import br.com.ada.programacaoweb.model.entity.Categoria;
import br.com.ada.programacaoweb.model.mapper.CategoriaMapper;
import br.com.ada.programacaoweb.repository.CategoriaRepository;
import br.com.ada.programacaoweb.service.CategoriaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
	private CategoriaRepository repository;
	
	@Autowired
	private CategoriaMapper mapper;
	
	public List<CategoriaDTO> buscarTodos() {
		return mapper.parseListDTO(repository.findAll());
	}
	
	public CategoriaDTO buscarUm(Long id) {
		Optional<Categoria> categoriaProdutoOp = repository.findById(id);
		if(categoriaProdutoOp.isPresent()) {
			Categoria categoria = categoriaProdutoOp.get();
			return mapper.parseDTO(categoria);
		}
		
		throw new EntityNotFoundException();
	}
	
	public CategoriaDTO criar(CategoriaDTO categoriaProdutoDTO) {
		Categoria categoriaProduto = mapper.parseEntity(categoriaProdutoDTO);
		categoriaProduto.setId(null);
		repository.save(categoriaProduto);
		return mapper.parseDTO(categoriaProduto);
	}
	
	public CategoriaDTO editar(Long id, CategoriaDTO categoriaProdutoDTO) {
		
		if(repository.existsById(id)) {
			Categoria categoriaProduto = mapper.parseEntity(categoriaProdutoDTO);
			categoriaProduto.setId(id);
			categoriaProduto = repository.save(categoriaProduto);
			return mapper.parseDTO(categoriaProduto);
		}
		
		throw new EntityNotFoundException();
	}
	
	public void excluir(Long id) {
		if(!repository.existsById(id)) {
			throw new EntityNotFoundException();
		}
		
		repository.deleteById(id);
	}
}
