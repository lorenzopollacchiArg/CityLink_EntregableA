
package gestor;

import modelo.Cliente;
import modelo.Vehiculo;
import modelo.Pedido;
import modelo.Repartidor;
import excepciones.AlmacenamientoLlenoException;
import excepciones.CapacidadExcedidaException;
import excepciones.ClienteNoEncontradoException;


public class CityLink {
    private static final int MAX_CLIENTES = 50;
    private static final int MAX_PEDIDOS = 100;
    private static final int MAX_REPARTIDORES = 10; 
    private Cliente[] clientes;
    private Pedido[] pedidos;
    private Repartidor[] repartidores;
    private int cantidadClientes; 
    private int cantidadPedidos;
    private int cantidadRepartidores; 
    
    
  
   public CityLink(){
       this.clientes = new Cliente[MAX_CLIENTES];
       this.pedidos = new Pedido[MAX_PEDIDOS];
       this.repartidores = new Repartidor[MAX_REPARTIDORES];
       this.cantidadClientes = 0;
       this.cantidadPedidos = 0;
       this.cantidadRepartidores = 0;
    }
   
   public void agregarCliente(Cliente client) throws AlmacenamientoLlenoException{
       if (client == null){
           throw new IllegalArgumentException("El cliente no puede estar vacio");
       }
       if(this.cantidadClientes >= MAX_CLIENTES){
           throw new AlmacenamientoLlenoException("La capacidad maxima de los clientes supero el maximo");
       }
       this.clientes[this.cantidadClientes++] = client;
   }
   
   public void agregarPedido(Pedido order) throws AlmacenamientoLlenoException {
       if(order == null){
           throw new IllegalArgumentException("El pedido no puede estar vacio");
       }
       
       if(this.cantidadPedidos >= MAX_PEDIDOS){
           throw new AlmacenamientoLlenoException("La capacidad maxima de los clientes supero el maximo");
       }
       
       this.pedidos[this.cantidadPedidos++] = order;
   }
   
   public void agregarRepartidor(Repartidor dealer )throws AlmacenamientoLlenoException{
     if (dealer == null){
        throw new IllegalArgumentException("El repartidor no puede estar vacio");
    }
     if (this.cantidadRepartidores >= MAX_REPARTIDORES){
          throw new AlmacenamientoLlenoException("La capacidad maxima de los repartidores supero el maximo");
     }
     this.repartidores[this.cantidadRepartidores++] = dealer; 
   }
   
   public void asignarPedido(Pedido p, Vehiculo v) throws CapacidadExcedidaException {
        if (p == null || v == null) {
            throw new IllegalArgumentException("El pedido y el vehículo no pueden ser nulos.");
        }
        if (!v.puedeTransportar(p)) {
            throw new CapacidadExcedidaException("El vehículo no puede transportar el pedido (excede peso o es frágil).");
        }
        p.setVehiculoAsignado(v);
    }
   
   public void asignarPedido(Pedido order) throws CapacidadExcedidaException{
       
       if (order == null){
           throw new CapacidadExcedidaException("El pedido no puede estar vacio");
       }
       
     
       
       boolean asignado = false;
       
        for (int i = 0; i < this.cantidadRepartidores; i++) {
            Vehiculo v = this.repartidores[i].getVehiculoAsignado();
            if (v != null && v.puedeTransportar(order)) {
                order.setVehiculoAsignado(v);
                this.repartidores[i].asignarPedido(order);
                asignado = true;
                break;
            }
        }
        if (!asignado) {
            throw new CapacidadExcedidaException("No hay ningun repartidor disponible con un vehiculo para este pedido.");
        }
   }
   
   
   public boolean eliminarPedido(int id){
       int posicion = -1;
       
       // buscar la posision del pedido por el id
       
       for(int i=0; i < this.cantidadPedidos; i++){
           if(this.pedidos[i].getId() == id){
               posicion = i ;
               break;
           }
       }
       
       if (posicion == -1){
           return false;
       }
       // corrimiento hacia la izquierda para no dejar huecos
       
       for(int i= posicion; i < this.cantidadPedidos; i++ ){
           this.pedidos[i] = this.pedidos[i+1];
       }
      // se limpia la ultima casilla y se resta el contador
       this.pedidos[this.cantidadPedidos - 1] = null;
       this.cantidadPedidos--;
       return true;
       
   }
   
   public Pedido buscarPedido(int id) {
        for (int i=0; i < this.cantidadPedidos; i++) {
            
            if (this.pedidos[i].getId() == id) {
                
                return this.pedidos[i];
            }
        }
        return null;
    }

    public Repartidor buscarRepartidor(String legajo) {
        if (legajo == null){
            return null;
        }
        for (int i = 0; i < this.cantidadRepartidores; i++) {
            
            if (this.repartidores[i].getLegajo().equalsIgnoreCase(legajo.trim())) {
                return this.repartidores[i];
            }
         }
        
        return null;
    }
    
    public Cliente buscarCliente(int codigo) throws ClienteNoEncontradoException {
        for (int i = 0; i < this.cantidadClientes; i++) {
            if (this.clientes[i].getCodigo() == codigo) {
                return this.clientes[i];
            }
        }
        throw new ClienteNoEncontradoException("No se encontró ningún cliente con el código: " + codigo);
    }

    public Cliente buscarCliente(String nombre) throws ClienteNoEncontradoException {
        if (nombre == null) {
            throw new IllegalArgumentException("El nombre de búsqueda no puede ser nulo.");
        }
        for (int i = 0; i < this.cantidadClientes; i++) {
            if (this.clientes[i].getNombre().equalsIgnoreCase(nombre.trim())) {
                return this.clientes[i];
            }
        }
        throw new ClienteNoEncontradoException("No se encontró ningún cliente con el nombre: " + nombre);
    }
    
    public Cliente[] getClientes() {
        return clientes;
    }

    
    public Pedido[] getPedidos() {
        return pedidos;
    }

    public Repartidor[] getRepartidores() {
        return repartidores;
    }
   
    public int getCantidadPedidos(){
        return cantidadPedidos; 
    }
    
    public int getCantidadRepartidores(){
        return cantidadRepartidores;
   }
    
   public int getCantidadClientes(){
       return cantidadClientes; 
   }
   
 
    
    
    
    
    
    
}
