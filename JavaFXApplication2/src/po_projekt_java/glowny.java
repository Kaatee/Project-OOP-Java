/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package po_projekt_java;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.Node;

/**
 * klasa przechowujaca liste wszystkich aktualnych list zamowien, klientow,
 * pojazdow i dostawcow. W programie stworzone jest jedno wystapienie obiektu
 * tej klasy Klasa zawiera tez pole pamietajace ostatnio kliknietego dostawce
 * oraz ostatnio kliknietego klienta
 *
 * @author Kasi
 */
public class glowny {

    private static List<Zamowienie> lista_wszystkich_zamowien;
    private static List<Klient> lista_wszystkich_klientow;
    private static List<Pojazd> lista_wszystkich_pojazdow;
    private static List<Dostawca> lista_wszystkich_dostawcow;
    private static List<Posilek> lista_wszystkich_posilkow;

   

    private Dostawca ostatnio_klikniety_dostawca;
    private Klient ostatnio_klikniety_klient;

    /**
     * Funkcja tworzaca liste wszystkich zamowien
     */
    public static void StworzListeWszystkichZamowien() {
        setLista_wszystkich_zamowien(new ArrayList<>());
    }
    
    /**
     * Funkcja tworzaca liste wszystkich posilkow
     */
    public static void StworzListeWszystkichPosilkow() {
        setLista_wszystkich_posilkow(new ArrayList<>());
    }
 

    /**
     * Funkcja tworzaca liste wszystkich klientow
     */
    public static void StworzListeWszystkichKlientow() {
        setLista_wszystkich_klientow(new ArrayList<>());
    }

    /**
     * Funkcja tworzaca liste wszystkich pojazdow
     */
    public static void StworzListeWszystkichPojazdow() {
        setLista_wszystkich_pojazdow(new ArrayList<>());
    }

    
    /**
     * Funkcja tworzaca liste wszystkich dostawcow
     */
    public static void StworzListeWszystkichDostawcow() {
        setLista_wszystkich_dostawcow(new ArrayList<>());
    }

   
    /**
     * @return the lista_wszystkich_zamowien
     */
    public static synchronized List<Zamowienie> getLista_wszystkich_zamowien() {
        return lista_wszystkich_zamowien;
    }
    
     /**
     * @return the lista_wszystkich_posilkow
     */
    public static List<Posilek> getLista_wszystkich_posilkow() {
        return lista_wszystkich_posilkow;
    }

    /**
     * @param aLista_wszystkich_zamowien the lista_wszystkich_zamowien to set
     */
    public static synchronized void setLista_wszystkich_zamowien(List<Zamowienie> aLista_wszystkich_zamowien) { //
        lista_wszystkich_zamowien = aLista_wszystkich_zamowien;
    }

    /**
     * @return the lista_wszystkich_klientow
     */
    public static synchronized List<Klient> getLista_wszystkich_klientow() { //
        return lista_wszystkich_klientow;
    }
    

    /**
     * @param aLista_wszystkich_klientow the lista_wszystkich_klientow to set
     */
    public static void setLista_wszystkich_klientow(List<Klient> aLista_wszystkich_klientow) {
        lista_wszystkich_klientow = aLista_wszystkich_klientow;
    }
    
       /**
     * @param aLista_wszystkich_posilkow the lista_wszystkich_posilkow to set
     */
    public static void setLista_wszystkich_posilkow(List<Posilek> aLista_wszystkich_posilkow) {
        lista_wszystkich_posilkow = aLista_wszystkich_posilkow;
    }

    /**
     * @return the lista_wszystkich_pojazdow
     */
    public static List<Pojazd> getLista_wszystkich_pojazdow() {
        return lista_wszystkich_pojazdow;
    }

    /**
     * @param aLista_wszystkich_pojazdow the lista_wszystkich_pojazdow to set
     */
    public static void setLista_wszystkich_pojazdow(List<Pojazd> aLista_wszystkich_pojazdow) {
        lista_wszystkich_pojazdow = aLista_wszystkich_pojazdow;
    }

    /**
     * @return the lista_wszystkich_dostawcow
     */
    public static List<Dostawca> getLista_wszystkich_dostawcow() {
        return lista_wszystkich_dostawcow;
    }
    
   

    /**
     * @param aLista_wszystkich_dostawcow the lista_wszystkich_dostawcow to set
     */
    public static void setLista_wszystkich_dostawcow(List<Dostawca> aLista_wszystkich_dostawcow) {
        lista_wszystkich_dostawcow = aLista_wszystkich_dostawcow;
    }

    /**
     * @return the ostatnio_klikniety_dostawca
     */
    public Dostawca getOstatnio_klikniety_dostawca() {
        return ostatnio_klikniety_dostawca;
    }

    /**
     * @param ostatnio_klikniety_dostawca the ostatnio_klikniety_dostawca to set
     */
    public void setOstatnio_klikniety_dostawca(Dostawca ostatnio_klikniety_dostawca) {
        this.ostatnio_klikniety_dostawca = ostatnio_klikniety_dostawca;
    }

    /**
     * @return the ostatnio_klikniety_klient
     */
    public Klient getOstatnio_klikniety_klient() {
        return ostatnio_klikniety_klient;
    }

    /**
     * @param ostatnio_klikniety_klient the ostatnio_klikniety_klient to set
     */
    public void setOstatnio_klikniety_klient(Klient ostatnio_klikniety_klient) {
        this.ostatnio_klikniety_klient = ostatnio_klikniety_klient;
    }

  

    

}
