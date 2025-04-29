package McCafee;

public class Mitarbeiter {
    private String name;
    private int mitarbeiterNr;
    private boolean kassenBerächtigungen;
    private String passwort;

    public Mitarbeiter(String name, String passwort, int mittarbeiterNr){
        this.name = name;
        this.passwort = passwort;
        this.mitarbeiterNr = mittarbeiterNr;
    }

    public String getName() {
        return name;
    }

    public int getMitarbeiterNr() {
        return mitarbeiterNr;
    }

    public String getPasswort() {
        return passwort;
    }

}
