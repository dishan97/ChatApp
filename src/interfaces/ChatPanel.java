package interfaces;

import AdminConfig.Chat;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class ChatPanel extends javax.swing.JFrame {

    int userid;
    String username;

    Registry reg;
    Chat chat;

    DefaultListModel listmodel = new DefaultListModel();

    public ChatPanel(int userid, String username) {
        initComponents();

        this.userid = userid;
        this.username = username;

        this.start_client();
    }

    public ChatPanel() {
        initComponents();
    }

    public void start_client() {

        try {
            reg = LocateRegistry.getRegistry("localhost", 2121);
            chat = (Chat) reg.lookup("rmichatapp");

            retrivemsg.start();

        } catch (RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        send_btn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        msg_display = new javax.swing.JList<>();
        content_box = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        send_btn.setText(">");
        send_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                send_btnActionPerformed(evt);
            }
        });
        getContentPane().add(send_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 630, 50, 40));

        msg_display.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jScrollPane1.setViewportView(msg_display);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 350, 520));

        content_box.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                content_boxKeyPressed(evt);
            }
        });
        getContentPane().add(content_box, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 630, 290, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bg.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 700));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void send() {
        String content = content_box.getText();

        try {
            Messages msg = new Messages();
            msg.setMsgid(msg.hashCode());
            msg.setUserid(this.userid);
            msg.setUsername(this.username);
            msg.setMsg_content(content);
            chat.set_msg(msg);

            content_box.setText("");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Send faild!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void send_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_send_btnActionPerformed
        this.send();
    }//GEN-LAST:event_send_btnActionPerformed

    private void content_boxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_content_boxKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            this.send();
        }
    }//GEN-LAST:event_content_boxKeyPressed

    Thread retrivemsg = new Thread() {
        public void run() {

            msg_display.setModel(listmodel);
            listmodel.removeAllElements();

            int preiv = 0;
            int i = 0;
            while (true) {
                try {

                    Messages nmsg = chat.broadcast_msg();
                    if (nmsg != null) {
                        if (preiv != nmsg.getMsgid()) {
                            listmodel.add(i, nmsg.getUsername() + " : " + nmsg.getMsg_content());
                            System.out.println(nmsg.getMsgid() + "\t" + nmsg.getUsername() + " : " + nmsg.getMsg_content() + "\n");

//                            if (nmsg.getUserid() == me.getId()) {
//                                send_msg_handler(nmsg);
//                            } else {
//                                recive_msg_handler(nmsg);
//                            }
                            preiv = nmsg.getMsgid();
                            i++;
                        }
                    }

//                    if(newmsg!=preiv){
//                        System.out.println(chat.broadcast().getMessage());
//                        preiv = newmsg;
//                    }
                    Thread.sleep(100);
                } catch (RemoteException | NullPointerException ex) {
                    System.out.println(ex);
                } catch (InterruptedException ex) {

                }
            }

        }
    };

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ChatPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChatPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChatPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChatPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChatPanel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField content_box;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> msg_display;
    private javax.swing.JButton send_btn;
    // End of variables declaration//GEN-END:variables
}
