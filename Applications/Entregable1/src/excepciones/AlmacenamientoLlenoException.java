
package excepciones;


public class AlmacenamientoLlenoException extends Exception{
    
    public AlmacenamientoLlenoException(){
        super();
    }
    
    public AlmacenamientoLlenoException(String mensaje){
        super(mensaje);
    }
    
}
