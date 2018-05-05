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
public class Asociado extends Persona {

    public int numero;
    public boolean estado;

    public Asociado(int numero, boolean estado, String nombre, long cedula) {
        super(nombre, cedula);
        this.numero = numero;
        this.estado = estado;
    }

    public Asociado() {
        super();
        numero = 0;
        estado = false;
    }

    public int getNumero() {
        return numero;
    }

    public boolean isEstado() {
        return estado;
    }

    public String getNombre() {
        return nombre;
    }

    public long getCedula() {
        return cedula;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCedula(long cedula) {
        this.cedula = cedula;
    }

}
