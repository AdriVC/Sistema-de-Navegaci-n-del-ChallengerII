
package p3.sistema_navegacion_challenger;

import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;

public class Grafo {
    String nombre;
    ArrayList <Nodo> lista_nodos;

    ArrayList <Nodo> ruta_optima;
    ArrayList <Integer> costos_ruta_op;

    public Grafo(String nombre) {
        this.nombre = nombre;
        this.lista_nodos = new ArrayList();
    }
    
    public Grafo(){
        this.nombre = "";
        this.lista_nodos = new ArrayList();
    }
    
    public int calcularCostoRutaOptima(Nodo inicial, Nodo finale, boolean warpSpeed) {
        ruta_optima = new ArrayList();
        costos_ruta_op = new ArrayList();
        Nodo[] nodos = new Nodo[lista_nodos.size() - 1];
        //Nodo[] recorridos = new Nodo[lista_nodos.size()];
        ArrayList <Nodo> recorridos = new ArrayList();
        int[] costos = new int[lista_nodos.size()-1];
        if (inicial == null || finale == null) {
            return -1;
        } else {
            int destino = 0;
            int cont = 0;
            for (int i = 0; i < lista_nodos.size(); i++) {
                if (!lista_nodos.get(i).equals(inicial)) {
                    nodos[cont] = lista_nodos.get(i);
                    cont++;
                }else{
                    recorridos.add(inicial);
                }
                if (lista_nodos.get(i).equals(finale)) {
                    destino = i;
                }
            }
            //llenar los costosParaLlegarADestino para nodo inicial
            for (int i = 0; i < nodos.length; i++) {
                if (inicial.getPesoFlecha(nodos[i]) == -1) {
                    costos[i] = (int) Double.POSITIVE_INFINITY;
                } else {
                    costos[i] = inicial.getPesoFlecha(nodos[i]);
                }
            }
            
            System.out.print("nodos:");
            this.printArray(nodos);
            System.out.print("recorridos:");
            this.printArray(recorridos);
            System.out.print("costos:");
            this.printArray(costos);
            
            for (int i = 0; i < nodos.length; i++) {
                Nodo temp = null;
                int indexTemp =-1;
                int costoMin = (int)Double.POSITIVE_INFINITY;
                for (int j = 0; j < nodos.length; j++) {
                    if(costos[j] < costoMin && nodos[j] != null){
                        temp = nodos[j];
                        indexTemp = j;
                        costoMin = costos[j];
                    }
                }
                nodos[indexTemp] = null;
                for (int j = 0; j < nodos.length; j++) {
                    if(nodos[j] != null){
                        int suma = -1;
                        if (costos[indexTemp] == (int)Double.POSITIVE_INFINITY || temp.getPesoFlecha(nodos[j]) == -1) {
                            suma = (int)Double.POSITIVE_INFINITY;
                        }else{
                            suma = costos[indexTemp] + temp.getPesoFlecha(nodos[j]);
                        }
                        if(suma < costos[j]){
                            costos[j] = suma;
                            recorridos.add(temp);
                        }
                    }
                } 
                
            }
            
            System.out.println("");
            System.out.print("nodos:");
            this.printArray(nodos);
            System.out.print("recorridos:");
            this.printArray(recorridos);
            System.out.print("costos:");
            this.printArray(costos);
            
            
           if(costos[destino-1] != (int)Double.POSITIVE_INFINITY){
                ruta(destino,lista_nodos,recorridos,inicial);
                Collections.reverse(ruta_optima);
                Collections.reverse(costos_ruta_op);
            }
            return costos[destino-1];
        }
    }
    
    public int calcularCostoRutaOptima2(Nodo inicial, Nodo finale, boolean warpSpeed) {
        ruta_optima = new ArrayList();
        costos_ruta_op = new ArrayList();
        
        System.out.println("lista de flechas de tierra");
        this.printArray(inicial.flechas_salientes);
        
        Nodo[] nodosPorRecorrer = new Nodo[lista_nodos.size()];
        ArrayList <Nodo> recorridos = new ArrayList();
        int costoParaLlegarADestino = (int)Double.POSITIVE_INFINITY;
        
        if (inicial == null || finale == null) {
            return -1;
        } else {
            for (int i = 0; i < lista_nodos.size(); i++) {
                nodosPorRecorrer[i] = lista_nodos.get(i);
            }
            
            Nodo temp = inicial;
            Nodo tempPadre = inicial;
            for (int i = 0; i < 10; i++) {
                
            }
            int indexTemp = lista_nodos.indexOf(inicial);
            
            System.out.println("temp: " + temp.toString());
            for (int i = 0; i < lista_nodos.size(); i++) {
                
                for (int j = 0; j < lista_nodos.size(); j++) {
                    if (lista_nodos.get(j).equals(temp)){
                        recorridos.add(temp);
                        nodosPorRecorrer[j] = null;
                    }
                }
                
                int suma = (int)Double.POSITIVE_INFINITY;
                if (temp.getPesoFlecha(finale) != -1) {
                    suma = 0;
                    for (int j = 0; j < recorridos.size() - 1; j++) {
                        if (recorridos.get(j).getPesoFlecha(recorridos.get(j + 1)) != -1) {
                            suma += recorridos.get(j).getPesoFlecha(recorridos.get(j + 1));
                        }
                    }
                    suma += temp.getPesoFlecha(finale);
                }
                if(suma < costoParaLlegarADestino){
                    costoParaLlegarADestino = suma;
                }
                
                System.out.print("nodos por recorrer:  ");
                this.printArray(nodosPorRecorrer);
                System.out.print("recorridos:  ");
                this.printArray(recorridos);
                System.out.println("costo para llegar al destino:  " + costoParaLlegarADestino);
                
                
                for (int j = 0; j < nodosPorRecorrer.length; j++) {
                    if (nodosPorRecorrer[j] != null && tempPadre.getPesoFlecha(nodosPorRecorrer[j]) != -1) {
                        temp = nodosPorRecorrer[j];
                        System.out.println(j+ ": temp en nodos conectados: " + temp.toString());
                        break;
                    }
                }
                if (indexTemp == lista_nodos.indexOf(temp)) {
                    for (int j = 0; j < nodosPorRecorrer.length; j++) {
                        if (nodosPorRecorrer[j] != null) {
                            temp = nodosPorRecorrer[j];
                            indexTemp = j;
                            break;
                        }
                    }
                }else{
                    indexTemp = lista_nodos.indexOf(temp);
                }
                
                System.out.println("Temp: " + temp.toString());
            }
            
            
            //if(costosParaLlegarADestino[destino] != (int)Double.POSITIVE_INFINITY){
                //ruta(destino,lista_nodos,recorridos,inicial);
                //Collections.reverse(ruta_optima);
                //Collections.reverse(costos_ruta_op);
            //}
            return costoParaLlegarADestino;
        }
    }
    
