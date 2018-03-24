/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package po_projekt_java;

import java.sql.Array;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Kasi
 */
public class Czlowiek {

    private String Imie;
    private String Nazwisko;
    private glowny glowny1;

    /**
     * konstruktor czlowieka losujacy z klasy enuma imie i nazwisko
     *
     * @param g
     */
    public Czlowiek(glowny g) {
        this.glowny1 = g;

        int nr_imienia;
        int nr_nazwiska;

        ArrayList<Nazwiska> nazw = new ArrayList();
        nazw.addAll(Arrays.asList(Nazwiska.values()));

        ArrayList<Imiona> im = new ArrayList();
        im.addAll(Arrays.asList(Imiona.values()));

        Random random = new Random();
        nr_imienia = random.nextInt(im.size());
        nr_nazwiska = random.nextInt(nazw.size());

        Imie = im.get(nr_imienia).toString();
        Nazwisko = nazw.get(nr_nazwiska).toString();
    }

    /**
     * @return the Imie
     */
    public String getImie() {
        return Imie;
    }

    /**
     * @return the Nazwisko
     */
    public String getNazwisko() {
        return Nazwisko;
    }

    /**
     * @return the glowny1
     */
    public glowny getGlowny1() {
        return glowny1;
    }

    /**
     * @param glowny1 the glowny1 to set
     */
    public void setGlowny1(glowny glowny1) {
        this.glowny1 = glowny1;
    }

}
