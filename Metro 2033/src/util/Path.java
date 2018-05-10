package util;

/**
 * Classe que representa um caminho entre dois v�rtices
 * @author Brendo Nascimento e Gabriel Azevedo
 *
 */
public class Path implements Comparable<Path>{
	
	private String vertex;
	private String previous;
	private Double time;
	
	/**
	 * M�todo construtor
	 * @param v V�rtice inicial (esta��o de partida)
	 * @param p V�rtice final (esta��o de chegada)
	 * @param t Tempo que leva para ir da esta��o de partida at� a chegada
	 */
	public Path(String v, String p, double t) {
		vertex = v;
		previous = p;
		time = t;
	}
	
	//Getters e setters
	public String getVertex() {
		return vertex;
	}
	
	public void setVertex(String v) {
		vertex = v;
	}
	
	public String getPrevious() {
		return previous;
	}
	
	public void getPrevious(String p) {
		previous = p;
	}
	
	public double getTime() {
		return time;
	}
	
	public void setTime(double t) {
		time = t;
	}
	
	/**
	 * M�todo que compara o tempo entre caminhos
	 */
	public int compareTo(Path p) {				
		return time.compareTo(p.getTime());
	}
	
}