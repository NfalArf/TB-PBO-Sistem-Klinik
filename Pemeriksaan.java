// Class Pemeriksaan
// Menyimpan hasil pemeriksaan pasien
public class Pemeriksaan {

    private Pasien pasien;
    private Dokter dokter;
    private String diagnosa;
    private int biayaTindakan;

    private final int ADMIN = 55000;

    // Constructor
    public Pemeriksaan(Pasien pasien, Dokter dokter, String diagnosa, int biayaTindakan) {
        this.pasien = pasien;
        this.dokter = dokter;
        this.diagnosa = diagnosa;
        this.biayaTindakan = biayaTindakan;
    }

    // Menghitung total bayar
    public int getTotalBayar() {
        return biayaTindakan + ADMIN;
    }

    // Menampilkan hasil pemeriksaan
    public void tampilkanHasil() {
        System.out.println("\n=== HASIL PEMERIKSAAN ===");
        pasien.tampilkan();
        dokter.tampilkanDokter();
        System.out.println("Diagnosa       : " + diagnosa);
        System.out.println("Biaya Tindakan : " + biayaTindakan);
        System.out.println("Total Bayar    : " + getTotalBayar());
    }

    // Getter
    public Pasien getPasien() { return pasien; }
    public Dokter getDokter() { return dokter; }
    public String getDiagnosa() { return diagnosa; }
}
