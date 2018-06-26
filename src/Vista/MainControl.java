/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.ControlAnimacion;
import static Modelo.BonoSolidario.numerodeSorteos;
import static Modelo.Mensajes.A_NUMEROS;
import static Modelo.Mensajes.MSG_SORTEO;
import static Modelo.Mensajes.NOHAY;
import static Modelo.Mensajes.RERROR;
import static Modelo.Mensajes.REXITOSO;
import static Modelo.Mensajes.SORTEOS_FINAL;
import static Modelo.Mensajes.YGENERADOS;
import Modelo.Sorteo;
import Controlador.ControlArchivos;
import Controlador.ControlHistorial;
import static Modelo.Mensajes.A_HABILACTUALES;
import static Modelo.Mensajes.A_HACON;
import static Modelo.Mensajes.A_HASIN;
import static Modelo.Mensajes.A_HNS;
import static Modelo.Mensajes.A_HOPERACIONES;
import static Modelo.Mensajes.A_INHABILACTUALES;
import static Modelo.Mensajes.A_RACTUALES;
import static Modelo.Mensajes.A_REPORTESORTEOS;
import Modelo.Peticiones;
import java.io.File;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
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

    public int sorteosRealizados;
    private Sorteo sorteo;
    public float premio;
    public int tipoPremio;
    private File archivo;
    private static Historial historial;
    private NumSorteos numeroSorteos;
    private ControlHistorial controlHistorial;
    private ControlArchivos cntrlArchivos;
    private AddAdministrador agregarAdmin;
    private ImageIcon imagen;
    private Icon icono;
    private Peticiones peticion;

    public MainControl() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/icono.png")).getImage());
        agregarAdmin = new AddAdministrador(this, true);
        sorteosRealizados = 0;
        premio = 0;
        tipoPremio = 0;
        controlHistorial = new ControlHistorial();
        cntrlArchivos = new ControlArchivos();
        numeroSorteos = new NumSorteos(this, true);
        peticion = new Peticiones();
        sorteo = new Sorteo();
        historial = new Historial(this, true);

        super.setSize(super.getToolkit().getScreenSize());
        this.setResizable(true);
        this.setLocationRelativeTo(this);
        JCBPruebaSorteos.setSelected(false);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jLabel2Cargando.setVisible(false);
        JLActivos.setText("Número de participantes para los sorteos: " + peticion.numeroAsociadosActivos());
    }

    public void prepararAsociacion() {
        if (!peticion.numerosAsignados()) {//si no hay números asignados en el año actual, se prepara para una nueva asociación
            if (peticion.prepararAsociacion()) {
            }
        }
    }

    public void cerrarVentana() {
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                if (sorteosRealizados != numerodeSorteos) {
                    JOptionPane.showMessageDialog(MainControl.this, "Aún faltan sorteos por realizar!");
                } else {
                    System.exit(0);
                }
            }
        });
    }

    public void setJLCuanto(JLabel JLCuanto) {
        this.JLCuanto = JLCuanto;
    }

    public JMenu getJMSuper() {
        return JM3;
    }

    public void setJMSuper(JMenu JMSuper) {
        this.JM3 = JMSuper;
    }

    public JLabel getJLActivos() {
        return JLActivos;
    }

    public void setJLActivos(JLabel JLActivos) {
        this.JLActivos = JLActivos;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        JPanelBalotas = new javax.swing.JPanel();
        JBSorteo = new javax.swing.JButton();
        JLBalota2 = new javax.swing.JLabel();
        JLBalota3 = new javax.swing.JLabel();
        JLBalota4 = new javax.swing.JLabel();
        JLBalota1 = new javax.swing.JLabel();
        JLCuanto = new javax.swing.JLabel();
        JLActivos = new javax.swing.JLabel();
        jLabel2Cargando = new javax.swing.JLabel();
        JLFondo = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        JM1 = new javax.swing.JMenu();
        JMExAsociados = new javax.swing.JMenu();
        JMIInhabilitadosActual = new javax.swing.JMenuItem();
        JMIHabilitadosActual = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        JMIHAsociados = new javax.swing.JMenuItem();
        JMIHSorteos = new javax.swing.JMenuItem();
        JMIHModificaciones = new javax.swing.JMenuItem();
        JMIActuales = new javax.swing.JMenuItem();
        JM2 = new javax.swing.JMenu();
        JMIAsignarAso = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        JMAAsociados = new javax.swing.JMenuItem();
        JMIModificar = new javax.swing.JMenuItem();
        JMIModificarDatos = new javax.swing.JMenuItem();
        JM3 = new javax.swing.JMenu();
        JMGestionAdmins = new javax.swing.JMenu();
        JMIAddAdmin = new javax.swing.JMenuItem();
        JMIDeleteAdmin = new javax.swing.JMenuItem();
        JMIReingreso = new javax.swing.JMenuItem();
        JMIVerAdmins = new javax.swing.JMenuItem();
        JCBPruebaSorteos = new javax.swing.JCheckBoxMenuItem();

        jMenuItem1.setText("jMenuItem1");

        jMenu4.setText("File");
        jMenuBar2.add(jMenu4);

        jMenu5.setText("Edit");
        jMenuBar2.add(jMenu5);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("FONDUCAR - Bono Solidario ");
        setBackground(new java.awt.Color(102, 255, 0));
        setMinimumSize(new java.awt.Dimension(1366, 768));
        setResizable(false);
        setSize(new java.awt.Dimension(1366, 768));
        getContentPane().setLayout(null);

        JPanelBalotas.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        JPanelBalotas.setOpaque(false);

        JBSorteo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        JBSorteo.setText("Iniciar Sorteo");
        JBSorteo.addActionListener(new java.awt.event.ActionListener() {
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

        javax.swing.GroupLayout JPanelBalotasLayout = new javax.swing.GroupLayout(JPanelBalotas);
        JPanelBalotas.setLayout(JPanelBalotasLayout);
        JPanelBalotasLayout.setHorizontalGroup(
            JPanelBalotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelBalotasLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(JPanelBalotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPanelBalotasLayout.createSequentialGroup()
                        .addComponent(JBSorteo, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(JPanelBalotasLayout.createSequentialGroup()
                        .addComponent(JLBalota1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(JLBalota2, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addComponent(JLBalota3, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                        .addComponent(JLBalota4, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(15, Short.MAX_VALUE))))
        );
        JPanelBalotasLayout.setVerticalGroup(
            JPanelBalotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelBalotasLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(JBSorteo, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JPanelBalotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JLBalota3, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JLBalota4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JLBalota1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JLBalota2, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        getContentPane().add(JPanelBalotas);
        JPanelBalotas.setBounds(110, 120, 1156, 337);

        JLCuanto.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        JLCuanto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLCuanto.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        JLCuanto.setOpaque(true);
        getContentPane().add(JLCuanto);
        JLCuanto.setBounds(1080, 10, 282, 47);

        JLActivos.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        JLActivos.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        getContentPane().add(JLActivos);
        JLActivos.setBounds(10, 10, 426, 47);

        jLabel2Cargando.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cargando.gif"))); // NOI18N
        getContentPane().add(jLabel2Cargando);
        jLabel2Cargando.setBounds(610, 10, 287, 77);

        JLFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondo.gif"))); // NOI18N
        JLFondo.setMaximumSize(new java.awt.Dimension(1400, 800));
        getContentPane().add(JLFondo);
        JLFondo.setBounds(0, -26, 1370, 760);

        JM1.setText("Informes");

        JMExAsociados.setText("Ex-Asociados");

        JMIInhabilitadosActual.setText("Inhabilitados para los sorteos de este año");
        JMIInhabilitadosActual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMIInhabilitadosActualActionPerformed(evt);
            }
        });
        JMExAsociados.add(JMIInhabilitadosActual);

        JMIHabilitadosActual.setText("Habilitados para los sorteos de este año");
        JMIHabilitadosActual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMIHabilitadosActualActionPerformed(evt);
            }
        });
        JMExAsociados.add(JMIHabilitadosActual);

        jMenuItem4.setText("Historial ex-asociados CON participación en sorteos");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        JMExAsociados.add(jMenuItem4);

        jMenuItem5.setText("Historial de los ex-asociados SIN participaron en sorteos");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        JMExAsociados.add(jMenuItem5);

        JM1.add(JMExAsociados);

        JMIHAsociados.setText("Historial general de los números asignados a cada asociado");
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
        JM1.add(JMIHAsociados);

        JMIHSorteos.setText("Historial general de los sorteos");
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
        JM1.add(JMIHSorteos);

        JMIHModificaciones.setText("Historial de operaciones y movimientos realizados por administradores");
        JMIHModificaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMIHModificacionesActionPerformed(evt);
            }
        });
        JM1.add(JMIHModificaciones);

        JMIActuales.setText("Informe del número actual asignado a cada asociado y ex-asociado con participación");
        JMIActuales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMIActualesActionPerformed(evt);
            }
        });
        JM1.add(JMIActuales);

        jMenuBar1.add(JM1);

        JM2.setText("Operaciones");

        JMIAsignarAso.setText("Asignar números");
        JMIAsignarAso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMIAsignarAsoActionPerformed(evt);
            }
        });
        JM2.add(JMIAsignarAso);

        jMenu1.setText("Operaciones sobre el Asociado");

        JMAAsociados.setText("Agregar asociados");
        JMAAsociados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMAAsociadosActionPerformed(evt);
            }
        });
        jMenu1.add(JMAAsociados);

        JMIModificar.setText("Modificar estado del asociado");
        JMIModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMIModificarActionPerformed(evt);
            }
        });
        jMenu1.add(JMIModificar);

        JMIModificarDatos.setText("Modificar datos del asociado");
        JMIModificarDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMIModificarDatosActionPerformed(evt);
            }
        });
        jMenu1.add(JMIModificarDatos);

        JM2.add(jMenu1);

        jMenuBar1.add(JM2);

        JM3.setText("Super Usuario");

        JMGestionAdmins.setText("Gestionar administradores");

        JMIAddAdmin.setText("Agregar administrador");
        JMIAddAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMIAddAdminActionPerformed(evt);
            }
        });
        JMGestionAdmins.add(JMIAddAdmin);

        JMIDeleteAdmin.setText("Eliminar administrador");
        JMIDeleteAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMIDeleteAdminActionPerformed(evt);
            }
        });
        JMGestionAdmins.add(JMIDeleteAdmin);

        JMIReingreso.setText("Reingresar administrador");
        JMIReingreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMIReingresoActionPerformed(evt);
            }
        });
        JMGestionAdmins.add(JMIReingreso);

        JMIVerAdmins.setText("Ver administradores");
        JMIVerAdmins.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMIVerAdminsActionPerformed(evt);
            }
        });
        JMGestionAdmins.add(JMIVerAdmins);

        JM3.add(JMGestionAdmins);

        JCBPruebaSorteos.setSelected(true);
        JCBPruebaSorteos.setText("Pruebas de sorteos");
        JCBPruebaSorteos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JCBPruebaSorteosActionPerformed(evt);
            }
        });
        JM3.add(JCBPruebaSorteos);

        jMenuBar1.add(JM3);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JMAAsociadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMAAsociadosActionPerformed
        cntrlArchivos.cargarArchivo(controlHistorial);
        JLActivos.setText("Número de participantes para los sorteos: " + peticion.numeroAsociadosActivos());
    }//GEN-LAST:event_JMAAsociadosActionPerformed

    private void JMIAsignarAsoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMIAsignarAsoActionPerformed

        if (peticion.numerosAsignados() && !JCBPruebaSorteos.isSelected()) {

            JOptionPane.showMessageDialog(null, YGENERADOS, "Información importante", JOptionPane.INFORMATION_MESSAGE);

        } else {
            Thread hilo = new Thread() {
                @Override
                public void run() {

                    boolean asociar = true;

                    jLabel2Cargando.setVisible(true);

                    asociar = peticion.asociarNumeros();

                    jLabel2Cargando.setVisible(false);

                    if (asociar) {

                        JOptionPane.showMessageDialog(MainControl.this, REXITOSO, "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
                        try {
                            controlHistorial.numerosActuales(peticion.numerosActuales(A_NUMEROS));
                        } catch (SQLException ex) {
                            Logger.getLogger(MainControl.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        JOptionPane.showMessageDialog(MainControl.this, RERROR, "Operacion fallida", JOptionPane.ERROR_MESSAGE);

                    }
                }

            };

            hilo.start();

        }
    }//GEN-LAST:event_JMIAsignarAsoActionPerformed

    private void JBSorteoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBSorteoActionPerformed
        ValorSorteo valorSorteo = new ValorSorteo(MainControl.this, true);
        Thread hilo = new Thread() {

            @Override
            public void run() {
//                
                if (!peticion.numerosAsignados()) {
                    JOptionPane.showMessageDialog(MainControl.this, NOHAY);
                } else {
                    if (JCBPruebaSorteos.isSelected()) {
                        JLCuanto.setText("Sorteo de prueba");
                        datosSorteo(valorSorteo);
                    } else {
                        if (numerodeSorteos == 0) {//pido la cantidad de sorteos.
                            numeroSorteos.setVisible(true);
                            if (numerodeSorteos != 0) {
                                JLCuanto.setText("Número de sorteos: " + numerodeSorteos);
                                datosSorteo(valorSorteo);
                            }
                        } else {
                            if (sorteosRealizados == numerodeSorteos) {
                                JOptionPane.showMessageDialog(MainControl.this, SORTEOS_FINAL, MSG_SORTEO, JOptionPane.ERROR_MESSAGE);
                            } else {
                                datosSorteo(valorSorteo);
                            }
                        }
                    }
                }
            }
        };

        hilo.start();
    }//GEN-LAST:event_JBSorteoActionPerformed

    public void datosSorteo(ValorSorteo valorSorteo) {
        double[] datosSorteo;
        ControlAnimacion controlAnimacion = new ControlAnimacion(this);

        valorSorteo.visible(sorteosRealizados + 1, JCBPruebaSorteos.isSelected());
        datosSorteo = valorSorteo.valoresSorteo();

        if (datosSorteo[0] != 0) {
            premio = (float) datosSorteo[0];
            tipoPremio = (int) datosSorteo[1];
            controlAnimacion.generarAnimacion(sorteo.generarSorteo());// me regresa un entero
        }
    }

    private void JMIModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMIModificarActionPerformed
        CambiarEstado cambiar = new CambiarEstado(this, true);
        cambiar.setVisible(rootPaneCheckingEnabled);
        JLActivos.setText("Número de participantes para los sorteos: " + peticion.numeroAsociadosActivos());
    }//GEN-LAST:event_JMIModificarActionPerformed

    private void JMIActualesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMIActualesActionPerformed

        if (!peticion.numerosAsignados()) {
            JOptionPane.showMessageDialog(this, NOHAY);
        } else {
            try {
                controlHistorial.numerosActuales(peticion.numerosActuales(A_RACTUALES));
            } catch (SQLException ex) {
                Logger.getLogger(MainControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_JMIActualesActionPerformed

    private void JMIHModificacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMIHModificacionesActionPerformed
        try {
            controlHistorial.historialModificaciones(peticion.historialModificaciones(A_HOPERACIONES));
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
            controlHistorial.historialSorteos(peticion.historialSorteos(A_REPORTESORTEOS));
        } catch (SQLException ex) {
            Logger.getLogger(MainControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_JMIHSorteosMousePressed

    private void JMIHSorteosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JMIHSorteosMouseClicked

    }//GEN-LAST:event_JMIHSorteosMouseClicked

    private void JMIHAsociadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMIHAsociadosActionPerformed
        try {
            controlHistorial.historialNumeros(peticion.historialNumeros(A_HNS));
        } catch (SQLException ex) {
            Logger.getLogger(MainControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_JMIHAsociadosActionPerformed

    private void JMIHAsociadosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JMIHAsociadosMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JMIHAsociadosMousePressed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        try {
            controlHistorial.historialExA(peticion.historialEASIN(A_HASIN));
        } catch (SQLException ex) {
            Logger.getLogger(MainControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        try {
            controlHistorial.historialExA(peticion.historialEACON(A_HACON));
        } catch (SQLException ex) {
            Logger.getLogger(MainControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void JMIInhabilitadosActualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMIInhabilitadosActualActionPerformed
        try {
            controlHistorial.historialInhabilitadosActuales(peticion.historialInhabilitadosActuales(A_INHABILACTUALES));

        } catch (SQLException ex) {
            Logger.getLogger(MainControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_JMIInhabilitadosActualActionPerformed

    private void JMIHabilitadosActualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMIHabilitadosActualActionPerformed
        try {
            controlHistorial.historialInhabilitadosActuales(peticion.historialHabilitadosActuales(A_HABILACTUALES));

        } catch (SQLException ex) {
            Logger.getLogger(MainControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_JMIHabilitadosActualActionPerformed

    private void JMIDeleteAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMIDeleteAdminActionPerformed
        agregarAdmin.setTipoOperacion(2);
        agregarAdmin.enabled(false);
        agregarAdmin.setTitle("Formulario para eliminar un administrador");
        agregarAdmin.setVisible(true);
    }//GEN-LAST:event_JMIDeleteAdminActionPerformed

    private void JMIAddAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMIAddAdminActionPerformed
        agregarAdmin.setTipoOperacion(1);
        agregarAdmin.enabled(true);
        agregarAdmin.setTitle("Formulario para agregar un administrador");
        agregarAdmin.setVisible(true);
    }//GEN-LAST:event_JMIAddAdminActionPerformed

    private void JMIVerAdminsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMIVerAdminsActionPerformed
        try {
            controlHistorial.verAdministradores(peticion.verAdministradores());
        } catch (SQLException ex) {
            Logger.getLogger(MainControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_JMIVerAdminsActionPerformed

    private void JCBPruebaSorteosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JCBPruebaSorteosActionPerformed
        if (JCBPruebaSorteos.isSelected()) {
            JLCuanto.setText("Sorteo de prueba");
        } else {
            if (sorteosRealizados != 0) {
                JLCuanto.setText("Sorteo " + sorteosRealizados + " de " + numerodeSorteos);
            } else {
                JLCuanto.setText("Número de sorteos: " + numerodeSorteos);
            }
        }
        JLBalota1.setIcon(null);
        JLBalota2.setIcon(null);
        JLBalota3.setIcon(null);
        JLBalota4.setIcon(null);

    }//GEN-LAST:event_JCBPruebaSorteosActionPerformed

    private void JMIReingresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMIReingresoActionPerformed
        agregarAdmin.setTipoOperacion(3);
        agregarAdmin.enabled(false);
        agregarAdmin.setTitle("Formulario para reingresar un administrador");
        agregarAdmin.setVisible(true);
    }//GEN-LAST:event_JMIReingresoActionPerformed

    private void JMIModificarDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMIModificarDatosActionPerformed
        DatosAsociado datos = new DatosAsociado(this, true);
        datos.setVisible(true);
    }//GEN-LAST:event_JMIModificarDatosActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton JBSorteo;
    public javax.swing.JCheckBoxMenuItem JCBPruebaSorteos;
    public javax.swing.JLabel JLActivos;
    public javax.swing.JLabel JLBalota1;
    public javax.swing.JLabel JLBalota2;
    public javax.swing.JLabel JLBalota3;
    public javax.swing.JLabel JLBalota4;
    public javax.swing.JLabel JLCuanto;
    public javax.swing.JLabel JLFondo;
    public javax.swing.JMenu JM1;
    public javax.swing.JMenu JM2;
    public javax.swing.JMenu JM3;
    public javax.swing.JMenuItem JMAAsociados;
    public javax.swing.JMenu JMExAsociados;
    public javax.swing.JMenu JMGestionAdmins;
    public javax.swing.JMenuItem JMIActuales;
    public javax.swing.JMenuItem JMIAddAdmin;
    public javax.swing.JMenuItem JMIAsignarAso;
    public javax.swing.JMenuItem JMIDeleteAdmin;
    public javax.swing.JMenuItem JMIHAsociados;
    public javax.swing.JMenuItem JMIHModificaciones;
    public javax.swing.JMenuItem JMIHSorteos;
    public javax.swing.JMenuItem JMIHabilitadosActual;
    public javax.swing.JMenuItem JMIInhabilitadosActual;
    public javax.swing.JMenuItem JMIModificar;
    public javax.swing.JMenuItem JMIModificarDatos;
    public javax.swing.JMenuItem JMIReingreso;
    public javax.swing.JMenuItem JMIVerAdmins;
    public javax.swing.JPanel JPanelBalotas;
    public javax.swing.JLabel jLabel2Cargando;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    public javax.swing.JMenuItem jMenuItem4;
    public javax.swing.JMenuItem jMenuItem5;
    // End of variables declaration//GEN-END:variables
}
