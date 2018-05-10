package util;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * Classe que representa o grafo
 * @author Brendo Nascimento e Gabriel Azevedo
 *
 */
public class Grafo {
	
	private HashMap<String, HashMap<String, Double>> graph;
	
	/**
	 * Construtor
	 */
	public Grafo(){
		graph = new HashMap<>();
	}
	
	
	/**
	 * M�todo que verifica se � um vertice
	 * @param v Nome do v�rtice
	 * @return Uma resposta true caso o nome seja de um v�rtice, e false caso n�o
	 */
	public boolean isVertex(String v) {
		return graph.containsKey(v);
	}	
		
	/**
	 * M�todo que adiciona um vertice se ele nao existir
	 * @param v Nome do v�rtice
	 * @return O nome do v�rtice
	 */
	public String addVertex(String v) {
		if(!isVertex(v)){
			graph.put(v, new HashMap<String, Double>());
		    return v;
		}
		return null;
	}	
			
	/**
	 * M�todo que emove um vertice e suas arestas se ele existir
	 * @param v Nome do v�tice
	 */
	public void removeVertex(String v) {
		if(isVertex(v)) {
			for(String a : graph.get(v).keySet())
			graph.get(a).remove(v);	
			graph.remove(v);
		}
	}
		
	/**
	 * M�todo que retorna o numero de vertices
	 * @return O tamanho do grafo (n�mero de v�rtices)
	 */
	public int numVertex() {
		return graph.size();
	}
		
	/**
	 * M�todo que retorna o iterator dos vertices
	 * @return O itertador com todos os nomes das esta��es (v�tices) do grafo
	 */
	public Iterable<String> getVertex() {
			return graph.keySet();
	}
		
	/**
	 * M�todo que verifica se � uma aresta
	 * @param v Nome do v�rtice de in�cio (esta��o de partida)
	 * @param u Nome do v�rtice final (esta��o de destino)
	 * @return Uma resposta true casoseja uma aresta, e false, caso n�o
	 */
	public boolean isEdge(String v, String u) {
		return graph.containsKey(v) && graph.get(v).containsKey(u);
	}
		
	/**
	 * M�todo que adiciona uma aresta se ela nao existir
	 * @param v Nome do v�rtice de in�cio (esta��o de partida)
	 * @param u Nome do v�rtice final (esta��o de destino)
	 * @param t Tempo que leva de uma esta��o at� outra
	 */
	public void addEdge(String v, String u, Double t) {
		if(!isEdge(v, u) && t > 0) {			
			graph.get(v).put(u, t);
			graph.get(u).put(v, t);
		}
	}	
			
	/**
	 * M�todo que retorna uma aresta se ela existir
	 * @param v Nome do v�rtice de in�cio (esta��o de partida)
	 * @param u Nome do v�rtice final (esta��o de destino)
	 * @return 
	 */
	public double getEdge(String v, String u) {
		return isEdge(v, u) ? graph.get(u).get(v) : -1;
	}
		
	/**
	 * M�todo que remove uma aresta se ela existir
	 * @param u Nome do v�rtice de in�cio (esta��o de partida)
	 * @param v Nome do v�rtice final (esta��o de destino)
	 */
	public void removeEdge(String u, String v) {
		if(isEdge(v, u)) {
			graph.get(v).remove(u);
			graph.get(u).remove(v);
		}
	}
		
	/**
	 * M�todo que retorna o numero de arestas ajacentes do vertice, ou -1 se nao existir
	 * @param v Nome da esta��o (v�rtice)
	 * @return O n�mero de arestas que est�o ligadas ao v�tices, ou -1 caso n�o tenha
	 */
	public int numEdge(String v) {
		return isVertex(v) ? graph.get(v).size() : -1;
	}
		
	/**
	 * M�todo que retorna o iterator dos vertices adjacentes, ou null se sao exitir
	 * @param v Nome do v�tice
	 * @return O iterador com todos os v�tices adjacentes do v�rtice atual
	 */
	public Iterable<String> getAdjacent(String v) {
		return isVertex(v) ? graph.get(v).keySet() : null;
	}
		
	/**
	 * Algoritmo de Dijikstra: Define os caminhos de um v�rtice at� o outro, inclusive o m�nimo
	 * @param v Nome do v�rtice de in�cio (esta��o de partida)
	 * @param u Nome do v�rtice final (esta��o de destino)
	 * @return Uma HashMap com os caminho m�nimo
	 */
	public Stack<Path> dijkstra(String v, String u) {
			//Verifica se os vertice existem
			if(isVertex(v) && isVertex(u)) {
				//Armazena os visitados e suas informacoes
				HashMap<String, Path> visited = new HashMap<>();
				
				//Armazena os que faltam visitar
				PriorityQueue<Path> next = new PriorityQueue<>();
				next.add(new Path(v, null, 0));
				
				//Verificar se tem proximo e se o destino nao foi atingido
				while(!next.isEmpty() && !visited.containsKey(u)) {
					//Pega as informacoes e o nome do proximo
					Path info = next.remove();
					String rem = info.getVertex();
					
					//Verifica se ele nao foi visitado
					if(!visited.containsKey(rem)) {
						visited.put(rem, info);
						
						//Verifica os adjacentes a ele					
						for(String cur : getAdjacent(rem)) {												
							//Atualiza a distancia dos adjacentes nao visitados
							if(!visited.containsKey(cur)) {
								double time = getEdge(rem, cur);
								time += info.getTime();
								next.add(new Path(cur, rem, time));
							}
						}
					}
				}
							
				//Se o destino foi atingido retorna a pila de path, se nao null
				return visited.containsKey(u) ? getPath(visited, v, u) : null;
			}			
			
			return null;
	}
		
	/**
	 * M�todo que converte os v�tices visitados em uma pilha de paths (caminhos)
	 * @param visited HashMap com os v�tices visitados no algoritmo de caminho m�nimo
	 * @param v Nome do v�rtice de in�cio (esta��o de partida)
	 * @param u Nome do v�rtice final (esta��o de destino)
	 * @return Uma estrutura de pilha com os v�tices visitados
	 */
	private Stack<Path> getPath(HashMap<String, Path> visited, String v, String u) {
			Stack<Path> path = new Stack<>();
					
			while(v != u) {		
				path.push(visited.get(u));
				u = visited.get(u).getPrevious();			
			}
			
			path.push(visited.get(v));				
			return path;
	}	
	
}