package com.douglas.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 * Categoria dos produtos. <br/>
 * Classe responsável por representar as Categorias que 
 *  serão persistidas no banco de dados. <br/>
 * Nome da Tabela no Banco de Dados: categoria.<br/>
 * Atributos: 
 *  <br/> &nbsp id : Integer (Gerado automaticamente pelo banco) 
 *  <br/> &nbsp nome : String <br/>
 * Metodos: Getters and Setters,<br/>
 * HashCode e Equals.<br/>
 *  Relacionamento: <br/> &nbsp produtos: ManyToMany, Muitas Categoria percetencem á muitos produtos.
 *  Ex: Categoria {Informatica, Eletronicos} - Produto{Mouse, Monitor} <br/>
 * Instancia e inicializa uma lista de Produtos.
 *
 * @see Produto
 * @author douglas eleuterio
 * @version 0.2.0
 */

@Entity(name = "categoria")
public class Categoria implements Serializable {

    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;

    /**
     * Relacionamento entre Categoria e Produto. <br/> 
     * Tipo de Relacionamento: ManyToMany, Muitas Categoria percetencem á muitos produtos; <br/>
     * definições para criação no banco está contido na classe produto.<br/>
     * Tabela de Associação: "PRODUTO_CATEGORIA"<br/>
     */
    @ManyToMany(mappedBy = "categorias") //Informado que o mapeamento foi realizado no atributo categorias da Classe Produto.
    private List<Produto> produtos = new ArrayList<>();

    public Categoria() {
    }

    /**
     * Método Construtor da Classe, Utiliza-se para intanciar objetos com os
     * seguintes atributos:
     * @param id número ID do objeto - gerado automaticamente.
     * @param nome nome da Categoria dado ao objeto.
     */
    public Categoria(Integer id, String nome) {
        super();
        this.id = id;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
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
        Categoria other = (Categoria) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }

}
