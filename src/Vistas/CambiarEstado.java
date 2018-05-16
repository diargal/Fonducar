/**
 *
 * @author Diego García
 */
package Vistas;

import static Logica.Mensajes.MSG;
import static Logica.Mensajes.RERROR;
import Logica.Sorteo;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class CambiarEstado extends javax.swing.JDialog {

    private final Sorteo sorteo;

    public CambiarEstado(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        JTxAObservacion.setLineWrap(true);
        this.setResizable(false);
        this.setLocationRelativeTo(this);
        sorteo = new Sorteo();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        JLCedula = new javax.swing.JLabel();
        JTxFCedula = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTxAObservacion = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        JLAccion = new javax.swing.JLabel();
        JCBAccion = new javax.swing.JComboBox<>();
        JBBuscar = new javax.swing.JButton();
        JBAceptar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Modificación del estado del asociado");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        JLCedula.setText("Cédula del asociado o ex-asociado: ");

        JTxFCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JTxFCedulaKeyTyped(evt);
            }
        });

        JTxAObservacion.setColumns(20);
        JTxAObservacion.setRows(5);
        JTxAObservacion.setEnabled(false);
        jScrollPane1.setViewportView(JTxAObservacion);

        jLabel1.setText("Observación:");

        JLAccion.setText("Cambiar a...");

        JCBAccion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ex asociado CON participación", "Ex asociado SIN participacion", "Habilitar como ASOCIADO" }));
        JCBAccion.setEnabled(false);
        JCBAccion.setOpaque(false);

        JBBuscar.setText("Buscar");
        JBBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(JBBuscar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(JLAccion, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(JLCedula, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(JCBAccion, 0, 215, Short.MAX_VALUE)
                            .addComponent(JTxFCedula)))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JTxFCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JLCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JBBuscar)
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JCBAccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JLAccion, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JBAceptar.setText("Aceptar");
        JBAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBAceptarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(JBAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JBAceptar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JBAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBAceptarActionPerformed
        int opcion = 0;
        if (JCBAccion.getSelectedItem().equals("Ex-Asociado SIN participación")) {
            opcion = 3;
        } else {
            if (JCBAccion.getSelectedItem().equals("Ex-Asociado CON participación")) {
                opcion = 2;
            } else {
                if (JCBAccion.getSelectedItem().equals("Asociado activo")) {
                    opcion = 1;
                }
            }
        }

        if (sorteo.cambiarEstado(Long.parseLong(JTxFCedula.getText()), opcion, JTxAObservacion.getText())) {
            JOptionPane.showMessageDialog(null, MSG, "Operación exitosa!", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, RERROR, "Operación fallida!", JOptionPane.INFORMATION_MESSAGE);
        }
        this.setVisible(false);
    }//GEN-LAST:event_JBAceptarActionPerformed

    private void JBBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBBuscarActionPerformed
        if (JTxFCedula.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese la cédula del asociado o ex-asociado", "Campo vacío", JOptionPane.ERROR_MESSAGE);
        } else {
            int estado = sorteo.estadoAsociado(Long.parseLong(JTxFCedula.getText()));
            JComboBox nuevo = new JComboBox();
            JCBAccion.setModel(nuevo.getModel());
            switch (estado) {
                case 1:
                    JCBAccion.addItem("Ex-Asociado CON participación");
                    JCBAccion.addItem("Ex-Asociado SIN participación");
                    JCBAccion.setEnabled(true);
                    JTxAObservacion.setEditable(true);
                    JTxAObservacion.setEnabled(true);
                    break;
                case 2:
                    JCBAccion.addItem("Asociado activo");
                    JCBAccion.addItem("Ex-Asociado SIN participación");
                    JCBAccion.setEnabled(true);
                    JTxAObservacion.setEditable(true);
                    JTxAObservacion.setEnabled(true);
                    break;
                case 3:
                    JCBAccion.addItem("Asociado activo");
                    JCBAccion.addItem("Ex-Asociado CON participación");
                    JCBAccion.setEnabled(true);
                    JTxAObservacion.setEditable(true);
                    JTxAObservacion.setEnabled(true);
                    break;
                case 0:
                    JOptionPane.showMessageDialog(null, "No existen datos asociados con la cédula.", "Operación fallida.", JOptionPane.ERROR_MESSAGE);
                    JCBAccion.setModel(nuevo.getModel());
                    break;
            }
        }
    }//GEN-LAST:event_JBBuscarActionPerformed

    private void JTxFCedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTxFCedulaKeyTyped
        char c = evt.getKeyChar();
        if ((c < '0' || c > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_JTxFCedulaKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBAceptar;
    private javax.swing.JButton JBBuscar;
    private javax.swing.JComboBox<String> JCBAccion;
    private javax.swing.JLabel JLAccion;
    private javax.swing.JLabel JLCedula;
    private javax.swing.JTextArea JTxAObservacion;
    private javax.swing.JTextField JTxFCedula;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
