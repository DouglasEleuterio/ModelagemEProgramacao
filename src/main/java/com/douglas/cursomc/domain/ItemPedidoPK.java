/*
 * Classe auxiliar para ItemPedido.
    A classe facilitará a definição do ID da classe ItemPedido, que será a junção dos Ids das classes Produto e Pedido.
   Chave composta da classe ItemPedido.
 */
package com.douglas.cursomc.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author douglas
 */
@Embeddable
public class ItemPedidoPK implements Serializable{
    private static final Long serialVersionUID = 1L;
    
    //Chave composta da classe ItemPedido. 
    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.pedido);
        hash = 79 * hash + Objects.hashCode(this.produto);
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
        final ItemPedidoPK other = (ItemPedidoPK) obj;
        if (!Objects.equals(this.pedido, other.pedido)) {
            return false;
        }
        if (!Objects.equals(this.produto, other.produto)) {
            return false;
        }
        return true;
    }
    
    
    
}