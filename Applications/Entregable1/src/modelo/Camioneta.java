
package modelo;


public class Camioneta extends Vehiculo {

    public Camioneta() {
        super("", 500, 30, 300);
    }

    public Camioneta(String patente) {
        super(patente, 500, 30, 300);
    }
    
    @Override
    public double calcularTiempoEntrega(double km){
        if (km <= 0 ){
            throw new IllegalArgumentException("Lo km deben ser mayor a 0");
        }
        return ((km / getVelocidadKmh() *60) ) + 10 ;
    }
   
}
