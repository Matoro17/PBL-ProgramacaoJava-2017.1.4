package model;

/**
 * Classe que representa um vértice do grafo
 * @author Brendo Nascimento e Gabriel Azevedo
 *
 */
public class Vertice implements Comparable<Vertice>{
	
	private String nome; //Variável que representa o nome da estação (vértice)
	private int x,y;//Coordenadas do vértice no grafo
			
	//Getters e setters para as coordenadas
	public void setX(int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}
	public int getX(){
		return this.x;
	}
	public int getY(){
		return this.y;
	}
	
	/**
	 * Método construtor
	 * @param nome Nome da estação (vértice)
	 */
	public Vertice(String nome){
		this.nome = nome;
	}	
	
	/**
	 * Método construtor
	 * @param nome Nome da estação (vértice)
	 * @param tempo Tempo de espera na estação
	 */
	public Vertice(String nome, int tempo) {
		this.nome = nome;
	}
	
	/**
	 * Método que retorna o nome da estação e suas coordenadas do grafo
	 */
	@Override
	public String toString() {
		return ("Estacao: " + nome + "\tX - " + x + "\tY - " + y);
	}
	
	//Get e set para o nome
	public String getNome() {
		return nome;
	}
	public void setNome(String rotulo) {
		this.nome = rotulo;
	}
	
	/**
	 * Método que faz a compração entre o nome de um vértice com outro
	 */
	public boolean equals(Object e){
		if(e instanceof Vertice){
			Vertice outro = (Vertice) e;
			return getNome().equals(outro.getNome());
			
		}
		return false;
	}
	
	/**
	 * Método que compara um vértice com o outro
	 */
	public int compareTo(Vertice comparado) {		
		return this.nome.compareTo(comparado.getNome());
	}

}
