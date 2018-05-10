package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import util.Path;
import controller.Controller;

/**
 * Classe que representa a interface do software
 * @author Brendo Nascimento e Gabriel Azevedo
 *
 */
public class Interface extends JFrame{
	/**
	 * ID para serializacao
	 */
	private static final long serialVersionUID = 968527323003530740L;
	
	private int totalwidth = 750;
	private double tempo = 5;
	/**
	 * Contrutor da Interface que necessita de um tempo de espera entre estacoes padrao para poder iniciar
	 */
	public Interface(double tempo) {
		setTitle("Metro São Paulo - Trajeto Minimo");
		this.tempo = tempo;
	}



	static Controller control = Controller.getInstance();
	JPanel painelBotoes = null, painelGrafo = null, painelRadio = null, painelTexto = null;
	Desenho aux = null;
	String caminho = null;
	JLabel textoExibe = null;
	int tex = 40;


	JLabel lbl1, lbl2;
	JTextField txt1;
	JComboBox<String> combo, combo2;
	JComboBox<String> tempoespera;
	JButton btn1;
	JRadioButton diaDeSemana, fds, feriado;
	

	
	
	/**
	 * Metodo de construcao da tela JFrame
	 */
	public void montarTela(){
		setResizable(false);
		prepararJanela();

		prepararJpanelBotoes();
		prepararCombo();
		prepararBotaoPesquisa();
		prepararText();

		texto("Percuso");

		texto("Verde - Estacao Inicial");
		
		painelBotoes.setVisible(false);
		painelBotoes.setVisible(true);

		prepararGrafo(this.tempo);

	}
	
	/**
	 * Metodo que define as caracterï¿½sticas da janela
	 */
	private void prepararJanela() {
		this.setBounds(0, 0, totalwidth, 700);
		// No windows funcinoa eu acho
		getContentPane().setLayout(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.setVisible(true);

	}
    
	/**
	 * Metodo que define as caracteristicas dos botoes do painel
	 */
	public void prepararJpanelBotoes(){
		painelBotoes = new JPanel();
		painelBotoes.setBounds(0, 0, totalwidth, 80);

		painelBotoes.setBackground(Color.WHITE);
		// painelBotoes.setLayout(null);
		getContentPane().add(painelBotoes);

		painelBotoes.setVisible(true);

	}
	
	/**
	 * Metodo que instancia o painel que contem o desenho do grafo
	 */
	public void prepararGrafo(Double te){
		aux = new  Desenho(control, te);
		painelGrafo = new JPanel();
		painelGrafo.setBounds(10, 100, 875, 600);
		
		getContentPane().add(painelGrafo);
		painelGrafo.add(aux);
		aux.desenharPontos(painelGrafo.getGraphics());
		painelGrafo.setVisible(true);
	}
	/**
	 * Metodo que instancia o painel com os nomes da estaï¿½ï¿½es
	 */
	public void prepararText(){
		JPanel panelback = new JPanel();
		painelTexto = new JPanel();
		panelback.setBounds(0, 0, totalwidth, 100);
		panelback.setBackground(Color.white);
		
		painelTexto.setBounds(250, 40, totalwidth/2, 100);
		panelback.add(painelTexto);
		panelback.setVisible(true);
		getContentPane().add(panelback);
		painelTexto.setBackground(Color.white);
		painelTexto.setVisible(true);

	}
	/**
	 * Metodo que exibe um texto 
	 * @param d Texto a ser exibido
	 */
	public void texto(String d){
		textoExibe = new JLabel(d);
		tex = tex + 40;
		textoExibe.setBounds(0,tex, totalwidth, 40);
		painelTexto.add(new JLabel(d), BorderLayout.CENTER);
		textoExibe.setVisible(true);
	}
	/**
	 * Metodo que instancia os JRadioButton que serao exibidos na interface
	 */
	public void prepararRadio(){
		painelRadio = new JPanel();
		painelRadio.setBounds(0, 40, totalwidth, 40);
		painelRadio.setBackground(Color.BLUE);

		diaDeSemana = new JRadioButton();
		fds = new JRadioButton();
		feriado = new JRadioButton();

		fds.setBounds(1, 1, 60, 15);
		feriado.setBounds(1, 1, 60, 15);
		diaDeSemana.setBounds(1, 1, 60, 15);

		painelRadio.add(fds);
		painelRadio.add(feriado);
		painelRadio.add(diaDeSemana);

		painelRadio.setVisible(true);

	}
	/**
	 * Metodo que instancia as combo boxes que serao adicionadas na interface
	 */
	public void prepararCombo() {
		/* Esses sao vetores de exemplo */
		String[] estacoes = control.vetorVertices();
		String[] estacoes2 = control.vetorVertices();

		// As combo boxes

		lbl1 = new JLabel("Estação 1");

		lbl1 = new JLabel("Estacao 1");

		combo = new JComboBox<String>(estacoes);
		combo.setBounds(10, 10, 200, 20);
		painelBotoes.add(lbl1);
		painelBotoes.add(combo);


		lbl2 = new JLabel("Estacao 2");

		combo2 = new JComboBox<String>(estacoes2);
		combo2.setBounds(10, 30, 200, 20);
		painelBotoes.add(lbl2);
		painelBotoes.add(combo2);
		
		//Caixa de texto
		JLabel lbl3 = new JLabel("Tempo de Espera nas estacoes: ");
		
		tempoespera = new JComboBox<>(vetor1a10());
		tempoespera.setBounds(10, 50, 50, 20);
		painelBotoes.add(lbl3);
		painelBotoes.add(tempoespera);
		painelBotoes.add(new JLabel(" min  "));
		

	}	
	

	private String[] vetor1a10(){
		String[] vetor = new String[10];
		
		for(Integer i = 0; i<10;i++){
			vetor[i] = i.toString() ;
		}
		return vetor;
	}
	
	/*
	 * Metodo para preparar a caixa de texto para o tempo de espera entre estações
	 */
	public void prepararCaixanum() {
		
	}
	
	

	/**
	 * Metodo que instancia o botao para a pesquisa do menor caminho entre duas estacoes
	 */
	public void prepararBotaoPesquisa(){
		JButton botaoPesquisar = new JButton("Buscar Trajeto");
		botaoPesquisar.setBounds(10, 20, 40, 20);
		botaoPesquisar.addActionListener(new ActionListener() {

			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent arg0) {

				String estacao1, estacao2;
				aux.clear();	


				painelGrafo = null;				

				System.out.println(tempoespera.getSelectedItem());
				System.out.println(Double.parseDouble((String) tempoespera.getSelectedItem()));
				

				painelGrafo = null;


				prepararGrafo(Double.parseDouble((String) tempoespera.getSelectedItem()));
				estacao1 = (String) combo.getSelectedItem();
				estacao2 = (String) combo2.getSelectedItem();
				Stack<Path> caminhopequeno = control.caminhominimo(estacao1, estacao2);
				aux.paintCaminho(caminhopequeno, painelGrafo.getGraphics());
				
				painelGrafo.setVisible(true);

			}
		});
		painelBotoes.add(botaoPesquisar);

	}
	
	/**
	 * Metodo para preparar a caixa de texto para o tempo de espera entre estações
	 */
	/*public void caixaDeTexto() {
		JTextField fixa = new JTextField();
		fixa.setBounds(60, 70, 40, 30);
		fixa.setEditable(true);
		JLabel lbl3 = new JLabel("Tempo de espera");
		lbl3.setBounds(30, 90, 100, 90);
		add(lbl3);
		add(fixa);
	}*/
	

}
