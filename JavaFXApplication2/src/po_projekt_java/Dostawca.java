/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package po_projekt_java;

import static java.lang.Math.abs;
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
 *
 * @author Kasi
 */
public class Dostawca extends Czlowiek implements Runnable {

    private int PESEL;
    private String[] Dni_pracy = new String[5];
    private int Godz_rozpoczecia_pracy;
    private int Godz_zakonczenia_pracy;
    private String Lista_kategorii_prawa_jazdy;
    private Pojazd pojazd_ktorym_jedzie;
    private ImageView rysunek_dostawcy;
    private Thread watek;
    private ArrayList<Zamowienie> lista_rozwazonych_zamowien = new ArrayList();
    private boolean chce_wracac;

    /**
     * Konstruktor dostawcy, z klasy enum losowane są dni tygodnia. Kazdy
     * dostawca pracuje 5 dni. Losowany jest tez nr PESEL, godziny pracy oraz
     * lista kategorii prawa jazdy. Dostawca ma tez pole chce_wracac (boolean),
     * ktore wskazuje na to czy uzydkownik kliknal przycisk "wroc do
     * restauracji"
     *
     * @param g1
     */
    public Dostawca(glowny g1) {
        super(g1);

        Random random3 = new Random();

        ArrayList<dni_tygodnia> dni = new ArrayList();
        dni.addAll(Arrays.asList(dni_tygodnia.values()));

        ArrayList<kategorie_prawka> kat = new ArrayList();
        kat.addAll(Arrays.asList(kategorie_prawka.values()));

        //losowanie dni pracy
        for (int i = 0; i < 5; i++) {
            int nr_dnia = random3.nextInt(dni.size());
            Dni_pracy[i] = dni.get(nr_dnia).toString();
            dni.remove(nr_dnia);
        }

        int nr_kat = random3.nextInt(kat.size());
        // int nr_poj = random3.nextInt(kat.size() - 1);

        PESEL = random3.nextInt(9999998) + 1;

        Godz_rozpoczecia_pracy = random3.nextInt(24);
        Godz_zakonczenia_pracy = random3.nextInt(24);

        Lista_kategorii_prawa_jazdy = kat.get(nr_kat).toString();
        pojazd_ktorym_jedzie = new Pojazd();
        while (!Lista_kategorii_prawa_jazdy.equals(pojazd_ktorym_jedzie.getPotrzebna_kategoria()) && !Lista_kategorii_prawa_jazdy.equals("skuter_i_auto")) {
            pojazd_ktorym_jedzie = new Pojazd();
        }
        chce_wracac = false;
        glowny.getLista_wszystkich_pojazdow().add(pojazd_ktorym_jedzie);

    }

