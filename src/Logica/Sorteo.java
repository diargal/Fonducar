/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import DataAcces.AccesoBD;
import java.util.ArrayList;
import java.util.Random;

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
        Random r1 = new Random(System.currentTimeMillis());
        cantidadAsociados = acceso.numerodeAsociados();
        random = r1.nextInt(cantidadAsociados);
        System.out.println("Random: " + random);
        return random;
    }

    public String ganador(int numero, float premio, int tipo) {
        return acceso.ganador(numero, premio, tipo);
    }

    public boolean actividad(String tipo) {
        return acceso.guardarOperacion(tipo);
    }

    public boolean asociarNumeros() {
        ArrayList<Integer> array = new ArrayList<>(acceso.idsAsociados());
        ArrayList<Integer> aux = new ArrayList<>(array);
        ArrayList<Integer> aux2 = new ArrayList<>(array);;
        Random r;
        int tamanio = array.size();

        for (int i = 0; i < tamanio; i++) {
            aux.set(i, 0);
            aux2.set(i, 0);
        }

        for (int i = 0; i < tamanio; i++) {
            do {
//                random = (int) Math.floor(Math.random() * tamanio);
                r = new Random(System.currentTimeMillis());
                random = r.nextInt(tamanio);
                //System.out.println("Puesto: " + puesto);
//                System.out.println("Puesto generado: " + puesto);
            } while (aux.get(random) != 0);
            // System.out.println("Puesto aceptado: " + random);
            aux.set(random, array.get(i));
            array.set(i, i + 1);
        }
        random = 0;
        for (int i = 0; i < tamanio; i++) {
            do {
//                r = new Random(System.currentTimeMillis());
//                random = r.nextInt(tamanio);
                random = (int) Math.floor(Math.random() * tamanio);
            } while (aux2.get(random) != 0);
            aux2.set(random, array.get(i));
        }
        System.out.println("Id Persona ---> Número");
        for (int i = 0; i < tamanio; i++) {
            System.out.println(aux.get(i) + " ---> " + aux2.get(i));
            if (!acceso.asociarNumeros(aux.get(i), aux2.get(i))) {
                return false;
            }
        }

        return true;
    }

}
