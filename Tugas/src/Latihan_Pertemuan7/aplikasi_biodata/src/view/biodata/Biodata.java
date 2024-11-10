package view.biodata;

import model.TempatLahir;

public class Biodata {
    private String id;
    private String nama;
    private TempatLahir tempatLahir;
    private String tanggalLahir;
    private String alamat;
    private String umur;
    private String jenisKelamin;

    // Konstruktor lengkap
    public Biodata(String id, String nama, TempatLahir tempatLahir, String tanggalLahir, String alamat, String umur, String jenisKelamin) {
        this.id = id;
        this.nama = nama;
        this.tempatLahir = tempatLahir;
        this.tanggalLahir = tanggalLahir;
        this.alamat = alamat;
        this.umur = umur;
        this.jenisKelamin = jenisKelamin;
    }

    // Getters dan setters untuk semua atribut
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public TempatLahir getTempatLahir() { return tempatLahir; }
    public void setTempatLahir(TempatLahir tempatLahir) { this.tempatLahir = tempatLahir; }

    public String getTanggalLahir() { return tanggalLahir; }
    public void setTanggalLahir(String tanggalLahir) { this.tanggalLahir = tanggalLahir; }

    public String getAlamat() { return alamat; }
    public void setAlamat(String alamat) { this.alamat = alamat; }

    public String getUmur() { return umur; }
    public void setUmur(String umur) { this.umur = umur; }

    public String getJenisKelamin() { return jenisKelamin; }
    public void setJenisKelamin(String jenisKelamin) { this.jenisKelamin = jenisKelamin; }
}
