/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import static Modelo.BonoSolidario.administrador;
import static Modelo.Mensajes.ERROR_LOGIN;
import static Modelo.Mensajes.LOGIN_VER;
import Modelo.Peticiones;
import Controlador.ControlIntro;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego García
 */
public class AccesControl extends javax.swing.JDialog {

    private final String pass;
    private final long cedula;
//    Sorteo sorteo;
    private Peticiones peticion;
    private ControlIntro control;

    /**
     * Creates new form AccesControl
     *
     * @param parent
     * @param modal
     */
    public AccesControl(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        pass = "";
        cedula = 0;
        control = new ControlIntro();
        peticion = new Peticiones();

        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/Logo.png")).getImage());
        this.setResizable(false);
        this.setLocationRelativeTo(this);
        cerrarVentana();
    }

    public void cerrarVentana() {
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                System.exit(0);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JTxFUsuario = new javax.swing.JTextField();
        JBLogin = new javax.swing.JButton();
        JTxfPass = new javax.swing.JPasswordField();
        JLFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Inicio de Sesión");
        setMinimumSize(new java.awt.Dimension(755, 413));
        setPreferredSize(new java.awt.Dimension(780, 440));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JTxFUsuario.setText("sofimanotas");
        JTxFUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTxFUsuarioActionPerformed(evt);
            }
        });
        JTxFUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JTxFUsuarioKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JTxFUsuarioKeyTyped(evt);
            }
        });
        getContentPane().add(JTxFUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 110, 180, 30));

        JBLogin.setText("Iniciar Sesión");
        JBLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBLoginActionPerformed(evt);
            }
        });
        getContentPane().add(JBLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 310, 188, 32));

        JTxfPass.setText("manotas");
        JTxfPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JTxfPassKeyPressed(evt);
            }
        });
        getContentPane().add(JTxfPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 190, 165, -1));

        JLFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondos/login.jpg"))); // NOI18N
        JLFondo.setMaximumSize(new java.awt.Dimension(7535, 4130));
        getContentPane().add(JLFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JTxFUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTxFUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTxFUsuarioActionPerformed

    private void JBLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBLoginActionPerformed

        if (new String(JTxfPass.getPassword()).isEmpty() || JTxFUsuario.getText().isEmpty()) { //Si dejan los campos en blanco.
            ManagerError(); //método para mostrar errores.
        } else {
            if (peticion.login(JTxFUsuario.getText(), new String(JTxfPass.getPassword()), true)) { //inmediatamente hago la consulta
                this.dispose();
                control.mostrarIntro();
            } else {
                ManagerError();
            }
        }


    }//GEN-LAST:event_JBLoginActionPerformed

    private void JTxFUsuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTxFUsuarioKeyPressed
        ActionEvent jd = new ActionEvent(evt, WIDTH, "Hola mundo");
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this.JBLoginActionPerformed(jd);
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }

    }//GEN-LAST:event_JTxFUsuarioKeyPressed
    private void ManagerError() {
        JOptionPane.showMessageDialog(this, LOGIN_VER, ERROR_LOGIN, JOptionPane.ERROR_MESSAGE);
    }
    private void JTxfPassKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTxfPassKeyPressed
        ActionEvent jd = new ActionEvent(evt, WIDTH, "Hola mundo");

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this.JBLoginActionPerformed(jd);
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }

    }//GEN-LAST:event_JTxfPassKeyPressed

    private void JTxFUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTxFUsuarioKeyTyped

    }//GEN-LAST:event_JTxFUsuarioKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBLogin;
    private javax.swing.JLabel JLFondo;
    private javax.swing.JTextField JTxFUsuario;
    private javax.swing.JPasswordField JTxfPass;
    // End of variables declaration//GEN-END:variables
}
