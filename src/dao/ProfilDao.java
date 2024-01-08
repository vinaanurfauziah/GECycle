/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import db.MySqlConnection;
import profil.Profil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfilDao {

    public int update(Profil profil) {
    int result = -1;
    try (Connection connection = MySqlConnection.getInstance().getConnection();) {
        PreparedStatement statement = connection.prepareStatement(
                "update biodata set nama = ?, alamat = ?, tanggal_lahir = ?, no_telp = ? where id = ?");
        statement.setString(1, profil.getNama());
        statement.setString(2, profil.getAlamat());
        statement.setDate(3, java.sql.Date.valueOf(profil.getTanggal_lahir()));
        statement.setString(4, profil.getNo_telp());
        statement.setString(5, profil.getId());
        result = statement.executeUpdate();
        System.out.println("Update should be successful");
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    return result;
}

public int delete(Profil profil) {
    int result = -1;
    try (Connection connection = MySqlConnection.getInstance().getConnection();) {
        PreparedStatement statement = connection.prepareStatement("delete from biodata where id = ?");
        statement.setString(1, profil.getId());
        result = statement.executeUpdate();
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    return result;
}

public Profil findByName(String nama) {
    Profil profil = null;
    try {
        ResultSet resultSet = getResultSetNama(nama);
        while (resultSet.next()) {
            profil = new Profil();
            profil.setId(resultSet.getString("id"));
            profil.setNama(resultSet.getString("nama"));
            profil.setAlamat(resultSet.getString("alamat"));
            profil.setTanggal_lahir(resultSet.getDate("tanggal_lahir").toLocalDate());
            profil.setNo_telp(resultSet.getString("no_telp"));
        }
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
    return profil;
}

private static ResultSet getResultSetNama(String nama) throws SQLException {
    Connection connection = MySqlConnection.getInstance().getConnection();
    String query = "select * from biodata where nama = ?";
    PreparedStatement preparedStatement = connection.prepareStatement(query);
    preparedStatement.setString(1, nama);
    ResultSet resultSet = preparedStatement.executeQuery();
    return resultSet;
}

public static List<Profil> findAll() {
    List<Profil> list = new ArrayList<>();
    try (Connection connection = MySqlConnection.getInstance().getConnection();
         Statement statement = connection.createStatement();) {
        try (ResultSet resultSet = statement.executeQuery("select * from biodata ");) {
            while (resultSet.next()) {
                Profil profil = new Profil();
                profil.setId(resultSet.getString("id"));
                profil.setNama(resultSet.getString("nama"));
                profil.setAlamat(resultSet.getString("alamat"));
                profil.setTanggal_lahir(resultSet.getDate("tanggal_lahir").toLocalDate());
                profil.setNo_telp(resultSet.getString("no_telp"));

                list.add(profil);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
}
}