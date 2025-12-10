package src.Core;
import src.Utils.IO.Output;

// party position on board
public class Piece {

    private int row;
    private int col;

    public Piece(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    // try move if valid
    private boolean move(int dRow, int dCol, Board board) {
        int newRow = row + dRow;
        int newCol = col + dCol;

        if (!board.isInside(newRow, newCol)) {
            System.out.println("You can't move outside the map!");
            return false;
        }
        
        Tile dest = board.getTile(newRow, newCol);
        if (!dest.isAccessible()) {
            System.out.println("That tile is inaccessible!");
            Output.sleep(1000);
            return false;
        }

        row = newRow;
        col = newCol;
        return true;
    }

    public boolean moveUp(Board board)    { return move(-1, 0, board); }
    public boolean moveDown(Board board)  { return move( 1, 0, board); }
    public boolean moveLeft(Board board)  { return move( 0,-1, board); }
    public boolean moveRight(Board board) { return move( 0, 1, board); }


    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
