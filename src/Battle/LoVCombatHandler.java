package src.Battle;

import src.Core.Tile;
import src.Games.LegendsOfValor.LoVBoard;
import src.Hero.Hero;
import src.Monster.Monster;
import src.Monster.MonsterSpawner;
import src.Item.*;
import src.Inventory.InventoryEntry;
import src.Utils.IO.Input;
import src.Utils.IO.Output;
import src.Core.Party;

import java.util.*;

/**
 * LoVCombatHandler - Handles all combat actions for Legends of Valor
 * 
 * This class adapts combat logic from Battle.java for position-based gameplay.
 * Key differences:
 * - No battle loop (continuous gameplay instead of separate battles)
 * - Range-based targeting (adjacent tiles only)
 * - Monster AI (attack if in range, else move south)
 * - Wave-based spawning (every N rounds)
 */

public class LoVCombatHandler {
    
    private LoVBoard board;
    private Party party;
    private Map<Hero, int[]> heroPositions;      // Hero -> [row, col, laneIndex]
    private Map<Monster, int[]> monsterPositions; // Monster -> [row, col, laneIndex]
    private List<Monster> activeMonsters;
    
    public LoVCombatHandler(LoVBoard board, Party party, 
                           Map<Hero, int[]> heroPositions,
                           Map<Monster, int[]> monsterPositions,
                           List<Monster> activeMonsters) {
        this.board = board;
        this.party = party;
        this.heroPositions = heroPositions;
        this.monsterPositions = monsterPositions;
        this.activeMonsters = activeMonsters;
    }
    
    // ============================================
    // HERO COMBAT ACTIONS
    // ============================================
    
    // Hero attacks monster in range
    // Player selects target from available monsters in range
    public void heroAttack(Hero attacker) {
        if (attacker.isFainted()) {
            Output.narrative(attacker.getName() + " cannot act (fainted).\n");
            return;
        }
        
        int[] pos = heroPositions.get(attacker);
        List<Monster> targets = getMonstersInRange(pos[0], pos[1]);
        
        if (targets.isEmpty()) {
            Output.narrative("No monsters in range to attack!\n");
            return;
        }
        
        // Display targets
        System.out.println("Select target monster:");
        for (int i = 0; i < targets.size(); i++) {
            Monster m = targets.get(i);
            int[] mPos = monsterPositions.get(m);
            System.out.println("(" + (i+1) + ") " + m.getName() + 
                             " HP:" + m.getHP() + " at (" + mPos[0] + "," + mPos[1] + ")");
        }
        
        int choice = Input.readInt(1, targets.size()) - 1;
        Monster target = targets.get(choice);
        
        // Dodge check
        if (Math.random() * 100 < target.getDodge()) {
            Output.narrative(target.getName() + " dodged the attack!\n");
            return;
        }
        
        // Calculate damage (same formula as MaH)
        int rawDamage = attacker.computeAttackDamage();
        int finalDamage = Math.max(0, rawDamage - target.getDefense());
        target.takeDamage(finalDamage);
        
        Output.narrative(attacker.getName() + " hits " + target.getName() + 
                        " for " + finalDamage + " damage!\n", Output.GREEN);
        
        if (target.isDefeated()) {
            handleMonsterDefeat(target);
        }
    }
    
