package com.example.mykampuslite.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.mykampuslite.database.AkademikDao;
import com.example.mykampuslite.database.MyKampusDatabase;
import com.example.mykampuslite.model.Dosen;
import com.example.mykampuslite.model.Mahasiswa;

import java.util.List;

public class AkademikRepositoryImpl implements AkademikRepository {

    private final AkademikDao akademikDao;
    private final LiveData<List<Mahasiswa>> allMahasiswa;
    private final LiveData<List<Dosen>> allDosen;

    // Constructor membutuhkan Application context untuk inisialisasi Database
    public AkademikRepositoryImpl(Application application) {
        MyKampusDatabase db = MyKampusDatabase.getDatabase(application);
        akademikDao = db.akademikDao();

        // LiveData otomatis berjalan di background, jadi bisa langsung dipanggil
        allMahasiswa = akademikDao.getAllMahasiswa();
        allDosen = akademikDao.getAllDosen();
    }

    // --- Implementasi Mahasiswa ---
    @Override
    public LiveData<List<Mahasiswa>> getAllMahasiswa() {
        return allMahasiswa;
    }

    @Override
    public void insertMahasiswa(Mahasiswa mhs) {
        MyKampusDatabase.databaseWriteExecutor.execute(() -> {
            akademikDao.insertMahasiswa(mhs);
        });
    }

    @Override
    public void updateMahasiswa(Mahasiswa mhs) {
        MyKampusDatabase.databaseWriteExecutor.execute(() -> {
            akademikDao.updateMahasiswa(mhs);
        });
    }

    @Override
    public void deleteMahasiswa(Mahasiswa mhs) {
        MyKampusDatabase.databaseWriteExecutor.execute(() -> {
            akademikDao.deleteMahasiswa(mhs);
        });
    }

    // --- Implementasi Dosen ---
    @Override
    public LiveData<List<Dosen>> getAllDosen() {
        return allDosen;
    }

    @Override
    public void insertDosen(Dosen dosen) {
        MyKampusDatabase.databaseWriteExecutor.execute(() -> {
            akademikDao.insertDosen(dosen);
        });
    }

    @Override
    public void updateDosen(Dosen dosen) {
        MyKampusDatabase.databaseWriteExecutor.execute(() -> {
            akademikDao.updateDosen(dosen);
        });
    }

    @Override
    public void deleteDosen(Dosen dosen) {
        MyKampusDatabase.databaseWriteExecutor.execute(() -> {
            akademikDao.deleteDosen(dosen);
        });
    }
}
