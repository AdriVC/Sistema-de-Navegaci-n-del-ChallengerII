
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
        Nodo[] nodosParaTachar = new Nodo[lista_nodos.size() - 1];
        Nodo[] nodos = new Nodo[lista_nodos.size()-1];
        Nodo[] recorridos = new Nodo[lista_nodos.size()-1];
        int[] costos = new int[lista_nodos.size()-1];
        
        if (inicial == null || finale == null) {
            return -1;
        } else {
            
            int destino = 0;
            int cont = 0;
            for (int i = 0; i < lista_nodos.size(); i++) {
                if (!lista_nodos.get(i).equals(inicial)) {
                    nodosParaTachar[cont] = lista_nodos.get(i);
                    nodos[cont] = lista_nodos.get(i);
                    if(lista_nodos.get(i).equals(finale)){
                        destino = cont;
                    }
                    
                    //recorridos[cont] = inicial;
                    cont++;
                }
                
            }
            
            ruta_optima.add(inicial);
            
            //llenar los costosParaLlegarADestino para nodo inicial
            for (int i = 0; i < nodosParaTachar.length; i++) {
                if (inicial.getPesoFlecha(nodosParaTachar[i]) == -1) {
                    costos[i] = (int) Double.POSITIVE_INFINITY;
                } else {
                    costos[i] = inicial.getPesoFlecha(nodosParaTachar[i]);
                }
            }
            
//            System.out.print("nodos para tachar:  ");
//            this.printArray(nodosParaTachar);
//            System.out.print("nodos:  ");
//            this.printArray(nodos);
//            System.out.print("recorridos:  ");
//            this.printArray(recorridos);
//            System.out.print("costos:  ");
//            this.printArray(costos);
            
            for (int i = 0; i < nodosParaTachar.length; i++) {
                Nodo temp = null;
                int indexTemp =0;
                
                for (int j = 0; j < nodosParaTachar.length; j++) {
                    if (nodosParaTachar[j] != null) {
                        temp = nodosParaTachar[j];
                        indexTemp = j;
                    }
                }
                
                int costoMin = costos[indexTemp];
                for (int j = 0; j < nodosParaTachar.length; j++) {
                    if(costos[j] < costoMin && nodosParaTachar[j] != null){
                        temp = nodosParaTachar[j];
                        indexTemp = j;
                        costoMin = costos[j];
                    }
                }
                
                nodosParaTachar[indexTemp] = null;
                
                //cont = 0;
                for (int j = 0; j < nodosParaTachar.length; j++) {
                    if(nodosParaTachar[j] != null){
                        int suma;
                        if (costos[indexTemp] == (int)Double.POSITIVE_INFINITY || temp.getPesoFlecha(nodosParaTachar[j]) == -1) {
                            suma = (int)Double.POSITIVE_INFINITY;
                        }else{
                            suma = costos[indexTemp] + temp.getPesoFlecha(nodosParaTachar[j]);
                        }
                        if(suma < costos[j]){
                            costos[j] = suma;
                            if(j== destino){
                                if(ruta_optima.get(ruta_optima.size()-1).getPesoFlecha(temp) == -1){
                                    ruta_optima.remove(ruta_optima.size()-1);
                                }
                                ruta_optima.add(temp);
                                
                            }
                            //recorridos[cont] = temp;
                            //cont++;
                        }
                    }
                } 
                
//                System.out.print("nodos para tachar:  ");
//                this.printArray(nodosParaTachar);
//                System.out.print("nodos:  ");
//                this.printArray(nodos);
//                System.out.print("recorridos:  ");
//                this.printArray(recorridos);
//                System.out.print("costos:  ");
//                this.printArray(costos);
                
            }
            
            ruta_optima.add(finale);

            return costos[destino];
        }
    }
    
    public String printRutaOp(){
        String texto = "";
        if (!ruta_optima.isEmpty()) {
            texto += "\nNUEVA RUTA:\nPartida: " + ruta_optima.get(0) + "\nDestino: " + ruta_optima.get(ruta_optima.size()-1) + "\nRuta optima: ";
            for (int i = 0; i < ruta_optima.size() - 1; i++) {
                texto += ruta_optima.get(i) + "--" + ruta_optima.get(i).getPesoFlecha(ruta_optima.get(i+1))+ "-->";
            }
            texto += ruta_optima.get(ruta_optima.size() - 1);
        } 
        return texto;
    }
    
//    private void rutaOptima(int indice, Nodo[] nodos, Nodo[] recorridos, Nodo inicial){
//        System.out.println("NUEVO RECURSIVO:\nindice: " + indice);
//        if(recorridos[indice].equals(inicial)){
//            System.out.println("entro a condicion final");
//            ruta_optima.add(inicial);
//            costos_ruta_op.add(recorridos[indice].getPesoFlecha(nodos[indice]));
//        }else{
//            ruta_optima.add(recorridos[indice]);
//            costos_ruta_op.add(recorridos[indice].getPesoFlecha(nodos[indice]));
//            for (int i = 0; i < nodos.length; i++) {
//                System.out.println("for del else: "+ nodos[i] + " == " + recorridos[i]);
//                if(nodos[i].equals(recorridos[i])){
//                    indice = i;
//                }
//            }
//            rutaOptima(indice,nodos,recorridos,inicial);
//        }
//    }
    
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
