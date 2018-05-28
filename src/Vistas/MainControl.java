/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import static Imagenes.ControlImagenes.CERO;
import static Imagenes.ControlImagenes.CINCO;
import static Imagenes.ControlImagenes.CUATRO;
import static Imagenes.ControlImagenes.DOS;
import static Imagenes.ControlImagenes.NUEVE;
import static Imagenes.ControlImagenes.OCHO;
import static Imagenes.ControlImagenes.SEIS;
import static Imagenes.ControlImagenes.SIETE;
import static Imagenes.ControlImagenes.TRES;
import static Imagenes.ControlImagenes.UNO;
import Logica.BonoSolidario;
import static Logica.BonoSolidario.accesoBD;
import static Logica.BonoSolidario.numerodeSorteos;
import static Logica.Mensajes.A_NUMEROS;
import static Logica.Mensajes.A_RIFA;
import static Logica.Mensajes.MSG_SORTEO;
import static Logica.Mensajes.NOHAY;
import static Logica.Mensajes.RERROR;
import static Logica.Mensajes.REXITOSO;
import static Logica.Mensajes.SORTEOS_FINAL;
import static Logica.Mensajes.YGENERADOS;
import Logica.Sorteo;
import Vistas.Controlador.ControlArchivos;
import Vistas.Controlador.ControlHistorial;
import Vistas.Controlador.hiloCarga;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego García
 */
public class MainControl extends javax.swing.JFrame {

    private int sorteosRealizados;
    private Sorteo sorteo;
    private float premio;
    private int tipoPremio;
    private File archivo;
    private static Historial historial;
    private NumSorteos numeroSorteos;
    private ValorSorteo valorSorteo;
    private ControlHistorial control;
    private ControlArchivos cntrlArchivos;
    private AddAdministrador agregarAdmin;
    // public hiloCarga hilo;

    public MainControl() {
        initComponents();
        this.setResizable(true);
        this.setLocationRelativeTo(this);
        agregarAdmin = new AddAdministrador(this, true);
        sorteosRealizados = 0;
        premio = 0;
        tipoPremio = 0;
        control = new ControlHistorial();
        cntrlArchivos = new ControlArchivos();
        valorSorteo = new ValorSorteo(this, true);
        numeroSorteos = new NumSorteos(this, true);
        sorteo = new Sorteo();
        historial = new Historial(this, true);
        JLActivos.setText("Número de participantes para los sorteos: " + accesoBD.numeroAsociadosActivos());
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        cerrarVentana();
//        verificarInhabilitados();
    }

