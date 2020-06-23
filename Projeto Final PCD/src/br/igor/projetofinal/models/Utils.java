package br.igor.projetofinal.models;

import javax.swing.JOptionPane;
/**
 * Essa Classe tem como objetivo validar tipos de atributos
 * @author igor
 *
 */

public class Utils {
	/**
	 * Função que valida string que esteja vazia
	 * @return string não vazia
	 */
	public static String validaString() {
		String text = "";
		do {
			text = JOptionPane.showInputDialog("Insira o nome do produto:");
			if(text.isEmpty()) {
				JOptionPane.showInternalMessageDialog(null, "Nome não pode ser vazio!");
			}
			
		}while(text.isEmpty());
		
		return text;
		
	}
	/**
	 * Função que valida algum numero inteiro
	 * @return numero inteiro validado
	 */
	public static int validaInteiro() {
		
		int qtd = 0;
		
		do {
			qtd = Integer.parseInt(JOptionPane.showInputDialog("Insira a quantidade do produto no estoque:"));
			if(qtd <= 0) {
				JOptionPane.showInternalMessageDialog(null, "A quantidade no estoque não pode ser \nmenor ou igual a 0!");
			}
		} while (qtd <= 0);
		
		return qtd;
	}
	
	/**
	 * Função que mostra o menu e valida a opção
	 * @return numero inteiro validado
	 */
	public static int menu() {
		int option = 0;
		
		do {
			option = Integer.parseInt(JOptionPane.showInputDialog(null, " -------- Bem vindo! --------\n" 
																	+ "1 - Cadastrar Produto\n" 
																	+ "2 - Listar Produtos\n" 
																	+ "3 - Remover Produto\n" 
																	+ "4 -  Chat com Fornecedor\n"
																	+ "0 - Sair!\n"
																	+ "Escolha uma das opções acima:"));
			
			if(option <= -1 || option >= 5) {
				JOptionPane.showMessageDialog(null, "Opção incorreta!\nDigite a opção conforme o número correspondente de opções!");
			}
			
		} while (option <= -1 || option >= 5);
		
		return option;
	}
	
	/**
	 * Função mostra coisas para o usuário
	 * @return nenhum
	 */
	public static void mostrar(String string) {
		
		JOptionPane.showMessageDialog(null, string, "Listar", JOptionPane.INFORMATION_MESSAGE);
	}
}
