
package app;

import gestor.CityLink;
import excepciones.AlmacenamientoLlenoException;
import excepciones.CapacidadExcedidaException;
import excepciones.ClienteNoEncontradoException;
import excepciones.EstadoInvalidoException;
import excepciones.PedidoInvalidoException;
import modelo.Bicicleta;
import modelo.Camioneta;
import modelo.Cliente;
import modelo.Facturable;
import modelo.Moto;
import modelo.Pedido;
import modelo.PedidoFragil;
import modelo.PedidoNormal;
import modelo.PedidoUrgente;
import modelo.Rastreable;
import modelo.Repartidor;
import modelo.Vehiculo;

import java.util.Scanner;
        
public class Entregable1 {
    
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        CityLink sistema = new CityLink();
        
        ejecutarPrecarga(sistema);
        
        int opcion = 0;
        
        do{
            try{
                mostrarMenu();
                System.out.println("Ingrese una opcion para continuar:");
                opcion = Integer.parseInt(scanner.nextLine().trim());
                
                switch(opcion){
                    case 1: 
                        registrarCliente(sistema, scanner);
                    break;
                    
                    case 2:
                        registarRepartidor(sistema, scanner);
                    break;
                    
                    case 3:
                        registrarPedido(sistema, scanner);
                    break;
                    
                    case 4:
                        asignarPedido(sistema, scanner);
                    break;
                    
                    case 5:
                        cambiarEstado(sistema, scanner);
                    break;
                    
                    case 6:
                        buscarCliente(sistema, scanner);
                       break;
                    
                    case 7:
                        listaPrecios(sistema, scanner);
                    break; 
                    
                    case 8:
                        consutarEstadoRastreable(sistema, scanner);
                    break;
                    
                    case 9:
                        verFacturacion(sistema, scanner);
                    break;
                    
                    case 10:
                        System.out.println("Cerrando gestor. Muchas gracias.");
                    break;
                    
                    default:
                        System.out.println("Opción inválida, intente de nuevo.");
                }
            } catch(NumberFormatException e){
                System.out.println("Debe ingresar un valor numérico válido.");
            } catch (AlmacenamientoLlenoException e) {
                System.out.println("\n[ERROR DE NEGOCIO] " + e.getMessage());
            } catch (CapacidadExcedidaException e) {
                System.out.println("\n[ERROR DE NEGOCIO] " + e.getMessage());
            } catch (ClienteNoEncontradoException e) {
                System.out.println("\n[ERROR DE NEGOCIO] " + e.getMessage()); 
            } catch (EstadoInvalidoException e) {
                System.out.println("\n[ERROR DE ESTADO] " + e.getMessage());
            } catch (PedidoInvalidoException e) {
                System.out.println("\n[ERROR DE PEDIDO] " + e.getMessage());
            } catch (Exception e){
                System.out.println("Error general: " + e.getMessage());
            } finally{
                System.out.println("...\n");
            }
        } while (opcion != 10);
        
