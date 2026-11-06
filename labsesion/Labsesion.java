package com.mycompany.labsesion;

import java.util.ArrayList;
import java.util.List;

public class Labsesion {
    public static void main(String[] args) {
        System.out.println("==========================================");
        System.out.println("     SISTEM RENTAL KENDARAAN - SOLO");
        System.out.println("==========================================\n");

        // 1. Runtime polymorphism: objek subclass disimpan di referensi superclass
        List<Kendaraan> daftar = new ArrayList<>();
        daftar.add(new Mobil("AD 1234 AB", "Toyota Avanza", 350000, 7));
        daftar.add(new Motor("AD 5678 CD", "Honda Vario 160", 100000, 160));
        daftar.add(new Bus("AD 9012 EF", "Hino Medium Bus", 1200000, 31));
        daftar.add(new Mobil("AD 3456 GH", "Daihatsu Xenia", 300000, 7));

        System.out.println(">> DAFTAR KENDARAAN");
        int no = 1;
        for (Kendaraan k : daftar) {
            System.out.println("--- Kendaraan #" + no++ + " ---");
            k.tampilkanInfo(); // method override dipanggil sesuai objek aslinya
            System.out.println();
        }

        // 2. Perhitungan biaya (polymorphism + overloading)
        System.out.println(">> SIMULASI BIAYA SEWA 3 HARI");
        for (Kendaraan k : daftar) {
            System.out.println(k.getJenis() + " " + k.getMerek() + " : "
                    + Kendaraan.formatRupiah(k.hitungBiayaSewa(3)));
        }
        System.out.println();

        System.out.println(">> OVERLOADING: BIAYA 7 HARI, TANPA DAN DENGAN DISKON 15%");
        Kendaraan motor = daftar.get(1);
        System.out.println("Tanpa diskon : " + Kendaraan.formatRupiah(motor.hitungBiayaSewa(7)));
        System.out.println("Diskon 15%   : " + Kendaraan.formatRupiah(motor.hitungBiayaSewa(7, 15)));
        System.out.println();

        // 3. Transaksi penyewaan
        System.out.println(">> TRANSAKSI PENYEWAAN");
        Penyewa budi = new Penyewa("Budi Santoso", "081234567890");
        Penyewa sari = new Penyewa("Sari Wulandari", "085712345678");

        double biayaBudi = budi.sewa(daftar.get(0), 3);
        System.out.println(budi.getNama() + " menyewa " + daftar.get(0).getMerek()
                + " 3 hari  -> " + Kendaraan.formatRupiah(biayaBudi));

        double biayaSari = sari.sewa(daftar.get(2), 2, 10);
        System.out.println(sari.getNama() + " menyewa " + daftar.get(2).getMerek()
                + " 2 hari (diskon 10%) -> " + Kendaraan.formatRupiah(biayaSari));

        // Coba sewa kendaraan yang sedang tidak tersedia
        try {
            sari.sewa(daftar.get(0), 1);
        } catch (IllegalStateException e) {
            System.out.println("Gagal: " + e.getMessage());
        }
        System.out.println();

        // 4. Encapsulation: validasi pada setter
        System.out.println(">> UJI ENCAPSULATION (VALIDASI SETTER)");
        try {
            daftar.get(1).setTarifPerHari(-5000);
        } catch (IllegalArgumentException e) {
            System.out.println("Ditolak: " + e.getMessage());
        }
        daftar.get(1).setTarifPerHari(110000);
        System.out.println("Tarif baru " + daftar.get(1).getMerek() + " : "
                + Kendaraan.formatRupiah(daftar.get(1).getTarifPerHari()));
        System.out.println();

        // 5. Interface: semua kendaraan memenuhi kontrak Perawatan
        System.out.println(">> PERAWATAN KENDARAAN (INTERFACE)");
        for (Kendaraan k : daftar) {
            if (k instanceof Perawatan) {
                Perawatan p = (Perawatan) k;
                p.lakukanPerawatan();
                System.out.println("   Jadwal: " + p.getJadwalPerawatan());
            }
        }
        System.out.println();

        // 6. Pengembalian kendaraan
        System.out.println(">> PENGEMBALIAN");
        daftar.get(0).kembalikan();
        daftar.get(2).kembalikan();
        System.out.println(daftar.get(0).getMerek() + " -> "
                + (daftar.get(0).isTersedia() ? "Tersedia" : "Disewa"));
        System.out.println(daftar.get(2).getMerek() + " -> "
                + (daftar.get(2).isTersedia() ? "Tersedia" : "Disewa"));
        System.out.println();

        System.out.println(">> TOTAL PENGELUARAN");
        System.out.println(budi.getNama() + " : " + Kendaraan.formatRupiah(budi.getTotalPengeluaran()));
        System.out.println(sari.getNama() + " : " + Kendaraan.formatRupiah(sari.getTotalPengeluaran()));
    }
}