package br.igor.projetofinal.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import br.igor.projetofinal.jobs.ClientJob;
import br.igor.projetofinal.models.Usuario;

/**
 * Classe Server que estabelecerá o Back-end do projeto
 * @author igor
 *
 */
public class Server {
	
	public static void main(String[] args) throws IOException {
	
		try {
			System.out.println("Aguardando conexão com o cliente...");
			
			//Criando nova conexão entre cliente e servidor, sem estabelecer comunicação
			ServerSocket ss = new ServerSocket(9010);
			
			while(true) {	
				//Permitindo conexão com novos cliente
				Socket soc = ss.accept();
				
				//Receptor de informações, guardamos ela em um buffer, os dados que viram do Socket!
				BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
				
				//Criou o buffer de envio de dados!
				PrintWriter out = new PrintWriter(soc.getOutputStream(),true);
				
				System.out.println("Estabelecendo conexão com cliente: " + soc);
				
				/**
				 * Lógica de Atribuição de variáveis e parte de configuração do projeto
				 */
				//Cadastrando Usuário, ClientJob e iniciando a Thread
				Usuario user;
				String name = "";
				String str[] = in.readLine().split(":");
				name = str[0];
				
				user = new Usuario(name);
				
				ClientJob client = new ClientJob(soc, user, in, out);
				client.start();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
