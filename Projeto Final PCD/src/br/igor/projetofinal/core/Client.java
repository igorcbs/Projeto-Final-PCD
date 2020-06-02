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

		try {
			do {
				name = JOptionPane.showInputDialog("Insira o nome do usuário:");
				if(name.isEmpty()) {
					JOptionPane.showInternalMessageDialog(null, "Nome não pode ser vazio!");
				}
			} while (name.isEmpty());
			
			out.println(name + ":" + "");
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		//Finalizando socket, bufferes e printWriter
		soc.close();
		userInput.close();
		in.close();
		out.close();
		
	}

}
