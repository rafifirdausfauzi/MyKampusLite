package com.example.mykampuslite.model;

import androidx.room.Entity;
import java.util.Objects;

@Entity(tableName = "dosen")
public class Dosen extends Person {
    private String nidn;
    private int mataKuliahDiampu;

    public String getNidn() {
        return nidn;
    }

    public void setNidn(String nidn) {
        if (nidn == null || !nidn.matches("\\d{10}")) {
            throw new IllegalArgumentException("NIDN harus 10 digit angka");
        }
        this.nidn = nidn;
    }

    public int getMataKuliahDiampu() {
        return mataKuliahDiampu;
    }

    public void setMataKuliahDiampu(int mataKuliahDiampu) {
        if (mataKuliahDiampu < 0) {
            throw new IllegalArgumentException("Jumlah mata kuliah tidak boleh negatif");
        }
        this.mataKuliahDiampu = mataKuliahDiampu;
    }

    @Override
    public String getRole() {
        return "Dosen";
    }

    @Override
    public String getStatusAkademik() {
        if (mataKuliahDiampu > 3) return "Beban Lebih";
        return "Normal";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dosen dosen = (Dosen) o;
        return mataKuliahDiampu == dosen.mataKuliahDiampu &&
                Objects.equals(nidn, dosen.nidn) &&
                Objects.equals(getNama(), dosen.getNama());
    }

    @Override
    public int hashCode() {
        return Objects.hash(nidn, mataKuliahDiampu, getNama());
    }
}
