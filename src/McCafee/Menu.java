package McCafee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Menu {
    private List<Artikel> menu;
    private List<Artikel> bestellung;
    private List<Artikel> bestellungL;
    private BonusSystem bonusSystem;
    private double gesamtbetrag = 0;
    private int grosseGetraenkeCounter = 0; // Zähler für große Getränke

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

        System.out.println(gesamtbetrag + " € " + "Ohne Rabatt");
        System.out.println("Bestellung: " + bestellung);

        grosseGetraenkeCounter = bonusSystem.getBonusKarteFelder() / 5;

        // Prüfen, ob Gratis-Kaffee gewährt wird
        // Billigstes großes Getränk entfernen
        if (bonusSystem.getGratisKaffe() >= 1) {
            for (int i = 0; i < bonusSystem.getGratisKaffe(); i++) {
                Artikel gratis = bestellungL.remove(0);
                System.out.println("Gratis erhalten: " + gratis.getName() + " " + gratis.getSize());
                System.out.println("----------------");
                gesamtbetrag -= gratis.getPrice();
            }
        }

        System.out.println("Gesamtbetrag nach Rabatt: " + gesamtbetrag + " €");
    }

    public void showMenu() {
        for (int i = 0; i < menu.size(); i++) {
            System.out.println(i + ": " + menu.get(i));
        }
    }
}
