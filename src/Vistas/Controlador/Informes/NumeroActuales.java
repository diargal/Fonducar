/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas.Controlador.Informes;

/**
 *
 * @author Diego García
 */
public class NumeroActuales {

    String nombre;
    String cedula;
    String numero;
    String estado;

    public NumeroActuales(String nombre, String cedula, String numero, String estado) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.numero = numero;
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
