
package excepciones;


public class CapacidadExcedidaException extends Exception {
    
    public CapacidadExcedidaException(){
        super();
    }
    
    public CapacidadExcedidaException(String mensaje){
        super(mensaje);
    }
    
}
