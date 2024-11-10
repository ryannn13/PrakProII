package model;

public class Biodata {
    private String id;
    private String nama;
    private TempatLahir tempatLahir;
    private String tanggalLahir;
    private String alamat;
    private String umur;
    private String jenisKelamin;

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return id;
    }

    public void setNama(String nama){
        this.nama = nama;
    }

    public String getNama(){
        return nama;
    }

    public void setTempatLahir(TempatLahir tempatLahir){
        this.tempatLahir = tempatLahir;
    }

    public TempatLahir getTempatLahir(){
        return tempatLahir;
    }

    public void setTanggalLahir(String tanggalLahir){
        this.tanggalLahir = tanggalLahir;
    }

    public String getTanggalLahir(){
        return tanggalLahir;
    }

    public void setAlamat(String alamat){
        this.alamat = alamat;
    }

    public String getAlamat(){
        return alamat;
    }

    public void setUmur(String umur){
        this.umur = umur;
    }

    public String getUmur(){
        return umur;
    }

    public void setJenisKelamin(String jenisKelamin){
        this.jenisKelamin = jenisKelamin;
    }

    public String getJenisKelamin(){
        return jenisKelamin;
    }
}
