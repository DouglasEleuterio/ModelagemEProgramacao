package com.douglas.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

/**
 * Itens no Pedido.
 * <br/>Classe responsável por representar os Itens que compoe o Pedido,
 * sendo persistidos no banco de dados.
 * <br/>Aqui é realizado a junção entre Produto e Pedido.
 * <br/>Tal abordagem foi utilizada para garantir que o preço do produto no pedido não se altere.
 * Caso o produto fosse relacionado diretamente no Pedido, após o fechamento
 * do pedido, sempre que o preço do produto sofrer alteração, o preço do produto
 * no pedido também seria alterado, caso o produto fosse excluido ou modificado,
 * essas alteraçõe seriam feitas em todos os pedidos a qual ele estivesse relacionado.
 * <br/>Nome da Tabela no Banco de Dados: itemPedido.
 * <br/>Atributos:
 * <br/> &nbsp id : ItemPedidoPK - O id é a junção entre os ids de Pedido e Produto.
 * <br/> &nbsp nome : String
 * <br/>Metodos: Getters and Setters, HashCode e Equals.
 * Instancia e inicializa ItemPedidoPK.
 *
 * @author douglas eleuterio
 * @version 0.2.0
 * @see ItemPedidoPK
 * @see Produto
 */

@Entity(name = "itemPedido")
public class ItemPedido implements Serializable {
    private static final long serialVersionUID = 1L;

    //Chave composta contendo o produto e pedido.
    @JsonIgnore//Ignora a serialização. Item pedido não serializa ninguém.
    @EmbeddedId //Classe id embutido na classe auxiliar.
    private ItemPedidoPK id = new ItemPedidoPK();

    private Double desconto;
    private Integer quantidade;
    private Double preco;

    public ItemPedido() {
    }

    public ItemPedido(Pedido pedido, Produto produto, Double desconto, Integer quantidade, Double preco) {
        super();
        id.setPedido(pedido);
        id.setProduto(produto);
        this.desconto = desconto;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public Double getSubTotal() {
        return (preco - desconto) * quantidade;
    }

    @JsonIgnore
    public Pedido getPedido() {
        return id.getPedido();
    }

    public void setPedido(Pedido pedido) {
        id.setPedido(pedido);
    }

    public Produto getProduto() {
        return id.getProduto();
    }

    public void setProduto (Produto produto){
        id.setProduto(produto);
    }

    public ItemPedidoPK getId() {
        return id;
    }

    public void setId(ItemPedidoPK id) {
        this.id = id;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
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
        final ItemPedido other = (ItemPedido) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        StringBuilder builder = new StringBuilder();
        builder.append(getProduto().getNome()); //{Nome Produto}, Qte: {quantidade}, preço unitário: {preco}, subtotal: {subtotal} quebra linha
        builder.append(", Qte: "); //
        builder.append(getQuantidade());
        builder.append(", Preço unitário: ");
        builder.append(nf.format(getPreco()));
        builder.append(", Subtotal: ");
        builder.append(nf.format(getSubTotal()));
        builder.append("\n");
        return builder.toString();
    }
}
