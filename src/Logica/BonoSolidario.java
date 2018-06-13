/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import DataAcces.AccesoBD;
import Vistas.ErrorBD;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import Vistas.AccesControl;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.watermark.SubstanceImageWatermark;

/**
 *
 * @author Diego García
 */
public class BonoSolidario {

    public static Administrador administrador = new Administrador();
    public static int numerodeSorteos = 0;
    public static AccesoBD accesoBD = new AccesoBD();
    private static ServerSocket SERVER_SOCKET;
//    public static final List<Asociado> asociados = new ArrayList<Asociado>();

    public static void main(String[] args) {

        if (accesoBD.getConnect() == null) {
            ErrorBD entrada = new ErrorBD(null, true);
            entrada.setVisible(true);
        } else {
            try {
//                UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//                UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
//                UIManager.setLookAndFeel("UpperEssential.UpperEssentialLookAndFeel");
                //Plastic3DLookAndFeel.setPlasticTheme(new DarkStar());
                UIManager.setLookAndFeel("com.jgoodies.looks.plastic.PlasticLookAndFeel");
                JFrame.setDefaultLookAndFeelDecorated(true);
//                SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.RavenGraphiteSkin");
                SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.SaharaSkin");
//                SubstanceLookAndFeel.setCurrentTheme("org.jvnet.substance.theme.SubstanceSteelBlueTheme");
//                SubstanceLookAndFeel.setCurrentTheme("org.jvnet.substance.theme.SubstanceCremeTheme");
                SubstanceLookAndFeel.setCurrentWatermark(new SubstanceImageWatermark("src/Imagenes/Logo blanco.png"));
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
