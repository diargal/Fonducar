/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Diego García
 */
public class Administrador extends Persona {

    private String pass, usuario;
    private int idAdmin, tipo;

    public Administrador() {
        super();
        pass = "";
        usuario = "";
        idAdmin = 0;
        tipo = 0;
    }

    public Administrador(String nombre, String apellido, long cedula, String pass, String usuario, int tipo) {
        this.cedula = cedula;
        this.apellido = apellido;
        this.pass = pass;
        this.tipo = tipo;
        this.usuario = usuario;
        this.nombre = nombre;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
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
