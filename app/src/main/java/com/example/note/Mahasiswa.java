package com.example.note;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Mahasiswa {
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "nama")
    String nama;
    @ColumnInfo(name = "telepon")
    String telepon;
    @ColumnInfo(name = "email")
    String email;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public String getTelepon() {
        return telepon;
    }
    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
