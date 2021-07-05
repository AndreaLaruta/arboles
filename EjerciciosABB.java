package bo.edu.uagrm.ficct.inf310sb.ed2202101.arboles;

import java.util.List;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class EjerciciosABB<K extends Comparable<K>, V> extends ArbolBinarioBusqueda<K, V> {

    public int cantidadDeNodosCompletosEnElArbol() {
        if (this.esArbolVacio()) {
            return 0;
        }

        int cantidad = 0;
        Queue<NodoBinario<K, V>> colaDeNodos = new LinkedList<>();
        colaDeNodos.offer(this.raiz);

        while (!colaDeNodos.isEmpty()) {
            NodoBinario<K, V> nodoActual = colaDeNodos.poll();

            if (!nodoActual.esVacioHijoIzquierdo() && !nodoActual.esVacioHijoDerecho()) {
                cantidad = cantidad + 1;
            }
            if (!nodoActual.esVacioHijoIzquierdo()) {
                colaDeNodos.offer(nodoActual.getHijoIzquierdo());
            }
            if (!nodoActual.esVacioHijoDerecho()) {
                colaDeNodos.offer(nodoActual.getHijoDerecho());
            }
        }
        return cantidad;
    }

}
