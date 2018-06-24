/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author Diego García
 */
public class Intro extends javax.swing.JFrame {

    private MainControl main;

    public Intro() {
        setUndecorated(true);
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);
        setLocationRelativeTo(this);
        main = new MainControl();
        setBackground(new Color(0, 0, 0, 0));
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/icono.png")).getImage());
//        setOpacity(0.6f);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JLIntro = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        JLIntro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLIntro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Intro.gif"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JLIntro, javax.swing.GroupLayout.DEFAULT_SIZE, 1418, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JLIntro, javax.swing.GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JLIntro;
    // End of variables declaration//GEN-END:variables
}
