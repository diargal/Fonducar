/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import static Logica.BonoSolidario.numerodeSorteos;
import static Logica.Mensajes.COINCIDENCIA;
import static Logica.Mensajes.I_SORTEO;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import static java.awt.image.ImageObserver.WIDTH;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego García
 */
public class NumSorteos extends javax.swing.JDialog {

    public static int numsorteo2 = 0;

    public NumSorteos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(this);
        setResizable(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        JLInfo = new javax.swing.JLabel();
        JTxFNumero = new javax.swing.JTextField();
        JBAceptar = new javax.swing.JButton();
        JLConfirmacion = new javax.swing.JLabel();
        JTxFConfirmacion = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Datos de entrada para los sorteos del día de hoy");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        JLInfo.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        JLInfo.setText("Número de sorteos:");

        JTxFNumero.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        JTxFNumero.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        JTxFNumero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTxFNumeroActionPerformed(evt);
            }
        });
        JTxFNumero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JTxFNumeroKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JTxFNumeroKeyTyped(evt);
            }
        });

        JBAceptar.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        JBAceptar.setText("Aceptar");
        JBAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBAceptarActionPerformed(evt);
            }
        });

        JLConfirmacion.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        JLConfirmacion.setText("Confirmar sorteos:");

        JTxFConfirmacion.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        JTxFConfirmacion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        JTxFConfirmacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTxFConfirmacionActionPerformed(evt);
            }
        });
        JTxFConfirmacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JTxFConfirmacionKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JTxFConfirmacionKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(JLInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(JTxFNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 60, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(JLConfirmacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(JBAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(JTxFConfirmacion, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(JLInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JTxFNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JLConfirmacion, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JTxFConfirmacion, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(JBAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JTxFNumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTxFNumeroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTxFNumeroActionPerformed

    private void JTxFNumeroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTxFNumeroKeyTyped
        char c = evt.getKeyChar();
        if ((c < '0' || c > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_JTxFNumeroKeyTyped

    private void JBAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBAceptarActionPerformed
        if (JTxFNumero.getText().equals("") || JTxFConfirmacion.getText().equals("")) {
            JOptionPane.showMessageDialog(this, I_SORTEO, "Información importante!", JOptionPane.INFORMATION_MESSAGE);
        } else {
            int num1 = Integer.parseInt(JTxFNumero.getText());
            int num2 = Integer.parseInt(JTxFConfirmacion.getText());
            if (num1 == num2) {
                if (JOptionPane.showConfirmDialog(null, "Número de sorteos a realizar: " + num1 + ". \nDesea continuar?", "Confirmación de sorteos", JOptionPane.YES_NO_OPTION) == 0) {
                    numerodeSorteos = num1;
                    this.setVisible(false);
                }
                JTxFNumero.setText("");
                JTxFConfirmacion.setText("");
            } else {
                JOptionPane.showMessageDialog(this, COINCIDENCIA, "No coinciden los campos", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_JBAceptarActionPerformed

    private void JTxFNumeroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTxFNumeroKeyPressed
        ActionEvent jd = new ActionEvent(evt, WIDTH, "Hola mundo");
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this.JBAceptarActionPerformed(jd);
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            this.setVisible(false);
        }
    }//GEN-LAST:event_JTxFNumeroKeyPressed

    private void JTxFConfirmacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTxFConfirmacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTxFConfirmacionActionPerformed

    private void JTxFConfirmacionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTxFConfirmacionKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTxFConfirmacionKeyPressed

    private void JTxFConfirmacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTxFConfirmacionKeyTyped
        char c = evt.getKeyChar();
        if ((c < '0' || c > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_JTxFConfirmacionKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBAceptar;
    private javax.swing.JLabel JLConfirmacion;
    private javax.swing.JLabel JLInfo;
    private javax.swing.JTextField JTxFConfirmacion;
    private javax.swing.JTextField JTxFNumero;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
