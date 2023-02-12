package br.com.ada.programacaoweb.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_livro")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nr_id")
    private Long id;
    @JoinColumn(name = "nr_editora_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Editora editora;

    @JoinColumn(name = "nr_categoria_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Categoria categoria;
    @Column(name = "ds_nome")
    private String nome;

    @Column(name = "ds_isbn", length = 13)
    private String isbn;


}
