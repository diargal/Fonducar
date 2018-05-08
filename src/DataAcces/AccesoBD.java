package DataAcces;

import Logica.Asociado;
import static Logica.BonoSolidario.accesoBD;
import static Logica.BonoSolidario.administrador;
import static Logica.BonoSolidario.asociados;
import static Logica.Mensajes.A_AGREGARASOCIADOS;
import static Logica.Mensajes.INACTIVO;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.util.ArrayList;

/**
 *
 * @author Diego García
 */
public class AccesoBD {

    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost/fonducar";
    private static final String login = "root";
    private static final String password = "";
    private static Connection conexion = null;

    public AccesoBD() {
        this.conexion();
    }

    public void conexion() {
        try {
            Class.forName(driver).newInstance();
            conexion = DriverManager.getConnection(url, login, password);
            System.out.println("Se acaba de acceder a la BD de fonducar!");
        } catch (Exception exc) {
            System.out.println("Error al tratar de abrir la base de datos");
        }
    }

    public Connection getConnect() {
        return conexion;
    }

    public void desconectar() {
        conexion = null;
        if (conexion == null) {
            System.out.println("Conexión finalizada.");
        }
    }

    public boolean consultaAdmin(String usuario, String pass) {

        try {
            String password = DigestUtils.md5Hex(pass);
            System.out.println(password);
            ResultSet resultado = resultadoConexion("SELECT A.*, P.nombre, P.cedula FROM `administrador` as A, persona as P WHERE P.idPersona = A.idPersona and A.Password='" + password + "' and A.Usuario='" + usuario + "'");
            if (resultado.next()) {
                administrador.setIdAdmin(resultado.getInt(1));
                administrador.setUsuario(resultado.getString(2));
                administrador.setPass(resultado.getString(3));
                administrador.setIdPersona(resultado.getInt(4));
                administrador.setNombre(resultado.getString(5));
                administrador.setCedula(resultado.getLong(6));

                System.out.println("Datos del admin: " + administrador.toString());
                desconectar();
                return true;
            }

        } catch (java.sql.SQLException er) {
            System.out.println(er);
            JOptionPane.showMessageDialog(null, "No se pudo realizar la consulta de Administrador", "Failed!", JOptionPane.ERROR_MESSAGE);
        }
        desconectar();
        return false;
    }

    public void listadeAsociados() { //carga todos los asociados para la asignación de los nuevos números.

        Asociado asociado;

        try {
            ResultSet resultado = resultadoConexion("SELECT * FROM asociado");
            if (resultado.next()) {
                asociado = new Asociado();
                asociado.setCedula(resultado.getLong(1));
                asociado.setNombre(resultado.getString(2));
                asociados.add(asociado);
            }

        } catch (java.sql.SQLException er) {
            JOptionPane.showMessageDialog(null, "No se pudieron cargar los asociados", "Failed!", JOptionPane.ERROR_MESSAGE);
        }
        desconectar();

    }

    private void guardarGanador(long idNumAso, float premio, int tipo) {
        conexion();
        Date date = new Date();
        DateFormat fecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String fecha2 = fecha.format(date);
        System.out.println("Fecha: " + fecha.format(date));
        String ComandoSQL;
        PreparedStatement prepar;

        try {

            ComandoSQL = "INSERT INTO `sorteo`(`idSorteo`, `Fecha`, `idNumeroAsociado`, `Premio`, `TipoSorteo`) VALUES (" + null + ",'" + fecha2 + "'," + idNumAso + "," + premio + "," + tipo + ")";
            prepar = conexion.prepareStatement(ComandoSQL);
            prepar.executeUpdate();

            desconectar();
        } catch (java.sql.SQLException er) {
            JOptionPane.showMessageDialog(null, "No se pudo guardar el historial. " + er, "Failed!", JOptionPane.ERROR_MESSAGE);

        }
    }

