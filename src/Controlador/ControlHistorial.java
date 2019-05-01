package Controlador;

import Vista.Historial;
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
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
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
    private ArrayList<String> array;

    public ControlHistorial() {
        historial = new Historial(null, true);
        array = new ArrayList<>();
        locale = new Locale("es", "CO");
        nf = NumberFormat.getCurrencyInstance(locale);
    }

    /*
    Muestra en la tabla historial, todos los datos leidos del archivo de excel cargado para guardar los asociados
     */
    public boolean Importar(File file, boolean op) {
        historial = new Historial(null, true);
        historial.setArchivo(file);
        archivo = file;
        Workbook wb;

        String mensaje = op ? "Anexo de asociados" : "Importación de asociados y sus números para el año en curso.";

        aspectosGenerales(mensaje, true);

        DefaultTableModel modeloT = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        historial.getJTHistorial().setModel(modeloT);
        historial.getJTHistorial().setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        historial.setOpcionImportar(op);

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
        }

        return false;
    }

    /*
    Carga el historial de los números asignados a todos los asociados a lo largo del tiempo
     */
    public void historialNumeros(ResultSet resul) { //1

        historial = new Historial(null, true);
        aspectosGenerales("Información del historial de los números asignados a cada asociado", false);

        DefaultTableModel tabla = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // DefaultTableModel modelo2 = new DefaultTableModel();
        historial.getJTHistorial().setModel(tabla);

        tabla.addColumn("NOMBRE");
        tabla.addColumn("APELLIDO");
        tabla.addColumn("CEDULA");
        tabla.addColumn("NUMERO");
        tabla.addColumn("FECHA");
        Object[] object = new Object[5];
        array = new ArrayList<>();
        array.add("NOMBRE");
        array.add("APELLIDO");
        array.add("CEDULA");
        array.add("NUMERO");
        array.add("FECHA");
        llenarComboBox();

        try {
            while (resul.next()) {
                object[0] = resul.getString(1);
                object[1] = resul.getString(2);
                object[2] = resul.getLong(3);
                if ((resul.getInt(4) < 1000) && (resul.getInt(4) >= 100)) {
                    object[3] = "0" + resul.getInt(4);
                } else if ((resul.getInt(4) < 100) && (resul.getInt(4) >= 10)) {
                    object[3] = "00" + resul.getInt(4);
                } else if (resul.getInt(4) < 10) {
                    object[3] = "000" + resul.getInt(4);
                }

                object[4] = dividirFecha(resul.getString(5));

                tabla.addRow(object);
            }

            RowSorter<TableModel> sorter = new TableRowSorter<>(tabla);
            historial.getJTHistorial().setRowSorter(sorter);
            historial.setNumeroInforme(1);
            historial.getJTHistorial().setModel(tabla);
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

        historial.getJTHistorial().setModel(tabla);

        tabla.addColumn("NOMBRE");
        tabla.addColumn("APELLIDO");
        tabla.addColumn("CEDULA");
        tabla.addColumn("FECHA");
        tabla.addColumn("NUMERO");
        tabla.addColumn("PREMIO (PESOS)");
        tabla.addColumn("TIPO PREMIO");
        Object[] object = new Object[7];
        array = new ArrayList<>();
        array.add("NOMBRE");
        array.add("APELLIDO");
        array.add("CEDULA");
        array.add("FECHA");
        array.add("NUMERO");
        array.add("PREMIO (PESOS)");
        array.add("TIPO");
        llenarComboBox();

        try {
            while (resul.next()) {
                object[0] = resul.getString(1);
                object[1] = resul.getString(2);
                object[2] = resul.getLong(3);
                object[3] = dividirFecha(resul.getString(4));

                if (resul.getInt(5) >= 1000) {
                    object[4] = resul.getInt(5);
                } else {
                    if (resul.getInt(5) < 1000 && resul.getInt(5) >= 100) {
                        object[4] = "0" + resul.getInt(5);
                    } else {
                        if ((resul.getInt(5) < 100) && (resul.getInt(5) >= 10)) {
                            object[4] = "00" + resul.getInt(5);
                        } else {
                            if (resul.getInt(5) < 10) {
                                object[4] = "000" + resul.getInt(5);
                            }
                        }
                    }
                }

                object[5] = nf.format(resul.getLong(6));
                if (resul.getInt(7) == 0) {
                    object[6] = "Premio menor";
                } else if (resul.getInt(7) == 1) {
                    object[6] = "Premio mayor";
                }

                tabla.addRow(object);
            }
            RowSorter<TableModel> sorter = new TableRowSorter<>(tabla);
            historial.getJTHistorial().setRowSorter(sorter);
            historial.setNumeroInforme(2);
            historial.getJTHistorial().setModel(tabla);
            historial.getJBSubir().setEnabled(true);

        } catch (SQLException ex) {
            Logger.getLogger(Historial.class.getName()).log(Level.SEVERE, null, ex);
        }

        historial.setVisible(true);
    }

    public void historialExA(ResultSet resul, String titulo) {

        historial = new Historial(null, true);
        aspectosGenerales("Historial de los ex-asociados " + titulo + " participación", false);

        DefaultTableModel tabla = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        historial.getJTHistorial().setModel(tabla);

        tabla.addColumn("NOMBRE ASOCIADO");
        tabla.addColumn("APELLIDO ASOCIADO");
        tabla.addColumn("CEDULA ASOCIADO");
        tabla.addColumn("NUMERO ASOCIADO");
        tabla.addColumn("FECHA INHABILITACION");
        tabla.addColumn("RAZON");
        Object[] object = new Object[6];
        array = new ArrayList<>();
        array.add("NOMBRE ASOCIADO");
        array.add("APELLIDO ASOCIADO");
        array.add("CEDULA ASOCIADO");
        array.add("NUMERO ASOCIADO");
        array.add("FECHA INHABILITACION");
        array.add("RAZON");
        llenarComboBox();

        try {
            while (resul.next()) {
                object[0] = resul.getString(1);
                object[1] = resul.getString(2);
                object[2] = resul.getLong(3);

                int numero = resul.getInt(4);
                if (numero >= 1000) {
                    object[3] = numero;
                } else if (numero >= 100) {
                    object[3] = "0" + numero;
                } else if (numero >= 10) {
                    object[3] = "00" + numero;
                } else {
                    object[3] = "000" + numero;
                }

                object[4] = dividirFecha(resul.getString(5));
                object[5] = resul.getString(6);
                tabla.addRow(object);
            }

            RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tabla);
            historial.getJTHistorial().setRowSorter(sorter);
            historial.setNumeroInforme(3);
            historial.getJTHistorial().setModel(tabla);
            historial.getJBSubir().setEnabled(true);
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
        aspectosGenerales("Historial de operaciones y movimientos", false);

        DefaultTableModel tabla = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        historial.getJTHistorial().setModel(tabla);

        tabla.addColumn("NOMBRE ADMINISTRADOR");
        tabla.addColumn("APELLIDO ADMINISTRADOR");
        tabla.addColumn("CEDULA ADMINISTRADOR");
        tabla.addColumn("FECHA REALIZACION");
        tabla.addColumn("DETALLE OPERACION");
        Object[] object = new Object[5];
        array = new ArrayList<>();
        array.add("NOMBRE ADMINISTRADOR");
        array.add("APELLIDO ADMINISTRADOR");
        array.add("CEDULA ADMINISTRADOR");
        array.add("FECHA REALIZACION");
        array.add("DETALLE OPERACION");
        llenarComboBox();

        try {
            while (resul.next()) {

                object[0] = resul.getString(1);
                object[1] = resul.getString(2);
                object[2] = resul.getLong(3);
                object[3] = dividirFecha(resul.getString(4));
                object[4] = resul.getString(5);
                tabla.addRow(object);
            }

            RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tabla);
            historial.getJTHistorial().setRowSorter(sorter);
            historial.setNumeroInforme(4);
            historial.getJTHistorial().setModel(tabla);
//            historial.getJBSubir().setText("Generar archivo");
            historial.getJBSubir().setEnabled(true);
//            accesoBD.desconectar();

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
        aspectosGenerales("Informe de los números actuales de cada asociado y ex-asociado con participación", false);

        DefaultTableModel tabla = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        DefaultTableModel modelo2 = new DefaultTableModel();

        historial.getJTHistorial().setModel(modelo2);

        tabla.addColumn("NOMBRE");
        tabla.addColumn("APELLIDO");
        tabla.addColumn("CEDULA");
        tabla.addColumn("NUMERO");
        tabla.addColumn("ESTADO");
        Object[] object = new Object[5];
        array = new ArrayList<>();
        array.add("NOMBRE");
        array.add("APELLIDO");
        array.add("CEDULA");
        array.add("NUMERO");
        array.add("ESTADO");
        llenarComboBox();

        try {
            while (resul.next()) {
                object[0] = resul.getString(1);
                object[1] = resul.getString(2);
                object[2] = resul.getLong(3);
                if ((resul.getInt(4) < 1000) && (resul.getInt(4) >= 100)) {
                    object[3] = "0" + resul.getInt(4);
                } else if ((resul.getInt(4) < 100) && (resul.getInt(4) >= 10)) {
                    object[3] = "00" + resul.getInt(4);
                } else if (resul.getInt(4) < 10) {
                    object[3] = "000" + resul.getInt(4);

                }
                object[4] = resul.getString(5);
                tabla.addRow(object);
            }

            RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tabla);
            historial.getJTHistorial().setRowSorter(sorter);
            historial.setNumeroInforme(5);
            historial.getJTHistorial().setModel(tabla);
            historial.getJBSubir().setEnabled(true);

        } catch (SQLException ex) {
            Logger.getLogger(Historial.class.getName()).log(Level.SEVERE, null, ex);
        }

        historial.setVisible(true);
    }

    public void historialInhabilitadosActuales(ResultSet resul, String titulo) {
        historial = new Historial(null, true);
        aspectosGenerales("Ex-asociados " + titulo + " participación en los sorteos de este año", false);

        DefaultTableModel tabla = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        historial.getJTHistorial().setModel(tabla);

        tabla.addColumn("NOMBRE ASOCIADO");
        tabla.addColumn("APELLIDO ASOCIADO");
        tabla.addColumn("CEDULA ASOCIADO");
        tabla.addColumn("NUMERO ASOCIADO");
        tabla.addColumn("FECHA INHABILITACION");
        tabla.addColumn("RAZON");
        Object[] object = new Object[6];
        array = new ArrayList<>();
        array.add("NOMBRE ASOCIADO");
        array.add("APELLIDO ASOCIADO");
        array.add("CEDULA ASOCIADO");
        array.add("NUMERO ASOCIADO");
        array.add("FECHA INHABILITACION");
        array.add("RAZON");
        llenarComboBox();

        try {
            while (resul.next()) {
                object[0] = resul.getString(1);
                object[1] = resul.getString(2);
                object[2] = resul.getLong(3);

                int numero = resul.getInt(4);
                if (numero >= 1000) {
                    object[3] = numero;
                } else if (numero >= 100) {
                    object[3] = "0" + numero;
                } else if (numero >= 10) {
                    object[3] = "00" + numero;
                } else {
                    object[3] = "000" + numero;
                }

                object[4] = dividirFecha(resul.getString(5));
                object[5] = resul.getString(6);
                tabla.addRow(object);
            }

            RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tabla);
            historial.getJTHistorial().setRowSorter(sorter);
            historial.setNumeroInforme(6);
            historial.getJTHistorial().setModel(tabla);
//             historial.getJBSubir().setText("Generar archivo");
            historial.getJBSubir().setEnabled(true);
//            accesoBD.desconectar();

        } catch (SQLException ex) {
            Logger.getLogger(Historial.class
                    .getName()).log(Level.SEVERE, null, ex);
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
        tabla.addColumn("Apellido ex-asociado");
        tabla.addColumn("Cédula ex-asociado");
        Object[] object = new Object[3];
        array = new ArrayList<>();
        array.add("Nombre ex-asociado");
        array.add("Apellido ex-asociado");
        array.add("Cédula ex-asociado");
        llenarComboBox();

        try {
            while (resul.next()) {
                object[0] = resul.getString(1);
                object[1] = resul.getString(2);
                object[2] = resul.getLong(3);
                tabla.addRow(object);
            }

            RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tabla);
            historial.getJTHistorial().setRowSorter(sorter);
            historial.setNumeroInforme(0);
            historial.getJTHistorial().setModel(tabla);
//            historial.getJBSubir().setText("Generar archivo");
            historial.getJBSubir().setEnabled(true);
//            accesoBD.desconectar();

        } catch (SQLException ex) {
            Logger.getLogger(Historial.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        historial.setVisible(true);
    }

    public void verAdministradores(ResultSet resul) {
        historial = new Historial(null, true);
        aspectosGenerales("Administradores registrados", false);

        DefaultTableModel tabla = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        DefaultTableModel modelo2 = new DefaultTableModel();
        historial.getJTHistorial().setModel(modelo2);

        tabla.addColumn("USUARIO");
        tabla.addColumn("NOMBRE ADMINISTRADOR");
        tabla.addColumn("APELLIDO ADMINISTRADOR");
        tabla.addColumn("CEDULA ADMINISTRADOR");
        tabla.addColumn("ACTIVO");
        Object[] object = new Object[5];
        array = new ArrayList<>();
        array.add("USUARIO");
        array.add("NOMBRE ADMINISTRADOR");
        array.add("APELLIDO ADMINISTRADOR");
        array.add("CEDULA ADMINISTRADOR");
        array.add("ACTIVO");
        llenarComboBox();

        try {
            while (resul.next()) {
                object[0] = resul.getString(1);
                object[1] = resul.getString(2);
                object[2] = resul.getString(3);
                object[3] = resul.getLong(4);
                object[4] = resul.getString(5);
                tabla.addRow(object);
            }

            RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tabla);
            historial.getJTHistorial().setRowSorter(sorter);
            historial.setNumeroInforme(7);
            historial.getJTHistorial().setModel(tabla);
            historial.getJBSubir().setEnabled(true);
//            accesoBD.desconectar();

        } catch (SQLException ex) {
            Logger.getLogger(Historial.class
                    .getName()).log(Level.SEVERE, null, ex);
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
        if (valor) {
            historial.getJBSubir().setText("Subir a la BD");
        } else {
            historial.getJBSubir().setText("Generar informe");
        }
    }

    /*
    Recibe los nombres de las columnas que componen la tabla en cuestión, para crear el combobox para filtrar los datos de la tabla correspondiente
     */
    public void llenarComboBox() {
        JComboBox jc = new JComboBox();
        for (int i = 0; i < array.size(); i++) {
            jc.addItem(array.get(i));
        }
        historial.getJCBFiltro().setModel(jc.getModel());
    }

    public String dividirFecha(String fecha) {
        String dia = fecha.substring(0, 2);
        String mes = fecha.substring(3, 5);
        String anio = fecha.substring(6, 10);
        String hora = fecha.substring(11);
        return anio + "/" + mes + "/" + dia + " " + hora;
    }

}
