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

    public int generarSorteo() {
        Random r1 = new Random(System.currentTimeMillis());
        cantidadAsociados = accesoBD.numerodeAsociados(false);
        random = r1.nextInt(cantidadAsociados);
//        random = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese nùmero: "));
        // System.out.println("Número ganador generado: " + random);
        return random;
    }

    public String ganador(int numero, float premio, int tipo, boolean prueba) {
        return accesoBD.ganador(numero, premio, tipo, prueba);
    }

    public boolean actividad(String tipo) {
        return accesoBD.guardarOperacion(tipo);
    }

}
