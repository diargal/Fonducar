package DataAcces;

import Logica.Administrador;
import static Logica.BonoSolidario.administrador;
import static Logica.Mensajes.ADD_ADMIN;
//import static Logica.BonoSolidario.asociados;
import static Logica.Mensajes.A_AGREGARASOCIADOS;
import static Logica.Mensajes.A_SETASOCIADO;
import static Logica.Mensajes.DELETE_ADMIN;
import static Logica.Mensajes.ERRORBDC;
import static Logica.Mensajes.EXISTE;
import static Logica.Mensajes.REI_ADMIN;
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

    private final String driver = "com.mysql.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost/fonducar";
    private final String login = "root";
    private final String password = "";
    private static Connection conexion = null;
    private final Date date;
    private DateFormat fechaCompleta;
    private final DateFormat fechaAnio;
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

//    public void desconectar() {
//        conexion = null;
//        if (conexion == null) {
//            System.out.println("Conexión finalizada.");
//        }
//    }

    /* --------------------------------------------------------------------------------------------------------------------------- */
    private ResultSet resultadoConexion(String comando) throws SQLException {
        conexion();
        Statement stmt = conexion.createStatement();
        return stmt.executeQuery(comando);

    }

    /* -------------------------------------------------------------------------------------------------------------------------- */
 /*
    Operaciones de login
     */
    public boolean consultaAdmin(String usuario, String pass, boolean tipo) {

        try {
            String password = DigestUtils.md5Hex(pass);
//            System.out.println(password);
            resultado = resultadoConexion("SELECT A.*, P.nombre, P.cedula "
                    + "FROM `administrador` as A, persona as P "
                    + "WHERE P.idPersona = A.idPersona and A.estado = 0 and A.Password='" + password + "' and A.Usuario='" + usuario + "'");
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
//                desconectar();
                return true;
            }

        } catch (java.sql.SQLException er) {
            JOptionPane.showMessageDialog(null, "No se pudo realizar la consulta de Administrador", "Failed!", JOptionPane.ERROR_MESSAGE);
        }