    public boolean guardarOperacion(String tipo) {
        conexion();
        Date date = new Date();
        DateFormat fecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String comandoSQL;
        try {

            comandoSQL = "Insert into movimiento (idMovimiento, Fecha, Detalle, idAdministrador) values (" + null + ",?,?,?)";
            PreparedStatement prepar = conexion.prepareStatement(comandoSQL);
            prepar.setString(1, fecha.format(date));
            prepar.setString(2, tipo);
            prepar.setLong(3, administrador.getIdAdmin());
            prepar.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("No se guardò la operaciòn");
            return false;
        }
    }

    public int numerodeAsociados() {

        try {
            ResultSet resultado = resultadoConexion("SELECT count(*) FROM asociado");

            if (resultado.next()) {
                System.out.println("Cantida de asociados: " + resultado.getInt(1));
                desconectar();
                return resultado.getInt(1);
            }

        } catch (java.sql.SQLException er) {
            JOptionPane.showMessageDialog(null, er, "Failed!", JOptionPane.ERROR_MESSAGE);

        }

        desconectar();
        return 0;
    }

    public int numeroAsociadosActivos() {

        try {
            ResultSet resultado = resultadoConexion("SELECT count(*) FROM asociado as A where A.Estado=0");

            if (resultado.next()) {
                System.out.println("Cantida de asociados: " + resultado.getInt(1));
                desconectar();
                return resultado.getInt(1);
            }

        } catch (java.sql.SQLException er) {
            JOptionPane.showMessageDialog(null, er, "Failed!", JOptionPane.ERROR_MESSAGE);

        }

        desconectar();
        return 0;
    }

    public ResultSet historialSorteos() throws SQLException {
        return resultadoConexion("SELECT P.Nombre, P.Cedula, S.Fecha, NA.idNumero, S.Premio, S.TipoSorteo "
                + "FROM `sorteo` as S, asociado as A, persona as P, numeroasociado as NA "
                + "WHERE S.idNumeroAsociado = NA.idNumeroAsociado and NA.idAsociado = A.idAsociado and A.idPersona = P.idPersona");
    }

    public ResultSet historialModificaciones() throws SQLException {
        return resultadoConexion("SELECT P.Nombre, P.Cedula, M.Fecha, M.Detalle FROM movimiento as M, persona as P, administrador as A "
                + "WHERE M.idAdministrador = A.idAdministrador and A.idPersona = P.idPersona");
    }

    public ResultSet historialNumeros() throws SQLException {
        return resultadoConexion("SELECT P.Nombre, P.Cedula, NA.idNumero, NA.Fecha "
                + "FROM `numeroasociado` as NA, asociado as A, persona as P "
                + "WHERE NA.idAsociado = A.idAsociado and A.idPersona = P.idPersona");
    }

    public ResultSet numerosActuales() throws SQLException {
        return resultadoConexion("SELECT P.Nombre, P.Cedula, ("
                + "select NA.idNumero "
                + "from numeroasociado as NA, asociado as A2 "
                + "where NA.idAsociado = A2.idAsociado and A.idAsociado = A2.idAsociado order by NA.Fecha desc limit 1) as Numero "
                + "FROM `asociado` as A, persona as P "
                + "WHERE P.idPersona = A.idPersona and A.Estado = 0 order by P.Nombre asc");
    }

    private ResultSet resultadoConexion(String comando) throws SQLException {
        accesoBD.conexion();
        Statement stmt = conexion.createStatement();
        return stmt.executeQuery(comando);

    }

    public String ganador(int numero, float premio, int tipo) {

        try {
            ResultSet resultado = resultadoConexion("SELECT P.Nombre, NA.* FROM `numeroasociado` as NA, asociado as A, "
                    + "numero as N, persona as P WHERE NA.idNumero ='" + numero + "' and NA.idAsociado = A.idAsociado "
                    + "and A.Estado = 0 and NA.idNumero = N.idNumero and N.Estado = 0 and A.idPersona = P.idPersona "
                    + "order by NA.Fecha desc limit 1");
            if (resultado.next()) {
                System.out.println("Ganador: " + resultado.getString(1) + " ganó " + (long) premio);
                guardarGanador(resultado.getInt(2), premio, tipo);
                desconectar();
                return resultado.getString(1);
            } else {
                return INACTIVO;
            }

        } catch (java.sql.SQLException er) {
            JOptionPane.showMessageDialog(null, "Error al saber el ganador" + er, "Failed!", JOptionPane.ERROR_MESSAGE);
        }
        desconectar();
        return "";
    }

