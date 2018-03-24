/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package po_projekt_java;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

/**
 * Klasa odpowiadajaca za to, co sie wydarzy po naduszeniu klienta (wyswietlane
 * sa informacje o nim)
 *
 * @author Kasi
 */
public class Kontroler_klienta implements EventHandler<MouseEvent> {

    private TextArea okienko;
    public glowny glowny1;

    public Kontroler_klienta(TextArea okienko1, glowny g) {
        this.okienko = okienko1;
        this.glowny1 = g;
    }

    @Override
    public void handle(MouseEvent event) {
        okienko.clear();
        if (((Node) event.getSource()).getUserData() instanceof Klient) {
            Klient klient = (Klient) ((Node) event.getSource()).getUserData();
            this.okienko.appendText("KLIENT\n");
            this.okienko.appendText("Imie: " + klient.getImie() + "\n");
            this.okienko.appendText("Nazwisko: " + klient.getNazwisko() + "\n");
            this.okienko.appendText("Kod: " + klient.getKod() +"\n");
            this.okienko.appendText("Adres: " + klient.getNazwa_ulicy() + "\n");
            this.okienko.appendText("Email: " + klient.getAdres_email() + "\n");
            this.okienko.appendText("Nr tel: " + klient.getNr_tel() + "\n");    
       
            glowny1.setOstatnio_klikniety_klient(klient);
        
        } else {
            System.out.println("Nie znaleziono klienta");
        }
        
    }

}