    // Hero casts a spell on a monster
    // Range-based targeting
    public void heroCastSpell(Hero caster) {
        if (caster.isFainted()) {
            Output.narrative(caster.getName() + " cannot act (fainted).\n");
            return;
        }
        
        // Get available spells
        List<InventoryEntry> spellEntries = new ArrayList<>();
        for (InventoryEntry entry : caster.getInventory().getEntries()) {
            if (entry.getItem() instanceof Spell) {
                spellEntries.add(entry);
            }
        }
        
        if (spellEntries.isEmpty()) {
            System.out.println("No spells in inventory.");
            return;
        }
        
        // Display spells
        System.out.println("Select spell:");
        for (int i = 0; i < spellEntries.size(); i++) {
            Spell sp = (Spell) spellEntries.get(i).getItem();
            System.out.println("(" + (i+1) + ") " + sp.getName() + 
                             " DMG:" + sp.getDamage() + 
                             " Mana:" + sp.getManaCost() + 
                             " Type:" + sp.getSpellType());
        }
        
        int sIdx = Input.readInt(1, spellEntries.size()) - 1;
        Spell spell = (Spell) spellEntries.get(sIdx).getItem();
        InventoryEntry spellEntry = spellEntries.get(sIdx);
        
        // Check mana
        if (!caster.spendMP(spell.getManaCost())) {
            System.out.println("Not enough MP to cast the spell.");
            return;
        }
        
        // Get targets in range
        int[] pos = heroPositions.get(caster);
        List<Monster> targets = getMonstersInRange(pos[0], pos[1]);
        
        if (targets.isEmpty()) {
            Output.narrative("No monsters in range!\n");
            // Refund mana
            caster.restoreMP(spell.getManaCost());
            return;
        }
        
        // Select target
        System.out.println("Select target monster:");
        for (int i = 0; i < targets.size(); i++) {
            Monster m = targets.get(i);
            int[] mPos = monsterPositions.get(m);
            System.out.println("(" + (i+1) + ") " + m.getName() + 
                             " HP:" + m.getHP() + " at (" + mPos[0] + "," + mPos[1] + ")");
        }
        
        int mIdx = Input.readInt(1, targets.size()) - 1;
        Monster target = targets.get(mIdx);
        
        // Dodge check
        if (Math.random() * 100 < target.getDodge()) {
            Output.narrative(target.getName() + " dodged the spell!\n");
            return;
        }
        
        // Calculate spell damage (same as MaH)
        int rawDamage = spell.getDamage() + (int)Math.round(caster.getDexterity() * 0.05);
        int finalDamage = Math.max(0, rawDamage - target.getDefense());
        target.takeDamage(finalDamage);
        
        Output.narrative(caster.getName() + " casts " + spell.getName() + 
                        " on " + target.getName() + " for " + finalDamage + " damage.\n", 
                        Output.BLUE);
        
        // Apply spell effects
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
        
        // Consume spell
        spellEntry.decreaseQuantity(1);
        if (spellEntry.getQuantity() <= 0) {
            caster.getInventory().removeItemByName(spell.getName());
        }
        
        if (target.isDefeated()) {
            handleMonsterDefeat(target);
        }
    }
    
    // Hero uses a potion -- same as MaH
    public void heroUsePotion(Hero user) {
        if (user.isFainted()) {
            Output.narrative(user.getName() + " cannot act (fainted).\n");
            return;
        }
        
        List<InventoryEntry> potionEntries = new ArrayList<>();
        for (InventoryEntry entry : user.getInventory().getEntries()) {
            if (entry.getItem() instanceof Potion) {
                potionEntries.add(entry);
            }
        }
        
        if (potionEntries.isEmpty()) {
            System.out.println("No potions in inventory.");
            return;
        }
        
        System.out.println("Select potion:");
        for (int i = 0; i < potionEntries.size(); i++) {
            Potion p = (Potion) potionEntries.get(i).getItem();
            System.out.println("(" + (i+1) + ") " + p.getName() + 
                             " Type:" + p.getEffectType() + 
                             " Amount:" + p.getEffectAmount());
        }
        
        int pIdx = Input.readInt(1, potionEntries.size()) - 1;
        InventoryEntry pEntry = potionEntries.get(pIdx);
        Potion potion = (Potion) pEntry.getItem();
        
        user.applyPotion(potion);
        pEntry.decreaseQuantity(1);
        if (pEntry.getQuantity() <= 0) {
            user.getInventory().removeItemByName(potion.getName());
        }
        
        Output.narrative(user.getName() + " uses " + potion.getName() + ".\n", 
                        Output.BLUE);
    }
    
    // Hero changes equipment
    // Same as MaH
    public void heroChangeEquipment(Hero hero) {
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
                System.out.println("(" + (i+1) + ") " + w.getName() + 
                                 " DMG:" + w.getDamage() + 
                                 " Hands:" + w.getHands());
            }
            
            int wIdx = Input.readInt(1, weaponEntries.size()) - 1;
            Weapon w = (Weapon) weaponEntries.get(wIdx).getItem();
            
