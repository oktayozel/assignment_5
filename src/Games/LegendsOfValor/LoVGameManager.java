package src.Games.LegendsOfValor;

import java.util.List;
import src.Core.GameManager;
import src.Utils.IO.Input;
import src.Utils.IO.Output;
import src.Utils.Interface.Multiplayer;
import src.Core.User;

public class LoVGameManager extends GameManager implements Multiplayer {
    
    public LoVGameManager(src.Utils.Statistics.Statistics statistics) {
        super(statistics);
    }

    @Override
    public void setupGame() {
        Output.gameInitializationMessage("lov");
        Output.someSpace();

        List<String> nameList = Input.getMultiplayerUsernames();

        this.user1 = new User(nameList.get(0));
        this.user2 = new User(nameList.get(1));
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
