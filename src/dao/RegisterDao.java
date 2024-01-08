/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import db.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import register.Register;
import java.util.Date;

/**
 *
 * @author ACER
 */
public class RegisterDao {

public boolean registerNewUser(String nama, String username, String alamat, Date tanggal_lahir, String no_telp, String password) {
    boolean registrationSuccess = false;

    try (Connection connection = MySqlConnection.getInstance().getConnection()) {
        // Check if the username is already registered
        if (!isUsernameRegistered(username, connection)) {
            // If not registered, proceed with registration
            String query = "INSERT INTO masyarakat (nama, username, alamat, tanggal_lahir, no_telp, password) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, nama);
                statement.setString(2, username);
                statement.setString(3, alamat);
                statement.setDate(4, new java.sql.Date(tanggal_lahir.getTime()));
                statement.setString(5, no_telp);
                statement.setString(6, password);

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    registrationSuccess = true;
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return registrationSuccess;
}

private boolean isUsernameRegistered(String username, Connection connection) throws SQLException {
    String query = "SELECT COUNT(*) FROM masyarakat WHERE username = ?";
    try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setString(1, username);

        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        }
    }
    return false;
    }
}
