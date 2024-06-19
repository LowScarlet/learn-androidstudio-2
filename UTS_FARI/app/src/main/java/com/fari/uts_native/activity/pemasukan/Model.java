package com.fari.uts_native.activity.pemasukan;

public class Model {
    // TODO Edit This Scarlet
    public String id;
    public String tanggalMasuk;
    public Integer jumlah;
    public String penggunaId;
    public String sumberId;

    public Model(String tanggalMasuk, Integer jumlah, String penggunaId, String sumberId) {
        this.tanggalMasuk = tanggalMasuk;
        this.jumlah = jumlah;
        this.penggunaId = penggunaId;
        this.sumberId = sumberId;
    }
}
