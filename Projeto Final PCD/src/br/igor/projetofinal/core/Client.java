package br.igor.projetofinal.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

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
		
		
		
		
		//Finalizando socket, bufferes e printWriter
		soc.close();
		userInput.close();
		in.close();
		out.close();
		
	}

}
