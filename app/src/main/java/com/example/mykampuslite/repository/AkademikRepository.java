package com.example.mykampuslite.repository;

import androidx.lifecycle.LiveData;

import com.example.mykampuslite.model.Dosen;
import com.example.mykampuslite.model.Mahasiswa;

import java.util.List;

public interface AkademikRepository {
    // Kontrak untuk Mahasiswa
    LiveData<List<Mahasiswa>> getAllMahasiswa();
    void insertMahasiswa(Mahasiswa mhs);
    void updateMahasiswa(Mahasiswa mhs);
    void deleteMahasiswa(Mahasiswa mhs);

    // Kontrak untuk Dosen
    LiveData<List<Dosen>> getAllDosen();
    void insertDosen(Dosen dosen);
    void updateDosen(Dosen dosen);
    void deleteDosen(Dosen dosen);
}