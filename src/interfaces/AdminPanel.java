/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import AdminConfig.Chat;
import AdminConfig.ChatService;
import controllerPackage.Controller;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.Chatcreated;

/**
 *
 * @author ACER
 */
public class AdminPanel extends javax.swing.JFrame {

    private int admin_id;
    private String admin_name;

    public AdminPanel(int admin_id, String admin_name) {
        initComponents();
        this.admin_id = admin_id;
        this.admin_name = admin_name;

        this.retrive_chat_list();
        this.pull_offline_all();
    }

    public AdminPanel() {
        initComponents();

        this.retrive_chat_list();
        this.pull_offline_all();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        chat_list = new javax.swing.JComboBox<>();
        start_server = new javax.swing.JButton();
        create_chat = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        server_status = new javax.swing.JLabel();
        create_chat_name = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        jButton2.setText("jButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        chat_list.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        getContentPane().add(chat_list, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 340, 300, 40));

        start_server.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        start_server.setText("Start Chat Server");
        start_server.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                start_serverActionPerformed(evt);
            }
        });
        getContentPane().add(start_server, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 410, 170, 30));

        create_chat.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        create_chat.setText("Create");
        create_chat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                create_chatActionPerformed(evt);
            }
        });
        getContentPane().add(create_chat, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 210, 130, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Chat name");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, -1, -1));

        server_status.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        getContentPane().add(server_status, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 490, 310, 20));
        getContentPane().add(create_chat_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 310, 40));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Chat Group List");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 300, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Create Chat");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bg.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 400, 720));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void create_chatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_create_chatActionPerformed

        String chat_name = create_chat_name.getText();

        if (!"".equals(chat_name)) {

            Session s = Controller.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();

            Chatcreated chat_created = new Chatcreated();
            chat_created.setChatname(chat_name);
            chat_created.setDatecreated(new Date());
            s.save(chat_created);
            t.commit();
            s.close();

            this.retrive_chat_list();

            JOptionPane.showMessageDialog(null, "Chat created", "Message", JOptionPane.INFORMATION_MESSAGE);

        } else {
            JOptionPane.showMessageDialog(null, "Chat name is requird", "Alert", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_create_chatActionPerformed

    private void start_serverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_start_serverActionPerformed

        Session s = Controller.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();

        String selected_chat = (String) chat_list.getSelectedItem();
        int chat_id = Integer.parseInt(selected_chat.split(" - ")[0]);
        String chat_name = selected_chat.split(" - ")[1];

        Chatcreated chat = (Chatcreated) s.load(Chatcreated.class, chat_id);

        chat.setStatus(1);
        s.update(chat);
        t.commit();
        s.close();

        this.start_server(chat_name);

    }//GEN-LAST:event_start_serverActionPerformed

    public void pull_offline_all() {

        for (int i = 0; i < chat_list.getItemCount(); i++) {
            int chat_id = Integer.parseInt(chat_list.getItemAt(i).split(" - ")[0]);

            Session s = Controller.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();

            Chatcreated chat = (Chatcreated) s.load(Chatcreated.class, chat_id);

            chat.setStatus(0);
            s.update(chat);
            t.commit();
            s.close();

        }

    }

    public void retrive_chat_list() {

        Session s = Controller.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();

        String sql = "FROM Chatcreated";
        Query q = s.createQuery(sql);
        List<Chatcreated> chatlist = q.list();

        chat_list.removeAllItems();

        for (Iterator<Chatcreated> iterator = chatlist.iterator(); iterator.hasNext();) {
            Chatcreated chat = iterator.next();

            chat_list.addItem(chat.getChatid() + " - " + chat.getChatname());
        }

    }

    public void start_server(String chat_name) {
        try {
            Chat c = new ChatService();

            Registry reg = LocateRegistry.createRegistry(2121);
            reg.bind("rmichatapp", c);

            server_status.setText(chat_name + " is Running..");

        } catch (RemoteException | AlreadyBoundException ex) {
            server_status.setText("Server starting failed..");
            System.out.println(ex);
        }
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AdminPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminPanel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> chat_list;
    private javax.swing.JButton create_chat;
    private javax.swing.JTextField create_chat_name;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel server_status;
    private javax.swing.JButton start_server;
    // End of variables declaration//GEN-END:variables
}
