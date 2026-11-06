/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.labsesion;

import java.util.Locale;

/**
 * Abstract class Kendaraan (superclass).
 * Atribut dibuat private (encapsulation) dan diakses lewat getter/setter.
 */
public abstract class Kendaraan {
    private String plat;
    private String merek;
    private double tarifPerHari;
    private boolean tersedia;

    public Kendaraan(String plat, String merek, double tarifPerHari) {
        setPlat(plat);
        setMerek(merek);
        setTarifPerHari(tarifPerHari);
        this.tersedia = true;
    }

    // ===== Getter =====
    public String getPlat() {
        return plat;
    }

    public String getMerek() {
        return merek;
    }

    public double getTarifPerHari() {
        return tarifPerHari;
    }

    public boolean isTersedia() {
        return tersedia;
    }

    // ===== Setter (dengan validasi) =====
    public void setPlat(String plat) {
        if (plat == null || plat.trim().isEmpty()) {
            throw new IllegalArgumentException("Plat nomor tidak boleh kosong.");
        }
        this.plat = plat.trim().toUpperCase();
    }

    public void setMerek(String merek) {
        if (merek == null || merek.trim().isEmpty()) {
            throw new IllegalArgumentException("Merek tidak boleh kosong.");
        }
        this.merek = merek.trim();
    }

    public void setTarifPerHari(double tarifPerHari) {
        if (tarifPerHari <= 0) {
            throw new IllegalArgumentException("Tarif per hari harus lebih dari 0.");
        }
        this.tarifPerHari = tarifPerHari;
    }

    public void setTersedia(boolean tersedia) {
        this.tersedia = tersedia;
    }

    // Perubahan status dilakukan melalui method yang sesuai
    public void sewakan() {
        if (!tersedia) {
            throw new IllegalStateException(merek + " (" + plat + ") sedang tidak tersedia.");
        }
        tersedia = false;
    }

    public void kembalikan() {
        tersedia = true;
    }

    // ===== Abstract method =====
    // Cara menghitung biaya berbeda-beda untuk tiap jenis kendaraan.
    public abstract double hitungBiayaSewa(int hari);

    // ===== Method overloading =====
    // Nama sama dengan hitungBiayaSewa(int), tetapi parameter berbeda.
    public double hitungBiayaSewa(int hari, double diskonPersen) {
        if (diskonPersen < 0 || diskonPersen > 100) {
            throw new IllegalArgumentException("Diskon harus di antara 0 sampai 100.");
        }
        return hitungBiayaSewa(hari) * (1 - diskonPersen / 100);
    }

    public String getJenis() {
        return "Kendaraan";
    }

    public void tampilkanInfo() {
        System.out.println("Jenis        : " + getJenis());
        System.out.println("Merek        : " + merek);
        System.out.println("Plat         : " + plat);
        System.out.println("Tarif / hari : " + formatRupiah(tarifPerHari));
        System.out.println("Status       : " + (tersedia ? "Tersedia" : "Sedang disewa"));
    }

    public static String formatRupiah(double nilai) {
        return String.format(Locale.forLanguageTag("id-ID"), "Rp %,.0f", nilai);
    }
}