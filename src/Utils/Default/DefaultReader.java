package src.Utils.Default;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// reads data from txt files
public class DefaultReader {

    private static final String DEFAULTS_DIR = "src/data/new_defaults"; // list-based data here
    private static final Random RAND = new Random();


    // templates for all entities
    public static class HeroTemplate {
        public String name;
        public int level; 
        public int HP; 
        public int MP; 
        public int strength; 
        public int dexterity; 
        public int agility; 
        public int gold; 
        public int startingExp;
    }

    public static class MonsterTemplate {
        public String name; 
        public int level; 
        public int HP; 
        public int baseDamage; 
        public int defense; 
        public double dodge; 
    }

    public static class WeaponTemplate { 
        public String name; 
        public int cost; 
        public int level; 
        public int damage; 
        public int hands; 
    }

    public static class ArmorTemplate { 
        public String name; 
        public int cost; 
        public int level; 
        public int reduction; 
    }

    public static class PotionTemplate { 
        public String name; 
        public int cost; 
        public int level; 
        public int increase; 
        public String affected; 
    }

    public static class SpellTemplate { 
        public String name; 
        public int cost; 
        public int level; 
        public int damage; 
        public int manaCost; 
        public String type; 
    }

    public static int getDefaultSettings(String key){
        File f = new File(DEFAULTS_DIR, "Settings.txt");
        if (!f.exists()) return -1;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }
                int idx = line.indexOf(':');
                if (idx == -1) {
                    continue;
                }
                String k = line.substring(0, idx).trim();
                String v = line.substring(idx + 1).trim();
                if (k.equalsIgnoreCase(key)) {
                    v = v.replace("%"," ").trim();
                    try { return Integer.parseInt(v); } catch (NumberFormatException ignored) { return -1; }
                }
            }
        } catch (IOException ignored) {}
        return -1;
    }

    public static List<HeroTemplate> loadHeroClass(String classPlural){
        File f = new File(DEFAULTS_DIR, classPlural + ".txt");
        List<HeroTemplate> list = new ArrayList<>();
        if (!f.exists()) return list;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line; boolean headerSkipped = false;
            while((line = br.readLine()) != null){
                line = line.trim();
                if (line.isEmpty()) continue;
                if (!headerSkipped){ 
                    headerSkipped = true; continue;
                }
                String[] parts = line.split("\\s+");
                if (parts.length < 7) {
                    continue;
                }
                HeroTemplate ht = new HeroTemplate();
                ht.name = parts[0];
                ht.MP = parseInt(parts[1], 50);
                ht.strength = parseInt(parts[2], 20);
                ht.agility = parseInt(parts[3], 10);
                ht.dexterity = parseInt(parts[4], 15);
                ht.gold = parseInt(parts[5], 100);
                ht.startingExp = parseInt(parts[6], 0);
                ht.level = 1; ht.HP = 100;
                list.add(ht);
            }
        } catch(IOException ignored){}
        return list;
    }

    // returns a random hero template of the given type of the class
    public static HeroTemplate getRandomHero(String classPlural){
        List<HeroTemplate> list = loadHeroClass(classPlural);
        if (list.isEmpty()) return null;
        return list.get(RAND.nextInt(list.size()));
    }

    // -------- MONSTER LISTS --------
    public static List<MonsterTemplate> loadMonsterType(String typePlural){
        File f = new File(DEFAULTS_DIR, typePlural + ".txt");
        List<MonsterTemplate> list = new ArrayList<>();
        if (!f.exists()) return list;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line; boolean headerSkipped = false;
            while((line = br.readLine()) != null){
                line = line.trim();
                if (line.isEmpty()) continue;
                if (!headerSkipped) {
                    headerSkipped = true;
                    continue;
                }
                String[] parts = line.split("\\s+");
                if (parts.length < 5) {
                    continue;
                }
                MonsterTemplate mt = new MonsterTemplate();
                mt.name = parts[0];
                mt.level = parseInt(parts[1], 1);
                mt.baseDamage = parseInt(parts[2], 100);
                mt.defense = parseInt(parts[3], 100);
                int dodgePct = parseInt(parts[4], 10);
                mt.dodge = dodgePct / 100.0;
                mt.HP = mt.level * 100; // per 100
                list.add(mt);
            }
        } catch(IOException ignored){}
        return list;
    }
    public static MonsterTemplate getRandomMonster(String typePlural){
        List<MonsterTemplate> list = loadMonsterType(typePlural);
        if (list.isEmpty()) return null;
        return list.get(RAND.nextInt(list.size()));
    }

    // weapons
    public static List<WeaponTemplate> loadWeapons(){
        return parseWeaponFile("Weaponry.txt");
    }
    private static List<WeaponTemplate> parseWeaponFile(String file) {
        List<WeaponTemplate> list = new ArrayList<>();
        File f = new File(DEFAULTS_DIR, file);
        if (!f.exists()) {
            return list;
        }
        
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            boolean headerSkipped = false;
            
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }
                
                if (!headerSkipped) {
                    headerSkipped = true;
                    continue;
                }
                
                String[] p = line.split("\\s+");
                if (p.length < 5) {
                    continue;
                }
                
                WeaponTemplate wt = new WeaponTemplate();
                wt.name = p[0];
                wt.cost = parseInt(p[1], 0);
                wt.level = parseInt(p[2], 1);
                wt.damage = parseInt(p[3], 0);
                wt.hands = parseInt(p[4], 1);
                list.add(wt);
            }
        } catch (IOException e) {
            // do nothing
        }
        
        return list;
    }

    public static List<ArmorTemplate> loadArmor() {
        List<ArmorTemplate> list = new ArrayList<>();
        File f = new File(DEFAULTS_DIR, "Armory.txt");
        if (!f.exists()) {
            return list;
        }
        
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            boolean headerSkipped = false;
            
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }
                
                if (!headerSkipped) {
                    headerSkipped = true;
                    continue;
                }
                
                String[] p = line.split("\\s+");
                if (p.length < 4) {
                    continue;
                }
                
                ArmorTemplate at = new ArmorTemplate();
                at.name = p[0];
                at.cost = parseInt(p[1], 0);
                at.level = parseInt(p[2], 1);
                at.reduction = parseInt(p[3], 0);
                list.add(at);
            }
        } catch (IOException e) {
         // do nothing
        }
        
        return list;
    }

    public static List<PotionTemplate> loadPotions() {
        List<PotionTemplate> list = new ArrayList<>();
        File f = new File(DEFAULTS_DIR, "Potions.txt");
        if (!f.exists()) {
            return list;
        }
        
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            boolean headerSkipped = false;
            
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }
                
                if (!headerSkipped) {
                    headerSkipped = true;
                    continue;
                }
                
                String[] p = line.split("\\s+");
                if (p.length < 5) {
                    continue;
                }
                
                PotionTemplate pt = new PotionTemplate();
                pt.name = p[0];
                pt.cost = parseInt(p[1], 0);
                pt.level = parseInt(p[2], 1);
                pt.increase = parseInt(p[3], 0);
                
                StringBuilder sb = new StringBuilder();
                for (int i = 4; i < p.length; i++) {
                    if (i > 4) {
                        sb.append(' ');
                    }
                    sb.append(p[i]);
                }
                pt.affected = sb.toString();
                
                list.add(pt);
            }
        } catch (IOException e) {
            // do nothing
        }
        
        return list;
    }

    public static List<SpellTemplate> loadSpells() {
        List<SpellTemplate> all = new ArrayList<>();
        parseSpellFile("FireSpells.txt", "Fire", all);
        parseSpellFile("IceSpells.txt", "Ice", all);
        parseSpellFile("LightningSpells.txt", "Lightning", all);
        return all;
    }

    private static void parseSpellFile(String file, String type, List<SpellTemplate> out) {
        File f = new File(DEFAULTS_DIR, file);
        if (!f.exists()) {
            return;
        }
        
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            boolean headerSkipped = false;
            
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }
                
                if (!headerSkipped) {
                    headerSkipped = true;
                    continue;
                }
                
                String[] p = line.split("\\s+");
                if (p.length < 5) {
                    continue;
                }
                
                SpellTemplate st = new SpellTemplate();
                st.name = p[0];
                st.cost = parseInt(p[1], 0);
                st.level = parseInt(p[2], 1);
                st.damage = parseInt(p[3], 0);
                st.manaCost = parseInt(p[4], 0);
                st.type = type;
                out.add(st);
            }
        } catch (IOException e) {
            // do nothing
        }
    }

    private static int parseInt(String s, int def) {
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return def;
        }
    }
}

