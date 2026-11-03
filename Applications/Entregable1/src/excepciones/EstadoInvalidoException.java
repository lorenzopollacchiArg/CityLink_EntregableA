
package excepciones;


public class EstadoInvalidoException extends  RuntimeException {
    public EstadoInvalidoException(){
        super();
    }
    
    public EstadoInvalidoException(String mensaje){
        super(mensaje);
    }
    
}
