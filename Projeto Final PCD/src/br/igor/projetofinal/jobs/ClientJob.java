package br.igor.projetofinal.jobs;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

import br.igor.projetofinal.models.Produtos;
import br.igor.projetofinal.models.Usuario;
import br.igor.projetofinal.models.Utils;


/**
 * Classe de comunicação entre Servidor e Cliente, onde está a parte de backend do Cliente
 * @autor igor
 */
public class ClientJob extends Thread{
	
	//Atributos
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private Usuario usuario;
	
	//Construtor
	public ClientJob(Socket socket, Usuario usuario, BufferedReader in,PrintWriter out) {
		setSocket(socket);
		setUsuario(usuario);
		setIn(in);
		setOut(out);	
	}
	
	//Métodos e Getters e Setters
	public Socket getSocket() {
		return socket;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	public BufferedReader getIn() {
		return in;
	}
	public void setIn(BufferedReader in) {
		this.in = in;
	}
	public PrintWriter getOut() {
		return out;
	}
	public void setOut(PrintWriter out) {
		this.out = out;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * Método da Thread
	 * @author igor
	 *
	 */
	@Override
	public void run() {
		super.run();
		
		try {
			Produtos produto;
			String name = "";
			int number = 0;
			int option = 0;

			while(true) {
				//Comunicação com o cliente
				String string[] = in.readLine().split(":");
				
				if (string.length != 0){
					name = string[0];
					number = Integer.parseInt(string[1]);
					option = Integer.parseInt(string[2]);
					
					String response = "";
					
					switch (option) {
					case 1:
						//Cadastrando Produto adicionado pelo usuário e iniciando a Thread 
						produto = new Produtos(name,number);
						
						//adicionando o produto na classe Usuario
						usuario.setProdutos(produto);
						
						response = "Produto Adicionado!";
						
						break;
					case 2:
						//
						if(usuario.getProdutos().size() == 0) {
							Utils.mostrar("Nenhum produto cadastrado!\n Cadastre algum produto!");
						}else {
							Utils.mostrar(usuario.listarProdutos());
						}
						response = "Voltando para o menu";
						
						break;
					case 3:
						
						int item = 0;
						
						if(usuario.getProdutos().size() == 0) {
							Utils.mostrar("Nenhum produto cadastrado!\n Cadastre algum produto!");
							response = "Voltando para o menu!";
						}else {
							item = Utils.validaRemover(usuario.listarProdutos(), usuario.getProdutos().size());
						}
						
						item -= 1;
						String strAux = usuario.deleteProdut(item);
						response = strAux;
						
						break;
					case 0:
						response = "Servidor Fechando!";
						break;
					default:
						break;
					}

					if (option == 0 || option == 4) {
		                break;
		            }else {
		            	out.println(response);
		            }
				}else {
					break;
				}
				
			}	

			socket.close();
			in.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}