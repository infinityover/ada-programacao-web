package br.com.ada.programacaoweb.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Perfil implements GrantedAuthority{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String descricao;

	@Override
	public String getAuthority() {
		return nome;
	}
}
