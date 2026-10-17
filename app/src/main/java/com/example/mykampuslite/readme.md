# MyKampus Lite - Manajemen Akademik Prodi

Aplikasi manajemen data mahasiswa dan dosen berbasis Android yang menerapkan arsitektur **MVVM (Model-View-ViewModel)**, **Room Database**, dan **4 Pilar Object-Oriented Programming (OOP)**.

## 🚀 Fitur & Implementasi OOP

Aplikasi ini memenuhi kriteria tugas LAT 4 dengan implementasi sebagai berikut:

### 1. Inheritance (Pewarisan)
Hierarki class disusun dalam 3 level untuk efisiensi kode:
- **BaseEntity (Abstract):** Menangani ID dan timestamp (id, createdAt, updatedAt).
- **Person (Abstract):** Turunan dari BaseEntity yang memuat atribut umum (nama, email) dan method abstract `getRole()`.
- **Mahasiswa & Dosen:** Class konkret yang meng-override atribut spesifik (NIM/NIDN) dan method `getRole()`.

### 2. Encapsulation (Enkapsulasi)
- Semua field di model diset sebagai `private`.
- Akses data dilakukan melalui `getter` dan `setter`.
- **Validasi Ketat di Setter:**
    - `setNama()` memvalidasi minimal 3 karakter.
    - `setNim()` & `setNidn()` memvalidasi tepat 10 digit angka menggunakan Regex.
    - `setIpk()` memvalidasi range 0.0 - 4.0.
    - Jika validasi gagal, model melempar `IllegalArgumentException` yang ditangkap oleh ViewModel untuk ditampilkan sebagai Toast di UI.

### 3. Polymorphism (Polimorfisme)
- Menggunakan interface `EvaluasiAkademik` dengan method `getStatusAkademik()`.
- **Implementasi Berbeda:**
    - Di `Mahasiswa`: Status berdasarkan IPK (Cumlaude, Memuaskan, dll).
    - Di `Dosen`: Status berdasarkan beban mata kuliah (Beban Lebih/Normal).
- **ListAdapter Polimorfik:** Satu `AkademikListAdapter` dapat menerima List berisi objek `Mahasiswa` maupun `Dosen` karena keduanya merupakan turunan dari `Person`.

### 4. Abstraction (Abstraksi)
- Menggunakan `AkademikRepository` sebagai **Interface**.
- `AkademikViewModel` hanya bergantung pada Interface tersebut (Dependency Inversion), sehingga logic database terpisah sepenuhnya dari UI.

---

## 📝 Jawaban Pertanyaan Refleksi

### 1. Apa yang terjadi jika kalian menghapus constructor kosong di Entity Room?
**Jawaban:**
Room Database memerlukan constructor kosong (atau constructor yang parameternya cocok dengan field database) untuk menginstansiasi objek saat mengambil data dari SQLite. Jika constructor kosong dihapus dan tidak ada constructor lain yang sesuai, maka Room akan memunculkan **compile-time error**. Room tidak akan tahu bagaimana cara memetakan baris-baris di tabel database kembali menjadi objek Java.

### 2. Mengapa ViewModel tidak boleh meng-import `android.widget.TextView`? Hubungkan dengan prinsip Abstraction.
**Jawaban:**
Sesuai prinsip **Abstraction** dan **Separation of Concerns**, ViewModel bertugas mengelola data dan logic, bukan mengelola tampilan fisik UI. Jika ViewModel meng-import `TextView`, maka ViewModel menjadi "terikat" (tightly coupled) dengan framework Android UI.
- Hal ini menyulitkan Unit Testing.
- Melanggar abstraksi karena ViewModel seharusnya tidak tahu-menahu tentang bagaimana data ditampilkan (apakah lewat TextView, Toast, atau log). ViewModel hanya menyediakan data (misal melalui LiveData), dan Activity-lah yang bertugas menampilkannya ke UI.

### 3. Berikan 1 contoh nyata di studi kasus ini di mana Polymorphism menghemat baris kode kalian.
**Jawaban:**
Contoh paling nyata adalah pada **`AkademikListAdapter`**. Tanpa polimorfisme, saya harus membuat dua adapter berbeda (satu untuk Mahasiswa, satu untuk Dosen) atau menggunakan banyak percabangan `if-else` (instanceof) di dalam satu adapter.
Dengan polimorfisme, saya cukup menggunakan `getItem(position).getStatusAkademik()`. Kode ini secara otomatis akan memanggil logika IPK jika objeknya Mahasiswa, dan memanggil logika jumlah matkul jika objeknya Dosen, tanpa saya perlu menulis kode pengecekan manual.

---

## 🛠️ Teknologi yang Digunakan
- **Language:** Java
- **Architecture:** MVVM
- **Database:** Room Persistence Library
- **Reactive UI:** LiveData & ViewModel
- **UI Components:** ViewPager2, TabLayout, Material Design 3, ListAdapter (DiffUtil)