// Class Dokter turunan dari User
// Menerapkan inheritance
public class Dokter extends User {

    private String spesialis;

    // Constructor Dokter
    public Dokter(String idUser, String nama, String spesialis) {
        super(idUser, nama);
        this.spesialis = spesialis;
    }

    public String getSpesialis() {
        return spesialis;
    }

    // Menampilkan data dokter
    public void tampilkanDokter() {
        tampilkanUser();
        System.out.println("Spesialis : " + spesialis);
    }
}
