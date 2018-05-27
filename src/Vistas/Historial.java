/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import static Logica.BonoSolidario.accesoBD;
import Vistas.Controlador.ControlArchivos;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Diego García
 */
public class Historial extends javax.swing.JDialog {

    private File archivo;
    public boolean tipoAccion;
    Locale locale = new Locale("es", "CO");
    NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
    private TableRowSorter trsFiltro;

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
//        ordenar();
//        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
    }

    public void ordenar() {
        TableRowSorter<TableModel> elQueOrdena = new TableRowSorter<>(JTHistorial.getModel());
        JTHistorial.setRowSorter(elQueOrdena);
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

    public JComboBox<String> getJCBFiltro() {
        return JCBFiltro;
    }

    public void setJCBFiltro(JComboBox<String> JCBFiltro) {
        this.JCBFiltro = JCBFiltro;
    }

    public JPanel getJPFiltro() {
        return JPFiltro;
    }

    public void setJPFiltro(JPanel JPFiltro) {
        this.JPFiltro = JPFiltro;
    }

    public JTextField getJTxFFiltro() {
        return JTxFFiltro;
    }

    public void setJTxFFiltro(JTextField JTxFFiltro) {
        this.JTxFFiltro = JTxFFiltro;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTHistorial = new javax.swing.JTable();
        JBSubir = new javax.swing.JButton();
        JPFiltro = new javax.swing.JPanel();
        JCBFiltro = new javax.swing.JComboBox<>();
        JTxFFiltro = new javax.swing.JTextField();

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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1055, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(JBSubir, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JBSubir)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JPFiltro.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtrar por"));

        JTxFFiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JTxFFiltroKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout JPFiltroLayout = new javax.swing.GroupLayout(JPFiltro);
        JPFiltro.setLayout(JPFiltroLayout);
        JPFiltroLayout.setHorizontalGroup(
            JPFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPFiltroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JCBFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(JTxFFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JPFiltroLayout.setVerticalGroup(
            JPFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPFiltroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JCBFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JTxFFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(JPFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(JPFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
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

    private void JTxFFiltroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTxFFiltroKeyTyped

        trsFiltro = new TableRowSorter(JTHistorial.getModel());
        JTHistorial.setRowSorter(trsFiltro);

        JTxFFiltro.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String cadena = (JTxFFiltro.getText());
                JTxFFiltro.setText(cadena);
                repaint();
                filtro();
            }
        });


    }//GEN-LAST:event_JTxFFiltroKeyTyped

    /*
    Mètodo que genera el archivo excel de la tabla en cuestiòn
     */
    public boolean generarArchivo() {
        ControlArchivos control = new ControlArchivos();
        return control.generarArchivo(JTHistorial);
    }

    /*
    Creado para identificar el número de la columna en la cual se hará el filtro
     */
    public void filtro() {
        int columna = 0;
        for (int i = 0; i < JTHistorial.getModel().getColumnCount(); i++) {
            if (JTHistorial.getColumnName(i).equalsIgnoreCase(JCBFiltro.getSelectedItem().toString())) {
                System.out.println(JTHistorial.getColumnName(i));
                columna = i;
            }
        }
        // trsFiltro.setRowFilter(RowFilter.regexFilter("^(?i)" + JTxFFiltro.getText(), columna));
        trsFiltro.setRowFilter(RowFilter.regexFilter("(?i)" + JTxFFiltro.getText(), columna));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBSubir;
    private javax.swing.JComboBox<String> JCBFiltro;
    private javax.swing.JPanel JPFiltro;
    private javax.swing.JTable JTHistorial;
    private javax.swing.JTextField JTxFFiltro;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
