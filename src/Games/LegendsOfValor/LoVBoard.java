package src.Games.LegendsOfValor;

import java.util.Random;

import src.Core.Board;
import src.Core.Tile;
import src.Core.Tile.Terrain;
import src.Core.Tile.Type;
import src.Core.User;
import src.Utils.IO.Output;
import src.Utils.Default.DefaultReader;

public class LoVBoard extends Board {


    public static final int BOARD_SIZE = 8;
    private final Random rand = new Random();


    public LoVBoard() {
        super(BOARD_SIZE);
        generateRandomLayout();
    }

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

    private Terrain randomTerrain() {
        double p = rand.nextDouble();
        if (p < 0.20)      return Terrain.BUSH;
        else if (p < 0.40) return Terrain.CAVE;
        else if (p < 0.60) return Terrain.KOULOU;
        else if (p < 0.80) return Terrain.OBSTACLE;
        else               return Terrain.PLAIN;
    }

    @Override
    public boolean isBoardPlayable() {
        return true;
    }

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
                String baseSymbol;
                String cellText;

                if (tile.getHeroOccupant() != null) {
                    Terrain t = tile.getTerrain();
                    String terrainChar = terrainSymbol(t);
                    int heroNum = getHeroNumber(tile.getHeroOccupant());
                    String display = terrainChar + "/H" + heroNum;
                    String colored = Output.BRIGHT_CYAN + display + Output.RESET;
                    // Ensure consistent spacing for 5-character display (e.g., "N/H1")
                    cellText = " " + colored + "  ";
                } else if (tile.getMonsterOccupant() != null) {
                    Terrain t = tile.getTerrain();
                    String terrainChar = terrainSymbol(t);
                    int monsterLane = getMonsterLane(c);
                    String display = terrainChar + "/M" + monsterLane;
                    String colored = Output.BRIGHT_RED + display + Output.RESET;
                    // Ensure consistent spacing for 5-character display (e.g., "N/M1")
                    cellText = " " + colored + "  ";
                } else {
                    Terrain t = tile.getTerrain();
                    baseSymbol = terrainSymbol(t);
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
        
        // Check if destination is in the same lane
        if (!isInSameLane(currentCol, newCol)) {
            Output.print("Heroes can only move within their lane!");
            Output.sleep(1000);
            return false;
        }
        
        // Move hero
        grid[currentRow][currentCol].setHeroOccupant(null);
        dest.setHeroOccupant(hero);
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
}
