package com.douglas.cursomc.domain;

import com.douglas.cursomc.domain.Enums.Perfil;
import com.douglas.cursomc.domain.Enums.TipoCliente;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.*;

/**
 * Clientes do Sistema.
 * Classe responsável por representar os Clientes que
 *  serão persistidos no banco de dados.
 * Nome da Tabela no Banco de Dados: cliente.
 * Atributos:
 *  id : Integer (Gerado automaticamente pelo banco)
 *  nome : String
 *  email: String
 *  cpfOuCnpj: String
 *  tipoCliente : TipoCliente
 * Metodos: Getters and Setters, HashCode e Equals.
 * Relacionamentos:
 *      endereço: OneToMany um cliente possui muitos endereços.
 *      pedido: OneToMany um cliente possui muitos pedidos.
 * Instancia e inicializa uma lista de Endereços, Telefones e Pedidos.
 * 
 * @see TipoCliente
 * @see Endereco
 * @see Pedido
 * @author douglas eleuterio
 * @version 0.2.0
 */

@Entity(name = "cliente")
public class Cliente implements Serializable {

    private static final long servialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;

    @Column(unique = true)
    private String email;
    private String cpfOuCnpj;
    private Integer tipo;

    @JsonIgnore // Nao deixaremos o senha ficar sendo recuperada, mesmo se tratando apenas do Hash dela.
    private String senha;

    /* Intancia e Inicializa uma lista de Endereços.
     * O mapeamento foi definido na classe "Cliente".
     * Ao apagar um cliente, obrigátoriamente excluiremos os endereços associados e este cliente.
     */

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL) //Toda operação que modificar um cliente, refletirá em casatada nos endereços.
    private List<Endereco> enderecos = new ArrayList<>();

    /*
     * Instancia e inicia um conjunto de telefones.
     * Será construida uma tabela chamada Telefones no banco de dados
     * A tabela telefones não terá identificador, será criada uma coluna chamada CLIENTE_ID.
     * A chave estrangeria de cliente estará nessa tabela para fazer a junção do cliente ao telefone.
     */
    @ElementCollection
    @CollectionTable(name = "telefone")
    private Set<String> telefones = new HashSet<>();

    /**
     * Quando buscarmos o cliente do banco de dados, queremos que o Perfil seja retornardo também.
     * Utilizaremos o Set para guarda-lo como uma coleção.
     * Criamos a tabela 'PEFIS' no Banco de Dados.
     */
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "PERFIS")
    private Set<Integer> perfis = new HashSet<>();

    /* 
     * Instancia e Inicializa uma lista de pedidos.
     * O mapeamento foi definido na classe cliente.
     */
    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos = new ArrayList<>();

    //Construtores
    public Cliente() {
        addPerfil(Perfil.CLIENTE);
    }

    public Cliente(Integer id, String nome, String email, String cpfOuCnpj, TipoCliente tipo, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpfOuCnpj = cpfOuCnpj;
        this.tipo = (tipo==null) ? null : tipo.getCod();
        this.senha = senha;
        addPerfil(Perfil.CLIENTE);
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    /**
     *
     * @return Perfil
     */
    public Set<Perfil> getPerfis() {
        return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
    }

    public void addPerfil(Perfil perfil) {
        perfis.add(perfil.getCod());
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
