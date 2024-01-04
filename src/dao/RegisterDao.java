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

       public boolean registerNewUser(String email, String password, String foto, String nama, String alamat, Date tanggal_lahir) {
        boolean registrationSuccess = false;

        try (Connection connection = MySqlConnection.getInstance().getConnection()) {
            // Check if the email is already registered
            if (!isEmailRegistered(email, connection)) {
                // If not registered, proceed with registration
                String query = "INSERT INTO masyarakat (email, password, foto, nama, alamat, tanggal_lahir) VALUES (?, ?, ?, ?, ?, ?)";
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setString(1, email);
                    statement.setString(2, password);
                    statement.setString(3, foto); // Ganti foto
                    statement.setString(4, nama); // Ganti nama
                    statement.setString(5, alamat); // Ganti alamat
                    statement.setDate(6, new java.sql.Date(tanggal_lahir.getTime())); // Ubah tipe data tanggal_lahir

                    // Execute the update query
                    int rowsAffected = statement.executeUpdate();

                    // Check if registration was successful
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

    private boolean isEmailRegistered(String email, Connection connection) throws SQLException {
        String query = "SELECT COUNT(*) FROM masyarakat WHERE email = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);

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
