package McCafee;

import java.util.ArrayList;
import java.util.List;

public class MittarbeiterVerwaltung {
    private List<Mittarbeiter> mittarbeiternList = new ArrayList<>();
    Mittarbeiter mittarbeiter;

    public MittarbeiterVerwaltung(){
        mittarbeitern();
    }

    private List<Mittarbeiter> mittarbeitern(){
        mittarbeiternList.add(new Mittarbeiter("Olaf","1234",001));

        return mittarbeiternList;
    }

    public List<Mittarbeiter> getMittarbeiternList() {
        return mittarbeiternList;
    }
}
