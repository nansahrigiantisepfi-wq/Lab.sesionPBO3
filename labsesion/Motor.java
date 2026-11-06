/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.labsesion;

/**
 * Subclass Motor: biaya = tarif x hari, diskon 10% jika sewa >= 7 hari.
 */
public class Motor extends Kendaraan implements Perawatan {
    private int kapasitasCC;

    public Motor(String plat, String merek, double tarifPerHari, int kapasitasCC) {
        super(plat, merek, tarifPerHari);
        setKapasitasCC(kapasitasCC);
    }

    public int getKapasitasCC() {
        return kapasitasCC;
    }

    public void setKapasitasCC(int kapasitasCC) {
        if (kapasitasCC <= 0) {
            throw new IllegalArgumentException("Kapasitas mesin harus lebih dari 0.");
        }
        this.kapasitasCC = kapasitasCC;
    }

    @Override
    public double hitungBiayaSewa(int hari) {
        double total = getTarifPerHari() * hari;
        if (hari >= 7) {
            total *= 0.9;
        }
        return total;
    }

    @Override
    public String getJenis() {
        return "Motor";
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Mesin        : " + kapasitasCC + " cc");
    }

    @Override
    public void lakukanPerawatan() {
        System.out.println("[Perawatan] " + getMerek() + ": ganti oli, setel rantai, cek ban.");
    }

    @Override
    public String getJadwalPerawatan() {
        return "Setiap 2.000 km atau 2 bulan";
    }
}