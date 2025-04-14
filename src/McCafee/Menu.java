package McCafee;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Menu {
    Scanner sc = new Scanner(System.in);
    private final List<Artikel> menu;
    private final List<Artikel> bestellung;
    private final List<Artikel> bestellungL;
    private final BonusSystem bonusSystem;
    private double gesamtbetrag = 0;
    private double geldGegeben;
    private double rückgeld;


    public Menu(int bonusFelder) {
        this.menu = loadMenu();
        this.bestellung = new ArrayList<>();
        this.bestellungL = new ArrayList<>();
        bonusSystem = new BonusSystem(bonusFelder);
    }

    public List<Artikel> getBestellung() {
        return bestellung;
    }

    // Statische Kaffee-Liste
    private List<Artikel> loadMenu() {
        return Arrays.asList(
                new Artikel("Espresso", "klein", 1.69),
                new Artikel("Espresso", "groß", 2.19),
                new Artikel("Espresso Macchiato", "klein", 1.79),
                new Artikel("Cafe", "klein", 1.79),
                new Artikel("Cafe", "mittel", 2.19),
                new Artikel("Cafe", "groß", 2.49),
                new Artikel("Flat White Espresso", "klein", 1.89),
                new Artikel("Flat White Espresso", "mittel", 2.29),
                new Artikel("Flat White Espresso", "groß", 2.59),
                new Artikel("Cappuccino", "klein", 1.99),
                new Artikel("Cappuccino", "mittel", 2.39),
                new Artikel("Cappuccino", "groß", 2.69),
                new Artikel("Caffe Latte", "klein", 1.99),
                new Artikel("Caffe Latte", "mittel", 2.39),
                new Artikel("Caffe Latte", "groß", 2.69),
                new Artikel("Latte Macchiato", "klein", 2.39)
        );
    }

    // Bestellvorgang
    public void addKaffee(int index) {
        if (index >= 0 && index < menu.size()) {
            Artikel kaffee = menu.get(index);
            if (kaffee.getSize().equals("groß")) { // Korrektur: richtige Eigenschaft geprüft
                bestellungL.add(kaffee);
                bonusSystem.setBonusKarteFelder(); // Counter erhöhen
                System.out.println(kaffee + " hinzugefügt.");
                System.out.println(bonusSystem.getBonusKarteFelder());
                bonusSystem.isGratisKaffe();
            } else {
                bestellung.add(kaffee);
                System.out.println(kaffee + " hinzugefügt.");
            }
        } else {
            System.out.println("Ungültige Auswahl.");
        }
    }

    // Bestellung abschließen
    public void rechnung() {
        gesamtbetrag = 0; // Zurücksetzen des Gesamtbetrags

        // Summe berechnen
        for (Artikel kaffee : bestellung) {
            gesamtbetrag += kaffee.getPrice();
        }
        for (Artikel kaffee : bestellungL) {
            gesamtbetrag += kaffee.getPrice();
        }

        System.out.println("Bestellete Artikeln ");
        for (int i = 0; i < bestellung.size(); i++) {
            System.out.println(bestellung.get(i).getName() + " " + bestellung.get(i).getSize() + "\t" + bestellung.get(i).getPrice());
        }
        for (int i = 0; i < bestellungL.size(); i++) {
            System.out.println(bestellungL.get(i).getName() + " " + bestellungL.get(i).getSize() + "\t" + bestellungL.get(i).getPrice());
        }

        // Prüfen, ob Gratis-Kaffee gewährt wird
        // Billigstes großes Getränk entfernen
        if (bonusSystem.getGratisKaffe() >= 1) {
            for (int i = 0; i < bonusSystem.getGratisKaffe(); i++) {
                Artikel gratis = bestellungL.remove(0);
                System.out.println("Gratis erhalten: " + gratis.getName() + " " + gratis.getSize());
                System.out.println("----------------");
                gesamtbetrag -= gratis.getPrice();
            }
        } else {
            System.out.println("----------------");
        }

        // Gesammtbetrag Runden
        BigDecimal gesamtbetragGerundet = BigDecimal.valueOf(gesamtbetrag).setScale(2, RoundingMode.HALF_UP);

        //Gesammtbetrag ausgeben
        System.out.println("Zu zahlen " + gesamtbetragGerundet + " €");
        System.out.println("----------------");
        System.out.println("");

        System.out.println("Einkassierte Geldsumme");
        geldGegeben = sc.nextDouble();

        rückgeld = gesamtbetrag - geldGegeben;

        System.out.println("Rückgeld: " + rückgeld);
        System.out.println("----------------");

        Kassenzettel();
    }

    public void Kassenzettel(){
        BigDecimal gesamtbetragGerundet = BigDecimal.valueOf(gesamtbetrag).setScale(2, RoundingMode.HALF_UP);
        BigDecimal rückgeldGerundet = BigDecimal.valueOf(rückgeld).setScale(2, RoundingMode.HALF_UP);
        for (int i = 0; i < 100; i++) {
            System.out.println(" ");
        }

        System.out.println("Bestellete Artikeln ");
        System.out.println(" ");
        for (int i = 0; i < bestellung.size(); i++) {
            System.out.println(bestellung.get(i).getName() + " " + bestellung.get(i).getSize() + "\t" + bestellung.get(i).getPrice());
        }
        for (int i = 0; i < bestellungL.size(); i++) {
            System.out.println(bestellungL.get(i).getName() + " " + bestellungL.get(i).getSize() + "\t" + bestellungL.get(i).getPrice());
        }
        System.out.println("----------------");
        System.out.println("Gesamtbetrag " + gesamtbetragGerundet + " €");
        System.out.println("----------------");
        System.out.println("Bar " + geldGegeben);
        System.out.println("Rückgeld " + rückgeldGerundet);
        System.out.println("----------------");
    }

    public void showMenu() {
        for (int i = 0; i < menu.size(); i++) {
            System.out.println("[NOLOG]"+ i + ": " + menu.get(i));
        }
    }
}
