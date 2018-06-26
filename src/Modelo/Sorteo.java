/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import DataAcces.AccesoBD;
import java.util.Random;

/**
 *
 * @author Diego García
 */
public class Sorteo {

    private int random;
    private int cantidadAsociados;
    private AccesoBD accesoBD;
    //private AccesoBD acceso;

    public Sorteo() {
        // this.acceso = new AccesoBD();
        random = 0;
        cantidadAsociados = 0;
        accesoBD = new AccesoBD();
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

    /*
    Crea un aleatorio que indica el número ganador.
     */
    public int generarSorteo() {
        cantidadAsociados = accesoBD.numerodeAsociados(false);//Obtengo la cantidad de asociados existentes para los sorteos.

        random = (int) Math.floor(Math.random() * (cantidadAsociados - 1 + 1) + 1);// me regresa un nùmero presente en el rango de 1 hasta la cantidad de asociados.

        return random;
    }

    /*
    Encuentro el ganador a partir de una serie de conidiciones
     */
    public String ganador(int numero, float premio, int tipo, boolean prueba) {
        return accesoBD.ganador(numero, premio, tipo, prueba);
    }

    public boolean actividad(String tipo) {
        return accesoBD.guardarOperacion(tipo);
    }

}
