/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package e.commerce;

import java.sql.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author dexte
 */
public class CustomerP extends javax.swing.JFrame {

    /**
     * Creates new form AdminP
     */
    public CustomerP() {
        initComponents();
        loadProductsForCustomer();
    }

    private void loadProductsForCustomer() {
     try {
        Connection con = DBConnection.getConnection();
        String query = "SELECT name, price, image FROM products WHERE status = 'AVAILABLE'";
        PreparedStatement pst = con.prepareStatement(query);
        ResultSet rs = pst.executeQuery();

        ShopPP.removeAll();
        
        int productCount = 0;

        while (rs.next()) {
            String name = rs.getString("name");
            int price = rs.getInt("price");
            String imagePath = rs.getString("image");

            // Load Image
            ImageIcon icon;
            File imgFile = new File(imagePath);
            if (imgFile.exists()) {
                icon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(150, 120, Image.SCALE_SMOOTH));
            } else {
                icon = new ImageIcon(getClass().getResource("/icon/placeholder.png"));
            }

            // Panel for one product
            JPanel card = new JPanel();
            card.setPreferredSize(new Dimension(190, 220));
            card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
            card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

            JLabel imageLabel = new JLabel(icon);
            imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel nameLabel = new JLabel(name, JLabel.CENTER);
            nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
            nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel priceLabel = new JLabel("₱ " + price, JLabel.CENTER);
            priceLabel.setForeground(Color.DARK_GRAY);
            priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JButton btnAdd = new JButton("Add to Cart");
            btnAdd.setBackground(new Color(240, 0, 80));
            btnAdd.setForeground(Color.WHITE);
            btnAdd.setFocusPainted(false);
            btnAdd.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnAdd.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btnAdd.setPreferredSize(new Dimension(100, 30));

            // You can attach a listener to btnAdd to add to cart here
            
            btnAdd.addActionListener(new ActionListener(){
                
                 public void actionPerformed(ActionEvent e) {
                
                System.out.println("button is click");
            }
            });
            

            card.add(Box.createVerticalStrut(10));
            card.add(imageLabel);
            card.add(Box.createVerticalStrut(5));
            card.add(nameLabel);
            card.add(priceLabel);
            card.add(Box.createVerticalStrut(5));
            card.add(btnAdd);

            ShopPP.add(card);
            productCount++;
        }

        // Dynamically adjust ShopP size for scroll
        int cardsPerRow = 5;
        int cardHeightWithGap = 240;
        int rows = (int) Math.ceil((double) productCount / cardsPerRow);
        ShopPP.setPreferredSize(new Dimension(800, rows * cardHeightWithGap));

        

        rs.close();
        pst.close();
        con.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Home = new javax.swing.JLabel();
        Shop = new javax.swing.JLabel();
        Contact = new javax.swing.JLabel();
        logout = new javax.swing.JLabel();
        cart = new javax.swing.JLabel();
        ParentP = new javax.swing.JPanel();
        HomeP = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        ShopP = new javax.swing.JPanel();
        ContactP = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        ShopPP = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1200, 70));
        jPanel1.setRequestFocusEnabled(false);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/cart icon (1).png"))); // NOI18N
        jLabel1.setText("jLabel1");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(242, 92, 5));
        jLabel2.setText("Fake Store");

        Home.setBackground(new java.awt.Color(0, 0, 0));
        Home.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Home.setForeground(new java.awt.Color(0, 0, 0));
        Home.setText("Home");
        Home.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                HomeFocusGained(evt);
            }
        });
        Home.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HomeMouseClicked(evt);
            }
        });

        Shop.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Shop.setForeground(new java.awt.Color(0, 0, 0));
        Shop.setText("Shop");
        Shop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ShopMouseClicked(evt);
            }
        });

        Contact.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Contact.setForeground(new java.awt.Color(0, 0, 0));
        Contact.setText("Contact");
        Contact.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ContactMouseClicked(evt);
            }
        });

        logout.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        logout.setForeground(new java.awt.Color(0, 0, 0));
        logout.setText("Log out");
        logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoutMouseClicked(evt);
            }
        });

        cart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/9025885_shopping_cart_icon (1).png"))); // NOI18N
        cart.setText("jLabel7");
        cart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cartMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 494, Short.MAX_VALUE)
                .addComponent(Home)
                .addGap(49, 49, 49)
                .addComponent(Shop)
                .addGap(40, 40, 40)
                .addComponent(Contact)
                .addGap(41, 41, 41)
                .addComponent(logout)
                .addGap(35, 35, 35)
                .addComponent(cart, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addComponent(Home)
                    .addComponent(Shop)
                    .addComponent(Contact)
                    .addComponent(logout)
                    .addComponent(cart, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        ParentP.setBackground(new java.awt.Color(240, 240, 240));
        ParentP.setLayout(new java.awt.CardLayout());

        HomeP.setBackground(new java.awt.Color(240, 240, 240));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Latest Deals");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(242, 92, 5));
        jLabel4.setText("Unbeatable Offers ");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("This Season");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Upgrade your lifestyle with top-quality products");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("designed for comfort and efficiency!");

        jButton1.setBackground(new java.awt.Color(242, 92, 5));
        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Shop Now");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel10.setBackground(new java.awt.Color(240, 240, 240));
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/handsome-freelance-hipster-man-sitting-floor-with-his-laptop-computer-bearded-man-glasses-smiling-looking-screen__1_-removebg-preview.png"))); // NOI18N
        jLabel10.setText("jLabel10");

        javax.swing.GroupLayout HomePLayout = new javax.swing.GroupLayout(HomeP);
        HomeP.setLayout(HomePLayout);
        HomePLayout.setHorizontalGroup(
            HomePLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HomePLayout.createSequentialGroup()
                .addGap(151, 151, 151)
                .addGroup(HomePLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel8)
                    .addGroup(HomePLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5))
                    .addComponent(jLabel9)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70))
        );
        HomePLayout.setVerticalGroup(
            HomePLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HomePLayout.createSequentialGroup()
                .addGap(147, 147, 147)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(HomePLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(9, 9, 9)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HomePLayout.createSequentialGroup()
                .addContainerGap(48, Short.MAX_VALUE)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );

        ParentP.add(HomeP, "card2");

        ShopP.setBackground(new java.awt.Color(0, 255, 255));
        ParentP.add(ShopP, "card3");

        ContactP.setBackground(new java.awt.Color(204, 102, 0));

        javax.swing.GroupLayout ContactPLayout = new javax.swing.GroupLayout(ContactP);
        ContactP.setLayout(ContactPLayout);
        ContactPLayout.setHorizontalGroup(
            ContactPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1200, Short.MAX_VALUE)
        );
        ContactPLayout.setVerticalGroup(
            ContactPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 530, Short.MAX_VALUE)
        );

        ParentP.add(ContactP, "card4");

        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane2.setViewportView(ShopPP);
        jScrollPane2.setViewportView(ShopPP);

        ParentP.add(jScrollPane2, "card5");

        getContentPane().add(ParentP, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(1200, 600));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
  private JLabel lastClickedLabel = null;
  // Method to update label colors
