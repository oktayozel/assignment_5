package src.Battle;


import java.util.List;
import java.util.ArrayList;
import src.IO.Input;
import src.IO.Output;
import src.Monster.MonsterSpawner;
import src.Statistics.Statistics;
import src.Core.GameManager;
import src.Core.Party;
import src.Monster.Monster;
import src.Hero.Hero;
import src.Inventory.InventoryEntry;
import src.Item.Weapon;
import src.Item.Armor;
import src.Item.Spell;
import src.Item.Potion;
import src.Default.DefaultReader;

public class Battle {

    private Party party;
    private List<Monster> monsters;
    private boolean finished;
    private boolean heroesWon;
    private GameManager gm;
    private int round;
    private Statistics statistics;


    // constructor
    public Battle(GameManager gm) {
        this.gm = gm;
        this.statistics = gm.getStatistics();
        this.finished = false;
        this.heroesWon = false;
        this.round = 1;
        this.party = gm.getUser().getParty();
        this.monsters = generateMonstersForBattle(party.size(),party.getHighestLevel());
    }

    // battle  loop
    public void start() {

        while (!finished) {
            Output.displayBattle(this);
            
            System.out.println("\n--- Heroes' Turn ---");
            heroesTurn();
            if (finished || checkBattleEnd()) {
                break;
            }
            
            System.out.println("\n--- Monsters' Turn ---");
            monstersTurn();
            if (finished || checkBattleEnd()) {
                break;
            }
            
            endOfRoundRegeneration();
        }

        gm.getUser().setInBattle(false); // sets the user state to false
        if (heroesWon) {
            Output.narrative("Heroes won the battle!\n", Output.BRIGHT_GREEN);
        } else {
            Output.narrative("All heroes have fallen... Game over.\n", Output.BRIGHT_RED);
        }
        Output.sleep(DefaultReader.getDefaultSettings("sleep_ms_after_action"));
        if (!heroesWon) {
            Input.inputNewGame(statistics);
        }
        System.out.println("===== BATTLE ENDS =====");
    }

    // each alive hero takes action
    private void heroesTurn() {
        List<Hero> alive = new ArrayList<>();
        for (Hero h : party.getHeroes()) {
            if (!h.isFainted()) {
                alive.add(h);
            }
        }
        if (alive.isEmpty()) {
            return;
        }
        
        for (Hero h : alive) {
            char action = Input.getHeroBattleAction(h, this);
            switch (action) {
                case 'A':
                    heroAttack(h);
                    break;
                case 'S':
                    castSpell(h);
                    break;
                case 'P':
                    usePotion(h);
                    break;
                case 'E':
                    changeEquipment(h);
                    break;
                case 'Q':
                    finished = true;
                    return;
                default:
                    break;
            }
            if (checkBattleEnd()) {
                return;
            }
        }
    }


    public void heroAttack(Hero attacker) {
        if (attacker.isFainted()) {
            Output.narrative(attacker.getName() + " cannot act (fainted).\n");
            return;
        }
        List<Monster> aliveMonsters = new ArrayList<>();
        for (Monster m : monsters) {
            if (!m.isDefeated()) {
                aliveMonsters.add(m);
            }
        }
        if (aliveMonsters.isEmpty()) {
            Output.narrative("All monsters are already defeated.\n");
            return;
        }
        System.out.println("Select target monster:");
        for (int i = 0; i < aliveMonsters.size(); i++) {
            Monster m = aliveMonsters.get(i);
            System.out.println("(" + (i+1) + ") " + m.getName() + " HP:" + m.getHP());
        }
        System.out.print("> ");
        int mIdx = Input.readInt(1, aliveMonsters.size()) - 1;
        Monster target = aliveMonsters.get(mIdx);
        if (Math.random() * 100 < target.getDodge()) {
            Output.narrative(target.getName() + " dodged the attack!\n");
            return;
        }
        int rawDamage = attacker.computeAttackDamage();
        int finalDamage = Math.max(0, rawDamage - target.getDefense());
        target.takeDamage(finalDamage);

        Output.narrative(">" + attacker.getName() + " hits " + target.getName() + " for " + finalDamage + " damage.\n");

        if (target.isDefeated()) {
            Output.narrative(target.getName() + " is defeated!\n");
        }
    }

