package model;

/**
 * Classe que representa um v�rtice do grafo
 * @author Brendo Nascimento e Gabriel Azevedo
 *
 */
public class Vertice implements Comparable<Vertice>{
	
	private String nome; //Vari�vel que representa o nome da esta��o (v�rtice)
	private int x,y;//Coordenadas do v�rtice no grafo
			
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
	 * M�todo construtor
	 * @param nome Nome da esta��o (v�rtice)
	 */
	public Vertice(String nome){
		this.nome = nome;
	}	
	
	/**
	 * M�todo construtor
	 * @param nome Nome da esta��o (v�rtice)
	 * @param tempo Tempo de espera na esta��o
	 */
	public Vertice(String nome, int tempo) {
		this.nome = nome;
	}
	
	/**
	 * M�todo que retorna o nome da esta��o e suas coordenadas do grafo
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
	 * M�todo que faz a compra��o entre o nome de um v�rtice com outro
	 */
	public boolean equals(Object e){
		if(e instanceof Vertice){
			Vertice outro = (Vertice) e;
			return getNome().equals(outro.getNome());
			
		}
		return false;
	}
	
	/**
	 * M�todo que compara um v�rtice com o outro
	 */
	public int compareTo(Vertice comparado) {		
		return this.nome.compareTo(comparado.getNome());
	}

}
