
package p3.sistema_navegacion_challenger;

public class Flecha {
    int peso;
    Nodo destino;

    public Flecha(int peso, Nodo destino) {
        this.destino = destino;
        this.peso = peso;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public Nodo getDestino() {
        return destino;
    }

    public void setDestino(Nodo destino) {
        this.destino = destino;
    }
    
    @Override
    public String toString(){
        return "--" + Integer.toString(peso) + "-->" + destino.getNombre();
    }
    
            
}
