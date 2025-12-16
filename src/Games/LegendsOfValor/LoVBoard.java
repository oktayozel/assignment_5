package src.Games.LegendsOfValor;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import src.Core.Board;
import src.Core.Tile;
import src.Core.Tile.Terrain;
import src.Core.Tile.Type;
import src.Core.User;
import src.Utils.IO.Output;
import src.Utils.Default.DefaultReader;


// class representing the game board for Legends of Valor
public class LoVBoard extends Board {

    // default board size for LoV
    public static final int BOARD_SIZE = 8;
    private final Random rand = new Random();

    // Map to track monster numbers for display
    private Map<src.Monster.Monster, Integer> monsterNumbers;

    // constructor
    public LoVBoard() {
        super(BOARD_SIZE);
        this.monsterNumbers = new HashMap<>();
        generateRandomLayout();
    }

    // setter and getter for monster numbers
    public void setMonsterNumber(src.Monster.Monster monster, int number) {
        monsterNumbers.put(monster, number);
    }
    
    public int getMonsterNumber(src.Monster.Monster monster) {
        return monsterNumbers.getOrDefault(monster, 1);
    }


    // generate a random board 
    @Override
    protected void generateRandomLayout() {
        grid = new Tile[size][size];

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {

                if (c == 2 || c == 5) {
                    grid[r][c] = new Tile(r, c, Type.INACCESSIBLE, Terrain.WALL);
                    continue;
                }

                if (r == 0) {
                    grid[r][c] = new Tile(r, c, Type.COMMON, Terrain.NEXUS_MONSTER);
                    continue;
                }

                if (r == size - 1) {
                    grid[r][c] = new Tile(r, c, Type.MARKET, Terrain.NEXUS_HERO);
                    continue;
                }

                // Lane tiles
                Terrain terrain = randomTerrain();
                grid[r][c] = new Tile(r, c, Type.COMMON, terrain);
            }
        }
    }

    // randomly select a terrain type based on defined probabilities
    private Terrain randomTerrain() {
        double p = rand.nextDouble();
        if (p < 0.20)      return Terrain.BUSH;
        else if (p < 0.40) return Terrain.CAVE;
        else if (p < 0.60) return Terrain.KOULOU;
        else if (p < 0.80) return Terrain.OBSTACLE;
        else               return Terrain.PLAIN;
    }

    // check if the board is playable (always true for LoV)
    @Override
    public boolean isBoardPlayable() {
        return true;
    }

    // remove obstacle at specified position (asks before doing so))
    public void removeObstacleAt(int row, int col) {
        if (!isInside(row, col)) return;
        Tile tile = grid[row][col];
        if (tile.getTerrain() == Terrain.OBSTACLE) {
            tile.setTerrain(Terrain.PLAIN);
        }
    }

   @Override
    public void printBoard() {
        final String H_BORDER = "=======";
        
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                System.out.print("+" + H_BORDER);
            }
            System.out.println("+");
            
            for (int c = 0; c < size; c++) {
                Tile tile = grid[r][c];
                String cellText;
                
                src.Hero.Hero hero = tile.getHeroOccupant();
                src.Monster.Monster monster = tile.getMonsterOccupant();
                Terrain t = tile.getTerrain();
                
                // Case 1: Both hero and monster on same tile
                if (hero != null && monster != null) {
                    int heroNum = getHeroNumber(hero);
                    int monsterNum = getMonsterNumber(monster);
                    // Color hero part cyan, monster part red
                    String display = Output.BRIGHT_CYAN + "H" + heroNum + Output.RESET + 
                                    "/" + 
                                    Output.BRIGHT_RED + "M" + monsterNum + Output.RESET;
                    cellText = " " + display + " ";
                    
                // Case 2: Only hero (no terrain symbol)
                } else if (hero != null) {
                    int heroNum = getHeroNumber(hero);
                    String display = "H" + heroNum;
                    String colored = Output.BRIGHT_CYAN + display + Output.RESET;
                    cellText = "  " + colored + "   ";
                    
                // Case 3: Only monster (no terrain symbol)
                } else if (monster != null) {
                    int monsterNum = getMonsterNumber(monster);

                    String display = "M" + monsterNum;
                    String colored = Output.BRIGHT_RED + display + Output.RESET;
                    cellText = "  " + colored + "   ";
                    
                // Case 4: Empty tile - show terrain
                } else {
                    String baseSymbol = terrainSymbol(t);
                    String coloredSymbol = baseSymbol;
                    
                    switch (t) {
                        case NEXUS_HERO:
                            coloredSymbol = Output.BRIGHT_GREEN + baseSymbol + Output.RESET;
                            break;
                        case NEXUS_MONSTER:
                            coloredSymbol = Output.BRIGHT_RED + baseSymbol + Output.RESET;
                            break;
                        case OBSTACLE:
                            coloredSymbol = Output.MAGENTA + baseSymbol + Output.RESET;
                            break;
                        case BUSH:
                            coloredSymbol = Output.GREEN + baseSymbol + Output.RESET;
                            break;
                        case CAVE:
                            coloredSymbol = Output.BLUE + baseSymbol + Output.RESET;
                            break;
                        case KOULOU:
                            coloredSymbol = Output.YELLOW + baseSymbol + Output.RESET;
                            break;
                        case WALL:
                            break;
                        case PLAIN:
                        case NONE:
                        default:
                            break;
                    }
                    
                    cellText = "   " + coloredSymbol + "   ";
                    
                    if (t == Terrain.WALL) {
                        String inner = "   " + baseSymbol + "   ";
                        cellText = Output.BLACK_BG + inner + Output.RESET;
                    }
                }
                
                System.out.print("|" + cellText);
            }
            System.out.println("|");
        }
        
        for (int c = 0; c < size; c++) {
            System.out.print("+" + H_BORDER);
        }
        System.out.println("+");
    }

    private int heroCounter = 0;
    private java.util.Map<src.Hero.Hero, Integer> heroNumberMap = new java.util.HashMap<>();
    
    public void assignHeroNumber(src.Hero.Hero hero) {
        if (!heroNumberMap.containsKey(hero)) {
            heroCounter++;
            heroNumberMap.put(hero, heroCounter);
        }
    }
    
    // get hero number for display
    private int getHeroNumber(src.Hero.Hero hero) {
        return heroNumberMap.getOrDefault(hero, 0);
    }
    
    private int getMonsterLane(int col) {
        if (col <= 1) return 1;       // Left lane
        if (col >= 3 && col <= 4) return 2;  // Mid lane
        if (col >= 6) return 3;       // Right lane
        return 0;
    }

    private String terrainSymbol(Terrain t) {
        switch (t) {
            case NEXUS_HERO:
            case NEXUS_MONSTER: return "N";
            case WALL:          return "I";
            case OBSTACLE:      return "X";
            case BUSH:          return "B";
            case CAVE:          return "C";
            case KOULOU:        return "K";
            case PLAIN:
            case NONE:
            default:            return " ";
        }
    }

    @Override
    public void tryEnterMarket(int row, int col, User user) {
        Tile tile = getTile(row, col);
        if (tile != null && tile.isMarket() && tile.getTerrain() == Terrain.NEXUS_HERO) {
            user.setInMarket(true);
            tile.getMarket().start(user);
        } else {
            Output.print("You must stand on the heroes' Nexus to access the market.");
            Output.sleep(DefaultReader.getDefaultSettings("sleep_ms_after_action"));
        }
    }
    
    // Move hero up (towards monsters' Nexus) with lane restrictions
    public boolean moveHeroUp(src.Hero.Hero hero) {
        int[] pos = findHeroPosition(hero);
        if (pos == null) return false;
        
        int newRow = pos[0] - 1;
        int newCol = pos[1];
        
        return moveHero(hero, pos[0], pos[1], newRow, newCol);
    }
    
    // Move hero down (towards heroes' Nexus) with lane restrictions
    public boolean moveHeroDown(src.Hero.Hero hero) {
        int[] pos = findHeroPosition(hero);
        if (pos == null) return false;
        
        int newRow = pos[0] + 1;
        int newCol = pos[1];
        
        return moveHero(hero, pos[0], pos[1], newRow, newCol);
    }
    
    // Move hero left within same lane
    public boolean moveHeroLeft(src.Hero.Hero hero) {
        int[] pos = findHeroPosition(hero);
        if (pos == null) return false;
        
        int newRow = pos[0];
        int newCol = pos[1] - 1;
        
        return moveHero(hero, pos[0], pos[1], newRow, newCol);
    }
    
    // Move hero right within same lane
    public boolean moveHeroRight(src.Hero.Hero hero) {
        int[] pos = findHeroPosition(hero);
        if (pos == null) return false;
        
        int newRow = pos[0];
        int newCol = pos[1] + 1;
        
        return moveHero(hero, pos[0], pos[1], newRow, newCol);
    }
    
    // Core movement logic with validation
    private boolean moveHero(src.Hero.Hero hero, int currentRow, int currentCol, int newRow, int newCol) {
        if (!isInside(newRow, newCol)) {
            Output.print("You can't move outside the map!");
            Output.sleep(1000);
            return false;
        }
        
        Tile dest = grid[newRow][newCol];
        if (!dest.isAccessible()) {
            Output.print("That tile is inaccessible!");
            Output.sleep(1000);
            return false;
        }

        if (dest.getTerrain() == Terrain.OBSTACLE) {
            Output.print("An obstacle blocks the way!");
            Output.sleep(1000);
            return false;
        }
        
        // Check if destination is in the same lane
        if (!isInSameLane(currentCol, newCol)) {
            Output.print("Heroes can only move within their lane!");
            Output.sleep(1000);
            return false;
        }

        // Check if hero is currently on the same tile as a monster
        Tile currentTile = grid[currentRow][currentCol];
        if (currentTile.getMonsterOccupant() != null && !currentTile.getMonsterOccupant().isDefeated()) {
            Output.print("You must defeat the monster in your tile before moving!");
            Output.sleep(1000);
            return false;
        }
        
        // Determine direction for narrative
        String directionName;
        if (newRow < currentRow) directionName = "north";
        else if (newRow > currentRow) directionName = "south";
        else if (newCol < currentCol) directionName = "west";
        else directionName = "east";
        
        // Move hero
        grid[currentRow][currentCol].setHeroOccupant(null);
        dest.setHeroOccupant(hero);
        
        // Narrative
        Output.narrative(hero.getName() + " moved " + directionName + " from (" + currentRow + "," + currentCol + ") to (" + newRow + "," + newCol + ")");
        
        return true;
    }
    
    // Find hero's current position on the board
    private int[] findHeroPosition(src.Hero.Hero hero) {
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (grid[r][c].getHeroOccupant() == hero) {
                    return new int[]{r, c};
                }
            }
        }
        return null;
    }
    
    // Check if two columns are in the same lane
    private boolean isInSameLane(int col1, int col2) {
        // Left lane: 0-1, Mid lane: 3-4, Right lane: 6-7
        int lane1 = (col1 <= 1) ? 0 : (col1 <= 4) ? 1 : 2;
        int lane2 = (col2 <= 1) ? 0 : (col2 <= 4) ? 1 : 2;
        return lane1 == lane2;
    }

    public int[] getHeroPosition(src.Hero.Hero hero) {
        return findHeroPosition(hero);
    }
}
