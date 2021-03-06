package Vista;

import static Modelo.BonoSolidario.administrador;
import Modelo.Peticiones;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import static java.awt.image.ImageObserver.WIDTH;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Diego García
 */
public class DatosAsociado extends javax.swing.JDialog {

    private Peticiones peticion;

    public DatosAsociado(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/Logo.png")).getImage());
        setResizable(false);
        setLocationRelativeTo(this);
        peticion = new Peticiones();
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JPFondo = new javax.swing.JPanel();
        JTxFPass = new javax.swing.JPasswordField();
        JBCancelar = new javax.swing.JButton();
        JTxFUsuario = new javax.swing.JTextField();
        JTxFApellido = new javax.swing.JTextField();
        JTxFNombre = new javax.swing.JTextField();
        JTxFCedula = new javax.swing.JTextField();
        JBModificar = new javax.swing.JButton();
        JBIr = new javax.swing.JButton();
        JLFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Modificación datos del Asociado");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JPFondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JTxFPass.setEnabled(false);
        JTxFPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JTxFPassKeyPressed(evt);
            }
        });
        JPFondo.add(JTxFPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 270, -1));

        JBCancelar.setBackground(new java.awt.Color(125, 202, 62));
        JBCancelar.setText("Cancelar");
        JBCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBCancelarActionPerformed(evt);
            }
        });
        JPFondo.add(JBCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 250, 140, 30));

        JTxFUsuario.setEnabled(false);
        JPFondo.add(JTxFUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 270, -1));

        JTxFApellido.setEnabled(false);
        JPFondo.add(JTxFApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 270, -1));

        JTxFNombre.setEnabled(false);
        JTxFNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JTxFNombreKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JTxFNombreKeyTyped(evt);
            }
        });
        JPFondo.add(JTxFNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 270, -1));

        JTxFCedula.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                JTxFCedulaFocusGained(evt);
            }
        });
        JTxFCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JTxFCedulaKeyPressed(evt);
            }
        });
        JPFondo.add(JTxFCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, 190, -1));

        JBModificar.setBackground(new java.awt.Color(125, 202, 62));
        JBModificar.setText("Modificar");
        JBModificar.setEnabled(false);
        JBModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBModificarActionPerformed(evt);
            }
        });
        JPFondo.add(JBModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 250, 130, 30));

        JBIr.setText("Ir");
        JBIr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBIrActionPerformed(evt);
            }
        });
        JPFondo.add(JBIr, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 120, 50, -1));

        JLFondo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondos/datosAsociado.jpg"))); // NOI18N
        JPFondo.add(JLFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 460, 290));

        getContentPane().add(JPFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 460, 290));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JTxFNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTxFNombreKeyTyped
        char c = evt.getKeyChar();
        if (!(c < '0' || c > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_JTxFNombreKeyTyped

    private void JBCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBCancelarActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_JBCancelarActionPerformed

    private void JTxFNombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTxFNombreKeyPressed

    }//GEN-LAST:event_JTxFNombreKeyPressed

    private void JTxFCedulaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JTxFCedulaFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_JTxFCedulaFocusGained

    private void JTxFPassKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTxFPassKeyPressed
        ActionEvent jd = new ActionEvent(evt, WIDTH, "Hola mundo");

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            JBModificarActionPerformed(jd);
        }
    }//GEN-LAST:event_JTxFPassKeyPressed

    private void JBModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBModificarActionPerformed
        if (vacio()) {
            JOptionPane.showMessageDialog(this, "Debe diligenciar todo el formulario.", "Existen campos vacios", JOptionPane.INFORMATION_MESSAGE);
        } else {
            String password = DigestUtils.md5Hex(new String(JTxFPass.getPassword()));
            if ((JTxFUsuario.getText().equals(administrador.getUsuario())) && (administrador.getPass().equals(password))) {
                if (!peticion.modificarAsociado(JTxFNombre.getText(), JTxFApellido.getText(), Long.parseLong(JTxFCedula.getText()))) {
                    JOptionPane.showMessageDialog(this, "La cédula del asociado no se encuentra en la base de datos.", "Operación fallida", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Se han modificado los datos del asociado.", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Los datos del administrador no son correctos, verifique.", "Operación fallida", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_JBModificarActionPerformed

    private void JBIrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBIrActionPerformed
        if (!JTxFCedula.getText().isEmpty()) {
            try {
                JTxFNombre.setText(peticion.nombreAsociado(Long.parseLong(JTxFCedula.getText())).get(0));
                JTxFApellido.setText(peticion.nombreAsociado(Long.parseLong(JTxFCedula.getText())).get(1));
                JTxFApellido.setEnabled(true);
                JTxFNombre.setEnabled(true);
                JTxFPass.setEnabled(true);
                JTxFUsuario.setEnabled(true);
                JBModificar.setEnabled(true);
            } catch (IndexOutOfBoundsException e) {
                JOptionPane.showMessageDialog(this, "La cédula ingresada no coincide con algún asociado registrado.", "Verificar cédula.", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Ingresar la cédula del asociado.");
        }
    }//GEN-LAST:event_JBIrActionPerformed

    private void JTxFCedulaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTxFCedulaKeyPressed
        ActionEvent jd = new ActionEvent(evt, WIDTH, "Hola mundo");

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this.JBIrActionPerformed(jd);
        }
    }//GEN-LAST:event_JTxFCedulaKeyPressed

    public boolean vacio() {
        for (int i = 0; i < JPFondo.getComponentCount(); i++) {
            if (JPFondo.getComponent(i) instanceof JTextField) {
                JTextField jt = (JTextField) JPFondo.getComponent(i);
                if (jt.getText().isEmpty()) {
                    return true;
                }
            } else {
                if (JPFondo.getComponent(i) instanceof JPasswordField) {
                    JPasswordField jp = (JPasswordField) JPFondo.getComponent(i);
                    if (jp.getPassword().toString().isEmpty()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBCancelar;
    private javax.swing.JButton JBIr;
    private javax.swing.JButton JBModificar;
    private javax.swing.JLabel JLFondo;
    private javax.swing.JPanel JPFondo;
    private javax.swing.JTextField JTxFApellido;
    private javax.swing.JTextField JTxFCedula;
    private javax.swing.JTextField JTxFNombre;
    private javax.swing.JPasswordField JTxFPass;
    private javax.swing.JTextField JTxFUsuario;
    // End of variables declaration//GEN-END:variables
}
