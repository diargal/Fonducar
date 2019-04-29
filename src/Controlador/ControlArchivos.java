package Controlador;

import DataAcces.AccesoBD;
import Modelo.Mensajes;
import static Modelo.Mensajes.COPIA;
import static Modelo.Mensajes.ERRORBDC;
import static Modelo.Mensajes.INFORME;
import static Modelo.Mensajes.MENSAJE;
import static Modelo.Mensajes.MENSAJE2;
import static Modelo.Mensajes.RESTAURACION;
import Modelo.Sorteo;
import Vista.Informes.Informe;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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
import net.sf.jasperreports.view.JasperViewer;
import org.apache.poi.openxml4j.exceptions.InvalidOperationException;

/**
 *
 * @author Diego García
 */
public class ControlArchivos {

    private Informe ventanaInfo;
    private Sorteo sorteo;

    public ControlArchivos() {
        ventanaInfo = new Informe(null, true);
        sorteo = new Sorteo();
    }

    /*
    Permite cargar del PC, el archivo de excel con el cual ingresarán asociados
     */
    public void cargarArchivo(ControlHistorial cntrl, boolean op) {
        try {
            File archivo;
            JFileChooser selecArchivo = new JFileChooser();

            String mensaje = op ? MENSAJE : MENSAJE2;
            JOptionPane.showMessageDialog(null, mensaje, "Información importante", JOptionPane.INFORMATION_MESSAGE);

            selecArchivo.setFileFilter(new FileNameExtensionFilter("Excel (*.xls)", "xls"));
            selecArchivo.setFileFilter(new FileNameExtensionFilter("Excel (*.xlsx)", "xlsx"));
            selecArchivo.showDialog(null, "Seleccionar archivo");

            archivo = selecArchivo.getSelectedFile();

            if (archivo.getName().endsWith("xls") || archivo.getName().endsWith("xlsx")) {
                JOptionPane.showMessageDialog(null, "Importación exitosa");
                cntrl.Importar(archivo, op);
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
                jasperReport = (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResourceAsStream("Vista/Informes/historialNumeros.jasper"));
                break;
            case 2:
                jasperReport = (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResourceAsStream("Vista/Informes/HistorialSorteos.jasper"));
                break;
            case 3:
                jasperReport = (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResourceAsStream("Vista/Informes/HistorialEXAS.jasper"));
                break;
            case 4:
                jasperReport = (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResourceAsStream("Vista/Informes/HistorialModificaciones.jasper"));
                break;
            case 5:
                jasperReport = (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResourceAsStream("Vista/Informes/NumerosActuales.jasper"));
                break;
            case 6:
                jasperReport = (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResourceAsStream("Vista/Informes/HistorialEXAS.jasper"));
                break;
            case 7:
                jasperReport = (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResourceAsStream("Vista/Informes/Administrador.jasper"));
                break;

        }
        try {
            DefaultTableModel model = (DefaultTableModel) tablita.getModel();
            JasperPrint jPrint;

            jPrint = JasperFillManager.fillReport(jasperReport, map, new JRTableModelDataSource(tabla));
            JasperViewer.viewReport(jPrint, true);

            JRViewer jv = new JRViewer(jPrint);
            label.setVisible(false);
            ventanaInfo.setContentPane(jv);
            ventanaInfo.setVisible(true);
            sorteo.actividad(INFORME + nombre);

        } catch (JRException ex) {
        }
        return true;

    }

    public int crearBackup(JLabel label) {
        try {
            int resp;
            DateFormat fecha = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss"),
                    fecha2 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date date = new Date();
            JFileChooser chooser = new JFileChooser();
            resp = chooser.showSaveDialog(null);

            if (resp == JFileChooser.APPROVE_OPTION) {
                sorteo.actividad(COPIA);
                File backupFile = new File(String.valueOf(chooser.getSelectedFile().toString() + " " + fecha.format(date) + ".sql"));
                String RutaFile = backupFile.getAbsolutePath();

                String Ruta = "C:\\wamp\\bin\\mysql\\mysql5.6.17\\bin\\mysqldump.exe";
                String clave = "Fonducar**BonoSolidario2018*";
                String user = "root";
                String db = "fonducarbs";

                Process p = Runtime.getRuntime().exec(Ruta + " -u root -p" + clave + " fonducarbs");
                InputStream is = p.getInputStream();
                FileOutputStream fos = new FileOutputStream(RutaFile);

                byte[] buffer = new byte[1000];

                int leido = is.read(buffer);

                while (leido > 0) {
                    fos.write(buffer, 0, leido);
                    leido = is.read(buffer);
                }

                fos.close();

                /*
                String cad = "\"" + Ruta + "\" --opt --password=" + clave + " --user=" + user + " " + db + " > \"" + RutaFile + "\"\n";
                File fcopi = new File("copia_seguridad.bat");
                FileWriter fw = new FileWriter(fcopi);
                fw.write(cad, 0, cad.length());
                fw.close();
                Runtime.getRuntime().exec("copia_seguridad.bat");
                 */
                label.setText("Está trabajando con el backup de fecha y hora: " + fecha2.format(date));
                return 0;
            } else {
                return 1;
            }
        } catch (HeadlessException | IOException ex) {
            System.out.println(ex);
        }
        return 2;
    }

    public int restaurarBackup(JLabel label) {

        try {
            File archivo;
            JFileChooser selecArchivo = new JFileChooser();
            selecArchivo.setFileFilter(new FileNameExtensionFilter("SQL (*.sql)", "sql"));
            selecArchivo.showDialog(null, "Seleccionar archivo");
            archivo = selecArchivo.getSelectedFile();
            if (archivo != null) {
                String RutaFile = archivo.getAbsolutePath();
                String clave = "Fonducar**BonoSolidario2018*";
                String user = "root";
                String db = "fonducarbs";
                System.out.println(RutaFile);

                /* Peticiones pet = new Peticiones();

                Statement con = (Statement) pet.getAcces().getConnect().createStatement();
                con.execute("drop database if exists fonducarbs;");
                con.close();
                con = (Statement) pet.getAcces().getConnect().createStatement();
                con.execute("create database fonducarbs");
                con.close();
                pet.getAcces().desconectar();
                 */
                String Ruta = "C:\\wamp\\bin\\mysql\\mysql5.6.17\\bin\\mysql";

                Process p = Runtime.getRuntime().exec(Ruta + " -u root -p" + clave + " fonducarbs");

                OutputStream os = p.getOutputStream();
                FileInputStream fis = new FileInputStream(RutaFile);

                byte[] buffer = new byte[1000];

                int leido = fis.read(buffer);

                while (leido > 0) {
                    os.write(buffer, 0, leido);
                    leido = fis.read(buffer);
                }

                os.flush();
                os.close();
                fis.close();

                AccesoBD acc = new AccesoBD();
                String fechaBackup;

                if (acc.fechaBackup().isEmpty()) {
                    String cad = Ruta + " --password=" + clave + " --user=" + user + " " + db + " < " + RutaFile;
                    File fcopi = new File("restaurar_copia_seguridad.bat");
                    FileWriter fw = new FileWriter(fcopi);
                    fw.write(cad, 0, cad.length());
                    fw.close();
                    Runtime.getRuntime().exec("restaurar_copia_seguridad.bat");

                    BasicFileAttributes atribute = Files.readAttributes(archivo.toPath(), BasicFileAttributes.class);
                    FileTime time = atribute.lastModifiedTime();
                    System.out.println(time);
                    String pattern = "dd-MM-yyyy HH:mm:ss";
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

                    fechaBackup = simpleDateFormat.format(new Date(time.toMillis()));
                } else {
                    fechaBackup = acc.fechaBackup();
                }

                label.setText("Está trabajando con el backup de fecha y hora: " + fechaBackup);

                // Thread.sleep(5000);
                sorteo.actividad(RESTAURACION);

                return 0;

            } else {

                return 1;

            }
        } catch (HeadlessException | IOException ex) {
            return 2;
        }
    }
}
