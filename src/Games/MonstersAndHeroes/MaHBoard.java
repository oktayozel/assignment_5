package src.Games.MonstersAndHeroes;

import java.util.Random;

import src.Core.Board;
import src.Core.Tile;
import src.Core.User;
import src.IO.Output;
import src.Default.DefaultReader;

// Monsters and Heroes game board implementation
public class MaHBoard extends Board {

    private final Random random = new Random();

    public MaHBoard(int size) {
        super(size);
        generateRandomLayout();
    }

    // check tile has neighbor to move to
    private boolean hasAccessibleNeighbor(int row, int col) {
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            if (isInside(newRow, newCol) && grid[newRow][newCol].isAccessible()) {
                return true;
            }
        }
        return false;
    }

    // generate random tiles
    @Override
    protected void generateRandomLayout() {
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                double p = random.nextDouble();
                Tile.Type type;
                
                if (p < (double) DefaultReader.getDefaultSettings("inaccessible_ratio")/100) {
                    type = Tile.Type.INACCESSIBLE;
                } else if (p < (double) (DefaultReader.getDefaultSettings("market_ratio") + DefaultReader.getDefaultSettings("inaccessible_ratio"))/100) {
                    type = Tile.Type.MARKET;
                } else {
                    type = Tile.Type.COMMON;
                }
                grid[r][c] = new Tile(r, c, type);
            }
        }
    }

    // check if board playable
    @Override
    public boolean isBoardPlayable() {
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (grid[r][c].isAccessible()) {
                    if (hasAccessibleNeighbor(r, c)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // print board with party position
    @Override
    public void printBoard(int partyRow, int partyCol) {
        Output.clearScreen();
        Output.boardBanner();
        
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                System.out.print("+---");
            }
            System.out.println("+");

            for (int c = 0; c < size; c++) {
                Tile tile = grid[r][c];
                boolean hasParty = (r == partyRow && c == partyCol);
                char symbol = tile.getSymbol(hasParty);
                System.out.print("| " + symbol + " ");
            }
            System.out.println("|");
        }
        
        for (int c = 0; c < size; c++) {
            System.out.print("+---");
        }
        System.out.println("+");
    }

    // enter market if on market tile
    @Override
    public void tryEnterMarket(int row, int col, User user) {
        Tile tile = getTile(row, col);
        if (tile != null && tile.isMarket()) {
            user.setInMarket(true);
            tile.getMarket().start(user);
        } 
        else {
            Output.print("You are not standing on a market tile.");
            Output.sleep(2000);
        }
    }
}
