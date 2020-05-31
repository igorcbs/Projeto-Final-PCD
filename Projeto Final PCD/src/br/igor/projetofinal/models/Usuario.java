package br.igor.projetofinal.models;

import java.util.ArrayList;
import java.util.Random;

public class Usuario {
	
	//Atributos
	protected String nome;
	protected int id;
	protected ArrayList<Produtos> produtos = new ArrayList<Produtos>();
	protected Random number = new Random();
	protected static ArrayList<Integer> ids = new ArrayList<Integer>();
			
	//Construtor
	public Usuario(String nome){
		setNome(nome);
		
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
		setId(id);
	}
	
	
	//Metodos
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Produtos> getProdutos() {
		return produtos;
	}

	public void setProdutos(ArrayList<Produtos> produtos) {
		this.produtos = produtos;
	}
	
	
}
