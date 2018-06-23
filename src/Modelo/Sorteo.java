/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import static Modelo.BonoSolidario.accesoBD;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Diego García
 */
public class Sorteo {

    private int random;
    private int cantidadAsociados;
    //private AccesoBD acceso;

    public Sorteo() {
        // this.acceso = new AccesoBD();
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

    public boolean asociarNumeros() {

        /*
        Primero verificaré y haré que haya el mismo número de personas habilitadas y de números habilitados
         */
        int cantidadHabilitados = accesoBD.numerodeAsociados(true);
        int cantidadNumerosHabiles = accesoBD.cantidadNumerosHabiles(true);
        int cantidadTotal = accesoBD.cantidadNumerosHabiles(false);
        if (cantidadHabilitados > cantidadNumerosHabiles) {
            if (cantidadHabilitados >= cantidadTotal) {
                accesoBD.todoNumeroHabilitado();//habilito todos los números
                for (int i = cantidadTotal + 1; i <= cantidadHabilitados; i++) {
                    accesoBD.nuevoNumero(i);//creo más numeros
                }
            } else { //si la cantidad de números habilitados (na) es menor que los que ya existen, sólo habilito hasta el número igual a na.
                for (int i = cantidadNumerosHabiles + 1; i <= cantidadHabilitados; i++) {
                    accesoBD.setEstadoNumero(i);
                }
            }
        } else if (cantidadHabilitados < cantidadNumerosHabiles) {
        }

        //Descargo todos los ids de los asociados activos
        ArrayList<Integer> array = new ArrayList<>(accesoBD.idsAsociados());
        ArrayList<Integer> aux = new ArrayList<>(array);
        ArrayList<Integer> aux2 = new ArrayList<>(array);
        Random r;
        int tamanio = array.size();

        for (int i = 0; i < tamanio; i++) {
            aux.set(i, 0);
            aux2.set(i, 0);
        }

        for (int i = 0; i < tamanio; i++) {
            do {
                random = (int) Math.floor(Math.random() * tamanio);
            } while (aux.get(random) != 0);
            aux.set(random, array.get(i));
            array.set(i, i + 1);
        }
        random = 0;
        for (int i = 0; i < tamanio; i++) {
            do {
                random = (int) Math.floor(Math.random() * tamanio);
            } while (aux2.get(random) != 0);
            aux2.set(random, array.get(i));
        }
        for (int i = 0; i < tamanio; i++) {
            if (!accesoBD.asociarNumeros(aux.get(i), aux2.get(i))) {
                return false;
            }
        }
        accesoBD.desconectar();
        return true;
    }

    public int estadoAsociado(long cedula) {
        return accesoBD.estadoAsociado(cedula);
    }

    public boolean cambiarEstado(long cedula, int tipo, String texto) {
        return accesoBD.cambiarEstado(cedula, tipo, texto);
    }

    public boolean verificarFecha() {
        return accesoBD.verificarFecha();
    }

}
