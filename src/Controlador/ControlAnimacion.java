/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import static Modelo.BonoSolidario.numerodeSorteos;
import static Modelo.Mensajes.A_RIFA;
import Modelo.Peticiones;
import Modelo.Sorteo;
import Vista.Ganador;
import Vista.MainControl;
import java.awt.Image;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Diego García
 */
public class ControlAnimacion {

    private MainControl main;
//    private Ganador ganador;
    private Sorteo sorteo;
    private Peticiones peticion;

    public ControlAnimacion(MainControl main) {
        this.main = main;
//        ganador = new Ganador(main, true);
        sorteo = new Sorteo();
        peticion = new Peticiones();
    }

    public void generarAnimacion(int numero) {
        String ganador;
        Ganador vistaGanador = new Ganador(main, true);
        Locale locale = new Locale("es", "CO");
        NumberFormat nf = NumberFormat.getCurrencyInstance(locale);

        main.JM1.setEnabled(false);
        main.JM2.setEnabled(false);
        main.JM3.setEnabled(false);
        main.JCBPruebaSorteos.setEnabled(false);
        main.JMIHSorteos.setVisible(false);
        main.JBSorteo.setEnabled(false);

        if (main.JCBPruebaSorteos.isSelected()) {
            ganador = sorteo.ganador(numero, main.premio, main.tipoPremio, true);
        } else {
            ganador = sorteo.ganador(numero, main.premio, main.tipoPremio, false);
        }

        if (ganador.equals("anterior") || ganador.equals("")) {
            generarAnimacion(sorteo.generarSorteo());

        } else {

            int mil = numero / 1000;
            int cent = (numero % 1000) / 100;
            int dec = ((numero % 1000) % 100) / 10;
            int uni = numero % 10;

            if (peticion.numeroAsociadosActivos() > 1000) {
                main.JLBalota1.setIcon(null);
            } else {
                main.JLBalota1.setIcon(seleccionNumero(0));
            }

            if (!main.JCBPruebaSorteos.isSelected()) {
                main.sorteosRealizados++;
                main.JLCuanto.setText("Sorteo " + main.sorteosRealizados + " de " + numerodeSorteos);
                sorteo.actividad(A_RIFA);
            } else {
                sorteo.actividad("Realización de sorteo de prueba");
            }

            main.JLBalota2.setIcon(null);
            main.JLBalota3.setIcon(null);
            main.JLBalota4.setIcon(null);

            controlImagen(uni, main.JLBalota4);
            controlImagen(dec, main.JLBalota3);
            controlImagen(cent, main.JLBalota2);

            if (peticion.numeroAsociadosActivos() > 1000) {
                controlImagen(mil, main.JLBalota1);
            }

            vistaGanador.getJLGanador().setText(ganador);
            vistaGanador.getJLNumGanador().setText("Número ganador: " + mil + cent + dec + uni);
            vistaGanador.getJLPremio().setText(nf.format(main.premio));
            vistaGanador.getJLPremio().setAutoscrolls(true);
            vistaGanador.repaint();

            try {
                //hacemos una pausa antes de mostrar la ventana del ganador.
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ControlAnimacion.class.getName()).log(Level.SEVERE, null, ex);
            }

            vistaGanador.setVisible(true);

            main.JM1.setEnabled(true);
            main.JM2.setEnabled(true);
            main.JM3.setEnabled(true);
            main.JMIHSorteos.setVisible(true);
            main.JCBPruebaSorteos.setEnabled(true);
            main.JBSorteo.setEnabled(true);
        }
    }

    public void controlImagen(int num, JLabel label) {
        double tiempo = Math.floor(Math.random() * (7 - 4 + 1) + 4);
        label.setIcon(seleccionNumero(10));
        main.repaint();
        try {
            Thread.sleep((long) (tiempo * 1000));
        } catch (InterruptedException ex) {
            Logger.getLogger(MainControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        label.setIcon(seleccionNumero(num));
    }

    public ImageIcon seleccionNumero(int numero) {
        switch (numero) {
            case 0:
                return new ImageIcon(new ImageIcon(getClass().getResource("/Imagenes/Pelotas/0.png")).getImage().getScaledInstance(main.JLBalota1.getWidth(), main.JLBalota1.getHeight(), Image.SCALE_DEFAULT));
            case 1:
                return new ImageIcon(new ImageIcon(getClass().getResource("/Imagenes/Pelotas/1.png")).getImage().getScaledInstance(main.JLBalota1.getWidth(), main.JLBalota1.getHeight(), Image.SCALE_DEFAULT));
            case 2:
                return new ImageIcon(new ImageIcon(getClass().getResource("/Imagenes/Pelotas/2.png")).getImage().getScaledInstance(main.JLBalota1.getWidth(), main.JLBalota1.getHeight(), Image.SCALE_DEFAULT));
            case 3:
                return new ImageIcon(new ImageIcon(getClass().getResource("/Imagenes/Pelotas/3.png")).getImage().getScaledInstance(main.JLBalota1.getWidth(), main.JLBalota1.getHeight(), Image.SCALE_DEFAULT));
            case 4:
                return new ImageIcon(new ImageIcon(getClass().getResource("/Imagenes/Pelotas/4.png")).getImage().getScaledInstance(main.JLBalota1.getWidth(), main.JLBalota1.getHeight(), Image.SCALE_DEFAULT));
            case 5:
                return new ImageIcon(new ImageIcon(getClass().getResource("/Imagenes/Pelotas/5.png")).getImage().getScaledInstance(main.JLBalota1.getWidth(), main.JLBalota1.getHeight(), Image.SCALE_DEFAULT));
            case 6:
                return new ImageIcon(new ImageIcon(getClass().getResource("/Imagenes/Pelotas/6.png")).getImage().getScaledInstance(main.JLBalota1.getWidth(), main.JLBalota1.getHeight(), Image.SCALE_DEFAULT));
            case 7:
                return new ImageIcon(new ImageIcon(getClass().getResource("/Imagenes/Pelotas/7.png")).getImage().getScaledInstance(main.JLBalota1.getWidth(), main.JLBalota1.getHeight(), Image.SCALE_DEFAULT));
            case 8:
                return new ImageIcon(new ImageIcon(getClass().getResource("/Imagenes/Pelotas/8.png")).getImage().getScaledInstance(main.JLBalota1.getWidth(), main.JLBalota1.getHeight(), Image.SCALE_DEFAULT));
            case 9:
                return new ImageIcon(new ImageIcon(getClass().getResource("/Imagenes/Pelotas/9.png")).getImage().getScaledInstance(main.JLBalota1.getWidth(), main.JLBalota1.getHeight(), Image.SCALE_DEFAULT));
            default:
                return new ImageIcon(new ImageIcon(getClass().getResource("/Imagenes/Pelotas/Animacion.gif")).getImage().getScaledInstance(main.JLBalota1.getWidth(), main.JLBalota1.getHeight(), Image.SCALE_DEFAULT));
        }
    }
}