    // uses spell
    public void castSpell(Hero caster) {
        // sanity check
        if (caster.isFainted()) {
             Output.narrative(caster.getName() + " cannot act (fainted).\n"); 
             return;
        }
        List<InventoryEntry> spellEntries = new ArrayList<>();
        for (InventoryEntry entry : caster.getInventory().getEntries()) {
            if (entry.getItem() instanceof Spell) {
                spellEntries.add(entry);
            }
        }
        if (spellEntries.isEmpty()) {
            System.out.println("No spells in inventory."); // TODO fix this if no spell user should be able to continue
            return;
        }
        System.out.println("Select spell:");
        for (int i = 0; i < spellEntries.size(); i++) {
            Spell sp = (Spell) spellEntries.get(i).getItem();
            System.out.println("(" + (i+1) + ") " + sp.getName() + " DMG:" + sp.getDamage() + " Mana:" + sp.getManaCost() + " Type:" + sp.getSpellType());
        }
        int sIdx = Input.readInt(1, spellEntries.size()) - 1;
        Spell spell = (Spell) spellEntries.get(sIdx).getItem();
        InventoryEntry spellEntry = spellEntries.get(sIdx);

        if (!caster.spendMP(spell.getManaCost())) {
            System.out.println("Not enough MP to cast the spell.");
            return;
        }

        List<Monster> aliveMonsters = new ArrayList<>();
        for (Monster m : monsters) {
            if (!m.isDefeated()) {
                aliveMonsters.add(m);
            }
        }
        if (aliveMonsters.isEmpty()) {
            Output.narrative("All monsters are defeated.\n");
            return;
        }
        System.out.println("Select target monster:");
        for (int i = 0; i < aliveMonsters.size(); i++) {
            Monster m = aliveMonsters.get(i);
            System.out.println("(" + (i+1) + ") " + m.getName() + " HP:" + m.getHP());
        }
        int mIdx = Input.readInt(1, aliveMonsters.size()) - 1;
        Monster target = aliveMonsters.get(mIdx);
        if (Math.random() * 100 < target.getDodge()) {
            Output.narrative(target.getName() + " dodged the spell!\n");
            return;
        }

        int rawDamage = spell.getDamage() + (int)Math.round(caster.getDexterity() * 0.05);
        int finalDamage = Math.max(0, rawDamage - target.getDefense());
        target.takeDamage(finalDamage);
        Output.narrative(caster.getName() + " casts " + spell.getName() + " on " + target.getName() + " for " + finalDamage + " damage.\n");


    
        String type = spell.getSpellType().toUpperCase();
        if (type.equals("FIRE")) {
            target.reduceDefenseBy10Percent();
            Output.narrative(target.getName() + "'s defense reduced!\n");
        } else if (type.equals("ICE")) {
            target.reduceBaseDamageBy10Percent();
            Output.narrative(target.getName() + "'s damage reduced!\n");
        } else if (type.equals("LIGHTNING")) {
            target.reduceDodgeBy10Percent();
            Output.narrative(target.getName() + "'s dodge reduced!\n");
        }

        spellEntry.decreaseQuantity(1);
        if (spellEntry.getQuantity() <= 0) {
            caster.getInventory().removeItemByName(spell.getName());
        }
        if (target.isDefeated()) {
            Output.narrative(target.getName() + " is defeated!\n", Output.BRIGHT_GREEN);
        }
    }

