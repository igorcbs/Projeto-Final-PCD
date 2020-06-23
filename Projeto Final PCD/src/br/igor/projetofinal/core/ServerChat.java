package br.igor.projetofinal.core;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import br.igor.projetofinal.jobs.Conversation;

public class ServerChat {
	
	// Atributos do Servidor, para ter referencia aos Clientes e formas de envio!
	public static ArrayList<String> userNames = new ArrayList<String>();
	public static ArrayList<PrintWriter> printWriters = new ArrayList<PrintWriter>();


	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		//Criando uma conexão para o chat 
		ServerSocket server = new ServerSocket(9011);
		
		try {
			while(true) {
				Socket soc2 = server.accept();
				
				//Criando a parte de conversa entre cliente e servidor passando o socket como referencia
				Conversation conversation = new Conversation(soc2);
				
				//Inicio da Thread
//				response = "Iniciando Chat";
				conversation.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		server.close();

	}

}
