package com.fon.server.forms;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
import com.fon.common.domain.Admin;
import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import com.fon.server.table.AdminTableModel;
import com.fon.server.threads.ServerThread;

/**
 *
 * @author Aleksa
 */
public class ServerForm extends javax.swing.JFrame {

    private ServerThread serverThread;
    private AdminTableModel tableModel;
    Boolean configExists;

    /**
     * Creates new form ServerFrm
     */
    public ServerForm() {
        initComponents();
        prepareForm();
        configExists = false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnStartServer = new javax.swing.JButton();
        btnStopServer = new javax.swing.JButton();
        lblStatus = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar = new javax.swing.JMenuBar();
        jMenu = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Серверска форма");

        btnStartServer.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnStartServer.setText("Покрени");
        btnStartServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartServerActionPerformed(evt);
            }
        });

        btnStopServer.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnStopServer.setText("Заустави");
        btnStopServer.setEnabled(false);
        btnStopServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopServerActionPerformed(evt);
            }
        });

        lblStatus.setForeground(java.awt.Color.red);
        lblStatus.setText("заустављен");

        tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tbl);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel1.setText("Присутни админи:");

        jLabel2.setText("Статус сервера:");

        jMenuBar.setMinimumSize(getMaximumSize());

        jMenu.setText("Подешавања");
        jMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuMousePressed(evt);
            }
        });
        jMenuBar.add(jMenu);

        setJMenuBar(jMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblStatus)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(btnStartServer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 104, Short.MAX_VALUE)
                .addComponent(btnStopServer)
                .addGap(50, 50, 50))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnStartServer, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnStopServer, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblStatus))
                .addGap(26, 26, 26)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnStartServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartServerActionPerformed
        try {
            configureDb();

            if (!configExists) {
                return;
            }

            serverThread = new ServerThread(this);
            serverThread.start();
            btnStartServer.setEnabled(false);
            btnStopServer.setEnabled(true);
            lblStatus.setText("упаљен");
            lblStatus.setForeground(Color.GREEN);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Упозорење", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnStartServerActionPerformed

    private void configureDb() throws IOException {
        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream("config/dbconfig.properties"));
            configExists = true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ServerForm.class.getName()).log(Level.SEVERE, null, ex);
            DbSettingsForm dbSettingsForm = new DbSettingsForm(this, true, configExists);
            dbSettingsForm.setVisible(true);

            if (configExists == false) {
                throw new IOException("Конфигурација није унета");
            }
        } catch (IOException ex) {
            throw ex;
        }
    }

    private void btnStopServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStopServerActionPerformed
        try {
            serverThread.stopServer();
            serverThread = null;
            btnStartServer.setEnabled(true);
            btnStopServer.setEnabled(false);
            lblStatus.setText("заустављен");
            lblStatus.setForeground(Color.RED);
        } catch (Exception e) {
            Logger.getLogger(ServerForm.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnStopServerActionPerformed

    private void jMenuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuMousePressed
        new DbSettingsForm(this, true, configExists).setVisible(true);
    }//GEN-LAST:event_jMenuMousePressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnStartServer;
    private javax.swing.JButton btnStopServer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JTable tbl;
    // End of variables declaration//GEN-END:variables

    private void prepareForm() {
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        prepareTable();
    }

    private void prepareTable() {
        tableModel = new AdminTableModel();
        tbl.setModel(tableModel);
    }

    public void addAdminToTable(Admin admin) {
        tableModel.addAdmin(admin);
    }

    public void removeAdminFromTable(Admin admin) {
        tableModel.removeAdmin(admin);
    }
}
