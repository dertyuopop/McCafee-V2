package McCafee;

import java.util.ArrayList;
import java.util.List;

public class MitarbeiterVerwaltung {
    private List<Mitarbeiter> mittarbeiternList = new ArrayList<>();
    Mitarbeiter mittarbeiter;

    public MitarbeiterVerwaltung(){
        mitarbeitern();
    }

    private List<Mitarbeiter> mitarbeitern(){
        mittarbeiternList.add(new Mitarbeiter("Olaf","1234",001));

        return mittarbeiternList;
    }

    public List<Mitarbeiter> getMittarbeiternList() {
        return mittarbeiternList;
    }
}
