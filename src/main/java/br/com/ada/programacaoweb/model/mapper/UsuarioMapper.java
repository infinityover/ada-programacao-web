package br.com.ada.programacaoweb.model.mapper;

import br.com.ada.programacaoweb.model.dto.UsuarioLoginDTO;
import br.com.ada.programacaoweb.model.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
	List<UsuarioLoginDTO> parseListDTO(List<Usuario> clientes);
	List<Usuario> parseListEntity(List<UsuarioLoginDTO> clientes);
	@Mapping(target = "password", ignore=true)
	@Mapping(source = "perfil.id",target = "perfil")
	UsuarioLoginDTO parseDTO(Usuario cliente);
	@Mapping(target="authorities",ignore=true)
	@Mapping(source = "perfil",target = "perfil.id")
	Usuario parseEntity(UsuarioLoginDTO cliente);
}
