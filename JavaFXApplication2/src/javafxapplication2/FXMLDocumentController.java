package javafxapplication2;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import po_projekt_java.Dostawca;
import po_projekt_java.Firmowy;
import po_projekt_java.Klient;
import po_projekt_java.Kontroler_dostawcy;
import po_projekt_java.Kontroler_klienta;
import po_projekt_java.Nazwy_adresow;
import po_projekt_java.Posilek;
import po_projekt_java.Staly;
import po_projekt_java.Zamowienie;
import po_projekt_java.glowny;

/**
 * Klasa obslugujaca guziki
 *
 * @author Kasi
 */
public class FXMLDocumentController implements Initializable {

    private glowny glowny;
    @FXML
    private Label label;
    @FXML
    private Button dodaj_klienta;
    @FXML
    private Button dodaj_posilek;
    @FXML
    private Button dodaj_zamowienie;
    @FXML
    private Button dodaj_dostawce;
    @FXML
    private Button usun_dostawce;
    @FXML
    private Button usun_klienta;
    @FXML
    private Button wroc_do_restauracji;
    @FXML
    private AnchorPane mapa;
    @FXML
    private ImageView rys_restauracji;
    @FXML
    private TextArea okienko;

    /**
     * funkcja odpoiedzialna za to co sie stanie po nacisnieciu na przycisk
     * "dodaj klienta". Losuje ona ktory klient zostanie dodany (zwykly, firmowy
     * czy stalu), tworzy go, tworzy mu watek, dodaje do listy wszystkich
     * klientow a nastepnie wyswietla w polu tekstowym jego dane
     *
     * @param event
     */
    @FXML
    private void handleDodajKlienta(ActionEvent event) {

        Random rand = new Random();
        int ktory = rand.nextInt(3);//0 - zwykly, 1- firmowy, 2-staly

        if (ktory == 0) {
            Klient x = new Klient(glowny);
            x.rysuj(mapa);
            x.getRys().setOnMouseClicked(new Kontroler_klienta(okienko, glowny));
            glowny.getLista_wszystkich_klientow().add(x);
            Thread watek = new Thread(x);
            watek.start();
            x.setWatek(watek);
            okienko.clear();
            okienko.appendText("DODALEM KLIENTA ZWYKLEGO");
            okienko.appendText("\nImie: " + x.getImie() + "\n");
            okienko.appendText("Nazwisko: " + x.getNazwisko() + "\n");
            okienko.appendText("Adres: " + x.getNazwa_ulicy() + "\n");
            okienko.appendText("Email: " + x.getAdres_email() + "\n");
            okienko.appendText("Nr tel: " + x.getNr_tel() + "\n");
        }

        if (ktory == 1) {
            Klient x1 = new Firmowy(glowny);
            x1.rysuj(mapa);
            x1.getRys().setOnMouseClicked(new Kontroler_klienta(okienko, glowny));
            glowny.getLista_wszystkich_klientow().add(x1);
            Thread watek = new Thread(x1);
            watek.start();
            x1.setWatek(watek);
            okienko.clear();
            okienko.appendText("DODALEM KLIENTA FIRMOWEGO");
            okienko.appendText("\nImie: " + x1.getImie() + "\n");
            okienko.appendText("Nazwisko: " + x1.getNazwisko() + "\n");
            okienko.appendText("Adres: " + x1.getNazwa_ulicy() + "\n");
            okienko.appendText("Email: " + x1.getAdres_email() + "\n");
            okienko.appendText("Nr tel: " + x1.getNr_tel() + "\n");

        }

        if (ktory == 2) {
            Klient x2 = new Staly(glowny);
            x2.rysuj(mapa);
            x2.getRys().setOnMouseClicked(new Kontroler_klienta(okienko, glowny));
            glowny.getLista_wszystkich_klientow().add(x2);
            Thread watek = new Thread(x2);
            watek.start();
            x2.setWatek(watek);
            okienko.clear();
            okienko.appendText("DODALEM KLIENTA STALEGO\n");
            okienko.appendText("Imie: " + x2.getImie() + "\n");
            okienko.appendText("Nazwisko: " + x2.getNazwisko() + "\n");
            okienko.appendText("Adres: " + x2.getNazwa_ulicy() + "\n");
            okienko.appendText("Email: " + x2.getAdres_email() + "\n");
            okienko.appendText("Nr tel: " + x2.getNr_tel() + "\n");
        }
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    /**
     * Funkcja odpowiedzialna za obsluge guzika "dodaj dostawce". Po jego
     * wcisnieciu tworzy nowego dostawce, dodaje go do listy wszystkich
     * dostawcow, tworzy jego watek i wyswietla informacje o jego dodaniu w
     * oknie tekstowym.
     *
     * @param event
     */
    @FXML
    private void handleDodajDostawca(ActionEvent event) {

        Dostawca z = new Dostawca(glowny);
        glowny.getLista_wszystkich_dostawcow().add(z);
        okienko.clear();
        okienko.appendText("DODALEM DOSTAWCE");
        //System.out.println("JESTEM W DODAJ DOSTAWCE");
        z.rysuj_dostawce(mapa);
        z.getRysunek_dostawcy().setOnMouseClicked(new Kontroler_dostawcy(okienko, glowny));
        Thread watek = new Thread(z);
        watek.start();
        z.setWatek(watek);
    }

    /**
     * Funkcja odpowiedajaca za obsluge guzika "Usun dostawce". Usuwa ona
     * ostatnio kliknietego dostawce, usuwa jego obrazek z mapy, usuwa go z
     * listy wszystkich dostawcow oraz przerywa jego watek.
     *
     * @param event
     */
    @FXML
    private void handleUsunDostawce(ActionEvent event) {

        Dostawca x = glowny.getOstatnio_klikniety_dostawca();

        x.getRysunek_dostawcy().setOnMouseClicked(null);
        mapa.getChildren().remove(x.getRysunek_dostawcy());
        x.getWatek().interrupt();
        glowny.getLista_wszystkich_dostawcow().remove(x);
        okienko.clear();
        okienko.appendText("ZABILES DOSTAWCE\n");
        
        if(glowny.getLista_wszystkich_dostawcow().size() == 0)
            okienko.appendText("MUSISZ ZATRUDNIC NOWYCH \nDOSTAWCOW");

    }

    /**
     * Funkcja odpowiedajaca za obsluge guzika "Usun klienta". Usuwa ona
     * ostatnio kliknietego klienta, usuwa jego obrazek z mapy i usuwa go z
     * listy wszystkich klientow.
     *
     * @param event
     */
    @FXML
    private void handleUsunKlienta(ActionEvent event) {

        Klient x = glowny.getOstatnio_klikniety_klient();
        
        //szukanie zamowienie nalezacego do tego klienta
        for(int i=0; i<glowny.getLista_wszystkich_zamowien().size(); i++){
            if(glowny.getLista_wszystkich_zamowien().get(i).getKlient() == x){
                glowny.getLista_wszystkich_zamowien().remove(glowny.getLista_wszystkich_zamowien().get(i));
            }
        }
        
        x.getRys().setOnMouseClicked(null);

        mapa.getChildren().remove(x.getRys());
        System.out.println("PRZED");
        x.getWatek().interrupt();
        System.out.println("PO");
        glowny.getLista_wszystkich_klientow().remove(x);
        okienko.clear();
        okienko.appendText("USUNIETO KLIENTA");

    }

    /**
     * Funkcja odpowiedzialna za obsluge przycisku "Wroc do restauracji".
     * Funkcja zmienia pole dostawcy wskazujace na to czy chce wrocic na true.
     * Funkcja wyswietla odpowiedni komunikat na polu tekstowym.
     *
     * @param event
     */
    @FXML
    private void handleWrocDoRestauracji(ActionEvent event) {

        Dostawca x = glowny.getOstatnio_klikniety_dostawca();
        x.setChce_wracac(true);
        okienko.clear();
        okienko.appendText("WRACAM DO RESTAURACJI");

    }

    /**
     * Funkcja obslugujaca przycisk "dodaj posilek". Dodaje obiekt klasy Posilek
     * oraz wyswietla w polu tekstowym podstawowe informacje o nim. Dodaje tez
     * posilek do listy wszystkich posilkow.
     *
     * @param event
     */
    @FXML
    private void handleDodajPosilek(ActionEvent event) {
        Posilek p = new Posilek();
        glowny.getLista_wszystkich_posilkow().add(p);
        okienko.clear();
        okienko.appendText("DODALEM POSILEK: \n");
        okienko.appendText("Nazwa: " + p.getNazwa() + "\n");
        okienko.appendText("Cena: " + p.getCena() + "zl\n");

    }

    /**
     * Funkcja obslugujaca przycisk "Dodaj Zamowienie", tworzy nowe zamowienie,
     * dodaje je do listy wszystkich zamowien i wyswietla komunikat w polu
     * tekstowym
     *
     * @param event
     */
    @FXML
    private void handleDodajZamowienie(ActionEvent event) {
        Zamowienie zam = new Zamowienie(glowny);
        glowny.getLista_wszystkich_zamowien().add(zam);
        okienko.clear();
        okienko.appendText("DODALEM ZAMOWIENIE");

    }

    /**
     * Funkcja inicjalizujaca
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mapa.setStyle("-fx-background-color: #FFFFFF");
        glowny = new glowny();
    }

}
