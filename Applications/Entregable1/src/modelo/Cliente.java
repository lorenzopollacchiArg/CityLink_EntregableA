
package modelo;

public class Cliente {
    
    private String nombre;
    private String telefono; 
    private int codigo; 
    private  String zona;

    public Cliente() {
    }

    public Cliente(String nombre, String telefono, int codigo, String zona) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.codigo = codigo;
        this.zona = zona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null){
            throw new IllegalArgumentException("El nombre no debe estar vacio");
        }
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        if (telefono == null ){
            throw new IllegalArgumentException("El telefono no debe estar vacio");
        }
        this.telefono = telefono;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        if (codigo <= 0  ){
            throw new IllegalArgumentException("El codigo no debe ser menor que cero");
        }
        this.codigo = codigo;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        if (zona == null ){
            throw new IllegalArgumentException("Debe ingresar una zona");
        }
        this.zona = zona;
    }
    
    
}
