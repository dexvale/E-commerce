/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package e.commerce;

import java.sql.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Window;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

/**
 *
 * @author dexte
 */
public class NewCart extends javax.swing.JDialog {

    private CustomerP customerP;

    public void setCustomerP(CustomerP customerP) {
        this.customerP = customerP;
    }

    public NewCart(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        loadCartItems();
    }

    int userId = Session.userId;
    int cartId;

    public void loadCartItems() {
        try {
            Connection con = DBConnection.getConnection();
            String query = "SELECT c.cart_id, c.product_id, c.product_id, p.name, p.price, p.image, c.quantity FROM cart c "
                    + "JOIN products p ON c.product_id = p.product_id WHERE c.user_id = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, userId);
            ResultSet rs = pst.executeQuery();
            cartP.removeAll();

            while (rs.next()) {
                cartId = rs.getInt("cart_id");
                String productID = rs.getString("product_id");
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
                check.putClientProperty("product_id", productID);
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

    private double calculateTotal() {
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
        return totalPrice;
    }

    public boolean isOrderIDExist(String orderID) {
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement("SELECT COUNT(*) FROM orders WHERE order_id = ?");
            pst.setString(1, orderID);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0; // If count > 0, ID already exists
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String orderID() {
        String order;
        Random random = new Random();
        do {
            int part1 = 100 + random.nextInt(900);
            int part2 = 100 + random.nextInt(900);
            int part3 = 1000 + random.nextInt(9000);
            order = part1 + " - " + part2 + " - " + part3;
        } while (isOrderIDExist(order));
        //txtOID.setText(order);
        return order;

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        remove = new javax.swing.JButton();
        checkout = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        total = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        cartP = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(400, 530));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(10, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 0, 0));
        jLabel2.setText("X");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(368, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(400, 90));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        remove.setText("Remove");
        remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeActionPerformed(evt);
            }
        });
        jPanel2.add(remove, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 57, -1, -1));

        checkout.setText("Checkout");
        checkout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkoutActionPerformed(evt);
            }
        });
        jPanel2.add(checkout, new org.netbeans.lib.awtextra.AbsoluteConstraints(94, 57, -1, -1));

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Total:");

        total.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        total.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(total, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 5, 390, 40));

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_END);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        cartP.setBackground(new java.awt.Color(204, 204, 204));
        cartP.setLayout(new javax.swing.BoxLayout(cartP, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(cartP);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeActionPerformed
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
                                    customerP.updateCartCounter(); // update label on CustomerP
                                    
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
    }//GEN-LAST:event_removeActionPerformed
    public String orderid = orderID();
    private void checkoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkoutActionPerformed
        
        try {
        Connection con = DBConnection.getConnection();
        double totalPrice = calculateTotal();

        // Insert order
        PreparedStatement orderStmt = con.prepareStatement(
            "INSERT INTO orders (order_id, user_id, total_price, status) VALUES (?, ?, ?, ?)"
        );
        orderStmt.setString(1, orderid);
        orderStmt.setInt(2, userId); // Make sure this variable is available
        orderStmt.setDouble(3, totalPrice);
        orderStmt.setString(4, "PENDING");
        orderStmt.executeUpdate();
        

        // Insert order items and delete from cart
        for (Component com : cartP.getComponents()) {
            if (com instanceof JPanel itemRow) {
                for (Component innerCom : itemRow.getComponents()) {
                    if (innerCom instanceof JCheckBox checkBox && checkBox.isSelected()) {
                        String productId = (String) checkBox.getClientProperty("product_id");
                        Double price = (Double) checkBox.getClientProperty("price");
                        JSpinner spinner = (JSpinner) checkBox.getClientProperty("spinner");
                        Integer quantity = (Integer) spinner.getValue();
                        Double subtotal = price * quantity;

                        if (productId != null) {
                            // Insert item
                            PreparedStatement itemStmt = con.prepareStatement(
                                "INSERT INTO order_items (order_id, product_id, quantity, subtotal) VALUES (?, ?, ?, ?)"
                            );
                            itemStmt.setString(1, orderid); // Use custom order ID
                            itemStmt.setString(2, productId);
                            itemStmt.setInt(3, quantity);
                            itemStmt.setDouble(4, subtotal);
                            itemStmt.executeUpdate();
                            itemStmt.close();

                            // Delete item from cart
                            PreparedStatement delStmt = con.prepareStatement("DELETE FROM cart WHERE cart_id = ?");
                            delStmt.setInt(1, cartId);
                            delStmt.executeUpdate();
                            delStmt.close();
                        } else {
                            System.out.println("Missing product_id for selected item.");
                        }
                    }
                }
            }
        }
        orderStmt.close();
        // Refresh UI
        loadCartItems();
        if (customerP != null) {
            customerP.updateCartCounter();
        }

       // JOptionPane.showMessageDialog(this, "Order placed successfully!");

    } catch (Exception e) {
        e.printStackTrace();
       // JOptionPane.showMessageDialog(this, "Error during checkout.");
    }
        Window window = SwingUtilities.getWindowAncestor(this);
        payment pay = new payment((Frame) window, true);
        pay.setLocationRelativeTo(null);
        pay.setVisible(true);


    }//GEN-LAST:event_checkoutActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        this.dispose();
    }//GEN-LAST:event_jLabel2MouseClicked

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
            java.util.logging.Logger.getLogger(NewCart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewCart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewCart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewCart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                NewCart dialog = new NewCart(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel cartP;
    private javax.swing.JButton checkout;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton remove;
    private javax.swing.JLabel total;
    // End of variables declaration//GEN-END:variables
}
