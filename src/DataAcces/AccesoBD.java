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

    public void historialRifa() {
        /*
        select asociados.nombre,historial_rifa.fecha, historial_rifa.cedulaG, historial_rifa.numeroG, 
        historial_rifa.premio from asociados,historial_rifa where asociados.cedula=historial_rifa.cedul
         */
    }

    public void listadeAsociados() { //carga todos los asociados para la asignación de los nuevos números.

        Asociado asociado;

        try {
            ResultSet resultado = resultadoConexion("SELECT * FROM asociados");
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

    private void guardarGanador(long cedula, float premio, int numero, int tipo) {
        Date date = new Date();
        DateFormat fecha = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        System.out.println("Fecha: " + fecha.format(date));
        String ComandoSQL = "INSERT INTO `historial_rifa` (id,fecha,cedulaG,numeroG,premio,tipo_premio) values (" + null + ",'" + fecha.format(date) + "'," + cedula + "," + numero + "," + premio + "," + tipo + ")";

        try {

            conexion = getConnect();
            PreparedStatement prepar = conexion.prepareStatement(ComandoSQL);
            prepar.executeUpdate();
            System.out.println("Se guardó el registro exitosamente.");
            desconectar();
        } catch (java.sql.SQLException er) {
            JOptionPane.showMessageDialog(null, "No se pudo guardar el historial. " + er, "Failed!", JOptionPane.ERROR_MESSAGE);

        }
    }

    public boolean guardarOperacion(String tipo) {

        try {
            Date date = new Date();
            DateFormat fecha = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
            accesoBD.conexion();

            String comandoSQL = "Insert into movimiento (idMovimiento, Fecha, Detalle, idAdministrador) values (" + null + ",?,?,?)";
            conexion = getConnect();
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

    public void guardarAsignacion() { //este método se encargará de guardar los asociados con sus nuevos números
        //y esta será llamada desde la clase ControlAsignacion.
        accesoBD = new AccesoBD();
        desconectar();
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

    public ResultSet historialSorteos() throws SQLException {
        return resultadoConexion("SELECT historial_rifa.fecha, asociados.nombre, historial_rifa.cedulaG,"
                + "historial_rifa.numeroG, historial_rifa.premio, historial_rifa.tipo_premio FROM historial_rifa, "
                + "asociados WHERE asociados.cedula=historial_rifa.cedulaG");
    }

    public ResultSet historialModificaciones() throws SQLException {
        return resultadoConexion("SELECT historial_modificaciones.fecha, historial_modificaciones.cedula_admin, administrador.nombre,"
                + " historial_modificaciones.detalle FROM `historial_modificaciones`, administrador "
                + "WHERE administrador.cedula=historial_modificaciones.cedula_admin");
    }

    public ResultSet numerosActuales() throws SQLException {
        return resultadoConexion("SELECT `nombre`,`cedula`,`numero_rifa` FROM `asociados` WHERE 1 ORDER BY nombre ASC");
    }

    private ResultSet resultadoConexion(String comando) throws SQLException {
        accesoBD.conexion();
        Statement stmt = conexion.createStatement();
        return stmt.executeQuery(comando);

    }

    public String ganador(int numero, float premio, int tipo) {

        try {
            ResultSet resultado = resultadoConexion("SELECT * FROM asociado where numero_rifa= '" + numero + "' and estado=0");
            if (resultado.next()) {
                System.out.println("Ganador: " + resultado.getString(1) + " ganó " + (long) premio);
                guardarGanador(resultado.getLong(1), premio, numero, tipo);
                desconectar();
                return resultado.getString(2) + " ganó " + premio;
            } else {
                return INACTIVO;
            }

        } catch (java.sql.SQLException er) {
            JOptionPane.showMessageDialog(null, "Error al saber el ganador" + er, "Failed!", JOptionPane.ERROR_MESSAGE);
        }
        desconectar();
        return "";
    }

    public boolean guardarAsociados(File file) {
        conexion();
        PreparedStatement ps;
        ResultSet resultado;
        int id = 0;
        Date date = new Date();
        DateFormat fecha = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
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
