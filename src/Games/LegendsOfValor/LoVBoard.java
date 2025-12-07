package src.Games.LegendsOfValor;

import java.util.Random;

import src.Core.Board;
import src.Core.Tile;
import src.Core.Tile.Terrain;
import src.Core.Tile.Type;
import src.Core.User;
import src.IO.Output;
import src.Default.DefaultReader;

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
    public void printBoard(int partyRow, int partyCol) {
        Output.clearScreen();
        Output.boardBanner();

        final String H_BORDER = "=======";

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                System.out.print("+" + H_BORDER);
            }
            System.out.println("+");

            for (int c = 0; c < size; c++) {
                Tile tile = grid[r][c];
                boolean hasParty = (r == partyRow && c == partyCol);

                String baseSymbol;
                String cellText;

                if (hasParty) {
                    baseSymbol = "P";
                    String colored = Output.BRIGHT_GREEN + baseSymbol + Output.RESET;
                    cellText = "   " + colored + "   ";   // width 7
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
}
