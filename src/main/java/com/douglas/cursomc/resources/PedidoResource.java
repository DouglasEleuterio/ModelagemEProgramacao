package com.douglas.cursomc.resources;


import com.douglas.cursomc.domain.Categoria;
import com.douglas.cursomc.dto.CategoriaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.douglas.cursomc.domain.Pedido;
import com.douglas.cursomc.service.PedidoService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

/**
 * Classe da Ponta da Arquitetura MVC.
 * A responsábilidade da classe é comunicar-se com o FRONT-END.
 * Passando-lhe os dados, erros, filtros, exceções, etc provenientes da classe de serviço.
 * O serviço será acessado através do endereço "www.endereco.com"/pedidos
 *
 * @author Douglas Eleutério - Graduando em Engenharia de Software pela Unicesumar 2019-2023
 * @version 0.0.1
 * @since 30/07/2019
 */
@RestController //Controlador Rest
@RequestMapping(value = "/pedidos") //Respondendo a esse "endereço".
public class PedidoResource {

    @Autowired //Obj. injetado pelo Spring.
    private PedidoService service;

    /**
     * O acesso a dados se dá atraves do Id passado pelo navegador
     * O Controlador espera encontrar o "id" do html com nome de id.
     * O Spring faz a leitura dos Ids do HTML através da anotação @PathVariable
     *
     * Erros de resposta
     * O controlador REST não costuma tratar os erros diretamente na Classe
     * Para tratar os Erros faremos uso do Handler
     * Handler é um objeto especial que intercepta a resposta e envia o retorno adequado.
     *
     * @param id - Espera o id do objeto que deseja find.
     * @return ResponseEntity - retornará um objeto do tipo ResponseEntity que encapsula várias iinformações de resposta HTTP para o serviço REST.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id) {
        /*
         * O tipo de retorno ResponseEntity ecapsula a resposta e adiciona várias funcionalidades ao retorno.
         */
        Pedido obj = service.find(id);
        return ResponseEntity.ok().body(obj);//Retornando a resposta ao Serviço
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody Pedido obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
