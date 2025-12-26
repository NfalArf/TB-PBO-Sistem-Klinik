// Class Pasien
// Mengimplementasikan interface KelolaData
public class Pasien implements KelolaData {

    private String idPasien;
    private String nama;
    private int umur;

    // Constructor Pasien
    public Pasien(String idPasien, String nama, int umur) {
        this.idPasien = idPasien;
        this.nama = nama;
        this.umur = umur;
    }

    // Getter
    public String getIdPasien() {
        return idPasien;
    }

    public String getNama() {
        return nama;
    }

    public int getUmur() {
        return umur;
    }

    // Implementasi interface
    @Override
    public void tampilkan() {
        System.out.println("ID Pasien : " + idPasien);
        System.out.println("Nama      : " + nama.toUpperCase());
        System.out.println("Umur      : " + umur);
    }
}
