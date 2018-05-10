package controller;

import static org.junit.Assert.*;

import org.junit.Test;

import view.Desenho;

public class ControllerTest {

		
	@Test
	public void testArquivoOriginal() throws Exception{
		Controller control = Controller.getInstance();
		Boolean test =  control.lerAquivo("MapaMetro.txt");
		control.mostrarTodoGrafo();
		
		assertEquals(true,test);
	}
	
	@Test
	public void testArquivoparaGrafo() throws Exception {
		Controller control = Controller.getInstance();
		Boolean test =  control.lerAquivo("arquivo.txt");
		control.mostrarTodoGrafo();
		System.out.println("\n\n");
		control.mostrarVertices();
		assertEquals(true, test);
	}
	
	@Test
	public void testdoisArquivos() throws Exception{
		Controller control = Controller.getInstance();
		control.lerAquivo("arquivo.txt");
		boolean test2 = control.lerCoordenadas("Coordenadas.txt");
		control.mostrarCoordenadas();
		
		assertEquals(true, test2);
		
	}
	
	@Test
	public void testDesenho() throws Exception{
		Controller control = Controller.getInstance();
		control.lerAquivo("arquivo.txt");
		boolean test2 = control.lerCoordenadas("Coordenadas.txt");
		
		new Desenho(control, 5);
		
		assertEquals(true, test2);
	}


}
