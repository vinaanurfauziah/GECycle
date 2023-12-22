
package gecycle.db;

import java.sql.Connection;// mengimpor connection dari java.sql
import java.sql.DriverManager;// mengimpor kelas DriverManager dari java.sql

// membuat class MysqlConnection
public class MySqlConnection {
    // koneksi ke database menggunakan MySql, username , password
    private final static String DB_URL = "jdbc:mysql://localhost:3306/pp2_membership";
    private final static String DB_USER = "root";
    private final static String  DB_PASS = "";
    
    private static MySqlConnection instance;// menyimpan variabel instance
    
    public static MySqlConnection getInstance(){
        if (instance == null){ // jika instance null, maka metode ini membuat instance baru
            instance = new MySqlConnection();
        }
        return instance; 
    }
    
    //membuat koneksi ke database menggunakan kredensial yang diberikan 
    public Connection getConnection() {
        Connection connection = null;
        try {
            // memuat driver JDBC MySql dan membuat koneksi
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;// objek connection jika berhasil; jika tidak akan mengembalikan null
    }
}
