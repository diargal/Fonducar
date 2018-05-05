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
public class Administrador extends Persona {

    private String pass;

    public Administrador() {
        super();
        pass = "";
    }

    public Administrador(long cedula, String pass) {
        this.cedula = cedula;
        this.pass = pass;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    public long getCedula() {
        return cedula;
    }

    public String getPass() {
        return pass;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCedula(long cedula) {
        this.cedula = cedula;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

}
