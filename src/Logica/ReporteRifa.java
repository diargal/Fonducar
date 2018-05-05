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
public class ReporteRifa {

    public long id;
    public String fecha;
    public Asociado ganador;
    public float premio;

    public ReporteRifa() {
        id = 0;
        fecha = "";
        ganador = new Asociado();
        premio = 0;
    }

    public ReporteRifa(long id, String fecha, Asociado ganador, float premio) {
        this.id = id;
        this.fecha = fecha;
        this.ganador = ganador;
        this.premio = premio;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setGanador(Asociado ganador) {
        this.ganador = ganador;
    }

    public void setPremio(float premio) {
        this.premio = premio;
    }

    public long getId() {
        return id;
    }

    public String getFecha() {
        return fecha;
    }

    public Asociado getGanador() {
        return ganador;
    }

    public float getPremio() {
        return premio;
    }

}
