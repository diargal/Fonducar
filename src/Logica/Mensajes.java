/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

/**
 *
 * @author Diego García
 */
public class Mensajes {

    /*Mensajes sobre los sorteos*/
    public static final String CUANTOS_SORTEOS = "Cuántos sorteos se realizarán?";
    public static final String SORTEOS_FINAL = "Ya se han realizado todos los sorteos estipulados";
    public static final String MSG_SORTEO = "No quedan más sorteos";
    public static final String A_RIFA = "Realización de sorteo";


    /*Movimientos y operaciones*/
    public static final String A_MODIASOCIADO = "Modificación de estado del asociado";
    public static final String A_AGREGARASOCIADOS = "Adición de nuevos asociados.";
    public static final String A_NUMEROS = "Asignación de números a los asociados.";
    public static final String A_REPORTESORTEOS = "Generación del historial general de los sorteos.";
    public static final String A_SESION = "Inicio de sesión.";
    public static final String A_RACTUALES = "Informe del número actual asignado a cada asociado y ex-asociado con participación.";
    public static final String A_HNS = "Generación del historial general de los números asignados a cada asociado";
    public static final String A_HACON = "Generación del historial de los ex-asociados CON participación.";
    public static final String A_HABILACTUALES = "Generación del historial de los ex-asociados habilitados actualmente para participar.";
    public static final String A_HASIN = "Generación del historial de los ex-asociados SIN participación.";
    public static final String A_INHABILACTUALES = "Generación del historial de los ex-asociados inhabilitados actualmente para participar.";
    public static final String A_HOPERACIONES = "Generación del historial de las operaciones realizadas por el administrador.";
    public static final String A_SETASOCIADO = "Modificación de datos de Asociado";
    public static final String ADD_ADMIN = "Creación de un nuevo administrador.";
    public static final String DELETE_ADMIN = "Eliminación de administrador.";
    public static final String REI_ADMIN = "Reingreso de un administrador.";

    /*Información relevante*/
    public static final String MENSAJE = "Recuerde que el formato del archivo para cargar nuevos asociados es: NOMBRE y CEDULA respectivamente.";
    public static final String ERRORBDC = "El formato requerido no es correcto. Recuerde que es: NOMBRE, CEDULA, NUMERO respectivamente y con su tipo de dato correspondiente.";
    public static final String ERROR_LOGIN = "Error de inicio de sesión.";
    public static final String PREMIO = "Ingrese valor del premio.";
    public static final String REXITOSO = "Todos los números fueron asignados a todos los empleados habilitados.";
    public static final String YGENERADOS = "Ya este año han sido generados los números.";
    public static final String NOHAY = "No hay números asignados a los asociados para este año. Vaya a operaciones > asignar números.";
    public static final String EXISTE = "Ya existe una persona registrada con la cédula ";
    public static final String MSG = "Se realizó el cambio efectivamente.";

    /*Mensajes de operaciones*/
    public static final String I_SORTEO = "Para realizar los sorteos, se hace necesario ingresar el número total a realizar";
    public static final String C_VALIDA = "Favor ingresar una cantidad válida.";
    public static final String COINCIDENCIA = "Los valores no coinciden, verifiquelos.";
    public static final String RERROR = "Ocurrió un error durante el proceso. Inténtelo nuevamente.";
    public static final String LOGIN_VER = "Verifique los campos ingresados e intente nuevamente.";
}
