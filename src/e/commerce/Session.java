/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package e.commerce;

/**
 *
 * @author dexte
 */
public class Session {

    public static int userId = -1;  // -1 means "no user logged in"

    public static void logout() {
        userId = -1;
    }
}
