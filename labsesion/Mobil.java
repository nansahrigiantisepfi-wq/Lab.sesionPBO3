/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.labsesion;

/**
 * Subclass Mobil: biaya = tarif x hari + biaya layanan tetap.
 */
public class Mobil extends Kendaraan implements Perawatan {
    private static final double BIAYA_LAYANAN = 50000;
    private int jumlahPenumpang;

    public Mobil(String plat, String merek, double tarifPerHari, int jumlahPenumpang) {
        super(plat, merek, tarifPerHari);
        setJumlahPenumpang(jumlahPenumpang);
    }

    public int getJumlahPenumpang() {
        return jumlahPenumpang;
    }

    public void setJumlahPenumpang(int jumlahPenumpang) {
        if (jumlahPenumpang <= 0) {
            throw new IllegalArgumentException("Jumlah penumpang harus lebih dari 0.");
        }
        this.jumlahPenumpang = jumlahPenumpang;
    }

    @Override
    public double hitungBiayaSewa(int hari) {
        return getTarifPerHari() * hari + BIAYA_LAYANAN;
    }

    @Override
    public String getJenis() {
        return "Mobil";
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Penumpang    : " + jumlahPenumpang + " orang");
    }

    @Override
    public void lakukanPerawatan() {
        System.out.println("[Perawatan] " + getMerek() + ": ganti oli mesin dan cek rem.");
    }

    @Override
    public String getJadwalPerawatan() {
        return "Setiap 5.000 km atau 3 bulan";
    }
}
