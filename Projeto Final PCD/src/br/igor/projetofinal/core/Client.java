package br.igor.projetofinal.core;

import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.igor.projetofinal.listener.ChatListener;
import br.igor.projetofinal.models.Utils;
/**
 * Classe Client onde será feita a configuração de Front-end do projeto
 * @author igor
 *
 */
public class Client{

	//Front-end --- chat
	//Criando a janela
	static JFrame chatWindow = new JFrame("Aplicando Chat");
	
	//Criando uma area de texto que possa ser populada
	static JTextArea chatArea = new JTextArea(22, 45);
	
	//Criando textfiled para inserção de texto
	public static JTextField textField = new JTextField(45);
	
	//Criando uma label
	static JLabel blankLabel = new JLabel("     ");
	
	//Criando botao de envio
	static JButton sendButton = new JButton("Enviar");
	
	static BufferedReader in;
	public static PrintWriter out;
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		System.out.println("Cliente iniciando!");
		
		//Criando socket de comunicação
		Socket soc =  new Socket("localhost",9010);
		
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
				//Criando estrutura para o chat
				
				// Para renderizar e ajustar todos os elementos na tela, precisamos de um coordenador, o Flow Layout!
				chatWindow.setLayout(new FlowLayout());
				
				//Adicionando os elementos visuais 
				chatWindow.add(new JScrollPane(chatArea));
				chatWindow.add(blankLabel);
				chatWindow.add(textField);
				chatWindow.add(sendButton);
				
				chatWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				chatWindow.setSize(475, 500);
				chatWindow.setVisible(true);
				
				textField.setEditable(false);
				chatArea.setEditable(false);
				
				//atrelando classe ChatListener como o botão enviar
				sendButton.addActionListener(new ChatListener());
				//adicionando em ChatListener para quando clicar em Enter
				textField.addActionListener(new ChatListener());
				
				Client client = new Client();
				
				try {
					client.startChat(name);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
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
	
	public void startChat(String userName) throws Exception {
		
		String ipAddress = JOptionPane.showInputDialog(chatWindow, "Digite o endereço IP do chat", "Endereço IP", JOptionPane.PLAIN_MESSAGE);
		
		while(true) {
			
			//Recebendo a mensagem 
			String serverMsg = in.readLine();
			
			if(serverMsg.contentEquals("NAMEREQUIRED")) {
				out.println(userName);
				chatWindow.setTitle("Aplicação Chat - Logado como: " + userName);
				
			}else if (serverMsg.contentEquals("NAMEACCEPTED")) {
				//Se entrou aqui é porque o nome foi aceito e podemos desloquear o textfield para receber input do usuario
				System.out.println(serverMsg);
				textField.setEditable(true);
			
			}else {
				
				//Aqui mostraremos as mensagens enviadas pelos clientes
				chatArea.append(serverMsg + "\n");
			}
			
		}
	
		
		
	}

}
