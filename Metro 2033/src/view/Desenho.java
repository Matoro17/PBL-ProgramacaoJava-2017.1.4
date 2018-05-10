package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.Stack;

import javax.swing.JPanel;

import controller.Controller;
import model.Vertice;
import util.Interador;
import util.Path;

/**
 * Classe respons�vel por criar o desenho do grafo na interface
 * @author Brendo Nascimento e Gabriel Azevedo
 *
 */
@SuppressWarnings("serial")
public class Desenho extends JPanel{
	private Controller control;
	private double espera = 0;
	/**
	 * Metodo construtor
	 * @param control Variavel que representa a classe controler para aplicacao de seus metodos
	 */
	public Desenho(Controller control, double tempoEstacao){	
		this.espera = tempoEstacao;
		this.control = control;	
		this.setBounds(100, 100, 1000, 600);
		
		this.setVisible(true);
	}
	
	/**
	 * Metodo que desenha os pontos do grafo na interface
	 */
	public void paint(Graphics g){
		desenharPontos(g);
		
		g.clearRect(0, 0, getWidth(), getHeight());
		
		desenharPontos(g);
	}
	
	/**
	 * Metodo que limpa um caminho minimo tracado
	 */
	public void clear() {
		Graphics g = getGraphics();
		g.clearRect(0, 0, getWidth(), getHeight());		
		
	}
	
	/**
	 * Metodo que destaca o caminho minimo entre dois vertices do grafo
	 * @param caminho Pilha que contem o caminho minimo
	 * @param g Objeto que contem os metodos para fazer o desenho grafico
	 * @return Os nomes das estacoes do caminho e o tempo de espera
	 */
	public double paintCaminho(Stack<Path> caminho, Graphics g){
		Graphics2D g2d = (Graphics2D) g.create();        
        g2d.setFont(new Font("Serif", Font.PLAIN, 16));
		double tempototal = 0, tempototal2 = espera * (caminho.size()-2);
		int first = 2;
		int rot = -45;
		
		while(!caminho.isEmpty()){
			Path temp = caminho.pop();
			
			if(first > 0){
				g.setColor(Color.BLUE);
				g2d.setColor(Color.blue);
				first--;
				rot = 0;
			}
			else{
				tempototal = temp.getTime();
				g.setColor(Color.orange);
				g2d.setColor(Color.ORANGE);
				rot = -45;
			}
			
			Vertice ponto = (Vertice) control.getVerticesXY().encontrarVertice(new Vertice(temp.getVertex()));
			Vertice pontoAnter = (Vertice) control.getVerticesXY().encontrarVertice(new Vertice(temp.getPrevious()));
			if(pontoAnter != null) {				
		        g2d.setTransform(AffineTransform.getRotateInstance(Math.toRadians(rot), pontoAnter.getX() + 8, pontoAnter.getY() -3));
		        g2d.drawString(pontoAnter.getNome(),  pontoAnter.getX() + 8, pontoAnter.getY() -3);
		        
				g.fillOval(pontoAnter.getX() -  4, pontoAnter.getY() -4, 8, 8);
				g.drawLine(ponto.getX(), ponto.getY(), pontoAnter.getX(), pontoAnter.getY());
			}
			if(caminho.isEmpty()){
				g2d.setColor(Color.GREEN);
				g.setColor(Color.GREEN);
				 g2d.setTransform(AffineTransform.getRotateInstance(Math.toRadians(0), ponto.getX() + 8, ponto.getY() -3));
			     g2d.drawString(ponto.getNome(),  ponto.getX() + 8, ponto.getY() +3);
			     
			}
			g.fillOval(ponto.getX() - 4, ponto.getY() -4 , 8, 8);			
			
		}
		
		g.setColor(Color.DARK_GRAY);
		tempototal2 = tempototal + tempototal2;
		g.drawString("Tempo Total de Viagem: " + tempototal2 +  " min", 20, 20);
		g.drawString("Com o tempo de espera nas estacoes de " + espera + " min", 20, 35);
		
        
		return tempototal;
	}
	
	/**
	 * Metodo que desenha o formato do vertice
	 * @param g Objeto que contem os metodos para fazer o desenho grafo
	 * @param ponto Vertice que serao desenhado
	 */
	public void paintVertice(Graphics g, Vertice ponto){
		g.drawOval(ponto.getX()- 4, ponto.getY() -4, 7, 7);	
	}
	
	/**
	 * Metodo que desenha uma aresta ligando dois vertices do grafo
	 * @param g Objeto que contem os metodos para fazer o desenho grafo
	 * @param ponto1 Vertice de partida
	 * @param ponto2 Vertice de chegada
	 */
	public void paintAresta(Graphics g, Vertice ponto1, Vertice ponto2){
		g.drawLine(ponto1.getX(), ponto1.getY(), ponto2.getX(), ponto2.getY());
	
	}
	
	/**
	 * Metodo que desenha na interface todos os pontos do grafo
	 * @param g Objeto que contem os metodos para fazer o desenho grafico
	 *  
	 */
	public void desenharPontos(Graphics g){
		
		Interador corr = control.interadorVerticesXY();
		Interador corr2 = control.interadorVerticesXY();
		corr2.obterProximo();
		while(corr.temProximo()){			
			Vertice temp = (Vertice) corr.obterProximo();
			while(corr2.temProximo()){
				Vertice tempafrente = (Vertice) corr2.obterProximo();
				if(control.getGrafo().isEdge(temp.getNome(), tempafrente.getNome())){
					paintAresta(g, temp, tempafrente);
				}
				
			}
			corr2 = control.interadorVerticesXY();
			corr2.obterProximo();
			paintVertice(g,temp);
			
		}
	}

	public void setEspera(double tem) {
		this.espera = tem;
	}
	
	
}
