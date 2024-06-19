package id.my.lowscarlet.uts_native.activity.pengeluaran;

public class Model {
    // TODO Edit This Scarlet
    public String id;
    public String tanggalKeluar;
    public String jumlah;
    public String tujuan;
    public String penggunaId;

    public Model(String jumlah, String tujuan, String penggunaId) {
        this.jumlah = jumlah;
        this.tujuan = tujuan;
        this.penggunaId = penggunaId;
    }
}