//        desconectar();
        return false;
    }

    /* ----------------------------------------------------------------------------------------------------------------------- */
 /*
    Operaciones de guardado
     */
    private void guardarGanador(long idNumAso, float premio, int tipo) {
        conexion();

        String fecha2 = fechaCompleta.format(date);

        try {

            comandoSQL = "INSERT INTO `sorteo`(`idSorteo`, `Fecha`, `idNumeroAsociado`, `Premio`, `TipoSorteo`) VALUES (" + null + ",'" + fecha2 + "'," + idNumAso + "," + premio + "," + tipo + ")";
            prepar = conexion.prepareStatement(comandoSQL);
            prepar.execute();

//            desconectar();
        } catch (java.sql.SQLException er) {
            JOptionPane.showMessageDialog(null, "No se pudo guardar el historial. " + er, "Failed!", JOptionPane.ERROR_MESSAGE);

        }
    }

    public boolean guardarOperacion(String tipo) {
        conexion();
        try {

            fechaCompleta = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

            comandoSQL = "Insert into movimiento (idMovimiento, Fecha, Detalle, idAdministrador) values (" + null + ",?,?,?)";
            prepar = conexion.prepareStatement(comandoSQL);
            prepar.setString(1, fechaCompleta.format(date));
            prepar.setString(2, tipo);
            prepar.setLong(3, administrador.getIdAdmin());
            prepar.execute();
//            desconectar();
            return true;
        } catch (Exception e) {
//            desconectar();
            return false;
        }
    }

    public boolean guardarAsociados(File file) {
        conexion();
        int id = 0;
        long cedula = 0;
        String nombre = "";

        try {

            XSSFWorkbook wb = new XSSFWorkbook(file);
            XSSFSheet sheet = wb.getSheetAt(0);

            int numFilas = sheet.getLastRowNum();

            for (int a = 1; a <= numFilas; a++) {
                int numeroGuardar = 0;
                Row fila = sheet.getRow(a);

                //Leo del archivo, y obtengo los valores de cada fila para crear una persona
                prepar = conexion.prepareStatement("INSERT INTO persona (idPersona, nombre, cedula) VALUES(" + null + ",?,?)");
                nombre = fila.getCell(0).getStringCellValue();
                prepar.setString(1, nombre);
                cedula = (long) fila.getCell(1).getNumericCellValue();
                prepar.setLong(2, cedula);
                prepar.execute();

                // Selecciono el id de la persona recien creada, para poder crear un asociado
                resultado = resultadoConexion("SELECT P.idPersona FROM persona as P WHERE P.Cedula = '" + cedula + "'");
                if (resultado.next()) {
                    id = resultado.getInt(1);
                }

                //Creo el asociado
                prepar = conexion.prepareStatement("Insert into asociado (idAsociado, Estado, idPersona) values (" + null + ",0,?)");
                prepar.setDouble(1, id);
                prepar.execute();

                //obtengo el id del nuevo asociado y lo guardo
                resultado = resultadoConexion("select A.idAsociado from asociado as A where A.idPersona = '" + id + "'");
                if (resultado.next()) {
                    id = resultado.getInt(1);
                }

                /*
                Ahora verifico si aún no se han asignado los números a cada asociado.
                 */
                if (numerosAsignados()) {//si hay números asignados

                    int cantidad = cantidadNumAsociados();
                    numeroGuardar = cantidad + 1;

                    if (!nuevoNumero(numeroGuardar)) {//si ya el número existía en la tabla numero, debo cambiar su estado a 0
                        prepar = conexion.prepareStatement("UPDATE `numero` as n SET n.Estado = 0 "
                                + "WHERE n.idnumero = ? and n.estado = 1");
                        prepar.setDouble(1, numeroGuardar);
                        prepar.executeUpdate();
                    }

                } else if (asociadosVacia()) {//no han sido asignados y está completamente vacía la tabla asociados

                    numeroGuardar = a;
                    nuevoNumero(numeroGuardar);
                }

                if (numeroGuardar != 0) {
                    conexion();
                    prepar = conexion.prepareStatement("Insert into numeroasociado (idNumeroAsociado, Fecha, idAsociado, idNumero) values (" + null + ",?,?,?)");
                    prepar.setString(1, fechaCompleta.format(date) + "");
                    prepar.setDouble(2, id);
                    prepar.setDouble(3, numeroGuardar);
                    prepar.execute();
                }
            }

            guardarOperacion(A_AGREGARASOCIADOS);

            //conexion.close();
//            desconectar();
            return true;

        } catch (IOException | InvalidFormatException ex) {
        } catch (SQLException es) {
            JOptionPane.showMessageDialog(null, EXISTE + cedula, "Coincidencia en registro.", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalStateException | InvalidOperationException de) {
            JOptionPane.showMessageDialog(null, ERRORBDC, "Error de lectura", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    /* -------------------------------------------------------------------------------------------------------------------------- */
 /*
    Historiales y opciones mostradas en la vista principal
     */
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
        return resultadoConexion("SELECT p.nombre, p.cedula, na.idnumero, IF(a.estado = 0, \"Asociado\", \"Ex-asociado con participación\") as estado "
                + "FROM `numeroasociado` as na, numero as n, persona as p, asociado as a "
                + "WHERE n.estado = 0 and n.idNumero = na.idNumero and '" + fecha2 + "' = substring( na.fecha, length(na.fecha)-12 , length(na.fecha)-15 ) "
                + "and na.idAsociado = a.idAsociado and a.idPersona = p.idPersona ORDER BY `p`.`Nombre` ASC");
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

    public boolean cambiarEstado(long cedula, int tipo, String razon) {

        try {
            conexion();

            if (tipo == 2 || tipo == 3) {
                //cambio el estado del asociado en la tabla asociado, que indica inhabilitación
                prepar = conexion.prepareStatement("UPDATE `asociado`, persona SET asociado.Estado = 1 "
                        + "WHERE persona.cedula = ? and persona.idPersona = asociado.idPersona");
                prepar.setDouble(1, cedula);

                if (prepar.executeUpdate() == 0) {
//                    desconectar();
                    return false;
                } else {
                    //busco el id del asociado que concuerda con la cédula ingresada
                    resultado = idAsociado(cedula);
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
//                                desconectar();
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
//                                desconectar();
                                //modifico el estado del número, lo coloco en 0 que indica que el número está inhabilitado para sorteos
                                return modificarEstadoNumero(cedula, 1);
                        }
                    }
                }
            } else {
                prepar = conexion.prepareStatement("UPDATE `asociado`, persona SET asociado.Estado = 0 "
                        + "WHERE persona.cedula = ? and persona.idPersona = asociado.idPersona");
                prepar.setDouble(1, cedula);
                prepar.executeUpdate();

                if (numerosAsignados()) {//solo si hay números asignados
                    resultado = idAsociado(cedula);//obtengo el id del asociado

                    if (resultado.next()) {
                        int idAsociado = resultado.getInt(1);
                        System.out.println("Resultado: " + idAsociado);
                        if (asociacionAnioActual(resultado.getInt(1))) {
                            System.out.println("entra");
                            modificarEstadoNumero(cedula, 0);
                        } else {
                            System.out.println("otra");
                            return reingresarAsociado(idAsociado);
                        }
                    }
                }
//                desconectar();
            }
        } catch (SQLException e) {
            System.out.println("error: " + e);
            return false;
        }
        return false;
    }

    public ResultSet idAsociado(long cedula) throws SQLException {
        return resultado = resultadoConexion("SELECT a.idAsociado FROM `asociado` as a, persona as p "
                + "WHERE a.idPersona = p.idPersona and p.Cedula ='" + cedula + "'");
    }

    public boolean asociacionAnioActual(int idAsociado) {
        try {
            resultado = resultadoConexion("SELECT na.idNumeroAsociado FROM `numeroasociado` as na "
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
            System.out.println("llega");
            int cantidad = cantidadNumAsociados() + 1;
            System.out.println("cantidad: " + cantidad);

            if (!nuevoNumero(cantidad)) {
                System.out.println("puta madre");
                conexion();
                //si ya el número existía en la tabla numero, debo cambiar su estado a 0
                prepar = conexion.prepareStatement("UPDATE `numero` as n SET n.Estado = 0 "
                        + "WHERE n.idnumero = ? and n.estado = 1");
                prepar.setDouble(1, cantidad);
                prepar.executeUpdate();

            }
            conexion();
            System.out.println("verga");
            prepar = conexion.prepareStatement("Insert into numeroasociado (idNumeroAsociado, Fecha, idAsociado, idNumero) values (" + null + ",?,?,?)");
            prepar.setString(1, fechaCompleta.format(date) + "");
            prepar.setDouble(2, idAsociado);
            prepar.setDouble(3, cantidad);
            prepar.execute();
//            desconectar();
        } catch (SQLException ex) {
            return false;
        }

        return true;
    }

    public int estadoAsociado(long cedula) {
        try {
            //retorno si es asociado puro
            resultado = resultadoConexion("SELECT p.Nombre, a.idAsociado FROM `asociado` as a, persona as p "
                    + "WHERE a.estado = 0 and a.idPersona = p.idPersona and p.Cedula = '" + cedula + "'");
            if (resultado.next()) {
//                desconectar();
                return 1;
            }
            /*retorno si es ex-asociado con participación*/
            resultado = resultadoConexion("SELECT p.Nombre, p.cedula FROM `asociado` as a, persona as p, inhabilitacion as i "
                    + "WHERE a.estado = 1 and a.idPersona = p.idPersona and p.Cedula = '" + cedula + "' and i.idAsociado = a.idAsociado and i.estado=0");
            if (resultado.next()) {
//                desconectar();
                return 2;
            }
            /*retorno si es ex-asociado sin participación*/
            resultado = resultadoConexion("SELECT p.Nombre, p.cedula FROM `asociado` as a, persona as p, inhabilitacion as i "
                    + "WHERE a.estado = 1 and a.idPersona = p.idPersona and p.Cedula = '" + cedula + "' and i.idAsociado = a.idAsociado and i.estado=1");
            if (resultado.next()) {
//                desconectar();
                return 3;
            }

        } catch (SQLException ex) {
        }
        return 0;
    }

    public boolean modificarAsociado(String nombre, long cedula) {
        try {
            conexion();
            prepar = conexion.prepareStatement("UPDATE `persona` as p SET `Nombre`=? WHERE p.Cedula = ?");
            prepar.setString(1, nombre);
            prepar.setLong(2, cedula);
//            desconectar();
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
//                desconectar();
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
//            String password = DigestUtils.md5Hex(admin.getPass());
            prepar.setString(2, admin.getPass());
            prepar.setInt(3, idPersona);
            prepar.setInt(4, 0);
            prepar.execute();
//            desconectar();
            guardarOperacion(ADD_ADMIN);
            return true;
        } catch (MySQLIntegrityConstraintViolationException er) {
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(AccesoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
//        desconectar();
        return false;
    }

    public boolean deleteAdmin(Administrador admin) {

        try {
            /*
            Verifico si el usuario no concuerda con otro ya utilizado.
             */
            conexion();

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
            conexion();

            prepar = conexion.prepareStatement("UPDATE `administrador` as a, persona as p SET a.estado = 0 WHERE a.idPersona = p.idpersona and p.cedula = ?");
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

    public ResultSet verAdministradores() throws SQLException {
        return resultadoConexion("SELECT p.nombre, p.cedula, if(a.estado=0,\"SI\",\"NO\") as Activo FROM `administrador` as a, persona as p WHERE a.idPersona = p.idPersona and a.tipo = 0");
    }

    /* ------------------------------------------------------------------------------------------------------------------------ */
 /*
    Operaciones para la asignación de los números a cada asociado
     */
    public boolean verificarFecha() {
        String fecha2 = fechaAnio.format(date);
        try {
            resultado = resultadoConexion("SELECT na.fecha FROM `numeroasociado` as na WHERE '" + fecha2 + "' = substring( na.fecha, length(na.fecha)-12 , length(na.fecha)-15 )");
            if (resultado.next()) {
//                desconectar();
                return false;
            }
        } catch (SQLException es) {
        }
//        desconectar();
        return true;
    }

    public boolean numerosAsignados() {
        String fecha2 = fechaAnio.format(date);
        try {
            resultado = resultadoConexion("select na.fecha "
                    + "from numeroasociado as na "
                    + "where '" + fecha2 + "' = substring( na.fecha, length(na.fecha)-12 , length(na.fecha)-15)");
            if (resultado.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccesoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean asociadosVacia() {
        try {
            conexion();
            resultado = resultadoConexion("Select count(a.idAsociado) from asociado as a");
            if (resultado.next()) {
                if (resultado.getInt(1) == 0) {
//                    desconectar();
                    return false;
                }
            }
        } catch (SQLException ex) {
        }
//        desconectar();
        return true;
    }

    public ArrayList<Integer> idsAsociados() {
        ArrayList<Integer> array = new ArrayList<>();
        try {
//            resultado = resultadoConexion("SELECT p.nombre, a.idAsociado, na.idnumero "
//                    + "FROM numero as n, numeroasociado as na, asociado as a, persona as p "
//                    + "WHERE n.estado = 0 and n.idnumero = na.idnumero and '2018' = substring( na.fecha, length(na.fecha)-12 , length(na.fecha)-15 ) "
//                    + "and na.idasociado = a.idasociado and a.idpersona = p.idpersona ORDER BY `p`.`Nombre` ASC");

            resultado = resultadoConexion("SELECT a.idasociado FROM `asociado` as a WHERE a.estado=0 ORDER BY `a`.`idAsociado` ASC");
            while (resultado.next()) {
                array.add(resultado.getInt(1));
            }
//            return array;
        } catch (SQLException e) {
        }

        return array;
    }

    public boolean prepararAsociacion() {
        try {
            resultado = verificarsihayInhabilitados();
            while (resultado.next()) {
                //Registro todos los asociados, con estado 1 a la tabla inhabilitados para que representen ex-asociados sin participación 
                prepar = conexion.prepareStatement("INSERT INTO `inhabilitacion`(`idInhabilitacion`, `Razon`, `Fecha`, `Estado`, `idAsociado`) VALUES(" + null + ",?,?,?,?)");
                prepar.setString(1, "No pidió reingreso y se procedió a deshabilitarlo por completo.");
                prepar.setString(2, fechaCompleta.format(date));
                prepar.setInt(3, 1);
                prepar.setInt(4, resultado.getInt(3));
                prepar.execute();

                //ahora cambio el estado a 1 del número que cada uno de ellos tenía.
                modificarEstadoNumeroAnioPasado(resultado.getLong(2), 1);
            }

            //cuento cuantos números tienen estado 0.
            int cantidad = cantidadNumerosHabiles(true);

            //modifico el estado a cero(0) de todos los números que son menores o iguales al conteo anterior
            prepar = conexion.prepareStatement("UPDATE `numero` as n SET `Estado`=? WHERE n.idnumero <= ? ");
            prepar.setInt(1, 0);
            prepar.setInt(2, cantidad);
            prepar.execute();

            //modifico el estado a uno(1) de todos los números que son mayores al conteo anterior
            prepar = conexion.prepareStatement("UPDATE `numero` as n SET `Estado`=? WHERE n.idnumero > ? ");
            prepar.setInt(1, 1);
            prepar.setInt(2, cantidad);
            prepar.execute();

//            desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(AccesoBD.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public boolean asociarNumeros(int idAsociado, int idNumero) {
        conexion();
        try {
            prepar = conexion.prepareStatement("INSERT INTO `numeroasociado`(`idNumeroAsociado`, `Fecha`, `idAsociado`, `idNumero`) VALUES(" + null + ",?,?,?)");
            prepar.setString(1, fechaCompleta.format(date));
            prepar.setDouble(2, idAsociado);
            prepar.setDouble(3, idNumero);
            prepar.execute();

//            desconectar();
            return true;
        } catch (java.sql.SQLException er) {
            return false;
        }
    }

    public int cantidadNumerosHabiles(boolean habiles) {
        try {
            conexion();
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
            conexion();
            prepar = conexion.prepareStatement("UPDATE `numero` as n SET n.Estado = 0 WHERE n.estado = 1");
            prepar.executeUpdate();
//            desconectar();
        } catch (SQLException ex) {
            return false;
        }
        return true;
    }

    public boolean nuevoNumero(int num) {
        try {
            conexion();
            prepar = conexion.prepareStatement("Insert into numero (idNumero, Estado) values (?," + 0 + ")");
            prepar.setInt(1, num);
            prepar.execute();
//            desconectar();
        } catch (SQLException ex) {
            return false;
        }
        return true;
    }

    public boolean setEstadoNumero(int numero) {
        try {
            conexion();
            prepar = conexion.prepareStatement("UPDATE `numero` as n SET n.Estado = 0 WHERE n.idNumero = ?");
            prepar.setInt(1, numero);
            if (prepar.executeUpdate() != 0) {
//                desconectar();
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccesoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public int cantidadNumAsociados() {
        try {
            resultado = resultadoConexion("SELECT count(na.idNumeroAsociado) "
                    + "FROM `numeroasociado` as na "
                    + "WHERE '" + fechaAnio.format(date) + "' = substring( na.fecha, length(na.fecha)-12 , length(na.fecha)-15 ) ");
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
            if (!habilitado) {
                resultado = resultadoConexion("SELECT count(*) FROM asociado");
            } else {
                resultado = resultadoConexion("SELECT count(*) FROM asociado as a WHERE a.estado = 0");
            }

            if (resultado.next()) {
//                desconectar();
                return resultado.getInt(1);
            }
        } catch (java.sql.SQLException er) {
            JOptionPane.showMessageDialog(null, er, "Failed!", JOptionPane.ERROR_MESSAGE);
        }

//        desconectar();
        return 0;
    }

    public String ganador(int numero, float premio, int tipo, boolean prueba) {
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
                if (!prueba) {
                    guardarGanador(resultado.getInt(4), premio, tipo);
                }
                return resultado.getString(1);
            }
        } catch (java.sql.SQLException er) {
            JOptionPane.showMessageDialog(null, "Error al saber el ganador" + er, "Failed!", JOptionPane.ERROR_MESSAGE);
        }
//        desconectar();
        return "";
    }

    /* ------------------------------------------------------------------------------------------------------------------------*/
    public int numeroAsociadosActivos() {

        try {
            //conexion();
            resultado = resultadoConexion("SELECT count(*) FROM numero as n where n.Estado=0");
//            desconectar();
            if (resultado.next()) {
                return resultado.getInt(1);
            }

        } catch (java.sql.SQLException er) {
            JOptionPane.showMessageDialog(null, er, "Failed!", JOptionPane.ERROR_MESSAGE);
        }

//        desconectar();
        return 0;
    }

    public ResultSet verificarsihayInhabilitados() throws SQLException {
        int anioAnterior = Integer.parseInt(fechaAnio.format(date)) - 1;
        return resultadoConexion("SELECT p.nombre, p.cedula, a.idAsociado "
                + "FROM inhabilitacion as i, asociado as a, persona as p "
                + "WHERE a.idasociado = i.idasociado and '" + anioAnterior + "' = substring( i.fecha, length(i.fecha)-12 , length(i.fecha)-15 ) "
                + "and i.estado = 0 and p.idpersona = a.idpersona");
    }

    public boolean modificarEstadoNumero(long cedula, int estado) throws SQLException {
        resultado = resultadoConexion("SELECT na.idNumeroAsociado, na.idAsociado, na.idNumero, na.fecha "
                + "FROM numeroasociado as na, asociado as a, persona as p "
                + "where p.cedula = " + cedula + " and p.idPersona = a.idPersona and a.idAsociado = na.idAsociado and '" + fechaAnio.format(date) + "' = substring( na.fecha, length(na.fecha)-12 , length(na.fecha)-15 )");
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
        resultado = resultadoConexion("SELECT na.idNumeroAsociado, na.idAsociado, na.idNumero, na.fecha "
                + "FROM numeroasociado as na, asociado as a, persona as p "
                + "where p.cedula = " + cedula + " and p.idPersona = a.idPersona and a.idAsociado = na.idAsociado and '" + anioAnterior + "' = substring( na.fecha, length(na.fecha)-12 , length(na.fecha)-15 )");
        if (resultado.next()) {
            prepar = conexion.prepareStatement("UPDATE `numero` as n SET n.Estado = ? WHERE n.idNumero = ?");
            prepar.setInt(1, estado);
            prepar.setInt(2, resultado.getInt(3));
            return prepar.executeUpdate() != 0;
        }
        return false;
    }

}
