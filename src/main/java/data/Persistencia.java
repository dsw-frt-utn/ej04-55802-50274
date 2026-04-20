package data;

import domain.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Persistencia {
    private static ArrayList<Vehiculo> vehiculos = new ArrayList<>();
    private static ArrayList<Responsable> responsables = new ArrayList<>();
    private static ArrayList<Sucursal> sucursales = new ArrayList<>();
    private static ArrayList<List<String>> marcas = new ArrayList<>();
    
    private static void inicializarResponsables(){
        Responsable r1 = new Responsable("Carlos Gómez", "25444111", "3815551111");
        Responsable r2 = new Responsable("Laura Pérez", "30111222", "3815552222");
        responsables.add(r1);
        responsables.add(r2);
    }
    
    private static void inicializarSucursales(){
        Sucursal s1 = new Sucursal("SUC01", "Av. Belgrano 1200", "Tucumán", responsables.get(0));
        Sucursal s2 = new Sucursal("SUC02", "San Martín 450", "Yerba Buena", responsables.get(1));
        
        sucursales.add(s1);
        sucursales.add(s2);
    }
    
    private static void inicializarMarcas(){        
        marcas.add(Arrays.asList("Renault","Alemania"));
        marcas.add(Arrays.asList("Iveco","Braisl"));
        marcas.add(Arrays.asList("Mercedes Benz","Alemania"));
        marcas.add(Arrays.asList("Hyundai","China"));
    }
    
    public static ArrayList<Vehiculo> getVehiculos(){
        return vehiculos;
    }
    
    public static Optional<Vehiculo> getVehiculo(String patente){
        return vehiculos.stream()
                .filter(v -> v.getPatente().equals(patente))
                .findFirst();
    }

    public static ArrayList<Sucursal> getSucursales() {
        return sucursales;
    }

    public static ArrayList<List<String>> getMarcas() {
        return marcas;
    }
    
    public static void cargarElectrico(String patente,String marcaNombre,String modelo, int anio,double capacidad,Sucursal sucursal,double campo1){
        VehiculoElectrico vehiculo = new VehiculoElectrico(patente,marcaNombre,modelo,anio,capacidad,sucursal,campo1);
        vehiculos.add(vehiculo);
    }
    
    public static void cargarCombustible(String patente,String marcaNombre,String modelo, int anio,double capacidad,Sucursal sucursal,double campo1, double campo2){
        VehiculoCombustible vehiculo = new VehiculoCombustible(patente,marcaNombre,modelo,anio,capacidad,sucursal,campo1,campo2);
        vehiculos.add(vehiculo);
    }
    
    public static void inicializar(){
        inicializarResponsables();
        inicializarSucursales();
        inicializarMarcas();
    }
}
