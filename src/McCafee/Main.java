package McCafee;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc;
        int bonusPunkte;
        sc = new Scanner(System.in);

        System.out.println("Bitte geben Sie ihre Bonuspunkte ein");
        bonusPunkte = sc.nextInt();
        Menu bestellung = new Menu(bonusPunkte);

        System.out.println("Willkommen im McCafe! Wählen Sie Ihr Getränk aus dem Menü:");
        bestellung.showMenu();

        while (true) {
            System.out.print("Geben Sie die Nummer der Kaffeespezialität ein (oder -1 zum Beenden): ");
            int wahl = sc.nextInt();
            if (wahl == -1) break;
            bestellung.addKaffee(wahl);
        }
        bestellung.rechnung();
    }
}