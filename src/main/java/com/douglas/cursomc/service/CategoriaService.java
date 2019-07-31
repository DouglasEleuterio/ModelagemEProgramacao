package com.douglas.cursomc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douglas.cursomc.domain.Categoria;
import com.douglas.cursomc.repositories.CategoriaRepository;

/**
 * Classe de serviço responsável por intermediar as transações entre Repository(BD) e Controladores.
 * Tal classe invoca os metodos da classe Repository.
 * Os tratamentos de erros, filtros, controle de acesso e demais opções são de responsabilidade desta classe.
 * A classe de acesso aos dados, ficará responsável apenas por acessar aos dados.
 * A Classe de Controladores, ficará responsável apenas por "conversar" com o "FRONT-END".
 * @author Douglas Eleutério
 */
@Service
public class CategoriaService {

	@Autowired //Injeção de Dependencia do Spring
	private CategoriaRepository repo; // repo passa a ser o objeto instanciado, injetado e controlado pelo Spring para acessar a classe "Repository" para prover os dados.

	/**
	 * Nova implementação do JAVA 8 
	 * Caso o objeto com id não seja encontrado, não será retornado NullPointerException.
	 * @param id - Receberá um id para realizar a busca do objeto.
	 * @return Objeto do tipo categoria
	 */
	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElse(null);
	}
}			
