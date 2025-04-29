package McCafee;

import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc;
        int bonusPunkte;
        int tempMNR;
        String tempMP;
        sc = new Scanner(System.in);
        MitarbeiterVerwaltung mv;
        mv = new MitarbeiterVerwaltung();
        boolean eingeloggt = false;


        System.out.println("Bitte Loggen Sie sih ein mit Ihren mitarbeiter Nummer und Passwort");
        System.out.println("Mitarbeiter Nr:");
        tempMNR = sc.nextInt();
        System.out.println("Passwort: ");
        tempMP = sc.next();

        for (int i = 0; i < mv.getMittarbeiternList().size(); i++) {
            if (tempMNR == mv.getMittarbeiternList().get(i).getMitarbeiterNr() &&
                    Objects.equals(tempMP, mv.getMittarbeiternList().get(i).getPasswort())) {
                eingeloggt = true;
                System.out.println("Willkommen Sie dürfen Kassieren");
            } else {
                eingeloggt = false;
                System.out.println("MitarbeiterNr oder Passwort ist falsch");
            }
        }


        while (eingeloggt) {
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
}