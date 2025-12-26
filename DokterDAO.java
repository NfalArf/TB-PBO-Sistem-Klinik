import java.sql.*;
import java.util.ArrayList;

// DAO Dokter hanya READ
// Karena data dokter bersifat statis
public class DokterDAO {

    // Mengambil seluruh data dokter dari database
    public ArrayList<Dokter> getAllDokter() {
        ArrayList<Dokter> list = new ArrayList<>();
        String sql = "SELECT * FROM dokter";

        try (
            Connection c = DatabaseConnection.getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql)
        ) {
            while (rs.next()) {
                list.add(new Dokter(
                    rs.getString("id_dokter"),
                    rs.getString("nama"),
                    rs.getString("spesialis")
                ));
            }
        } catch (Exception e) {
            System.out.println("Gagal membaca data dokter");
        }
        return list;
    }
}
