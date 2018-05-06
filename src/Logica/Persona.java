/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

/**
 *
 * @author Diego García
 */
public class Persona {

    public String nombre;
    public long cedula;
    public int idPersona;

    public Persona() {
        nombre = "";
        cedula = 0;
        idPersona = 0;
    }

    public Persona(String nombre, long cedula, int per) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.idPersona = per;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public long getCedula() {
        return cedula;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCedula(long cedula) {
        this.cedula = cedula;
    }

    @Override
    public String toString() {
        return "Persona{" + "nombre=" + nombre + ", cedula=" + cedula + ", idPersona=" + idPersona + '}';
    }

}
