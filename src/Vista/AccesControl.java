/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import static Modelo.Mensajes.ERROR_LOGIN;
import static Modelo.Mensajes.LOGIN_VER;
import Modelo.Peticiones;
import Controlador.ControlIntro;
import java.awt.Image;
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

        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/icono.png")).getImage());
        JLFondo.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/Imagenes/Fondos/login.jpg")).getImage().getScaledInstance(JLFondo.getWidth(), JLFondo.getHeight(), Image.SCALE_DEFAULT)));
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
        setResizable(false);
        getContentPane().setLayout(null);

        JTxFUsuario.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.light"));
        JTxFUsuario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        JTxFUsuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        JTxFUsuario.setBorder(javax.swing.BorderFactory.createEtchedBorder());
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
        getContentPane().add(JTxFUsuario);
        JTxFUsuario.setBounds(440, 90, 250, 50);

        JBLogin.setBackground(new java.awt.Color(125, 202, 62));
        JBLogin.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        JBLogin.setText("Iniciar Sesión");
        JBLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBLoginActionPerformed(evt);
            }
        });
        getContentPane().add(JBLogin);
        JBLogin.setBounds(440, 280, 250, 80);

        JTxfPass.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.light"));
        JTxfPass.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        JTxfPass.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        JTxfPass.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        JTxfPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JTxfPassKeyPressed(evt);
            }
        });
        getContentPane().add(JTxfPass);
        JTxfPass.setBounds(440, 190, 250, 50);

        JLFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondos/login.jpg"))); // NOI18N
        JLFondo.setMaximumSize(new java.awt.Dimension(7535, 4130));
        getContentPane().add(JLFondo);
        JLFondo.setBounds(0, 0, 755, 410);

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
