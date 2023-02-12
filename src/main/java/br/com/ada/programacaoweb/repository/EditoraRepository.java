package br.com.ada.programacaoweb.repository;

import br.com.ada.programacaoweb.model.entity.Editora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditoraRepository extends JpaRepository<Editora, Long>{

}
