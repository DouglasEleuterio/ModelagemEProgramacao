/*
 * Pagamento do pedido.

*/
package com.douglas.cursomc.domain;

import com.douglas.cursomc.domain.Enums.EstadoPagamento;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

/**
 * A forma de gravar dados de subclasses no banco de dados se da por duas estrategias.
 * No Nosso caso, as tabelas de pagamentos podem ficar da seguinte forma, ou, salvamos tudo em apenas uma tabela, tanto pagamento com boleto, tanto para pagamento em cartão ou uma tabela para cada.
 *  No caso de dados em uma unica tabela, será feita um "tabelão" com os dados do tipo de pagamento selecionado e os outros inseriremos nulo.
 *  No caso de tabela unica, ficará uma tabela para cada tipo de pagamento, que deverá se unida com Join.
 * Via de regra, poucos dados na tabela faz um tabelão/tabela unica ao contratio tabelas independentes.
 * 
 * @author douglas
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED) //Uma tabela para cada
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //Tabela unica
public abstract class  Pagamento implements Serializable{
    private static final long servialVersionUID = 1L;

    //Deixaremos o Id do pagamento identico ao id do pedido 
    @Id
    private Integer id;
    private Integer estado; //Enum {Pendente, Quitado e Cancelado}
    
    /**
     * Associação Pedido - Pagamento. 1 para 1.
     */
    @OneToOne
    @JoinColumn(name = "pedido_id")
    @MapsId //Setando o valor do Id do Pedido com id de pagamento.
    private Pedido pedido;

    
    
    /**
     * Construtores
     */
    public Pagamento() {
    }

    public Pagamento(Integer id, EstadoPagamento estado, Pedido pedido) {
        this.id = id;
        this.estado = estado.getCod();
        this.pedido = pedido;
    }
    
    
    //Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EstadoPagamento getEstado() {
        return EstadoPagamento.toEnum(estado);
    }

    public void setEstado(EstadoPagamento estado) {
        this.estado = estado.getCod();
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    //HashCode
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.id);
        return hash;
    }
    //Equals
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
        final Pagamento other = (Pagamento) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}

