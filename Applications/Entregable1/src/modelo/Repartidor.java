
package modelo;


public class Repartidor implements Rastreable , Facturable {
    
    private String legajo;
    private Vehiculo vehiculoAsignado;
    private double comision;
    private Pedido[] pedidosAsignados; 
    private int cantidadPedidos; 

    public Repartidor() {
       this.comision = 0.10;
       this.pedidosAsignados = new Pedido[20];
       this.cantidadPedidos = 0;
    }

   public Repartidor(String legajo, Vehiculo vehiculoAsignado) {
    this.legajo = legajo;
    this.vehiculoAsignado = vehiculoAsignado;
    this.comision = 0.10; // 10% fijado por regla de negocio
    this.pedidosAsignados = new Pedido[20];
    this.cantidadPedidos = 0;
}

    public String getLegajo() {
        return legajo;
    }

    public void setLegajo(String legajo) {
        if (legajo == null){
            throw new IllegalArgumentException("El legajo no debe estar vacio");
        }
        this.legajo = legajo;
    }

    public Vehiculo getVehiculoAsignado() {
        return vehiculoAsignado;
    }

    public void setVehiculoAsignado(Vehiculo vehiculoAsignado) {
        if (vehiculoAsignado== null){
           throw new IllegalArgumentException("El repartidor debe tener un vehiuclo asignado"); 
        }
        this.vehiculoAsignado = vehiculoAsignado;
    }

    public double getComision() {
        return comision;
    }

    public void setComision(double comision) {
         if (comision <= 0){
           throw new IllegalArgumentException("El repartidor debe tener una comision asignada"); 
        }
        this.comision = comision;
    }
    
    public void asignarPedido(Pedido pedido){
        if(pedido == null){
            throw new IllegalArgumentException("El pedido no puede ser nulo");
        }
        if (cantidadPedidos < pedidosAsignados.length){
            pedidosAsignados[cantidadPedidos++] = pedido;
        }
    }
    
    public String registrarEntregas(Pedido pedido){
        pedido.cambiarEstado("Entregado");
      
       return "Pedido" +pedido.getId()+ "entregado exitosamente";
    }
    
    @Override
    
    public double calcularImporte(){
        double totalEntregado = 0.0;
        
        for(int i = 0 ;  i < cantidadPedidos; i++){
            if("entregado".equalsIgnoreCase(pedidosAsignados[i].getEstado())){
                totalEntregado += pedidosAsignados[i].calcularImporte();
            }
        }
        
        return totalEntregado * this.comision;
        
    }
    
    @Override
    
    public String obtenerEstadoActual(){
        String infoVehiculo;
        if (vehiculoAsignado != null){
           infoVehiculo = vehiculoAsignado.getPatente();
        }
        else {
            infoVehiculo = "Sin vehiculo";
        }
        return "Estado del pedido: Repartidor:"+ legajo +  "Vehiculo:" + infoVehiculo + "Numero de pedidos:" + cantidadPedidos;
    }
    
    
    
}
