/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package po_projekt_java;

import static java.lang.Math.random;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * klasa klienta zwyklego
 *
 * @author Kasi
 */
public class Klient extends Czlowiek implements Runnable {

    private int Kod;
    private int Nr_tel;
    private Adres Adres_dostawy;
    private String Nazwa_ulicy;
    private int Godzina_zamowienie;
    private String Adres_email;
    private ImageView rys;
    private glowny glowny1;
    private Thread watek;

    /**
     * Konstruktor klienta przypisujaca mu kod a takze losujaca numer telefonu,
     * adres dostawy, nazwe ulicy, godzine zamowienia oraz tworzaca login maila
     * z jego imienia i nazwiska
     *
     * @param g
     */
    public Klient(glowny g) {
        super(g);

        Random random1 = new Random();

        ArrayList<Nazwy_adresow> adresy = new ArrayList();
        adresy.addAll(Arrays.asList(Nazwy_adresow.values()));

        int nr_adresu = random1.nextInt(adresy.size());

        Kod = glowny.getLista_wszystkich_klientow().size()+1;
        Nr_tel = random1.nextInt(499999999) + 500000000;
        Adres_dostawy = new Adres();
        Nazwa_ulicy = adresy.get(nr_adresu).toString();
        Godzina_zamowienie = random1.nextInt(24);
        Adres_email = String.valueOf(getImie() + "_" + getNazwisko() + "@gmail.com");
    }

    /**
     * @return the Kod
     */
    public int getKod() {
        return Kod;
    }

    /**
     * @param Kod the Kod to set
     */
    public void setKod(int Kod) {
        this.Kod = Kod;
    }

    /**
     * @return the Nr_tel
     */
    public int getNr_tel() {
        return Nr_tel;
    }

    /**
     * @param Nr_tel the Nr_tel to set
     */
    public void setNr_tel(int Nr_tel) {
        this.Nr_tel = Nr_tel;
    }

    /**
     * @return the Adres_dostawy
     */
    public Adres getAdres_dostawy() {
        return Adres_dostawy;
    }

    /**
     * @param Adres_dostawy the Adres_dostawy to set
     */
    public void setAdres_dostawy(Adres Adres_dostawy) {
        this.Adres_dostawy = Adres_dostawy;
    }

    /**
     * @return the Nazwa_ulicy
     */
    public String getNazwa_ulicy() {
        return Nazwa_ulicy;
    }

    /**
     * @param Nazwa_ulicy the Nazwa_ulicy to set
     */
    public void setNazwa_ulicy(String Nazwa_ulicy) {
        this.Nazwa_ulicy = Nazwa_ulicy;
    }

    /**
     * @return the Godzina_zamowienie
     */
    public int getGodzina_zamowienie() {
        return Godzina_zamowienie;
    }

    /**
     * @param Godzina_zamowienie the Godzina_zamowienie to set
     */
    public void setGodzina_zamowienie(int Godzina_zamowienie) {
        this.Godzina_zamowienie = Godzina_zamowienie;
    }

    /**
     * @return the Adres_email
     */
    public String getAdres_email() {
        return Adres_email;
    }

    /**
     * @param Adres_email the Adres_email to set
     */
    public void setAdres_email(String Adres_email) {
        this.Adres_email = Adres_email;
    }

    /**
     * Funkcja rysujaca klienta zwyklego na mapie zgodnie z jego adresem
     *
     * @param mapa
     */
    public void rysuj(AnchorPane mapa) {
        this.setRys(new ImageView());
        rys.setImage(new Image("file:src/photo/klient.png"));
        getRys().setLayoutX(Adres_dostawy.getX());
        getRys().setLayoutY(Adres_dostawy.getY());
        getRys().setUserData(this);
        mapa.getChildren().add(getRys());
    }

    /**
     * @return the rys
     */
    public ImageView getRys() {
        return rys;
    }

    /**
     * @param rys the rys to set
     */
    public void setRys(ImageView rys) {
        this.rys = rys;
    }

    /**
     * funkcja odpowiedzialna za watek klienta. Losuje odstep czasu z jakim
     * automatycznie pojawiaja sie zamowienia i dodaje je.
     */
    @Override
    public void run() {
        Random rand = new Random();
        int losowy_odstep_czasu;
        while (!Thread.currentThread().isInterrupted()) {
            losowy_odstep_czasu = rand.nextInt(7000) + 4000;
            Zamowienie z = new Zamowienie(glowny1);
            System.out.println("SAM DODALEM ZAMOWIENIE :D");
            glowny.getLista_wszystkich_zamowien().add(z);
            try {
                sleep(losowy_odstep_czasu);
            } catch (InterruptedException ex) {
                Logger.getLogger(Klient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the watek
     */
    public Thread getWatek() {
        return watek;
    }

    /**
     * @param watek the watek to set
     */
    public void setWatek(Thread watek) {
        this.watek = watek;
    }

}
