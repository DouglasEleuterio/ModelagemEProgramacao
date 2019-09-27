
package com.douglas.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.douglas.cursomc.domain.Categoria;

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

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}
