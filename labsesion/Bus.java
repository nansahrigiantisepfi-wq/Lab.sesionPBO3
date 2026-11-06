/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.labsesion;

/**
 * Subclass Bus: biaya = (tarif + biaya sopir) x hari.
 */
public class Bus extends Kendaraan implements Perawatan {
    private static final double BIAYA_SOPIR_PER_HARI = 300000;
    private int kapasitasKursi;

    public Bus(String plat, String merek, double tarifPerHari, int kapasitasKursi) {
        super(plat, merek, tarifPerHari);
        setKapasitasKursi(kapasitasKursi);
    }

    public int getKapasitasKursi() {
        return kapasitasKursi;
    }

    public void setKapasitasKursi(int kapasitasKursi) {
        if (kapasitasKursi <= 0) {
            throw new IllegalArgumentException("Kapasitas kursi harus lebih dari 0.");
        }
        this.kapasitasKursi = kapasitasKursi;
    }

    @Override
    public double hitungBiayaSewa(int hari) {
        return (getTarifPerHari() + BIAYA_SOPIR_PER_HARI) * hari;
    }

    @Override
    public String getJenis() {
        return "Bus";
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Kursi        : " + kapasitasKursi + " kursi");
    }

    @Override
    public void lakukanPerawatan() {
        System.out.println("[Perawatan] " + getMerek() + ": servis berkala, cek tekanan angin dan kopling.");
    }

    @Override
    public String getJadwalPerawatan() {
        return "Setiap 10.000 km atau 6 bulan";
    }
}