            if (w.getHands() == 1) {
                System.out.println("Use (1) One hand or (2) Two hands? (Two hands gives 50% damage boost)");
                int handChoice = Input.readInt(1, 2);
                boolean useTwoHands = (handChoice == 2);
                hero.setEquippedWeapon(w, useTwoHands);
                String handMsg = useTwoHands ? " with both hands (boosted!)" : " with one hand";
                Output.narrative(hero.getName() + " equipped weapon " + w.getName() + handMsg + "\n", 
                               Output.CYAN);
            } else {
                hero.setEquippedWeapon(w);
                Output.narrative(hero.getName() + " equipped weapon " + w.getName() + 
                               " (requires both hands)\n", Output.CYAN);
            }
        } else {
            if (armorEntries.isEmpty()) {
                System.out.println("No armor available.");
                return;
            }
            
            System.out.println("Select armor:");
            for (int i = 0; i < armorEntries.size(); i++) {
                Armor a = (Armor) armorEntries.get(i).getItem();
                System.out.println("(" + (i+1) + ") " + a.getName() + 
                                 " RED:" + a.getDamageReduction());
            }
            
            int aIdx = Input.readInt(1, armorEntries.size()) - 1;
            Armor a = (Armor) armorEntries.get(aIdx).getItem();
            hero.setEquippedArmor(a);
            Output.narrative(hero.getName() + " equipped armor " + a.getName() + "\n", 
                           Output.CYAN);
        }
    }
    
    // ============================================
    // MONSTER AI & ACTIONS
    // ============================================
    
    // MonsterAI: Attack if hero in range, else move south
    public void monsterTakeTurn(Monster monster) {
        if (monster.isDefeated()) return;
        
        int[] pos = monsterPositions.get(monster);
        int currentRow = pos[0];
        int currentCol = pos[1];
        
        // Check if can attack hero
        List<Hero> targets = getHeroesInRange(currentRow, currentCol);
        
        if (!targets.isEmpty()) {
            // Attack random hero in range
            Hero target = targets.get(new Random().nextInt(targets.size()));
            monsterAttack(monster, target);
        } else {
            // Move south toward hero nexus
            moveMonsterSouth(monster);
        }
    }
    
    // Monster attacks a hero
    private void monsterAttack(Monster attacker, Hero target) {
        // Dodge check
        if (Math.random() < target.getDodgeChance()) {
            Output.narrative(target.getName() + " dodged " + attacker.getName() + 
                           "'s attack!", Output.YELLOW);
            return;
        }
        
        int rawDamage = attacker.computeAttackDamage();
        target.takeDamage(rawDamage);
        
        int actualDamage = Math.max(0, rawDamage - target.getArmorReduction());
        Output.narrative(attacker.getName() + " hits " + target.getName() + 
                        " for " + actualDamage + " damage.", Output.RED);
        
        if (target.isFainted()) {
            Output.narrative(target.getName() + " has been defeated! Will respawn next round.", 
                           Output.BRIGHT_RED);
        }
    }
    

    // Move monster one space south -- towards hero nexus
    // Returns true if monster reached hero nexus (game over)
    public boolean moveMonsterSouth(Monster monster) {
        int[] pos = monsterPositions.get(monster);
        int currentRow = pos[0];
        int currentCol = pos[1];
        int laneIndex = pos[2];
        
        int newRow = currentRow + 1;
        
        // Check if reached hero nexus row
        if (newRow >= 7) {
            Tile nexusTile = board.getTile(newRow, currentCol);
            if (nexusTile != null && nexusTile.isHeroesNexus()) {
                Output.narrative("\n" + monster.getName() + 
                               " REACHED THE HERO NEXUS! DEFEAT!", 
                               Output.BRIGHT_RED);
                return true; // Game over
            }
        }
        
        // Check if path is blocked by another monster
        Tile targetTile = board.getTile(newRow, currentCol);
        if (targetTile.getMonsterOccupant() != null) {
            // Path blocked, can't move
            return false;
        }
        
        // Move monster
        board.getTile(currentRow, currentCol).setMonsterOccupant(null);
        monsterPositions.put(monster, new int[]{newRow, currentCol, laneIndex});
        targetTile.setMonsterOccupant(monster);
        
        Output.narrative(monster.getName() + " advances to (" + newRow + ", " + currentCol + ")", 
                        Output.MAGENTA);
        
        return false;
    }
    
    // ============================================
    // MONSTER SPAWNING
    // ============================================
    

    // Spawn a wave of monsters at the monster nexus - one per lane
    public void spawnMonsterWave(int round) {
        int[] laneCols = {1, 4, 7}; // Right columns of each lane
        int monsterNexusRow = 0;
        int level = party.getHighestLevel();
        
        List<Monster> newMonsters = MonsterSpawner.generateRandomMonsters(3, level);
        
        for (int i = 0; i < newMonsters.size() && i < 3; i++) {
            Monster m = newMonsters.get(i);
            int col = laneCols[i];
            
            // Check if spawn location is occupied
            Tile spawnTile = board.getTile(monsterNexusRow, col);
            if (spawnTile.getMonsterOccupant() != null) {
                Output.narrative("Lane " + (i+1) + " spawn blocked! Monster couldn't spawn.", 
                               Output.YELLOW);
                continue;
            }
            
            monsterPositions.put(m, new int[]{monsterNexusRow, col, i});
            activeMonsters.add(m);
            spawnTile.setMonsterOccupant(m);
            
            Output.narrative(m.getName() + " spawns in lane " + (i+1) + "!", 
                           Output.BRIGHT_RED);
        }
        
        System.out.println("\n=== Monster wave spawned! (Round " + round + ") ===");
    }
    
    // ============================================
    // COMBAT HELPERS
    // ============================================
    

    // Get all monsters in attack range of a position
    public List<Monster> getMonstersInRange(int row, int col) {
        List<Monster> inRange = new ArrayList<>();
        int[][] offsets = {{-1,0}, {1,0}, {0,-1}, {0,1}}; // N, S, W, E
        
        for (int[] offset : offsets) {
            int r = row + offset[0];
            int c = col + offset[1];
            if (board.isInside(r, c)) {
                Tile tile = board.getTile(r, c);
                Monster m = tile.getMonsterOccupant();
                if (m != null && !m.isDefeated()) {
                    inRange.add(m);
                }
            }
        }
        
        return inRange;
    }
    
    // Get all heroes in attack range of a position
    public List<Hero> getHeroesInRange(int row, int col) {
        List<Hero> inRange = new ArrayList<>();
        int[][] offsets = {{-1,0}, {1,0}, {0,-1}, {0,1}};
        
        for (int[] offset : offsets) {
            int r = row + offset[0];
            int c = col + offset[1];
            if (board.isInside(r, c)) {
                Tile tile = board.getTile(r, c);
                Hero h = tile.getHeroOccupant();
                if (h != null && !h.isFainted()) {
                    inRange.add(h);
                }
            }
        }
        
        return inRange;
    }
    

    // Handle monster defeat: distribute rewards to heroes and remove from board
    private void handleMonsterDefeat(Monster monster) {
        Output.narrative(monster.getName() + " is defeated!", Output.BRIGHT_GREEN);
        
        // Distribute rewards to ALL heroes (per spec)
        int gold = 500 * monster.getLevel();
        int xp = 2 * monster.getLevel();
        
        for (Hero h : party.getHeroes()) {
            h.addGold(gold);
            h.addExperience(xp);
            boolean leveledUp = h.checkLevelUp();
            if (leveledUp) {
                Output.narrative(h.getName() + " leveled up to level " + h.getLevel() + "!", 
                               Output.BRIGHT_YELLOW);
            }
        }
        
        System.out.println("All heroes gain " + gold + " gold and " + xp + " XP!");
        
        // Remove from board
        int[] pos = monsterPositions.get(monster);
        board.getTile(pos[0], pos[1]).setMonsterOccupant(null);
        
        activeMonsters.remove(monster);
        monsterPositions.remove(monster);
    }
    

    // Display combat information
    public void displayCombatInfo() {
        System.out.println("\n=== Combat Information ===");
        System.out.println("Active Monsters: " + activeMonsters.size());
        
        for (Monster m : activeMonsters) {
            if (m.isDefeated()) continue;
            int[] pos = monsterPositions.get(m);
            System.out.println("  " + m.getName() + " | HP: " + m.getHP() + 
                             " | Pos: (" + pos[0] + "," + pos[1] + ")");
        }
    }
    
    // ============================================
    // GETTERS
    // ============================================
    
    public List<Monster> getActiveMonsters() {
        return activeMonsters;
    }
    
    public Map<Monster, int[]> getMonsterPositions() {
        return monsterPositions;
    }
}