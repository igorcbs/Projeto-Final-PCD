package br.igor.projetofinal.models;

import java.util.UUID;

/**
 * Classe Produtos do projeto
 * @author igor
 *
 */
public class Produtos extends Thread{
	
	//Atributos
	protected String nome;
	protected int qtdEstoque;
	protected String idProduto;
	
	
	//Construtor
	public Produtos(String nome, int qtd) {
		setNome(nome);
		setQtdEstoque(qtd);
	}

	//Metodos
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
	
	@Override
	public String toString() {
		return "Nome:" + this.getNome() + " Quantidade no Estoque:" + this.getQtdEstoque() + " IdProduto:" + this.getIdProduto();
	}
	
	//Metodo da Thread
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		
		try {
			setIdProduto(UUID.randomUUID().toString().replace("-", ""));
			Thread.sleep(3000);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}

