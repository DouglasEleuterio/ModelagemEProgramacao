package com.douglas.cursomc.service;

import java.util.Optional;

import com.douglas.cursomc.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douglas.cursomc.domain.Pedido;
import com.douglas.cursomc.repositories.PedidoRepository;

/**
 * Classe de serviço responsável por intermediar as transações entre Repository(BD) e Controladores.
 * Tal classe invoca os metodos da classe Repository.
 * Os tratamentos de erros, filtros, controle de acesso e demais opções são de responsabilidade desta classe.
 * A classe de acesso aos dados, ficará responsável apenas por acessar aos dados.
 * A Classe de Controladores, ficará responsável apenas por "conversar" com o "FRONT-END".
 * @author Douglas Eleutério
 *
 */
@Service
public class PedidoService {

	@Autowired //Injeção de Dependencia do Spring
	private PedidoRepository repo; // repo passa a ser o objeto instanciado, injetado e controlado pelo Spring para acessar a classe "Repository" para prover os dados.

	/**
	 * Nova implementação do JAVA 8 
	 * Caso o objeto com id não seja encontrado, será lançada nossa exceção personalizada.
	 * É de responsabilidade da camada de Controle enviar o erro, cabendo a camada de serviço a implementação.
	 * @param id - Receberá um id para realizar a busca do objeto.
	 * @return Objeto do tipo pedido
	 * @throws ObjectNotFoundException - Caso não localize o ID
	 * @see ObjectNotFoundException
	 * @exception ObjectNotFoundException - Será lançado caso não localize o obj com id informado.
	 */
	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return 	obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto Não Encontrado!	Id: " +id+" , Tipo: "+PedidoService.class.getName()
		));
	}
}