/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;

/**
 *
 * @author Diego García
 */
public class Intro extends javax.swing.JFrame {

    private MainControl main;

    /**
     * Creates new form Intro
     */
    public Intro() {
        setUndecorated(true);
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);
        setLocationRelativeTo(this);
        main = new MainControl();
        setBackground(new Color(0, 0, 0, 0));
//        setOpacity(0.6f);

    }

    public void control(boolean enabled) {

        setVisible(true);
//        JLIntro.setIcon(new ImageIcon(INTRO.getImage().getScaledInstance(JLIntro.getWidth(), JLIntro.getHeight(), Image.SCALE_DEFAULT)));

        Timer timer = new Timer();
        TimerTask tk = new TimerTask() {
            @Override
            public void run() {
                if (enabled) {
                    main.getJMSuper().setEnabled(true);
                } else {
                    main.getJMSuper().setEnabled(false);
                }
                Intro.this.dispose();
                main.cerrarVentana();
                main.setVisible(true);
            }
        };
        timer.schedule(tk, 9000);

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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(JLIntro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(JLIntro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JLIntro;
    // End of variables declaration//GEN-END:variables
}
