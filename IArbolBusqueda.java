/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uagrm.ficct.inf310sb.ed2202101.arboles;

import bo.edu.uagrm.ficct.inf310sb.ed2202101.arboles.excepciones.ExcepcionClaveNoExiste;
import java.util.List;

/**
 *
 * @author Geraldinne
 * @param <K>
 * @param <V>
 */
public interface IArbolBusqueda<K extends Comparable<K>,V> {
    void insertar(K claveAInsertar, V valorAInsertar);
            //throws ExcepcionClaveYaExiste; 
                /*ya no ponemos excepcion, trabajaremos 
                como trabaja el Mapa (si pilla clave 
                igual reemplaza valor(clave))*/
    V eliminar(K claveAEliminar) throws ExcepcionClaveNoExiste;
    V buscar(K claveABuscar);
    boolean contiene(K claveABuscar);
    int size();
    int altura();
    int nivel();
    void vaciar();
    boolean esArbolVacio();
    List<K> recorridoPorNiveles();
    List<K> recorridoPreOrden();
    List<K> recorridoInOrden();
    List<K> recorridoPostOrden();
}


