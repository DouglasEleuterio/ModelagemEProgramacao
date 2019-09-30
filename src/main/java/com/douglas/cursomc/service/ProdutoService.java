package com.douglas.cursomc.service;

import com.douglas.cursomc.domain.Categoria;
import com.douglas.cursomc.domain.Produto;
import com.douglas.cursomc.repositories.CategoriaRepository;
import com.douglas.cursomc.repositories.ProdutoRepository;
import com.douglas.cursomc.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
public class ProdutoService {

    @Autowired //Injeção de Dependencia do Spring
    private ProdutoRepository repo; // repo passa a ser o objeto instanciado, injetado e controlado pelo Spring para acessar a classe "Repository" para prover os dados.

    @Autowired
    private CategoriaRepository categoriaRepository;

    /**
     * Nova implementação do JAVA 8
     * Caso o objeto com id não seja encontrado, será lançada nossa exceção personalizada.
     * É de responsabilidade da camada de Controle enviar o erro, cabendo a camada de serviço a implementação.
     *
     * @param id - Receberá um id para realizar a busca do objeto.
     * @return Objeto do tipo Produto
     * @throws ObjectNotFoundException - Caso não localize o ID
     * @throws ObjectNotFoundException - Será lançado caso não localize o obj com id informado.
     * @see ObjectNotFoundException
     */
    public Produto find(Integer id) {
        Optional<Produto> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
    }

    /**
     * Busca páginada de Produtos, dado um trcho do nome.
     * Buscará produtos que está relacionado a uma lista de categorias.
     *
     * @param nome
     * @param ids
     * @return
     */
    public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        List<Categoria> categorias = categoriaRepository.findAllById(ids);
        return repo.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
    }
}