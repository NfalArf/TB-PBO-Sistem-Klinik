import java.sql.*;
import java.util.ArrayList;

// DAO untuk CRUD data pasien
public class PasienDAO {

    // CREATE
    public void tambahPasien(Pasien p) {
        String sql = "INSERT INTO pasien VALUES (?, ?, ?)";

        try (
            Connection c = DatabaseConnection.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)
        ) {
            ps.setString(1, p.getIdPasien());
            ps.setString(2, p.getNama());
            ps.setInt(3, p.getUmur());
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Gagal menambah pasien");
        }
    }

    // READ
    public ArrayList<Pasien> getAllPasien() {
        ArrayList<Pasien> list = new ArrayList<>();
        String sql = "SELECT * FROM pasien";

        try (
            Connection c = DatabaseConnection.getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql)
        ) {
            while (rs.next()) {
                list.add(new Pasien(
                    rs.getString("id_pasien"),
                    rs.getString("nama"),
                    rs.getInt("umur")
                ));
            }
        } catch (Exception e) {
            System.out.println("Gagal membaca data pasien");
        }
        return list;
    }

    // VALIDASI ID
    public boolean isIdExist(String id) {
        String sql = "SELECT id_pasien FROM pasien WHERE id_pasien=?";

        try (
            Connection c = DatabaseConnection.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)
        ) {
            ps.setString(1, id);
            return ps.executeQuery().next();
        } catch (Exception e) {
            return false;
        }
    }

    // UPDATE
    public boolean updatePasien(Pasien p) {
        if (!isIdExist(p.getIdPasien())) return false;

        String sql = "UPDATE pasien SET nama=?, umur=? WHERE id_pasien=?";

        try (
            Connection c = DatabaseConnection.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)
        ) {
            ps.setString(1, p.getNama());
            ps.setInt(2, p.getUmur());
            ps.setString(3, p.getIdPasien());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // DELETE satu data pasien berdasarkan ID
    public boolean hapusPasienById(String idPasien) {

        // Validasi apakah ID ada di database
        if (!isIdExist(idPasien)) {
            return false; // ID tidak ditemukan
        }

        String sql = "DELETE FROM pasien WHERE id_pasien=?";

        try (
            Connection c = DatabaseConnection.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)
        ) {
            ps.setString(1, idPasien);
            ps.executeUpdate();
            return true; // berhasil dihapus
        } catch (Exception e) {
            return false;
        }
    }

    
    // DELETE ALL
    public void hapusSemuaPasien() {
        String sql = "DELETE FROM pasien";

        try (
            Connection c = DatabaseConnection.getConnection();
            Statement s = c.createStatement()
        ) {
            s.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println("Gagal menghapus data pasien");
        }
    }
}
