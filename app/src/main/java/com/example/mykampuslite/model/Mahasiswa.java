package com.example.mykampuslite.model;

import androidx.room.Entity;
import java.util.Objects;

@Entity(tableName = "mahasiswa")
public class Mahasiswa extends Person {
    private String nim;
    private String jurusan;
    private double ipk;

    public String getNim() {
        return nim;
    }

    // Validasi Encapsulation: NIM harus tepat 10 digit angka
    public void setNim(String nim) {
        if (nim == null || !nim.matches("\\d{10}")) {
            throw new IllegalArgumentException("NIM harus 10 digit angka");
        }
        this.nim = nim;
    }

    public String getJurusan() {
        return jurusan;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }

    public double getIpk() {
        return ipk;
    }

    // Validasi Encapsulation: IPK range 0.0 sampai 4.0
    public void setIpk(double ipk) {
        if (ipk < 0.0 || ipk > 4.0) {
            throw new IllegalArgumentException("IPK harus berada di range 0.0 - 4.0");
        }
        this.ipk = ipk;
    }

    @Override
    public String getRole() {
        return "Mahasiswa";
    }

    // Implementasi Polymorphism
    @Override
    public String getStatusAkademik() {
        if (ipk > 3.5) return "Cumlaude";
        if (ipk > 3.0) return "Memuaskan";
        if (ipk < 2.0) return "Peringatan";
        return "Normal"; // Range 2.0 - 3.0
    }

    // BONUS +10: Digunakan oleh DiffUtil pada ListAdapter
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mahasiswa mahasiswa = (Mahasiswa) o;
        return Double.compare(mahasiswa.ipk, ipk) == 0 &&
                Objects.equals(nim, mahasiswa.nim) &&
                Objects.equals(getNama(), mahasiswa.getNama());
    }

    @Override
    public int hashCode() {
        return Objects.hash(nim, ipk, getNama());
    }
}
