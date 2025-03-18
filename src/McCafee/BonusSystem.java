package McCafee;

public class BonusSystem {
    private int bonusKarteFelder;
    private boolean gratisKaffe;

    public BonusSystem(int bonusKarteFelder) {
        this.bonusKarteFelder = bonusKarteFelder;
    }

    public boolean isGratisKaffe() {
        if (bonusKarteFelder == 5 || bonusKarteFelder == 10 || bonusKarteFelder == 14) {
            this.gratisKaffe = true;
        }
        return gratisKaffe;
    }

    public void setBonusKarteFelder() {
        this.bonusKarteFelder = this.bonusKarteFelder + 1;
    }

    public int getBonusKarteFelder() {
        return bonusKarteFelder;
    }
}
