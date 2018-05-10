/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import model.Vertice;

/**
 * M�todo que representa uma lista encadeada
 * @author Brendo Nascimento e Gabriel Azevedo
 */
public class Listas implements ILista{
	
    public Nodo first;
    
    /**
     * M�todo constutor
     */
    public Listas(){
        first = null;
    }    
    
    /**
     * M�todo que pega o primeiro n� da lista
     * @return Primeiro n� da lista
     */
    public Nodo getFirst() {
        return first;
    }

    /**
     * M�todo que verifica se a lista est� vazia
     * @return Uma resposta true caso a lista esteja vazia, e false, caso n�o
     */
    public boolean estaVazia(){
        return (first == null);
    }

    /**
     * M�todo que retorna o tamanho da lista
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
     * M�todo que insere um n� no in�cio da lista
     */
    public void inserirInicio(Object o) {
        Nodo novono = new Nodo();
        novono.setDados(o);
        novono.setProx(first);
        first = novono;
    }

    /**
     * M�todo que insere um n� no final da lista
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
     * M�todo que remove um n� no inc�cio da lista
     */
    public Object removerInicio() {
        Object temp = first.getDados();
        first = first.getProx();
        return temp;
    }
    
    /**
     * M�todo que remove um n� em qualquer posi��o da lista
     * @param d O objeto que dever� ser removido
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
     * Remove o n� ao final da lista
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
     * M�todo que encontra um determinado n�
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
     * M�todo iterador
     * @return O iterador com os n�s da lista
     */
    public Interador iterador() {
    	return  new Interador(first);
	}
    
    private Object[] vetor;
    
    /**
     * M�todo que passar os itens da lista para um vetor
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
	 * M�todo que passa os dados do vetor para a lista encadeada
	 * @param vetor Vetor com os itens que ser�o passados para a lista
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
     * Metodo para encontrar um vertice na lista, ou confirmar se um determinado vertice j� existe
     * @param quero Vertice que ser� buscado
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
     * M�todo que exibe os itens da lista
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