/**
 *
 * @author Diego García
 */
package Vista;

import static Modelo.Mensajes.MSG;
import static Modelo.Mensajes.RERROR;
import Modelo.Peticiones;
import Modelo.Sorteo;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import static java.awt.image.ImageObserver.WIDTH;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class CambiarEstado extends javax.swing.JDialog {
    
    private final Sorteo sorteo;
    private Peticiones peticion;
    
    public CambiarEstado(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        JTxAObservacion.setLineWrap(true);
        this.setResizable(false);
        this.setLocationRelativeTo(this);
        sorteo = new Sorteo();
        peticion = new Peticiones();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JBAceptar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTxAObservacion = new javax.swing.JTextArea();
        JBBuscar = new javax.swing.JButton();
        JTxFCedula = new javax.swing.JTextField();
        JCBAccion = new javax.swing.JComboBox<>();
        JTxFNombre = new javax.swing.JTextField();
        JLFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Modificación del estado del asociado");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JBAceptar.setBackground(new java.awt.Color(125, 202, 62));
        JBAceptar.setText("Aceptar");
        JBAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBAceptarActionPerformed(evt);
            }
        });
        getContentPane().add(JBAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 330, 110, 40));

        JTxAObservacion.setColumns(20);
        JTxAObservacion.setRows(5);
        JTxAObservacion.setEnabled(false);
        JTxAObservacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JTxAObservacionKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(JTxAObservacion);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 460, 100));

        JBBuscar.setText("Buscar");
        JBBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBBuscarActionPerformed(evt);
            }
        });
        getContentPane().add(JBBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 100, 110, 30));

        JTxFCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JTxFCedulaKeyTyped(evt);
            }
        });
        getContentPane().add(JTxFCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 50, 210, 30));

        JCBAccion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ex asociado CON participación", "Ex asociado SIN participacion", "Habilitar como ASOCIADO" }));
        JCBAccion.setEnabled(false);
        JCBAccion.setOpaque(false);
        JCBAccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JCBAccionKeyPressed(evt);
            }
        });
        getContentPane().add(JCBAccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 140, 220, -1));

        JTxFNombre.setEnabled(false);
        getContentPane().add(JTxFNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 270, -1));

        JLFondo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondos/estadoAsociado.jpg"))); // NOI18N
        getContentPane().add(JLFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JBAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBAceptarActionPerformed
        int opcion = 0;
        if (JCBAccion.isEnabled()) {
            if (JCBAccion.getSelectedItem().equals("Ex-Asociado SIN participación")) {
                opcion = 3;
            } else {
                if (JCBAccion.getSelectedItem().equals("Ex-Asociado CON participación")) {
                    opcion = 2;
                } else {
                    if (JCBAccion.getSelectedItem().equals("Asociado activo")) {
                        opcion = 1;
                    }
                }
            }
            
            if (peticion.cambiarEstado(Long.parseLong(JTxFCedula.getText()), opcion, JTxAObservacion.getText())) {
                JOptionPane.showMessageDialog(null, MSG, "Operación exitosa!", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, RERROR, "Operación fallida!", JOptionPane.INFORMATION_MESSAGE);
            }
            dispose();
        }
    }//GEN-LAST:event_JBAceptarActionPerformed

    private void JBBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBBuscarActionPerformed
        if (JTxFCedula.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese la cédula del asociado o ex-asociado", "Campo vacío", JOptionPane.ERROR_MESSAGE);
        } else {
            int estado = peticion.estadoAsociado(Long.parseLong(JTxFCedula.getText()));
            JComboBox nuevo = new JComboBox();
            JCBAccion.setModel(nuevo.getModel());
            JTxFNombre.setText(peticion.nombreAsociado(Long.parseLong(JTxFCedula.getText())));
            switch (estado) {
                case 1:
                    JCBAccion.addItem("Ex-Asociado CON participación");
                    JCBAccion.addItem("Ex-Asociado SIN participación");
                    JCBAccion.setEnabled(true);
                    JTxAObservacion.setEditable(true);
                    JTxAObservacion.setEnabled(true);
                    break;
                case 2:
                    JCBAccion.addItem("Asociado activo");
                    JCBAccion.addItem("Ex-Asociado SIN participación");
                    JCBAccion.setEnabled(true);
                    JTxAObservacion.setEditable(true);
                    JTxAObservacion.setEnabled(true);
                    break;
                case 3:
                    JCBAccion.addItem("Asociado activo");
                    JCBAccion.addItem("Ex-Asociado CON participación");
                    JCBAccion.setEnabled(true);
                    JTxAObservacion.setEditable(true);
                    JTxAObservacion.setEnabled(true);
                    break;
                case 0:
                    JOptionPane.showMessageDialog(null, "No existen datos asociados con la cédula.", "Operación fallida.", JOptionPane.ERROR_MESSAGE);
                    JCBAccion.setModel(nuevo.getModel());
                    break;
            }
        }
    }//GEN-LAST:event_JBBuscarActionPerformed

    private void JTxFCedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTxFCedulaKeyTyped
        char c = evt.getKeyChar();
        if ((c < '0' || c > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_JTxFCedulaKeyTyped

    private void JTxAObservacionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTxAObservacionKeyPressed
        ActionEvent jd = new ActionEvent(evt, WIDTH, "Hola mundo");
        
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this.JBAceptarActionPerformed(jd);
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
    }//GEN-LAST:event_JTxAObservacionKeyPressed

    private void JCBAccionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JCBAccionKeyPressed
        ActionEvent jd = new ActionEvent(evt, WIDTH, "Hola mundo");
        
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this.JBAceptarActionPerformed(jd);
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
    }//GEN-LAST:event_JCBAccionKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBAceptar;
    private javax.swing.JButton JBBuscar;
    private javax.swing.JComboBox<String> JCBAccion;
    private javax.swing.JLabel JLFondo;
    private javax.swing.JTextArea JTxAObservacion;
    private javax.swing.JTextField JTxFCedula;
    private javax.swing.JTextField JTxFNombre;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
