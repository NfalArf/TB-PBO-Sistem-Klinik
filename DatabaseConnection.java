import java.sql.Connection;
import java.sql.DriverManager;

// Class untuk mengatur koneksi ke database MySQL
public class DatabaseConnection {

    // URL database (nama database: tb_pbo)
    private static final String URL =
        "jdbc:mysql://localhost:3306/tb_pbo?useSSL=false&serverTimezone=UTC";

    // Username dan password database
    private static final String USER = "root";
    private static final String PASS = ""; // default Laragon

    // Method untuk mendapatkan koneksi database
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception e) {
            System.out.println("Koneksi database gagal!");
            return null;
        }
    }
}
