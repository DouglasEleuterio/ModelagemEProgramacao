package com.douglas.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 * Classe da entidade Categoria contendo seus atributos e metodos.
 * Essa classe é acessada somente pela interface CategoriaRepository que a instanciará para manipular os atributos no Banco de Dados.
 * 
 * @author Douglas Eleutério
 * @see CategoriaRepository 
 * @version 0.0.1
  */
@Entity
public class Categoria implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	
        /**
         * Relacionamento com a classe produto.
         * Uma categoria é de vários produtos.
         * Relacionamento definido na classe produto
		 * Referencia Cíclica - Ao listar uma categoria é listado os produtos, que traz a categoria novamente, e assim forma um loop
		 * Para solucionar o problema a anotação @JsonManagedReference foi utilizada.
		 * Devemos usar a anotação do lado que desejamos que que busque os objetos associados.
		 * No outro lado da relação utiliza-se @JsonIgnore
		 * Neste cenário, as categorias trará os produtos quando houver uma solicitação do Obj.
		 * O Contrário não é verdadeiro.
         */
        @ManyToMany(mappedBy = "categorias") //Informado que o mapeamento foi realizado no atributo categorias da Classe Produto.
        private List<Produto> produtos = new ArrayList<>();
        
	public Categoria() {}
        /**
         * Método Construtor.
         * Deve utiliza-la para instanciar objetos.
         * @param id número ID do objeto.
         * @param nome nome da Categoria dado ao objeto.
         */
	public Categoria(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

        /**
         * @return Retornará o Nome do Ojbeto.
         */
	public String getNome() {
		return nome;
	}
        /** 
         * 
         * @param Define o nome do objeto. 
         */
	public void setNome(String nome) {
		this.nome = nome;
	}

        /**
         * 
         * @return Retorna o ID do objeto pesquisado.
         */
	public Integer getId() {
		return id;
	}

        /**
         * Define o ID para o objeto selecionado.
         * @param id 
         */
        public void setId(Integer id){
            this.id = id;
        }
        
        public List<Produto> getProdutos() {
            return produtos;
        }

        public void setProdutos(List<Produto> produtos) {
            this.produtos = produtos;
        }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
        /**
         * Método que compara objetos pelo se CONTEUDO.
         * @param Recebe um Objeto como parametro
         * @return Caso o conteudo seja igual, retornará TRUE.
         */

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categoria other = (Categoria) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
