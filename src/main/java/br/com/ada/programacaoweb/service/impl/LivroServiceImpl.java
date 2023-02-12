package br.com.ada.programacaoweb.service.impl;

import br.com.ada.programacaoweb.model.dto.LivroDTO;
import br.com.ada.programacaoweb.model.entity.Livro;
import br.com.ada.programacaoweb.model.mapper.LivroMapper;
import br.com.ada.programacaoweb.repository.LivroFilterRepository;
import br.com.ada.programacaoweb.repository.LivroRepository;
import br.com.ada.programacaoweb.service.LivroService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroServiceImpl implements LivroService {

	@Autowired
	private LivroRepository repository;
	
	@Autowired
	private LivroMapper mapper;

	@Autowired
	private LivroFilterRepository livroFilterRepository;
	
	public List<LivroDTO> buscarTodos() {
		return mapper.parseListDTO(repository.findAll());
	}
	
	public LivroDTO buscarUm(Long id) {
		Optional<Livro> livroProdutoOp = repository.findById(id);
		if(livroProdutoOp.isPresent()) {
			Livro livro = livroProdutoOp.get();
			return mapper.parseDTO(livro);
		}
		
		throw new EntityNotFoundException();
	}
	
	public LivroDTO criar(LivroDTO livroProdutoDTO) {
		Livro livroProduto = mapper.parseEntity(livroProdutoDTO);
		livroProduto.setId(null);
		repository.save(livroProduto);
		return mapper.parseDTO(livroProduto);
	}
	
	public LivroDTO editar(Long id, LivroDTO livroProdutoDTO) {
		
		if(repository.existsById(id)) {
			Livro livroProduto = mapper.parseEntity(livroProdutoDTO);
			livroProduto.setId(id);
			livroProduto = repository.save(livroProduto);
			return mapper.parseDTO(livroProduto);
		}
		
		throw new EntityNotFoundException();
	}
	
	public void excluir(Long id) {
		if(!repository.existsById(id)) {
			throw new EntityNotFoundException();
		}
		
		repository.deleteById(id);
	}
	
	
	public List<LivroDTO> filtrarPorCategoria(Long id){
		List<Livro> byCategoria = repository.findByCategoria(id);
		return mapper.parseListDTO(byCategoria);
	}


	public List<LivroDTO> filtrarPorEditora(Long id){
		List<Livro> byEditora = repository.findByEditora(id);
		return mapper.parseListDTO(byEditora);
	}


	public List<LivroDTO> filtrar(LivroDTO livroDTO){
		Livro livro = mapper.parseEntity(livroDTO);
		List<Livro> livros = livroFilterRepository.filtrar(livro);
		return mapper.parseListDTO(livros);
	}
}