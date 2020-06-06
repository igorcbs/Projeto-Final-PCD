package br.igor.projetofinal.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.igor.projetofinal.core.Client;

/**
 * Classe ChatListener que gerenciará ações de botões e textfield
 * @author igor
 *
 */
public class ChatListener implements ActionListener{

	//Metodo que serjá acionado toda vez que for clicado "Enviar
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//É enviado o texto que esta na textfield digitada pelo usuario
		Client.out.println(Client.textField.getText());
		
		//Limpando o textfield
		Client.textField.setText("");
		
	}
	
	
	
	
}
