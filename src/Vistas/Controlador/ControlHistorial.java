package Vistas.Controlador;

import static Logica.BonoSolidario.accesoBD;
import static Logica.Mensajes.A_HABILACTUALES;
import static Logica.Mensajes.A_HACON;
import static Logica.Mensajes.A_HASIN;
import static Logica.Mensajes.A_HNS;
import static Logica.Mensajes.A_HOPERACIONES;
import static Logica.Mensajes.A_INHABILACTUALES;
import static Logica.Mensajes.A_RACTUALES;
import static Logica.Mensajes.A_REPORTESORTEOS;
import Vistas.Historial;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 *
 * @author Diego García
 */
public class ControlHistorial {

    private Historial historial;
    private File archivo;
    private Locale locale;
    private NumberFormat nf;
    ArrayList<String> array;

    public ControlHistorial() {
        historial = new Historial(null, true);
        array = new ArrayList<>();
        locale = new Locale("es", "CO");
        nf = NumberFormat.getCurrencyInstance(locale);
    }

    /*
    Muestra en la tabla historial, todos los datos leidos del archivo de excel cargado para guardar los asociados
     */
    public boolean Importar(File file) {
        historial = new Historial(null, true);
        historial.setArchivo(file);
        archivo = file;
        historial.getJBSubir().setText("Subir a la BD");
        Workbook wb;

        aspectosGenerales("Anexo de asociados", true);

        DefaultTableModel modeloT = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        historial.getJTHistorial().setModel(modeloT);
        historial.getJTHistorial().setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        try {
            wb = WorkbookFactory.create(new FileInputStream(archivo));
            Sheet hoja = wb.getSheetAt(0);
            Iterator filaIterator = hoja.rowIterator();
            int indiceFila = -1;
            while (filaIterator.hasNext()) {
                indiceFila++;
                Row fila = (Row) filaIterator.next();
                Iterator columnaIterator = fila.cellIterator();
                Object[] listaColumna = new Object[3000];
                int indiceColumna = -1;
                while (columnaIterator.hasNext()) {
                    indiceColumna++;
                    Cell celda = (Cell) columnaIterator.next();
                    if (indiceFila == 0) {
                        modeloT.addColumn(celda.getStringCellValue());
                    } else {
                        if (celda != null) {
                            switch (celda.getCellType()) {
                                case Cell.CELL_TYPE_NUMERIC:
                                    listaColumna[indiceColumna] = (int) Math.round(celda.getNumericCellValue());
                                    break;
                                case Cell.CELL_TYPE_STRING:
                                    listaColumna[indiceColumna] = celda.getStringCellValue();
                                    break;
                                case Cell.CELL_TYPE_BOOLEAN:
                                    listaColumna[indiceColumna] = celda.getBooleanCellValue();
                                    break;
                                default:
                                    listaColumna[indiceColumna] = celda.getDateCellValue();
                                    break;
                            }
                            //  System.out.println("col" + indiceColumna + " valor: true - " + celda + ".");
                        }
                    }
                }
                if (indiceFila != 0) {
                    modeloT.addRow(listaColumna);
                }
            }

            historial.setVisible(true);

            return true;
        } catch (IOException | InvalidFormatException | EncryptedDocumentException e) {
            System.err.println(e.getMessage());
            System.out.println(e);
        }

        return false;
    }

    /*
    Carga el historial de los números asignados a todos los asociados a lo largo del tiempo
     */
    public void historialNumeros(ResultSet resul) {

        historial = new Historial(null, true);
        aspectosGenerales("Información del historial de los números asignados a cada asociado", false);

        DefaultTableModel tabla = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        DefaultTableModel modelo2 = new DefaultTableModel();
        historial.getJTHistorial().setModel(modelo2);

        tabla.addColumn("Nombre Asociado");
        tabla.addColumn("Cédula Asociado");
        tabla.addColumn("Número Asignado");
        tabla.addColumn("Fecha de Asignación");
        Object[] object = new Object[4];
        array = new ArrayList<>();
        array.add("Nombre Asociado");
        array.add("Cédula Asociado");
        array.add("Número Asignado");
        array.add("Fecha de Asignación");
        llenarComboBox();

        try {
            while (resul.next()) {
                object[0] = resul.getString(1);
                object[1] = resul.getLong(2);
                object[2] = resul.getInt(3);
                object[3] = resul.getString(4);
                tabla.addRow(object);
            }
            historial.getJTHistorial().setModel(tabla);
            accesoBD.guardarOperacion(A_HNS);
            historial.getJBSubir().setText("Descargar archivo");
            historial.getJBSubir().setEnabled(true);
        } catch (SQLException ex) {
            Logger.getLogger(Historial.class.getName()).log(Level.SEVERE, null, ex);
        }

        historial.setVisible(true);
    }

