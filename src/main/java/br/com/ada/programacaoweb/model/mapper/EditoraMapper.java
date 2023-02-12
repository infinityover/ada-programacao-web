package br.com.ada.programacaoweb.model.mapper;

import br.com.ada.programacaoweb.model.dto.EditoraDTO;
import br.com.ada.programacaoweb.model.entity.Editora;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EditoraMapper {
	List<EditoraDTO> parseListDTO(List<Editora> editoras);
	List<Editora> parseListEntity(List<EditoraDTO> editoras);
	EditoraDTO parseDTO(Editora editora);
	Editora parseEntity(EditoraDTO editora);
}
