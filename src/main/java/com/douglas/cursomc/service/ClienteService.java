package com.douglas.cursomc.service;

import java.util.List;
import java.util.Optional;

import com.douglas.cursomc.domain.Cliente;
import com.douglas.cursomc.dto.ClienteDTO;
import com.douglas.cursomc.service.exceptions.DataIntegrityException;
import com.douglas.cursomc.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.douglas.cursomc.domain.Cliente;
import com.douglas.cursomc.repositories.ClienteRepository;

/**
 * Classe de serviço responsável por intermediar as transações entre Repository(BD) e Controladores.
 * Tal classe invoca os metodos da classe Repository.
 * Os tratamentos de erros, filtros, controle de acesso e demais opções são de responsabilidade desta classe.
 * A classe de acesso aos dados, ficará responsável apenas por acessar aos dados.
 * A Classe de Controladores, ficará responsável apenas por "conversar" com o "FRONT-END".
 *
 * @author Douglas Eleutério
 */
@Service
public class ClienteService {

    @Autowired //Injeção de Dependencia do Spring
    private ClienteRepository repo; // repo passa a ser o objeto instanciado, injetado e controlado pelo Spring para acessar a classe "Repository" para prover os dados.

    /**
     * Nova implementação do JAVA 8
     * Caso o objeto com id não seja encontrado, será lançada nossa exceção personalizada.
     * É de responsabilidade da camada de Controle enviar o erro, cabendo a camada de serviço a implementação.
     *
     * @param id - Receberá um id para realizar a busca do objeto.
     * @return Objeto do tipo cliente
     * @throws ObjectNotFoundException - Caso não localize o ID
     * @throws ObjectNotFoundException - Será lançado caso não localize o obj com id informado.
     * @see ObjectNotFoundException
     */
    public Cliente find(Integer id) {
        Optional<Cliente> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto Não Encontrado!	Id: " + id + " , Tipo: " + ClienteService.class.getName()
        ));
    }

    /**
     * Metodo que altera o objeto enviado na requisição.
     * <br/>Invocado o Metodo find para checar o id passado.
     *
     * @param obj - Objeto do tipo Cliente
     * @return Retorna um objeto do tipo Cliente.
     */
    public Cliente update(Cliente obj) {
        find(obj.getId());
        return repo.save(obj);
    }

    /**
     * Metodo que deleta o objeto enviado na requisição.
     * <br/>Invocado o Metodo find para checar o id passado.
     *
     * @param id - id passado na requisição
     */
    public void delete(Integer id) {
        find(id);
        try {
            repo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um cliente que possui produtos");
        }
    }

    /**
     * Metodo que retorna uma lista de clientes
     *
     * @return Lista de Clientes
     */
    public List<Cliente> findAll() {
        return repo.findAll();
    }

    /**
     * Metodo de Paginação de dados.
     *
     * @param page         - número da página
     * @param linesPerPage - Quantos resultados deseja por página.
     * @param orderBy      - Ordenar por qual campo? Nome ou Id ?
     * @param direction    - Deseja os or ordem crescente-ASC ou decrescente-DESC
     * @return
     */
    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    /**
     * Construindo um bojeto cliente a partir de um Objeto ClienteDTO.
     *
     * @param objDto - Objeto do Tipo DTO
     * @return Cliente - retornará um objeto do tipo Cliente.
     */
    public Cliente fromDto(ClienteDTO objDto) {
        throw new UnsupportedOperationException();
    }
}