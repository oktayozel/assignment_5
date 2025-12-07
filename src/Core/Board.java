package src.Core;

// abstract game board
public abstract class Board {

    protected int size;
    protected Tile[][] grid;

    public Board(int size) {
        this.size = size;
        this.grid = new Tile[size][size];
    }

    // abstract method to generate board layout
    protected abstract void generateRandomLayout();

    // check if position inside board
    public boolean isInside(int row, int col) {
        return row >= 0 && row < size && col >= 0 && col < size;
    }

    // check if board playable
    public abstract boolean isBoardPlayable();

    // print board with party position
    public abstract void printBoard(int partyRow, int partyCol);

    // enter market if on market tile
    public abstract void tryEnterMarket(int row, int col, User user);
    
    public int getSize() {
        return size;
    }

    public Tile getTile(int row, int col) {
        if (!isInside(row, col)) return null;
        return grid[row][col];
    }
}
