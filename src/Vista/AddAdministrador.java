/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Modelo.Administrador;
import static Modelo.BonoSolidario.administrador;
import Modelo.Peticiones;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import static java.awt.image.ImageObserver.WIDTH;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Diego García
 */
public class AddAdministrador extends javax.swing.JDialog {

    public int tipoOperacion;
    private Peticiones peticion;

    public AddAdministrador(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/Logo.png")).getImage());
        setResizable(false);
        setLocationRelativeTo(this);
        tipoOperacion = 0;
        peticion = new Peticiones();
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        JPFondo.setOpaque(false);
        JLIngresar.setVisible(false);
    }

    public int isTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(int tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public JButton getJBIr() {
        return JBIr;
    }

    public void setJBIr(JButton JBIr) {
        this.JBIr = JBIr;
    }

    public JPasswordField getJTxFSUPass() {
        return JTxFSUPass;
    }

    public void setJTxFSUPass(JPasswordField JTxFSUPass) {
        this.JTxFSUPass = JTxFSUPass;
    }

    public JTextField getJTxFSUUsuario() {
        return JTxFSUUsuario;
    }

    public void setJTxFSUUsuario(JTextField JTxFSUUsuario) {
        this.JTxFSUUsuario = JTxFSUUsuario;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JPFondo = new javax.swing.JPanel();
        JTxFSUUsuario = new javax.swing.JTextField();
        JBAceptar = new javax.swing.JButton();
        JBCancelar = new javax.swing.JButton();
        JTxFSUPass = new javax.swing.JPasswordField();
        JTxFNombre = new javax.swing.JTextField();
        JTxFCedula = new javax.swing.JTextField();
        JTxFUsuario = new javax.swing.JTextField();
        JTxFPass = new javax.swing.JPasswordField();
        JTxFConfiPass = new javax.swing.JPasswordField();
        JTxFApellidos = new javax.swing.JTextField();
        JBIr = new javax.swing.JButton();
        JLIngresar = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        JLActuales = new javax.swing.JLabel();
        JLFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JPFondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        JPFondo.add(JTxFSUUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 210, -1));

        JBAceptar.setBackground(new java.awt.Color(125, 202, 62));
        JBAceptar.setText("Aceptar");
        JBAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBAceptarActionPerformed(evt);
            }
        });
        JPFondo.add(JBAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 340, 128, -1));

        JBCancelar.setBackground(new java.awt.Color(125, 202, 62));
        JBCancelar.setText("Cancelar");
        JBCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBCancelarActionPerformed(evt);
            }
        });
        JPFondo.add(JBCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(213, 340, 130, -1));

        JTxFSUPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JTxFSUPassKeyPressed(evt);
            }
        });
        JPFondo.add(JTxFSUPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 310, 230, -1));

        JTxFNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JTxFNombreKeyTyped(evt);
            }
        });
        JPFondo.add(JTxFNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 30, 270, -1));

        JTxFCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JTxFCedulaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JTxFCedulaKeyTyped(evt);
            }
        });
        JPFondo.add(JTxFCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 110, 200, -1));
        JPFondo.add(JTxFUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 150, 270, -1));
        JPFondo.add(JTxFPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 190, 200, -1));
        JPFondo.add(JTxFConfiPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 230, 200, -1));

        JTxFApellidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTxFApellidosActionPerformed(evt);
            }
        });
        JPFondo.add(JTxFApellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 70, 270, -1));

        JBIr.setText("Ir");
        JBIr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBIrActionPerformed(evt);
            }
        });
        JBIr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JBIrKeyPressed(evt);
            }
        });
        JPFondo.add(JBIr, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 110, 50, -1));

        JLIngresar.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        JLIngresar.setText("Ingrese los nuevos datos para el administrador");
        JPFondo.add(JLIngresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 134, 240, 20));

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(0, 153, 0));
        jTextField1.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText("Datos del administrador actual");
        jTextField1.setBorder(null);
        JPFondo.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 260, 270, 20));

        JLActuales.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        JLActuales.setText("Debe ingresar los datos actuales");
        JPFondo.add(JLActuales, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 170, -1));

        JLFondo.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        JLFondo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondos/addAdmin.jpg"))); // NOI18N
        JLFondo.setText("Ingrese nuevos datos para el administrador");
        JPFondo.add(JLFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 370));

        getContentPane().add(JPFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 490, 370));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JBAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBAceptarActionPerformed
        if (new String(JTxFPass.getPassword()).equals(new String(JTxFConfiPass.getPassword()))) {
            //if (BonoSolidario.accesoBD.consultaAdmin(JTxFSUUsuario.getText(), JTxFSUPass.getText(), false)) {

            String password = DigestUtils.md5Hex(new String(JTxFSUPass.getPassword()));

            if (administrador.getTipo() == 1 && administrador.getUsuario().equals(JTxFSUUsuario.getText()) && administrador.getPass().equals(password)) {

                password = DigestUtils.md5Hex(new String(JTxFPass.getPassword()));
                Administrador admin;
                boolean exito = false;
                String mensaje = "";

                switch (tipoOperacion) {
                    case 1:
                        if (vacio()) {
                            JOptionPane.showMessageDialog(this, "Debe diligenciar todo el formulario.", "Existen campos vacíos.", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            admin = new Administrador(JTxFNombre.getText(), JTxFApellidos.getText(), Long.parseLong(JTxFCedula.getText()), password, JTxFUsuario.getText(), 0);
                            int resultado = peticion.guardarAdministrador(admin);
                            switch (resultado) {
                                case 1:
                                    exito = true;
                                    mensaje = "Administrador creado con éxito.";
                                    break;
                                case 2:
                                    exito = false;
                                    mensaje = "Ya el nombre de usuario existe, por favor ingrese otro.";
                                    break;
                                case 3:
                                    mensaje = "La cédula existe en la BD y el asociado ahora es administrador.";
                                    exito = true;
                                    break;
                                default:
                                    mensaje = "La operación no se pudo realizar.";
                                    break;
                            }
                        }
                        break;
                    case 2:
                        if (JTxFCedula.getText().isEmpty()) {
                            JOptionPane.showMessageDialog(this, "Debe diligenciar la cédula del administrador.", "Existen campos vacíos.", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            //aquí llamaré al método para eliminar al admin
                            admin = new Administrador(JTxFNombre.getText(), JTxFApellidos.getText(), Long.parseLong(JTxFCedula.getText()), password, JTxFUsuario.getText(), 0);
                            if (peticion.deleteAdmin(admin)) {
                                exito = true;
                                mensaje = "Administrador eliminado con éxito.";

                            } else {
                                exito = false;
                                mensaje = "Los datos del administrador no son correctos.";
                            }
                        }
                        break;
                    case 3:
                        if (JTxFCedula.getText().isEmpty()) {
                            JOptionPane.showMessageDialog(this, "Debe diligenciar la cédula del administrador.", "Existen campos vacíos.", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            //aquí llamaré al método para reingresar al administrador 
                            admin = new Administrador(JTxFNombre.getText(), JTxFApellidos.getText(), Long.parseLong(JTxFCedula.getText()), password, JTxFUsuario.getText(), 0);
                            if (peticion.reingresarAdmin(admin)) {
                                exito = true;
                                mensaje = "Administrador reingresado con éxito.";
                            } else {
                                exito = false;
                                mensaje = "Los datos del administrador no son correctos.";
                            }
                        }
                        break;

                    case 4:
                        if (JTxFUsuario.getText().isEmpty() || JTxFConfiPass.getPassword().toString().isEmpty() || JTxFPass.getPassword().toString().isEmpty() || JTxFSUUsuario.getText().isEmpty() || JTxFSUPass.getPassword().toString().isEmpty()) {
                            mensaje = "Debe llenar todo el formulario.";
                            exito = false;
                        } else {
                            if (!JTxFConfiPass.getPassword().equals(JTxFPass.getPassword())) {
                                exito = false;
                                mensaje = "Las contraseñas diligenciadas no concuerdan.";
                            } else {
                                admin = new Administrador("", "", Long.parseLong(JTxFCedula.getText()), password, mensaje, WIDTH);
                                peticion.modificarAdmin(admin);
                            }
                        }
                        break;

                }

                if (exito) {

                    JOptionPane.showMessageDialog(this, mensaje, "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
                    vaciar();
                    dispose();

                } else if (!mensaje.isEmpty()) {

                    JOptionPane.showMessageDialog(this, mensaje, "Verificar", JOptionPane.ERROR_MESSAGE);

                }
            } else {
                JOptionPane.showMessageDialog(this, "Los datos del Super Usuario no son válidos. Verifique.", "Verificar.", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Las contraseñas del nuevo administrador no coinciden.", "Verificar.", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_JBAceptarActionPerformed

    private void JBCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBCancelarActionPerformed
        JLIngresar.setVisible(false);
        JLActuales.setVisible(false);
        vaciar();
        dispose();
    }//GEN-LAST:event_JBCancelarActionPerformed

    private void JTxFCedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTxFCedulaKeyTyped
        char c = evt.getKeyChar();
        if ((c < '0' || c > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_JTxFCedulaKeyTyped

    private void JTxFNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTxFNombreKeyTyped
        char c = evt.getKeyChar();
        if (!(c < '0' || c > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_JTxFNombreKeyTyped

    private void JTxFSUPassKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTxFSUPassKeyPressed
        ActionEvent jd = new ActionEvent(evt, WIDTH, "Hola mundo");

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this.JBAceptarActionPerformed(jd);
        }
    }//GEN-LAST:event_JTxFSUPassKeyPressed

    private void JTxFApellidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTxFApellidosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTxFApellidosActionPerformed

    private void JBIrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBIrActionPerformed
        if (!JTxFCedula.getText().isEmpty()) {
            ArrayList<String> array = peticion.nombreAdmin(Long.parseLong(JTxFCedula.getText()));
            JTxFNombre.setText(array.get(0));
            JTxFApellidos.setText(array.get(1));
            JTxFUsuario.setText(array.get(2));
            JTxFSUPass.setEnabled(true);
            JTxFSUUsuario.setEnabled(true);
            JLIngresar.setVisible(tipoOperacion == 4);
            JTxFUsuario.setEnabled(tipoOperacion == 4);
            JTxFPass.setEnabled(tipoOperacion == 4);
            JTxFConfiPass.setEnabled(tipoOperacion == 4);
            JLActuales.setVisible(tipoOperacion == 4);

        } else {
            JOptionPane.showMessageDialog(this, "Ingrese la cédula del administrador");
        }
    }//GEN-LAST:event_JBIrActionPerformed

    private void JBIrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JBIrKeyPressed

    }//GEN-LAST:event_JBIrKeyPressed

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

            } else if (JPFondo.getComponent(i) instanceof JPasswordField) {
                JPasswordField jt = (JPasswordField) JPFondo.getComponent(i);
                if (jt.getPassword().toString().isEmpty()) {
                    return true;
                }

            }
        }
        return false;
    }

    public void enabled(boolean q) {
        JTxFNombre.setEnabled(q);
        JTxFApellidos.setEnabled(q);
        JTxFUsuario.setEnabled(q);
        JTxFPass.setEnabled(q);
        JTxFConfiPass.setEnabled(q);
    }

    public void vaciar() {
        JTxFApellidos.setText("");
        JTxFCedula.setText("");
        JTxFConfiPass.setText("");
        JTxFNombre.setText("");
        JTxFPass.setText("");
        JTxFSUPass.setText("");
        JTxFSUUsuario.setText("");
        JTxFUsuario.setText("");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBAceptar;
    private javax.swing.JButton JBCancelar;
    private javax.swing.JButton JBIr;
    private javax.swing.JLabel JLActuales;
    private javax.swing.JLabel JLFondo;
    private javax.swing.JLabel JLIngresar;
    private javax.swing.JPanel JPFondo;
    private javax.swing.JTextField JTxFApellidos;
    private javax.swing.JTextField JTxFCedula;
    private javax.swing.JPasswordField JTxFConfiPass;
    private javax.swing.JTextField JTxFNombre;
    private javax.swing.JPasswordField JTxFPass;
    private javax.swing.JPasswordField JTxFSUPass;
    private javax.swing.JTextField JTxFSUUsuario;
    private javax.swing.JTextField JTxFUsuario;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
