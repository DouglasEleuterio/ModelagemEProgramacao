package com.douglas.cursomc.service;

import java.util.List;
import java.util.Optional;

import com.douglas.cursomc.domain.Cliente;
import com.douglas.cursomc.dto.CategoriaDTO;
import com.douglas.cursomc.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.douglas.cursomc.domain.Categoria;
import com.douglas.cursomc.repositories.CategoriaRepository;
import com.douglas.cursomc.service.exceptions.DataIntegrityException;

/**
 * Classe de serviço responsável por intermediar as transações entre
 * Repository(BD) e Controladores. Tal classe invoca os metodos da classe
 * Repository. Os tratamentos de erros, filtros, controle de acesso e demais
 * opções são de responsabilidade desta classe. A classe de acesso aos dados,
 * ficará responsável apenas por acessar aos dados. A Classe de Controladores,
 * ficará responsável apenas por "conversar" com o "FRONT-END".
 *
 * @author Douglas Eleutério
 */
@Service
public class CategoriaService {

    @Autowired //Injeção de Dependencia do Spring
    private CategoriaRepository repo; // repo passa a ser o objeto instanciado, injetado e controlado pelo Spring para acessar a classe "Repository" para prover os dados.

    /**
     * Nova implementação do JAVA 8 Caso o objeto com id não seja encontrado,
     * será lançada nossa exceção personalizada. É de responsabilidade da camada
     * de Controle enviar o erro, cabendo a camada de serviço a implementação.
     *
     * @param id - Receberá um id para realizar a busca do objeto.
     * @return Objeto do tipo categoria
     * @throws ObjectNotFoundException - Caso não localize o ID
     * @throws ObjectNotFoundException - Será lançado caso não localize o obj
     *                                 com id informado.
     * @see ObjectNotFoundException
     */
    public Categoria find(Integer id) {
        Optional<Categoria> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto Não Encontrado!	Id: " + id + " , Tipo: " + CategoriaService.class.getName()
        ));
    }

    /**
     * Metodo que salva objeto do tipo Categoria no Banco de dados. Para
     * garantir que o objeto é novo, é definido o id como null.
     *
     * @param obj - Objeto do Tipo Categoria
     * @return Retorna objeto do tipo Categoria
     */
    public Categoria insert(Categoria obj) {
        obj.setId(null);
        return repo.save(obj);
    }

    /**
     * Metodo que altera o objeto enviado na requisição.
     * <br/>Invocado o Metodo find para checar o id passado.
     *
     * @param obj - Objeto do tipo Categoria
     * @return Retorna um objeto do tipo Categoria.
     */
    public Categoria update(Categoria obj) {
        Categoria newOBJ = find(obj.getId());
        updateData(newOBJ, obj);
        return repo.save(newOBJ);
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
            throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
        }
    }

    /**
     * Metodo que retorna uma lista de Categorias
     *
     * @return Lista de Categorias
     */
    public List<Categoria> findAll() {
        return repo.findAll();
    }

    /**
     * Metodo de Paginação de dados.
     * @param page - número da página
     * @param linesPerPage - Quantos resultados deseja por página.
     * @param orderBy - Ordenar por qual campo? Nome ou Id ?
     * @param direction - Deseja os or ordem crescente-ASC ou decrescente-DESC
     * @return
     */
    public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    /**
     * Construindo um bojeto categoria a partir de um Objeto CategoriaDTO.
     * @param objDto - Objeto do Tipo DTO
     * @return Categoria - retornará um objeto do tipo Categoria.
     */
    public Categoria fromDto(CategoriaDTO objDto){
        return new Categoria(objDto.getId(), objDto.getNome());
    }

    /**
     * Metodo auxiliar para atualizar os dados do banco de dados.
     *
     * @param newOBJ - Novo objeto que virá da requisição
     * @param obj - Objeto buscado no banco de dados.
     */
    private void updateData(Categoria newOBJ, Categoria obj) {
        newOBJ.setNome(obj.getNome());
    }
}
