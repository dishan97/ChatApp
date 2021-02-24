package interfaces;

import controllerPackage.Controller;
import java.util.Iterator;
import java.util.List;
import javax.swing.DefaultListModel;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.Chatcreated;

public class ClientsChatList extends javax.swing.JFrame {

    private int client_id;
    private String client_name;

    DefaultListModel listmodel = new DefaultListModel();

    public ClientsChatList(int client_id, String client_name) {
        initComponents();

        this.client_id = client_id;
        this.client_name = client_name;

        this.show_chat_groups();

    }

    public ClientsChatList() {
        initComponents();
        this.show_chat_groups();
    }

    public void show_chat_groups() {

        Session s = Controller.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();

        String sql = "FROM Chatcreated";
        Query q = s.createQuery(sql);
        List<Chatcreated> chatlist = q.list();

        chat_group_list.setModel(listmodel);
        listmodel.removeAllElements();

        int i = 0;
        for (Iterator<Chatcreated> iterator = chatlist.iterator(); iterator.hasNext();) {
            Chatcreated chat = iterator.next();

            if (chat.getStatus() == 1) {
                listmodel.add(i, chat.getChatname() + " - [ Online ]");
                online_grp.setText(chat.getChatname() + " - [ Online ]");
            } else {
                listmodel.add(i, chat.getChatname() + " - [ Offline ]");
            }

            i++;
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        online_grp = new javax.swing.JLabel();
        enter_to_chat = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        chat_group_list = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        online_grp.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        getContentPane().add(online_grp, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 400, 310, 30));

        enter_to_chat.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        enter_to_chat.setText("Enter to chat room");
        enter_to_chat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enter_to_chatActionPerformed(evt);
            }
        });
        getContentPane().add(enter_to_chat, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 450, 190, 40));

        chat_group_list.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        chat_group_list.setForeground(new java.awt.Color(102, 102, 102));
        chat_group_list.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        chat_group_list.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(chat_group_list);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 310, 270));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Chat Groups");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 280, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bg.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    private void enter_to_chatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enter_to_chatActionPerformed
        ChatPanel cht_panel = new ChatPanel(client_id, client_name);
        cht_panel.setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_enter_to_chatActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ClientsChatList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientsChatList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientsChatList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientsChatList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientsChatList().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> chat_group_list;
    private javax.swing.JButton enter_to_chat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel online_grp;
    // End of variables declaration//GEN-END:variables
}
