package src.Core;

// user who controls party
public class User {

    private String name;
    private Party party;
    private boolean inMarket;
    private boolean inBattle;

    public User(String name) {
        this.name = name;
        this.party = new Party();
        this.inMarket = false;
        this.inBattle = false;
    }

    public boolean isInMarket() {
        return inMarket;
    }
    public void setInMarket(boolean inMarket) {
        this.inMarket = inMarket;
    }
    public boolean isInBattle() {
        return inBattle;
    }
    public void setInBattle(boolean inBattle) {
        this.inBattle = inBattle;
    }   

    public String getName() {
        return name;
    }
    public Party getParty() {
        return party;
    }


}
