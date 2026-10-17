package com.example.mykampuslite.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mykampuslite.model.Dosen;
import com.example.mykampuslite.model.Mahasiswa;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Menambahkan anotasi Database dengan entitas Mahasiswa dan Dosen
@Database(entities = {Mahasiswa.class, Dosen.class}, version = 1, exportSchema = false)
public abstract class MyKampusDatabase extends RoomDatabase {

    // Menyambungkan dengan DAO
    public abstract AkademikDao akademikDao();

    // Instance volatile untuk menerapkan Singleton Pattern yang aman (Thread-Safe)
    private static volatile MyKampusDatabase INSTANCE;

    // ExecutorService dengan 4 thread untuk menjalankan proses database di background
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    // Method untuk mendapatkan instance database
    public static MyKampusDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MyKampusDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    MyKampusDatabase.class, "mykampus_database")
                            // Sesuai aturan tugas: TIDAK BOLEH ada allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
