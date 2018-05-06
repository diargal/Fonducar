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

    private String pass, usuario;
    private int idAdmin;

    public Administrador() {
        super();
        pass = "";
        usuario = "";
        idAdmin = 0;
    }

    public Administrador(long cedula, String pass, String usuario) {
        this.cedula = cedula;
        this.pass = pass;
        this.usuario = usuario;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
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

    @Override
    public String toString() {
        return "Administrador{" + "pass=" + pass + ", usuario=" + usuario + ", idAdmin=" + idAdmin + '}'
                + super.toString();
    }

}
