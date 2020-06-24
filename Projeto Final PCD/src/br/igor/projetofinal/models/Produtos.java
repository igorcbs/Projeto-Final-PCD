package br.igor.projetofinal.models;

import java.util.UUID;

/**
 * Classe Produtos do projeto
 * @author igor
 *
 */
public class Produtos{
	
	//Atributos
	protected String nome;
	protected int qtdEstoque;
	protected String idProduto;
	
	
	//Construtor
	public Produtos(String nome, int qtd) {
		setNome(nome);
		setQtdEstoque(qtd);
		setIdProduto(UUID.randomUUID().toString().replace("-", ""));
	}

	//Métodos e Getters e Setterss
	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {

		this.nome = nome;
	}


	public int getQtdEstoque() {
		return qtdEstoque;
	}


	public void setQtdEstoque(int qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}


	public String getIdProduto() {
		return idProduto;
	}


	public void setIdProduto(String idProduto) {
		this.idProduto = idProduto;
	}
	
	/**
	 * Função de Listar Produtos
	 * @return string com a lista de produtos
	 */
	public String listarProdutos() {
		return "Nome:" + this.getNome() + " Quantidade no Estoque:" + this.getQtdEstoque() + " IdProduto:" + this.getIdProduto() + "\n";
	}

}

