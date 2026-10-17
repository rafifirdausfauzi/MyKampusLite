package com.example.mykampuslite.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mykampuslite.model.Dosen;
import com.example.mykampuslite.model.Mahasiswa;
import com.example.mykampuslite.repository.AkademikRepository;
import com.example.mykampuslite.repository.AkademikRepositoryImpl;

import java.util.List;

public class AkademikViewModel extends AndroidViewModel {

    // Memanggil interface Repository, BUKAN implementasinya secara langsung untuk pemanggilan method
    private final AkademikRepository repository;

    private final LiveData<List<Mahasiswa>> allMahasiswa;
    private final LiveData<List<Dosen>> allDosen;

    // MutableLiveData untuk menangkap pesan error validasi
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public AkademikViewModel(@NonNull Application application) {
        super(application);
        // Inisialisasi repository
        repository = new AkademikRepositoryImpl(application);

        allMahasiswa = repository.getAllMahasiswa();
        allDosen = repository.getAllDosen();
    }

    // --- Getter untuk diobservasi oleh Activity/Fragment ---

    public LiveData<List<Mahasiswa>> getAllMahasiswa() {
        return allMahasiswa;
    }

    public LiveData<List<Dosen>> getAllDosen() {
        return allDosen;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    // --- Operasi Insert dengan Validasi (Try-Catch) ---

    public void tambahMahasiswa(String nim, String nama, double ipk) {
        try {
            Mahasiswa mhs = new Mahasiswa();
            // Proses set nilai. Jika validasi di Model gagal, akan melempar IllegalArgumentException
            mhs.setNim(nim);
            mhs.setNama(nama);
            mhs.setIpk(ipk);

            // Jika lolos validasi, simpan ke database
            repository.insertMahasiswa(mhs);
        } catch (IllegalArgumentException e) {
            // Tangkap error dan kirim ke UI via LiveData
            errorMessage.setValue(e.getMessage());
        }
    }

    public void tambahDosen(String nidn, String nama, int matkul) {
        try {
            Dosen dosen = new Dosen();
            dosen.setNidn(nidn);
            dosen.setNama(nama);
            dosen.setMataKuliahDiampu(matkul);

            repository.insertDosen(dosen);
        } catch (IllegalArgumentException e) {
            errorMessage.setValue(e.getMessage());
        }
    }

    // --- Operasi Delete ---

    public void hapusMahasiswa(Mahasiswa mhs) {
        repository.deleteMahasiswa(mhs);
    }

    public void hapusDosen(Dosen dosen) {
        repository.deleteDosen(dosen);
    }
}
