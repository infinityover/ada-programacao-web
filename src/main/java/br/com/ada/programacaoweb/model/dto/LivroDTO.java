package br.com.ada.programacaoweb.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LivroDTO {

    private Long id;
    private EditoraDTO editora;

    private CategoriaDTO categoria;
    private String nome;

    private String isbn;


}
