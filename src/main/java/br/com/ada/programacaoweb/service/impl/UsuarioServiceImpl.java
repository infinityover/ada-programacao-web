package br.com.ada.programacaoweb.service.impl;

import br.com.ada.programacaoweb.model.dto.TokenDTO;
import br.com.ada.programacaoweb.model.dto.UsuarioDTO;
import br.com.ada.programacaoweb.model.dto.UsuarioLoginDTO;
import br.com.ada.programacaoweb.model.entity.Usuario;
import br.com.ada.programacaoweb.model.mapper.UsuarioMapper;
import br.com.ada.programacaoweb.repository.UsuarioRepository;
import br.com.ada.programacaoweb.service.UsuarioService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private UsuarioMapper mapper;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private JWTService jwtService;
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	public List<UsuarioLoginDTO> buscarTodos() {
		return mapper.parseListDTO(repository.findAll());
	}
	
	public UsuarioLoginDTO buscarUm(Long id) {
		Optional<Usuario> usuarioOp = repository.findById(id);
		if(usuarioOp.isPresent()) {
			Usuario usuario = usuarioOp.get();
			return mapper.parseDTO(usuario);
		}
		
		throw new EntityNotFoundException();
	}
	
	public UsuarioLoginDTO criar(UsuarioLoginDTO clienteDTO) {
		Usuario usuario = mapper.parseEntity(clienteDTO);
		usuario.setPassword(encoder.encode(usuario.getPassword()));
		usuario.setId(null);
		repository.save(usuario);
		return mapper.parseDTO(usuario);
	}
	
	public UsuarioLoginDTO editar(Long id, UsuarioLoginDTO usuarioDTO) {
		
		Optional<Usuario> usuarioOp = repository.findById(id);
		
		if(usuarioOp.isPresent()) {
			Usuario usuario = usuarioOp.get();
			usuario.setId(id);
			usuario = repository.save(usuario);
			return mapper.parseDTO(usuario);
		}
		
		throw new EntityNotFoundException();
	}
	
	public void excluir(Long id) {
		if(!repository.existsById(id)) {
			throw new EntityNotFoundException();
		}
		
		repository.deleteById(id);
	}
	
	public TokenDTO atualizarToken(String refreshToken) {
		
		if(jwtService.validRefreshToken(refreshToken)) {
			String username = jwtService.getUsernameByRefreshToken(refreshToken);
			
			return buildTokenDTO(username,null);
		}
		
		throw new ExpiredJwtException(null, null,"Refresh token foi expirado.");
	}
	
	public TokenDTO logar(UsuarioLoginDTO usuarioLoginDTO) throws AuthenticationException,UsernameNotFoundException {
		
		UsernamePasswordAuthenticationToken autentication = 
				new UsernamePasswordAuthenticationToken(usuarioLoginDTO.getUsername(),usuarioLoginDTO.getPassword());
		
		authenticationManager.authenticate(autentication);
		
		Usuario usuario = (Usuario) authService.loadUserByUsername(usuarioLoginDTO.getUsername());
		
		return buildTokenDTO(usuario.getUsername(),usuario);
	}
	
	private TokenDTO buildTokenDTO(String username,Usuario usuario) {
		
		UsuarioDTO usuarioDTO = null;
		if(usuario!=null) {
			usuarioDTO = new UsuarioDTO();
			usuarioDTO.setId(usuario.getId());
			usuarioDTO.setPerfil(usuario.getPerfil().getId());
		}
		
		String token = jwtService.generateToken(username);
		String refreshToken = jwtService.generateRefreshToken(username);
		return TokenDTO.builder()
				.token(token)
				.refreshToken(refreshToken)
				.type("Bearer")
				.user(usuarioDTO)
				.build();
	}
}
