package br.com.ada.programacaoweb.controller;

import br.com.ada.programacaoweb.model.dto.EditoraDTO;
import br.com.ada.programacaoweb.service.EditoraService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/editoras")
public class EditoraController extends BaseController<EditoraDTO, EditoraService> {

	public EditoraController(EditoraService service) {
		super(service);
	}

}