    public boolean asociarNumeros(int idAsociado, int idNumero) {
        conexion();
        PreparedStatement ps;
        Date date = new Date();
        DateFormat fecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        try {
            ps = conexion.prepareStatement("INSERT INTO `numeroasociado`(`idNumeroAsociado`, `Fecha`, `idAsociado`, `idNumero`) VALUES(" + null + ",?,?,?)");
            ps.setString(1, fecha.format(date));
            ps.setDouble(2, idAsociado);
            ps.setDouble(3, idNumero);
            ps.execute();

            desconectar();

            return true;
        } catch (java.sql.SQLException er) {
            return false;
        }
    }

    public ArrayList<Integer> idsAsociados() {
        ArrayList<Integer> array = new ArrayList<>();
        try {
            ResultSet resultado = resultadoConexion("SELECT A.idAsociado, P.Nombre FROM `asociado` as A, persona as P WHERE A.Estado = 0 and P.idPersona = A.idPersona order by P.Nombre asc");
            while (resultado.next()) {
                array.add(resultado.getInt(1));
            }
//            return array;
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        return array;
    }

    public boolean guardarAsociados(File file) {
        conexion();
        PreparedStatement ps;
        ResultSet resultado;
        int id = 0;
        Date date = new Date();
        DateFormat fecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        long cedula = 0, numero = 0;
        String nombre = "";

        try {

            XSSFWorkbook wb = new XSSFWorkbook(file);
            XSSFSheet sheet = wb.getSheetAt(0);

            int numFilas = sheet.getLastRowNum();

            for (int a = 1; a <= numFilas; a++) {
                Row fila = sheet.getRow(a);

                ps = conexion.prepareStatement("INSERT INTO persona (idPersona, nombre, cedula) VALUES(" + null + ",?,?)");
                nombre = fila.getCell(0).getStringCellValue();
                ps.setString(1, nombre);
                cedula = Long.parseLong(String.valueOf((int) fila.getCell(1).getNumericCellValue()));
                ps.setDouble(2, cedula);
                ps.execute();

                System.out.println("Cédula => " + cedula);

                resultado = resultadoConexion("SELECT P.idPersona FROM persona as P WHERE P.Cedula = '" + cedula + "'");
                if (resultado.next()) {
                    System.out.println("Resultado => " + resultado.getInt(1));
                    id = resultado.getInt(1);
                }

                ps = conexion.prepareStatement("Insert into asociado (idAsociado, Estado, idPersona) values (" + null + ",0,?)");
                ps.setDouble(1, id);
                ps.execute();

                resultado = resultadoConexion("select A.idAsociado from asociado as A where A.idPersona = '" + id + "'");
                if (resultado.next()) {
                    id = resultado.getInt(1);
                    System.out.println("Id asociado: " + id);
                }

                ps = conexion.prepareStatement("Insert into numero (idNumero, Estado) values (?," + 0 + ")");
                numero = (long) fila.getCell(2).getNumericCellValue();
                ps.setDouble(1, numero);
                ps.execute();

                ps = conexion.prepareStatement("Insert into numeroasociado (idNumeroAsociado, Fecha, idAsociado, idNumero) values (" + null + ",?,?,?)");
                ps.setString(1, fecha.format(date) + "");
                ps.setDouble(2, id);
                ps.setDouble(3, numero);
                ps.execute();
            }

            guardarOperacion(A_AGREGARASOCIADOS);

            conexion.close();
            return true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, nombre + " de cédula " + cedula + " ya se encuentra en la BD", "Campos existentes en la BD", JOptionPane.ERROR_MESSAGE);
            return false;
        }

    }

}
