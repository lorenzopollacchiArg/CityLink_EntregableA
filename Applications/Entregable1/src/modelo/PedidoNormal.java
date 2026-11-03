
package modelo;
import excepciones.PedidoInvalidoException;


public  class PedidoNormal extends Pedido {

    public PedidoNormal(int id, double distanciaKm, double pesoKg, Cliente cliente, String destino) throws PedidoInvalidoException {
        super(id, distanciaKm, pesoKg, cliente, destino);
    }
    
    
   @Override
   public boolean esFragil(){
       return false; 
   }
   
    @Override
    
    public double calcularCosto(Vehiculo vehiculo) {
        return 1000 + ( getDistanciaKm() * vehiculo.getCostoPorkm());
    }
    
    @Override
    public double calcularImporte() {
        if (getVehiculoAsignado() != null){
           return calcularCosto(getVehiculoAsignado());
            
        }
        return 1000; // es la tarifa base si no hay un vehiculo asignaso
    }
    
    
}
