/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package po_projekt_java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * klasa klienta firmowego
 * @author Kasi
 */
public class Firmowy extends Klient{
    protected String Adres_korespondencyjny;
    protected int Nr_konta_bankowego;
    protected int REGON;
    
    private ImageView rys;//
    
    
    /**
     * konstruktor klienta firmowego,
     * losuje mu adres korespondencyjny (nazwe ulicy), numer konta bankowego i regon.
     * @param g 
     */
    public Firmowy(glowny g){
        super(g);
       
        Random random1 = new Random();
        ArrayList<Nazwy_adresow> adresy = new ArrayList();
        adresy.addAll(Arrays.asList(Nazwy_adresow.values()));
        
        int nr_adresu = random1.nextInt(adresy.size());
        
        Adres_korespondencyjny=adresy.get(nr_adresu).toString();
        
        Nr_konta_bankowego=random1.nextInt()+10000000;
        REGON=random1.nextInt(999999999)+10000000;        
    }
   /**
    * Funkcja pokazujaca na mapie obrazek klienta firmowego. Rysuje go w miejscu jego adresu
    * @param mapa 
    */
    @Override
     public void rysuj(AnchorPane mapa) {
         
        this.rys = new ImageView();
        getRys().setImage(new Image("file:src/photo/firmowy.png"));
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
    
    
}
