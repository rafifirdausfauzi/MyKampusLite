package com.example.mykampuslite.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mykampuslite.model.Dosen;
import com.example.mykampuslite.model.Mahasiswa;

import java.util.List;

@Dao
public interface AkademikDao {

    // --- Operasi untuk Mahasiswa ---
    @Insert
    void insertMahasiswa(Mahasiswa mhs);

    @Update
    void updateMahasiswa(Mahasiswa mhs);

    @Delete
    void deleteMahasiswa(Mahasiswa mhs);

    // Mengambil semua data mahasiswa dibungkus dengan LiveData
    @Query("SELECT * FROM mahasiswa ORDER BY id DESC")
    LiveData<List<Mahasiswa>> getAllMahasiswa();


    // --- Operasi untuk Dosen ---
    @Insert
    void insertDosen(Dosen dosen);

    @Update
    void updateDosen(Dosen dosen);

    @Delete
    void deleteDosen(Dosen dosen);

    // Mengambil semua data dosen dibungkus dengan LiveData
    @Query("SELECT * FROM dosen ORDER BY id DESC")
    LiveData<List<Dosen>> getAllDosen();
}
