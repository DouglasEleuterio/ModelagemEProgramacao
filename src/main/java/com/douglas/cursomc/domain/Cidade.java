package com.douglas.cursomc.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Entity;

/**
 * Categoria das cidades.
 * Classe responsável por representar as Cidades que
 *  serão persistidas no banco de dados.
 * Nome da Tabela no Banco de Dados: cidade.
 * Atributos:
 *  id : Integer (Gerado automaticamente pelo banco)
 *  nome : String
 * Metodos: Getters and Setters, HashCode e Equals.
 * Relacionamentos: estado: OneToMany, Muitas Cidades percetencem á um Estado;
 *
 * @see Produto
 * @author douglas eleuterio
 * @version 0.2.0
 */

@Entity(name = "cidade")
public class Cidade implements Serializable {
    private static final long servialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;

    /**
     * Relacionamento entre Cidade e Estado. Tipo de Relacionamento: ManyToOne,
     * Muitas Cidades percetencem á um Estado.
     * Coluna de assciação: "estado_id"
     */
    @ManyToOne
    @JoinColumn(name = "estado_id")
    private Estado estado;

    public Cidade() {
    }

    public Cidade(Integer id, String nome, Estado estado) {
        this.id = id;
        this.nome = nome;
        this.estado = estado;
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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
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
        final Cidade other = (Cidade) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
