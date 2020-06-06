package br.igor.projetofinal.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class Client extends Thread{

	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("Cliente iniciando!");
		
		//Criando socket de comunicação
		Socket soc =  new Socket("localhost",9010);
		
		//Ler o Buffer do console!!!
		BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
		
		//Ler o Buffer do Socket em si!
		BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
		
		//Fazendo o envio de dados para o outputStream, ou seja, via Socket
		PrintWriter out = new PrintWriter(soc.getOutputStream(),true);
		
		//Logica de front-end do projeto
		String name = "";
		String nameProdut = "";
		int qtdEstoque = 0;
		int option = 0;
		
		//Adicionando informações do usuário
		do {
			name = JOptionPane.showInputDialog("Insira o nome do usuário:");
			if(name.isEmpty()) {
				JOptionPane.showInternalMessageDialog(null, "Nome não pode ser vazio!");
			}
		} while (name.isEmpty());
		
		out.println(name + ":" + "");
		
		//Menu para Adicão de Produtos, Remoção de Produto, 
		do {
			
			do {
				System.out.println(" -------- Bem vindo! --------");
				System.out.println("1 - Cadastrar Produto");
				System.out.println("2 - Listar Produtos");
				System.out.println("3 - Remover Produto");
				System.out.println("4 -  Chat com Fornecedor");
				System.out.println("0 - Sair!");
				System.out.println("Escolha uma das opções acima:");
				option = Integer.parseInt(userInput.readLine());
				
			} while (option <= -1 || option >= 5);
			
			switch (option) {
			case 1:
				//Cadastrando Produto com validações *colocar validações em uma classe Utils
				do {
					nameProdut = JOptionPane.showInputDialog("Insira o nome do produto:");
					if(nameProdut.isEmpty()) {
						JOptionPane.showInternalMessageDialog(null, "Nome não pode ser vazio!");
					}
				} while (nameProdut.isEmpty());
				do {
					qtdEstoque = Integer.parseInt(JOptionPane.showInputDialog("Insira a quantidade do produto no estoque:"));
					if(qtdEstoque <= 0) {
						JOptionPane.showInternalMessageDialog(null, "A quantidade no estoque não pode ser \nmenor ou igual a 0!");
					}
				} while (qtdEstoque <= 0);

				
				out.println(nameProdut + ":" + qtdEstoque + ":" + option);
				
				break;
			case 2:
				//Listagem: Mando a opção para o Server para identificar a listagem 
				out.println("" + ":"+ 0 + ":" + option);
				
				break;
			case 3:
				//Remoção de algum produto desejado
				out.println("" + ":"+ 0 + ":" + option);
				
				break;
			case 4:
				break;
			case 0:
				out.println(option + ":0:0");
				break;
			default:
				break;
			}
			
			String response = in.readLine();
			
			//Printamos a resposta do server!
			System.out.println("-------------");
			System.out.println(response);
			System.out.println("-------------");

			
			
		}while(option != 0);

		
		
		//Finalizando socket, bufferes e printWriter
		soc.close();
		userInput.close();
		in.close();
		out.close();
		
	}

}
