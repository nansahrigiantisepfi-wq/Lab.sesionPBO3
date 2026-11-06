/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.labsesion;

/**
 * Class Penyewa: pelanggan yang menyewa kendaraan.
 */
public class Penyewa {
    private String nama;
    private String noTelepon;
    private double totalPengeluaran;

    public Penyewa(String nama, String noTelepon) {
        setNama(nama);
        setNoTelepon(noTelepon);
        this.totalPengeluaran = 0;
    }

    public String getNama() {
        return nama;
    }

    public String getNoTelepon() {
        return noTelepon;
    }

    public double getTotalPengeluaran() {
        return totalPengeluaran;
    }

    public void setNama(String nama) {
        if (nama == null || nama.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama tidak boleh kosong.");
        }
        this.nama = nama.trim();
    }

    public void setNoTelepon(String noTelepon) {
        if (noTelepon == null || !noTelepon.matches("\\d{10,13}")) {
            throw new IllegalArgumentException("No. telepon harus 10-13 digit angka.");
        }
        this.noTelepon = noTelepon;
    }

    // Overloading: sewa tanpa diskon
    public double sewa(Kendaraan kendaraan, int hari) {
        validasiHari(hari);
        kendaraan.sewakan();
        double biaya = kendaraan.hitungBiayaSewa(hari);
        totalPengeluaran += biaya;
        return biaya;
    }

    // Overloading: sewa dengan diskon (persen)
    public double sewa(Kendaraan kendaraan, int hari, double diskonPersen) {
        validasiHari(hari);
        kendaraan.sewakan();
        double biaya = kendaraan.hitungBiayaSewa(hari, diskonPersen);
        totalPengeluaran += biaya;
        return biaya;
    }

    private void validasiHari(int hari) {
        if (hari <= 0) {
            throw new IllegalArgumentException("Lama sewa harus minimal 1 hari.");
        }
    }
}