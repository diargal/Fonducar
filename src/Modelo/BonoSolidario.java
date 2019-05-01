package Modelo;

import DataAcces.AccesoBD;
import Vista.ErrorBD;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import Vista.AccesControl;
import Vista.MainControl;
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

    public static void main(String[] args) {

        try {
            /* directorio/ejecutable es el path del ejecutable y un nombre */
            ServerSocket SERVER_SOCKET;
            Runtime p = Runtime.getRuntime();

            p.exec("C:\\wamp\\wampmanager.exe");
            try {
                Thread.sleep(8000);
            } catch (InterruptedException ex) {
                Logger.getLogger(BonoSolidario.class.getName()).log(Level.SEVERE, null, ex);
            }

            AccesoBD accesoBD = new AccesoBD();

            if (accesoBD.getConnect() == null) {
                ErrorBD entrada = new ErrorBD(null, true);
                entrada.setVisible(true);
            } else {
                try {
                    UIManager.setLookAndFeel("com.jgoodies.looks.plastic.PlasticLookAndFeel");
                    JFrame.setDefaultLookAndFeelDecorated(true);
                    SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.SaharaSkin");
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
        } catch (IOException e) {
            /* Se lanza una excepción si no se encuentra en ejecutable o el fichero no es ejecutable. */
            JOptionPane.showMessageDialog(null, "Debe iniciar el programa como administrador");
            System.exit(0);
        }

    }

}
