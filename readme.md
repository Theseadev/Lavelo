
# Lavelo - Aplikasi Catatan Inspiratif

Lavelo adalah aplikasi mobile sederhana untuk mencatat momen, ide, dan inspirasi terbaikmu di satu tempat. Aplikasi ini dibuat menggunakan **Jetpack Compose** dan **Kotlin**, dengan dukungan tema gelap dan terang.

---

## Fitur Utama

1. **Buat Catatan Baru**  
   - Tambahkan judul dan isi catatan.
   - Catatan disimpan di **SharedPreferences** dalam format JSON.

2. **Lihat Catatan Tersimpan**  
   - Menampilkan catatan yang sudah disimpan.
   - Kartu catatan memiliki tampilan modern dengan warna tema aplikasi.

4. **UI Responsif dan Modern**  
   - Menggunakan **Jetpack Compose Material3**.
   - Tombol dan kartu memiliki desain rounded dan estetik.

---

## Struktur Proyek

```
id.antasari.uts_mp_230104040057.ui
│
├─ ScreenList.kt       // Halaman utama (menu awal)
├─ ScreenForm.kt       // Halaman buat catatan baru
├─ ScreenSaved.kt      // Halaman lihat catatan tersimpan
└─ res/drawable
   └─ ic_note.xml      // Vector asset icon catatan
```

---

## Teknologi yang Digunakan

- Kotlin  
- Jetpack Compose  
- Material3  
- Accompanist (untuk kontrol status bar)  
- SharedPreferences (penyimpanan sederhana)

---

## Cara Menggunakan

1. **Buka aplikasi** → akan tampil halaman utama dengan opsi buat catatan atau lihat catatan tersimpan.  
2. **Buat catatan baru** → isi judul dan isi catatan → tekan tombol "💾 Simpan Catatan".  
3. **Lihat catatan tersimpan** → tekan tombol "📂 Lihat Catatan Tersimpan".  


## License

Aplikasi ini dibuat untuk tujuan edukasi dan proyek pribadi. Bebas digunakan dan dimodifikasi.
