/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import Logica.BonoSolidario;
import static Logica.Mensajes.A_SESION;
import static Logica.Mensajes.ERROR_LOGIN;
import static Logica.Mensajes.LOGIN_VER;
import Logica.Sorteo;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Diego García
 */
public class AccesControl extends javax.swing.JDialog {

    private final String pass;
    private final long cedula;
    private final Vistas.MainControl controlPrincipal;
    Sorteo sorteo;

    /**
     * Creates new form AccesControl
     */
    public AccesControl(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(this);
        pass = "";
        sorteo = new Sorteo();
        cedula = 0;
        controlPrincipal = new Vistas.MainControl();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JPanelPrincipal = new javax.swing.JPanel();
        JLUsuario = new javax.swing.JLabel();
        JTxFUsuario = new javax.swing.JTextField();
        JLPass = new javax.swing.JLabel();
        JTxfPass = new javax.swing.JPasswordField();
        JBLogin = new javax.swing.JButton();
        otro = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Inicio de Sesión");

        JPanelPrincipal.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        JLUsuario.setText("Usuario:");

        JTxFUsuario.setText("diegogarcia");
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

        JLPass.setText("Contraseña:");

        JTxfPass.setText("0221310044");
        JTxfPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JTxfPassKeyPressed(evt);
            }
        });

        JBLogin.setText("Iniciar Sesión");
        JBLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBLoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JPanelPrincipalLayout = new javax.swing.GroupLayout(JPanelPrincipal);
        JPanelPrincipal.setLayout(JPanelPrincipalLayout);
        JPanelPrincipalLayout.setHorizontalGroup(
            JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelPrincipalLayout.createSequentialGroup()
                .addGroup(JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPanelPrincipalLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(JLUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(JTxFUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(JLPass, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(JTxfPass))
                    .addGroup(JPanelPrincipalLayout.createSequentialGroup()
                        .addGap(193, 193, 193)
                        .addComponent(JBLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(otro)))
                .addContainerGap())
        );
        JPanelPrincipalLayout.setVerticalGroup(
            JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelPrincipalLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JLUsuario)
                    .addComponent(JLPass)
                    .addComponent(JTxfPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JTxFUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(JPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JBLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(otro))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fonducarLogo.jpg"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(75, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JPanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(JPanelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JTxFUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTxFUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTxFUsuarioActionPerformed

    private void JBLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBLoginActionPerformed
        boolean error = true;
        if (JTxfPass.getText().isEmpty() || JTxFUsuario.getText().isEmpty()) { //Si dejan los campos en blanco.
            ManagerError(); //método para mostrar errores.
        } else {
            if (BonoSolidario.accesoBD.consultaAdmin(JTxFUsuario.getText(), JTxfPass.getText())) { //inmediatamente hago la consulta
                this.setVisible(false);
                sorteo.actividad(A_SESION);
                controlPrincipal.setVisible(true);
            } else {
                ManagerError();
            }
        }
    }//GEN-LAST:event_JBLoginActionPerformed
    private String encriptation(String pass) {
        System.out.println(DigestUtils.md5Hex(pass));
        //JOptionPane.showMessageDialog(this, DigestUtils.md5Hex(pass));
        return DigestUtils.md5Hex(pass);
    }
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
//Este evento sirve para validar que solo ingresen números.
        char c = evt.getKeyChar();
        if ((c < '0' || c > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_JTxFUsuarioKeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AccesControl.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AccesControl.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AccesControl.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AccesControl.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AccesControl dialog = new AccesControl(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBLogin;
    private javax.swing.JLabel JLPass;
    private javax.swing.JLabel JLUsuario;
    private javax.swing.JPanel JPanelPrincipal;
    private javax.swing.JTextField JTxFUsuario;
    private javax.swing.JPasswordField JTxfPass;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel otro;
    // End of variables declaration//GEN-END:variables
}
