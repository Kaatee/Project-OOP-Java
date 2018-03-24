package po_projekt_java;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Kasi
 */
public class Posilek {

    private int nr_posilku;
    private String Nazwa;
    private String[] Lista_skladnikow = new String[5];
    private int Cena;
    private String Kategoria;
    private String Rozmiar_porcji;

    /**
     * konstruktor posiłku losujacy nazwe posilku ze wszystkich dostepnych w
     * enumie nazw, liste skladnikow, cene, kategorie i rozmiar porcji
     */
    public Posilek() {

        Random rand = new Random();

        ArrayList<enum_posilkow> posilki = new ArrayList();
        posilki.addAll(Arrays.asList(enum_posilkow.values()));
        int nr_posilku = rand.nextInt(posilki.size());

        Nazwa = posilki.get(nr_posilku).toString();

        ArrayList<enum_skladnikow> skladniki = new ArrayList();
        skladniki.addAll(Arrays.asList(enum_skladnikow.values()));

        //tworzenie listy skladnikow
        for (int i = 0; i < 5; i++) {
            int nr_dnia = rand.nextInt(skladniki.size());
            Lista_skladnikow[i] = skladniki.get(nr_dnia).toString();
            skladniki.remove(i);
        }

        Cena = rand.nextInt(100) + 5;

        ArrayList<enum_kategorii_posilkow> kat_pos = new ArrayList();
        kat_pos.addAll(Arrays.asList(enum_kategorii_posilkow.values()));
        Kategoria = kat_pos.get(rand.nextInt(kat_pos.size())).toString();

        ArrayList<enum_rozmiarow_porcji> porcje = new ArrayList();
        porcje.addAll(Arrays.asList(enum_rozmiarow_porcji.values()));
        int r_porcji = rand.nextInt(porcje.size());
        Rozmiar_porcji = porcje.get(r_porcji).toString();

    }

   
    /**
     * @return the nr_posilku
     */
    public int getNr_posilku() {
        return nr_posilku;
    }

    /**
     * @return the Nazwa
     */
    public String getNazwa() {
        return Nazwa;
    }

    /**
     * @return the Lista_skladnikow
     */
    public String[] getLista_skladnikow() {
        return Lista_skladnikow;
    }

    /**
     * @return the Cena
     */
    public int getCena() {
        return Cena;
    }

    /**
     * @return the Kategoria
     */
    public String getKategoria() {
        return Kategoria;
    }

    /**
     * @return the Rozmiar_porcji
     */
    public String getRozmiar_porcji() {
        return Rozmiar_porcji;
    }
}
