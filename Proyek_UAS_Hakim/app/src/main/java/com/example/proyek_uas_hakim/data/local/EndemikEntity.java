package com.example.proyek_uas_hakim.data.local;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "endemik")
public class EndemikEntity {

    @PrimaryKey
    @NonNull
    private String id;

    private String tipe;
    private String nama;
    private String namaLatin;
    private String famili;
    private String genus;
    private String deskripsi;
    private String asal;
    private String sebaran;
    private String foto;
    private String sumberFoto;
    private String video;
    private String sumberVideo;
    private String status;

    public EndemikEntity(@NonNull String id, String tipe, String nama, String namaLatin,
                         String famili, String genus, String deskripsi, String asal,
                         String sebaran, String foto, String sumberFoto, String video,
                         String sumberVideo, String status) {
        this.id = id;
        this.tipe = tipe;
        this.nama = nama;
        this.namaLatin = namaLatin;
        this.famili = famili;
        this.genus = genus;
        this.deskripsi = deskripsi;
        this.asal = asal;
        this.sebaran = sebaran;
        this.foto = foto;
        this.sumberFoto = sumberFoto;
        this.video = video;
        this.sumberVideo = sumberVideo;
        this.status = status;
    }

    @NonNull public String getId() { return id; }
    public String getTipe() { return tipe; }
    public String getNama() { return nama; }
    public String getNamaLatin() { return namaLatin; }
    public String getFamili() { return famili; }
    public String getGenus() { return genus; }
    public String getDeskripsi() { return deskripsi; }
    public String getAsal() { return asal; }
    public String getSebaran() { return sebaran; }
    public String getFoto() { return foto; }
    public String getSumberFoto() { return sumberFoto; }
    public String getVideo() { return video; }
    public String getSumberVideo() { return sumberVideo; }
    public String getStatus() { return status; }
}