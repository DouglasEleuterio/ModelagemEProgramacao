package com.douglas.cursomc.repositories;

import com.douglas.cursomc.domain.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.douglas.cursomc.domain.Produto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Classe responsável pelas operações no banco de dados.
 * Foi utilizada a interface JpaRepository, tal interface manipula o acesso aos dados e encapsula as operações.
 * As classes do pacote Repositories são as "ultimas" classes quando observamos a arquitetura de Camada MVC.
 * As classes são responsáveis por acessar o banco de dados, realizar as alterações devidas.
 *
 * @author Douglas
 * @version 0.0.1
 * @see Categoria
 * @see com.douglas.cursomc.service.CategoriaService
 * @see JpaRepository
 * @since 30/07/2019
 */
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    /*
     * Interface que estende JpaRepository para fornecer acesso aos dados.
     * É passado o tipo de Obj. que deseja manipular e o tipo de dado do identificador.
     * Nesse caso Integer (Definido no ID da classe de dominio).
     *
     */
    @Transactional(readOnly = true)
//    @Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:nome% AND cat IN :categorias")
    Page<Produto> findDistinctByNomeContainingAndCategoriasIn(@Param("nome") String nome, @Param("categorias") List<Categoria> categorias, Pageable pageRequest);
}
