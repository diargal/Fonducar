/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import java.awt.Color;
import javax.swing.JLabel;

/**
 *
 * @author Diego García
 */
public class Ganador extends javax.swing.JDialog {

    /**
     * Creates new form Ganador
     */
    public Ganador(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        setUndecorated(true);
        initComponents();
        setBackground(new Color(0, 0, 0, 0));
        setOpacity(0.6f);
//        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JL1 = new javax.swing.JLabel();
        JLGanador = new javax.swing.JLabel();
        JL2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        JLGanador.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 48)); // NOI18N
        JLGanador.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(JL2)
                .addGap(51, 51, 51))
            .addGroup(layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(JL1)
                .addGap(39, 39, 39)
                .addComponent(JLGanador, javax.swing.GroupLayout.PREFERRED_SIZE, 675, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(78, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(JL1)
                .addGap(123, 123, 123)
                .addComponent(JLGanador, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(JL2)
                .addContainerGap(96, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public JLabel getJL1() {
        return JL1;
    }

    public void setJL1(JLabel JL1) {
        this.JL1 = JL1;
    }

    public JLabel getJL2() {
        return JL2;
    }

    public void setJL2(JLabel JL2) {
        this.JL2 = JL2;
    }

    public JLabel getJLGanador() {
        return JLGanador;
    }

    public void setJLGanador(JLabel JLGanador) {
        this.JLGanador = JLGanador;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JL1;
    private javax.swing.JLabel JL2;
    private javax.swing.JLabel JLGanador;
    // End of variables declaration//GEN-END:variables
}
