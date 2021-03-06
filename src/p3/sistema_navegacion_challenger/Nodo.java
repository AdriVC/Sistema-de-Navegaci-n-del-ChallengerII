
package p3.sistema_navegacion_challenger;

import java.awt.Point;
import java.util.*;
import javax.swing.ImageIcon;

public class Nodo {
    String nombre;
    ImageIcon foto;
    ArrayList <Flecha> flechas_salientes;
    Point posicion;
    String path;

    public Nodo(String nombre, String path_foto) {
        this.nombre = nombre;
        this.foto = new ImageIcon(path_foto);
        flechas_salientes = new ArrayList();
        this.path = path_foto;
        this.posicion = new Point(0,0);
    }
    
    public void setPosicion(int x, int y){
        this.posicion.setLocation(x, y);
    }
    
    public void setPosicion(Point p){
        this.posicion.setLocation(p);
    }
    
    public Point getPosicion(){
        return posicion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ImageIcon getFoto() {
        return foto;
    }
    
    public String getFotoPath() {
        return path;
    }

    public void setFoto(String path_foto) {
        this.path = path_foto;
        this.foto = new ImageIcon(path_foto);
    }
    
    public void agregarFlecha(int peso, Nodo destino){
        this.flechas_salientes.add(new Flecha(peso,destino));
    }
    
    public Flecha getFlecha(int pos){
        return flechas_salientes.get(pos);
    }
    
    public int getPesoFlecha(Nodo destino){
        int peso =-1;
        for (int i = 0; i < flechas_salientes.size(); i++) {
            if(flechas_salientes.get(i).getDestino().equals(destino)){
                peso = flechas_salientes.get(i).getPeso();
            }
        }
        return peso;
    }
    
    public void deleteFlecha(int pos){
        this.flechas_salientes.remove(pos);
    }
    
    public void deleteFlecha(Flecha flecha){
        
        this.flechas_salientes.remove(flecha);
    }
    
    public void setFlecha(int pos, Flecha nuevo){
        this.flechas_salientes.set(pos, nuevo);
    }
    
    public String toString(){
//        String retornable = "";
//        retornable += nombre + ":";
//        for (int i = 0; i < flechas_salientes.size(); i++) {
//            retornable += " (" + i + ")" + flechas_salientes.get(i).toString();
//        }
//        return retornable;
        return nombre;
    }
   
}

