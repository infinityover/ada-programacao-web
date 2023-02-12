package br.com.ada.programacaoweb.controller;

import br.com.ada.programacaoweb.model.dto.CategoriaDTO;
import br.com.ada.programacaoweb.service.CategoriaService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categorias")
public class CategoriaController extends BaseController<CategoriaDTO, CategoriaService> {

	public CategoriaController(CategoriaService service) {
		super(service);
	}

}
