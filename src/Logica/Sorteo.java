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
        cantidadAsociados = acceso.numerodeAsociados(false);
        random = r1.nextInt(cantidadAsociados);
//        random = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese nùmero: "));
        // System.out.println("Número ganador generado: " + random);
        return random;
    }

    public String ganador(int numero, float premio, int tipo, boolean prueba) {
        return acceso.ganador(numero, premio, tipo, prueba);
    }

    public boolean actividad(String tipo) {
        return acceso.guardarOperacion(tipo);
    }

    public boolean asociarNumeros() {
        /*
        Primero verificaré y haré que haya el mismo número de personas habilitadas y de números habilitados
         */
        int cantidadHabilitados = acceso.numerodeAsociados(true);
        int cantidadNumerosHabiles = acceso.cantidadNumerosHabiles(true);
        int cantidadTotal = acceso.cantidadNumerosHabiles(false);
        if (cantidadHabilitados > cantidadNumerosHabiles) {
            if (cantidadHabilitados >= cantidadTotal) {
                System.out.println("ingresa segundo if");
                acceso.todoNumeroHabilitado();//habilito todos los números
                System.out.println("habilita todos los nùmeros");
                for (int i = cantidadTotal + 1; i <= cantidadHabilitados; i++) {
                    acceso.nuevoNumero(i);//creo más numeros
                    System.out.println("crea el nuevo nùmero");
                }
            } else { //si la cantidad de números habilitados (na) es menor que los que ya existen, sólo habilito hasta el número igual a na.
                for (int i = cantidadNumerosHabiles + 1; i <= cantidadHabilitados; i++) {
                    acceso.setEstadoNumero(i);
                }
            }
        } else if (cantidadHabilitados < cantidadNumerosHabiles) {
        }

        //Descargo todos los ids de los asociados activos
        ArrayList<Integer> array = new ArrayList<>(acceso.idsAsociados());
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
//                r = new Random(System.currentTimeMillis());
//                random = r.nextInt(tamanio);
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
        // System.out.println("Id Persona ---> Número");
        for (int i = 0; i < tamanio; i++) {
            // System.out.println(aux.get(i) + " ---> " + aux2.get(i));
            if (!acceso.asociarNumeros(aux.get(i), aux2.get(i))) {
                return false;
            }
        }
        return true;
    }

    public int estadoAsociado(long cedula) {
        return acceso.estadoAsociado(cedula);
    }

    public boolean cambiarEstado(long cedula, int tipo, String texto) {
        return acceso.cambiarEstado(cedula, tipo, texto);
    }

    public boolean verificarFecha() {
        return acceso.verificarFecha();
    }

}
