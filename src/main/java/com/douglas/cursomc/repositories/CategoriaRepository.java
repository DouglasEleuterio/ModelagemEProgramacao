package com.douglas.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.douglas.cursomc.categoria.Categoria;

/**
 * Classe responsável por prover o acesso aos dados a classe de serviço.
 * Foi utilizada a interface JpaRepository, tal interface manipula o acesso aos dados e a encapsula.
 * 
 * @version 0.0.1
 * @author Douglas
 * @since 30/07/2019 
 */
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

	/*
	 * Interface que estende JpaRepository para fornecer acesso aos dados.
	 * É passado o tipo de Obj. que deseja manipular e o tipo de dado do identificador.
	 * Nesse caso Integer (Definido no ID da classe de dominio).
	 * 
	 */
	
}
