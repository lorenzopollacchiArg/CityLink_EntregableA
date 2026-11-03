
package modelo;
import excepciones.PedidoInvalidoException;
import excepciones.EstadoInvalidoException;


public abstract class Pedido implements Rastreable ,Facturable { 
    private int id;
    private double distanciaKm;
    private double pesoKg;
    private Vehiculo vehiculoAsignado;
    private Cliente cliente;
    private String estado;
    private String destino;

    public Pedido() {
        this.estado = "Pendiente";
    }
    
   public Pedido(int id, double distanciaKm, double pesoKg, Cliente cliente, String destino) 
            throws PedidoInvalidoException {
        setId(id);
        setDistanciaKm(distanciaKm);
        setPesoKg(pesoKg);
        setCliente(cliente);
        setDestino(destino);
        this.estado = "PENDIENTE"; 
        this.vehiculoAsignado = null; 
    }
    
    
    public Cliente getCliente(){
        return cliente; 
    }
    
    public void setCliente(Cliente cliente) throws PedidoInvalidoException {
        if (cliente == null){
            throw new PedidoInvalidoException("El cliente no debe estar vacio");
        }
        this.cliente = cliente; 
    }
    
    public String getDestino(){
        return destino;
    }
    
    public void setDestino(String destino)throws PedidoInvalidoException{
        if (destino == null){
            throw new PedidoInvalidoException("El destino no debe estar vacio");
        }  
        this.destino = destino;
    }
    
    public String getEstado(){
        return estado; 
    }
   
    

    public int getId() {
        return id;
    }

    public void setId(int id) throws PedidoInvalidoException {
        if (id <= 0){
           throw new PedidoInvalidoException("El id no debe estar vacio");
        }
        this.id = id;
    }

    public double getDistanciaKm() {
        return distanciaKm;
    }

    public void setDistanciaKm(double distanciaKm) throws PedidoInvalidoException {
        if (distanciaKm <= 0){
           throw new PedidoInvalidoException("La distancia debe ser mayor a 0");
        }
        this.distanciaKm = distanciaKm;
    }

    public Vehiculo getVehiculoAsignado(){
        return vehiculoAsignado;
    }

    public void setVehiculoAsignado(Vehiculo vehiculoAsignado) {
        this.vehiculoAsignado = vehiculoAsignado;
    }
    
    public double getPesoKg(){
        return pesoKg;
    }
    
    public void setPesoKg(double pesoKg)throws PedidoInvalidoException{
        if (pesoKg <= 0 ){
               throw new PedidoInvalidoException("El peso debe ser mayor a 0");
        }
        
        this.pesoKg = pesoKg; 
    }
    
    public void cambiarEstado( String nuevoEstado){
        if (nuevoEstado == null){
            throw new EstadoInvalidoException("El estado destino no puede estar vacio");
        }
        
        if ("Pendiente".equalsIgnoreCase(this.estado)){
            if("En Camino".equalsIgnoreCase(nuevoEstado) || "Cancelado".equalsIgnoreCase(nuevoEstado)){
                this.estado = nuevoEstado.toUpperCase(); 
            } else {
                throw new EstadoInvalidoException("Desde pendiente solo puede cancelarse o salir en camino");
            }
        }
        else if ("En Camino".equalsIgnoreCase(this.estado)){
           if ("Entregado".equalsIgnoreCase(nuevoEstado) || "Cancelado".equalsIgnoreCase(nuevoEstado)){
            this.estado = nuevoEstado.toUpperCase();     
            } else {
               throw new EstadoInvalidoException("Desde En camino solo se puede pasar a entregado  o cancelado");
            }
        }
        
        else if ("Entregado".equalsIgnoreCase(this.estado) || "Cancelado".equalsIgnoreCase(this.estado)){
            
          throw new EstadoInvalidoException("El pedido ya esta " + this.estado + " y no permite mas cambios de estado");  
        } else  {
            throw new EstadoInvalidoException("Estado actual desconocido: " + this.estado);
        }
        
        
    }
    public abstract double calcularCosto(Vehiculo vehiculo);
    
    public abstract boolean esFragil();
    
    @Override
    
    public String obtenerEstadoActual(){
        return this.estado; 
    }
    
    @Override 
    
    public abstract double  calcularImporte();
}
