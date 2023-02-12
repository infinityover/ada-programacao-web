package br.com.ada.programacaoweb.model.mapper;

import br.com.ada.programacaoweb.model.dto.CategoriaDTO;
import br.com.ada.programacaoweb.model.entity.Categoria;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {
	List<CategoriaDTO> parseListDTO(List<Categoria> categorias);
	List<Categoria> parseListEntity(List<CategoriaDTO> categorias);
	CategoriaDTO parseDTO(Categoria categoria);
	Categoria parseEntity(CategoriaDTO categoria);
}
