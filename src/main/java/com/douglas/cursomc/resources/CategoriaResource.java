package com.douglas.cursomc.resources;

import com.douglas.cursomc.dto.CategoriaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.douglas.cursomc.domain.Categoria;
import com.douglas.cursomc.service.CategoriaService;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * Classe da Ponta na Arquitetura MVC.
 * <br/>A responsábilidade da classe é disponibilizar um EndPoint para acesso.
 * <br/>Endereço: www.site.com/categorias
 * <br/>Metodo Get: www.site.com/{id} Passando-lhe os dados, erros, filtros,
 * exceções, etc provenientes da classe de serviço. O serviço será acessado
 * através do endereço "www.site.com"/categorias
 *
 * @author Douglas Eleutério - Graduando em Engenharia de Software pela
 * Unicesumar 2019-2023
 * @version 0.0.1
 * @since 30/07/2019
 */
@RestController //Controlador Rest
@RequestMapping(value = "/categorias") //Respondendo a esse "endereço".
public class CategoriaResource {

    @Autowired //Obj. injetado pelo Spring.
    private CategoriaService service;

    /**
     * O acesso a dados se dá atraves do Id passado pelo navegador O Controlador
     * espera encontrar o "id" do html com nome de id. O Spring faz a leitura
     * dos Ids do HTML através da anotação @PathVariable
     * <p>
     * Erros de resposta O controlador REST não costuma tratar os erros
     * diretamente na Classe Para tratar os Erros faremos uso do Handler Handler
     * é um objeto especial que intercepta a resposta e envia o retorno
     * adequado.
     *
     * @param id - Espera o id do objeto que deseja find.
     * @return ResponseEntity - retornará um objeto do tipo ResponseEntity que
     * encapsula várias iinformações de resposta HTTP para o serviço REST.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id) {
        /*
         * O tipo de retorno ResponseEntity ecapsula a resposta e adiciona várias funcionalidades ao retorno.
         */
        Categoria obj = service.find(id);
        return ResponseEntity.ok().body(obj);//Retornando a resposta ao Serviço
    }

    /**
     * Recurso para salvar categoria no banco de dados. Após salvar a nova
     * categoria, enviamos a URI nova como resposta. Pegamos o id do novo objeto
     * e setamos na URI de resposta.
     *
     * @param obj - Objeto do tipo Categoria
     * @return Endereço URI do novo objeto.
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody Categoria obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    /**
     * Recurso que prove atualização do Objeto no banco.
     *
     * @param obj - Categoria
     * @param id  - id da categoria que deseja alterar
     * @return - Retorna corpo vazio.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody Categoria obj, @PathVariable Integer id) {
        obj.setId(id);
        obj = service.update(obj);
        return ResponseEntity.noContent().build();
    }

    /**
     * Recurso que prove a deleção da Categoria
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
     * Recurso que retorna todas as categorias.
     * Será retornado uma lista contendo todas as categorias
     * Utilizado o Padrão DTO.
     * Será retornado uma lista de CategoriaDTO
     * @return Lista de Categorias.
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CategoriaDTO>> findAll() {
        List<Categoria> list = service.findAll();
        //Percorrendo a lista com Stream
        // map - atribuir uma operação para cada elemento da lista
        //cada elemento da lista tera o apelido de obj
        //para cada elemento da lista 'obj'
        //retornar o objeto do tipo stream para lista
        //Assim convertemos uma lista para outra lista
        List<CategoriaDTO> listDto = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    /**
     * <h2>Serviço de páginação.</h2>
     * <br/>Evitando carregamento de todas as páginas que poderão existir, que ocasionaria processamento exagerado caso
     * exista muitos dados.
     * <br/>
     * <p>Exemplo: http://localhost:8080/categorias/page?page=0&linesPerPage=2&ordeBy=id&direction=ASC </p>
     * @param page - Número da página (Inicia-se em 0)
     * @param linesPerPage - Quantidade de Resultados por página
     * @param orderBy - Qual dado deseja ordenar? (Id ou Nome)
     * @param direction - Qual ordenação dos dados crescente-ASC ou decrescente-DESC
     * @return
     */
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<CategoriaDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC")  String direction) {
        Page<Categoria> list = service.findPage(page,linesPerPage,orderBy,direction);
        Page<CategoriaDTO> listDto = list.map(obj -> new CategoriaDTO(obj));
        return ResponseEntity.ok().body(listDto);
    }

}
