# Sistem Rental Kendaraan (Labsesion)

Praktik Lab Session 3B: Pengembangan Program Berbasis Object-Oriented Programming (OOP)

| | |
|---|---|
| **Nama** | [SEPFI NANGSA RIGIANTI] |
| **NIM** | [L0325013] |
| **Kelas** | 3B |
| **Bahasa** | Java |
| **IDE** | Apache NetBeans (Maven) |

## Deskripsi Program

Program console sederhana untuk mengelola penyewaan kendaraan. Sistem ini dapat menampilkan daftar kendaraan, menghitung biaya sewa untuk setiap jenis kendaraan, memproses transaksi penyewaan oleh pelanggan, menjalankan perawatan kendaraan, dan mencatat total pengeluaran setiap penyewa.

Tema ini dipilih karena hubungan antar class-nya logis: `Mobil`, `Motor`, dan `Bus` sama-sama kendaraan (superclass `Kendaraan`), tetapi tiap jenis memiliki aturan biaya sewa dan perawatan yang berbeda.

## Struktur Class

| File | Jenis | Peran |
|---|---|---|
| `Labsesion.java` | Class utama | Berisi `main`, membuat objek dan menjalankan simulasi |
| `Kendaraan.java` | Abstract class | Superclass dengan atribut umum dan abstract method `hitungBiayaSewa` |
| `Mobil.java` | Subclass | Biaya = tarif × hari + biaya layanan tetap |
| `Motor.java` | Subclass | Biaya = tarif × hari, diskon 10% jika sewa 7 hari atau lebih |
| `Bus.java` | Subclass | Biaya = (tarif + biaya sopir) × hari |
| `Perawatan.java` | Interface | Kontrak perawatan kendaraan |
| `Penyewa.java` | Class | Pelanggan yang menyewa kendaraan |

```
            <<abstract>>                  <<interface>>
             Kendaraan                      Perawatan
                 ▲                              ▲
     ┌───────────┼───────────┐                  │ implements
     │           │           │                  │
   Mobil       Motor        Bus ────────────────┘
 (extends + implements Perawatan untuk ketiganya)

 Penyewa ──menyewa──► Kendaraan
```

## Penerapan Konsep OOP

### 1. Encapsulation
- Semua atribut dideklarasikan `private` (contoh: `plat`, `merek`, `tarifPerHari`, `tersedia` pada `Kendaraan`).
- Akses melalui getter dan setter, misalnya `getTarifPerHari()` dan `setTarifPerHari()`.
- Setter memvalidasi data. Contohnya tarif tidak boleh kurang dari atau sama dengan 0, dan nomor telepon harus 10-13 digit angka.
- Perubahan status kendaraan dilakukan lewat method `sewakan()` dan `kembalikan()`, bukan mengubah atribut langsung.

### 2. Inheritance
- Superclass: `Kendaraan`.
- Subclass: `Mobil`, `Motor`, dan `Bus`, semuanya memakai keyword `extends`.
- Constructor setiap subclass memanggil `super(plat, merek, tarifPerHari)`.

### 3. Polymorphism
- **Overriding:** `hitungBiayaSewa()`, `getJenis()`, `tampilkanInfo()`, `lakukanPerawatan()`, dan `getJadwalPerawatan()` ditandai `@Override`.
- **Overloading:**
  - `hitungBiayaSewa(int hari)` dan `hitungBiayaSewa(int hari, double diskonPersen)` pada `Kendaraan`.
  - `sewa(Kendaraan, int)` dan `sewa(Kendaraan, int, double)` pada `Penyewa`.
- **Runtime polymorphism:** objek `Mobil`, `Motor`, dan `Bus` disimpan dalam `List<Kendaraan>`, lalu dipanggil lewat satu perulangan. Method yang dijalankan mengikuti tipe objek aslinya.

### 4. Abstract Class
- `Kendaraan` adalah abstract class dengan abstract method `hitungBiayaSewa(int hari)`.
- Method tersebut diimplementasikan oleh `Mobil`, `Motor`, dan `Bus` dengan rumus yang berbeda-beda.

### 5. Interface
- `Perawatan` memiliki dua method: `lakukanPerawatan()` dan `getJadwalPerawatan()`.
- `Mobil`, `Motor`, dan `Bus` memakai `implements Perawatan` dan semuanya memenuhi kontrak tersebut.

## Ketentuan Tambahan

- Jumlah class/interface: 7 (minimal 4, termasuk class utama).
- Jumlah objek: 6, yaitu 4 kendaraan (2 `Mobil`, 1 `Motor`, 1 `Bus`) dan 2 `Penyewa` (minimal 4).

## Cara Menjalankan

### Dengan NetBeans
1. Buka project `Labsesion` di NetBeans.
2. Pastikan semua file berada di package `com.mycompany.labsesion`.
3. Klik kanan `Labsesion.java`, lalu pilih **Run File** (atau tekan `Shift+F6`).

### Dengan command line
```bash
mvn clean install
java -cp target/classes com.mycompany.labsesion.Labsesion
```

Kebutuhan: JDK 17 atau lebih baru (project ini dikompilasi dengan Java 25).

## Alur Program

1. Menampilkan daftar 4 kendaraan beserta informasinya.
2. Menghitung simulasi biaya sewa 3 hari untuk tiap kendaraan.
3. Mendemonstrasikan overloading: biaya sewa motor 7 hari tanpa diskon dan dengan diskon 15%.
4. Memproses transaksi penyewaan oleh dua penyewa, termasuk skenario kendaraan yang sedang tidak tersedia.
5. Menguji validasi setter (tarif negatif ditolak).
6. Menjalankan perawatan semua kendaraan melalui interface `Perawatan`.
7. Mengembalikan kendaraan dan menampilkan total pengeluaran setiap penyewa.

## Contoh Output (Cuplikan)

```
>> SIMULASI BIAYA SEWA 3 HARI
Mobil Toyota Avanza : Rp 1.100.000
Motor Honda Vario 160 : Rp 300.000
Bus Hino Medium Bus : Rp 4.500.000
Mobil Daihatsu Xenia : Rp 950.000

>> OVERLOADING: BIAYA 7 HARI, TANPA DAN DENGAN DISKON 15%
Tanpa diskon : Rp 630.000
Diskon 15%   : Rp 535.500
```
