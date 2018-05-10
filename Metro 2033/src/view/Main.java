package view;

import java.awt.EventQueue;

import controller.Controller;

public class Main {

	/**
	 * Inicializa a Aplicação.
	 * 
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		Controller.getInstance().lerAquivo("MapaMetro.txt");
		Controller.getInstance().lerCoordenadas("Coordenadas.txt");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SaoPauloInterfaceMetro window = new SaoPauloInterfaceMetro();
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
