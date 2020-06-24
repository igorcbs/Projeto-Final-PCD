package br.igor.projetofinal.models;

import java.util.ArrayList;
import java.util.Random;

/**
 * Classe Usuário do projeto
 * @author igor
 *
 */
public class Usuario{
	
	//Atributos
	protected String nome;
	protected int id;
	protected ArrayList<Produtos> produtos = new ArrayList<Produtos>();
	protected Random number = new Random();
	protected static ArrayList<Integer> ids = new ArrayList<Integer>();
			
	//Construtor
	public Usuario(String nome){
		setNome(nome);
		setId(id);
	}
	
	//Metodos e Getters e Setters
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		if(ids.isEmpty()) {
			id = number.nextInt(1000);
			ids.add(id);
			
		}else {
			id = number.nextInt(1000);
			for (Integer integer : ids) {
				if(integer == id) {
					id = number.nextInt(1000);
				}
			}
			ids.add(id);
		}
		this.id = id;
	}

	public ArrayList<Produtos> getProdutos() {
		return produtos;
	}

	public void setProdutos(Produtos produto) {
		this.produtos.add(produto);
	}
	
	/**
	 * Função de Deletar Produto
	 * @return string com a lista de produtos
	 */
	public String deleteProdut(int escolhido) {
		
		try {
			this.produtos.remove(escolhido);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "Item removido com sucesso!";
	}
	
	/**
	 * Função de Listar produtos
	 * @return string com a lista de produtos
	 */
	public String listarProdutos() {
		String str = "";
		
		for (Produtos produto : produtos) {
			str =  produto.listarProdutos();
		}
		return str;
	}
	
}
