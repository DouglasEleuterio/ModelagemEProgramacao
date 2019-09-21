package com.douglas.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.douglas.cursomc.domain.Pedido;

/**
 * Classe responsável pelas operações no banco de dados.
 * Foi utilizada a interface JpaRepository, tal interface manipula o acesso aos dados e encapsula as operações.
 * As classes do pacote Repositories são as "ultimas" classes quando observamos a arquitetura de Camada MVC.
 * As classes são responsáveis por acessar o banco de dados, realizar as alterações devidas.
 * @version 0.0.1
 * @author Douglas
 * @since 30/07/2019 
 * @see Pedido 
 * @see PedidoService
 * @see JpaRepository
 */
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

	/*
	 * Interface que estende JpaRepository para fornecer acesso aos dados.
	 * É passado o tipo de Obj. que deseja manipular e o tipo de dado do identificador.
	 * Nesse caso Integer (Definido no ID da classe de dominio).
	 * 
	 */
	
}
