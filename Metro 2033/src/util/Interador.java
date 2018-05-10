/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

/**
 * M�todo que implementa a interface do iterador
 * @author Brendo Nascimento e Gabriel Azevedo
 * @param <T>
 */
public class Interador {

    private Nodo atual;
    
    /**
     * M�todo construtor
     * @param atual N� atual
     */
    public Interador (Nodo atual){
        this.atual = atual;
    }
    
    /**
     * M�todo que verifica se h� um pr�ximo n�
     * @return Um resposta true caso tenha, e false caso n�o
     */
    public boolean temProximo() {
        return (atual != null);
    }
    
    /**
     * M�todo que retorna o pr�ximo n� da lista
     * @return O pr�ximo n�, ou null se n�o houver
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
