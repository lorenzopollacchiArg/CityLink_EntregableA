
package modelo;
import excepciones.PedidoInvalidoException;


public class PedidoUrgente extends Pedido {

    public PedidoUrgente() {
    }

    public PedidoUrgente(int id, double distanciaKm, double pesoKg, Cliente cliente, String destino) throws PedidoInvalidoException {
        super(id, distanciaKm, pesoKg, cliente, destino);
    }
    
    @Override
    
    public boolean esFragil(){
        return false; 
    }
    
    @Override
    public double calcularCosto(Vehiculo vehiculo){
        double costoBase = (1000 + (getDistanciaKm() * vehiculo.getCostoPorkm()));
         return costoBase * 1.50; 
    }
    
    @Override
    
    public double calcularImporte(){
        if (getVehiculoAsignado() != null){
            return calcularCosto(getVehiculoAsignado()); 
        }
        return 1000 * 1.50;
    }
    
}
