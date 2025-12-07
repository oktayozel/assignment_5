package src.Games.LegendsOfValor;

import src.Core.Board;
import src.Core.User;

// Legends of Valor board implementation (placeholder)
public class LoVBoard extends Board {

    public LoVBoard(int size) {
        super(size);
    }

    @Override
    protected void generateRandomLayout() {
        // TODO: Implement Legends of Valor board generation
    }

    @Override
    public boolean isBoardPlayable() {
        // TODO: Implement Legends of Valor board playability check
        return true;
    }

    @Override
    public void printBoard(int partyRow, int partyCol) {
        // TODO: Implement Legends of Valor board printing
    }

    @Override
    public void tryEnterMarket(int row, int col, User user) {
        // TODO: Implement Legends of Valor market entry
    }
}
