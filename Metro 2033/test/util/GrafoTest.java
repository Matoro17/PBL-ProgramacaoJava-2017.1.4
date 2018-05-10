package util;

import static org.junit.Assert.assertEquals;

import java.util.Stack;

import org.junit.Test;
 
public class GrafoTest {
	
	@Test
	public void test() {
		Grafo grafo = new Grafo();
		String test = "Portuga";
		String test2 = "Irineu";
		String test3 = "Chibata";
		String test4 = "Bilada";
		grafo.addVertex(test);
		grafo.addVertex(test2);
		grafo.addVertex(test3);
		grafo.addVertex(test4);
		
		
		grafo.addEdge(test, test2, 5.0);;
		grafo.addEdge(test2, test3, 8.0);
		grafo.addEdge(test2, test4, 3.0);
		grafo.addEdge(test, test4, 6.0);
		
		
		assertEquals(true, grafo.isVertex(test));
		
	}
	
	@Test
	public void testdeEncontrarVertice(){
		Grafo grafo = new Grafo();
		String test = "Portuga";		
		grafo.addVertex(test);
		
		assertEquals(true, grafo.isVertex(test));
		
		
	}
	
	@Test
	public void testDijkstra() {
		Grafo graph = new Grafo();
		
		//Adiciona os vertices
		graph.addVertex("Portuga");
		graph.addVertex("Irineu");
		graph.addVertex("Chibata");
		graph.addVertex("Portuga");
		graph.addVertex("Bilada");
		graph.addVertex("Tobi");
		
		//Adiciona as arestas
		graph.addEdge("Portuga", "Irineu", 5.0);
		graph.addEdge("Irineu", "Chibata", 8.0);
		graph.addEdge("Irineu", "Bilada", 14.0);
		graph.addEdge("Portuga", "Bilada", 6.0);
		graph.addEdge("Bilada", "Tobi", 6.0);
		graph.addEdge("Tobi", "Irineu", 5.0);
		
		//Salva a rota
		Stack<Path> path;
		
		//Rota 1
		System.out.println("\nDe Irineu para Bilada");
		path = graph.dijkstra("Irineu", "Bilada");
		
		if(path == null)
			System.out.println("Nao Existe");
		else {
			while(!path.isEmpty()) {
				Path rem = path.pop();					
				System.out.println(rem.getPrevious() +" -> "+ rem.getVertex() +" = " + rem.getTime());
			
			}
		}
		
		//Rota 2
		System.out.println("\nDe Portuga para Tobi");
		path = graph.dijkstra("Portuga", "Tobi");
		
		if(path == null)
			System.out.println("Nao Existe");
		else {
			while(!path.isEmpty()) {
				Path rem = path.pop();					
				System.out.println(rem.getPrevious() +" -> "+ rem.getVertex() +" = " + rem.getTime());
			
			}
		}
		
		//Exibe tudo
		System.out.println("\nVertices");
		for(String n : graph.getVertex())
			System.out.println(n);
		
	}

}
