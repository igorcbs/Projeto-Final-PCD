package br.igor.projetofinal.core;

import java.awt.FlowLayout;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.*;
import java.io.*;

import br.igor.projetofinal.listener.ChatListener;
import br.igor.projetofinal.models.Utils;
/**
 * Classe Client onde será feita a configuração de Front-end do projeto
 * @author igor
 *
 */
public class Client{

	//Front-end --- chat
	//Janela do chat
	static JFrame chatWindow = new JFrame("Chat");
	//Area de texto que vai ser populada
	static JTextArea chatArea = new JTextArea(22, 45);
	//TextField
	public static JTextField textField = new JTextField(30);
	//Label
	static JLabel label = new JLabel("          ");
	//Botão
	static JButton button = new JButton("Enviar");
	
	//Atributos do Cliente
	static BufferedReader in;
	public static PrintWriter out;
	public static Socket soc;
	
	//Construtor vazio para quando for escolhido a opção de Chat ele iniciar
	Client() {
		//Coordenador que irá redenrizar e ajustar os elementos na tela
		chatWindow.setLayout(new FlowLayout());
		
		//Adicionando os elementos visuais
		chatWindow.add(new JScrollPane(chatArea));
		chatWindow.add(label);
		chatWindow.add(textField);
		chatWindow.add(button);
		chatWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		chatWindow.setSize(475, 500);
		chatWindow.setVisible(true);
		textField.setEditable(false);
		chatArea.setEditable(false);
		
		//Ações do Botão e do TextField
		button.addActionListener(new ChatListener());
		textField.addActionListener(new ChatListener());
	}
	
	//Main
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		System.out.println("Cliente iniciando!");
		
		//Criando socket de comunicação
		soc =  new Socket("localhost",9010);
		
		//Ler o Buffer do console!!!
		BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
		
		//Ler o Buffer do Socket em si!
		in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
		
		//Fazendo o envio de dados para o outputStream, ou seja, via Socket
		out = new PrintWriter(soc.getOutputStream(),true);
		
		
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
			
			option = Utils.menu();
			
			switch (option) {
			case 1:
				//Cadastrando Produto com validações *colocar validações em uma classe Utils
				nameProdut = Utils.validaString();				
				qtdEstoque = Utils.validaInteiro();

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
				
				//Instanciamos o cliente onde aparecerá os elementos visuais
				Client client = new Client();
				
				try {
//					System.out.println(name);
					client.startChat(name);
				} catch (Exception e) {
					e.printStackTrace();
				}

				break;
			case 0:
				out.println(option + ":0:0");
				break;
			default:
				break;
			}

			if(option != 0 || option != 4) {
				String response = in.readLine();
				
				//Printamos a resposta do server!
				System.out.println("-------------");
				System.out.println(response);
				System.out.println("-------------");
			}

			
		}while(option != 0);

		//Finalizando socket, bufferes e printWriter
		soc.close();
		userInput.close();
		in.close();
		out.close();
		
	}
	
	//Metodo Core do nosso Cliente
	public void startChat(String userName) throws Exception {
		
		Socket socket = new Socket("localhost",9011);
		
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		// Iniciamos o nosso Out (remetente de dados) com o OutputStream do Socket, dando como true o AutoFlush!
		out = new PrintWriter(socket.getOutputStream(), true);	
		
		try {
			//Logica de envio e recepção de mensagens
			while(true) {
				//Recebendo mensagem do servidor
				String serverMsg = in.readLine();
				
				if(serverMsg.contentEquals("NAMEREQUIRED")) {
					//Enviando a mensagem pro servidor
					out.println(userName);
					chatWindow.setTitle("Aplicacao de Chat - Logado como: " + userName);
					
				}else if(serverMsg.contentEquals("NAMEACCEPTED")) {
					//Entra nesse if se o nome foi aceito
					System.out.println(serverMsg);
					textField.setEditable(true);
				}else {
					//aqui mostrará as mensagens para os clientes envolvidos
					chatArea.append(serverMsg + "\n");
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		socket.close();
	}


}
