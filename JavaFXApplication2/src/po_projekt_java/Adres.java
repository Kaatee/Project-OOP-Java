package po_projekt_java;

import java.util.Random;

/**
 * klasa trzymajaca adres (wspolrzedna X i Y)
 * @author Kasi
 */
public class Adres {

    private int x;
    private int y;

    /**
     * konstruktor adresu, losuje pola X i Y tak, aby nie pokrywaly sie z polozeniem restauracji
     */
    public Adres() {
        Random random2 = new Random();

        int gdzie = random2.nextInt(4);

        //losowanie zeby nie losowaly sie na restauracji
        if (gdzie == 0) { //nad restauracja
            x = random2.nextInt(370);
            y = random2.nextInt(175); 
        }
        if (gdzie == 1) { //pod restauracja
            x = random2.nextInt(370);
            y = random2.nextInt(175)+225;
        }
        if (gdzie == 2) { //po lewej
            x = random2.nextInt(160);
            y = random2.nextInt(370);
        } else { //po prawej
            x = random2.nextInt(170)+230;
            y = random2.nextInt(370);
        }
        
    }

    /**
     * metoda liczy odleglosc miedzy danym adresem a restauracja 
     * @return 
     */
     public double odcinek() {
        double x1 = this.getX();
        double x2 = 200; // x restauracji
        double y1 = this.getY();
        double y2 = 200; //y restauracji

        double d = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
        
        return d;
        
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }
}