    private void usePotion(Hero user) {
        if (user.isFainted()) {
            Output.narrative(user.getName() + " cannot act (fainted).\n", Output.RED);
            return;
        }
        List<InventoryEntry> potionEntries = new ArrayList<>();
        for (InventoryEntry entry : user.getInventory().getEntries()) {
            if (entry.getItem() instanceof Potion) {
                potionEntries.add(entry);
            }
        }
        if (potionEntries.isEmpty()) {
            System.out.println("No potions available.");
            return;
        }
        System.out.println("Select potion:");
        for (int i = 0; i < potionEntries.size(); i++) {
            Potion p = (Potion) potionEntries.get(i).getItem();
            System.out.println("(" + (i+1) + ") " + p.getName() + " Type:" + p.getEffectType() + " Amount:" + p.getEffectAmount());
        }
        int pindex = Input.readInt(1, potionEntries.size()) - 1;
        InventoryEntry pEntry = potionEntries.get(pindex);
        Potion potion = (Potion) pEntry.getItem();
        user.applyPotion(potion);
        pEntry.decreaseQuantity(1);
        if (pEntry.getQuantity() <= 0) {
            user.getInventory().removeItemByName(potion.getName());
        }
        Output.narrative(user.getName() + " uses " + potion.getName() + ".\n", Output.BLUE);
    }

    public void changeEquipment(Hero hero) {
        if (hero.isFainted()) {
            Output.narrative(hero.getName() + " cannot act (fainted).\n");
            return;
        }
        List<InventoryEntry> weaponEntries = new ArrayList<>();
        List<InventoryEntry> armorEntries = new ArrayList<>();
        for (InventoryEntry entry : hero.getInventory().getEntries()) {
            if (entry.getItem() instanceof Weapon) {
                weaponEntries.add(entry);
            } else if (entry.getItem() instanceof Armor) {
                armorEntries.add(entry);
            }
        }
        if (weaponEntries.isEmpty() && armorEntries.isEmpty()) {
            System.out.println("No equipment items to equip.");
            return;
        }
        System.out.println("Equip (1) Weapon or (2) Armor?");
        int choice = Input.readInt(1, 2);
        if (choice == 1) {
            if (weaponEntries.isEmpty()) {
                System.out.println("No weapons available.");
                return;
            }
            System.out.println("Select weapon:");
            for (int i = 0; i < weaponEntries.size(); i++) {
                Weapon w = (Weapon) weaponEntries.get(i).getItem();
                System.out.println("(" + (i+1) + ") " + w.getName() + " DMG:" + w.getDamage() + " Hands:" + w.getHands());
            }
            int wIdx = Input.readInt(1, weaponEntries.size()) - 1;
            Weapon w = (Weapon) weaponEntries.get(wIdx).getItem();
            
            if (w.getHands() == 1) {
                System.out.println("Use (1) One hand or (2) Two hands? (Two hands gives 50% damage boost)");
                int handChoice = Input.readInt(1, 2);
                boolean useTwoHands = (handChoice == 2);
                hero.setEquippedWeapon(w, useTwoHands);
                String handMsg = useTwoHands ? " with both hands (boosted!)" : " with one hand";
                Output.narrative(hero.getName() + " equipped weapon " + w.getName() + handMsg + "\n", Output.CYAN);
            } else {
                hero.setEquippedWeapon(w);
                Output.narrative(hero.getName() + " equipped weapon " + w.getName() + " (requires both hands)\n", Output.CYAN);
            }
        } else {
            if (armorEntries.isEmpty()) {
                System.out.println("No armor available.");
                return;
            }
            System.out.println("Select armor:");
            for (int i = 0; i < armorEntries.size(); i++) {
                Armor a = (Armor) armorEntries.get(i).getItem();
                System.out.println("(" + (i+1) + ") " + a.getName() + " RED:" + a.getDamageReduction());
            }
            int aIdx = Input.readInt(1, armorEntries.size()) - 1;
            Armor a = (Armor) armorEntries.get(aIdx).getItem();
            hero.setEquippedArmor(a);
            Output.narrative(hero.getName() + " equipped armor " + a.getName() + "\n", Output.CYAN);
        }
    }

