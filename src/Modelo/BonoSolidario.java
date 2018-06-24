/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import DataAcces.AccesoBD;
import Vista.ErrorBD;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import Vista.AccesControl;
import java.io.IOException;
import java.net.ServerSocket;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.jvnet.substance.SubstanceLookAndFeel;

/**
 *
 * @author Diego García
 */
public class BonoSolidario {

    public static Administrador administrador = new Administrador();
    public static int numerodeSorteos = 0;
//    public static AccesoBD accesoBD = new AccesoBD();
    private static ServerSocket SERVER_SOCKET;
//    public static final List<Asociado> asociados = new ArrayList<Asociado>();

    public static void main(String[] args) {
        AccesoBD accesoBD = new AccesoBD();

        if (accesoBD.getConnect() == null) {
            ErrorBD entrada = new ErrorBD(null, true);
            entrada.setVisible(true);
        } else {
            try {
                UIManager.setLookAndFeel("com.jgoodies.looks.plastic.PlasticLookAndFeel");
                JFrame.setDefaultLookAndFeelDecorated(true);
                SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.SaharaSkin");
//                SubstanceLookAndFeel.setCurrentWatermark(new SubstanceImageWatermark("src/Imagenes/Logo blanco.png"));
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                Logger.getLogger(BonoSolidario.class.getName()).log(Level.SEVERE, null, ex);
            }

            AccesControl inicio = new AccesControl(null, true);
            try {
                SERVER_SOCKET = new ServerSocket(1334);
                inicio.setVisible(true);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(inicio, "Este programa ya se está ejecutando.", "No se puede ejecutar nuevamente.", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
        }
    }

}
