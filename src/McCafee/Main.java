package McCafee;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //Log starten
        Log.info("System", "Programm startet");

        //Erstellen einen neuen Scanner
        Scanner sc;
        sc = new Scanner(System.in);

        //Eingabe der bereits bestehenden Bonuspunkte
        int bonusPunkte;
        System.out.print("Bitte geben Sie ihre Bonuspunkte ein: ");
        bonusPunkte = sc.nextInt(); // Input Bonuspunkte
        Menu bestellung = new Menu(bonusPunkte);
        System.out.print("");

        System.out.println("Willkommen im McCafe! Wählen Sie Ihr Getränk aus dem Menü:");
        bestellung.showMenu();

        //Auswahl der Produkte
        while (true) {
            System.out.print("Geben Sie die Nummer der Kaffeespezialität ein (oder -1 zum Beenden): ");
            int wahl = sc.nextInt();
            if (wahl == -1) break;
            bestellung.addKaffee(wahl);
        }
        bestellung.rechnung();
        Log.info("System", "Programm endet");
    }
}