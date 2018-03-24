/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package po_projekt_java;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

/**
 * Klasa odpowiadajaca za to, co sie wydarzy po naduszeniu dostawcy (wyswietlane
 * sa informacje o nim)
 *
 * @author Kasi
 */
public class Kontroler_dostawcy implements EventHandler<MouseEvent> {

    private TextArea okienko;
    private glowny glowny1;

    public Kontroler_dostawcy(TextArea okienko1, glowny g) {
        this.okienko = okienko1;
        this.glowny1 = g;
    }

    @Override
    public void handle(MouseEvent event) {
        okienko.clear();
        if (((Node) event.getSource()).getUserData() instanceof Dostawca) {
            Dostawca dostawca = (Dostawca) ((Node) event.getSource()).getUserData();

            this.okienko.appendText("DOSTAWCA\n");
            this.okienko.appendText("Imie: " + dostawca.getImie() + "\n");
            this.okienko.appendText("Nazwisko: " + dostawca.getNazwisko() + "\n");
            this.okienko.appendText("PESEL: " + dostawca.getPESEL() + "\n");
            double x = dostawca.getPojazd_ktorym_jedzie().getIlosc_paliwa_w_baku();
            this.okienko.appendText("Ilosc paliwa w baku: " + x);

            glowny1.setOstatnio_klikniety_dostawca(dostawca);

        } else {
            System.out.println("Nie znaleziono dostawcy");
        }

    }

}
