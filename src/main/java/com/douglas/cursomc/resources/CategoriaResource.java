package com.douglas.cursomc.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.douglas.cursomc.categoria.Categoria;

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
	public List<Categoria> listar() {
		
		Categoria cat1 = new Categoria(1,"Informatica");
		Categoria cat2 = new Categoria(2,"Escritório");
		
		List<Categoria> lista = new ArrayList<>();
		lista.add(cat1);
		lista.add(cat2);
		
		return lista;
	}
	
}
