package Controlador;

/*
Esta clase fue hecha para controlar el JDialog Intro y luego mostrar el JDialog MainControl
 */

import static Modelo.BonoSolidario.administrador;
import Vista.Intro;
import Vista.MainControl;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Diego García
 */
public class ControlIntro {

    private Intro intro;
    private MainControl main;

    public ControlIntro() {
        intro = new Intro();
        main = new MainControl();
    }

    public void mostrarIntro() {
        boolean enabled;
        enabled = administrador.getTipo() == 1;
        intro.setVisible(true);
        Timer timer = new Timer();
        TimerTask tk = new TimerTask() {
            @Override
            public void run() {
                intro.dispose();
                main.getJMSuper().setEnabled(enabled);
                main.cerrarVentana();
                main.setVisible(true);
            }
        };
        timer.schedule(tk, 9000);

    }
}
