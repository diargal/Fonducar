package DataAcces;

import Modelo.Administrador;
import static Modelo.BonoSolidario.administrador;
import static Modelo.Mensajes.ADD_ADMIN;
import static Modelo.Mensajes.A_AGREGARASOCIADOS;
import static Modelo.Mensajes.A_SESION;
import static Modelo.Mensajes.A_SETASOCIADO;
import static Modelo.Mensajes.DELETE_ADMIN;
import static Modelo.Mensajes.ERRORBDC;
import static Modelo.Mensajes.EXISTE;
import static Modelo.Mensajes.REI_ADMIN;
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
import javax.swing.JLabel;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.InvalidOperationException;

/**
 *
 * @author Diego García
 */
public class AccesoBD {

    private final String driver = "com.mysql.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost/fonducarbs";
    private final String login = "root";
    private final String password = "Fonducar**BonoSolidario2018*";
    public static Connection conexion = null;
    private Date date;
    private DateFormat fechaCompleta;
    private DateFormat fechaAnio;
//    private ResultSet resultado;
    private String comandoSQL;
    private PreparedStatement prepar;

    public AccesoBD() {
        this.conexion();
//        resultado = null;
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
//            System.out.println("Se acaba de acceder a la BD de fonducar!");
        } catch (Exception exc) {
//            System.out.println("Error al tratar de abrir la base de datos");
        }
    }

    public Connection getConnect() {
        return conexion;
    }

    public void desconectar() {
        conexion = null;
        if (conexion == null) {
//            System.out.println("Conexión finalizada.");
        }
    }

    /* --------------------------------------------------------------------------------------------------------------------------- */
    public ResultSet resultadoConexion(String comando) throws SQLException {
        Statement stmt = conexion.createStatement();
        return stmt.executeQuery(comando);
    }

    /* -------------------------------------------------------------------------------------------------------------------------- */
 /*
    Operaciones de login
     */
    public boolean login(String usuario, String pass, boolean tipo) {

        try {
            String passw = DigestUtils.md5Hex(pass);
            ResultSet resultado = resultadoConexion("SELECT A.*, P.nombre, P.cedula "
                    + "FROM `administrador` as A, persona as P "
                    + "WHERE P.idPersona = A.idPersona and A.estado = 0 and A.Password='" + passw + "' and A.Usuario='" + usuario + "'");
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
                guardarOperacion(A_SESION);

                return true;
            }

        } catch (java.sql.SQLException er) {
            JOptionPane.showMessageDialog(null, "No se pudo realizar la consulta de Administrador", "Failed!", JOptionPane.ERROR_MESSAGE);
        }

        return false;
    }

    /* ----------------------------------------------------------------------------------------------------------------------- */
 /*
    Operaciones de guardado
     */
    private void guardarGanador(long idNumAso, float premio, int tipo) {
        Date date = new Date();
        fechaCompleta = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String fecha2 = fechaCompleta.format(date);

        try {
            comandoSQL = "INSERT INTO `sorteo`(`idSorteo`, `Fecha`, `idNumeroAsociado`, `Premio`, `TipoSorteo`) VALUES (" + null + ",'" + fecha2 + "'," + idNumAso + "," + premio + "," + tipo + ")";
            prepar = conexion.prepareStatement(comandoSQL);
            prepar.execute();

        } catch (java.sql.SQLException er) {
            JOptionPane.showMessageDialog(null, "No se pudo guardar el historial. " + er, "Failed!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean guardarOperacion(String tipo) {
        try {
            Date date = new Date();
            fechaCompleta = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String fecha2 = fechaCompleta.format(date);

            comandoSQL = "Insert into movimiento (idMovimiento, Fecha, Detalle, idAdministrador) values (" + null + ",?,?,?)";
            prepar = conexion.prepareStatement(comandoSQL);
            prepar.setString(1, fecha2);
            prepar.setString(2, tipo);
            prepar.setLong(3, administrador.getIdAdmin());
            prepar.execute();
//            
            return true;
        } catch (Exception e) {

            return false;
        }
    }

    public boolean guardarAsociados(File file, JLabel label) {

        int id = 0;
        long cedula = 0;
        String nombre, apellido;

        try {

            XSSFWorkbook wb = new XSSFWorkbook(file);
            XSSFSheet sheet = wb.getSheetAt(0);

            int numFilas = sheet.getLastRowNum();

            for (int f = 1; f <= numFilas; f++) {
                int numeroGuardar = 0;
                Row fila = sheet.getRow(f);

                //Leo el archivo y obtengo los valores de cada fila para crear una persona
                prepar = conexion.prepareStatement("INSERT INTO persona (nombre, apellido, cedula) VALUES(?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
                nombre = fila.getCell(0).getStringCellValue().toUpperCase();
                prepar.setString(1, nombre);
                apellido = fila.getCell(1).getStringCellValue().toUpperCase();
                prepar.setString(2, apellido);
                cedula = (long) fila.getCell(2).getNumericCellValue();
                prepar.setLong(3, cedula);

                prepar.executeUpdate();

                ResultSet rs = prepar.getGeneratedKeys();

                //Obtengo el id de la persona creada
                while (rs.next()) {
                    id = rs.getInt(1);
                }

                //Creo el asociado
                prepar = conexion.prepareStatement("Insert into asociado (Estado, idPersona) values (0,?)", PreparedStatement.RETURN_GENERATED_KEYS);
                prepar.setDouble(1, id);
                prepar.executeUpdate();

                rs = prepar.getGeneratedKeys();

                //obtengo el id del nuevo asociado y lo guardo
                if (rs.next()) {
                    id = rs.getInt(1);
                }

                /*
                Ahora verifico si aún no se han asignado los números a todos los asociados.
                 */
                if (numerosAsignados()) {//si hay números asignados

                    numeroGuardar = cantidadNumAsociados() + 1;

                    if (!nuevoNumero(numeroGuardar)) {//si ya el número existía en la tabla numero, debo cambiar su estado a 0
                        prepar = conexion.prepareStatement("UPDATE `numero` as n SET n.Estado = 0 "
                                + "WHERE n.idnumero = ? and n.estado = 1");
                        prepar.setDouble(1, numeroGuardar);
                        prepar.executeUpdate();
                    }

                } else if (asociadosVacia()) {//no han sido asignados y está completamente vacía la tabla asociados
                    numeroGuardar = f;
                    nuevoNumero(numeroGuardar);
                }

                //asocio el nuevo número con el asociado
                if (numeroGuardar > 0) {
                    prepar = conexion.prepareStatement("Insert into numeroasociado (idNumeroAsociado, Fecha, idAsociado, idNumero) values (" + null + ",?,?,?)");
                    prepar.setString(1, fechaCompleta.format(date) + "");
                    prepar.setDouble(2, id);
                    prepar.setDouble(3, numeroGuardar);
                    prepar.executeUpdate();
                }
            }

            guardarOperacion(A_AGREGARASOCIADOS);

            label.setVisible(false);
            return true;

        } catch (IOException | InvalidFormatException ex) {
        } catch (SQLException es) {
            file = null;
            JOptionPane.showMessageDialog(null, EXISTE + cedula, "Coincidencia en registro.", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalStateException | InvalidOperationException de) {
            JOptionPane.showMessageDialog(null, ERRORBDC, "Error de lectura", JOptionPane.ERROR_MESSAGE);
        }
        label.setVisible(false);
        return false;
    }

    public boolean insertarAsociacion(File file, JLabel label) {
        try {

            XSSFWorkbook wb = new XSSFWorkbook(file);
            XSSFSheet sheet = wb.getSheetAt(0);

            int numFilas = sheet.getLastRowNum();

            for (int f = 1; f <= numFilas; f++) {
                Row fila = sheet.getRow(f);

                long cedula = (long) fila.getCell(2).getNumericCellValue();
                int numero = (int) fila.getCell(3).getNumericCellValue();

                ResultSet resultado = idAsociado(cedula);

                try {
                    while (resultado.next()) {

                        prepar = conexion.prepareStatement("INSERT INTO numeroasociado (`idNumeroAsociado`, `Fecha`, `idAsociado`, `idNumero`) VALUES(" + null + ",?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
                        prepar.setString(1, fechaCompleta.format(date));
                        prepar.setInt(2, resultado.getInt(1));
                        prepar.setInt(3, numero);

                        prepar.executeUpdate();
                        ResultSet rs = prepar.getGeneratedKeys();
                    }
                } catch (Exception ss) {
                    JOptionPane.showMessageDialog(null, ss.getMessage());
                    return false;
                }

            }

        } catch (IOException ex) {
            Logger.getLogger(AccesoBD.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return false;
        } catch (InvalidFormatException ex) {
            Logger.getLogger(AccesoBD.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(AccesoBD.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return false;
        }
        return true;
    }

    /* -------------------------------------------------------------------------------------------------------------------------- */
 /*
    Historiales y opciones mostradas en la vista principal
     */
    public ResultSet historialSorteos() throws SQLException {
        return resultadoConexion("SELECT P.Nombre, P.Apellido, P.Cedula, S.Fecha, NA.idNumero, S.Premio, S.TipoSorteo "
                + "FROM `sorteo` as S, asociado as A, persona as P, numeroasociado as NA "
                + "WHERE S.idNumeroAsociado = NA.idNumeroAsociado and NA.idAsociado = A.idAsociado and A.idPersona = P.idPersona order by S.idSorteo desc");
    }

    public ResultSet historialModificaciones() throws SQLException {
        return resultadoConexion("SELECT P.Nombre, P.Apellido, P.Cedula, M.Fecha, M.Detalle FROM movimiento as M, persona as P, administrador as A "
                + "WHERE M.idAdministrador = A.idAdministrador and A.idPersona = P.idPersona order by M.idMovimiento desc");
    }

    public ResultSet historialNumeros() throws SQLException {
        return resultadoConexion("SELECT P.Nombre, P.Apellido, P.Cedula, NA.idNumero, NA.Fecha "
                + "FROM `numeroasociado` as NA, asociado as A, persona as P "
                + "WHERE NA.idAsociado = A.idAsociado and A.idPersona = P.idPersona order by P.Nombre, NA.Fecha");
    }

    public ResultSet numerosActuales() throws SQLException {
        String fecha2 = fechaAnio.format(date);
        return resultadoConexion("SELECT p.nombre, p.apellido, p.cedula, na.idnumero, IF(a.estado = 0, \"Asociado\", \"Ex-asociado con participación\") as estado "
                + "FROM `numeroasociado` as na, numero as n, persona as p, asociado as a "
                + "WHERE n.estado = 0 and n.idNumero = na.idNumero and na.fecha like '%" + fecha2 + "%'"
                + "and na.idAsociado = a.idAsociado and a.idPersona = p.idPersona ORDER BY `p`.`Nombre` ASC, p.apellido asc, p.cedula asc");
    }

    public ResultSet historialEACON() throws SQLException {
        return resultadoConexion("SELECT p.nombre, p.apellido, p.cedula, na.idnumero, i.fecha, i.razon "
                + "FROM `inhabilitacion` as i, persona as p, asociado as a, numeroasociado as na "
                + "WHERE p.idPersona = a.idPersona and a.idAsociado = i.idAsociado and i.estado = 0 and i.razon != 'Asociado deshabilitado por cambio de año' and a.idasociado = na.idasociado "
                + "and substring( na.fecha, length(na.fecha)-12 , length(na.fecha)-15 ) = substring( i.fecha, length(i.fecha)-12 , length(i.fecha)-15 ) "
                + "order by i.idinhabilitacion desc");
    }

    public ResultSet historialEASIN() throws SQLException {
        return resultadoConexion("SELECT p.nombre, p.apellido, p.cedula, na.idnumero, i.fecha, i.razon "
                + "FROM `inhabilitacion` as i, persona as p, asociado as a, numeroasociado as na "
                + "WHERE p.idPersona = a.idPersona and a.idAsociado = i.idAsociado and i.estado = 1 and i.razon != 'Asociado deshabilitado por cambio de año' and a.idasociado = na.idasociado "
                + "and substring( na.fecha, length(na.fecha)-12 , length(na.fecha)-15 ) = substring( i.fecha, length(i.fecha)-12 , length(i.fecha)-15 ) "
                + "order by i.idinhabilitacion desc");
    }

    public ResultSet historialInhabilitadosActuales() throws SQLException {
        String fecha2 = fechaAnio.format(date);
        return resultadoConexion("SELECT p.nombre, p.apellido, p.cedula, na.idnumero, i.fecha, i.razon "
                + "FROM inhabilitacion as i, persona as p, asociado as a, numeroasociado as na "
                + "WHERE p.idPersona = a.idPersona and a.estado =1 and a.idAsociado = i.idAsociado and i.estado = 1 and "
                + "'" + fecha2 + "' = substring( i.fecha, length(i.fecha)-12 , length(i.fecha)-15 ) and na.idasociado = a.idasociado "
                + "and '" + fecha2 + "' = substring( na.fecha, length(na.fecha)-12 , length(na.fecha)-15 ) ORDER BY i.idinhabilitacion DESC");
    }

    public ResultSet historialHabilitadosActuales() throws SQLException {
        String fecha2 = fechaAnio.format(date);
        return resultadoConexion("SELECT p.nombre, p.apellido, p.cedula, na.idnumero, i.fecha, i.razon "
                + "FROM inhabilitacion as i, persona as p, asociado as a, numeroasociado as na "
                + "WHERE p.idPersona = a.idPersona and a.estado =1 and a.idAsociado = i.idAsociado and i.estado = 0 and "
                + "'" + fecha2 + "' = substring( i.fecha, length(i.fecha)-12 , length(i.fecha)-15 ) and na.idasociado = a.idasociado "
                + "and '" + fecha2 + "' = substring( na.fecha, length(na.fecha)-12 , length(na.fecha)-15 ) ORDER BY i.idinhabilitacion DESC");
    }

    public boolean cambiarEstado(long cedula, int tipo, String razon) {

        try {

            if (tipo == 2 || tipo == 3) {
                //cambio el estado del asociado en la tabla asociado, que indica inhabilitación
                prepar = conexion.prepareStatement("UPDATE `asociado`, persona SET asociado.Estado = 1 "
                        + "WHERE persona.cedula = ? and persona.idPersona = asociado.idPersona");
                prepar.setDouble(1, cedula);

                if (prepar.executeUpdate() == 0) {

                    return false;
                } else {
                    //busco el id del asociado que concuerda con la cédula ingresada
                    ResultSet resultado = idAsociado(cedula);
                    if (resultado.next()) {
                        //agrego el id del asociado a la tabla de inhabilitación
                        prepar = conexion.prepareStatement("INSERT INTO `inhabilitacion`(`idInhabilitacion`, `Razon`, `Fecha`, `Estado`, `idAsociado`) "
                                + "VALUES (" + null + ",?,?,?,?)");
                        prepar.setString(1, razon);
                        prepar.setString(2, fechaCompleta.format(date));
                        int idAsociado = resultado.getInt(1);
                        prepar.setInt(4, idAsociado);

                        switch (tipo) {
                            case 2:
                                prepar.setInt(3, 0);
                                prepar.execute();
                                if (numerosAsignados()) //hago todo el proceso sólo si hay números asignados
                                { //modifico el estado del número, lo coloco en 0 que indica que el número está habilitado para sorteos

                                    if (asociacionAnioActual(idAsociado)) { //aquí miro si la persona tiene asignado un número este año
                                        return modificarEstadoNumero(cedula, 0); //la persona ya tenía un número este año, sólo cambio el estado del número
                                    } else {
                                        return reingresarAsociado(idAsociado);
                                    }
                                }

                            case 3:
                                prepar.setInt(3, 1);
                                prepar.execute();
                                //modifico el estado del número, lo coloco en 0 que indica que el número está inhabilitado para sorteos
                                if (modificarEstadoNumero(cedula, 1) == true) {

                                    return true;
                                } else {

                                    return false;
                                }
                        }
                    }
                }
            } else {
                prepar = conexion.prepareStatement("UPDATE `asociado`, persona SET asociado.Estado = 0 "
                        + "WHERE persona.cedula = ? and persona.idPersona = asociado.idPersona");
                prepar.setDouble(1, cedula);
                prepar.executeUpdate();

                if (numerosAsignados()) {//solo si hay números asignados
                    ResultSet resultado = idAsociado(cedula);//obtengo el id del asociado

                    if (resultado.next()) {
                        int idAsociado = resultado.getInt(1);
                        if (asociacionAnioActual(resultado.getInt(1))) { //si el asociado tiene asignado un número
                            modificarEstadoNumero(cedula, 0);
                            return true;
                        } else {
                            return reingresarAsociado(idAsociado);
                        }
                    }
                }

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
        return false;
    }

    public ResultSet idAsociado(long cedula) throws SQLException {
        return resultadoConexion("SELECT a.idAsociado, p.nombre, p.apellido FROM `asociado` as a, persona as p "
                + "WHERE a.idPersona = p.idPersona and p.Cedula ='" + cedula + "'");
    }

    public boolean asociacionAnioActual(int idAsociado) {
        try {
            ResultSet resultado = resultadoConexion("SELECT na.idNumeroAsociado FROM `numeroasociado` as na "
                    + "WHERE na.idAsociado = " + idAsociado + " and '" + fechaAnio.format(date) + "' = substring( na.fecha, length(na.fecha)-12 , length(na.fecha)-15 )");
            if (resultado.next()) {
                return true;
            }
        } catch (SQLException ex) {
        }
        return false;
    }

    public boolean reingresarAsociado(int idAsociado) {
        try {
            int cantidad = cantidadNumAsociados() + 1;

            if (!nuevoNumero(cantidad)) {

                //si ya el número existía en la tabla numero, debo cambiar su estado a 0
                prepar = conexion.prepareStatement("UPDATE `numero` as n SET n.Estado = 0 "
                        + "WHERE n.idnumero = ? and n.estado = 1");
                prepar.setDouble(1, cantidad);
                prepar.executeUpdate();

            }

            prepar = conexion.prepareStatement("Insert into numeroasociado (idNumeroAsociado, Fecha, idAsociado, idNumero) values (" + null + ",?,?,?)");
            prepar.setString(1, fechaCompleta.format(date) + "");
            prepar.setDouble(2, idAsociado);
            prepar.setDouble(3, cantidad);
            prepar.execute();

        } catch (SQLException ex) {
            return false;
        }

        return true;
    }

    public int estadoAsociado(long cedula) {
        try {
            //retorno si es asociado puro
            ResultSet resultado = resultadoConexion("SELECT p.Nombre, a.idAsociado FROM `asociado` as a, persona as p "
                    + "WHERE a.estado = 0 and a.idPersona = p.idPersona and p.Cedula = '" + cedula + "'");
            if (resultado.next()) {

                return 1;
            }
            /*retorno si es ex-asociado con participación*/
            resultado = resultadoConexion("SELECT p.Nombre, p.cedula FROM `asociado` as a, persona as p, inhabilitacion as i "
                    + "WHERE a.estado = 1 and a.idPersona = p.idPersona and p.Cedula = '" + cedula + "' and i.idAsociado = a.idAsociado and i.estado=0");
            if (resultado.next()) {

                return 2;
            }
            /*retorno si es ex-asociado sin participación*/
            resultado = resultadoConexion("SELECT p.Nombre, p.cedula FROM `asociado` as a, persona as p, inhabilitacion as i "
                    + "WHERE a.estado = 1 and a.idPersona = p.idPersona and p.Cedula = '" + cedula + "' and i.idAsociado = a.idAsociado and i.estado=1");
            if (resultado.next()) {

                return 3;
            }

        } catch (SQLException ex) {
        }
        return 0;
    }

    public boolean modificarAsociado(String nombre, String apellido, long cedula) {
        try {

            prepar = conexion.prepareStatement("UPDATE `persona` as p SET `Nombre`=?, Apellido = ? WHERE p.Cedula = ?");
            prepar.setString(1, nombre);
            prepar.setString(2, apellido);
            prepar.setLong(3, cedula);
            if (prepar.executeUpdate() == 0) {
                guardarOperacion(A_SETASOCIADO);

                return false;
            } else {

                return true;
            }
        } catch (SQLException e) {
        }
        return true;
    }

    public int guardarAdministrador(Administrador admin) {
        try {
            int idPersona = 0;
            int retorno = 1;
            Long cedula = (long) admin.getCedula();

            /*
            Verifico si el usuario no concuerda con otro ya utilizado.
             */
            ResultSet resultado = resultadoConexion("Select a.idAdministrador "
                    + "from administrador as a "
                    + "where a.usuario = '" + admin.getUsuario() + "'");

            if (resultado.next()) {

                return 2;
            }

            /*
            Ingreso una nueva persona.
             */
            prepar = conexion.prepareStatement("INSERT INTO persona (idPersona, nombre, apellido, cedula) VALUES(" + null + ",?,?,?)");
            prepar.setString(1, admin.getNombre());
            prepar.setString(2, admin.getApellido());
            prepar.setLong(3, cedula);
            try {
                prepar.execute();
            } catch (SQLException ee) { //si me bota una excepción, quiere decir que ya existe la cédula.
                retorno = 3;
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
            try {

                prepar = conexion.prepareStatement("INSERT INTO `administrador`(`idAdministrador`, `Usuario`, `Password`, `idPersona`, `tipo`) VALUES (" + null + ",?,?,?,?)");
                prepar.setString(1, admin.getUsuario());
                prepar.setString(2, admin.getPass());
                prepar.setInt(3, idPersona);
                prepar.setInt(4, 0);
                prepar.execute();

                guardarOperacion(ADD_ADMIN);

            } catch (SQLException e) {
                retorno = 4;
            }

            return retorno;
        } catch (MySQLIntegrityConstraintViolationException er) {
        } catch (SQLException ex) {
            Logger.getLogger(AccesoBD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }

    public boolean deleteAdmin(Administrador admin) {

        try {
            /*
            Verifico si el usuario no concuerda con otro ya utilizado.
             */

            prepar = conexion.prepareStatement("UPDATE `administrador` as a, persona as p SET a.estado = 1 WHERE a.idPersona = p.idpersona and p.cedula = ?");
            prepar.setLong(1, admin.getCedula());
            int result = prepar.executeUpdate();
            if (result == 0) {
                return false;
            } else {
                guardarOperacion(DELETE_ADMIN);
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(AccesoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public boolean reingresarAdmin(Administrador admin) {
        try {

            prepar = conexion.prepareStatement("UPDATE `administrador` as a, persona as p SET a.estado = 0 WHERE a.idPersona = p.idpersona and p.cedula = ? and a.estado = 1");
            prepar.setLong(1, admin.getCedula());
            int result = prepar.executeUpdate();
            if (result == 0) {
                return false;
            } else {
                guardarOperacion(REI_ADMIN);
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccesoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public boolean modificarAdmin(Administrador admin) {
        try {

            prepar = conexion.prepareStatement("UPDATE `administrador` as a, persona as p SET a.Usuario=?, a.Password=? WHERE a.idPersona = p.idPersona and p.cedula = ?");
            prepar.setString(1, admin.getUsuario());
            prepar.setString(2, admin.getPass());
            prepar.setLong(3, admin.getCedula());
            int resultado = prepar.executeUpdate();
            if (resultado != 0) {
                guardarOperacion("Modificación de usuario y/o contraseña del administrador.");
                administrador = admin;
                return true;
            }
        } catch (SQLException e) {
        }
        return false;
    }

    public ResultSet nombreAdmin(long cedula, boolean reingreso) throws SQLException {
        if (reingreso) {
            return resultadoConexion("Select p.nombre, p.apellido, a.usuario "
                    + "from persona as p, administrador as a "
                    + "where a.estado = 1 and a.idpersona = p.idpersona and p.cedula =" + cedula);
        }
        return resultadoConexion("Select p.nombre, p.apellido, a.usuario "
                + "from persona as p, administrador as a "
                + "where a.estado = 0 and a.idpersona = p.idpersona and p.cedula =" + cedula);
    }

    public ResultSet verAdministradores() throws SQLException {
        return resultadoConexion("SELECT a.usuario, p.nombre, p.apellido, p.cedula, if(a.estado=0,'SI','NO') as Activo FROM `administrador` as a, persona as p WHERE a.idPersona = p.idPersona and a.tipo = 0");
    }

    /* ------------------------------------------------------------------------------------------------------------------------ */
 /*
    Operaciones para la asignación de los números a cada asociado
     */
    public boolean numerosAsignados() {
        String fecha2 = fechaAnio.format(date);
        try {
            ResultSet resultado = resultadoConexion("SELECT count(na.fecha)  FROM numeroasociado as na WHERE Fecha LIKE '%" + fecha2 + "%'");
            if (resultado.next()) {

                return resultado.getInt(1) > 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccesoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean asociadosVacia() {
        try {
            //   
            ResultSet resultado = resultadoConexion("Select count(a.idAsociado) from asociado as a");
            if (resultado.next()) {
                if (resultado.getInt(1) == 0) {

                    return true;
                }
            }
        } catch (SQLException ex) {
        }
        // 
        return false;
    }

    /*
    Para asignar los números a cada asociado, necesito el id de cada uno 
     */
    public ArrayList<Integer> idsAsociados() {
        ArrayList<Integer> array = new ArrayList<>();
        try {
            ResultSet resultado = resultadoConexion("SELECT a.idasociado FROM `asociado` as a WHERE a.estado=0 ORDER BY `a`.`idAsociado` ASC");
            while (resultado.next()) {
                array.add(resultado.getInt(1));
            }
        } catch (SQLException e) {
        }

        return array;
    }

    public boolean prepararAsociacion() {

        try {

            //cuento cuantos números tienen estado 0.
            int cantidad = cantidadNumerosHabiles(true);
            //cuantos asociados tienen estado 0
            int cantidadAsociadoenCero = numeroAsociadosActivos();

            System.out.println("Números: " + cantidad + "/n Asociados: " + cantidadAsociadoenCero);

            /*
            Si la cantidad de números en 0 es igual a la de asociados en 0, entonces no hay necesidad de buscar 
            ex-asociados con participación.
             */
            if (cantidad > cantidadAsociadoenCero) {

                System.out.println("Cantidad números hábiles es mayor");

                /* 
            Obtengo la lista con los datos de los asociados que, hasta el año anterior, eran ex-asociados con 
            participación 
                 */
                ResultSet resultado = verificarsihayInhabilitados();

                while (resultado.next()) {

                    /*
                Registro todos los asociados con estado 1 de la tabla Asociado, en la tabla inhabilitados para que representen 
                ex-asociados sin participación.
                     */
                    prepar = conexion.prepareStatement("INSERT INTO `inhabilitacion`(`idInhabilitacion`, `Razon`, `Fecha`, `Estado`, `idAsociado`) VALUES(" + null + ",?,?,?,?)");
                    prepar.setString(1, "Asociado deshabilitado por cambio de año");
                    prepar.setString(2, fechaCompleta.format(date));
                    prepar.setInt(3, 1);
                    prepar.setInt(4, resultado.getInt(3));
                    prepar.execute();

                    /*
                ahora cambio el estado a 1 del número que cada uno de ellos tenía anteriormente.
                     */
                    //  modificarEstadoNumeroAnioPasado(resultado.getLong(2), 1);
                }

            } else if (cantidadAsociadoenCero > cantidad) { //esto sucede cuando agregan nuevos asociados sin haber entregado números en el año actual.

                System.out.println("Cantidad asociados en cero es mayor");
                //Ahora hago, que al menos, existan las mismas cantidades
                for (int i = cantidad + 1; i <= cantidadAsociadoenCero; i++) {
                    nuevoNumero(i);
                }
            }

            //todo esto, deja igual cantidad de números hábiles, como de asociados hábiles listos para relacionar entre sí
            normalizarNumerosYAsociados(cantidadAsociadoenCero);

            System.out.println("Datos finales: Números: " + cantidadNumerosHabiles(true) + " ; Asociados: " + numeroAsociadosActivos());

        } catch (SQLException ex) {
            System.out.println("excepción: " + ex);
            return false;
        }
        return true;
    }

    public void normalizarNumerosYAsociados(int num) throws SQLException {
        //modifico el estado a cero(0) de todos los números que son menores o iguales a la cantidad de asociados en 0.
        prepar = conexion.prepareStatement("UPDATE `numero` as n SET `Estado`=? WHERE n.idnumero <= ? ");
        prepar.setInt(1, 0);
        prepar.setInt(2, num);
        prepar.execute();

        //Ahora, cambio el valor a 1 de todos los números mayores
        prepar = conexion.prepareStatement("UPDATE `numero` as n SET `Estado`=? WHERE n.idnumero > ? ");
        prepar.setInt(1, 1);
        prepar.setInt(2, num);
        prepar.execute();
    }

    public boolean asociarNumeros(int idAsociado, int idNumero) {
//        
        try {
            prepar = conexion.prepareStatement("INSERT INTO `numeroasociado`(`idNumeroAsociado`, `Fecha`, `idAsociado`, `idNumero`) VALUES(" + null + ",?,?,?)");
            prepar.setString(1, fechaCompleta.format(date));
            prepar.setDouble(2, idAsociado);
            prepar.setDouble(3, idNumero);
            prepar.execute();

//            
            return true;
        } catch (java.sql.SQLException er) {
            JOptionPane.showMessageDialog(null, "Ocurrió un problema en el proceso");
            return false;
        }
    }

    public int cantidadNumerosHabiles(boolean habiles) {
        try {

            ResultSet resultado;
            if (habiles) {
                resultado = resultadoConexion("Select count(n.idNumero) From numero as n where n.estado = 0");
            } else {
                resultado = resultadoConexion("select count(n.idnumero) from numero as n");
            }
            if (resultado.next()) {
                return resultado.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccesoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public boolean todoNumeroHabilitado() {
        try {

            prepar = conexion.prepareStatement("UPDATE `numero` as n SET n.Estado = 0 WHERE n.estado = 1");
            prepar.executeUpdate();

        } catch (SQLException ex) {
            return false;
        }
        return true;
    }

    public boolean nuevoNumero(int num) {
        try {

            prepar = conexion.prepareStatement("Insert into numero (idNumero, Estado) values (?," + 0 + ")");
            prepar.setInt(1, num);
            prepar.execute();
        } catch (SQLException ex) {
            return false;
        }
        return true;
    }

    public boolean setEstadoNumero(int numero) {
        try {

            prepar = conexion.prepareStatement("UPDATE `numero` as n SET n.Estado = 0 WHERE n.idNumero = ?");
            prepar.setInt(1, numero);
            if (prepar.executeUpdate() != 0) {

                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccesoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public int cantidadNumAsociados() {
        try {
            ResultSet resultado = resultadoConexion("SELECT count(na.idNumeroAsociado) FROM `numeroasociado` as na WHERE na.fecha like '%" + fechaAnio.format(date) + "%'");
            if (resultado.next()) {
                return resultado.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccesoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    /* ------------------------------------------------------------------------------------------------------------------------- */
 /*
    Operaciones del sorteo
     */
    public int numerodeAsociados(boolean habilitado) {

        try {
            ResultSet resultado;

            if (!habilitado) {
                /*
                Cantidad de asociados que me permite generar el nùmero ganador
                 */
                resultado = resultadoConexion("SELECT count(*) FROM asociado");
            } else {
                /*
                Me permite saber la cantidad de asociado hàbiles para los sorteos, y me ayuda a asignarle cada 
                número a cada asociado
                 */
                resultado = resultadoConexion("SELECT count(*) FROM asociado as a WHERE a.estado = 0");
            }

            if (resultado.next()) {
                return resultado.getInt(1);
            }
        } catch (java.sql.SQLException er) {
            JOptionPane.showMessageDialog(null, er, "Failed!", JOptionPane.ERROR_MESSAGE);
        }

        return 0;
    }

    public String ganador(int numero, float premio, int tipo, boolean prueba) {
        try {
            if (tipo == 0) {// si el premio es menor, busco que el número no haya salido antes ganador otro en el mismo año.
                /*
        Aquí busco si el número ya ha sido ganador de un premio menor a lo largo del año
                 */
                ResultSet resultado = resultadoConexion("Select na2.idNumero from numeroasociado as na2 where na2.idNumero "
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
            ResultSet resultado = resultadoConexion("select p.nombre, p.apellido, p.cedula, na.idnumero, na.idnumeroasociado "
                    + "from numeroasociado as na, numero as n, asociado as a, persona as p "
                    + "where n.estado = 0 and n.idnumero = na.idnumero and '" + fechaAnio.format(date) + "' = "
                    + "substring( na.fecha, length(na.fecha)-12 , length(na.fecha)-15 ) and na.idasociado = a.idasociado "
                    + "and a.idpersona = p.idpersona and na.idnumero = " + numero + "");
            if (resultado.next()) {
                if (!prueba) {
                    guardarGanador(resultado.getInt(5), premio, tipo); // guardo al ganador
                }
                return resultado.getString(1) + " " + resultado.getString(2); // retorno el nombre del ganador
            }
        } catch (java.sql.SQLException er) {
            JOptionPane.showMessageDialog(null, "Error al saber el ganador" + er, "Failed!", JOptionPane.ERROR_MESSAGE);
        }

        return "";
    }

    /* ------------------------------------------------------------------------------------------------------------------------*/
    public int numeroAsociadosActivos() {

        try {

            ResultSet resultado = resultadoConexion("SELECT count(*) FROM asociado as n where n.Estado = 0");

            if (resultado.next()) {
                return resultado.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AccesoBD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }

    public ResultSet verificarsihayInhabilitados() throws SQLException {
        int anioAnterior = Integer.parseInt(fechaAnio.format(date)) - 1;
        return resultadoConexion("SELECT p.nombre, p.cedula, a.idAsociado "
                + "FROM inhabilitacion as i, asociado as a, persona as p "
                + "WHERE a.idasociado = i.idasociado and i.fecha like '%" + anioAnterior + "%' and i.estado = 0 and p.idpersona = a.idpersona");
    }

    public boolean modificarEstadoNumero(long cedula, int estado) throws SQLException {
        ResultSet resultado = resultadoConexion("SELECT na.idNumeroAsociado, na.idAsociado, na.idNumero, na.fecha "
                + "FROM numeroasociado as na, asociado as a, persona as p "
                + "where p.cedula = " + cedula + " and p.idPersona = a.idPersona and a.idAsociado = na.idAsociado and na.fecha like '%" + fechaAnio.format(date) + "%'");
        if (resultado.next()) {
            prepar = conexion.prepareStatement("UPDATE `numero` as n SET n.Estado = ? WHERE n.idNumero = ?");
            prepar.setInt(1, estado);
            prepar.setInt(2, resultado.getInt(3));
            return prepar.executeUpdate() != 0;
        }
        return false;
    }

    public boolean modificarEstadoNumeroAnioPasado(long cedula, int estado) throws SQLException {
        int anioAnterior = Integer.parseInt(fechaAnio.format(date)) - 1;
        ResultSet resultado2 = resultadoConexion("SELECT na.idNumeroAsociado, na.idAsociado, na.idNumero, na.fecha "
                + "FROM numeroasociado as na, asociado as a, persona as p "
                + "where p.cedula = " + cedula + " and p.idPersona = a.idPersona and a.idAsociado = na.idAsociado and '" + anioAnterior + "' = substring( na.fecha, length(na.fecha)-12 , length(na.fecha)-15 )");
        if (resultado2.next()) {
            prepar = conexion.prepareStatement("UPDATE `numero` as n SET n.Estado = ? WHERE n.idNumero = ?");
            prepar.setInt(1, estado);
            prepar.setInt(2, resultado2.getInt(3));
            return prepar.executeUpdate() != 0;
        }
        return false;
    }

    public String fechaBackup() {
        try {
            ResultSet resultado = resultadoConexion("SELECT fecha FROM `movimiento` "
                    + "WHERE detalle = 'Creación de backup de la base de datos.' order by idmovimiento desc limit 1");
            if (resultado.next()) {
                return resultado.getString(1);
            }
        } catch (SQLException ex) {
        }
        return "";
    }

}
