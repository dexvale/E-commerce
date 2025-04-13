/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package e.commerce;
import java. sql.*;
/**
 *
 * @author dexte
 */
public class DBConnection {
    
    private static Connection con = null;
    private static final String sUrl = "jdbc:mysql://localhost:3306/e-commerce";
    private static final String sUser = "root";
    private static final String sPass = "";

    public static Connection getConnection() {
        try {
            if (con == null || con.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(sUrl, sUser, sPass);
                System.out.println("Database Connected Successfully.");
            }
        } catch (Exception e) {
            e.printStackTrace(); // Print error details
        }
        return con;
    }
    
}
