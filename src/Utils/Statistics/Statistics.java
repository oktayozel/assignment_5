package src.Utils.Statistics;

// tracks game stats
public class Statistics {
    private int totalGamesPlayed;
    private int totalBattlesWon;
    private int totalHeroesLevelledUp;
    private int totalMonstersDefeated;
    
    public Statistics() {
        totalGamesPlayed = 0;
        totalBattlesWon = 0;
        totalHeroesLevelledUp = 0;
        totalMonstersDefeated = 0;
    }
    
    // Getters
    public int getTotalGamesPlayed() {
        return totalGamesPlayed;
    }
    
    public int getTotalBattlesWon() {
        return totalBattlesWon;
    }
    
    public int getTotalHeroesLevelledUp() {
        return totalHeroesLevelledUp;
    }
    
    public int getTotalMonstersDefeated() {
        return totalMonstersDefeated;
    }

    public void setTotalGamesPlayed(int totalGamesPlayed) {
        this.totalGamesPlayed = totalGamesPlayed;
    }
    
    public void setTotalBattlesWon(int totalBattlesWon) {
        this.totalBattlesWon = totalBattlesWon;
    }
    
    public void setTotalHeroesLevelledUp(int totalHeroesLevelledUp) {
        this.totalHeroesLevelledUp = totalHeroesLevelledUp;
    }
    
    public void setTotalMonstersDefeated(int totalMonstersDefeated) {
        this.totalMonstersDefeated = totalMonstersDefeated;
    }
    
    // Increment methods for convenience
    public void incrementGamesPlayed() {
        totalGamesPlayed++;
    }
    
    public void incrementBattlesWon() {
        totalBattlesWon++;
    }
    
    public void incrementHeroesLevelledUp() {
        totalHeroesLevelledUp++;
    }
    
    public void incrementMonstersDefeated(int count) {
        totalMonstersDefeated += count;
    }

    

}
