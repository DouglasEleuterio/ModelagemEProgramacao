/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.douglas.cursomc.domain;

import com.douglas.cursomc.domain.Enums.TipoCliente;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author douglas
 */
@Entity
public class Cliente implements Serializable{
    
    private static final long servialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String email;
    private String cpfOuCnpj;
    private Integer tipo;
    
     //O cliente pode serializar os endereços dele
    @OneToMany(mappedBy = "cliente")
    private List<Endereco> enderecos = new ArrayList<>();
    
    @ElementCollection 
    @CollectionTable(name = "telefone")
    private Set<String> telefones = new HashSet<>();

    /**
     * Associação Cliente - Pedido. 1 - *
     * Pedidos também necessita conhecer o cliente Bidirecional
     * Não é permitido os pedidos serializar os clientes.
     */
    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos = new ArrayList<>();
    
    
    //Construtores
    public Cliente() {
    }

    public Cliente(Integer id, String nome, String email, String cpfOuCnpj, TipoCliente tipo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpfOuCnpj = cpfOuCnpj;
        this.tipo = tipo.getCod();
    }
    
    //Getters and Setters
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpfOuCnpj() {
        return cpfOuCnpj;
    }

    public void setCpfOuCnpj(String cpfOuCnpj) {
        this.cpfOuCnpj = cpfOuCnpj;
    }

    public TipoCliente getTipo() {
        return TipoCliente.toEnum(tipo);
    }

    public void setTipo(TipoCliente tipo) {
        this.tipo = tipo.getCod();
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public Set<String> getTelefones() {
        return telefones;
    }

    public void setTelefones(Set<String> telefones) {
        this.telefones = telefones;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    //HashCode
    @Override
    public int hashCode() {
        int hash = 7;
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
        final Cliente other = (Cliente) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}