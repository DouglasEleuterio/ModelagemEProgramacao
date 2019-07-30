package com.douglas.cursomc.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * CategoriaResource.class 
 * @since 30/07/2019
 * @version 0.0.1
 * @author Douglas Eleutério - Graduando em Engenharia de Software pela Unicesumar 2019-2023
 */
@RestController //Controlador Rest
@RequestMapping(value="/categorias") //Respondendo a esse "endereço".
public class CategoriaResource {
	
	@RequestMapping(method=RequestMethod.GET)
	public String listar() {
		return "REST Está funcionando!";
	}
	
}
