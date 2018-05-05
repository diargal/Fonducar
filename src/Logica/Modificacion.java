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
public class Modificacion {

    public long id;
    public Administrador admin;
    public String fecha, detalle;

    public Modificacion() {
        id = 0;
        admin = new Administrador();
        fecha = detalle = "";
    }

    public Modificacion(long id, Administrador admin, String fecha, String detalle) {
        this.id = id;
        this.admin = admin;
        this.fecha = fecha;
        this.detalle = detalle;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAdmin(Administrador admin) {
        this.admin = admin;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public long getId() {
        return id;
    }

    public Administrador getAdmin() {
        return admin;
    }

    public String getFecha() {
        return fecha;
    }

    public String getDetalle() {
        return detalle;
    }

}
