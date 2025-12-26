/**
 * Superclass User
 * Digunakan sebagai parent untuk Dokter
 */
public class User {

    protected String idUser;
    protected String nama;

    public User(String idUser, String nama) {
        this.idUser = idUser;
        this.nama = nama;
    }

    public String getIdUser() {
        return idUser;
    }

    public String getNama() {
        return nama;
    }

    public void tampilkanUser() {
        System.out.println("ID   : " + idUser);
        System.out.println("Nama : " + nama);
    }
}
