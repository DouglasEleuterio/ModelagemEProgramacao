package com.douglas.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 * Classe Produtos.
 * Responsável por representar os Produtos que
 * serão persistidas no banco de dados.
 * Nome da Tabela no Banco de Dados: produto.
 * Atributos:
 * id : Integer (Gerado automaticamente pelo banco)
 * nome : String
 * Metodos: Getters and Setters, HashCode e Equals.
 * Relacionamento: ManyToMany, Muitos Categoria percetencem á muitas Categorias;
 * Ex: Categoria {Informatica, Eletronicos} - Produto{Mouse, Monitor}
 * Instancia e inicializa uma lista de Pedidos, Categorias, ItemPedido.
 *
 * @author douglas eleuterio
 * @version 0.2.0
 * @see Pedido
 * @see Categoria
 * @see ItemPedido
 */

@Entity(name = "produto")
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private Double preco;

    /*
     * Quando possuimos um relacionamento de Muitos para Muitos É criado uma
     * tabela "no meio" das classes, contendo as duas chaves estrageiras.
     * Essa tabela recebeu o nome de: PRODUTO_CATEGORIA
     * Nossa tabela possui duas colunas, produto_id e categoria_id. Das respectivas classes.
     */
    @JsonIgnore //Resolvendo Referencia Cíclica.
    @ManyToMany
    @JoinTable(name = "PRODUTO_CATEGORIA", //Tabela de associação.
            joinColumns = @JoinColumn(name = "produto_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )
    private List<Categoria> categorias = new ArrayList<>();

    /*
     * Relacionamento entre Produto e Pedido, o relacionamento não será direto.
     * O relacionamento acontecerá pela classe associativa ItemPedido.
     * Dessa forma, resolvemos o problema do valor do produto no pedido ser mutável após fechamento do pedido.
     * O Produto precisa conhecer o itens associados a ele.
     */
    @JsonIgnore //A classe produto não serializará os Itens do Pedido.
    @OneToMany(mappedBy = "id.produto")
    private Set<ItemPedido> itens = new HashSet<>();

    public Produto() {
    }

    public Produto(Integer id, String nome, Double preco) {
        super();
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    /* O produto necessita conhecer os Pedidos associados a ele.
     *
     */
    @JsonIgnore // Não permite que Produto serialize Pedido
    public List<Pedido> getPedidos() {
        List<Pedido> lista = new ArrayList<>();
        //Percorrer a lista de Itens
        for (ItemPedido x : itens) {
            //para item de pedido x que existe na minha lista de itens, vou adicionar o pedido associado a ele na minha lista.
            lista.add(x.getPedido());

        }
        return lista;
    }

    //Getters e Setters
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

    public Set<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(Set<ItemPedido> itens) {
        this.itens = itens;
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
        return Objects.equals(this.id, other.id);
    }

}
