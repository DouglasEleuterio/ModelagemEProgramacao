
package com.douglas.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Entity;

/**
 * Estados da Federação. <br/>
 * Classe responsável por representar os Estados Brasileiros que 
 *  serão persistidas no banco de dados. <br/>
 * Nome da Tabela no Banco de Dados: estado.<br/>
 * Atributos: 
 *  <br/> &nbsp id : Integer (Gerado automaticamente pelo banco) 
 *  <br/> &nbsp nome : String <br/>
 * Metodos: Getters and Setters, HashCode e Equals.<br/>
 *  Relacionamento: <br/> 
 *      &nbsp cidades: OneToMany, Muitas Cidades percetencem á um Estado.
 * <br/>Instancia e inicializa uma lista de Cidades.
 *
 * @see Cidade
 * @author douglas eleuterio
 * @version 0.2.0
 */

@Entity(name = "estado")
public class Estado implements Serializable{
    
    private static final long servialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    
    
    @JsonIgnore //O estado não pode serializar suas cidades
    @OneToMany(mappedBy = "estado")
    private List<Cidade> cidades = new ArrayList<>();

    
    
    public Estado() {
    }

    public Estado(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
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

    public List<Cidade> getCidades() {
        return cidades;
    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.id);
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
        final Estado other = (Estado) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
}
