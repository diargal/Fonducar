/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import DataAcces.AccesoBD;
import Vistas.ErrorBD;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import vistas.AccesControl;

/**
 *
 * @author Diego García
 */
public class BonoSolidario {

    public static Administrador administrador = new Administrador();
    public static int numerodeSorteos = 0;
    public static AccesoBD accesoBD = new AccesoBD();
    public static final List<Asociado> asociados = new ArrayList<Asociado>();

    public static void main(String[] args) {

        if (accesoBD.getConnect() == null) {
            ErrorBD entrada = new ErrorBD(null, true);
            entrada.setVisible(true);
        } else {
            // TODO code application logic here
            // JFrame.setDefaultLookAndFeelDecorated(true);
            //SubstanceLookAndFeel.setSkin("org.pushingpixels.substance.api.ColorSchemeAssociationKind.class");

            AccesControl inicio = new AccesControl(null, true);
            inicio.setVisible(true);
        }
    }

}
