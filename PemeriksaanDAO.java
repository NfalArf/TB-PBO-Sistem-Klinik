import java.sql.*;

// DAO untuk menyimpan pemeriksaan ke database
public class PemeriksaanDAO {

    public void simpanPemeriksaan(Pemeriksaan p) {
        String sql =
            "INSERT INTO pemeriksaan (id_pasien, id_dokter, diagnosa, total) VALUES (?, ?, ?, ?)";

        try (
            Connection c = DatabaseConnection.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)
        ) {
            ps.setString(1, p.getPasien().getIdPasien());
            ps.setString(2, p.getDokter().getIdUser());
            ps.setString(3, p.getDiagnosa());
            ps.setInt(4, p.getTotalBayar());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Gagal menyimpan pemeriksaan");
        }
    }
}
