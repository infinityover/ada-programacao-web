package br.com.ada.programacaoweb.service;

import br.com.ada.programacaoweb.model.dto.LivroDTO;

import java.util.List;

public interface LivroService extends BaseService<LivroDTO>{

    List<LivroDTO> filtrarPorCategoria(Long id);

    List<LivroDTO> filtrar(LivroDTO livro);
    List<LivroDTO> filtrarPorEditora(Long id);
}
