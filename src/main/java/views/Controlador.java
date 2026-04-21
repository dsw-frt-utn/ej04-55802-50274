package views;

import data.Persistencia;
import domain.Sucursal;
import domain.Vehiculo;
import domain.VehiculoTipo;
import domain.Marca;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import views.AgregarVehiculosView;

public class Controlador {
    static AgregarVehiculosView vehiculoView = new AgregarVehiculosView();
    static MenuPrincipalView menuView = new MenuPrincipalView();
    
    public static void mostrarMenu () {
        menuView.setVisible(true);
    }
    
    public static ArrayList<VehiculoViewModel> getVehiculos(){
        ArrayList<VehiculoViewModel> vehiculos = new ArrayList<>();
        for(Vehiculo vehiculo : Persistencia.getVehiculos()) {
            vehiculos.add(new VehiculoViewModel(vehiculo));
        }
        return vehiculos;
    }
    
    public static double[] calcularConsumos(Map<String, Double> vehiculos){
        double consumoElectricos = 0;
        double consumoCombustible= 0;
        for(Map.Entry<String, Double> entry : vehiculos.entrySet()){
           double consumo = 0;
           Optional<Vehiculo> vehiculo = Persistencia.getVehiculo(entry.getKey());
           if(vehiculo.isPresent()){
               consumo = vehiculo.get().calcularConsumo(entry.getValue());
               consumoElectricos += vehiculo.get().esDe(VehiculoTipo.ELECTRICO) ? consumo : 0;
               consumoCombustible += vehiculo.get().esDe(VehiculoTipo.COMBUSTIBLE) ? consumo : 0;
           }
        }
        return new double[] {consumoElectricos, consumoCombustible};
    }
    
    public static void iniciarVentanaVehiculo(){
        vehiculoView.getlCampo1().setVisible(false);
        vehiculoView.getTfCampo1().setVisible(false);
        vehiculoView.getTfCampo2().setVisible(false);
        vehiculoView.getlCampo2().setVisible(false);
        vehiculoView.setVisible(true);
        vehiculoView.getComboTipo().addItem("Eléctrico");
        vehiculoView.getComboTipo().addItem("Combustible");
        
        vehiculoView.getComboMarca().removeAllItems();
        vehiculoView.getComboMarca().addItem("-- Seleccione --");
        for(Marca fila : Persistencia.getMarcas()){
            vehiculoView.getComboMarca().addItem(fila.getNombre());
        }
        
        vehiculoView.getComboSucursal().removeAllItems();
        vehiculoView.getComboSucursal().addItem("-- Selecione --");
        for(Sucursal sucursal : Persistencia.getSucursales()){
            vehiculoView.getComboSucursal().addItem(sucursal.getCodigo());
        }
    }
    
    public static void actualizarVentanaVehiculo(){
        String seleccion = (String)vehiculoView.getComboTipo().getSelectedItem();
        switch(seleccion){
            case "Eléctrico":
                vehiculoView.getlCampo1().setText("kw/h:");
                vehiculoView.getlCampo1().setVisible(true);
                vehiculoView.getlCampo2().setVisible(false);
                vehiculoView.getTfCampo1().setVisible(true);
                vehiculoView.getTfCampo2().setVisible(false);
                break;
            case "Combustible":
                vehiculoView.getlCampo1().setText("km/L:");
                vehiculoView.getlCampo2().setText("L extra");
                vehiculoView.getlCampo1().setVisible(true);
                vehiculoView.getlCampo2().setVisible(true);
                vehiculoView.getTfCampo1().setVisible(true);
                vehiculoView.getTfCampo2().setVisible(true);
                break;
            default:
                break;
        }
        
        
    }
    
    public static void guardarVehiculo(){
        String tipo = vehiculoView.getComboTipo().getSelectedItem().toString();
        String patente = vehiculoView.getTfPatente().getText();
        String marcaNombre = vehiculoView.getComboMarca().getSelectedItem().toString();
        String modelo = vehiculoView.getTfModelo().getText();
        int anio = Integer.parseInt(vehiculoView.getTfAnio().getText());
        double capacidad = Double.parseDouble(vehiculoView.getTfCapacidad().getText());
        String sucursalNombre = vehiculoView.getComboSucursal().getSelectedItem().toString();
        Double campo1 = Double.parseDouble(vehiculoView.getTfCampo1().getText());
        
        Sucursal sucursal = null;
        for(Sucursal suc : Persistencia.getSucursales()){
            if(suc.getCodigo().equals(sucursalNombre)){sucursal=suc;};
            break;
        }
        
        Marca marca = null;
        for(Marca marc : Persistencia.getMarcas()){
            if(marc.getNombre().equals(marcaNombre)){
                marca = marc;
                break;
            }
        }   
        if(tipo.equals("Eléctrico")){
            Persistencia.cargarElectrico(patente,marca,modelo,anio,capacidad,sucursal,campo1);
        } else if(tipo.equals("Combustible")){
            Double campo2 = Double.parseDouble(vehiculoView.getTfCampo2().getText());
            Persistencia.cargarCombustible(patente,marca,modelo,anio,capacidad,sucursal,campo1,campo2);
        }
        
        //iniciarVentanaVehiculo();
        ListarVehiculosView ventana = new ListarVehiculosView();
        ventana.setVisible(true);
        System.out.println(Persistencia.getVehiculos());
    }
}
