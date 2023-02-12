package br.com.ada.programacaoweb.controller;

import br.com.ada.programacaoweb.model.dto.LivroDTO;
import br.com.ada.programacaoweb.service.LivroService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController extends BaseController<LivroDTO, LivroService> {

	public LivroController(LivroService service) {
		super(service);
	}

	@GetMapping("/categoria/{id}")
	public List<LivroDTO> filtrarPorCategoria(@PathVariable("id") Long id){
		return service.filtrarPorCategoria(id);
	}


	@GetMapping("/editora/{id}")
	public List<LivroDTO> filtrarPorEditora(@PathVariable("id") Long id){
		return service.filtrarPorEditora(id);
	}

	@PostMapping("/filtrar")
	private List<LivroDTO> filtrar(@RequestBody LivroDTO livroDTO){
		return service.filtrar(livroDTO);
	}
}
