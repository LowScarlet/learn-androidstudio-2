package com.fari.uts_native.activity.pengeluaran;

public class Model {
    // TODO Edit This Scarlet
    public String id;
    public String tanggalKeluar;
    public Integer jumlah;
    public String tujuan;
    public String penggunaId;

    public Model(String tanggalKeluar, Integer jumlah, String tujuan, String penggunaId) {
        this.tanggalKeluar = tanggalKeluar;
        this.jumlah = jumlah;
        this.tujuan = tujuan;
        this.penggunaId = penggunaId;
    }
}
