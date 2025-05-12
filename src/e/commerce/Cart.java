/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package e.commerce;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.io.File;
import java.sql.*;
import javax.swing.*;

/**
 *
 * @author dexte
 */
public class Cart extends javax.swing.JFrame {

    private CustomerP customerP;

    public void setCustomerP(CustomerP customerP) {
        this.customerP = customerP;
    }

    public Cart() {
        initComponents();
        loadCartItems();

    }
    int userId = Session.userId;
    int cartId;

    public void loadCartItems() {
        try {
            Connection con = DBConnection.getConnection();
            String query = "SELECT c.cart_id, p.name, p.price, p.image, c.quantity FROM cart c "
                    + "JOIN products p ON c.product_id = p.product_id WHERE c.user_id = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, userId);
            ResultSet rs = pst.executeQuery();
            cartP.removeAll();

            while (rs.next()) {
                cartId = rs.getInt("cart_id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");

                JPanel itemRow = new JPanel();
                itemRow.setLayout(new FlowLayout(FlowLayout.LEFT));
                itemRow.setPreferredSize(new Dimension(400, 80));
                itemRow.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

                JCheckBox check = new JCheckBox();

                JLabel lblName = new JLabel(name);
                JLabel lblPrice = new JLabel("₱ " + price);
                JSpinner spinner = new JSpinner(new SpinnerNumberModel(quantity, 1, 100, 1));
                // Store cartId, price, and spinner in checkbox
                check.putClientProperty("cartId", cartId);
                check.putClientProperty("price", price);
                check.putClientProperty("spinner", spinner);

                // Add listener for checkbox
                check.addItemListener(e -> calculateTotal());

                // Add listener for spinner value change
                spinner.addChangeListener(e -> calculateTotal());
                itemRow.add(check);
                itemRow.add(lblName);
                itemRow.add(lblPrice);
                itemRow.add(spinner);
                //itemRow.add(btnRemove);

                cartP.add(itemRow);
            }
            cartP.revalidate();
            cartP.repaint();

            rs.close();
            pst.close();
            con.close();
            calculateTotal();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void calculateTotal() {
    double totalPrice = 0.0;

    for (Component comp : cartP.getComponents()) {
        if (comp instanceof JPanel) {
            JPanel itemRow = (JPanel) comp;
            for (Component innerComp : itemRow.getComponents()) {
                if (innerComp instanceof JCheckBox) {
                    JCheckBox checkBox = (JCheckBox) innerComp;
                    if (checkBox.isSelected()) {
                        double price = (double) checkBox.getClientProperty("price");
                        JSpinner spinner = (JSpinner) checkBox.getClientProperty("spinner");
                        int quantity = (int) spinner.getValue();
                        totalPrice += price * quantity;
                    }
                }
            }
        }
    }

    total.setText("₱ " + String.format("%.2f", totalPrice));
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        total = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        cartP = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setLocation(new java.awt.Point(700, 0));
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(100, 80));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setText("Check- out");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, -1, -1));

        jButton2.setText("Delete");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 50, -1, -1));

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        total.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        total.setForeground(new java.awt.Color(0, 0, 0));
        jPanel3.add(total, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 0, 160, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Total:");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, 50, 30));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 390, 30));

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        cartP.setBackground(new java.awt.Color(204, 204, 204));
        cartP.setLayout(new javax.swing.BoxLayout(cartP, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(cartP);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(10, 40));
        jPanel2.setRequestFocusEnabled(false);
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/back-button.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 40));

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_START);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        //CustomerP c = new CustomerP();

        try {
            Connection con = DBConnection.getConnection();

            // Loop through all item rows inside cartP
            for (Component comp : cartP.getComponents()) {
                if (comp instanceof JPanel) {
                    JPanel itemRow = (JPanel) comp;
                    for (Component innerComp : itemRow.getComponents()) {
                        if (innerComp instanceof JCheckBox) {
                            JCheckBox checkBox = (JCheckBox) innerComp;
                            if (checkBox.isSelected()) {
                                int cartIdToDelete = (int) checkBox.getClientProperty("cartId");

                                // Delete from DB
                                PreparedStatement delStmt = con.prepareStatement("DELETE FROM cart WHERE cart_id = ?");
                                delStmt.setInt(1, cartIdToDelete);
                                delStmt.executeUpdate();
                                if (customerP != null) {
                                    customerP.updateCartCounter(); // ✅ update label on CustomerP
                                }
                            }
                        }
                    }
                }
            }

            loadCartItems(); // reload UI after deletion

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        this.dispose();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        payment pay = new payment(this, true);
        pay.setVisible(true);
        pay.setLocationRelativeTo(null);
        
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Cart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cart().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel cartP;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel total;
    // End of variables declaration//GEN-END:variables
}
