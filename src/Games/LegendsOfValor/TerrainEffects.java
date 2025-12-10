package src.Games.LegendsOfValor;

import src.Hero.Hero;
import src.Core.Tile;
import src.Core.Tile.Terrain;

public final class TerrainEffects {

    private TerrainEffects() {
    }

    public static int applyAttackBonus(Hero hero, Tile tile, int baseDamage) {
        Terrain t = tile.getTerrain();
        if (t == Terrain.KOULOU) {
            return (int) Math.round(baseDamage * 1.10);
        }
        return baseDamage;
    }

    public static int applySpellBonus(Hero hero, Tile tile, int baseDamage) {
        Terrain t = tile.getTerrain();
        if (t == Terrain.BUSH) {
            return (int) Math.round(baseDamage * 1.10);
        }
        return baseDamage;
    }

    public static double effectiveDodgeChance(Hero hero, Tile tile) {
        double base = hero.getDodgeChance();

        Terrain t = tile.getTerrain();
        if (t == Terrain.CAVE) {
            base *= 1.10;
        }
        return base;
    }
}