    /**
     * Funkcja zwracajaca true jesli dostawca posiada uprawniwnia do kierowania
     * pojazdem o zmiennej "czym"
     *
     * @param czym
     * @return
     */
    public boolean czy_moze_jechac(Pojazd czym) {
        if ("skuter_i_auto".equals(getLista_kategorii_prawa_jazdy())) {

            return true;
        }
        if (getLista_kategorii_prawa_jazdy().equals(czym.getPotrzebna_kategoria())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * metoda sprawdzajaca czy dostawca w danym czasie pracuje (nie moze
     * rozwozic zamowienia kiedy nie ma go w pracy)
     *
     * @param dane_zamowienia
     * @return
     */
    public boolean czy_moze_pracowac(Klient dane_zamowienia) { //true jesli moze
        if ((dane_zamowienia.getGodzina_zamowienie() >= getGodz_rozpoczecia_pracy()) && (dane_zamowienia.getGodzina_zamowienie() < getGodz_zakonczenia_pracy())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * metoda odpowiedzialna za watek dostawcy, w petli while cyklicznie
     * wywoluje funkcje rozwiaz zamowienie
     *
     * @return the y_polozenia
     */
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {

            rozwiez_zamowienie();
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    /**
     * @return the PESEL
     */
    public int getPESEL() {
        return PESEL;
    }

    /**
     * @return the Dni_pracy
     */
    public String[] getDni_pracy() {
        return Dni_pracy;
    }

    /**
     * @return the Godz_rozpoczecia_pracy
     */
    public int getGodz_rozpoczecia_pracy() {
        return Godz_rozpoczecia_pracy;
    }

    /**
     * @return the Godz_zakonczenia_pracy
     */
    public int getGodz_zakonczenia_pracy() {
        return Godz_zakonczenia_pracy;
    }

    /**
     * @return the Lista_kategorii_prawa_jazdy
     */
    public String getLista_kategorii_prawa_jazdy() {
        return Lista_kategorii_prawa_jazdy;
    }

    /**
     * @return the pojazd_ktorym_jedzie
     */
    public Pojazd getPojazd_ktorym_jedzie() {
        return pojazd_ktorym_jedzie;
    }

    /**
     * metoda rysujaca dostawce na mapie. Dostawca ma rysunek skutera albo auta
     * w zaleznosci od tego czym jedzie. Dostawca jest rysowany w miejscu
     * restauracji
     */
    public void rysuj_dostawce(AnchorPane mapa) {
        this.setRysunek_dostawcy(new ImageView());
        if (this.getPojazd_ktorym_jedzie().getPotrzebna_kategoria().equals("auto")) {
            rysunek_dostawcy.setImage(new Image("file:src/photo/auto.png"));
        } else {
            rysunek_dostawcy.setImage(new Image("file:src/photo/skuter.png"));
        }

        getRysunek_dostawcy().setLayoutX(200);
        getRysunek_dostawcy().setLayoutY(200);
        getRysunek_dostawcy().setUserData(this);
        mapa.getChildren().add(getRysunek_dostawcy());
    }

    /**
     * funkcja przemieszczajaca dostawce na wspolrzedne restauracji
     */
    public void wroc_do_restauracji(Pojazd pojazd) {

        while (this.getRysunek_dostawcy().getLayoutX() < 200) {
            this.getRysunek_dostawcy().setLayoutX(this.getRysunek_dostawcy().getLayoutX() + 1);
            this.getPojazd_ktorym_jedzie().setIlosc_paliwa_w_baku(this.getPojazd_ktorym_jedzie().getIlosc_paliwa_w_baku() - this.getPojazd_ktorym_jedzie().getSpalanie()); //zakladam ze auto i skuter maja takie samo spalanie :P
            try {
                sleep(this.getPojazd_ktorym_jedzie().getPredkosc()); //predkosc
            } catch (InterruptedException ex) {
                Logger.getLogger(Dostawca.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        while (this.getRysunek_dostawcy().getLayoutX() > 200) {
            this.getRysunek_dostawcy().setLayoutX(this.getRysunek_dostawcy().getLayoutX() - 1);
            this.getPojazd_ktorym_jedzie().setIlosc_paliwa_w_baku(this.getPojazd_ktorym_jedzie().getIlosc_paliwa_w_baku() - this.getPojazd_ktorym_jedzie().getSpalanie()); //zakladam ze auto i skuter maja takie samo spalanie :P
            try {
                sleep(this.getPojazd_ktorym_jedzie().getPredkosc()); //predkosc
            } catch (InterruptedException ex) {
                Logger.getLogger(Dostawca.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        while (this.getRysunek_dostawcy().getLayoutY() < 200) {
            this.getRysunek_dostawcy().setLayoutY(this.getRysunek_dostawcy().getLayoutY() + 1);
            this.getPojazd_ktorym_jedzie().setIlosc_paliwa_w_baku(this.getPojazd_ktorym_jedzie().getIlosc_paliwa_w_baku() - this.getPojazd_ktorym_jedzie().getSpalanie());
            try {
                sleep(this.getPojazd_ktorym_jedzie().getPredkosc());
            } catch (InterruptedException ex) {
                Logger.getLogger(Dostawca.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        while (this.getRysunek_dostawcy().getLayoutY() > 200) {
            this.getRysunek_dostawcy().setLayoutY(this.getRysunek_dostawcy().getLayoutY() - 1);
            this.getPojazd_ktorym_jedzie().setIlosc_paliwa_w_baku(this.getPojazd_ktorym_jedzie().getIlosc_paliwa_w_baku() - this.getPojazd_ktorym_jedzie().getSpalanie());
            try {
                sleep(this.getPojazd_ktorym_jedzie().getPredkosc());
            } catch (InterruptedException ex) {
                Logger.getLogger(Dostawca.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.getPojazd_ktorym_jedzie().zatankuj();
    }

    /**
     * funkcja kopiuje wszystkie zamowienia z ogolnej listy na liste
     * dostawcy. Dostawca rozwozi zamowienia dopoki nie rozwiezie wszystkich, nie
     * bedzie mial wystarczajaco paliwa na zrealizowanie zamowienia lub
     * uzydkownik wymusi na nim powrot do restauracji przyciskiem (zmienna
     * chce_wracac bedzie miala wartosc true)
     */
    public void rozwiez_zamowienie() {

        while (glowny.getLista_wszystkich_zamowien().size() > 0 && this.lista_rozwazonych_zamowien.size() <= this.getPojazd_ktorym_jedzie().getLadownosc()) {
            this.lista_rozwazonych_zamowien.add(glowny.getLista_wszystkich_zamowien().get(0));
            glowny.getLista_wszystkich_zamowien().remove(glowny.getLista_wszystkich_zamowien().get(0));
        }

        while (!this.lista_rozwazonych_zamowien.isEmpty()) {
            if (this.isChce_wracac() == true) {
                break;
            }

            Zamowienie aktualnie_rozwozone = this.lista_rozwazonych_zamowien.get(0);

            int docelowe_x = aktualnie_rozwozone.getKlient().getAdres_dostawy().getX();
            int docelowe_y = aktualnie_rozwozone.getKlient().getAdres_dostawy().getY();

            while ((this.getRysunek_dostawcy().getLayoutX() < docelowe_x) && (this.getPojazd_ktorym_jedzie().getIlosc_paliwa_w_baku()) > (abs(docelowe_x - this.getRysunek_dostawcy().getLayoutX()) + abs(docelowe_y - this.getRysunek_dostawcy().getLayoutY()) + abs(docelowe_y - 200) + abs(docelowe_x - 200)) * this.getPojazd_ktorym_jedzie().getSpalanie() && this.isChce_wracac() == false) {// && this.getPojazd_ktorym_jedzie().getIlosc_paliwa_w_baku()>0.05*//) {

                this.getRysunek_dostawcy().setLayoutX(this.getRysunek_dostawcy().getLayoutX() + 1);
                this.getPojazd_ktorym_jedzie().setIlosc_paliwa_w_baku(this.getPojazd_ktorym_jedzie().getIlosc_paliwa_w_baku() - this.getPojazd_ktorym_jedzie().getSpalanie()); //zakladam ze auto i skuter maja takie samo spalanie :P
                try {
                    sleep(this.getPojazd_ktorym_jedzie().getPredkosc()); //predkosc
                } catch (InterruptedException ex) {
                    Logger.getLogger(Dostawca.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            while ((this.getRysunek_dostawcy().getLayoutX() > docelowe_x) && (this.getPojazd_ktorym_jedzie().getIlosc_paliwa_w_baku()) > (abs(docelowe_x - this.getRysunek_dostawcy().getLayoutX()) + abs(docelowe_y - this.getRysunek_dostawcy().getLayoutY()) + abs(docelowe_y - 200) + abs(docelowe_x - 200)) * this.getPojazd_ktorym_jedzie().getSpalanie() && this.isChce_wracac() == false) {

                this.getRysunek_dostawcy().setLayoutX(this.getRysunek_dostawcy().getLayoutX() - 1);
                this.getPojazd_ktorym_jedzie().setIlosc_paliwa_w_baku(this.getPojazd_ktorym_jedzie().getIlosc_paliwa_w_baku() - this.getPojazd_ktorym_jedzie().getSpalanie());

                try {
                    sleep(this.getPojazd_ktorym_jedzie().getPredkosc()); //predkosc
                } catch (InterruptedException ex) {
                    Logger.getLogger(Dostawca.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            while ((this.getRysunek_dostawcy().getLayoutY() < docelowe_y) && (this.getPojazd_ktorym_jedzie().getIlosc_paliwa_w_baku()) > (abs(docelowe_x - this.getRysunek_dostawcy().getLayoutX()) + abs(docelowe_y - this.getRysunek_dostawcy().getLayoutY()) + abs(docelowe_y - 200) + abs(docelowe_x - 200)) * this.getPojazd_ktorym_jedzie().getSpalanie() && this.isChce_wracac() == false) {

                this.getRysunek_dostawcy().setLayoutY(this.getRysunek_dostawcy().getLayoutY() + 1);
                this.getPojazd_ktorym_jedzie().setIlosc_paliwa_w_baku(this.getPojazd_ktorym_jedzie().getIlosc_paliwa_w_baku() - this.getPojazd_ktorym_jedzie().getSpalanie());
                try {
                    sleep(this.getPojazd_ktorym_jedzie().getPredkosc());
                } catch (InterruptedException ex) {
                    Logger.getLogger(Dostawca.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            while ((this.getRysunek_dostawcy().getLayoutY() > docelowe_y) && (this.getPojazd_ktorym_jedzie().getIlosc_paliwa_w_baku()) > (abs(docelowe_x - this.getRysunek_dostawcy().getLayoutX()) + abs(docelowe_y - this.getRysunek_dostawcy().getLayoutY()) + abs(docelowe_y - 200) + abs(docelowe_x - 200)) * this.getPojazd_ktorym_jedzie().getSpalanie() && this.isChce_wracac() == false) {

                this.getRysunek_dostawcy().setLayoutY(this.getRysunek_dostawcy().getLayoutY() - 1);
                this.getPojazd_ktorym_jedzie().setIlosc_paliwa_w_baku(this.getPojazd_ktorym_jedzie().getIlosc_paliwa_w_baku() - this.getPojazd_ktorym_jedzie().getSpalanie());
                try {
                    sleep(this.getPojazd_ktorym_jedzie().getPredkosc());
                } catch (InterruptedException ex) {
                    Logger.getLogger(Dostawca.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if ((this.getRysunek_dostawcy().getLayoutY() == docelowe_y) && (this.getRysunek_dostawcy().getLayoutX() == docelowe_x)) {
                this.lista_rozwazonych_zamowien.remove(aktualnie_rozwozone);
            }

        }

        this.wroc_do_restauracji(getPojazd_ktorym_jedzie());
        this.chce_wracac = false;

        //oddawanie nierozwiezionych zamowien do listy wszystkich zamowien
        for (int i = 0; i < this.lista_rozwazonych_zamowien.size(); i++) {
            glowny.getLista_wszystkich_zamowien().add(this.lista_rozwazonych_zamowien.get(i));
        }
        lista_rozwazonych_zamowien.clear();
    }

    /**
     * @return the rysunek_dostawcy
     */
    public ImageView getRysunek_dostawcy() {
        return rysunek_dostawcy;
    }

    /**
     * @param rysunek_dostawcy the rysunek_dostawcy to set
     */
    public void setRysunek_dostawcy(ImageView rysunek_dostawcy) {
        this.rysunek_dostawcy = rysunek_dostawcy;
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

    /**
     * @param pojazd_ktorym_jedzie the pojazd_ktorym_jedzie to set
     */
    public void setPojazd_ktorym_jedzie(Pojazd pojazd_ktorym_jedzie) {
        this.pojazd_ktorym_jedzie = pojazd_ktorym_jedzie;
    }

    /**
     * @return the chce_wracac
     */
    public boolean isChce_wracac() {
        return chce_wracac;
    }

    /**
     * @param chce_wracac the chce_wracac to set
     */
    public void setChce_wracac(boolean chce_wracac) {
        this.chce_wracac = chce_wracac;
    }
}
