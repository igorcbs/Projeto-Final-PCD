package br.igor.projetofinal.jobs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import br.igor.projetofinal.core.Server;
/**
 * Classe Conversation gerenciará a conversa entre client e servidor do Chat
 * @author igor
 *
 */
public class Conversation extends Thread{
	
	//Atributos
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private String userName;
	
	//Construtor
	public Conversation(Socket socket) {
		setSocket(socket);
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	//Metodos da Thread
	@Override
	public void run() {
		super.run();
		
		//Instanciando inputStream e outputstream
		
		try {
			System.out.println("Thread");
			//Iniciando o In
			setIn(new BufferedReader(new InputStreamReader(getSocket().getInputStream())));
			
			//Iniciando o Out
			setOut(new PrintWriter(getSocket().getOutputStream(),true));
			
			//Mensagem do Servidor para o Cliente solicitando um nome
			getOut().println("NAMEREQUIRED");
			
			//Atribuindo o nome vindo do Cliente
			setUserName(getIn().readLine());
			
			//Verificação se o nome esta nulo ou não, caso esteja ele não entra no chat
			if(getName() == null) { 
				return;
			}
			
			//Caso o nome seja unico, podemos adiciona-lo ao servidor
			if(!Server.userNames.contains(getUserName())) {
				
				//Adicionando o nome 
				Server.userNames.add(getUserName());
				//Dizemos ao cliente que o nome foi aceito
				getOut().println("NAMEACCEPTED");
				// Adicionamos o PrintWriter (nosso OutputStream) ao Servidor para ele receber todos os dados
				Server.printWriters.add(getOut());
			}
			
			//Enviando mensagens para todos os clientes
			
			while(true) {
				
				//Recebendo a mensagem vinda do Cliente
				String clientMessage = in.readLine();
				
				//Verificando se a mensagem é nula
				if(clientMessage == null) {
					return;
				}
				
				//Não sendo nula, envia a mensagem para todos os clientes
				for(PrintWriter writer: Server.printWriters) {
					//Formantando a mensagem para ser entregue ao cliente
					writer.println(getUserName() + ":" + clientMessage);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
