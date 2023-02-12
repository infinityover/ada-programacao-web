package br.com.ada.programacaoweb.model.mapper;

import br.com.ada.programacaoweb.model.dto.LivroDTO;
import br.com.ada.programacaoweb.model.entity.Livro;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LivroMapper {
	List<LivroDTO> parseListDTO(List<Livro> livros);
	List<Livro> parseListEntity(List<LivroDTO> livros);
	LivroDTO parseDTO(Livro livro);
	Livro parseEntity(LivroDTO livro);
}
