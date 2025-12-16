package src.Games.MonstersAndHeroes;

import src.Core.User;
import src.Market.Market;
import src.Market.MarketItemTemplates;
import src.Utils.IO.Input;
import src.Utils.IO.Output;



// monsters and heroes market implementation
public class MaHMarket extends Market {
    
    public MaHMarket() {
        super();
        for (int i = 0; i < 10; i++) {
            addItem(MarketItemTemplates.generateRandomInventory(random, 1).get(0));
        }
    }

    
    // start market interaction leaves back to the 
    @Override
    public void start(User user) {
        if (user == null) {
            return;
        }
        
        user.setInMarket(true);

        boolean running = true;

        while (running) {
            Output.displayMarket(this);
            Output.printMarketMenu();
            running = Input.getMarketInput(this, user);
        }
        user.setInMarket(false);
    }
}
