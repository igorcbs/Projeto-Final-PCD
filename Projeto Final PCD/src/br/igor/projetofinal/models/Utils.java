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
	
}
