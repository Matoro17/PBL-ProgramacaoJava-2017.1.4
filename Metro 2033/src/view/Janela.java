package view;

import javax.swing.JFrame;

/**
 * Classe que cria uma janela de interface
 * @author Brendo Nascimento e Gabriel Azevedo
 *
 */
public class Janela {
	
	/**
	 * Método construtor
	 */
	public Janela(){
		JFrame frame = new JFrame();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400,400);
		frame.setVisible(true);
		
	}
    
	/**
	 * Método principal
	 * @param args
	 */
	public static void main(String args[]){
		new Janela();
	}
	
	
}
