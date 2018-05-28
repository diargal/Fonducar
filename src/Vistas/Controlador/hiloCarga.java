package Vistas.Controlador;

import Vistas.Carga;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diego García
 */
public class hiloCarga implements Runnable {

    private Carga carga;
    public boolean ejecutar;

    public boolean isEjecutar() {
        return ejecutar;
    }

    public void setEjecutar(boolean ejecutar) {
        this.ejecutar = ejecutar;
    }

    public hiloCarga() {
        super();
        carga = new Carga(null, true);
        ejecutar = true;
    }

    @Override
    public void run() {
        carga.setVisible(true);
        try {
            Thread.sleep(3500);
        } catch (InterruptedException ex) {
            Logger.getLogger(hiloCarga.class.getName()).log(Level.SEVERE, null, ex);
        }
        carga.dispose();
    }

    public void terminar() {
        ejecutar = false;
        System.out.println("hola");
    }

}
