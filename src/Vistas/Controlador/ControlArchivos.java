package Vistas.Controlador;

import static Logica.Mensajes.ERRORBDC;
import static Logica.Mensajes.MENSAJE;
import Vistas.Informe.Informe;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JRViewer;
import org.apache.poi.openxml4j.exceptions.InvalidOperationException;

/**
 *
 * @author Diego García
 */
public class ControlArchivos {

    public ControlHistorial control;
    public static Informe ventanaInfo;

    public ControlArchivos() {
        control = new ControlHistorial();
        ventanaInfo = new Informe(null, true);
    }

    /*
    Permite cargar del PC, el archivo de excel con el cual ingresarán asociados
     */
    public void cargarArchivo(ControlHistorial cntrl) {
//        cambiarApariencia(true);
        try {
            File archivo;
            JFileChooser selecArchivo = new JFileChooser();

            JOptionPane.showMessageDialog(null, MENSAJE, "Información importante", JOptionPane.INFORMATION_MESSAGE);

            selecArchivo.setFileFilter(new FileNameExtensionFilter("Excel (*.xls)", "xls"));
            selecArchivo.setFileFilter(new FileNameExtensionFilter("Excel (*.xlsx)", "xlsx"));
            selecArchivo.showDialog(null, "Seleccionar archivo");

            archivo = selecArchivo.getSelectedFile();

            if (archivo.getName().endsWith("xls") || archivo.getName().endsWith("xlsx")) {
                JOptionPane.showMessageDialog(null, "Importación exitosa");
//                cambiarApariencia(false);
                control.Importar(archivo);
            } else {
                JOptionPane.showMessageDialog(null, "Elija un formato válido");
            }
        } catch (IllegalStateException | InvalidOperationException e) {
            JOptionPane.showMessageDialog(null, ERRORBDC, "Error de lectura", JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException exc) {
        }
//        cambiarApariencia(false);
    }

    public boolean generarArchivo(JTable tablita, int numero, String nombre, String item, String filtro, JLabel label) throws JRException {
        DateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        Map map = new HashMap();
        DefaultTableModel tabla = new DefaultTableModel();
        Object[] columnas = new Object[tablita.getColumnCount()];
        JasperReport jasperReport = null;

        map.put("Titulo", String.valueOf(nombre));
        if (!filtro.isEmpty()) {
            map.put("Subtitulo", "Filtrado por " + item + ", con valor de " + filtro);
        } else {
            map.put("Subtitulo", "Tabla original sin filtro aplicado");
        }
        map.put("Fecha", fecha.format(date));

        //Creo las columnas de la nueva tabla
        for (int c = 0; c < tablita.getColumnCount(); c++) {
            tabla.addColumn(tablita.getColumnName(c));
            System.out.println("Nombre Columna: " + tablita.getColumnName(c));
        }

        //Lleno la nueva tabla que creé
        for (int f = 0; f < tablita.getRowCount(); f++) {
            for (int c = 0; c < tablita.getColumnCount(); c++) {
                columnas[c] = String.valueOf(tablita.getValueAt(f, c));
            }
            tabla.addRow(columnas);
        }

        //Aquí obtengo la dirección del informe que se generará
        switch (numero) {
            case 1:
                jasperReport = JasperCompileManager.compileReport(this.getClass().getClassLoader().getResourceAsStream("Vistas/Informe/historialNumeros.jrxml"));
                break;
            case 2:
                jasperReport = JasperCompileManager.compileReport(this.getClass().getClassLoader().getResourceAsStream("Vistas/Informe/HistorialSorteos.jrxml"));
                break;
            case 3:
                jasperReport = JasperCompileManager.compileReport(this.getClass().getClassLoader().getResourceAsStream("Vistas/Informe/HistorialEXAS.jrxml"));
                break;
            case 4:
                jasperReport = JasperCompileManager.compileReport(this.getClass().getClassLoader().getResourceAsStream("Vistas/Informe/HistorialModificaciones.jrxml"));
                break;
            case 5:
                jasperReport = JasperCompileManager.compileReport(this.getClass().getClassLoader().getResourceAsStream("Vistas/Informe/NumerosActuales.jrxml"));
                break;
            case 6:
                jasperReport = JasperCompileManager.compileReport(this.getClass().getClassLoader().getResourceAsStream("Vistas/Informe/HistorialEXAS.jrxml"));
                break;
            case 7:
                jasperReport = JasperCompileManager.compileReport(this.getClass().getClassLoader().getResourceAsStream("Vistas/Informe/Administrador.jrxml"));
                break;

        }

//        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResourceAsStream("Vistas/Informe/NumerosActuales.jrxml"));
        try {
            DefaultTableModel model = (DefaultTableModel) tablita.getModel();
            JasperPrint jPrint;

            jPrint = JasperFillManager.fillReport(jasperReport, map, new JRTableModelDataSource(tabla));
//            jPrint = JasperFillManager.fillReport(jasperReport, map, new JRBeanCollectionDataSource(numero));
//            JasperViewer.viewReport(jPrint, true);

            JRViewer jv = new JRViewer(jPrint);
            label.setVisible(false);
            ventanaInfo.setContentPane(jv);
            ventanaInfo.setVisible(true);

        } catch (JRException ex) {
            Logger.getLogger(ControlArchivos.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("");
        return true;

    }

    public void cambiarApariencia(boolean tipo) {
        try {
            if (tipo) {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            } else {
                UIManager.setLookAndFeel("com.jgoodies.looks.plastic.PlasticLookAndFeel");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControlArchivos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ControlArchivos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ControlArchivos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(ControlArchivos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