    public void verificarInhabilitados() {
        if (accesoBD.numerosAsignados()) {

            try {
                ResultSet resultado = accesoBD.verificarsihayInhabilitados();
                ResultSet resl = resultado;
                //if (resultado.next()) {
                JOptionPane.showMessageDialog(jMenu1, "En la siguiente tabla se mostrarán los ex-asociados que se les debe definir su estado.");
                control.inhabilitadosAnioPasado(resl);
                //  }
            } catch (SQLException ex) {
                Logger.getLogger(MainControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void cerrarVentana() {
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                cierre();
            }
        });
    }

    public void cierre() {
        if (sorteosRealizados != numerodeSorteos) {
            JOptionPane.showMessageDialog(this, "Aún faltan sorteos por realizar!");
        } else {
            System.exit(0);
        }
    }

    public void setJLCuanto(JLabel JLCuanto) {
        this.JLCuanto = JLCuanto;
    }

    public JMenu getJMSuper() {
        return JMSuper;
    }

    public void setJMSuper(JMenu JMSuper) {
        this.JMSuper = JMSuper;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jPanel4 = new javax.swing.JPanel();
        JBSorteo1 = new javax.swing.JButton();
        JLBalota2 = new javax.swing.JLabel();
        JLBalota3 = new javax.swing.JLabel();
        JLBalota4 = new javax.swing.JLabel();
        JLBalota1 = new javax.swing.JLabel();
        JLCuanto = new javax.swing.JLabel();
        JLGanador1 = new javax.swing.JLabel();
        JLActivos = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        JMIInhabilitadosActual = new javax.swing.JMenuItem();
        JMIHabilitadosActual = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        JMIHAsociados = new javax.swing.JMenuItem();
        JMIHSorteos = new javax.swing.JMenuItem();
        JMIHModificaciones = new javax.swing.JMenuItem();
        JMIActuales = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        JMIAsignarAso = new javax.swing.JMenuItem();
        JMAAsociados = new javax.swing.JMenuItem();
        JMIModificar = new javax.swing.JMenuItem();
        JMSuper = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        jMenu4.setText("File");
        jMenuBar2.add(jMenu4);

        jMenu5.setText("Edit");
        jMenuBar2.add(jMenu5);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("FONDUCAR - Bono Solidario ");

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.setOpaque(false);

        JBSorteo1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        JBSorteo1.setText("Iniciar Sorteo");
        JBSorteo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBSorteoActionPerformed(evt);
            }
        });

        JLBalota2.setBackground(new java.awt.Color(204, 255, 153));
        JLBalota2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLBalota2.setAutoscrolls(true);
        JLBalota2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        JLBalota3.setBackground(new java.awt.Color(204, 255, 153));
        JLBalota3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLBalota3.setAutoscrolls(true);
        JLBalota3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        JLBalota4.setBackground(new java.awt.Color(204, 255, 153));
        JLBalota4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLBalota4.setAutoscrolls(true);
        JLBalota4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        JLBalota1.setBackground(new java.awt.Color(204, 255, 153));
        JLBalota1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLBalota1.setAutoscrolls(true);
        JLBalota1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(JLBalota1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(JLBalota2, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(JLBalota3, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(JLBalota4, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(JBSorteo1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JBSorteo1)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JLBalota3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JLBalota4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JLBalota2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JLBalota1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(57, Short.MAX_VALUE))
        );

        JLCuanto.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        JLCuanto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLCuanto.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        JLCuanto.setOpaque(true);

        JLGanador1.setBackground(new java.awt.Color(153, 255, 153));
        JLGanador1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 36)); // NOI18N
        JLGanador1.setForeground(new java.awt.Color(51, 51, 51));
        JLGanador1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLGanador1.setText("GANADOR");

        JLActivos.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jMenu1.setText("Informes");

        jMenu3.setText("Ex-Asociados");

        JMIInhabilitadosActual.setText("Inhabilitados para los sorteos de este año");
        JMIInhabilitadosActual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMIInhabilitadosActualActionPerformed(evt);
            }
        });
        jMenu3.add(JMIInhabilitadosActual);

        JMIHabilitadosActual.setText("Habilitados para los sorteos de este año");
        JMIHabilitadosActual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMIHabilitadosActualActionPerformed(evt);
            }
        });
        jMenu3.add(JMIHabilitadosActual);

        jMenuItem4.setText("Historial ex-asociados CON participación en sorteos");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuItem5.setText("Historial de los ex-asociados SIN participaron en sorteos");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem5);

        jMenu1.add(jMenu3);

        JMIHAsociados.setText("Historial de números asignados a asociados");
        JMIHAsociados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                JMIHAsociadosMousePressed(evt);
            }
        });
        JMIHAsociados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMIHAsociadosActionPerformed(evt);
            }
        });
        jMenu1.add(JMIHAsociados);

        JMIHSorteos.setText("Historial de los sorteos");
        JMIHSorteos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JMIHSorteosMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                JMIHSorteosMousePressed(evt);
            }
        });
        JMIHSorteos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMIHSorteosActionPerformed(evt);
            }
        });
        JMIHSorteos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JMIHSorteosKeyPressed(evt);
            }
        });
        jMenu1.add(JMIHSorteos);

        JMIHModificaciones.setText("Historial de operaciones y movimientos");
        JMIHModificaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMIHModificacionesActionPerformed(evt);
            }
        });
        jMenu1.add(JMIHModificaciones);

        JMIActuales.setText("Informe del número actual de los asociados y ex-asociados hábiles para sorteos");
        JMIActuales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMIActualesActionPerformed(evt);
            }
        });
        jMenu1.add(JMIActuales);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Operaciones");

        JMIAsignarAso.setText("Asignar números");
        JMIAsignarAso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMIAsignarAsoActionPerformed(evt);
            }
        });
        jMenu2.add(JMIAsignarAso);

        JMAAsociados.setText("Agregar asociados");
        JMAAsociados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMAAsociadosActionPerformed(evt);
            }
        });
        jMenu2.add(JMAAsociados);

        JMIModificar.setText("Modificar estado de asociado");
        JMIModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMIModificarActionPerformed(evt);
            }
        });
        jMenu2.add(JMIModificar);

        jMenuBar1.add(jMenu2);

        JMSuper.setText("Super Usuario");

        jMenu6.setText("Gestionar administradores");

        jMenuItem2.setText("Eliminar administrador");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem2);

        jMenuItem3.setText("Agregar administrador");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem3);

        JMSuper.add(jMenu6);

        jMenuBar1.add(JMSuper);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(JLActivos, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(JLCuanto, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JLGanador1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JLCuanto, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                    .addComponent(JLActivos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JLGanador1, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                .addGap(32, 32, 32))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JMAAsociadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMAAsociadosActionPerformed
        cntrlArchivos.cargarArchivo(control);
    }//GEN-LAST:event_JMAAsociadosActionPerformed

    private void JMIAsignarAsoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMIAsignarAsoActionPerformed
// 
//        hiloCarga carga = new hiloCarga();
//        Thread hilo = new Thread(carga);
//        hilo.start();
        if (!sorteo.verificarFecha()) {
            JOptionPane.showMessageDialog(null, YGENERADOS, "Información importante", JOptionPane.INFORMATION_MESSAGE);
        } else {
            //hilo.start();
            boolean asociar = sorteo.asociarNumeros();

            if (asociar) {
                JOptionPane.showMessageDialog(this, REXITOSO, "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
                try {
                    control.numerosActuales(accesoBD.numerosActuales());
                    accesoBD.guardarOperacion(A_NUMEROS);
                } catch (SQLException ex) {
                    Logger.getLogger(MainControl.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(this, RERROR, "Operacion fallida", JOptionPane.ERROR_MESSAGE);
//                miRunnable.terminar();
            }
        }
    }//GEN-LAST:event_JMIAsignarAsoActionPerformed

    private void JBSorteoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBSorteoActionPerformed

        if (accesoBD.numerosAsignados()) {
            JOptionPane.showMessageDialog(this, NOHAY);
            sorteo.asociarNumeros();
            try {
                control.numerosActuales(accesoBD.numerosActuales());
                accesoBD.guardarOperacion(A_NUMEROS);
            } catch (SQLException ex) {
                Logger.getLogger(MainControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            if (numerodeSorteos == 0) {
                numeroSorteos.setVisible(true);
                if (numerodeSorteos != 0) {
                    JLCuanto.setText("Número de sorteos: " + numerodeSorteos);
                }
            } else {
                if (sorteosRealizados == numerodeSorteos) {
                    JOptionPane.showMessageDialog(this, SORTEOS_FINAL, MSG_SORTEO, JOptionPane.ERROR_MESSAGE);
                } else {
                    double[] datosSorteo;

                    valorSorteo.visible(sorteosRealizados + 1);
                    datosSorteo = valorSorteo.valoresSorteo();

                    if (datosSorteo[0] != 0) {
                        premio = (float) datosSorteo[0];
                        tipoPremio = (int) datosSorteo[1];
                        animacion(sorteo.generarSorteo());
                    }
                }
            }
        }
    }//GEN-LAST:event_JBSorteoActionPerformed

    private void JMIModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMIModificarActionPerformed
        CambiarEstado cambiar = new CambiarEstado(this, true);
        cambiar.setVisible(rootPaneCheckingEnabled);
    }//GEN-LAST:event_JMIModificarActionPerformed

    private void JMIActualesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMIActualesActionPerformed
        try {
            control.numerosActuales(accesoBD.numerosActuales());
        } catch (SQLException ex) {
            Logger.getLogger(MainControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_JMIActualesActionPerformed

    private void JMIHModificacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMIHModificacionesActionPerformed
        try {
            control.historialModificaciones(accesoBD.historialModificaciones());
        } catch (SQLException ex) {
            Logger.getLogger(MainControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_JMIHModificacionesActionPerformed

    private void JMIHSorteosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JMIHSorteosKeyPressed

    }//GEN-LAST:event_JMIHSorteosKeyPressed

    private void JMIHSorteosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMIHSorteosActionPerformed

    }//GEN-LAST:event_JMIHSorteosActionPerformed

    private void JMIHSorteosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JMIHSorteosMousePressed
        try {
            control.historialSorteos(accesoBD.historialSorteos());
        } catch (SQLException ex) {
            Logger.getLogger(MainControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_JMIHSorteosMousePressed

    private void JMIHSorteosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JMIHSorteosMouseClicked

    }//GEN-LAST:event_JMIHSorteosMouseClicked

    private void JMIHAsociadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMIHAsociadosActionPerformed
        try {
            control.historialNumeros(accesoBD.historialNumeros());

        } catch (SQLException ex) {
            Logger.getLogger(MainControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_JMIHAsociadosActionPerformed

    private void JMIHAsociadosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JMIHAsociadosMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JMIHAsociadosMousePressed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        try {
            control.historialExA(accesoBD.historialEASIN(), false);

        } catch (SQLException ex) {
            Logger.getLogger(MainControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        try {
            control.historialExA(accesoBD.historialEACON(), true);

        } catch (SQLException ex) {
            Logger.getLogger(MainControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void JMIInhabilitadosActualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMIInhabilitadosActualActionPerformed
        try {
            control.historialInhabilitadosActuales(accesoBD.historialInhabilitadosActuales(), false);

        } catch (SQLException ex) {
            Logger.getLogger(MainControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_JMIInhabilitadosActualActionPerformed

    private void JMIHabilitadosActualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMIHabilitadosActualActionPerformed
        try {
            control.historialInhabilitadosActuales(accesoBD.historialHabilitadosActuales(), true);

        } catch (SQLException ex) {
            Logger.getLogger(MainControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_JMIHabilitadosActualActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        agregarAdmin.setTitle("Formulario para eliminar un administrador");
        agregarAdmin.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        agregarAdmin.setTitle("Formulario para agregar un administrador");
        agregarAdmin.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed
    public void animacion(int numero) {

        String ganador = sorteo.ganador(numero, premio, tipoPremio);
        Locale locale = new Locale("es", "CO");
        NumberFormat nf = NumberFormat.getCurrencyInstance(locale);

        if (ganador.equals("anterior") || ganador.equals("")) {
            animacion(sorteo.generarSorteo());

        } else {

            int mil = numero / 1000;
            int cent = (numero % 1000) / 100;
            int dec = ((numero % 1000) % 100) / 10;
            int uni = numero % 10;

            if (numero < 10) {
                JLBalota1.setIcon(CERO);
                JLBalota2.setIcon(CERO);
                JLBalota3.setIcon(CERO);
                JLBalota4.setIcon(seleccionNumero(numero));
            } else {
                if (numero < 100) {
                    JLBalota1.setIcon(CERO);
                    JLBalota2.setIcon(CERO);
                    JLBalota3.setIcon(seleccionNumero(dec));
                    JLBalota4.setIcon(seleccionNumero(uni));
                } else {
                    if (numero < 1000) {
                        JLBalota1.setIcon(CERO);
                        JLBalota2.setIcon(seleccionNumero(cent));
                        JLBalota3.setIcon(seleccionNumero(dec));
                        JLBalota4.setIcon(seleccionNumero(uni));
                    } else {
                        JLBalota1.setIcon(seleccionNumero(mil));
                        JLBalota2.setIcon(seleccionNumero(cent));
                        JLBalota3.setIcon(seleccionNumero(dec));
                        JLBalota4.setIcon(seleccionNumero(uni));
                    }
                }
            }
            JLGanador1.setText("Felicitaciones " + ganador + ", usted ha ganado " + nf.format(premio) + " Pesos");
            sorteosRealizados++;
            JLCuanto.setText("Sorteo " + sorteosRealizados + " de " + numerodeSorteos);
            sorteo.actividad(A_RIFA);
        }
    }

    public ImageIcon seleccionNumero(int numero) {
        switch (numero) {
            case 0:
                return CERO;
            case 1:
                return UNO;
            case 2:
                return DOS;
            case 3:
                return TRES;
            case 4:
                return CUATRO;
            case 5:
                return CINCO;
            case 6:
                return SEIS;
            case 7:
                return SIETE;
            case 8:
                return OCHO;
            case 9:
                return NUEVE;
            default:
                return CERO;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBSorteo1;
    private javax.swing.JLabel JLActivos;
    private javax.swing.JLabel JLBalota1;
    private javax.swing.JLabel JLBalota2;
    private javax.swing.JLabel JLBalota3;
    private javax.swing.JLabel JLBalota4;
    private javax.swing.JLabel JLCuanto;
    private javax.swing.JLabel JLGanador1;
    private javax.swing.JMenuItem JMAAsociados;
    private javax.swing.JMenuItem JMIActuales;
    private javax.swing.JMenuItem JMIAsignarAso;
    private javax.swing.JMenuItem JMIHAsociados;
    private javax.swing.JMenuItem JMIHModificaciones;
    private javax.swing.JMenuItem JMIHSorteos;
    private javax.swing.JMenuItem JMIHabilitadosActual;
    private javax.swing.JMenuItem JMIInhabilitadosActual;
    private javax.swing.JMenuItem JMIModificar;
    private javax.swing.JMenu JMSuper;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JPanel jPanel4;
    // End of variables declaration//GEN-END:variables
}
