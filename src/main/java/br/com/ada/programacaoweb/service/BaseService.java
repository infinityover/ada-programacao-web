package br.com.ada.programacaoweb.service;

import java.util.List;

public interface BaseService<T> {
	
	List<T> buscarTodos();
	T buscarUm(Long id);
	T criar(T clienteDTO);
	T editar(Long id, T clienteDTO);
	void excluir(Long id);
}
