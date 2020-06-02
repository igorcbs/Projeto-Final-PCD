package br.igor.projetofinal.models;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

public class Usuario extends Thread{
	
	//Atributos
	protected String nome;
	protected int id;
	protected ArrayList<Produtos> produtos = new ArrayList<Produtos>();
	protected Random number = new Random();
	protected static ArrayList<Integer> ids = new ArrayList<Integer>();
			
	//Construtor
	public Usuario(String nome){
		setNome(nome);
	}
	
	
	//Metodos
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
		this.id = id;
	}

	public ArrayList<Produtos> getProdutos() {
		return produtos;
	}

	public void setProdutos(Produtos produto) {
		this.produtos.add(produto);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		
		try {
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
			JOptionPane.showInternalMessageDialog(null, "Usuário Adicionado!");
			Thread.sleep(6000);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
}
