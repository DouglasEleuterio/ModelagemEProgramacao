package com.douglas.cursomc.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.douglas.cursomc.categoria.Categoria;
import com.douglas.cursomc.service.CategoriaService;

/**
 * CategoriaResource.class 
 * @since 30/07/2019
 * @version 0.0.1
 * @author Douglas Eleutério - Graduando em Engenharia de Software pela Unicesumar 2019-2023
 */
@RestController //Controlador Rest
@RequestMapping(value="/categorias") //Respondendo a esse "endereço".
public class CategoriaResource {
	
	@Autowired //Obj. injetado pelo Spring.
	private CategoriaService service;
	
	/* O acesso a dados se dá atraves do Id passado pelo navegador 
	 * O Controlador espera encontrar o "id" do html com nome de id.
	 * O Spring faz a leitura dos Ids do HTML através da anotação @PathVariable
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		/*
		 * O tipo de retorno ResponseEntity ecapsula a resposta e adiciona várias funcionalidades ao retorno.		
		 */
		Categoria obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);//Retornando a resposta ao Serviço
	}
	
}
