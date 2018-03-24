/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package po_projekt_java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Klasa pojazdu
 *
 * @author Kasi
 */
public class Pojazd {

    private int Ladownosc; //ile posilkow sie zmiesci
    private int Predkosc;
    private String Nr_rejestracyjny;
    private int Pojemnosc_baku;
    private double Ilosc_paliwa_w_baku;
    private double Spalanie;
    private String potrzebna_kategoria; //(czym jest - skuterem czy autem)

    /**
     * Konstrutor pojazdu losujacy jego ladownosc, numer rejestracyjny (sklada
     * sie z nazwy pojazdu i losowego intigera),kategorie potrzebna do jego
     * prowadzenia (czyli wlasciwie to czym jest pojazd: skuterem czy autem). Na
     * podstawie tego czy jest to skuter czy auto, nadawana jest pojemnosc baku,
     * ilosc paliwa, spalanie oraz predkosc.
     */
    public Pojazd() {
        Random random3 = new Random();

        Ladownosc = random3.nextInt(10)+3; //ile moze zabrac zamowien

        Nr_rejestracyjny = String.valueOf(getPotrzebna_kategoria() + "_" + random3.nextInt(999));

        ArrayList<kategorie_prawka> kat = new ArrayList();
        kat.addAll(Arrays.asList(kategorie_prawka.values()));
        int nr_kat = random3.nextInt(kat.size() - 1);
        potrzebna_kategoria = kat.get(nr_kat).toString();

        if ("skuter".equals(potrzebna_kategoria)) {
            Pojemnosc_baku = 10;
            Ilosc_paliwa_w_baku = 10.0;
            Spalanie = 0.005;
            Predkosc = 50; //skuter ma wieksza predkosc od samochodu, poniewaz predkosc odpowiada za sleep w dostarczaniu
        }

        if ("auto".equals(potrzebna_kategoria)) {
            Pojemnosc_baku = 60;
            Ilosc_paliwa_w_baku = 60.0;
            Spalanie = 0.05;
            Predkosc = 25;
        }
    }
/**
 * funkcja zmieniajaca aktualna ilosc paliwa w baku na maksymalna
 */
    public void zatankuj() {
        this.setIlosc_paliwa_w_baku(getPojemnosc_baku());
    }

    /**
     * @return the Ladownosc
     */
    public int getLadownosc() {
        return Ladownosc;
    }

    /**
     * @param Ladownosc the Ladownosc to set
     */
    public void setLadownosc(int Ladownosc) {
        this.Ladownosc = Ladownosc;
    }

    /**
     * @return the Predkosc
     */
    public int getPredkosc() {
        return Predkosc;
    }

    /**
     * @param Predkosc the Predkosc to set
     */
    public void setPredkosc(int Predkosc) {
        this.Predkosc = Predkosc;
    }

    /**
     * @return the Pojemnosc_baku
     */
    public int getPojemnosc_baku() {
        return Pojemnosc_baku;
    }

    /**
     * @param Pojemnosc_baku the Pojemnosc_baku to set
     */
    public void setPojemnosc_baku(int Pojemnosc_baku) {
        this.Pojemnosc_baku = Pojemnosc_baku;
    }

    /**
     * @return the Ilosc_paliwa_w_baku
     */
    public double getIlosc_paliwa_w_baku() {
        return Ilosc_paliwa_w_baku;
    }

    /**
     * @param Ilosc_paliwa_w_baku the Ilosc_paliwa_w_baku to set
     */
    public void setIlosc_paliwa_w_baku(double Ilosc_paliwa_w_baku) {
        this.Ilosc_paliwa_w_baku = Ilosc_paliwa_w_baku;
    }

    /**
     * @return the potrzebna_kategoria
     */
    public String getPotrzebna_kategoria() {
        return potrzebna_kategoria;
    }

    /**
     * @return the Spalanie
     */
    public double getSpalanie() {
        return Spalanie;
    }

}
