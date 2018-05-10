package view;

import javax.swing.JFrame;

public class SaoPauloInterfaceMetro {

	private JFrame frame;

	/**
	 * Cria a Aplicacao.
	 */
	public SaoPauloInterfaceMetro() {
		initialize();
	}

	/**
	 * Inicializa o conteúdo do Frame
	 */
	private void initialize() {
		setFrame(new Interface(1));
		((Interface) getFrame()).montarTela();

	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

}
