package com.douglas.cursomc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douglas.cursomc.categoria.Categoria;
import com.douglas.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired //Injeção de Dependencia do Spring
	private CategoriaRepository repo; // repo passa a ser o objeto instanciado, injetado e controlado pelo Spring.

	/**
	 * Nova implementação do JAVA 8 
	 * Caso o objeto com id não seja encontrado, não será retornado NullPointerException.
	 * @param Recebe um ID
	 * @return Objeto do tipo categoria
	 */
	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElse(null);
	}
}			
