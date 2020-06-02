package br.igor.projetofinal.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import br.igor.projetofinal.models.Produtos;
import br.igor.projetofinal.models.Usuario;

public class Server {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		System.out.println("Aguardando conexão com o cliente...");
		
		//Criando nova conexão entre cliente e servidor, sem estabelecer comunicação
		ServerSocket ss = new ServerSocket(9010);
		
		//Permitindo conexão com novos cliente
		Socket soc = ss.accept();
		
		System.out.println("Estabelecendo conexão com cliente: " + soc);
		
		//Receptor de informações, guardamos ela em um buffer, os dados que viram do Socket!
		BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
		
		//Criou o buffer de envio de dados!
		//Esse construtor com autoFlush significa que nao vou ficar guardando dados no buffer
		//aguardando entao ser enviado! Ele recebe os dados e da flush(envia pro cliente) na mesma hora
		//Sem ele, precisamos chamar o metodo flush() sempre que quisermos estabelecer esse envio de dados
		PrintWriter out = new PrintWriter(soc.getOutputStream(),true);
		
		//Logica de atribuição do projeto
		
		Usuario user;
		Produtos produto;
		String name = "";
		int number = 0;
		int option = 0;
		
		String str[] = in.readLine().split(":");
		
		name = str[0];
		
		user = new Usuario(name);
		user.start();
			
			
		while(true) {	
			
			String string[] = in.readLine().split(":");
		
			name = string[0];
			number = Integer.parseInt(string[1]);
			option = Integer.parseInt(string[2]);
			
			String response = "";
			
			switch (option) {
			case 1:
				produto = new Produtos(name,number);
				produto.start();
				
				user.setProdutos(produto);
				
				response = "Produto Adicionado!";
				
				break;
			case 2:
				response = "Ainda em reforma!";
				break;
			case 3:
				response = "Ainda em reforma!";
				break;
			case 4:
				response = "Ainda em reforma!";
				break;
			default:
				response = "Ainda em reforma!";
				break;
			}
			
			if(option == 0) break;
			
			out.println(response);
			
		}
		
		//Finalizando comunicação com o Cliente fechando Server, Socket, Buffer e Print
		ss.close();
		soc.close();
		in.close();
		out.close();
	}

}
