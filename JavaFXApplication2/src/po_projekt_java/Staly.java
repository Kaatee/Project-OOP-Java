/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package po_projekt_java;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * klasa klienta stalego
 *
 * @author Kasi
 */
public class Staly extends Klient {

    private int Punkty_lojalnosciowe;
    private int Stala_znizka_na_zamowienie;
    private ImageView rys;

    /**
     * konstruktor klienta stalego przypisujaca mu poczatkowe wartosci punktow
     * lojalnosciowych i znizki
     *
     * @param g
     */
    public Staly(glowny g) {
        super(g);
        Punkty_lojalnosciowe = 0;
        Stala_znizka_na_zamowienie = 0;
    }

    /**
     * Funkcja liczaca znizke na podstawie punktow lojalnosciowych
     *
     * @return
     */
    public int oblicz_znizke() {
        return this.getPunkty_lojalnosciowe() % 10;
    }

    /**
     * Funkcja rysujaca klienta stalego na mapie
     * @param mapa 
     */
    @Override
    public void rysuj(AnchorPane mapa) {

        this.rys = new ImageView();
        getRys().setImage(new Image("file:src/photo/staly.png"));
        getRys().setLayoutX(super.getAdres_dostawy().getX());
        getRys().setLayoutY(super.getAdres_dostawy().getY());
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
     * @return the Punkty_lojalnosciowe
     */
    public int getPunkty_lojalnosciowe() {
        return Punkty_lojalnosciowe;
    }

    /**
     * @param Punkty_lojalnosciowe the Punkty_lojalnosciowe to set
     */
    public void setPunkty_lojalnosciowe(int Punkty_lojalnosciowe) {
        this.Punkty_lojalnosciowe = Punkty_lojalnosciowe;
    }

    /**
     * @return the Stala_znizka_na_zamowienie
     */
    public int getStala_znizka_na_zamowienie() {
        return Stala_znizka_na_zamowienie;
    }

    /**
     * @param Stala_znizka_na_zamowienie the Stala_znizka_na_zamowienie to set
     */
    public void setStala_znizka_na_zamowienie(int Stala_znizka_na_zamowienie) {
        this.Stala_znizka_na_zamowienie = Stala_znizka_na_zamowienie;
    }
}
