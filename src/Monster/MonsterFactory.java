package src.Monster;

import java.util.ArrayList;
import java.util.List;
import src.Utils.Default.DefaultReader;

import java.util.Random;

// generates monsters for battle
public class MonsterFactory {
    static Random rand = new Random();


    // generates random monsters based on the level of the max hero
    // this parameter should be passed from the battle manager
    public static List<Monster> generateRandomMonsters(int count, int level) {
        List<Monster> monsters = new ArrayList<>();
        
        for (int i = 0; i < count; i++) {
            int type = rand.nextInt(3);
            String typeName = type == 0 ? "Dragons" : type == 1 ? "Exoskeletons" : "Spirits";
            
            List<DefaultReader.MonsterTemplate> templates = DefaultReader.loadMonsterType(typeName);
            if (templates.isEmpty()) continue;
            
            // Filter templates to only those matching the desired level (±1 for flexibility)
            List<DefaultReader.MonsterTemplate> levelAppropriate = new ArrayList<>();
            for (DefaultReader.MonsterTemplate t : templates) {
                if (Math.abs(t.level - level) <= 1) {
                    levelAppropriate.add(t);
                }
            }
            
            // If no level appropriate monsters, use closest level
            if (levelAppropriate.isEmpty()) {
                DefaultReader.MonsterTemplate closest = templates.get(0);
                for (DefaultReader.MonsterTemplate t : templates) {
                    if (Math.abs(t.level - level) < Math.abs(closest.level - level)) {
                        closest = t;
                    }
                }
                levelAppropriate.add(closest);
            }
            
            DefaultReader.MonsterTemplate template = levelAppropriate.get(rand.nextInt(levelAppropriate.size()));
            
            // Scale stats proportionally to actual battle level vs template level
            double scaleFactor = (double) level / template.level;
            int hp = level * 100;
            int scaledDamage = (int) Math.round(template.baseDamage * scaleFactor);
            int scaledDefense = (int) Math.round(template.defense * scaleFactor);
            
            Monster m = null;
            if (type == 0) {
                m = new Dragon(template.name, level, hp, scaledDamage, scaledDefense, template.dodge);
            } else if (type == 1) {
                m = new Exoskeleton(template.name, level, hp, scaledDamage, scaledDefense, template.dodge);
            } else {
                m = new Spirit(template.name, level, hp, scaledDamage, scaledDefense, template.dodge);
            }
            
            monsters.add(m);
        }
        return monsters;
    }
}