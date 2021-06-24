package bo.edu.uagrm.ficct.inf310sb.ed2202101.arboles;

import java.util.List;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Ejercicio1 <K extends Comparable<K>, V> extends ArbolBinarioBusqueda<K,V>{

    @Override
    public List<K> recorridoInOrden(){
        List<K> recorrido = new LinkedList<>();
        if (this.esArbolVacio()){
            return recorrido;
        }
        Stack<NodoBinario<K,V>> pilaDeNodos = new Stack();
        NodoBinario<K,V> nodoActual = this.raiz;
        llenarPilaPorIzquierda(nodoActual, pilaDeNodos);
        while(!pilaDeNodos.isEmpty()){
            NodoBinario<K,V> nodoObtenidoDeLaPila = pilaDeNodos.pop();
            recorrido.add(nodoObtenidoDeLaPila.getClave());
            if(!nodoObtenidoDeLaPila.esVacioHijoDerecho()){
                llenarPilaPorIzquierda(nodoObtenidoDeLaPila.getHijoDerecho(), pilaDeNodos);
            }
        }
        return recorrido;
    }

    private void llenarPilaPorIzquierda(NodoBinario<K,V> nodoParaProcesar, Stack<NodoBinario<K,V>> pila){
        while(!NodoBinario.esNodoVacio(nodoParaProcesar)){
            pila.push(nodoParaProcesar);
            nodoParaProcesar = nodoParaProcesar.getHijoIzquierdo();
        }
    }


    @Override
    public int size(){
        if(this.esArbolVacio()){
            return 0;
        }

        int contador = 0;
        Queue<NodoBinario<K,V>> colaDeNodos = new LinkedList<>();
        colaDeNodos.offer(this.raiz);
        while(!colaDeNodos.isEmpty()){
            NodoBinario<K,V> nodoDeLaCola = colaDeNodos.poll();
            if(!nodoDeLaCola.esVacioHijoIzquierdo()){
                colaDeNodos.offer(nodoDeLaCola.getHijoIzquierdo());
            }
            if(!nodoDeLaCola.esVacioHijoDerecho()){
                colaDeNodos.offer(nodoDeLaCola.getHijoDerecho());
            }
            contador = contador + 1;
        }
        return contador;
    }

}