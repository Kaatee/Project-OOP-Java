/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package po_projekt_java;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * klasa zestawu obiadowego
 *
 * @author Kasi
 *
 */
public class Zestaw_obiadowy {

    private ArrayList<Posilek> sklad_zestawu_obiadowego = new ArrayList();
    private double cena;

    /**
     * Konstruktor zestawu obiadowego, dodaje do losowa ilosc posilkow z ktorych
     * ma sie skladac zestaw i na podstawie listy tych posilkow oblicza cene
     * zestawu
     */
    public Zestaw_obiadowy() {
        cena = 0;
        Random rand = new Random();
        int ile_posilkow = rand.nextInt(10) + 1;
        for (int i = 0; i < ile_posilkow; i++) {
            sklad_zestawu_obiadowego.add(new Posilek());
            glowny.getLista_wszystkich_posilkow().add(sklad_zestawu_obiadowego.get(i));
            cena += sklad_zestawu_obiadowego.get(i).getCena();
        }
        cena = 0.9 * cena; //znizka w ramach zestawu obiadowego
    }

    /**
     * zwraca cene zestawu obiadowego
     *
     * @return the cena
     */
    public double getCena() {
        return cena;
    }

}
