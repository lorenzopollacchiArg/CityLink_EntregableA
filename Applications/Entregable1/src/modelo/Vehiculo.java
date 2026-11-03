
package modelo;

public abstract class Vehiculo {
    private String patente; 
    private double capacidadKg; 
    private double velocidadKmh;
    private double costoPorkm; 

    public Vehiculo() {
    }

    public Vehiculo(String patente, double capacidadKg, double velocidadKmh, double costoPorkm) {
        this.patente = patente;
        this.capacidadKg = capacidadKg;
        this.velocidadKmh = velocidadKmh;
        this.costoPorkm = costoPorkm;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public double getCapacidadKg() {
        return capacidadKg;
    }

    public void setCapacidadKg(double capacidadKg) {
      
        if (capacidadKg <= 0){
           throw new IllegalArgumentException("La capacidad debe ser mayor a cero");
        }
        this.capacidadKg = capacidadKg;
    }

    public double getVelocidadKmh() {
        return velocidadKmh;
    }

    public void setVelocidadKmh(double velocidadKmh) {
        
        if (velocidadKmh <= 0 ){
          throw new IllegalArgumentException("la velocidad debe ser mayor a cero");
        }
        this.velocidadKmh = velocidadKmh;
    }

    public double getCostoPorkm() {
        return costoPorkm;
    }

    public void setCostoPorkm(double costoPorkm) {

        if (costoPorkm <= 0 ){
           throw new IllegalArgumentException(" El costo debe sar mayor a cero ");
        }
         this.costoPorkm = costoPorkm;
    }
   public boolean puedeTransportar(Pedido pedido){
      if (pedido == null){
          return false; 
      }
      if(pedido.getPesoKg() <= capacidadKg){
          return true; 
      }
      else {
          return false;
      }
   }
    public abstract double calcularTiempoEntrega(double km); 
}

