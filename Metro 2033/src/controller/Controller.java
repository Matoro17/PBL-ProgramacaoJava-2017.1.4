package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Stack;

import model.Vertice;

import util.Grafo;
import util.Interador;
import util.Listas;

/**
 * Classe controladora responsável pelas operações de leitura de arquivo e construção do grafo
 * @author Brendo Nascimento e Gabriel Azevedo
 *
 */
public class Controller {
	private static Controller instance;
	private Grafo grafo = new Grafo();		
	private Listas verticesXY = new Listas();//Guarda as coordenadas dos vértices
	
	/**
	 * Método construtor
	 */
	private Controller(){}
	
	/**
	 * Pega uma instancia do Controller
	 * @return
	 */
	public static Controller getInstance(){
		if(instance == null)
			instance = new Controller();
		return instance;
	}
	
	/**
	 * Método que lê as coordenadas presentes no arquivo e as coloca na lista
	 * @param local Variável que faz a leitura dos dados do arquivo
	 * @return Uma resposta true caso a leitura tenha sido feita, e false caso contrário
	 * @throws Exception
	 */
	public boolean lerCoordenadas(String local) throws Exception{
		try{
			File arquivo = new File(local);		
			FileReader leitor = new FileReader(arquivo);
			BufferedReader BufferLeitor = new BufferedReader(leitor);
	       	        
	        while(BufferLeitor.ready()){
	            String linha = BufferLeitor.readLine();
	            
	            if(linha.charAt(0) != '#') {
	            	String[] vetor =  linha.split(";");
	            
	            	if(vetor.length == 4){
	            		if(grafo.isVertex(vetor[0])){
	            			Vertice temp = new Vertice(vetor[0]);
	            			temp.setX(Integer.parseInt(vetor[1])/2);
	            			temp.setY(Integer.parseInt(vetor[2])/2);
	            			verticesXY.inserirInicio(temp);
	            		}
	            		else{
	            			System.out.println("nao existe esse cara: " + vetor[0]);
	            		}

	            	}
	            }
	            
	         }
	        
	        
	        BufferLeitor.close();
	        return true;
	      }catch(IOException ioe){
	    	  throw new Exception("Arquivo nao Existe");
	         
	      }
	}
	
	/**
	 * Método que retorna a lista com as coordenadas do vértice
	 * @return
	 */
	public Listas getVerticesXY(){
		return this.verticesXY;
	}
	
	/**
	 * Método que retorna o grafo com as estações
	 * @return
	 */
	public Grafo getGrafo(){
		return this.grafo;
	}
	
	/**
	 * Método que retorna o iterador da lista de coordenadas
	 * @return
	 */
	public Interador interadorVerticesXY(){
		return verticesXY.iterador();
	}
	
	/**
	 * Método que lê o arquivo com os nomes das estações e o tempo de viagem entre elas
	 * @param local Variável que realiza a leitura dos arquivos
	 * @return Uma resposta true caso a leitura tenha sido bem sucedidade, e false caso contrário
	 * @throws Exception
	 */
	public boolean lerAquivo(String local) throws Exception{		
		try{
			File arquivo = new File(local);		
			FileReader leitor = new FileReader(arquivo);
			BufferedReader BufferLeitor = new BufferedReader(leitor);
	       	        
	        while(BufferLeitor.ready()){
	            String linha = BufferLeitor.readLine();
	            
	            if(linha.charAt(0) != '#') {
	            	String[] vetor =  linha.split(",");
	            
	            	if(vetor.length == 3){
	            		
	            		grafo.addVertex(vetor[0]);
	            		grafo.addVertex(vetor[1]);	            			            
	            		grafo.addEdge(vetor[0], vetor[1], Double.parseDouble(vetor[2]));
	            		

	            	}
	            }
	            
	         }
	        
	        
	        BufferLeitor.close();
	        return true;
	      }catch(IOException ioe){
	    	  throw new Exception("Arquivo nao Existe");
	         
	      }
	}
	
	/**
	 * Método
	 * @return
	 */
	public String[] vetorVertices(){
		String[] vetor = new String[grafo.numVertex()];
		int i = 0;
		for(String s : grafo.getVertex()){			
			vetor[i] = s;
			i++;
		}
		Arrays.sort(vetor);
		return vetor;
	}
	
	/**
	 * Método que exibe todos os elementos do grafo
	 */
	public void mostrarTodoGrafo(){
		for(String s : grafo.getVertex())
			System.out.println(s);
	}
	
	/**
	 * Método que adiciona um caminho entre dois vertices
	 * @param nome1 Nome do vertice inicial (estação de partida)
	 * @param nome2 Nome do vertice final (estação de chegada)
	 * @param temp Tempo da viagem entre as duas estações
	 */
	public void addVertice(String nome1, String nome2, Double temp){
    	grafo.addEdge(grafo.addVertex(nome1), grafo.addVertex(nome2), temp);

	}
	
	/**
	 * Método que exibe as coordenadas dos vertices
	 */
	public void mostrarCoordenadas(){
		Interador corr = verticesXY.iterador();
		while(corr.temProximo()){
			corr.obterProximo().toString();
		}
	}
    
	/**
	 * Método que exibe os nomes de todos os vértices do grafo (nomes das estações)
	 */
	public void mostrarVertices() {
		grafo.getVertex().toString();		
	}
	
	/**
	 * Método que retorna o caminho mais curto entre dois vértices do grafo
	 * @param a Vertice inicial (estação de partida)
	 * @param b Vertice final (estação de chegada)
	 * @return O caminho mais curto entre os vértices passados como parâmetros
	 */
	@SuppressWarnings("rawtypes")
	public Stack caminhominimo(String a, String b){
		Stack pilhota = grafo.dijkstra(a, b);	
		return pilhota;
		
	}
}
