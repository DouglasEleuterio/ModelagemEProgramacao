/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.douglas.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 *
 * @author douglas
 */
@Entity
public class Produto implements Serializable{
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private Double preco;
    
    
    /**
     * Associações com categorias.
     * Um produto possui uma ou mais categorias.
     * Quando possuimos um relacionamento de Muitos para Muitos
     * É criado uma tabela no meio contendo as duas chaves estrageiras
     * PRODUTO_CATEGORIA será a tabela contendo as chaves estrangeiras
     * produto_id será o campo da tabela que guardará a chave estrangeira do produto
     * categoria_id será o campo da tabela que guardará a chave estrangeira da categoria
     * joinColumns -> deve informar a classe em que foi realizado a implementação.
     * inverseJoinColumns -> deve ser usado na classe que está relacionando.
     * No outro lado da relacao deve ser informado...
     */
    @ManyToMany
    @JoinTable(name = "PRODUTO_CATEGORIA",
            joinColumns = @JoinColumn(name = "produto_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )
    private List<Categoria> categorias = new ArrayList<>(); //atributo será utilizado na classe categoria para informar que o mapeamento já foi realizado nessa classe.
    
    public Produto(){}

    public Produto(Integer id, String nome, Double preco) {
        super();
        this.id = id;
        this.nome = nome;
        this.preco = preco;
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

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Produto other = (Produto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
}
