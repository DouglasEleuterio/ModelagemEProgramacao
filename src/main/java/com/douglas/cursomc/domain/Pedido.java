package com.douglas.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * Pedido.
 * <br/>Implementação da classe mais importante do nosso dominio.
 * <br/>Classe responsável por representar os Pedidos, 
 *  sendo persistidos no banco de dados.
 * <br/> A Classe pedido fará a conexão/ligação entre os principais elementos do sistema, sendo eles:
 * <br/>&nbsp Cliente
 * <br/>&nbsp Endereço
 * <br/>&nbsp Pagamento
 * <br/>&nbsp ItemPedido ( A ligação não poderá ser feita diretamente com produto para evitar a seguinte situação.<br/>
 * <br/>&nbsp Ao recuperarmos o valor do produto vendido no pedido em especifico, o valor que será retornado será o preço atual do produto no sistema.
 * <br/>&nbsp Da forma que estamos modelando, ficará registrado o valor do produto no ato da venda, não podendo ser alterado posteriormente.
 * <br/>Nome da Tabela no Banco de Dados: pedido.
 * <br/>Atributos: 
 *  <br/> &nbsp id : Integer (Gerado automaticamente)
 *  <br/> &nbsp instante : Date
 * <br/>Metodos: Getters and Setters, HashCode e Equals.
 * <br/>Instancia e inicializa Pagamento, Cliente, Endereco, Itens.
 *
 * @see Pagamento
 * @see Cliente
 * @see Endereco
 * @see ItemPedido
 * @author douglas eleuterio
 * @version 0.2.0
 */

@Entity(name = "pedido")
public class Pedido implements Serializable{
    private static final long servialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    private Date instante;

    /**
     * Associação da Classe Pedido - Pagamento. 1 - 1
     * Mapeamento Bidirecional 1 - 1 e garantindo que o id do pagamento seja igual ao id do pedido correspondente.
     * Para tratar a serialização cíclica, o Pedido pode serializar o Pagamento, o inverso não pode.
     */
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido") //Necessario para evitar erro de entidade Transiente.
    private Pagamento pagamento;

    /**
     * Associação de Classe Pedido - Cliente. * - 1 Associação Bidirecional, o
     * cliente também precisa conhecer o pedido Bidirecional.
     * O Pedido precisa exibir o cliente, ou seja, deixaremos o Pedido Serializar o Cliente.
     */
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    /**
     * Associação da Classe Pedido - Endereço. * - 1 Associação direcionada de
     * Pedido para Endereço. Sendo assim, o Endereço não precisa conhecer o
     * pedido.
     * Como o endereço não conhece o pedido, não precisa tratar serialização cíclica.
     */
    @ManyToOne()
    @JoinColumn(name = "endereco_de_entrega_id")
    private Endereco enderecoDeEntrega;

 
    /* Como o Pedido conhece os itens associados a ele?
    
    */
    //O pedido vai conhecer os itens de pedido associado a ela.
    //Utilizando Set(Conjunto) para garantir que não exista item repetido no mesmo pedido.
    @OneToMany(mappedBy = "id.pedido")
    private Set<ItemPedido> itens = new HashSet<>();
    
    /**
     * Construtores
     */
    public Pedido() {
    }

    public Pedido(Integer id, Date instante, Cliente cliente, Endereco enderecoDeEntrega) {
        this.id = id;
        this.instante = instante;
        this.cliente = cliente;
        this.enderecoDeEntrega = enderecoDeEntrega;
    }

    public double getValorTotal(){
        double soma = 0.00;
        for (ItemPedido ip : itens){
            soma = soma + ip.getSubTotal();
        }
        return soma;
    }

    //Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getInstante() {
        return instante;
    }

    public void setInstante(Date instante) {
        this.instante = instante;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Endereco getEnderecoDeEntrega() {
        return enderecoDeEntrega;
    }

    public void setEnderecoDeEntrega(Endereco enderecoDeEntrega) {
        this.enderecoDeEntrega = enderecoDeEntrega;
    }

    public Set<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(Set<ItemPedido> itens) {
        this.itens = itens;
    }
    
    //HashCode
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.id);
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
        final Pedido other = (Pedido) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    

    
}
