package src.Games.LegendsOfValor;

import src.Hero.Hero;
import src.Core.Tile;
import src.Core.Tile.Terrain;



// terrain effects for Legends of Valor
public final class TerrainEffects {

    private TerrainEffects() {
    }

    // attack bonus in Koulou terrain
    public static int applyAttackBonus(Hero hero, Tile tile, int baseDamage) {
        Terrain t = tile.getTerrain();
        if (t == Terrain.KOULOU) {
            return (int) Math.round(baseDamage * 1.10);
        }
        return baseDamage;
    }
    // spell bonus in Bush terrain
    public static int applySpellBonus(Hero hero, Tile tile, int baseDamage) {
        Terrain t = tile.getTerrain();
        if (t == Terrain.BUSH) {
            return (int) Math.round(baseDamage * 1.10);
        }
        return baseDamage;
    }
    // get  dodge chance in Cave terrain
    public static double effectiveDodgeChance(Hero hero, Tile tile) {
        double base = hero.getDodgeChance();

        Terrain t = tile.getTerrain();
        if (t == Terrain.CAVE) {
            base *= 1.10;
        }
        return base;
    }
}
