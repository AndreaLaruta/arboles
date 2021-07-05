/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uagrm.ficct.inf310sb.ed2202101.ui;

import bo.edu.uagrm.ficct.inf310sb.ed2202101.arboles.ArbolBinarioBusqueda;
import bo.edu.uagrm.ficct.inf310sb.ed2202101.arboles.Ejercicio1;
import bo.edu.uagrm.ficct.inf310sb.ed2202101.arboles.IArbolBusqueda;
import bo.edu.uagrm.ficct.inf310sb.ed2202101.arboles.EjerciciosABB;
import bo.edu.uagrm.ficct.inf310sb.ed2202101.excepciones.ExcepcionClaveNoExiste;


/**
 *
 * @author Geraldinne
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ExcepcionClaveNoExiste {
      /*  // TODO code application logic here
        ArbolBinarioBusqueda<Integer, String> arbol = new ArbolBinarioBusqueda<>();

        arbol.insertar(50, "bis");
        arbol.insertar(28, "ko");
        arbol.insertar(70, "cho");
        arbol.insertar(25, "cho");
        arbol.insertar(10, "co");
        arbol.insertar(50, "la");
        arbol.insertar(27, "te");
        
        System.out.println(arbol.toString());
        System.out.println(arbol.cantidadDeHojas());
        System.out.println("Hola");
       */


        /*ArbolBinarioBusqueda<Integer, String> arbol1 = new Ejercicio1<>();
        arbol1.insertar(30, "a");
        arbol1.insertar(25, "b");
        arbol1.insertar(76,"c");
        System.out.println(arbol1);
        System.out.println(arbol1.size());
        System.out.println(arbol1.sizePN());
        System.out.println(arbol1.recorridoInOrden());
        */
/*
        ArbolBinarioBusqueda<Integer, String> arbol = new ArbolBinarioBusqueda<>();
        arbol.insertar(7, "a");
        arbol.insertar(5, "b");
        arbol.insertar(9, "c");
        arbol.insertar(2, "d");
        arbol.insertar(6, "e");
        arbol.insertar(8, "f");
        arbol.insertar(11, "g");
        arbol.insertar(1, "a");
        arbol.insertar(3, "a");
        arbol.insertar(10, "a");
        arbol.insertar(12, "a");

        System.out.println(arbol);
        System.out.println(arbol.sizeJ());
  */
        /*ArbolBinarioBusqueda<Integer, String> arbol3 = new ArbolBinarioBusqueda<>();
        arbol3.insertar(99, "a");
        arbol3.insertar(42, "b");
        arbol3.insertar(32, "b");
        arbol3.insertar(56, "b");
        arbol3.insertar(20, "b");
        arbol3.insertar(40, "b");
        arbol3.insertar(61, "b");
        arbol3.insertar(15, "b");
        arbol3.insertar(59, "b");
        arbol3.insertar(65, "b");
        arbol3.insertar(18, "b");
        arbol3.insertar(16, "b");
        arbol3.insertar(190, "b");
        System.out.println(arbol3.sizePN());
        System.out.println(arbol3.sizeR());
        System.out.println(arbol3.cantidadDeNodosCompletosEnElArbol());
*/

        ArbolBinarioBusqueda<String, Integer> arbolABB = new ArbolBinarioBusqueda<>();
        arbolABB = new EjerciciosABB<>();
        arbolABB.insertar("HM", 1);
        arbolABB.insertar("EK", 2);
        arbolABB.insertar("TA", 3);
        arbolABB.insertar("CA", 4);
        arbolABB.insertar("FE", 5);
        arbolABB.insertar("MK", 6);
        arbolABB.insertar("VB", 7);
        arbolABB.insertar("CF", 8);
        arbolABB.insertar("LP", 9);
        arbolABB.insertar("UM", 10);
        arbolABB.insertar("VK", 11);
        arbolABB.insertar("TH", 12);
        arbolABB.insertar("TZ", 13);

        System.out.println(arbolABB);
        System.out.println("Ejercicio 3");
        System.out.println("cantidad de Nodos Completos en el arbol: " + arbolABB.cantidadDeNodosCompletosEnElArbol());
        System.out.println("Ejercicio 4");
        System.out.println("cantidad de Hijos De NodosCompletos: " + arbolABB.cantidadHijosDeNodosCompletos());




    }
    
}
