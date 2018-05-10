/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import model.Vertice;

/**
 * Classe que representa um nó da lista encadeada
 * @author Brendo Nascimento e Azevedo
 */
public class Nodo {
    private Object dados;
    private Nodo prox;
    
    /**
     * Método construtor vazio
     */
    public Nodo(){
    	
    }
    
    /**
     * Método construtor
     * @param m Vértice do grafo
     */
    public Nodo(Vertice m){
    	this.dados = m;
    }
    
    //Getters e setters
    public Object getDados() {
        return dados;
    }

    public Nodo getProx() {
        return prox;
    }

    public void setDados(Object dados) {
        this.dados = dados;
    }

    public void setProx(Nodo prox) {
        this.prox = prox;
    }
    
}