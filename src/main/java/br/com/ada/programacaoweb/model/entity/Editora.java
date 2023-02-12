package br.com.ada.programacaoweb.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_editora")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Editora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nr_id")
    private Long id;
    @Column(length = 255, name = "ds_nome")
    private String nome;

    @Column(nullable = true, name = "ds_descricao")
    private String descricao;
}
