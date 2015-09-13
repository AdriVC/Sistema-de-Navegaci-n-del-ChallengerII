
package p3.sistema_navegacion_challenger;

import java.util.*;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Nodo {
    String nombre;
    Icon foto;
    ArrayList <Flecha> flechas_salientes;
    String path;

    public Nodo(String nombre, String path_foto) {
        this.nombre = nombre;
        this.foto = new ImageIcon(path_foto);
        flechas_salientes = new ArrayList();
        this.path = path_foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Icon getFoto() {
        return foto;
    }
    
    public String getFotoPath() {
        return path;
    }

    public void setFoto(String path_foto) {
        this.foto = new ImageIcon(path_foto);
    }
    
    public void agregarFlecha(int peso, Nodo destino){
        this.flechas_salientes.add(new Flecha(peso,destino));
    }
    
    public Flecha getFlecha(int pos){
        return flechas_salientes.get(pos);
    }
    
    public void deleteFlecha(int pos){
        this.flechas_salientes.remove(pos);
    }
    
    public void setFlecha(int pos, Flecha nuevo){
        this.flechas_salientes.set(pos, nuevo);
    }
    
    public String toString(){
        String retornable = "";
        retornable += nombre + ":";
        for (int i = 0; i < flechas_salientes.size(); i++) {
            retornable += " (" + i + ")" + flechas_salientes.get(i).toString();
        }
        return retornable;
    }
   
}

