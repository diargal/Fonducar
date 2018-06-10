package Vistas.Controlador;

import Vistas.Carga;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diego García
 */
public class hiloCarga extends Thread {

    private Carga carga;
    public boolean ejecutar;

    public hiloCarga() {
        super();
        carga = new Carga(null, true);
        ejecutar = true;
        this.carga.setVisible(true);
    }

    @Override
    public void run() {

        this.carga.setVisible(true);
    }

    public void terminar() {
        System.out.println("mirala");
        this.carga.setVisible(false);

    }
}
