package br.com.ada.programacaoweb.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditoraDTO {

    private Long id;
    private String nome;


    private String descricao;
}
