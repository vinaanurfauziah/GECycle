/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ACER
 */

public class MySqlConnection {
    public static Connection mycon() throws SQLException{
        Connection con = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con =
        DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pp2_masyarakat","root","");
        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        return con;
    }
}