        scanner.close();
    }
    
    private static void mostrarMenu(){
        System.out.println("=== BIENVENIDO/A AL SISTEMA CITYLINK ===");
        System.out.println("1- Registrar cliente");
        System.out.println("2- Registrar repartidor con vehiculo");
        System.out.println("3- Registrar pedido (Normal | Urgente | Fragil)");
        System.out.println("4- Asignar pedido a repartidor o vehiculo");
        System.out.println("5- Cambiar estado del pedido");
        System.out.println("6- Buscar cliente por codigo o nombre");
        System.out.println("7- Listar pedidos con costo y tiempo de entrega");
        System.out.println("8- Consultar estado del pedido o repartidor (Rastreable)");
        System.out.println("9- Ver importe total y comisiones");
        System.out.println("10- Salir");
    }
    
    private static void ejecutarPrecarga(CityLink sistema){
        try{
            Cliente client1 = new Cliente("Lorenzo Pollacchi", "2657-300978", 1, "Zona centro");
            Cliente client2 = new Cliente("Juan Carlos Pollacchi", "2657-611910", 2, "Zona centro");
            Cliente client3 = new Cliente("Aero Club VM", "2657-123456", 3, "Zona Norte");
            Cliente client4 = new Cliente("Augusto Magallanes", "2657-123456", 4, "Zona Oeste");
            Cliente client5 = new Cliente("Carolina Enciso", "2657-652975", 5, "Zona Centro");
            
            sistema.agregarCliente(client1);
            sistema.agregarCliente(client2);
            sistema.agregarCliente(client3);
            sistema.agregarCliente(client4);
            sistema.agregarCliente(client5);
            
            Vehiculo bici = new Bicicleta("Venzo-001");
            Vehiculo moto = new Moto("A12345");
            Vehiculo camioneta = new Camioneta("AH828GL");
            
            Repartidor d1 = new Repartidor("2026-001", bici);
            Repartidor d2 = new Repartidor("2024-023", moto);
            Repartidor d3 = new Repartidor("2025-033", camioneta);
            
            sistema.agregarRepartidor(d1);
            sistema.agregarRepartidor(d2);
            sistema.agregarRepartidor(d3);
            
            Pedido order1 = new PedidoNormal(1, 5.0, 3.0, client1, "Fuerte Constitucional 182");
            Pedido order2 = new PedidoNormal(2, 10.0, 12.0, client2, "Avenida Mitre 1200");
            Pedido order3 = new PedidoUrgente(3, 22.0, 13.0, client3, "Aero Club Villa Mercedes");
            Pedido order4 = new PedidoFragil(4, 8.0, 3.0, client4, "Aeropuerto Villa Reynolds");
            Pedido order5 = new PedidoUrgente(5, 10.0, 12.0, client5, "Rivadavia 1200");
            Pedido order6 = new PedidoNormal(6, 30.0, 23.0, client5, "Aeropuerto San Luis LUQ");
            Pedido order7 = new PedidoFragil(7, 14.0, 4.0, client3, "Aero Club Villa Mercedes");
            Pedido order8 = new PedidoUrgente(8, 5.0, 3.0, client1, "Fuerte Constitucional 182");
            
            sistema.agregarPedido(order1);
            sistema.agregarPedido(order2);
            sistema.agregarPedido(order3);
            sistema.agregarPedido(order4);
            sistema.agregarPedido(order5);
            sistema.agregarPedido(order6);
            sistema.agregarPedido(order7);
            sistema.agregarPedido(order8);
            
            sistema.asignarPedido(order1, bici);
            d1.asignarPedido(order1);
            
            sistema.asignarPedido(order4, moto);
            d2.asignarPedido(order4);
            
            sistema.asignarPedido(order8, camioneta);
            d3.asignarPedido(order8);
            
            System.out.println("Precarga exitosa.");
            
        } catch (Exception e) {
            System.out.println("[AVISO PRECARGA] " + e.getMessage());
        }
    }

    
     //menus capa visual de la app
   

    private static void registrarCliente(CityLink sistema, Scanner scanner) throws AlmacenamientoLlenoException {
        System.out.println(" REGISTRAR CLIENTE ");
        System.out.println("Ingrese el nombre:");
        String nombre = scanner.nextLine().trim();
        System.out.println("Ingrese el numero de telefono:");
        String telefono = scanner.nextLine().trim();
        System.out.println("Ingrese el codigo numerico de cliente:");
        int codigo = Integer.parseInt(scanner.nextLine().trim());
        System.out.println("Ingrese la zona:");
        String zona = scanner.nextLine().trim();

        Cliente nuevo = new Cliente(nombre, telefono, codigo, zona);
        sistema.agregarCliente(nuevo);
        System.out.println("Cliente registrado con exito!");
    }

    private static void registarRepartidor(CityLink sistema, Scanner scanner) throws AlmacenamientoLlenoException {
        System.out.println("REGISTRAR REPARTIDOR CON VEHICULO ");
        System.out.println("Ingrese el numero de  legajo:");
        String legajo = scanner.nextLine().trim();
        
        System.out.println("Seleccione el  tipo de vehiculo 1- Bicicleta, 2-  Moto, 3- Camioneta");
        int tipoVehiculo = Integer.parseInt(scanner.nextLine().trim());
        while (tipoVehiculo < 1 || tipoVehiculo > 3){
            System.out.println("Opcion invalida, intente de nuevo");
            tipoVehiculo = Integer.parseInt(scanner.nextLine().trim());
        }
        
        System.out.println("Ingrese la patente del vehiculo:");
        String patente = scanner.nextLine().trim();
        while (patente == null || patente.length() == 0 ){
            System.out.println("Ingrese la patente del vehiculo, es obligatorio");
            patente = scanner.nextLine().trim();
        }
        
        Vehiculo vehiculo;
        
        
        switch (tipoVehiculo) {
            case 1:
                
                vehiculo = new Bicicleta(patente);
                break;
            case 2:
                vehiculo = new Moto(patente);
                
                break;
            case 3:
                
                vehiculo = new Camioneta(patente);
                break;
            default:
                
                System.out.println("opcion invalida. Se asignara una bicicleta por defecto");
                vehiculo = new Bicicleta(patente);
        }
        
        Repartidor repartidor = new Repartidor(legajo, vehiculo);
        sistema.agregarRepartidor(repartidor);
        
        System.out.println("Repartidor registrado exitosamente");
    }

    private static void registrarPedido(CityLink sistema, Scanner scanner) 
            throws AlmacenamientoLlenoException, ClienteNoEncontradoException, PedidoInvalidoException {
        System.out.println("REGISTRAR PEDIDO");
        System.out.println("Ingrese el ID del pedido (Solo numeros):");
        int id = Integer.parseInt(scanner.nextLine().trim());
        while (id < 0 || id > 1000000){
            System.out.println("Ingrese un id valido, intente de nuevo");
            id = Integer.parseInt(scanner.nextLine().trim());
        }
        
        System.out.println("Ingrese peso en kg:");
        double peso = Double.parseDouble(scanner.nextLine().trim());
        
        while (peso < 0 || peso > 10000){
            System.out.println("Ingrese un peso valido, Si el peso que ingreso es veridico consulte a atencion al cliente");
            peso = Double.parseDouble(scanner.nextLine().trim());
        }
        
        System.out.println("Ingrese distancia en km:");
        double distancia = Double.parseDouble(scanner.nextLine().trim());
        
         while (distancia < 0 || distancia > 10000){
           System.out.println("Ingrese una distancia valida, Si la distanica que ingreso es veridica consulte a atencion al cliente");
           distancia = Double.parseDouble(scanner.nextLine().trim()); 
        }
        
        System.out.println("Ingrese código del cliente asociado:");
        int codCliente = Integer.parseInt(scanner.nextLine().trim());
        Cliente cliente = sistema.buscarCliente(codCliente); // lanza Clientenoencontradoexception si no existe
        
        System.out.println("Ingrese dirección de destino:");
        String destino = scanner.nextLine().trim();
        
        while (destino == null || destino.length() == 0){
            System.out.println("Ingrese un destino o dirreccion");
            destino = scanner.nextLine().trim();
        }
        
        System.out.println("Seleccione tipo de pedido (1- Normal, 2- Urgente, 3- Frágil):");
        int tipo = Integer.parseInt(scanner.nextLine().trim());
        
        while ( tipo < 0 || tipo > 3){
            System.out.println("Ingrese una opcion valida");
            tipo = Integer.parseInt(scanner.nextLine().trim());
        }
        
        Pedido pedido;
        switch (tipo) {
            case 1:
                pedido = new PedidoNormal(id, peso, distancia, cliente, destino);
                break;
            case 2:
                pedido = new PedidoUrgente(id, peso, distancia, cliente, destino);
                break;
            case 3:
                pedido = new PedidoFragil(id, peso, distancia, cliente, destino);
                break;
            default:
                pedido = new PedidoNormal(id, peso, distancia, cliente, destino);
        }
        
        sistema.agregarPedido(pedido);
        System.out.println("Pedido registrado con exito");
    }
    
    
    private static void asignarPedido(CityLink sistema, Scanner scanner) throws CapacidadExcedidaException {
        
        System.out.println(" ASIGNAR PEDIDO ");
        System.out.println("Ingrese el Id del pedido a asignar:");
        int idPedido = Integer.parseInt(scanner.nextLine().trim());
        
        while (idPedido < 0 || idPedido > 10000){
            System.out.println("Ingrese un numero de pedido valido o consulte a atencion a cliente");
            idPedido = Integer.parseInt(scanner.nextLine().trim());
        }
        
        Pedido pedido = sistema.buscarPedido(idPedido);
        
        if (pedido == null) {
            System.out.println("No se encontro ningun pedido con el Id: " + idPedido);
            return;
        }
        
        System.out.println("Ingrese una opcion para continuar");
        System.out.println("1- Asignarlo a un repartidor automático");
        System.out.println("2- Elegir vehículo específico");
        int opcion = Integer.parseInt(scanner.nextLine().trim());
        
        while (opcion < 1 || opcion > 2){
            System.out.println("Ingrese una opcion valida, intente de nuevo");
            opcion = Integer.parseInt(scanner.nextLine().trim());
        }
        
        if (opcion == 1) {
            
            sistema.asignarPedido(pedido);
            System.out.println("Pedido asignado automáticamente al repatidor");
        } 
        
        else {
            
            System.out.println("Ingrese el legajo del repartidor o patente del vehiculo deseado:");
            
            String legajoRepartidor = scanner.nextLine().trim();
            while(legajoRepartidor == null || legajoRepartidor.length() ==0){
                System.out.println("Ingrese un numero de legajo");
                legajoRepartidor = scanner.nextLine().trim();
            }
            
            Repartidor rep = sistema.buscarRepartidor(legajoRepartidor);
          
            
            if (rep != null && rep.getVehiculoAsignado() != null) {
                sistema.asignarPedido(pedido, rep.getVehiculoAsignado());
                rep.asignarPedido(pedido);
                System.out.println("Pedido asignado exitosamente al vehiculo " + rep.getVehiculoAsignado().getPatente());
            } 
            
            else {
                System.out.println("No se encontro ningun repartidor o no tiene ningun vehiculo asignado.");
            }
        }
    }

    //
    
    private static void cambiarEstado(CityLink sistema, Scanner scanner) throws EstadoInvalidoException {
        System.out.println("CAMBIAR ESTADO DE PEDIDO");
        System.out.println("Ingrese el Id del pedido:");
        int id = Integer.parseInt(scanner.nextLine().trim());
        
        while (id < 0 || id >1000000){
            System.out.println("Ingrese un id valido, intente de nuevo");
            id = Integer.parseInt(scanner.nextLine().trim());
        }
            
        
        Pedido pedido = sistema.buscarPedido(id);
        
        if (pedido == null) {
            System.out.println("Pedido no encontrado.");
            return;
        }
        
        System.out.println("Estado actual: " + pedido.obtenerEstadoActual());
       System.out.println("Estado actual: " + pedido.getEstado());
        System.out.println("Seleccione el nuevo estado:");
        System.out.println("1- En Camino");
        System.out.println("2-Entregado");
        System.out.println("3- Cancelado");
        int opcionEstado = Integer.parseInt(scanner.nextLine().trim());
        
          while(opcionEstado <1 || opcionEstado > 3){
              System.out.println("ingrese una opcion valida , intente de nuevo");
              opcionEstado = Integer.parseInt(scanner.nextLine().trim());
          }
        String nuevoEstado;

        switch (opcionEstado) {
            case 1:
                nuevoEstado = "En Camino";
                break;
            case 2:
                nuevoEstado = "Entregado";
                break;
            case 3:
                nuevoEstado = "Cancelado";
                break;
            default:
                System.out.println("Opción inválida. No se cambió el estado.");
                return; 
        }

        // llamamos a la máquina de estados de Pedido que evaluará si el salto es legal.
        pedido.cambiarEstado(nuevoEstado);
        System.out.println("Estado actualizado a: " + pedido.getEstado());
    }
    
     
    
    private static void buscarCliente(CityLink sistema, Scanner scanner) throws ClienteNoEncontradoException {
        System.out.println("BUSCAR CLIENTE ");
        System.out.println("Ingrese como desea buscar al cliente: ");
         System.out.println("1- Por Código");
           System.out.println("2- Por Nombre");
         int opcion = Integer.parseInt(scanner.nextLine().trim());
         
          while(opcion < 1 || opcion >2){
              System.out.println("Ingrese una opcion valida, intende de nuevo");
              opcion = Integer.parseInt(scanner.nextLine().trim());
          }
         
          
         if (opcion == 1) {
            System.out.println("Ingrese codigo de cliente: ");
            int codigo = Integer.parseInt(scanner.nextLine().trim());
            Cliente c = sistema.buscarCliente(codigo);
            System.out.println("Cliente encontrado: " + c.getNombre() + " | Telefono: " + c.getTelefono() + " | Zona: " + c.getZona());
        } 
         else {
             
            System.out.println("Ingrese nombre del cliente:");
            String nombre = scanner.nextLine().trim();
            Cliente c = sistema.buscarCliente(nombre);
            System.out.println("Cliente encontrado - Código: " + c.getCodigo() + " | Nombre: " + c.getNombre() + " | Telefono: " + c.getTelefono());
        }
    
                
    }

    private static void listaPrecios(CityLink sistema, Scanner scanner) {
        
        System.out.println("LISTA DE PEDIDOS, COSTOS Y TIEMPOS");
        Pedido[] pedidos = sistema.getPedidos();
        int total = sistema.getCantidadPedidos();
        
        if (total == 0) {
            System.out.println("No hay pedidos registrados en el sistema.");
            return;
        }
        
        System.out.println("Ingrese velocidad promedio o distancia de referencia en km para calcular el tiempo estimado (ej= 10):");
        double kmRef = Double.parseDouble(scanner.nextLine().trim());
        
        for (int i = 0; i < total; i++) {
            Pedido p = pedidos[i];
            System.out.println("Pedido Id: " +  p.getId() + " | Destino: " +  p.getDestino()+ " | Estado: "+ p.obtenerEstadoActual());
            
            if (p.getVehiculoAsignado() != null) {
                double costo = p.calcularCosto(p.getVehiculoAsignado());
                double tiempo = p.getVehiculoAsignado().calcularTiempoEntrega(kmRef);
                System.out.println(" Costo estimado: " + costo + "$ | Tiempo estimado (" + kmRef + " km): " +tiempo + " horas (Vehículo: " + p.getVehiculoAsignado().getPatente() +")");
            } 
            else {
                System.out.println("Sin vehículo asignado");
            }
        }
    }

    private static void consutarEstadoRastreable(CityLink sistema, Scanner scanner) {
        System.out.println("CONSULTAR ESTADO RASTREABLE");
        System.out.println("Ingrese que  desea consultar");
        System.out.println("1- Pedido por Id");
        System.out.println("2: Repartidor por Legajo");
        int opcion = Integer.parseInt(scanner.nextLine().trim());
        
        while(opcion < 1 || opcion > 2){
            System.out.println("Ingrese una opcion valida");
            opcion = Integer.parseInt(scanner.nextLine().trim());
            
        }
        
       
        
        if (opcion == 1) {
            System.out.println("Ingrese Id del pedido:");
            int id = Integer.parseInt(scanner.nextLine().trim());
            
            while (id < 0 || id >1000000){
            System.out.println("Ingrese un id valido, intente de nuevo");
            id = Integer.parseInt(scanner.nextLine().trim());
        }
            
            Pedido p = sistema.buscarPedido(id);
            if (p != null) {
                Rastreable r = p;
                System.out.println("Estado Rastreable del Pedido ess: " + r.obtenerEstadoActual());
            } 
            else {
                System.out.println("Pedido no encontrado.");
            }
        } 
        else {
            System.out.println("Ingrese legajo del repartidor:");
            String legajo = scanner.nextLine().trim();
            Repartidor rep = sistema.buscarRepartidor(legajo);
            
            if (rep != null) {
                Rastreable r = rep;
                System.out.println("Estado Rastreable del Repartidor: " + r.obtenerEstadoActual());
            } 
            else {
                System.out.println("Repartidor no encontrado.");
            }
        }
    }

    private static void verFacturacion(CityLink sistema, Scanner scanner) {
        System.out.println("IMPORTES Y COMISIONES (FACTURABLE)");
        
        Repartidor[] repartidores = sistema.getRepartidores();
        int totalRep = sistema.getCantidadRepartidores();
        
        double comisionTotalGeneral = 0.0;
        
        for (int i = 0; i < totalRep; i++) {
            Repartidor rep = repartidores[i];
            Facturable f = rep;
            double comisionRep = f.calcularImporte();
            comisionTotalGeneral += comisionRep;
            System.out.println("Repartidor Legajo: " +rep.getLegajo() + " | Comision (10% de entregas): $" + comisionRep);
        }
        System.out.println("...");
        System.out.println("Comisión total a abonar a la flota: $" + comisionTotalGeneral);
    }
}