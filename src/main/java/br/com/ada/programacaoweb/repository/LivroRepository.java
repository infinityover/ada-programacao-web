package br.com.ada.programacaoweb.repository;

import br.com.ada.programacaoweb.model.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long>{

    @Query(value = "SELECT l from Livro l WHERE l.categoria.id = :categoriaId")
    List<Livro> findByCategoria(@Param("categoriaId") Long id);

    @Query(value = "SELECT l from Livro l WHERE l.editora.id = :editoraId")
    List<Livro> findByEditora(@Param("editoraId") Long id);

}
