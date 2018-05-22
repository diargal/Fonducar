/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import static Logica.BonoSolidario.accesoBD;
import static Logica.Mensajes.A_HACON;
import static Logica.Mensajes.A_HASIN;
import static Logica.Mensajes.A_HNS;
import static Logica.Mensajes.A_HOPERACIONES;
import static Logica.Mensajes.A_RACTUALES;
import static Logica.Mensajes.A_REPORTESORTEOS;
import Vistas.Controlador.ControlArchivos;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Diego García
 */
public class Historial extends javax.swing.JDialog {

    private File archivo;
    public boolean tipoAccion;
    Locale locale = new Locale("es", "CO");
    NumberFormat nf = NumberFormat.getCurrencyInstance(locale);

    public void setTipoAccion(boolean tipoAccion) {
        this.tipoAccion = tipoAccion;
    }

    /**
     * Creates new form Historial
     */
    public void setArchivo(File archivo) {
        this.archivo = archivo;
    }

    public Historial(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(this);
        this.setResizable(true);
        tipoAccion = true;
    }

    public JButton getJBSubir() {
        return JBSubir;
    }

    public JTable getJTHistorial() {
        return JTHistorial;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public void setNf(NumberFormat nf) {
        this.nf = nf;
    }

    public void setJBSubir(JButton JBSubir) {
        this.JBSubir = JBSubir;
    }

    public void setJTHistorial(JTable JTHistorial) {
        this.JTHistorial = JTHistorial;
    }

    public void setjPanel1(JPanel jPanel1) {
        this.jPanel1 = jPanel1;
    }

    public void setjScrollPane1(JScrollPane jScrollPane1) {
        this.jScrollPane1 = jScrollPane1;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTHistorial = new javax.swing.JTable();
        JBSubir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        JTHistorial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(JTHistorial);

        JBSubir.setText("Subir a la BD");
        JBSubir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBSubirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 993, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(JBSubir, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(JBSubir)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JBSubirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBSubirActionPerformed
        if (tipoAccion) {
            if (accesoBD.guardarAsociados(archivo)) {
                JOptionPane.showMessageDialog(this, "Se cargaron todos los datos a la BD", "Proceso exitoso", JOptionPane.OK_CANCEL_OPTION);
                this.setVisible(false);
            }
        } else {
            if (generarArchivo()) {
                JOptionPane.showMessageDialog(this, "Archivo generado con éxito.");
                this.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo generar el archivo.", "Error de operación", JOptionPane.ERROR_MESSAGE);
            }
            System.out.println("Otra acción de historiales");
        }
    }//GEN-LAST:event_JBSubirActionPerformed

    public boolean generarArchivo() {

        ControlArchivos control = new ControlArchivos();
        return control.generarArchivo(JTHistorial);

//        Workbook wb;
//
//        JFileChooser selecArchivo = new JFileChooser();
//        selecArchivo.setFileFilter(new FileNameExtensionFilter("Excel (*.xls)", "xls"));
//        selecArchivo.setFileFilter(new FileNameExtensionFilter("Excel (*.xlsx)", "xlsx"));
//        selecArchivo.showDialog(null, "Seleccionar archivo");
//        archivo = selecArchivo.getSelectedFile();
//
//
//        if (archivo.getName().endsWith("xls") || archivo.getName().endsWith("xlsx")) {
//            String respuesta = "No se realizo con exito la exportación.";
//            int numFila = JTHistorial.getRowCount(), numColumna = JTHistorial.getColumnCount();
//            if (archivo.getName().endsWith("xls")) {
//                wb = new HSSFWorkbook();
//            } else {
//                wb = new XSSFWorkbook();
//            }
//            Sheet hoja = wb.createSheet("Reporte");
//
//            try {
//                for (int i = -1; i < numFila; i++) {
//                    Row fila = hoja.createRow(i + 1);
//                    for (int j = 0; j < numColumna; j++) {
//                        Cell celda = fila.createCell(j);
//                        if (i == -1) {
//                            celda.setCellValue(String.valueOf(JTHistorial.getColumnName(j)));
//                        } else {
//                            celda.setCellValue(String.valueOf(JTHistorial.getValueAt(i, j)));
//                        }
//                        wb.write(new FileOutputStream(archivo));
//                    }
//                }
//                return true;
//            } catch (Exception e) {
//                System.err.println(e.getMessage());
//
//            }
//        } else {
//
//            JOptionPane.showMessageDialog(null, "Elija un formato valido.");
//            return false;
//        }
//        return false;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBSubir;
    private javax.swing.JTable JTHistorial;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
