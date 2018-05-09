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
import static Logica.BonoSolidario.accesoBD;
import static Logica.BonoSolidario.numerodeSorteos;
import static Logica.Mensajes.A_NUMEROS;
import static Logica.Mensajes.A_RIFA;
import static Logica.Mensajes.G_Anterior;
import static Logica.Mensajes.G_INHA;
import static Logica.Mensajes.INACTIVO;
import static Logica.Mensajes.MENSAJE;
import static Logica.Mensajes.MSG_SORTEO;
import static Logica.Mensajes.RERROR;
import static Logica.Mensajes.REXITOSO;
import static Logica.Mensajes.SORTEO;
import static Logica.Mensajes.SORTEOS_FINAL;
import Logica.Sorteo;
import java.io.File;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

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

    public MainControl() {
        initComponents();
        this.setResizable(true);
        this.setLocationRelativeTo(this);
        //this.setDefaultCloseOperation(0);
        sorteosRealizados = 0;
        premio = 0;
        tipoPremio = 0;
        valorSorteo = new ValorSorteo(this, true);
        numeroSorteos = new NumSorteos(this, true);
        sorteo = new Sorteo();
        historial = new Historial(this, true);
        JLActivos.setText("Número de participantes para los sorteo: " + accesoBD.numeroAsociadosActivos());
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public void setJLCuanto(JLabel JLCuanto) {
        this.JLCuanto = JLCuanto;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel4 = new javax.swing.JPanel();
        JBSorteo1 = new javax.swing.JButton();
        JLBalota4 = new javax.swing.JLabel();
        JLBalota5 = new javax.swing.JLabel();
        JLBalota6 = new javax.swing.JLabel();
        JLCuanto = new javax.swing.JLabel();
        JLGanador1 = new javax.swing.JLabel();
        JLActivos = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
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
        JMSalir = new javax.swing.JMenu();

        jMenuItem1.setText("jMenuItem1");

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

        JLBalota4.setBackground(new java.awt.Color(204, 255, 153));
        JLBalota4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLBalota4.setAutoscrolls(true);
        JLBalota4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        JLBalota5.setBackground(new java.awt.Color(204, 255, 153));
        JLBalota5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLBalota5.setAutoscrolls(true);
        JLBalota5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        JLBalota6.setBackground(new java.awt.Color(204, 255, 153));
        JLBalota6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JLBalota6.setAutoscrolls(true);
        JLBalota6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(JBSorteo1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addComponent(JLBalota4, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(JLBalota5, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(JLBalota6, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JBSorteo1)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JLBalota5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JLBalota6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JLBalota4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        jMenuItem2.setText("Inhabilitados para los sorteos de este año");
        jMenu3.add(jMenuItem2);

        jMenuItem3.setText("Habilitados para los sorteos de este año");
        jMenu3.add(jMenuItem3);

        jMenuItem4.setText("Historial ex-asociados que participaron en sorteos");
        jMenu3.add(jMenuItem4);

        jMenuItem5.setText("Historial de los ex-asociados que NO participaron en sorteos");
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

        JMIActuales.setText("Informe del número actual de los asociados hábiles para sorteos");
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

        JMSalir.setText("Salir");
        JMSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JMSalirMouseClicked(evt);
            }
        });
        JMSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMSalirActionPerformed(evt);
            }
        });
        jMenuBar1.add(JMSalir);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JLGanador1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(JLActivos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(356, 356, 356)
                        .addComponent(JLCuanto, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 125, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 125, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JLCuanto, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(JLActivos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(23, 23, 23)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JLGanador1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(56, 56, 56))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JMSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMSalirActionPerformed

    }//GEN-LAST:event_JMSalirActionPerformed

    private void JMSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JMSalirMouseClicked
        System.out.println("Esta es");
        System.exit(0);
    }//GEN-LAST:event_JMSalirMouseClicked

    private void JMAAsociadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMAAsociadosActionPerformed
        try {

            JOptionPane.showMessageDialog(null, MENSAJE, "Información importante", JOptionPane.INFORMATION_MESSAGE);
            JFileChooser selecArchivo = new JFileChooser();
            selecArchivo.setFileFilter(new FileNameExtensionFilter("Excel (*.xls)", "xls"));
            selecArchivo.setFileFilter(new FileNameExtensionFilter("Excel (*.xlsx)", "xlsx"));
            selecArchivo.showDialog(null, "Seleccionar archivo");
            archivo = selecArchivo.getSelectedFile();
            if (archivo.getName().endsWith("xls") || archivo.getName().endsWith("xlsx")) {
                JOptionPane.showMessageDialog(this, "Importación exitosa");
                historial.setArchivo(archivo);
                historial.setTipoAccion(true);
                historial.getJBSubir().setEnabled(true);
                historial.setTitle("Carga de los asociados");
                historial.Importar();
                historial.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Elija un formato válido");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "El formato requerido no es correcto. Recuerde que es: NOMBRE, CEDULA, NUMERO", "Error de lectura", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_JMAAsociadosActionPerformed

    private void JMIAsignarAsoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMIAsignarAsoActionPerformed
        if (sorteo.asociarNumeros()) {
            JOptionPane.showMessageDialog(this, REXITOSO, "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
            try {
                historial.setTitle("Lista de los números actuales de cada asociado");
                historial.getJBSubir().setEnabled(false);
                historial.setTipoAccion(false);
                historial.numerosActuales(accesoBD.numerosActuales());
                historial.setVisible(true);
                accesoBD.guardarOperacion(A_NUMEROS);
            } catch (SQLException ex) {
                Logger.getLogger(MainControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(this, RERROR, "Operacion fallida", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_JMIAsignarAsoActionPerformed

    private void JBSorteoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBSorteoActionPerformed
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
                    sorteosRealizados++;
                    JLCuanto.setText("Sorteo " + sorteosRealizados + " de " + numerodeSorteos);
                    premio = (float) datosSorteo[0];
                    tipoPremio = (int) datosSorteo[1];
                    animacion(sorteo.generarSorteo());
                    // accesoBD.guardarOperacion(A_RIFA);
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
            historial.setTitle("Lista de los números actuales de cada asociado");
            historial.getJBSubir().setEnabled(false);
            historial.setTipoAccion(false);
            historial.numerosActuales(accesoBD.numerosActuales());
            historial.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(MainControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_JMIActualesActionPerformed

    private void JMIHModificacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMIHModificacionesActionPerformed

        try {
            historial.setTitle("Historial de moficaciones");
            historial.getJBSubir().setEnabled(false);
            historial.setTipoAccion(false);
            historial.historialModificaciones(accesoBD.historialModificaciones());
            historial.setVisible(true);
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
            historial.setTitle("Historial de los sorteos");
            historial.getJBSubir().setEnabled(false);
            historial.setTipoAccion(false);
            historial.historialSorteos(accesoBD.historialSorteos());
            historial.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(MainControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_JMIHSorteosMousePressed

    private void JMIHSorteosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JMIHSorteosMouseClicked

    }//GEN-LAST:event_JMIHSorteosMouseClicked

    private void JMIHAsociadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMIHAsociadosActionPerformed

        try {
            historial.setTitle("Información del historial de los números asignados a cada asociado");
            historial.getJBSubir().setEnabled(false);
            historial.setTipoAccion(false);
            historial.historialNumeros(accesoBD.historialNumeros());
            historial.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(MainControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_JMIHAsociadosActionPerformed

    private void JMIHAsociadosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JMIHAsociadosMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JMIHAsociadosMousePressed
    public void animacion(int numero) {
        int cent = numero / 100;
        int dec = (numero % 100) / 10;
        int uni = numero % 10;

        if (numero < 10) {
            JLBalota4.setIcon(CERO);
            JLBalota5.setIcon(CERO);
            JLBalota6.setIcon(seleccionNumero(numero));
        } else {
            if (numero < 100) {
                JLBalota4.setIcon(CERO);
                JLBalota5.setIcon(seleccionNumero(dec));
                JLBalota6.setIcon(seleccionNumero(uni));
            } else {
                JLBalota4.setIcon(seleccionNumero(cent));
                JLBalota5.setIcon(seleccionNumero(dec));
                JLBalota6.setIcon(seleccionNumero(uni));
            }
        }
        String ganador = sorteo.ganador(numero, premio, tipoPremio);
        Locale locale = new Locale("es", "CO");
        NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
        if (ganador.equals("false")) {
            JOptionPane.showMessageDialog(this, G_Anterior, G_INHA, JOptionPane.INFORMATION_MESSAGE);
            sorteosRealizados--;
        } else {
            if (ganador.equals("anterior")) {
                JOptionPane.showMessageDialog(this, SORTEO, G_INHA, JOptionPane.INFORMATION_MESSAGE);
            } else {
                JLGanador1.setText("Felicitaciones " + ganador + ", usted ha ganado " + nf.format(premio) + " Pesos");
                sorteo.actividad(A_RIFA);
            }
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
    private javax.swing.JLabel JLBalota4;
    private javax.swing.JLabel JLBalota5;
    private javax.swing.JLabel JLBalota6;
    private javax.swing.JLabel JLCuanto;
    private javax.swing.JLabel JLGanador1;
    private javax.swing.JMenuItem JMAAsociados;
    private javax.swing.JMenuItem JMIActuales;
    private javax.swing.JMenuItem JMIAsignarAso;
    private javax.swing.JMenuItem JMIHAsociados;
    private javax.swing.JMenuItem JMIHModificaciones;
    private javax.swing.JMenuItem JMIHSorteos;
    private javax.swing.JMenuItem JMIModificar;
    private javax.swing.JMenu JMSalir;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JPanel jPanel4;
    // End of variables declaration//GEN-END:variables
}