    /*
    Carga al historial, el resultado del acceso a la BD para saber el historial de todos los sorteos que se han realizado
    a lo largo del tiempo.
     */
    public void historialSorteos(ResultSet resul) {

        historial = new Historial(null, true);
        aspectosGenerales("Historial de los sorteos", false);

        DefaultTableModel tabla = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        DefaultTableModel modelo2 = new DefaultTableModel();
        historial.getJTHistorial().setModel(modelo2);

        tabla.addColumn("Nombre Ganador");
        tabla.addColumn("Cédula Ganador");
        tabla.addColumn("Hora y Fecha Sorteo");
        tabla.addColumn("Número Ganador");
        tabla.addColumn("Premio (Pesos)");
        tabla.addColumn("Tipo de Sorteo");
        Object[] object = new Object[6];
        array = new ArrayList<>();
        array.add("Nombre Ganador");
        array.add("Cédula Ganador");
        array.add("Hora y Fecha Sorteo");
        array.add("Número Ganador");
        array.add("Premio (Pesos)");
        array.add("Tipo de Sorteo");
        llenarComboBox();

        try {
            while (resul.next()) {
                object[0] = resul.getString(1);
                object[1] = resul.getLong(2);
                object[2] = resul.getString(3);
                if ((resul.getInt(4) < 100) && (resul.getInt(4) >= 10)) {
                    object[3] = "0" + resul.getInt(4);
                } else {
                    if (resul.getInt(4) < 10) {
                        object[3] = "00" + resul.getInt(4);
                    }
                }

                object[4] = nf.format(resul.getLong(5));
                if (resul.getInt(6) == 0) {
                    object[5] = "Premio menor";
                } else if (resul.getInt(6) == 1) {
                    object[5] = "Premio mayor";
                }
                tabla.addRow(object);
            }

            historial.getJTHistorial().setModel(tabla);
            accesoBD.guardarOperacion(A_REPORTESORTEOS);
            historial.getJBSubir().setText("Descargar archivo");
            historial.getJBSubir().setEnabled(true);

        } catch (SQLException ex) {
            Logger.getLogger(Historial.class.getName()).log(Level.SEVERE, null, ex);
        }

        historial.setVisible(true);
    }

    public void historialExA(ResultSet resul, boolean tipo) {

        historial = new Historial(null, true);
        aspectosGenerales("Historial de los ex-asociados SIN participación", false);

        DefaultTableModel tabla = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        DefaultTableModel modelo2 = new DefaultTableModel();
        historial.getJTHistorial().setModel(modelo2);

        tabla.addColumn("Nombre Asociado");
        tabla.addColumn("Cédula Asociado");
        tabla.addColumn("Fecha inhabilitación");
        tabla.addColumn("Razón");
        Object[] object = new Object[4];
        array = new ArrayList<>();
        array.add("Nombre Asociado");
        array.add("Cédula Asociado");
        array.add("Fecha inhabilitación");
        array.add("Razón");
        llenarComboBox();

        try {
            while (resul.next()) {
                object[0] = resul.getString(1);
                object[1] = resul.getLong(2);
                object[2] = resul.getString(3);
                object[3] = resul.getString(4);
                tabla.addRow(object);
            }
            if (tipo) {
                accesoBD.guardarOperacion(A_HACON);
            } else {
                accesoBD.guardarOperacion(A_HASIN);
            }
            historial.getJTHistorial().setModel(tabla);
            historial.getJBSubir().setText("Descargar archivo");
            historial.getJBSubir().setEnabled(true);
            accesoBD.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(Historial.class.getName()).log(Level.SEVERE, null, ex);
        }

        historial.setVisible(true);
    }

    /*
    Carga el historial de todas las operaciones realizadas por el administrador
     */
    public void historialModificaciones(ResultSet resul) {

        historial = new Historial(null, true);
        aspectosGenerales("Historial de moficaciones", false);

        DefaultTableModel tabla = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        DefaultTableModel modelo2 = new DefaultTableModel();
        historial.getJTHistorial().setModel(modelo2);

        tabla.addColumn("Hora y Fecha Realización");
        tabla.addColumn("Cédula Administrador");
        tabla.addColumn("Nombre Administrador");
        tabla.addColumn("Detalle Operación");
        Object[] object = new Object[4];
        array = new ArrayList<>();
        array.add("Hora y Fecha Realización");
        array.add("Cédula Administrador");
        array.add("Nombre Administrador");
        array.add("Detalle Operación");
        llenarComboBox();

        try {
            int cont = 0;
            while (resul.next()) {
                cont++;
                object[0] = resul.getString(3);
                object[1] = resul.getLong(2);
                object[2] = resul.getString(1);
                object[3] = resul.getString(4);
                tabla.addRow(object);
            }
            System.out.println(cont);
            historial.getJTHistorial().setModel(tabla);
            accesoBD.guardarOperacion(A_HOPERACIONES);
            historial.getJBSubir().setText("Descargar archivo");
            historial.getJBSubir().setEnabled(true);
            accesoBD.desconectar();

        } catch (SQLException ex) {
            Logger.getLogger(Historial.class.getName()).log(Level.SEVERE, null, ex);
        }

        historial.setVisible(true);
    }