    public void printInfo() {
        System.out.println("=== Battle Info ===");
        System.out.println("-- Heroes --");
        for (src.Hero.Hero h : gm.getUser().getParty().getHeroes()) {
            Output.displayHeroBattleInfo(h);
        }
        System.out.println("-- Monsters --");
        for (src.Monster.Monster m : monsters) {
            Output.displayMonsterStatistics(m);
        }
    }

    
    private void monstersTurn() {
        List<Hero> aliveHeroes = new ArrayList<>();
        for (Hero h : party.getHeroes()) {
            if (!h.isFainted()) {
                aliveHeroes.add(h);
            }
        }
        if (aliveHeroes.isEmpty()) {
            return;
        }
        for (Monster m : monsters) {
            if (m.isDefeated()) {
                continue;
            }
            // pick random hero
            Hero target = aliveHeroes.get((int)(Math.random() * aliveHeroes.size()));
            if (Math.random() < target.getDodgeChance()) {
                Output.narrative(target.getName() + " dodged " + m.getName() + "'s attack!", Output.YELLOW);
                continue;
            }
            int rawDamage = m.getBaseDamage();
            target.takeDamage(rawDamage);
            Output.narrative(m.getName() + " hits " + target.getName() + " for " + Math.max(0, rawDamage - target.getArmorReduction()) + " damage.", Output.RED);
            if (target.isFainted()) {
                Output.narrative(target.getName() + " has fainted!", Output.BRIGHT_RED);
                // Award EXP to monster for defeating a hero
                // remove fainted hero from alive list to prevent further targeting this turn
                aliveHeroes.remove(target);
                if (aliveHeroes.isEmpty()) {
                    break;
                }
            }
        }
    }
    // at the end of each round heoes gain some of the hp and mp
    private void endOfRoundRegeneration() {
        for (Hero h : party.getHeroes()) h.regenAfterRound();
        incrementRound();
        System.out.println(">End of round regeneration applied. Round now " + round + "\n");
        Output.sleep(DefaultReader.getDefaultSettings("sleep_ms_after_action"));
    }

    // helper to check if the battle is over or not
    private boolean checkBattleEnd() {
        boolean allMonstersDefeated = true;
        for (Monster m : monsters) {
            if (!m.isDefeated()) {
                allMonstersDefeated = false;
                break;
            }
        }
        boolean allHeroesFainted = true;
        for (Hero h : party.getHeroes()) {
            if (!h.isFainted()) {
                allHeroesFainted = false;
                break;
            }
        }

        if (allMonstersDefeated) {
            heroesWon = true;
            finished = true;
            distributeRewards();
            return true;
        }
        if (allHeroesFainted) {
            heroesWon = false;
            finished = true;
            return true;
        }
        return false;
    }


    // distribute gold and exp to surviving heroes
    private void distributeRewards() {
        int totalGold = 0;
        int totalExp = 0;
        for (Monster m : monsters) {
            totalGold += m.getLevel() * 100; 
            totalExp  += m.getLevel();    
        }
        
        // Track statistics
        statistics.incrementBattlesWon();
        statistics.incrementMonstersDefeated(monsters.size());
        
        for (Hero h : party.getHeroes()) {
            if (h.isFainted()) {
                h.reviveHalf();
                Output.narrative(h.getName() + " is revived with half HP/MP (no rewards).\n", Output.YELLOW);
                continue; // no gold/exp for fainted heroes
            }
            h.addGold(totalGold);
            h.addExperience(totalExp);
            boolean leveledUp = h.checkLevelUp();
            if (leveledUp) {
                statistics.incrementHeroesLevelledUp();
            }
        }
        System.out.println("Rewards distributed: +" + totalGold + " gold, +" + totalExp + " EXP (per surviving hero).");
    }

    public void setHeroesWon(boolean heroesWon) {
        this.heroesWon = heroesWon;
        this.finished = true;
    }

    public void endBattle() {
        this.finished = true;
    }
    private List<Monster> generateMonstersForBattle(int numberOfMonsters, int averagePartyLevel) {
        return MonsterSpawner.generateRandomMonsters(numberOfMonsters, averagePartyLevel);
    }

    public Party getParty() {
        return party;
    }

    public List<Monster> getMonsters() {
        return monsters;
    }
    public int getRound() {
        return round;
    }
    
    public void incrementRound() {
        round++;
    }

}
