package com.example.proyek_uas_hakim.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Endemik implements Serializable {

    @SerializedName("id")
    private String id;

    @SerializedName("tipe")
    private String tipe;  // "Hewan" atau "Tumbuhan"

    @SerializedName("nama")
    private String nama;

    @SerializedName("nama_latin")
    private String namaLatin;

    @SerializedName("famili")
    private String famili;

    @SerializedName("genus")
    private String genus;

    @SerializedName("deskripsi")
    private String deskripsi;

    @SerializedName("asal")
    private String asal;

    @SerializedName("sebaran")
    private String sebaran;

    @SerializedName("foto")
    private String foto;  // URL gambar

    @SerializedName("sumber_foto")
    private String sumberFoto;

    @SerializedName("vidio")
    private String video;

    @SerializedName("sumber_vidio")
    private String sumberVideo;

    @SerializedName("status")
    private String status;  // "Aman", "Terancam Punah", dll

    // Constructor kosong (diperlukan Gson)
    public Endemik() {
    }

    // Constructor dengan parameter
    public Endemik(String id, String tipe, String nama, String namaLatin,
                   String deskripsi, String asal, String foto, String status) {
        this.id = id;
        this.tipe = tipe;
        this.nama = nama;
        this.namaLatin = namaLatin;
        this.deskripsi = deskripsi;
        this.asal = asal;
        this.foto = foto;
        this.status = status;
    }

    // Getter dan Setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNamaLatin() {
        return namaLatin;
    }

    public void setNamaLatin(String namaLatin) {
        this.namaLatin = namaLatin;
    }

    public String getFamili() {
        return famili;
    }

    public void setFamili(String famili) {
        this.famili = famili;
    }

    public String getGenus() {
        return genus;
    }

    public void setGenus(String genus) {
        this.genus = genus;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getAsal() {
        return asal;
    }

    public void setAsal(String asal) {
        this.asal = asal;
    }

    public String getSebaran() {
        return sebaran;
    }

    public void setSebaran(String sebaran) {
        this.sebaran = sebaran;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getSumberFoto() {
        return sumberFoto;
    }

    public void setSumberFoto(String sumberFoto) {
        this.sumberFoto = sumberFoto;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getSumberVideo() {
        return sumberVideo;
    }

    public void setSumberVideo(String sumberVideo) {
        this.sumberVideo = sumberVideo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}