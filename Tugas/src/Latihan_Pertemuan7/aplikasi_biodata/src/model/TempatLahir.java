package model;

public class TempatLahir {
    private String id;
    private String namaKota;

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return id;
    }

    public void setNamaKota(String namaKota){
        this.namaKota = namaKota;
    }

    public String getNamaKota(){
        return namaKota;
    }
}
