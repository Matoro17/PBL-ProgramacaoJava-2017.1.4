/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

/**
 * Método que implementa a interface do iterador
 * @author Brendo Nascimento e Gabriel Azevedo
 * @param <T>
 */
public class Interador {

    private Nodo atual;
    
    /**
     * Método construtor
     * @param atual Nó atual
     */
    public Interador (Nodo atual){
        this.atual = atual;
    }
    
    /**
     * Método que verifica se há um próximo nó
     * @return Um resposta true caso tenha, e false caso não
     */
    public boolean temProximo() {
        return (atual != null);
    }
    
    /**
     * Método que retorna o próximo nó da lista
     * @return O próximo nó, ou null se não houver
     */
    public Object obterProximo() {
        if(temProximo()){            
            Object objeto = atual.getDados();
            atual = atual.getProx();
            return objeto;                                 
        }        
        return null; 
    }   

}
