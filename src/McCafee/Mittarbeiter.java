package McCafee;

public class Mittarbeiter {
    private String name;
    private int mittarbeiterNr;
    private boolean kassenBerächtigungen;
    private String passwort;

    public Mittarbeiter(String name, String passwort, int mittarbeiterNr){
        this.name = name;
        this.passwort = passwort;
        this.mittarbeiterNr = mittarbeiterNr;
    }

    public String getName() {
        return name;
    }

    public int getMittarbeiterNr() {
        return mittarbeiterNr;
    }

    public String getPasswort() {
        return passwort;
    }
}
