
package modelo;
import excepciones.PedidoInvalidoException;


public class PedidoFragil extends Pedido {

    public PedidoFragil(int id, double distanciaKm, double pesoKg,  Cliente cliente, String destino) throws PedidoInvalidoException {
        super(id, distanciaKm, pesoKg, cliente, destino);
    }
    
    @Override
    public boolean esFragil(){
        return true;
    }
    
    @Override
    public double calcularCosto( Vehiculo vehiculo){
        double costoBase = (1000 + (getDistanciaKm() * vehiculo.getCostoPorkm()));
         return costoBase + 800; 
    }
    
    @Override
    public double  calcularImporte(){
        if (getVehiculoAsignado() != null){
            return calcularCosto(getVehiculoAsignado());
        }
        return 1800 ; // tarifa base mas el embalaje
    }
    
  
    
}
