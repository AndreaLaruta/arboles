package bo.edu.uagrm.ficct.inf310sb.ed2202101.arboles;

//si es menor que -1 o mayor que 1 no sirve
//debe ser "-1", "0" ó "1"
public class AVL <K extends Comparable<K>, V> extends ArbolBinarioBusqueda<K,V> {

    private static final byte DIFERENCIA_PERMITIDA = 1;

    @Override
    public void insertar(K claveAInsertar, V valorAInsertar) {
        if(valorAInsertar == null){
            throw new RuntimeException("No se permite insertar valores nulos");
        }
        this.raiz = insertar(this.raiz, claveAInsertar, valorAInsertar);
    }

    private NodoBinario<K,V> insertar(NodoBinario<K,V> nodoActual, K claveAInsertar, V valorAinsertar){
        if(NodoBinario.esNodoVacio(nodoActual)){
            NodoBinario<K,V> nuevoNodo = new NodoBinario<>(claveAInsertar, valorAinsertar);
            return nuevoNodo;
        }

        K claveActual = nodoActual.getClave();
        if(claveAInsertar.compareTo(claveActual) < 0){
            NodoBinario<K,V> nuevoSupuestoHijo = insertar(nodoActual.getHijoIzquierdo(),
                    claveAInsertar, valorAinsertar);
            nodoActual.setHijoIzquierdo(nuevoSupuestoHijo);
            return balancear(nodoActual);
        }

        if(claveAInsertar.compareTo(claveActual) > 0){
            NodoBinario<K,V> nuevoSupuestoHijo = insertar(nodoActual.getHijoDerecho(),
                    claveAInsertar, valorAinsertar);
            nodoActual.setHijoDerecho(nuevoSupuestoHijo);
            return balancear(nodoActual);
        }

        //si se llega a este punto son iguales...
        //Quiere decir que encontre en el arbol la clave que queria
        //insertar, Entonces actualiza el valor
        nodoActual.setValor(valorAinsertar);
        return nodoActual;

    }

    private NodoBinario<K,V> balancear(NodoBinario<K,V> nodoActual){
        int alturaPorIzquierda = altura(nodoActual.getHijoIzquierdo());
        int alturaPorDerecha = altura(nodoActual.getHijoDerecho());
        int diferenciaDeAltura = alturaPorIzquierda - alturaPorDerecha;

        if(diferenciaDeAltura > DIFERENCIA_PERMITIDA){
            NodoBinario<K,V> hijoIzquierdoDelActual = nodoActual.getHijoIzquierdo();
            alturaPorIzquierda = altura(hijoIzquierdoDelActual.getHijoIzquierdo());
            alturaPorDerecha = altura(hijoIzquierdoDelActual.getHijoDerecho());
            if(alturaPorDerecha > alturaPorIzquierda){
                return rotacionDobleDerecha(nodoActual);
            }

        }else if(diferenciaDeAltura < -DIFERENCIA_PERMITIDA) {
            //rotacion a izquierda
        }
        return  nodoActual;
    }

}
