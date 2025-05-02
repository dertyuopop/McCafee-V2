package McCafee;

import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String Logo =
                 ".___  ___.   ______          ______     ___       _______  _______   \n"
                +"|   \\/   |  /      |        /      |   /   \\     |   ____||   ____|  \n"
                +"|  \\  /  | |  ,----' ______|  ,----'  /  ^  \\    |  |__   |  |__     \n"
                +"|  |\\/|  | |  |     |______|  |      /  /_\\  \\   |   __|  |   __|    \n"
                +"|  |  |  | |  `----.       |  `----./  _____  \\  |  |     |  |____  \n"
                +"|__|  |__|  \\______|        \\______/__/     \\__\\ |__|     |_______|  \n";

        //Starting
        Log.info("Start McCafee");

        Scanner sc;
        int bonusPunkte;
        int tempMNR;
        String tempMP;
        sc = new Scanner(System.in);
        MitarbeiterVerwaltung mv;
        mv = new MitarbeiterVerwaltung();
        boolean eingeloggt = false;


        System.out.println(Logo);

        System.out.println("Bitte Loggen Sie sich mit Mitarbeiternummer und Passwort ein:");
        System.out.print("Mitarbeiter Nr: ");
        tempMNR = sc.nextInt();
        Log.info("Mitarbeiter Nr: " + tempMNR + "Login Attempt");
        System.out.print("Passwort: ");
        tempMP = sc.next();

        for (int i = 0; i < mv.getMittarbeiternList().size(); i++) {
            if (tempMNR == mv.getMittarbeiternList().get(i).getMitarbeiterNr() &&
                    Objects.equals(tempMP, mv.getMittarbeiternList().get(i).getPasswort())) {
                eingeloggt = true;
                System.out.println("Willkommen Sie dürfen Kassieren");
                System.out.println("================================");
                Log.info("Mitarbeiter Nr: " + tempMNR + "Login erfolgreich");
                for (int j = 0; j < 3; j++) {
                    System.out.println(" ");
                }
            } else {
                eingeloggt = false;
                System.out.println("MitarbeiterNr oder Passwort ist falsch");
                Log.warning("Mitarbeiter Nr: " + tempMNR + "Login fehlgeschlagen");

            }
        }


        while (eingeloggt) {
            System.out.print("Bitte geben Sie ihre Bonuspunkte ein: ");
            bonusPunkte = sc.nextInt();
            Menu bestellung = new Menu(bonusPunkte);
            Log.info("Bonuspunkte: " + bonusPunkte);

            System.out.println("Willkommen im McCafe! Wählen Sie Ihr Getränk aus dem Menü:");
            bestellung.showMenu();

            while (true) {
                System.out.print("Geben Sie die Nummer der Kaffeespezialität ein (oder -1 zum Beenden): ");
                int wahl = sc.nextInt();
                if (wahl == -1) break;
                bestellung.addKaffee(wahl);
            }
            Log.info("Kunde bestellt: " + bestellung.getBestellung());
            bestellung.rechnung();

            System.out.println("Weitere Bestelleung aufnehmen Y/N");
            if (!(Objects.equals(sc.next(), "Y"))){
                eingeloggt = false;
                } else {
                for (int i = 0; i < 100; i++) {
                    System.out.println(" ");
                }
            }
        }
    }
}