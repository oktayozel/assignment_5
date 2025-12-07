package src.Games.LegendsOfValor;

import src.Core.GameManager;
import src.Utils.Interface.Multiplayer;
import src.Core.User;

public class LoVGameManager extends GameManager implements Multiplayer {
    
    public LoVGameManager(src.Utils.Statistics.Statistics statistics) {
        super(statistics);
    }

    @Override
    public void setupGame() {
        // TODO: Implement Legends of Valor setup
    }

    @Override
    public void start() {
        // TODO: Implement Legends of Valor game loop
    }

    @Override
    public void handleTileEvent() {
        // TODO: Implement Legends of Valor tile events
    }

    @Override
    public User switchUser() {
        return null;
    }
}
