package com.douglas.cursomc.dto;

import com.douglas.cursomc.domain.Categoria;

import java.io.Serializable;

/**
 * Classe que define atributos básicos que trafegarão na rede.
 * <br/> Atributos
 * <br/> &nbsp id : Integer
 * <br/> &nbsp nome : String
 */
public class CategoriaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;

    public CategoriaDTO() {
    }

    /**
     * Construtor que recebe um Objeto Categoria
     * Utilizado para converter a lista que necessitamos no Resource
     * @param obj - objeto do tipo Categoria.
     */
    public CategoriaDTO(Categoria obj){
        id = obj.getId();
        nome = obj.getNome();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
