import java.util.ArrayList;
import java.util.Scanner;

// Class utama program Sistem Klinik
// Berfungsi sebagai pengendali menu dan alur aplikasi
public class MainApp {

    public static void main(String[] args) {

        // Scanner untuk menerima input dari user
        Scanner input = new Scanner(System.in);

        // Inisialisasi DAO untuk akses database
        PasienDAO pasienDAO = new PasienDAO();
        DokterDAO dokterDAO = new DokterDAO();
        PemeriksaanDAO pemeriksaanDAO = new PemeriksaanDAO();

        int pilih;

        do {
            // Tampilan menu utama
            System.out.println("\n=== SISTEM KLINIK PRATAMA MEDIKA ANDALAS ===");
            System.out.println("1. Tambah Data Pasien");
            System.out.println("2. Tampilkan Data Pasien");
            System.out.println("3. Tampilkan Data Dokter");
            System.out.println("4. Proses Pemeriksaan");
            System.out.println("5. Hapus Data Pasien");
            System.out.println("6. Hapus Semua Data Pasien");
            System.out.println("7. Update Data Pasien");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");

            pilih = input.nextInt();
            input.nextLine(); // membersihkan buffer

            switch (pilih) {

                // =========================
                // 1. CREATE Pasien
                // =========================
                case 1 -> {
                    System.out.print("ID Pasien : ");
                    String id = input.nextLine();

                    System.out.print("Nama      : ");
                    String nama = input.nextLine();

                    System.out.print("Umur      : ");
                    int umur = input.nextInt();
                    input.nextLine();

                    pasienDAO.tambahPasien(new Pasien(id, nama, umur));
                    System.out.println("Data pasien berhasil ditambahkan");
                }

                // =========================
                // 2. READ Pasien
                // =========================
                case 2 -> {
                    ArrayList<Pasien> list = pasienDAO.getAllPasien();

                    if (list.isEmpty()) {
                        System.out.println("Belum ada data pasien");
                    } else {
                        for (Pasien p : list) {
                            p.tampilkan();
                            System.out.println("------------------");
                        }
                    }
                }

                // =========================
                // 3. READ Dokter
                // =========================
                case 3 -> {
                    ArrayList<Dokter> dokterList = dokterDAO.getAllDokter();

                    if (dokterList.isEmpty()) {
                        System.out.println("Data dokter belum tersedia di database");
                    } else {
                        for (Dokter d : dokterList) {
                            d.tampilkanDokter();
                            System.out.println("------------------");
                        }
                    }
                }

                // =========================
                // 4. Proses Pemeriksaan
                // =========================
                case 4 -> {
                    ArrayList<Pasien> pasienList = pasienDAO.getAllPasien();
                    ArrayList<Dokter> dokterList = dokterDAO.getAllDokter();

                    // Validasi data
                    if (pasienList.isEmpty() || dokterList.isEmpty()) {
                        System.out.println("Data pasien atau dokter belum tersedia");
                        break;
                    }

                    // Pilih pasien
                    System.out.println("Pilih Pasien:");
                    for (int i = 0; i < pasienList.size(); i++) {
                        System.out.println((i + 1) + ". " + pasienList.get(i).getNama());
                    }

                    int pIndex = input.nextInt() - 1;
                    input.nextLine();

                    if (pIndex < 0 || pIndex >= pasienList.size()) {
                        System.out.println("Pilihan pasien tidak valid");
                        break;
                    }

                    // Pilih dokter
                    System.out.println("Pilih Dokter:");
                    for (int i = 0; i < dokterList.size(); i++) {
                        System.out.println((i + 1) + ". " + dokterList.get(i).getNama());
                    }

                    int dIndex = input.nextInt() - 1;
                    input.nextLine();

                    if (dIndex < 0 || dIndex >= dokterList.size()) {
                        System.out.println("Pilihan dokter tidak valid");
                        break;
                    }

                    // Input pemeriksaan
                    System.out.print("Diagnosa: ");
                    String diagnosa = input.nextLine();

                    System.out.print("Biaya tindakan: ");
                    int biaya = input.nextInt();
                    input.nextLine();

                    // Membuat objek pemeriksaan
                    Pemeriksaan pemeriksaan = new Pemeriksaan(
                            pasienList.get(pIndex),
                            dokterList.get(dIndex),
                            diagnosa,
                            biaya
                    );

                    // Menampilkan hasil pemeriksaan
                    pemeriksaan.tampilkanHasil();

                    // Menyimpan ke database
                    pemeriksaanDAO.simpanPemeriksaan(pemeriksaan);
                }

                // =========================
                // 5. DELETE Pasien (per ID)
                // =========================
                case 5 -> {
                    System.out.print("Masukkan ID Pasien yang akan dihapus: ");
                    String id = input.nextLine();

                    if (pasienDAO.hapusPasienById(id)) {
                        System.out.println("Data pasien berhasil dihapus");
                    } else {
                        System.out.println("ID pasien tidak ditemukan");
                    }
                }

                // =========================
                // 6. DELETE ALL Pasien
                // =========================
                case 6 -> {
                    System.out.print("Yakin hapus semua data pasien? (y/n): ");
                    char konfirmasi = input.next().charAt(0);
                    input.nextLine();

                    if (konfirmasi == 'y' || konfirmasi == 'Y') {
                        pasienDAO.hapusSemuaPasien();
                        System.out.println("Semua data pasien berhasil dihapus");
                    } else {
                        System.out.println("Penghapusan dibatalkan");
                    }
                }

                // =========================
                // 7. UPDATE Pasien
                // =========================
                case 7 -> {
                    System.out.print("ID Pasien: ");
                    String id = input.nextLine();

                    System.out.print("Nama baru: ");
                    String nama = input.nextLine();

                    System.out.print("Umur baru: ");
                    int umur = input.nextInt();
                    input.nextLine();

                    if (pasienDAO.updatePasien(new Pasien(id, nama, umur))) {
                        System.out.println("Data berhasil diupdate");
                    } else {
                        System.out.println("ID tidak ditemukan");
                    }
                }

                case 0 -> System.out.println("Terima kasih telah menggunakan sistem");

                default -> System.out.println("Menu tidak tersedia");
            }

        } while (pilih != 0);

        input.close();
    }
}
