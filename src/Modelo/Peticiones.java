package Modelo;

import DataAcces.AccesoBD;

/**
 *
 * @author Diego García
 */
public class Peticiones {

    private AccesoBD acces;

    public Peticiones() {
        acces = new AccesoBD();
    }

    public boolean login(String usuario, String pass, boolean tipo) {
        return acces.login(usuario, pass, tipo);
    }
}
