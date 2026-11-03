
package excepciones;


public class ClienteNoEncontradoException extends Exception {
    
    public ClienteNoEncontradoException() {
        super();
    }
    
    public ClienteNoEncontradoException(String mensaje){
        super(mensaje); 
    }
    
}
