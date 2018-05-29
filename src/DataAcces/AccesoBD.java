package DataAcces;

import Logica.Administrador;
import static Logica.BonoSolidario.accesoBD;
import static Logica.BonoSolidario.administrador;
import static Logica.Mensajes.ADD_ADMIN;
//import static Logica.BonoSolidario.asociados;
import static Logica.Mensajes.A_AGREGARASOCIADOS;
import static Logica.Mensajes.ERRORBDC;
import static Logica.Mensajes.EXISTE;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import java.io.File;
import java.io.IOException;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.InvalidOperationException;

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
    private Date date;
    private DateFormat fechaCompleta;
    private DateFormat fechaAnio;
    private ResultSet resultado;
    private String comandoSQL;
    private PreparedStatement prepar;

    public AccesoBD() {
        this.conexion();
        resultado = null;
        comandoSQL = "";
        prepar = null;
        date = new Date();
        fechaCompleta = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        fechaAnio = new SimpleDateFormat("yyyy");
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

    public boolean consultaAdmin(String usuario, String pass, boolean tipo) {

        try {
            String password = DigestUtils.md5Hex(pass);
            System.out.println(password);
            resultado = resultadoConexion("SELECT A.*, P.nombre, P.cedula FROM `administrador` as A, persona as P WHERE P.idPersona = A.idPersona and A.Password='" + password + "' and A.Usuario='" + usuario + "'");
            if (resultado.next()) {
                if (tipo) {
                    administrador.setIdAdmin(resultado.getInt(1));
                    administrador.setUsuario(resultado.getString(2));
                    administrador.setPass(resultado.getString(3));
                    administrador.setIdPersona(resultado.getInt(4));
                    administrador.setTipo(resultado.getInt(5));
                    administrador.setNombre(resultado.getString(7));
                    administrador.setCedula(resultado.getLong(8));
                }
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

//    public void listadeAsociados() { //carga todos los asociados para la asignación de los nuevos números.
//
//        Asociado asociado;
//
//        try {
//            resultado = resultadoConexion("SELECT * FROM asociado");
//            if (resultado.next()) {
//                asociado = new Asociado();
//                asociado.setCedula(resultado.getLong(1));
//                asociado.setNombre(resultado.getString(2));
//                asociados.add(asociado);
//            }
//
//        } catch (java.sql.SQLException er) {
//            JOptionPane.showMessageDialog(null, "No se pudieron cargar los asociados", "Failed!", JOptionPane.ERROR_MESSAGE);
//        }
//        desconectar();
//
//    }
    private void guardarGanador(long idNumAso, float premio, int tipo) {
        conexion();

        String fecha2 = fechaCompleta.format(date);

        try {

            comandoSQL = "INSERT INTO `sorteo`(`idSorteo`, `Fecha`, `idNumeroAsociado`, `Premio`, `TipoSorteo`) VALUES (" + null + ",'" + fecha2 + "'," + idNumAso + "," + premio + "," + tipo + ")";
            prepar = conexion.prepareStatement(comandoSQL);
            prepar.executeUpdate();

            desconectar();
        } catch (java.sql.SQLException er) {
            JOptionPane.showMessageDialog(null, "No se pudo guardar el historial. " + er, "Failed!", JOptionPane.ERROR_MESSAGE);

        }
    }

    public boolean guardarOperacion(String tipo) {
        conexion();
        try {

            comandoSQL = "Insert into movimiento (idMovimiento, Fecha, Detalle, idAdministrador) values (" + null + ",?,?,?)";
            prepar = conexion.prepareStatement(comandoSQL);
            prepar.setString(1, fechaCompleta.format(date));
            prepar.setString(2, tipo);
            prepar.setLong(3, administrador.getIdAdmin());
            prepar.executeUpdate();
            desconectar();
            return true;
        } catch (Exception e) {
            desconectar();
            return false;
        }
    }

    public int numerodeAsociados() {

        try {
            resultado = resultadoConexion("SELECT count(*) FROM asociado");

            if (resultado.next()) {
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
            resultado = resultadoConexion("SELECT count(*) FROM asociado as A where A.Estado=0");

            if (resultado.next()) {
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
                + "WHERE S.idNumeroAsociado = NA.idNumeroAsociado and NA.idAsociado = A.idAsociado and A.idPersona = P.idPersona order by S.Fecha desc");
    }

    public ResultSet historialModificaciones() throws SQLException {
        return resultadoConexion("SELECT P.Nombre, P.Cedula, M.Fecha, M.Detalle FROM movimiento as M, persona as P, administrador as A "
                + "WHERE M.idAdministrador = A.idAdministrador and A.idPersona = P.idPersona order by M.Fecha desc");
    }

    public ResultSet historialNumeros() throws SQLException {
        return resultadoConexion("SELECT P.Nombre, P.Cedula, NA.idNumero, NA.Fecha "
                + "FROM `numeroasociado` as NA, asociado as A, persona as P "
                + "WHERE NA.idAsociado = A.idAsociado and A.idPersona = P.idPersona order by P.Nombre, NA.Fecha");
    }

    public ResultSet numerosActuales() throws SQLException {
        String fecha2 = fechaAnio.format(date);
        return resultadoConexion("SELECT p.nombre, p.cedula, na.idnumero FROM `numeroasociado` as na, numero as n, persona as p, "
                + "asociado as a WHERE n.estado = 0 and n.idNumero = na.idNumero and "
                + "'" + fecha2 + "' = substring( na.fecha, length(na.fecha)-12 , length(na.fecha)-15 ) "
                + "and na.idAsociado = a.idAsociado and a.idPersona = p.idPersona order by p.nombre desc");
    }

    public ResultSet historialEACON() throws SQLException {
        return resultadoConexion("SELECT p.nombre, p.cedula, i.fecha, i.razon "
                + "FROM `inhabilitacion` as i, persona as p, asociado as a "
                + "WHERE p.idPersona = a.idPersona and a.idAsociado = i.idAsociado and i.estado = 0 order by i.fecha desc");
    }

    public ResultSet historialEASIN() throws SQLException {
        return resultadoConexion("SELECT p.nombre, p.cedula, i.fecha, i.razon "
                + "FROM `inhabilitacion` as i, persona as p, asociado as a "
                + "WHERE p.idPersona = a.idPersona and a.idAsociado = i.idAsociado and i.estado = 1 order by i.fecha desc");
    }

    private ResultSet resultadoConexion(String comando) throws SQLException {
        conexion();
        Statement stmt = conexion.createStatement();
        return stmt.executeQuery(comando);

    }

    public ResultSet historialInhabilitadosActuales() throws SQLException {
        String fecha2 = fechaAnio.format(date);
        return resultadoConexion("SELECT p.nombre, p.cedula, i.fecha, i.razon "
                + "FROM inhabilitacion as i, persona as p, asociado as a "
                + "WHERE p.idPersona = a.idPersona and a.idAsociado = i.idAsociado and i.estado = 1 and '" + fecha2 + "' = "
                + "substring( i.fecha, length(i.fecha)-12 , length(i.fecha)-15 )order by i.fecha");
    }

    public ResultSet historialHabilitadosActuales() throws SQLException {
        String fecha2 = fechaAnio.format(date);
        return resultadoConexion("SELECT p.nombre, p.cedula, i.fecha, i.razon "
                + "FROM inhabilitacion as i, persona as p, asociado as a "
                + "WHERE p.idPersona = a.idPersona and a.idAsociado = i.idAsociado and i.estado = 0 and '" + fecha2 + "' = "
                + "substring( i.fecha, length(i.fecha)-12 , length(i.fecha)-15 )order by i.fecha");
    }

    public boolean numerosAsignados() {
        String fecha2 = fechaAnio.format(date);
        try {
            resultado = resultadoConexion("select na.fecha "
                    + "from numeroasociado as na "
                    + "where '" + fecha2 + "' = substring( na.fecha, length(na.fecha)-12 , length(na.fecha)-15)");
            if (resultado.next()) {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccesoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public String ganador(int numero, float premio, int tipo) {
        try {
            if (tipo == 0) {// si el premio es menor, busco que el número no haya ganado otro en el mismo año.
                /*
        Aquí busco si el número ya sido ganador de un premio menor a lo largo del año
                 */
                resultado = resultadoConexion("Select na2.idNumero from numeroasociado as na2 where na2.idNumero "
                        + "= " + numero + " and na2.idNumero in ( SELECT na.idNumero FROM `sorteo` as s, numeroasociado as na"
                        + " WHERE s.TipoSorteo = 0 and '" + fechaAnio.format(date) + "' = substring( s.fecha, length(s.fecha)-12 ,"
                        + " length(s.fecha)-15 ) and s.idNumeroAsociado = na.idNumeroAsociado)");
                if (resultado.next()) {
                    return "anterior"; //si ha sido ganador, retorno un identificador.
                }
            }

            /*
            luego busco al ganador a partir del número que tiene asociado y que es igual al número que salió en el sorteo
             */
            resultado = resultadoConexion("select p.nombre, p.cedula, na.idnumero, na.idnumeroasociado "
                    + "from numeroasociado as na, numero as n, asociado as a, persona as p "
                    + "where n.estado = 0 and n.idnumero = na.idnumero and '" + fechaAnio.format(date) + "' = "
                    + "substring( na.fecha, length(na.fecha)-12 , length(na.fecha)-15 ) and na.idasociado = a.idasociado "
                    + "and a.idpersona = p.idpersona and na.idnumero = " + numero + "");
            if (resultado.next()) {
                guardarGanador(resultado.getInt(4), premio, tipo);
                return resultado.getString(1);
            }
        } catch (java.sql.SQLException er) {
            JOptionPane.showMessageDialog(null, "Error al saber el ganador" + er, "Failed!", JOptionPane.ERROR_MESSAGE);
        }
        desconectar();
        return "";
    }

    public boolean asociarNumeros(int idAsociado, int idNumero) {
        conexion();
        try {
            prepar = conexion.prepareStatement("INSERT INTO `numeroasociado`(`idNumeroAsociado`, `Fecha`, `idAsociado`, `idNumero`) VALUES(" + null + ",?,?,?)");
            prepar.setString(1, fechaCompleta.format(date));
            prepar.setDouble(2, idAsociado);
            prepar.setDouble(3, idNumero);
            prepar.execute();

            desconectar();

            return true;
        } catch (java.sql.SQLException er) {
            return false;
        }
    }

    public ArrayList<Integer> idsAsociados() {
        ArrayList<Integer> array = new ArrayList<>();
        try {
            resultado = resultadoConexion("SELECT p.nombre, a.idAsociado, na.idnumero "
                    + "FROM numero as n, numeroasociado as na, asociado as a, persona as p "
                    + "WHERE n.estado = 0 and n.idnumero = na.idnumero and '2018' = substring( na.fecha, length(na.fecha)-12 , length(na.fecha)-15 ) "
                    + "and na.idasociado = a.idasociado and a.idpersona = p.idpersona ORDER BY `p`.`Nombre` ASC");
            while (resultado.next()) {
                array.add(resultado.getInt(2));
            }
//            return array;
        } catch (Exception e) {
        }

        return array;
    }

    public boolean guardarAsociados(File file) {
        conexion();
        int id = 0;
        long cedula = 0, numero = 0;
        String nombre = "";

        try {

            XSSFWorkbook wb = new XSSFWorkbook(file);
            XSSFSheet sheet = wb.getSheetAt(0);

            int numFilas = sheet.getLastRowNum();

            for (int a = 1; a <= numFilas; a++) {
                Row fila = sheet.getRow(a);

                prepar = conexion.prepareStatement("INSERT INTO persona (idPersona, nombre, cedula) VALUES(" + null + ",?,?)");
                nombre = fila.getCell(0).getStringCellValue();
                prepar.setString(1, nombre);
                cedula = (long) fila.getCell(1).getNumericCellValue();
                prepar.setLong(2, cedula);
                prepar.execute();

                resultado = resultadoConexion("SELECT P.idPersona FROM persona as P WHERE P.Cedula = '" + cedula + "'");
                if (resultado.next()) {
                    id = resultado.getInt(1);
                }

                prepar = conexion.prepareStatement("Insert into asociado (idAsociado, Estado, idPersona) values (" + null + ",0,?)");
                prepar.setDouble(1, id);
                prepar.execute();

                resultado = resultadoConexion("select A.idAsociado from asociado as A where A.idPersona = '" + id + "'");
                if (resultado.next()) {
                    id = resultado.getInt(1);
                }

                prepar = conexion.prepareStatement("Insert into numero (idNumero, Estado) values (?," + 0 + ")");
                numero = (long) fila.getCell(2).getNumericCellValue();
                prepar.setDouble(1, numero);
                prepar.execute();

                prepar = conexion.prepareStatement("Insert into numeroasociado (idNumeroAsociado, Fecha, idAsociado, idNumero) values (" + null + ",?,?,?)");
                prepar.setString(1, fechaCompleta.format(date) + "");
                prepar.setDouble(2, id);
                prepar.setDouble(3, numero);
                prepar.execute();
            }

            guardarOperacion(A_AGREGARASOCIADOS);

            //conexion.close();
            desconectar();

            return true;
        } catch (IOException | InvalidFormatException ex) {
        } catch (SQLException es) {
            JOptionPane.showMessageDialog(null, EXISTE + cedula, "Coincidencia en registro.", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalStateException | InvalidOperationException de) {
            JOptionPane.showMessageDialog(null, ERRORBDC, "Error de lectura", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public int estadoAsociado(long cedula) {
        try {
            resultado = resultadoConexion("SELECT p.Nombre, a.idAsociado FROM `asociado` as a, persona as p "
                    + "WHERE a.estado = 0 and a.idPersona = p.idPersona and p.Cedula = '" + cedula + "'");
            if (resultado.next()) {
                desconectar();
                return 1;
            }

            resultado = resultadoConexion("SELECT p.Nombre, p.cedula FROM `asociado` as a, persona as p, inhabilitacion as i "
                    + "WHERE a.estado = 1 and a.idPersona = p.idPersona and p.Cedula = '" + cedula + "' and i.idAsociado = i.idAsociado and i.estado=0");
            if (resultado.next()) {
                desconectar();
                return 2;
            }

            resultado = resultadoConexion("SELECT p.Nombre, p.cedula FROM `asociado` as a, persona as p, inhabilitacion as i "
                    + "WHERE a.estado = 1 and a.idPersona = p.idPersona and p.Cedula = '" + cedula + "' and i.idAsociado = i.idAsociado and i.estado=1");
            if (resultado.next()) {
                desconectar();
                return 3;
            }

        } catch (SQLException ex) {
        }
        return 0;
    }

    public boolean cambiarEstado(long cedula, int tipo, String razon) {

        try {
            conexion();

            if (tipo == 2 || tipo == 3) {
                prepar = conexion.prepareStatement("UPDATE `asociado`, persona SET asociado.Estado = 1 "
                        + "WHERE persona.cedula = ? and persona.idPersona = asociado.idPersona");
                prepar.setDouble(1, cedula);

                if (prepar.executeUpdate() == 0) {
                    desconectar();
                    return false;
                } else {
                    resultado = resultadoConexion("SELECT a.idAsociado FROM `asociado` as a, persona as p "
                            + "WHERE a.idPersona = p.idPersona and p.Cedula ='" + cedula + "'");
                    if (resultado.next()) {
                        prepar = conexion.prepareStatement("INSERT INTO `inhabilitacion`(`idInhabilitacion`, `Razon`, `Fecha`, `Estado`, `idAsociado`) "
                                + "VALUES (" + null + ",?,?,?,?)");
                        prepar.setString(1, razon);
                        prepar.setString(2, fechaCompleta.format(date));
                        prepar.setInt(4, resultado.getInt(1));

                        switch (tipo) {
                            case 2:
                                prepar.setInt(3, 0);
                                prepar.execute();
                                desconectar();
                                return true;
                            case 3:
                                prepar.setInt(3, 1);
                                prepar.execute();
                                desconectar();
                                return modificarEstadoNumero(cedula, 1);
                        }
                    }
                }
            } else {
                modificarEstadoNumero(cedula, 0);

                prepar = conexion.prepareStatement("UPDATE `asociado`, persona SET asociado.Estado = 0 "
                        + "WHERE persona.cedula = ? and persona.idPersona = asociado.idPersona");
                prepar.setDouble(1, cedula);
                desconectar();
                return prepar.executeUpdate() != 0;
            }
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
        return false;
    }

    public boolean modificarEstadoNumero(long cedula, int estado) throws SQLException {
        resultado = resultadoConexion("SELECT na.idNumeroAsociado, na.idAsociado, na.idNumero, na.fecha "
                + "FROM `numeroasociado` as na, asociado as a, persona as p "
                + "WHERE na.idAsociado = a.idAsociado and p.idPersona = a.idPersona and p.cedula = '" + cedula + "' order by na.fecha desc limit 1");
        if (resultado.next()) {
            prepar = conexion.prepareStatement("UPDATE `numero` as n SET n.Estado = ? WHERE n.idNumero = ?");
            prepar.setInt(1, estado);
            prepar.setInt(2, resultado.getInt(3));
            return prepar.executeUpdate() != 0;
        }
        return false;
    }

    public boolean verificarFecha() {
        String fecha2 = fechaAnio.format(date);
        try {
            resultado = resultadoConexion("SELECT na.fecha FROM `numeroasociado` as na WHERE '" + fecha2 + "' = substring( na.fecha, length(na.fecha)-12 , length(na.fecha)-15 )");
            if (resultado.next()) {
                desconectar();
                return false;
            }
        } catch (SQLException es) {
        }
        desconectar();
        return true;
    }

    public ResultSet verificarsihayInhabilitados() throws SQLException {
        int anioAnterior = Integer.parseInt(fechaAnio.format(date)) - 1;
        System.out.println(anioAnterior);
        return resultadoConexion("SELECT p.nombre, p.cedula, a.idAsociado "
                + "FROM inhabilitacion as i, asociado as a, persona as p "
                + "WHERE a.idasociado = i.idasociado and '" + anioAnterior + "' = substring( i.fecha, length(i.fecha)-12 , length(i.fecha)-15 ) "
                + "and i.estado = 0 and p.idpersona = a.idasociado");
    }

    public boolean guardarAdministrador(Administrador admin) {
        try {
            int idPersona = 0;
            Long cedula = (long) admin.getCedula();

            /*
            Verifico si el usuario no concuerda con otro ya utilizado.
             */
            resultado = resultadoConexion("Select a.idAdministrador "
                    + "from administrador as a "
                    + "where a.usuario = '" + admin.getUsuario() + "'");

            if (resultado.next()) {
                desconectar();
                return false;
            }

            /*
            Ingreso una nueva persona.
             */
            prepar = conexion.prepareStatement("INSERT INTO persona (idPersona, nombre, cedula) VALUES(" + null + ",?,?)");
            prepar.setString(1, admin.getNombre());
            prepar.setLong(2, cedula);
            try {
                prepar.execute();
            } catch (SQLException ee) {
                return false;
            }

            /*
            Obtengo el ID de la persona para poder insertar un nuevo admin.
             */
            resultado = resultadoConexion("Select p.idPersona "
                    + "from persona as p "
                    + "where p.cedula = " + cedula);

            if (resultado.next()) {
                idPersona = resultado.getInt(1);
            }

            /*
            Ingreso un nuevo administrador.
             */
            prepar = conexion.prepareStatement("INSERT INTO `administrador`(`idAdministrador`, `Usuario`, `Password`, `idPersona`, `tipo`) VALUES (" + null + ",?,?,?,?)");
            prepar.setString(1, admin.getUsuario());
            String password = DigestUtils.md5Hex(admin.getPass());
            prepar.setString(2, password);
            prepar.setInt(3, idPersona);
            prepar.setInt(4, 0);
            prepar.execute();
            desconectar();
            guardarOperacion(ADD_ADMIN);
            return true;
        } catch (MySQLIntegrityConstraintViolationException er) {
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(AccesoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        desconectar();
        return false;
    }

    public ResultSet verAdministradores() throws SQLException {
        return resultadoConexion("SELECT p.nombre, p.cedula FROM `administrador` as a, persona as p WHERE a.idPersona = p.idPersona and a.tipo = 0");
    }

}
