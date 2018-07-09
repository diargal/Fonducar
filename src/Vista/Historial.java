/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.ControlArchivos;
import Modelo.Peticiones;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
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
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author Diego García
 */
public class Historial extends javax.swing.JDialog {

    private File archivo;
    public boolean tipoAccion;
    private Locale locale = new Locale("es", "CO");
    private NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
    private TableRowSorter trsFiltro;
    public int numeroInforme;
    private Peticiones peticion;

    public Historial(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/Logo.png")).getImage());
        this.setLocationRelativeTo(this);
        this.setResizable(true);
        tipoAccion = true;
        numeroInforme = 0;
        peticion = new Peticiones();
        jLabel2Cargando.setVisible(false);

//        ordenar();
//        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
    }

    public void setTipoAccion(boolean tipoAccion) {
        this.tipoAccion = tipoAccion;
    }

    /**
     * Creates new form Historial
     */
    public void setArchivo(File archivo) {
        this.archivo = archivo;
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

    public int getNumeroInforme() {
        return numeroInforme;
    }

    public void setNumeroInforme(int numeroInforme) {
        this.numeroInforme = numeroInforme;
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
        jLabel2Cargando = new javax.swing.JLabel();

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

        jLabel2Cargando.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cargando.gif"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(JPFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2Cargando, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(93, 93, 93))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JPFiltro, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2Cargando, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JBSubirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBSubirActionPerformed

        Thread hilo = new Thread() {
            @Override
            public void run() {

                if (tipoAccion) {
                    jLabel2Cargando.setVisible(true);
                    if (peticion.guardarAsociados(archivo, jLabel2Cargando)) {

                        jLabel2Cargando.setVisible(false);
                        JOptionPane.showMessageDialog(Historial.this, "Se cargaron todos los datos a la BD", "Proceso exitoso", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    }
                } else {

                    jLabel2Cargando.setVisible(true);

                    if (generarArchivo()) {
                    } else {
                        JOptionPane.showMessageDialog(Historial.this, "No se pudo generar el archivo.", "Error de operación", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

        };

        hilo.start();


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
        try {
            return control.generarArchivo(JTHistorial, numeroInforme, this.getTitle(), JCBFiltro.getSelectedItem().toString(), JTxFFiltro.getText(), jLabel2Cargando);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage().toString());
        }
        return false;
    }

    /*
    Creado para identificar el número de la columna en la cual se hará el filtro
     */
    public void filtro() {
        int columna = 0;
        for (int i = 0; i < JTHistorial.getModel().getColumnCount(); i++) {
            if (JTHistorial.getColumnName(i).equalsIgnoreCase(JCBFiltro.getSelectedItem().toString())) {
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
    private javax.swing.JLabel jLabel2Cargando;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
