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
 * klasa zamowienia
 *
 * @author Kasi
 */
public class Zamowienie {

    private ArrayList<Posilek> zamowione_posilki = new ArrayList();
    private ArrayList<Zestaw_obiadowy> zamowione_zestawy_obiadowe = new ArrayList();
    private double cena_zamowienia;
    private double cena_dowozu;
    private Klient klient;

    /**
     * konstruktor zamowienia losujaca mu klienta ze wszystkich
     * dostepnych,tworzy liste posilkow i zestawow obiadowych wchodzacych
     * wchodzacych w sklad zamowienia i na ich podstawie oblicza cene. Oblicza
     * tez cene dowozu w zaleznosci od adresu klienta
     *
     * @param g
     */
    public Zamowienie(glowny g) {
        Random rand = new Random();

        if (glowny.getLista_wszystkich_klientow().isEmpty()) {
            throw new UnsupportedOperationException("Nie ma klientow zeby dodac to zamowienie");
        }

        int ktory_klient_chce_to_zamowienie = rand.nextInt(glowny.getLista_wszystkich_klientow().size());

        klient = glowny.getLista_wszystkich_klientow().get(ktory_klient_chce_to_zamowienie);

        cena_zamowienia = 0;

        //tworzenie listy zamowionych posilkow
        int ile_posilkow = rand.nextInt(10) + 1;
        for (int i = 0; i < ile_posilkow; i++) {
            Posilek p = new Posilek();
            zamowione_posilki.add(p);
            glowny.getLista_wszystkich_posilkow().add(p);
            cena_zamowienia += zamowione_posilki.get(i).getCena();
        }

        //tworzenie listy zamowionych zestawow obiadowych
        int ile_zestawow = rand.nextInt(10) + 1;
        for (int i = 0; i < ile_zestawow; i++) {
            zamowione_zestawy_obiadowe.add(new Zestaw_obiadowy());
            cena_zamowienia += zamowione_zestawy_obiadowe.get(i).getCena();
        }

        if (this.klient instanceof Staly) {
            if (((Staly) klient).getPunkty_lojalnosciowe() > 100) {
                cena_zamowienia -= 100; //znizka 100 zl po przekroczeniu zadanej liczby punktow lojalnosciowych
                ((Staly) klient).setPunkty_lojalnosciowe(10); //po wykorzystaniu znizki zostaje mu 10 punktow lojalnosciowych

            } else {
                cena_zamowienia = cena_zamowienia * 0.9;//- cena_zamowienia *(0.01 *((Staly)klient).getPunkty_lojalnosciowe());
                ((Staly) klient).setPunkty_lojalnosciowe(((Staly) klient).getPunkty_lojalnosciowe() + 2);
            }
        }
       
            
        cena_dowozu = this.oblicz_cene_dowozu();

    }

    /**
     * Funkcja obliczajaca cene dowozu zamowienia na podstawie adresu klienta
     *
     * @return
     */
    public double oblicz_cene_dowozu() {
        if (this.getKlient().getAdres_dostawy().odcinek() < 100 || this.cena_zamowienia > 100) {
            return 0.0;
        } else {
            return this.getKlient().getAdres_dostawy().odcinek() / 100;

        }

    }

    /**
     * @return the Lista_posilkow
     */
    public Posilek[] getLista_posilkow() {
        return Lista_posilkow;
    }

    /**
     * @param Lista_posilkow the Lista_posilkow to set
     */
    public void setLista_posilkow(Posilek[] Lista_posilkow) {
        this.Lista_posilkow = Lista_posilkow;
    }

    /**
     * @return the Lista_zestawow
     */
    public Zestaw_obiadowy[] getLista_zestawow() {
        return Lista_zestawow;
    }

    /**
     * @param Lista_zestawow the Lista_zestawow to set
     */
    public void setLista_zestawow(Zestaw_obiadowy[] Lista_zestawow) {
        this.Lista_zestawow = Lista_zestawow;
    }
    private Posilek[] Lista_posilkow;
    private Zestaw_obiadowy[] Lista_zestawow;

    /**
     * @return the zamowione_posilki
     */
    public ArrayList<Posilek> getZamowione_posilki() {
        return zamowione_posilki;
    }

    /**
     * @return the zamowione_zestawy_obiadowe
     */
    public ArrayList<Zestaw_obiadowy> getZamowione_zestawy_obiadowe() {
        return zamowione_zestawy_obiadowe;
    }

    /**
     * @return the cena_zamowienia
     */
    public double getCena_zamowienia() {
        return cena_zamowienia;
    }

    /**
     * @return the cena_dowozu
     */
    public double getCena_dowozu() {
        return cena_dowozu;
    }

    /**
     * @return the klient
     */
    public Klient getKlient() {
        return klient;
    }

}
