package com.example.mykampuslite.model;

public abstract class Person extends BaseEntity implements EvaluasiAkademik {
    private String nama;
    private String email;

    public String getNama() {
        return nama;
    }

    // Validasi Encapsulation: Nama tidak boleh kosong & minimal 3 karakter
    public void setNama(String nama) {
        if (nama == null || nama.trim().length() < 3) {
            throw new IllegalArgumentException("Nama tidak boleh kosong dan minimal 3 karakter");
        }
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Abstract method yang wajib di-override oleh kelas turunannya
    public abstract String getRole();
}
