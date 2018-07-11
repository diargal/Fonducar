package Controlador;

import DataAcces.AccesoBD;
import static Modelo.Mensajes.COPIA;
import static Modelo.Mensajes.ERRORBDC;
import static Modelo.Mensajes.INFORME;
import static Modelo.Mensajes.MENSAJE;
import static Modelo.Mensajes.RESTAURACION;
import Modelo.Peticiones;
import Modelo.Sorteo;
import Vista.Informes.Informe;
import com.mysql.jdbc.PreparedStatement;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.Statement;
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
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JRViewer;
import org.apache.poi.openxml4j.exceptions.InvalidOperationException;

/**
 *
 * @author Diego García
 */
public class ControlArchivos {

    public ControlHistorial control;
    public static Informe ventanaInfo;
    public static Sorteo sorteo;

    public ControlArchivos() {
        control = new ControlHistorial();
        ventanaInfo = new Informe(null, true);
        sorteo = new Sorteo();
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
                cntrl.Importar(archivo);
            } else {
                JOptionPane.showMessageDialog(null, "Elija un formato válido");
            }
        } catch (IllegalStateException | InvalidOperationException e) {
            JOptionPane.showMessageDialog(null, ERRORBDC, "Error de lectura", JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException exc) {
        }
    }

    public boolean generarArchivo(JTable tablita, int numero, String nombre, String item, String filtro, JLabel label) throws Exception {
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
//                jasperReport = JasperCompileManager.compileReport(this.getClass().getClassLoader().getResourceAsStream("Vista/Informes/historialNumeros.jrxml"));
                jasperReport = (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResourceAsStream("Vista/Informes/historialNumeros.jasper"));
                break;
            case 2:
//                jasperReport = JasperCompileManager.compileReport(this.getClass().getClassLoader().getResourceAsStream("Vista/Informes/HistorialSorteos.jrxml"));
                jasperReport = (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResourceAsStream("Vista/Informes/HistorialSorteos.jasper"));
                break;
            case 3:
                jasperReport = (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResourceAsStream("Vista/Informes/HistorialEXAS.jasper"));
//                jasperReport = JasperCompileManager.compileReport(this.getClass().getClassLoader().getResourceAsStream("Vista/Informes/HistorialEXAS.jrxml"));
                break;
            case 4:
                jasperReport = (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResourceAsStream("Vista/Informes/HistorialModificaciones.jasper"));
//                jasperReport = JasperCompileManager.compileReport(this.getClass().getClassLoader().getResourceAsStream("Vista/Informes/HistorialModificaciones.jrxml"));
                break;
            case 5:
                jasperReport = (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResourceAsStream("Vista/Informes/NumerosActuales.jasper"));
//                jasperReport = JasperCompileManager.compileReport(this.getClass().getClassLoader().getResourceAsStream("Vista/Informes/NumerosActuales.jrxml"));
                break;
            case 6:
                jasperReport = (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResourceAsStream("Vista/Informes/HistorialEXAS.jasper"));
//                jasperReport = JasperCompileManager.compileReport(this.getClass().getClassLoader().getResourceAsStream("Vista/Informes/HistorialEXAS.jrxml"));
                break;
            case 7:
                jasperReport = (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResourceAsStream("Vista/Informes/Administrador.jasper"));
//                jasperReport = JasperCompileManager.compileReport(this.getClass().getClassLoader().getResourceAsStream("Vista/Informes/Administrador.jrxml"));
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
            sorteo.actividad(INFORME + nombre);

        } catch (JRException ex) {
            Logger.getLogger(ControlArchivos.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("error: " + ex.getMessage().toString());
        }
        return true;

    }

    public boolean crearBackup() {
        int resp;
        DateFormat fecha = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        JFileChooser chooser = new JFileChooser();
        resp = chooser.showSaveDialog(ventanaInfo);
        if (resp == JFileChooser.APPROVE_OPTION) {

            Runtime runtime = Runtime.getRuntime();
            File backupFile = new File(String.valueOf(chooser.getSelectedFile().toString())
                    + "_" + fecha.format(date) + ".sql");
            System.out.println("si pasa");
            FileWriter fw;
            try {
                fw = new FileWriter(backupFile);

                Process child = runtime.exec("C:\\wamp\\bin\\mysql\\mysql5.6.17\\bin\\mysqldump --opt --password=Fonducar**BonoSolidario2018* --user=root --databases fonducarbs  -R");
                InputStreamReader irs = new InputStreamReader(child.getInputStream());
                BufferedReader br = new BufferedReader(irs);

                String line;
                while ((line = br.readLine()) != null) {
                    fw.write(line + "\n");
                }
                fw.close();
                irs.close();
                br.close();
                sorteo.actividad(COPIA);
            } catch (IOException e) {
                return false;
            }
        }
        return true;
    }

    public boolean restaurarBackup() {
        try {
            File archivo;
            JFileChooser selecArchivo = new JFileChooser();
            selecArchivo.setFileFilter(new FileNameExtensionFilter("SQL (*.sql)", "sql"));
            selecArchivo.showDialog(null, "Seleccionar archivo");
            archivo = selecArchivo.getSelectedFile();

            String path = archivo.getAbsolutePath();
            int c = JOptionPane.showConfirmDialog(null, "Desea Restaurar esta Base de datos", "mensaje de confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (c == JOptionPane.YES_OPTION) {
                Peticiones pet = new Peticiones();

                Statement con = (Statement) pet.getAcces().getConnect().createStatement();
                con.execute("drop database if exists apafa;");
                con.close();
                con = (Statement) pet.getAcces().getConnect().createStatement();
                con.execute("create database apafa");
                con.close();
                String backup = "cmd /c mysql --user=root  --password=Fonducar**BonoSolidario2018* fonducarbs < " + path;
                Process child = Runtime.getRuntime().exec(backup);
                JOptionPane.showMessageDialog(null, "La Base de Datos ha sido Restaurada Correctamente");
                return true;
            }

        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println(e);
        }
        return false;
    }
}
