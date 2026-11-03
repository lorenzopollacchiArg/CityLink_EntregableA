
package modelo;


public class Moto extends Vehiculo {

    public Moto() {
    }

    public Moto(String patente) {
        super(patente, 30, 40, 120);
    }
    
    @Override 
    public double calcularTiempoEntrega(double km){
        if (km <= 0){
            throw new IllegalArgumentException("Lo km deben ser mayor a 0");
        }
        return ((km / getVelocidadKmh() *60))+ 5;
    }
    
    
    
}
