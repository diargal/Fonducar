package Vista;

import static Modelo.BonoSolidario.accesoBD;
import static Modelo.BonoSolidario.administrador;
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

    public DatosAsociado(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/Logo.png")).getImage());
        setResizable(false);
        setLocationRelativeTo(this);
        System.out.println("DatosAsociado: " + this.getSize());
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JPAsociado = new javax.swing.JPanel();
        JLNombre = new javax.swing.JLabel();
        JLCedula = new javax.swing.JLabel();
        JTxFNombre = new javax.swing.JTextField();
        JTxFCedula = new javax.swing.JTextField();
        JLApellido = new javax.swing.JLabel();
        JTxFApellido = new javax.swing.JTextField();
        JPAdmin = new javax.swing.JPanel();
        JLUsuario = new javax.swing.JLabel();
        JTxFUsuario = new javax.swing.JTextField();
        JLPass = new javax.swing.JLabel();
        JTxFPass = new javax.swing.JPasswordField();
        JBModificar = new javax.swing.JButton();
        JBCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Modificación datos del Asociado");

        JPAsociado.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del Asociado"));

        JLNombre.setText("Nombre :");

        JLCedula.setText("Cédula:");

        JTxFNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JTxFNombreKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JTxFNombreKeyTyped(evt);
            }
        });

        JTxFCedula.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                JTxFCedulaFocusGained(evt);
            }
        });

        JLApellido.setText("Apellido:");

        javax.swing.GroupLayout JPAsociadoLayout = new javax.swing.GroupLayout(JPAsociado);
        JPAsociado.setLayout(JPAsociadoLayout);
        JPAsociadoLayout.setHorizontalGroup(
            JPAsociadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPAsociadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPAsociadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(JLApellido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JLNombre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                    .addComponent(JLCedula, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(JPAsociadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JTxFNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                    .addComponent(JTxFCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JTxFApellido))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JPAsociadoLayout.setVerticalGroup(
            JPAsociadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPAsociadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPAsociadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JLNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JTxFNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(JPAsociadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JLApellido)
                    .addComponent(JTxFApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(JPAsociadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JLCedula)
                    .addComponent(JTxFCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        JPAdmin.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del Administrador"));

        JLUsuario.setText("Usuario:");

        JLPass.setText("Contraseña:");

        JTxFPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JTxFPassKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout JPAdminLayout = new javax.swing.GroupLayout(JPAdmin);
        JPAdmin.setLayout(JPAdminLayout);
        JPAdminLayout.setHorizontalGroup(
            JPAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPAdminLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(JLPass, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                    .addComponent(JLUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(38, 38, 38)
                .addGroup(JPAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JTxFUsuario)
                    .addComponent(JTxFPass, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JPAdminLayout.setVerticalGroup(
            JPAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPAdminLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JLUsuario)
                    .addComponent(JTxFUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(JPAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JLPass)
                    .addComponent(JTxFPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JBModificar.setText("Modificar");
        JBModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBModificarActionPerformed(evt);
            }
        });

        JBCancelar.setText("Cancelar");
        JBCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(JPAsociado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(JPAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JBCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(JBModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JPAsociado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(JPAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JBModificar)
                    .addComponent(JBCancelar))
                .addGap(28, 28, 28))
        );

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
        if (vacio(JPAsociado) || vacio(JPAdmin)) {
            JOptionPane.showMessageDialog(this, "Debe diligenciar todo el formulario.", "Existen campos vacios", JOptionPane.INFORMATION_MESSAGE);
        } else {
            String password = DigestUtils.md5Hex(new String(JTxFPass.getPassword()));
            if ((JTxFUsuario.getText().equals(administrador.getUsuario())) && (administrador.getPass().equals(password))) {
                if (!accesoBD.modificarAsociado(JTxFNombre.getText(), JTxFApellido.getText(), Long.parseLong(JTxFCedula.getText()))) {
                    JOptionPane.showMessageDialog(this, "La cédula del asociado no se encuentra en la base de datos.", "Operación fallida", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Se han modificado los datos del asociado.", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
                    this.setVisible(false);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Los datos del administrador no son correctos, verifique.", "Operación fallida", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_JBModificarActionPerformed

    public boolean vacio(JPanel jpa) {
        for (int i = 0; i < jpa.getComponentCount(); i++) {
            if (jpa.getComponent(i) instanceof JTextField) {
                JTextField jt = (JTextField) jpa.getComponent(i);
                if (jt.getText().isEmpty()) {
                    return true;
                }
            } else {
                if (jpa.getComponent(i) instanceof JPasswordField) {
                    JPasswordField jp = (JPasswordField) jpa.getComponent(i);
                    if (jp.getText().isEmpty()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBCancelar;
    private javax.swing.JButton JBModificar;
    private javax.swing.JLabel JLApellido;
    private javax.swing.JLabel JLCedula;
    private javax.swing.JLabel JLNombre;
    private javax.swing.JLabel JLPass;
    private javax.swing.JLabel JLUsuario;
    private javax.swing.JPanel JPAdmin;
    private javax.swing.JPanel JPAsociado;
    private javax.swing.JTextField JTxFApellido;
    private javax.swing.JTextField JTxFCedula;
    private javax.swing.JTextField JTxFNombre;
    private javax.swing.JPasswordField JTxFPass;
    private javax.swing.JTextField JTxFUsuario;
    // End of variables declaration//GEN-END:variables
}
