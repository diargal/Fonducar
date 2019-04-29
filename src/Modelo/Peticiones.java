package Modelo;

import DataAcces.AccesoBD;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JLabel;

/**
 *
 * @author Diego García
 */
public class Peticiones {

    private AccesoBD acces;

    public Peticiones() {
        acces = new AccesoBD();
    }

    public AccesoBD getAcces() {
        return acces;
    }

    public void setAcces(AccesoBD acces) {
        this.acces = acces;
    }

    public boolean login(String usuario, String pass, boolean tipo) {
        return acces.login(usuario, pass, tipo);
    }

    public boolean numerosAsignados() {
        return acces.numerosAsignados();
    }

    public ResultSet numerosActuales(String info) throws SQLException {
        acces.guardarOperacion(info);
        return acces.numerosActuales();
    }

    public ResultSet historialModificaciones(String info) throws SQLException {
        acces.guardarOperacion(info);
        return acces.historialModificaciones();
    }

    public ResultSet historialSorteos(String info) throws SQLException {
        acces.guardarOperacion(info);
        return acces.historialSorteos();
    }

    public ResultSet historialNumeros(String info) throws SQLException {
        acces.guardarOperacion(info);
        return acces.historialNumeros();
    }

    public ResultSet historialEASIN(String info) throws SQLException {
        acces.guardarOperacion(info);
        return acces.historialEASIN();
    }

    public ResultSet historialEACON(String info) throws SQLException {
        acces.guardarOperacion(info);
        return acces.historialEACON();
    }

    public ResultSet historialHabilitadosActuales(String info) throws SQLException {
        acces.guardarOperacion(info);
        return acces.historialHabilitadosActuales();
    }

    public ResultSet historialInhabilitadosActuales(String info) throws SQLException {
        acces.guardarOperacion(info);
        return acces.historialInhabilitadosActuales();
    }

    public ResultSet verAdministradores() throws SQLException {
        return acces.verAdministradores();
    }

    public boolean asociarNumeros() {

        /*
        Primero verificaré y haré que haya el mismo número de personas habilitadas y de números habilitados
         */
        acces.conexion();
        int cantidadHabilitados = acces.numerodeAsociados(true);
        int cantidadNumerosHabiles = acces.cantidadNumerosHabiles(true);
        int cantidadTotal = acces.cantidadNumerosHabiles(false);
        int random = 0;

        if (cantidadHabilitados > cantidadNumerosHabiles) {
            if (cantidadHabilitados >= cantidadTotal) {
                acces.todoNumeroHabilitado();//habilito todos los números
                for (int i = cantidadTotal + 1; i <= cantidadHabilitados; i++) {
                    acces.nuevoNumero(i);//creo más numeros
                }
            } else { //si la cantidad de números habilitados (na) es menor que los que ya existen, sólo habilito hasta el número igual a na.
                for (int i = cantidadNumerosHabiles + 1; i <= cantidadHabilitados; i++) {
                    acces.setEstadoNumero(i);
                }
            }
        }

        //Descargo todos los ids de los asociados activos
        ArrayList<Integer> array = new ArrayList<>(acces.idsAsociados());
        ArrayList<Integer> aux = new ArrayList<>(array);
        ArrayList<Integer> aux2 = new ArrayList<>(array);
        int tamanio = array.size();

        for (int i = 0; i < tamanio; i++) {
            aux.set(i, 0);
            aux2.set(i, 0);
        }

        for (int i = 0; i < tamanio; i++) {
            /*
        Con el siguiente do-while, ordeno aleatoriamente los ids de las personas, en el array aux
             */
            do {
                random = (int) Math.floor(Math.random() * tamanio);
            } while (aux.get(random) != 0);// mientras la posición esté ocupada, creo un nuevo valor
            aux.set(random, array.get(i));

            /*
        En este otro do-while, guardo aleatoriamente los números del 1 hasta la cantidad de asociados, en aux2
             */
            do {
                random = (int) Math.floor(Math.random() * tamanio);
            } while (aux2.get(random) != 0);
            aux2.set(random, i + 1);
        }

        for (int i = 0; i < tamanio; i++) {
            if (!acces.asociarNumeros(aux.get(i), aux2.get(i))) {
                return false;
            }
        }
        acces.desconectar();
        return true;
    }

    public boolean guardarAsociados(File file, JLabel label) {
        return acces.guardarAsociados(file, label);
    }

    public boolean insertarAsociacion(File file, JLabel label) {
        return acces.insertarAsociacion(file, label);
    }

    public int numeroAsociadosActivos() throws Exception {
        return acces.numeroAsociadosActivos();
    }

    public boolean cambiarEstado(long cedula, int tipo, String texto) {
        return acces.cambiarEstado(cedula, tipo, texto);
    }

    public int estadoAsociado(long cedula) {
        return acces.estadoAsociado(cedula);
    }

    public ArrayList<String> nombreAsociado(long cedula) {
        ArrayList<String> array = new ArrayList<>();
        try {
            ResultSet resultado = acces.idAsociado(cedula);
//            acces.desconectar();
            if (resultado.next()) {
                array.add(resultado.getString(2));
                array.add(resultado.getString(3));
            }
        } catch (SQLException ex) {
        }
        return array;
    }

    public ArrayList<String> nombreAdmin(long cedula, boolean reingreso) {
//        acces.conexion();
        ArrayList<String> datos = new ArrayList<>();
        try {
            ResultSet resultado = acces.nombreAdmin(cedula, reingreso);
            if (resultado.next()) {
                System.out.println("resultado: " + resultado.getString(1));
                datos.add(resultado.getString(1));
                datos.add(resultado.getString(2));
                datos.add(resultado.getString(3));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
//        acces.desconectar();
        return datos;
    }

    public boolean modificarAsociado(String n, String m, Long k) {
        return acces.modificarAsociado(n, m, k);
    }

    public int guardarAdministrador(Administrador admin) {
        return acces.guardarAdministrador(admin);
    }

    public boolean deleteAdmin(Administrador admin) {
        return acces.deleteAdmin(admin);
    }

    public boolean reingresarAdmin(Administrador admin) {
        return acces.reingresarAdmin(admin);
    }

    public boolean modificarAdmin(Administrador admin) {
        return acces.modificarAdmin(admin);
    }

    public boolean prepararAsociacion() {
        return acces.prepararAsociacion();
    }

    public String fechaBackup() {
        return acces.fechaBackup();
    }
}
