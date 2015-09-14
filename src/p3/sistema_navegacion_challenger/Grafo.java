
package p3.sistema_navegacion_challenger;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Grafo {
    String nombre;
    ArrayList <Nodo> lista_nodos;
    Nodo head;

    public Grafo(String nombre) {
        this.nombre = nombre;
        this.lista_nodos = new ArrayList();
    }
    
    public Grafo(){
        this.nombre = "";
        this.lista_nodos = new ArrayList();
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
    
    public Nodo getHead(int pos){
        return head;
    }
    
    public void setHead(Nodo head){
        this.head = head;
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
