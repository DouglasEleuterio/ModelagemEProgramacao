package com.douglas.cursomc.resources;


import com.douglas.cursomc.dto.ClienteDTO;
import com.douglas.cursomc.dto.ClienteNewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.douglas.cursomc.domain.Cliente;
import com.douglas.cursomc.service.ClienteService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe da Ponta da Arquitetura MVC.
 * A responsábilidade da classe é comunicar-se com o FRONT-END.
 * Passando-lhe os dados, erros, filtros, exceções, etc provenientes da classe de serviço.
 * O serviço será acessado através do endereço "www.endereco.com"/clientes
 *
 * @author Douglas Eleutério - Graduando em Engenharia de Software pela Unicesumar 2019-2023
 * @version 0.0.1
 * @since 30/07/2019
 */
@RestController //Controlador Rest
@RequestMapping(value = "/clientes") //Respondendo a esse "endereço".
public class ClienteResource {

    @Autowired //Obj. injetado pelo Spring.
    private ClienteService service;

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
        Cliente obj = service.find(id);
        return ResponseEntity.ok().body(obj);//Retornando a resposta ao Serviço
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDto, @PathVariable Integer id) {
        Cliente obj = service.fromDto(objDto);
        obj.setId(id);
        obj = service.update(obj);
        return ResponseEntity.noContent().build();
    }

    /**
     * Recurso que prove a deleção do Cliente
     *
     * @param id - id passado na requisição
     * @return - Retorna vazio.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Recurso que retorna todos os Clientes.
     * Será retornado uma lista contendo todos os Clientes
     * Utilizado o Padrão DTO.
     * Será retornado uma lista de ClienteDTO
     * @return Lista de Clientes.
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ClienteDTO>> findAll() {
        List<Cliente> list = service.findAll();
        //Percorrendo a lista com Stream
        // map - atribuir uma operação para cada elemento da lista
        //cada elemento da lista tera o apelido de obj
        //para cada elemento da lista 'obj'
        //retornar o objeto do tipo stream para lista
        //Assim convertemos uma lista para outra lista
        List<ClienteDTO> listDto = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    /**
     * <h2>Serviço de páginação.</h2>
     * <br/>Evitando carregamento de todas as páginas que poderão existir, que ocasionaria processamento exagerado caso
     * exista muitos dados.
     * <br/>
     * <p>Exemplo: http://localhost:8080/Clientes/page?page=0&linesPerPage=2&ordeBy=id&direction=ASC </p>
     * @param page - Número da página (Inicia-se em 0)
     * @param linesPerPage - Quantidade de Resultados por página
     * @param orderBy - Qual dado deseja ordenar? (Id ou Nome)
     * @param direction - Qual ordenação dos dados crescente-ASC ou decrescente-DESC
     * @return
     */
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<ClienteDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC")  String direction) {
        Page<Cliente> list = service.findPage(page,linesPerPage,orderBy,direction);
        Page<ClienteDTO> listDto = list.map(obj -> new ClienteDTO(obj));
        return ResponseEntity.ok().body(listDto);
    }

    /**
     * Recurso para salvar categoria no banco de dados. Após salvar a nova
     * categoria, enviamos a URI nova como resposta. Pegamos o id do novo objeto
     * e setamos na URI de resposta.
     * Utilizando padrão DTO.
     *
     * @param objDto - Objeto do tipo CategoriaDTO
     * @return Endereço URI do novo objeto.
     */
    @Transactional
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO objDto) {
        Cliente obj = service.fromDto(objDto);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }


}
