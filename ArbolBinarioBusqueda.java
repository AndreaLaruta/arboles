/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uagrm.ficct.inf310sb.ed2202101.arboles;

import bo.edu.uagrm.ficct.inf310sb.ed2202101.arboles.excepciones.ExcepcionClaveNoExiste;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author Geraldinne
 */
public class ArbolBinarioBusqueda<K extends Comparable<K>, V>
    implements IArbolBusqueda<K, V>{
    
    protected NodoBinario<K,V> raiz;

    public ArbolBinarioBusqueda() {
    }
    
    @Override
    public void insertar(K claveAInsertar, V valorAInsertar) {
        if(valorAInsertar == null){
            throw new RuntimeException("No se permiten Valores Nulos");
        }
        if(this.esArbolVacio()){
            this.raiz = new NodoBinario<>(claveAInsertar, valorAInsertar);
            return;
        }
        
        NodoBinario<K,V> nodoAnterior = NodoBinario.nodoVacio();
        NodoBinario<K,V> nodoActual = this.raiz;
        
        while(!NodoBinario.esNodoVacio(nodoActual)){
            nodoAnterior = nodoActual;
            K claveActual = nodoActual.getClave();
            if (claveAInsertar.compareTo(claveActual) < 0){
                nodoActual = nodoActual.getHijoIzquierdo();
            }else if (claveAInsertar.compareTo(claveActual) > 0) {
                nodoActual = nodoActual.getHijoDerecho();
            }else{//cuando lo encuentre
                nodoActual.setValor(valorAInsertar);
                return;
            }
        }
        //si llego a este punto, encontre donde insertar
        //la clave y el valor
        NodoBinario<K,V> nuevoNodo = new NodoBinario<>(claveAInsertar, valorAInsertar);
        K claveAnterior = nodoAnterior.getClave();
        if(claveAInsertar.compareTo(claveAnterior) > 0){
            nodoAnterior.setHijoDerecho(nuevoNodo);
        }else{
            nodoAnterior.setHijoIzquierdo(nuevoNodo);
        }
    }

    @Override
    public V eliminar(K claveAEliminar) throws ExcepcionClaveNoExiste {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public V buscar(K claveABuscar) {
        if(this.esArbolVacio()){
           return null; //si se trata del dato podemos usar NULL
        }
        //viene aca si no es vacio
        NodoBinario<K,V> nodoActual = this.raiz;
        
        while(!NodoBinario.esNodoVacio(nodoActual)){
            K claveActual = nodoActual.getClave();
            if(claveABuscar.compareTo(claveActual) < 0){
                nodoActual = nodoActual.getHijoIzquierdo();
            }else if(claveABuscar.compareTo(claveActual) > 0){
                nodoActual = nodoActual.getHijoDerecho();
            }else{
                return nodoActual.getValor();
            }
        }
        //si llega a este punto, K clave a buscar
        //no esta en el Arbol
        //Entonces debe retornar null
        return null;
    }

    @Override
    public boolean contiene(K claveABuscar) {
        return this.buscar(claveABuscar) != null;
    }

    @Override
    public int size() {
        if(this.esArbolVacio()){
            return 0;
        }
        int contadorDeNodos = 0;
        Queue<NodoBinario<K,V>> colaDeNodos = new LinkedList<>();
        NodoBinario<K,V> nodoActual = this.raiz;
        colaDeNodos.offer(nodoActual);
        return contadorDeNodos;
    }

    public int sizeR(){
        return size(this.raiz);
    }

    protected int size(NodoBinario<K,V> nodoActual){
        if (NodoBinario.esNodoVacio(nodoActual)){
            return 0;
        }
        int tamHijoIzquierdo = size(nodoActual.getHijoIzquierdo());
        int tamHijoDerecho = size(nodoActual.getHijoDerecho());
        return tamHijoIzquierdo + 1 + tamHijoDerecho;
    }

    public int sizeJ(){
        if(esArbolVacio()){
            return 0;
        }
        List<K> recorridoPostOrden = new LinkedList<>();
        int contadorDeNodos = 0;
        NodoBinario<K,V> nodoActual = this.raiz;
        Stack<NodoBinario<K,V>> pilaDeNodos = new Stack<>();

        rellenarPila(nodoActual, pilaDeNodos);

        while(!pilaDeNodos.isEmpty()){
            NodoBinario<K,V> nodoAProcesar =  pilaDeNodos.peek();//si tiene hijo derecho y si no se encuentra en recorrido
            if((!nodoAProcesar.esVacioHijoDerecho()) && (!existeEnElRecorrido(nodoAProcesar.getHijoDerecho(), recorridoPostOrden))){
                rellenarPila(nodoAProcesar.getHijoDerecho(), pilaDeNodos);
            }else{ //no tiene derecho o ya se encuentra en el recorrido
                nodoAProcesar = pilaDeNodos.pop();
                recorridoPostOrden.add(nodoAProcesar.getClave());
                contadorDeNodos = contadorDeNodos + 1;
            }
        }
        //System.out.println(recorridoPostOrden.toString());
        return contadorDeNodos;
    }

    public boolean existeEnElRecorrido(NodoBinario<K,V> nodoAProcesar, List<K> recorrido){
        if(recorrido.isEmpty()){
            return false;
        }
        for(int i = 0; i < recorrido.size(); i++){
            //if(recorrido.get(i) == nodoAProcesar.getClave()){
            if(nodoAProcesar.getClave().compareTo(recorrido.get(i)) == 0){// 5.compareTo(2)  =>    5-2 = 3  ||
                                                                            // > 0(si es mayor); < 0 ; ==0
                return true;
            }
        }
        return false;
    }


    @Override
    public int altura() {
        return altura(this.raiz);
    }

    protected int altura(NodoBinario<K,V> nodoActual){
        if(this.esArbolVacio()){
            return 0;
        }
        Queue <NodoBinario<K,V>> colaDeNodos = new LinkedList<>();
        colaDeNodos.offer(this.raiz);
        int alturaDelArbol = 0;
        while(!colaDeNodos.isEmpty()){
            //int nodosDelNivel = colaDeNodos.poll();

        }
        return 0;
    }

    @Override
    public int nivel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void vaciar() {
        //this.raiz = null; //se realizo un metodo en NodoBinario
        this.raiz = NodoBinario.nodoVacio();
    }

    @Override
    public boolean esArbolVacio() {
        return NodoBinario.esNodoVacio(this.raiz);
    }

    //public List<K,V>
    
    @Override
    public List<K> recorridoPorNiveles() {
        List<K> recorrido = new LinkedList<>();
        if(this.esArbolVacio()){
            return recorrido;
        }
        
        Queue<NodoBinario<K,V>> colaDeNodos = new LinkedList<>();
        colaDeNodos.offer(this.raiz);
        while(!colaDeNodos.isEmpty()){
            NodoBinario<K,V> nodoActual = colaDeNodos.poll();
            recorrido.add(nodoActual.getClave());
            if(!nodoActual.esVacioHijoIzquierdo()){
                colaDeNodos.offer(nodoActual.getHijoIzquierdo());
            }
            if(!nodoActual.esVacioHijoDerecho()){
                colaDeNodos.offer(nodoActual.getHijoDerecho());
            }
        }
        return recorrido;
    }

    @Override
    public List<K> recorridoPreOrden() {
        List<K> recorrido = new LinkedList<>();
        if(this.esArbolVacio()){
            return recorrido;
        }
        
        Stack<NodoBinario<K,V>> pilaDeNodos = new Stack<>();
        pilaDeNodos.push(this.raiz);
        while(!pilaDeNodos.isEmpty()){
            NodoBinario<K,V> nodoActual = pilaDeNodos.pop();
            recorrido.add(nodoActual.getClave());
            if(!nodoActual.esVacioHijoDerecho()){
                pilaDeNodos.push(nodoActual.getHijoDerecho());
            }
            if(!nodoActual.esVacioHijoIzquierdo()){
                pilaDeNodos.push(nodoActual.getHijoIzquierdo());
            }
        }
        return recorrido;
    }

    @Override
    public List<K> recorridoInOrden() {
        List<K> recorrido = new LinkedList<>();
        if(this.esArbolVacio()){
            return recorrido;
        }
        Stack <NodoBinario<K,V>> pilaDeNodos = new Stack<>();
        rellenarPila(this.raiz, pilaDeNodos);
        while(!pilaDeNodos.isEmpty()){
            NodoBinario<K,V> nodo = pilaDeNodos.pop();
            recorrido.add(nodo.getClave());
            if(!nodo.esVacioHijoDerecho()){
                rellenarPila(nodo.getHijoDerecho(), pilaDeNodos);
            }
        }
        return recorrido;
    }

    private void rellenarPila(NodoBinario<K,V> nodoAProcesar, Stack<NodoBinario<K,V>> pila){
        while(!NodoBinario.esNodoVacio(nodoAProcesar)){
            pila.push(nodoAProcesar);
            nodoAProcesar = nodoAProcesar.getHijoIzquierdo();
        }
    }

    @Override
    public List<K> recorridoPostOrden() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<K> recorridoEnPreOrdenR(){
        List<K> recorrido = new LinkedList<>();
        recorridoEnPreOrdenR(this.raiz, recorrido);
        return  recorrido;
    }

    private void recorridoEnPreOrdenR(NodoBinario<K,V> nodoActual, List<K> recorrido){
        if(NodoBinario.esNodoVacio(nodoActual)){
            return;
        }
        recorrido.add(nodoActual.getClave());
        recorridoEnPreOrdenR(nodoActual.getHijoIzquierdo(), recorrido);
        recorridoEnPreOrdenR(nodoActual.getHijoDerecho(), recorrido);
    }

    public List<K> recorridoEnInOrdenR(){
        List<K> recorrido = new LinkedList<>();
        recorridoEnPreOrdenR(this.raiz, recorrido);
        return  recorrido;
    }

    private void recorridoEnInOrdenR(NodoBinario<K,V> nodoActual, List<K> recorrido){
        if(NodoBinario.esNodoVacio(nodoActual)){
            return;
        }
        recorridoEnPreOrdenR(nodoActual.getHijoIzquierdo(), recorrido);
        recorrido.add(nodoActual.getClave());
        recorridoEnPreOrdenR(nodoActual.getHijoDerecho(), recorrido);
    }

    public List<K> recorridoEnPostOrdenR(){
        List<K> recorrido = new LinkedList<>();
        recorridoEnPreOrdenR(this.raiz, recorrido);
        return  recorrido;
    }

    private void recorridoEnPostOrdenR(NodoBinario<K,V> nodoActual, List<K> recorrido){
        if(NodoBinario.esNodoVacio(nodoActual)){
            return;
        }
        recorridoEnPreOrdenR(nodoActual.getHijoIzquierdo(), recorrido);
        recorridoEnPreOrdenR(nodoActual.getHijoDerecho(), recorrido);
        recorrido.add(nodoActual.getClave());
    }
    
    public int cantidadDeHojas(){
        return cantidadDeHojas(this.raiz, 0);
    }
    private  int cantidadDeHojas(NodoBinario<K,V > nodo, int cantidad){
        int c = cantidad;
        if(NodoBinario.esNodoVacio(nodo)){
        return c;
        }
        if((nodo.esVacioHijoIzquierdo())&&((nodo.esVacioHijoDerecho()))){
            return c = c + 1;
        }
        if(!nodo.esVacioHijoIzquierdo()){
            c= cantidadDeHojas(nodo.getHijoIzquierdo(),c);
        }
            if(!nodo.esVacioHijoDerecho()){
            c= cantidadDeHojas(nodo.getHijoDerecho(),c);
        }
            return c;
    }
    
    
    @Override
	public String toString() {
		return generarCadenaDeArbol(raiz, "", true);
	}

	private String generarCadenaDeArbol(NodoBinario<K, V> nodoActual, String prefijo, boolean ponerCodo) {
		StringBuilder cadena = new StringBuilder();
		cadena.append(prefijo);
		if(prefijo.length() == 0) {
			cadena.append(ponerCodo ? "└--(R)":"├--(R)" );
		}else {
			cadena.append(ponerCodo ? "└--(D)":"├--(I)" );
		}
		
		if(NodoBinario.esNodoVacio(nodoActual)) {
			cadena.append("╣\n");
			return cadena.toString();
		}
		
		cadena.append(nodoActual.getClave().toString());
		cadena.append("\n");
		
		NodoBinario<K, V> nodoIzquierdo = nodoActual.getHijoIzquierdo();
		String prefijoAux = prefijo + (ponerCodo ? "   " : "|   " );
		cadena.append(generarCadenaDeArbol(nodoIzquierdo, prefijoAux, false));
		
		NodoBinario<K, V> nodoDerecho = nodoActual.getHijoDerecho();
		cadena.append(generarCadenaDeArbol(nodoDerecho, prefijoAux, true));
		
		return cadena.toString();
	}
}
