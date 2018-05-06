/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Logica.BonoSolidario;
import static Logica.BonoSolidario.accesoBD;
import static Logica.Mensajes.A_HOPERACIONES;
import static Logica.Mensajes.A_RACTUALES;
import static Logica.Mensajes.A_REPORTESORTEOS;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
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
    
    public String Importar() {
        JBSubir.setText("Subir a la BD");
        Workbook wb;
        String respuesta = "No se pudo realizar la importación.";
        DefaultTableModel modeloT = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTHistorial.setModel(modeloT);
        JTHistorial.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        
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
                            System.out.println("col" + indiceColumna + " valor: true - " + celda + ".");
                        }
                    }
                }
                if (indiceFila != 0) {
                    modeloT.addRow(listaColumna);
                }
            }
            respuesta = "Importación exitosa";
        } catch (IOException | InvalidFormatException | EncryptedDocumentException e) {
            System.err.println(e.getMessage());
        }
        return respuesta;
    }
    
    public void historialSorteos(ResultSet resul) {
        DefaultTableModel tabla = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTHistorial.setModel(tabla);
        tabla.addColumn("Fecha");
        tabla.addColumn("Nombre");
        tabla.addColumn("Cédula");
        tabla.addColumn("Número");
        tabla.addColumn("Premio");
        tabla.addColumn("Tipo de premio");
        Object[] object = new Object[6];
        try {
            while (resul.next()) {
                object[0] = resul.getString(1);
                object[1] = resul.getString(2);
                object[2] = resul.getLong(3);
                if ((resul.getInt(4) < 100) && (resul.getInt(4) >= 10)) {
                    object[3] = "0" + resul.getInt(4);
                } else {
                    if (resul.getInt(4) < 10) {
                        object[3] = "00" + resul.getInt(4);
                    }
                }
                object[4] = resul.getLong(5);
                if (resul.getInt(6) == 0) {
                    object[5] = "Premio menor";
                } else if (resul.getInt(6) == 1) {
                    object[5] = "Premio mayor";
                }
                tabla.addRow(object);
            }
            accesoBD.guardarOperacion(A_REPORTESORTEOS);
            JBSubir.setText("Descargar archivo");
            JBSubir.setEnabled(true);
            
        } catch (SQLException ex) {
            Logger.getLogger(Historial.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void historialModificaciones(ResultSet resul) {
        DefaultTableModel tabla = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTHistorial.setModel(tabla);
        tabla.addColumn("Fecha");
        tabla.addColumn("Cédula");
        tabla.addColumn("Nombre");
        tabla.addColumn("Detalle");
        Object[] object = new Object[4];
        try {
            while (resul.next()) {
                object[0] = resul.getString(1);
                object[1] = resul.getLong(2);
                object[2] = resul.getString(3);
                object[3] = resul.getString(4);
                tabla.addRow(object);
            }
            accesoBD.guardarOperacion(A_HOPERACIONES);
            JBSubir.setText("Descargar archivo");
            JBSubir.setEnabled(true);
            
        } catch (SQLException ex) {
            Logger.getLogger(Historial.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void numerosActuales(ResultSet resul) {
        DefaultTableModel tabla = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTHistorial.setModel(tabla);
        tabla.addColumn("Nombre");
        tabla.addColumn("Cédula");
        tabla.addColumn("Número");
        Object[] object = new Object[3];
        try {
            while (resul.next()) {
                object[0] = resul.getString(1);
                object[1] = resul.getLong(2);
                if ((resul.getInt(3) < 100) && (resul.getInt(3) >= 10)) {
                    object[2] = "0" + resul.getInt(3);
                } else {
                    if (resul.getInt(3) < 10) {
                        object[2] = "00" + resul.getInt(3);
                    }
                }
                tabla.addRow(object);
            }
            accesoBD.guardarOperacion(A_RACTUALES);
            JBSubir.setText("Descargar archivo");
            JBSubir.setEnabled(true);
            
        } catch (SQLException ex) {
            Logger.getLogger(Historial.class.getName()).log(Level.SEVERE, null, ex);
        }
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
//            else {
//                JOptionPane.showMessageDialog(this, "Verifique si alguno de los datos ingresados ya existe en la base de datos.", "Algo salió mal", JOptionPane.ERROR_MESSAGE);
//            }
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
        Workbook wb;
        
        JFileChooser selecArchivo = new JFileChooser();
        selecArchivo.setFileFilter(new FileNameExtensionFilter("Excel (*.xls)", "xls"));
        selecArchivo.setFileFilter(new FileNameExtensionFilter("Excel (*.xlsx)", "xlsx"));
        selecArchivo.showDialog(null, "Seleccionar archivo");
        archivo = selecArchivo.getSelectedFile();
        
        if (archivo.getName().endsWith("xls") || archivo.getName().endsWith("xlsx")) {
            String respuesta = "No se realizo con exito la exportación.";
            int numFila = JTHistorial.getRowCount(), numColumna = JTHistorial.getColumnCount();
            if (archivo.getName().endsWith("xls")) {
                wb = new HSSFWorkbook();
            } else {
                wb = new XSSFWorkbook();
            }
            Sheet hoja = wb.createSheet("Reporte");
            
            try {
                for (int i = -1; i < numFila; i++) {
                    Row fila = hoja.createRow(i + 1);
                    for (int j = 0; j < numColumna; j++) {
                        Cell celda = fila.createCell(j);
                        if (i == -1) {
                            celda.setCellValue(String.valueOf(JTHistorial.getColumnName(j)));
                        } else {
                            celda.setCellValue(String.valueOf(JTHistorial.getValueAt(i, j)));
                        }
                        wb.write(new FileOutputStream(archivo));
                    }
                }
                return true;
            } catch (Exception e) {
                System.err.println(e.getMessage());
                
            }
        } else {
            
            JOptionPane.showMessageDialog(null, "Elija un formato valido.");
            return false;
        }
        return false;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBSubir;
    private javax.swing.JTable JTHistorial;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
