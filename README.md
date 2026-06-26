# Flona

Flona (gabungan dari **Flora** dan **Fauna**) adalah aplikasi Android katalog flora dan fauna endemik Indonesia. Aplikasi ini dibuat sebagai proyek UAS Praktikum Mobile Programming.

## Identitas

- **Nama:** Hakim Syawatul Fitrah
- **NIM:** 2410501049
- **Kelas:** B
- **Mata Kuliah:** Praktikum Mobile Programming

## Tentang Aplikasi

Flona menampilkan data flora dan fauna endemik Indonesia yang diambil langsung dari API publik, lengkap dengan informasi nama, nama latin, asal, sebaran, deskripsi, dan status konservasi (Aman / Terancam Punah). Pengguna dapat mencari data, melihat detail tiap spesies, serta menyimpan spesies favorit untuk diakses kembali secara offline.

## Screenshot

| Home | Detail | Pencarian |
|---|---|---|
| ![Home](screenshots/home.png) | ![Detail](screenshots/detail.png) | ![Pencarian](screenshots/pencarian.png) |

| Favorit | Profil | Splash Screen |
|---|---|---|
| ![Favorit](screenshots/favorit.png) | ![Profil](screenshots/profil.png) | ![Splash](screenshots/splash.png) |

## Fitur

- **Home** — Menampilkan daftar Hewan dan Tumbuhan endemik dalam 2 tab (ViewPager2 + TabLayout), data diambil dari API.
- **Detail** — Menampilkan informasi lengkap suatu spesies (nama, nama latin, status, asal, sebaran, deskripsi, foto), serta badge status berwarna (hijau untuk "Aman", merah untuk "Terancam Punah").
- **Pencarian** — Mencari spesies berdasarkan nama secara real-time.
- **Favorit** — Menyimpan dan menghapus spesies favorit menggunakan database lokal (Room), dapat diakses meski offline.
- **Profil** — Menampilkan identitas pembuat aplikasi.
- **Splash Screen** — Menampilkan logo dan nama aplikasi saat dibuka.

## Teknologi yang Digunakan

| Teknologi | Fungsi |
|---|---|
| Java | Bahasa pemrograman utama |
| Retrofit2 + Gson | Koneksi dan parsing data dari REST API |
| Glide | Memuat dan menampilkan gambar dari URL |
| Room | Database lokal untuk menyimpan data favorit |
| ViewPager2 + TabLayout | Navigasi tab Hewan / Tumbuhan di halaman Home |
| RecyclerView + GridLayoutManager | Menampilkan data dalam bentuk grid |
| ConstraintLayout | Penyusunan tata letak UI |

## Sumber Data

Data flora dan fauna endemik diambil dari API berikut:

```
https://hendroprwk08.github.io/data_endemik/endemik.json
```

## Struktur Proyek

```
com.example.proyek_uas_hakim/
├── api/            -> ApiService.java, RetrofitClient.java
├── model/          -> Endemik.java
├── data/local/     -> AppDatabase.java, FavoritDao.java, FavoritEntity.java
├── repository/     -> EndemikRepository.java, FavoritRepository.java
├── adapter/        -> EndemikAdapter.java
├── ui/
│   ├── home/        -> MainActivity, ListFragment, ViewPagerAdapter
│   ├── search/       -> PencarianActivity
│   ├── favorite/      -> FavoritActivity
│   ├── detail/        -> DetailActivity
│   ├── profile/        -> ProfilActivity
│   └── splash/         -> SplashActivity
```

## Cara Menjalankan

1. Clone repository ini
2. Buka project menggunakan **Android Studio**
3. Tunggu proses **Gradle Sync** selesai
4. Jalankan aplikasi pada emulator atau perangkat Android fisik (disarankan menggunakan perangkat fisik dengan koneksi internet aktif)

## Catatan

Aplikasi ini membutuhkan koneksi internet untuk mengambil data dari API. Data favorit disimpan secara lokal di perangkat menggunakan Room Database sehingga tetap dapat diakses tanpa koneksi internet.
