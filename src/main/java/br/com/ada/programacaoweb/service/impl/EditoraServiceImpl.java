package br.com.ada.programacaoweb.service.impl;

import br.com.ada.programacaoweb.model.dto.EditoraDTO;
import br.com.ada.programacaoweb.model.entity.Editora;
import br.com.ada.programacaoweb.model.mapper.EditoraMapper;
import br.com.ada.programacaoweb.repository.EditoraRepository;
import br.com.ada.programacaoweb.service.EditoraService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EditoraServiceImpl implements EditoraService {

	@Autowired
	private EditoraRepository repository;
	
	@Autowired
	private EditoraMapper mapper;
	
	public List<EditoraDTO> buscarTodos() {
		return mapper.parseListDTO(repository.findAll());
	}
	
	public EditoraDTO buscarUm(Long id) {
		Optional<Editora> editoraProdutoOp = repository.findById(id);
		if(editoraProdutoOp.isPresent()) {
			Editora editora = editoraProdutoOp.get();
			return mapper.parseDTO(editora);
		}
		
		throw new EntityNotFoundException();
	}
	
	public EditoraDTO criar(EditoraDTO editoraProdutoDTO) {
		Editora editoraProduto = mapper.parseEntity(editoraProdutoDTO);
		editoraProduto.setId(null);
		repository.save(editoraProduto);
		return mapper.parseDTO(editoraProduto);
	}
	
	public EditoraDTO editar(Long id, EditoraDTO editoraProdutoDTO) {
		
		if(repository.existsById(id)) {
			Editora editoraProduto = mapper.parseEntity(editoraProdutoDTO);
			editoraProduto.setId(id);
			editoraProduto = repository.save(editoraProduto);
			return mapper.parseDTO(editoraProduto);
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
