package br.igor.projetofinal.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import br.igor.projetofinal.jobs.Conversation;
import br.igor.projetofinal.models.Produtos;
import br.igor.projetofinal.models.Usuario;

/**
 * Classe Server que estabelecerá o Back-end do projeto
 * @author igor
 *
 */
public class Server {
	
	//Atributos de referencia ao Cliente e forma de envio!
	public static ArrayList<String> userNames = new ArrayList<String>();
	public static ArrayList<PrintWriter> printWriters = new ArrayList<PrintWriter>();
	
	public static void main(String[] args) throws IOException {
		
		System.out.println("Aguardando conexão com o cliente...");
		
		//Criando nova conexão entre cliente e servidor, sem estabelecer comunicação
		ServerSocket ss = new ServerSocket(9010);
		
		//Criando uma conexão para o chat 
		ServerSocket server = new ServerSocket(9011);
		
		//Permitindo conexão com novos cliente
		Socket soc = ss.accept();
		
		//Permitindo conexão com cliente para o chat
		
		
		System.out.println("Estabelecendo conexão com cliente: " + soc);
		
		//Ler o Buffer do console!!!
		BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
		
		//Receptor de informações, guardamos ela em um buffer, os dados que viram do Socket!
		BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
		
		//Criou o buffer de envio de dados!
		//Esse construtor com autoFlush significa que nao vou ficar guardando dados no buffer
		//aguardando entao ser enviado! Ele recebe os dados e da flush(envia pro cliente) na mesma hora
		//Sem ele, precisamos chamar o metodo flush() sempre que quisermos estabelecer esse envio de dados
		PrintWriter out = new PrintWriter(soc.getOutputStream(),true);
		
		/**
		 * Lógica de Atribuição de variáveis e parte de configuração do projeto
		 */
		
		Usuario user;
		Produtos produto;
		String name = "";
		int number = 0;
		int option = 0;
		String result = "";
		
		//Cadastrando Usuário e iniciando a Thread
		String str[] = in.readLine().split(":");
		
		name = str[0];
		
		user = new Usuario(name);
//		user.start(); -----> se der certo excluir
			
		while(true) {	
			
			String string[] = in.readLine().split(":");
		
			name = string[0];
			number = Integer.parseInt(string[1]);
			option = Integer.parseInt(string[2]);
			
			String response = "";
			
			switch (option) {
			case 1:
				//Cadastrando Produto adicionado pelo usuário e iniciando a Thread 
				produto = new Produtos(name,number);
//				produto.start();
				
				//adicionando o produto na classe Usuario
				user.setProdutos(produto);
				
				response = "Produto Adicionado!";
				
				break;
			case 2:
				
				if(user.getProdutos().size() == 0) {
					response = "Nenhum produto cadastrado!\n Cadastre algum produto!";
				}else {
					
//					for(int i = 0; i < user.getProdutos().size(); i++) {
//						result += user.getProdutos().toString();
//					}
					result = user.getProdutos().toString();
				}
				response = result;
				
				break;
			case 3:
				
				int item = 0;
				
				if(user.getProdutos().size() == 0) {
					response = "Nenhum produto cadastrado!\n Cadastre algum produto!";
				}else {
					System.out.println(user.getProdutos().toString());
				}
				System.out.println("Digite o produto que deseja excluir:");
				
				do {
					item = Integer.parseInt(userIn.readLine());
					if(item < 1 && item > user.getProdutos().size()) {
						System.out.println("Produto não listado, escolha alguma opção que esteja na lista!");
					}
				} while (item < 1 && item > user.getProdutos().size());
				
				item -= 1;
				String strAux = user.deleteProdut(item);
				response = strAux;
				
				break;
			case 4:
				/** 
				 * Lógica do Chat
				 */
				Socket soc2 = server.accept();
				
				//Criando a parte de conversa entre cliente e servidor passando o socket como referencia
				Conversation conversation = new Conversation(soc2);
				
				//Inicio da Thread
//				response = "Iniciando Chat";
				conversation.start();

				break;
			case 0:
				response = "Servidor Fechando!";
				break;
			default:
				break;
			}
			
			if(option != 4) {
				out.println(response);
			}

			if (option == 0 ) {
                break;
            }
			
		}
		
		//Finalizando comunicação com o Cliente fechando Server, Socket, Buffer e Print
		ss.close();
		soc.close();
		in.close();
		out.close();
		server.close();
	}

}