private void updateLabelColor(JLabel clickedLabel) {
    if (lastClickedLabel != null) {
        lastClickedLabel.setForeground(Color.BLACK); // Reset previous label
    }
    
    clickedLabel.setForeground(Color.RED); // Change clicked label to red
    lastClickedLabel = clickedLabel; // Store as last clicked
}

    private void HomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HomeMouseClicked
        updateLabelColor(Home);
        ParentP.removeAll();
        ParentP.add(HomeP);
        ParentP.repaint();
        ParentP.revalidate();
    }//GEN-LAST:event_HomeMouseClicked

    private void ShopMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ShopMouseClicked
        updateLabelColor(Shop);
         
         JScrollPane scrollPane = new JScrollPane(ShopPP);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        // Dynamically update ParentP to show ShopPP with scroll
        
       ParentP.removeAll();
       ParentP.add(scrollPane, "ShopPP"); 
       ParentP.repaint();
       ParentP.revalidate();
    }//GEN-LAST:event_ShopMouseClicked

    private void ContactMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ContactMouseClicked
        updateLabelColor(Contact);
        ParentP.removeAll();
        ParentP.add(ContactP);
        ParentP.repaint();
        ParentP.revalidate();
    }//GEN-LAST:event_ContactMouseClicked

    private void HomeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_HomeFocusGained
     
    }//GEN-LAST:event_HomeFocusGained

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        updateLabelColor(Shop);
        JScrollPane scrollPane = new JScrollPane(ShopPP);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        // Dynamically update ParentP to show ShopPP with scroll
        
       ParentP.removeAll();
       ParentP.add(scrollPane, "ShopPP"); 
       ParentP.repaint();
       ParentP.revalidate();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void logoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseClicked
        updateLabelColor(logout); 
        int confirm = JOptionPane.showConfirmDialog(this, 
        "Are you sure you want to log out?", 
        "Confirm Logout", 
        JOptionPane.YES_NO_OPTION, 
        JOptionPane.QUESTION_MESSAGE);
    
        if (confirm == JOptionPane.YES_OPTION) {
        
        login lg = new login();
        lg.setVisible(true);
        lg.setLocationRelativeTo(null);
        this.dispose();
    }
    }//GEN-LAST:event_logoutMouseClicked

    private void cartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cartMouseClicked
       // updateLabelColor(cart);
        
        System.out.println("clicked");
         JPanel card = new JPanel();
    card.setPreferredSize(new Dimension(190, 220));
    card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
    card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
    
    // Customize the background color
    card.setBackground(new Color(240, 240, 240)); // Light gray background

    // Add components to the card panel
    JLabel itemLabel = new JLabel("Item Name");
    itemLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the label
    card.add(itemLabel);
    
    JButton btnRemove = new JButton("Remove");
    btnRemove.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the button
    btnRemove.setBackground(new Color(240, 0, 80)); // Custom button color
    btnRemove.setForeground(Color.WHITE);
    btnRemove.setFocusPainted(false);
    btnRemove.setCursor(new Cursor(Cursor.HAND_CURSOR));
    
    // Add action listener to the button
    btnRemove.addActionListener(e -> {
        System.out.println("Item removed from cart!");
        // Logic to remove the item from the cart
    });
    
    card.add(btnRemove);
    
    // Optionally, add some spacing between components
    card.add(Box.createRigidArea(new Dimension(0, 10))); // Vertical space
    
   // JPanel parentPanel = new JPanel(); // Replace with your actual parent panel
    ParentP.add(card);
    
    // Refresh the parent panel
    ParentP.revalidate();
    ParentP.repaint();

    
    }//GEN-LAST:event_cartMouseClicked

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
            java.util.logging.Logger.getLogger(CustomerP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CustomerP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CustomerP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CustomerP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CustomerP().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Contact;
    private javax.swing.JPanel ContactP;
    private javax.swing.JLabel Home;
    private javax.swing.JPanel HomeP;
    private javax.swing.JPanel ParentP;
    private javax.swing.JLabel Shop;
    private javax.swing.JPanel ShopP;
    private javax.swing.JPanel ShopPP;
    private javax.swing.JLabel cart;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel logout;
    // End of variables declaration//GEN-END:variables
}
