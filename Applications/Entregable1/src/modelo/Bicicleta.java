
package modelo;

/**
 *
 * @author lorenzopollacchi
 */
public class Bicicleta extends Vehiculo {

    public Bicicleta() {
    }

    public Bicicleta(String patente) {
        super(patente, 10, 15, 50);
    }
    
   
    
    
    
    @Override 
     public  double calcularTiempoEntrega(double km){
       if (km <= 0){
           throw new IllegalArgumentException("La distacnia debe ser mayor a 0km  ");
       }
       return ((km / getVelocidadKmh()) * 60);
    }

    @Override
    public boolean puedeTransportar(Pedido pedido) {
        if (pedido.esFragil() ){
            return false; 
        }
        
        return super.puedeTransportar(pedido); 
    }

}
