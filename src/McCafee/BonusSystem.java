package McCafee;

public class BonusSystem {
    private int bonusKarteFelder;
    private int gratisKaffe;

    public BonusSystem(int bonusKarteFelder) {
        this.bonusKarteFelder = bonusKarteFelder;
        this.gratisKaffe = 0;
    }

    public int isGratisKaffe() {
        if (bonusKarteFelder == 5 || bonusKarteFelder == 10 || bonusKarteFelder == 14) {
            this.gratisKaffe++;
        }
        return gratisKaffe;
    }


    public int getGratisKaffe() {
        return gratisKaffe;
    }

    public void setBonusKarteFelder() {
        this.bonusKarteFelder = this.bonusKarteFelder + 1;
        if (this.bonusKarteFelder == 15){
            this.bonusKarteFelder = 0;
        }
    }

    public int getBonusKarteFelder() {
        return bonusKarteFelder;
    }
}
