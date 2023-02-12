package br.com.ada.programacaoweb.service;

import br.com.ada.programacaoweb.model.dto.TokenDTO;
import br.com.ada.programacaoweb.model.dto.UsuarioLoginDTO;

public interface UsuarioService extends BaseService<UsuarioLoginDTO>{
	TokenDTO logar(UsuarioLoginDTO usuarioLoginDTO) throws Exception;
	TokenDTO atualizarToken(String refreshToken);
}
