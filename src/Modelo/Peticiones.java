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
    private boolean rs;
    private ResultSet rst;

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
        acces.conexion();
        rs = acces.login(usuario, pass, tipo);
        acces.desconectar();
        return rs;
    }

    public boolean numerosAsignados() {
        acces.conexion();
        rs = acces.numerosAsignados();
        acces.desconectar();
        return rs;
    }

    public ResultSet numerosActuales(String info) throws SQLException {
        acces.conexion();
        acces.guardarOperacion(info);
        rst = acces.numerosActuales();
        acces.desconectar();
        return rst;
    }

    public ResultSet historialModificaciones(String info) throws SQLException {
        acces.conexion();
        acces.guardarOperacion(info);
        rst = acces.historialModificaciones();
        acces.desconectar();
        return rst;
    }

    public ResultSet historialSorteos(String info) throws SQLException {
        acces.conexion();
        acces.guardarOperacion(info);
        rst = acces.historialSorteos();
        acces.desconectar();
        return rst;
    }

    public ResultSet historialNumeros(String info) throws SQLException {
        acces.conexion();
        acces.guardarOperacion(info);
        rst = acces.historialNumeros();
        acces.desconectar();
        return rst;
    }

    public ResultSet historialEASIN(String info) throws SQLException {
        acces.conexion();
        acces.guardarOperacion(info);
        rst = acces.historialEASIN();
        acces.desconectar();
        return rst;
    }

    public ResultSet historialEACON(String info) throws SQLException {
        acces.conexion();
        acces.guardarOperacion(info);
        rst = acces.historialEACON();
        acces.desconectar();
        return rst;
    }

    public ResultSet historialHabilitadosActuales(String info) throws SQLException {
        acces.conexion();
        acces.guardarOperacion(info);
        rst = acces.historialHabilitadosActuales();
        acces.desconectar();
        return rst;
    }

    public ResultSet historialInhabilitadosActuales(String info) throws SQLException {
        acces.conexion();
        acces.guardarOperacion(info);
        rst = acces.historialInhabilitadosActuales();
        acces.desconectar();
        return rst;
    }

    public ResultSet verAdministradores() throws SQLException {
        acces.conexion();
        rst = acces.verAdministradores();
        acces.desconectar();
        return rst;
    }

    public boolean asociarNumeros() {

        acces.conexion();

        acces.prepararAsociacion();

        //Descargo todos los ids de los asociados activos
        ArrayList<Integer> array = new ArrayList<>(acces.idsAsociados());
        ArrayList<Integer> aux = new ArrayList<>(array);
        ArrayList<Integer> aux2 = new ArrayList<>(array);
        int tamanio = array.size();
        int random = 0;

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
        acces.conexion();
        rs = acces.guardarAsociados(file, label);
        acces.desconectar();
        return rs;
    }

    public boolean insertarAsociacion(File file, JLabel label) {
        acces.conexion();
        rs = acces.insertarAsociacion(file, label);
        acces.desconectar();
        return rs;
    }

    public int numeroAsociadosActivos() throws Exception {
        acces.conexion();
        int rs = acces.cantidadNumerosHabiles(true);
        acces.desconectar();
        return rs;
    }

    public boolean cambiarEstado(long cedula, int tipo, String texto) {
        acces.conexion();
        rs = acces.cambiarEstado(cedula, tipo, texto);
        acces.desconectar();
        return rs;
    }

    public int estadoAsociado(long cedula) {
        acces.conexion();
        int rs = acces.estadoAsociado(cedula);
        acces.desconectar();
        return rs;
    }

    public ArrayList<String> nombreAsociado(long cedula) {

        acces.conexion();

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

        acces.desconectar();

        return array;
    }

    public ArrayList<String> nombreAdmin(long cedula, boolean reingreso) {
        acces.conexion();

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

        acces.desconectar();

        return datos;
    }

    public boolean modificarAsociado(String n, String m, Long k) {
        acces.conexion();
        rs = acces.modificarAsociado(n, m, k);
        acces.desconectar();
        return rs;
    }

    public int guardarAdministrador(Administrador admin) {
        acces.conexion();
        int i = acces.guardarAdministrador(admin);
        acces.desconectar();
        return i;
    }

    public boolean deleteAdmin(Administrador admin) {
        acces.conexion();
        rs = acces.deleteAdmin(admin);
        acces.desconectar();
        return rs;
    }

    public boolean reingresarAdmin(Administrador admin) {
        acces.conexion();
        rs = acces.reingresarAdmin(admin);
        acces.desconectar();
        return rs;
    }

    public boolean modificarAdmin(Administrador admin) {
        acces.conexion();
        rs = acces.modificarAdmin(admin);
        acces.desconectar();
        return rs;
    }

    public boolean prepararAsociacion() {
        acces.conexion();
        rs = acces.prepararAsociacion();
        acces.desconectar();
        return rs;
    }

    public String fechaBackup() {
        acces.conexion();
        String s = acces.fechaBackup();
        acces.desconectar();
        return s;
    }
}