    /*
    Carga una lista del número que tiene actualmente cada participante activo
     */
    public void numerosActuales(ResultSet resul) {
        historial = new Historial(null, true);
        aspectosGenerales("Lista de los números actuales de cada asociado y ex-asocaiado con participación", false);

        DefaultTableModel tabla = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        DefaultTableModel modelo2 = new DefaultTableModel();

        historial.getJTHistorial().setModel(modelo2);

        tabla.addColumn("Nombre");
        tabla.addColumn("Cédula");
        tabla.addColumn("Número");
        Object[] object = new Object[3];
        array = new ArrayList<>();
        array.add("Nombre");
        array.add("Cédula");
        array.add("Número");
        llenarComboBox();

        try {
            while (resul.next()) {
                object[0] = resul.getString(1);
                object[1] = resul.getInt(2);
                if ((resul.getInt(3) < 100) && (resul.getInt(3) >= 10)) {
                    object[2] = "0" + resul.getInt(3);
                } else {
                    if (resul.getInt(3) < 10) {
                        object[2] = "00" + resul.getInt(3);
                    }
                }
                tabla.addRow(object);
            }
            historial.getJTHistorial().setModel(tabla);
            accesoBD.guardarOperacion(A_RACTUALES);
            historial.getJBSubir().setText("Descargar archivo");
            historial.getJBSubir().setEnabled(true);

        } catch (SQLException ex) {
            Logger.getLogger(Historial.class.getName()).log(Level.SEVERE, null, ex);
        }

        historial.setVisible(true);
    }

    public void historialInhabilitadosActuales(ResultSet resul, boolean tipo) {
        historial = new Historial(null, true);
        aspectosGenerales("Inhabilitados actuales", false);

        DefaultTableModel tabla = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        DefaultTableModel modelo2 = new DefaultTableModel();
        historial.getJTHistorial().setModel(modelo2);

        tabla.addColumn("Nombre Asociado");
        tabla.addColumn("Cédula Asociado");
        tabla.addColumn("Fecha inhabilitación");
        tabla.addColumn("Razón");
        Object[] object = new Object[4];
        array = new ArrayList<>();
        array.add("Nombre Asociado");
        array.add("Cédula Asociado");
        array.add("Fecha inhabilitación");
        array.add("Razón");
        llenarComboBox();

        try {
            while (resul.next()) {
                object[0] = resul.getString(1);
                object[1] = resul.getLong(2);
                object[2] = resul.getString(3);
                object[3] = resul.getString(4);
                tabla.addRow(object);
            }
            if (tipo) {
                accesoBD.guardarOperacion(A_HABILACTUALES);
            } else {
                accesoBD.guardarOperacion(A_INHABILACTUALES);
            }
            historial.getJTHistorial().setModel(tabla);
            historial.getJBSubir().setText("Descargar archivo");
            historial.getJBSubir().setEnabled(true);
            accesoBD.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(Historial.class.getName()).log(Level.SEVERE, null, ex);
        }

        historial.setVisible(true);
    }

    public void inhabilitadosAnioPasado(ResultSet resul) {
        historial = new Historial(null, true);
        aspectosGenerales("Ex-asociados del año pasado", false);

        DefaultTableModel tabla = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        DefaultTableModel modelo2 = new DefaultTableModel();
        historial.getJTHistorial().setModel(modelo2);

        tabla.addColumn("Nombre ex-asociado");
        tabla.addColumn("Cédula ex-asociado");
        Object[] object = new Object[2];
        array = new ArrayList<>();
        array.add("Nombre ex-asociado");
        array.add("Cédula ex-asociado");
        llenarComboBox();

        try {
            while (resul.next()) {
                object[0] = resul.getString(1);
                object[1] = resul.getLong(2);
                tabla.addRow(object);
            }
            historial.getJTHistorial().setModel(tabla);
            historial.getJBSubir().setText("Descargar archivo");
            historial.getJBSubir().setEnabled(true);
            accesoBD.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(Historial.class.getName()).log(Level.SEVERE, null, ex);
        }

        historial.setVisible(true);
    }

    /*
    Modifica aspectos generales de la vista en el historial
     */
    public void aspectosGenerales(String titulo, boolean valor) {
        historial.setTitle(titulo);
        historial.getJBSubir().setEnabled(valor);
        historial.setTipoAccion(valor);
        historial.getJPFiltro().setEnabled(!valor);
        historial.getJCBFiltro().setEnabled(!valor);
        historial.getJTxFFiltro().setEnabled(!valor);
    }

    /*
    Recibe los nombres de las columnas que componen la tabla en cuestión, para crear el combobox para filtrar los datos de la tabla correspondiente
     */
    public void llenarComboBox() {
        JComboBox jc = new JComboBox();
        for (int i = 0; i < array.size(); i++) {
            System.out.println(array.get(i));
            jc.addItem(array.get(i));
        }
        historial.getJCBFiltro().setModel(jc.getModel());
    }

}
