package com.douglas.cursomc.domain;

import com.douglas.cursomc.domain.Enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

/**
 * Pagamento dos Pedidos.
 * <br/>Classe responsável por representar os Pagamentos dos Pedidos.
 * <br/>Duas Particularidades devem ser observadas nessa classe.
 * <br/> &nbsp Id - Necessitamos que o id do pagamento seja identico ao Id do Pedido.
 * <br/> &nbsp Tabela no banco - Por se tratar de uma SuperClasse, necessitamos
 *       gravar a tabela do banco, que será a junção da super com a sub classe.
 *       Utilizamos a abordagem "JOINED" que criará uma tabela para cada tipo de pagamento.   
 * <br/>Nome da Tabela no Banco de Dados: pagamento.
 * <br/>Atributos: 
 *  <br/> &nbsp id : Não gerado pela classe, o Id é copiado do id do pedido a ele associado. 
 *  <br/> &nbsp nome : Estado - Representa o estado do Pagamento, podendo ser:
 *        &nbsp &nbsp  PENDENTE = 1, QUITADO = 2 ou CANCELADO = 3. 
 *        &nbsp O Estado do Pagamento é representado na forma de String.  
 * <br/>Metodos: Getters and Setters, HashCode e Equals.
 * Instancia e inicializa ItemPedidoPK.
 *
 * @see EstadoPagamento
 * @see Pedido
 * @author douglas eleuterio
 * @version 0.2.0
 */

@Entity(name = "pagamento")
@Inheritance(strategy = InheritanceType.JOINED) //Uma tabela para cada subclasse
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //Tabela unica
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")
public abstract class  Pagamento implements Serializable{
    private static final long servialVersionUID = 1L;

    
    @Id //Deixaremos o Id do pagamento identico ao id do pedido 
    private Integer id;
    private Integer estado; //Enum {Pendente, Quitado e Cancelado}
    
    /**
     * Associação Pedido - Pagamento. 1 para 1.
     * O Pagamento não pode serializar o pedido.
     */
    @JsonIgnore
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
        this.estado = (estado ==null) ? null : estado.getCod();
        this.pedido = pedido;
    }
    
    
    //Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Responsável por converter o estado para forma de String.
     * @return Pendente, Quitado ou Cancelado.
     */
    public EstadoPagamento getEstado() {
        return EstadoPagamento.toEnum(estado);
    }

    /**
     * Responsável por converter o estado para forma de String.
     * @param estado 
     */
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