    private void ruta(int indice, ArrayList<Nodo> nodos, ArrayList<Nodo> recorridos, Nodo inicial){
        System.out.println("recursion:");
        System.out.print("indice: " + indice);
        System.out.println("recorrido[indice]: " + recorridos.get(indice).toString());
        if(recorridos.get(indice).equals(inicial)){
            System.out.println("entra");
            ruta_optima.add(inicial);
            costos_ruta_op.add(recorridos.get(indice).getPesoFlecha(nodos.get(indice+1)));
        }else{
            ruta_optima.add(recorridos.get(indice));
            System.out.println("recorridos.get(indice) " + recorridos.get(indice).toString());
            System.out.println("nodos.get(indice) " + nodos.get(indice).toString());
            costos_ruta_op.add(recorridos.get(indice).getPesoFlecha(nodos.get(indice)));
            System.out.println("for del else:");
            for (int i = 0; i < recorridos.size()-1; i++) {
                System.out.print(i + ": ");
                System.out.print(nodos.get(i+1).toString() + " == ");
                System.out.println(recorridos.get(i).toString());
                if(nodos.get(i+1).equals(recorridos.get(i))){
                    indice = i;
                }
            }
            ruta(indice,nodos,recorridos,inicial);
        }
    }
    
    private int warpSpeed(ArrayList <Nodo> lista){
        int ahorro =0;
        
        return ahorro;
    }
    
    public ArrayList<Nodo> getRutaOptima(){
        return ruta_optima;
    }
    
    public void printArray(ArrayList array){
        for (int i = 0; i < array.size(); i++) {
            System.out.print(array.get(i).toString() + ", ");
        }
        System.out.println("");
    }
    
    public void printArray(Object[] array){
        for (int i = 0; i < array.length; i++) {
            if(array[i] == null){
                System.out.print("null, ");
            }else{
                System.out.print(array[i].toString() + ", ");
            }
            
        }
        System.out.println("");
    }
    
    public void printArray(int[] array){
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + ", ");
        }
        System.out.println("");
    }
    
    public void printRutaOp(){
        System.out.println("Ruta optima: ");
        for (int i = 0; i < ruta_optima.size()-1; i++) {
            System.out.print(ruta_optima.get(i) + "--" + costos_ruta_op.get(i) + "-->");
        }
        //System.out.print(ruta_optima.get(ruta_optima.size()-1));
    }
    
    public void agregarNodo(Nodo nuevo){
        this.lista_nodos.add(nuevo);
    }
    
    public void deleteNodo(int pos){
        Nodo elemento = lista_nodos.get(pos);
        for (int i = 0; i < lista_nodos.size(); i++) {
            for (int j = 0; j < lista_nodos.get(i).flechas_salientes.size(); j++) {
                if(elemento.equals(lista_nodos.get(i).flechas_salientes.get(j).getDestino())){
                    lista_nodos.get(i).deleteFlecha(j);
                }
            }
        }
        lista_nodos.remove(pos);
    }
    
    public void agregarFlecha(String fuente, String destino, int peso){
        Nodo fuent = null;
        Nodo dest = null;
        for (int i = 0; i < lista_nodos.size(); i++) {
            if(lista_nodos.get(i).getNombre().equals(fuente))
                fuent = lista_nodos.get(i);
            if(lista_nodos.get(i).getNombre().equals(destino))
                dest = lista_nodos.get(i);
        }
        if(fuent != null && dest != null)
            fuent.agregarFlecha(peso, dest);
        else
            JOptionPane.showMessageDialog(null, "flecha no se pudo crear porque uno de los nodos no existe");
    }
    
    public void deleteFlecha(Flecha flecha){
        if(flecha != null)
            flecha.destino.deleteFlecha(flecha);
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    @Override
    public String toString(){
        String retornable = "";
        retornable += nombre + ": \n";
        for (int i = 0; i < lista_nodos.size(); i++) {
            retornable += lista_nodos.get(i).toString() + "\n";
        }
        return retornable;
    }
}
