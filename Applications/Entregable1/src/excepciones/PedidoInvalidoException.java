
package excepciones;


public class PedidoInvalidoException extends RuntimeException {

    public PedidoInvalidoException() {
    }
    
    
    public PedidoInvalidoException(String mensaje){
        super(mensaje); 
    }
  
}
