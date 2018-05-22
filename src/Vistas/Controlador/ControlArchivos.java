package Vistas.Controlador;

import static Logica.Mensajes.ERRORBDC;
import static Logica.Mensajes.MENSAJE;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author Diego García
 */
public class ControlArchivos {

    public ControlHistorial control;

    public ControlArchivos() {
        control = new ControlHistorial();
    }

    /*
    Permite cargar del PC, el archivo de excel con el cual ingresarán asociados
     */
    public void cargarArchivo(ControlHistorial cntrl) {

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
                control.Importar(archivo);
            } else {
                JOptionPane.showMessageDialog(null, "Elija un formato válido");
            }
        } catch (IllegalStateException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, ERRORBDC, "Error de lectura", JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException exc) {

        }
    }

    public boolean generarArchivo(JTable tablita) {
        javax.swing.JFileChooser fileChooser = new JFileChooser();

        HSSFWorkbook wb = new HSSFWorkbook();
//        File archivo;

//        JFileChooser selecArchivo = new JFileChooser();
//        selecArchivo.setFileFilter(new FileNameExtensionFilter("Excel (*.xls)", "xls"));
//        selecArchivo.setFileFilter(new FileNameExtensionFilter("Excel (*.xlsx)", "xlsx"));
//        selecArchivo.showDialog(null, "Seleccionar ubicación");
//        archivo = selecArchivo.getSelectedFile();
        HSSFSheet hoja = wb.createSheet("Documento de prueba");
        int numFila = tablita.getRowCount(), numColumna = tablita.getColumnCount();

        for (int i = -1; i < numFila; i++) {
            Row fila = hoja.createRow(i + 1);
            for (int j = 0; j < numColumna; j++) {
                Cell celda = fila.createCell(j);
                if (i == -1) {
                    celda.setCellValue(String.valueOf(tablita.getColumnName(j)));
                } else {
                    celda.setCellValue(String.valueOf(tablita.getValueAt(i, j)));
                }
                hoja.autoSizeColumn(j);
            }
        }

        try {
            FileOutputStream file = new FileOutputStream(new File(fileChooser.getCurrentDirectory(), fileChooser.getName(fileChooser.getSelectedFile()) + ".xls"));
            wb.write(file);
            file.close();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(ControlArchivos.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
}
