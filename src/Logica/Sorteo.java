/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import DataAcces.AccesoBD;

/**
 *
 * @author Diego García
 */
public class Sorteo {

    private int random;
    private int cantidadAsociados;
    private AccesoBD acceso;

    public Sorteo() {
        this.acceso = new AccesoBD();
        random = 0;
        cantidadAsociados = 0;
    }

    public Sorteo(int random, int cantidadAsociados) {
        this.random = random;
        this.cantidadAsociados = cantidadAsociados;
    }

    public int getRandom() {
        return random;
    }

    public int getCantidadAsociados() {
        return cantidadAsociados;
    }

    public void setRandom(int random) {
        this.random = random;
    }

    public void setCantidadAsociados(int cantidadAsociados) {
        this.cantidadAsociados = cantidadAsociados;
    }

    public int generarSorteo() {
        cantidadAsociados = acceso.numerodeAsociados();
        random = (int) (Math.random() * cantidadAsociados) + 1;
        System.out.println("Random: " + random);
        return random;
    }

    public String ganador(int numero, float premio, int tipo) {
        return acceso.ganador(numero, premio, tipo);
    }

    public boolean actividad(String tipo) {
        return acceso.guardarOperacion(tipo);
    }

}
