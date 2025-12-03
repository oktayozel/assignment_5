package src.Core;

import java.util.Random;

import src.IO.Output;
import src.Default.DefaultReader;

// game board grid
public class Board {

    
    private final int size;
    private final Tile[][] grid;
    private final Random random = new Random();

    public Board(int size) {
        this.size = size;
        this.grid = new Tile[size][size];
        generateRandomLayout();
    }

    // check if position inside board
    public boolean isInside(int row, int col) {
        return row >= 0 && row < size && col >= 0 && col < size;
    }


    // check if board playable
    public boolean isBoardPlayable(){
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
    private void generateRandomLayout() {
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

    // print board with party position
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
    
    public int getSize() {
        return size;
    }
    public Tile getTile(int row, int col) {
        if (!isInside(row, col)) return null;
        return grid[row][col];
    }


}
