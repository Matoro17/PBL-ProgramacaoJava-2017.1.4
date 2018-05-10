/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import model.Vertice;

/**
 * Método que representa uma lista encadeada
 * @author Brendo Nascimento e Gabriel Azevedo
 */
public class Listas implements ILista{
	
    public Nodo first;
    
    /**
     * Método constutor
     */
    public Listas(){
        first = null;
    }    
    
    /**
     * Método que pega o primeiro nó da lista
     * @return Primeiro nó da lista
     */
    public Nodo getFirst() {
        return first;
    }

    /**
     * Método que verifica se a lista está vazia
     * @return Uma resposta true caso a lista esteja vazia, e false, caso não
     */
    public boolean estaVazia(){
        return (first == null);
    }

    /**
     * Método que retorna o tamanho da lista
     * @return A quantidade de itens da lista
     */
    public int obterTamanho() {
       int contador = 0;
       Nodo temp = first;
       while(temp != null){
           contador++;
           temp = temp.getProx();
       }
       
       return contador;
    }

    /**
     * Método que insere um nó no início da lista
     */
    public void inserirInicio(Object o) {
        Nodo novono = new Nodo();
        novono.setDados(o);
        novono.setProx(first);
        first = novono;
    }

    /**
     * Método que insere um nó no final da lista
     */
    public void inserirFinal(Object o) {
        Nodo novono = first;
        
        if (novono == null){
            inserirInicio(o);
            return;
        }
        while(novono.getProx() != null){
            if (novono.getProx().getProx() == null){
                break;
            }
            novono = novono.getProx();            
        }
        novono.getProx().setProx(new Nodo());
        novono.getProx().setDados(o);
    }

    /**
     * Método que remove um nó no incício da lista
     */
    public Object removerInicio() {
        Object temp = first.getDados();
        first = first.getProx();
        return temp;
    }
    
    /**
     * Método que remove um nó em qualquer posição da lista
     * @param d O objeto que deverá ser removido
     */
    public void removerLocal(Object d){
        Nodo temporario = first;
        if(temporario == null){
            return;
        }
        if(d == first.getDados()){
            this.removerInicio();
            return;
        }
        while(temporario.getProx() != null){
            if (temporario.getProx().getDados() == d){
                temporario.setProx(temporario.getProx().getProx());
                return;
            }
            temporario = temporario.getProx();
        }
    }

    /**
     * Remove o nó ao final da lista
     */
    public Object removerFinal() {
        Nodo novono = first;       
        while(novono.getProx() != null){
            if (novono.getProx().getProx() == null){
                break;
            }
            novono = novono.getProx();            
        }
        novono.setProx(null);
        return novono.getDados();
    }

    
    /**
     * Método que encontra um determinado nó
     */
    public Object recuperar(int index) {
        Nodo novono = first;
        int contador = 0;
                
        while(novono.getProx() != null){
            contador++;            
            if (index == contador){
                break;
            }            
            novono = novono.getProx();            
        }
        return novono.getDados();        
    
    }

    /**
     * Método iterador
     * @return O iterador com os nós da lista
     */
    public Interador iterador() {
    	return  new Interador(first);
	}
    
    private Object[] vetor;
    
    /**
     * Método que passar os itens da lista para um vetor
     * @return O vetor com os dados da lista
     */
	public Object[] mandarParaVetor(){
        this.vetor = new Object[this.obterTamanho()];
        Interador dalista = (Interador) this.iterador();
        int x = 0;
        while(dalista.temProximo()){
            Object temp =  dalista.obterProximo();
            this.vetor[x] = temp;
            x++;
        }
        return vetor;
    }
    
	/**
	 * Método que passa os dados do vetor para a lista encadeada
	 * @param vetor Vetor com os itens que serão passados para a lista
	 */
    public void vetorParaLista(Object[] vetor){        
        while(!this.estaVazia()){
            this.removerInicio();
        }
        int x=0;
        while(x!=vetor.length){
            Object temp = vetor[x];
            this.inserirInicio(temp);
            x++;
            
        }
    }   
    
    /**
     * Metodo para encontrar um vertice na lista, ou confirmar se um determinado vertice já existe
     * @param quero Vertice que será buscado
     * @return Objeto encontrado, nulo caso nao ache
     */
	public Object encontrarVertice(Vertice quero){
    	if(first == null)
    		return null;
		Interador correr = this.iterador();
		while(correr.temProximo()){
			Vertice temp = (Vertice) correr.obterProximo();
			if(temp.equals(quero)){
				return temp;
			}
		}
    	
    	return null;
    	
    }
    
    /**
     * Método que exibe os itens da lista
     */
	public void mostrarLista(){
    	Interador temporario = (Interador) this.iterador();
        if(temporario == null){
            return;
        }        
        while(temporario.temProximo()){
            System.out.println(temporario.obterProximo());
            
        }
    }    
    
}