Deskripsi Proyek

Aplikasi ini adalah simulasi sistem ATM sederhana yang dibuat menggunakan Java Swing untuk antarmuka pengguna grafis (GUI). Aplikasi memungkinkan pengguna untuk melakukan berbagai transaksi seperti:

Login menggunakan PIN

Mengecek saldo

Menarik uang tunai

Melakukan transfer

Menambahkan saldo

Melihat mutasi transaksi

Aplikasi ini dirancang untuk mempelajari cara membuat aplikasi GUI dengan Java dan memahami konsep dasar pemrograman berorientasi objek.

Fitur Utama

Login:

Pengguna dapat login menggunakan PIN.

Validasi PIN dilakukan sebelum akses diberikan.

Cek Saldo:

Menampilkan saldo saat ini pengguna.

Tarik Tunai:

Pengguna dapat menarik uang dengan jumlah tertentu.

Validasi untuk saldo cukup dan jumlah positif.

Transfer:

Transfer uang ke akun lain dengan memasukkan nama penerima.

Validasi untuk saldo cukup dan jumlah positif.

Tambah Saldo:

Menambahkan saldo ke akun pengguna.

Mutasi Transaksi:

Menampilkan daftar transaksi yang pernah dilakukan oleh pengguna.

Keluar:

Menutup aplikasi.

Struktur Kode

1. Kelas AplikasiATM

Mengatur tampilan GUI menggunakan Java Swing.

Mengelola interaksi pengguna dengan aplikasi.

Menampilkan menu utama setelah login berhasil.

2. Kelas ATM

Mengelola logika utama transaksi.

Menghubungkan data pengguna dengan aksi yang diminta (cek saldo, tarik tunai, dll).

3. Kelas Pengguna

Menyimpan data pengguna seperti PIN, saldo, dan mutasi transaksi.

Menyediakan metode untuk validasi PIN dan pengelolaan data saldo dan mutasi.

4. Kelas PinTidakValidException dan SaldoTidakCukupException

Menangani pengecualian untuk validasi PIN dan saldo tidak cukup.

Cara Menjalankan

Pastikan Java Development Kit (JDK) telah terinstal di komputer Anda.

Salin semua kode ke dalam file AplikasiATM.java.

Buka terminal atau command prompt, lalu navigasikan ke direktori tempat file disimpan.

Jalankan perintah berikut untuk mengkompilasi:

javac AplikasiATM.java

Jalankan aplikasi dengan perintah:

java AplikasiATM

Cara Menggunakan Aplikasi

Masukkan PIN (default: 1812) di layar login.

Setelah berhasil login, pilih menu yang tersedia:

Cek Saldo: Menampilkan saldo saat ini.

Tarik Tunai: Masukkan jumlah uang yang ingin ditarik.

Transfer: Masukkan jumlah uang dan nama penerima.

Tambah Saldo: Masukkan jumlah uang untuk ditambahkan ke saldo.

Mutasi Transaksi: Menampilkan riwayat transaksi.

Keluar: Menutup aplikasi.

Ikuti petunjuk pada setiap dialog yang muncul.

Catatan Penting

Default PIN adalah 1812. PIN ini dapat diubah langsung di kode kelas Pengguna.

Aplikasi tidak menyimpan data secara permanen. Semua data akan hilang ketika aplikasi ditutup.

Gunakan aplikasi ini hanya untuk simulasi dan pembelajaran.

Pengembangan Selanjutnya

Penyimpanan Data Permanen:

Menyimpan data pengguna dan transaksi menggunakan database atau file.

Keamanan Tambahan:

Menambahkan pembatasan jumlah upaya login.

Menambahkan enkripsi untuk PIN.

Peningkatan Antarmuka:

Menambahkan desain yang lebih modern dengan library seperti JavaFX.
