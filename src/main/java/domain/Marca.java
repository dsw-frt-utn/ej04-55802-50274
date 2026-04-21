/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

/**
 *
 * @author matia
 */
public class Marca {
    private String nombre;
    private String pais;

    public Marca(String nombre, String pais) {
        this.nombre = nombre;
        this.pais = pais;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public String getPais() {
        return pais;
    }
    
}