package dao;

import db.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import login.Login;
/**
 *
 * @author ACER
 */
public class LoginDao {
    public Login loginDenganUsername(String username, String password) {
        Login login = null;
        try (Connection connection = MySqlConnection.getInstance().getConnection();) {
            String query = "SELECT * FROM masyarakat WHERE username = ? AND password = ?"; 
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, username);
                statement.setString(2, password); 
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        login = new Login();
                        login.setUsername(resultSet.getString("username"));                  
                    }
                }
            }
        } catch (SQLException e) {           
            e.printStackTrace();
        }
        return login;
    }
}