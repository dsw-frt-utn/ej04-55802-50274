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
    private static ArrayList<Marca> marcas = new ArrayList<>();
    
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
        Marca m1 = new Marca("Renault", "Francia");
        Marca m2 = new Marca("Ford", "Estados Unidos");
        Marca m3 = new Marca("Iveco", "Italia");
        Marca m4 = new Marca("Mercedes", "Alemania");
        
        marcas.add(m1);
        marcas.add(m2);
        marcas.add(m3);
        marcas.add(m4);
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

    public static ArrayList<Marca> getMarcas() {
        return marcas;
    }
    
    public static void cargarElectrico(String patente,Marca marca,String modelo, int anio,double capacidad,Sucursal sucursal,double campo1){
        VehiculoElectrico vehiculo = new VehiculoElectrico(patente,marcaNombre,modelo,anio,capacidad,sucursal,campo1);
        vehiculos.add(vehiculo);
    }
    
    public static void cargarCombustible(String patente,Marca marca,String modelo, int anio,double capacidad,Sucursal sucursal,double campo1, double campo2){
        VehiculoCombustible vehiculo = new VehiculoCombustible(patente,marcaNombre,modelo,anio,capacidad,sucursal,campo1,campo2);
        vehiculos.add(vehiculo);
    }
    
    public static void inicializar(){
        inicializarResponsables();
        inicializarSucursales();
        inicializarMarcas();
    }
}
