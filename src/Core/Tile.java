package src.Core;

import src.Market.Market;
import src.Hero.Hero;
import src.Monster.Monster;


public class Tile {

    public enum Type {
        INACCESSIBLE,
        MARKET,
        COMMON
    }

    public enum Terrain {
        NONE,          // default / MaH
        PLAIN,
        BUSH,
        CAVE,
        KOULOU,
        OBSTACLE,      // removable rock
        NEXUS_HERO,    // heroes' nexus (also a market)
        NEXUS_MONSTER, // monsters' nexus
        WALL           // hard lane separator (inaccessible)
    }

    private final int row;
    private final int col;
    private final Type type;
    private final Market market;

    private Terrain terrain;

    private Hero heroOccupant;
    private Monster monsterOccupant;


    public Tile(int row, int col, Type type) {
        this.row = row;
        this.col = col;
        this.type = type;
        this.market = (type == Type.MARKET) ? new Market() : null;
    }

    // New constructor for LoV
    public Tile(int row, int col, Type type, Terrain terrain) {
        this.row = row;
        this.col = col;
        this.type = type;
        this.terrain = terrain;
        this.market = (type == Type.MARKET) ? new Market() : null;
    }




    // check if accessible or not
    public boolean isAccessible() {
        return type != Type.INACCESSIBLE;
    }

    public boolean isMarket() {
        return type == Type.MARKET;
    }

    public boolean isCommon() {
        return type == Type.COMMON;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Type getType() {
        return type;
    }

    public Terrain getTerrain() {
        return terrain;
    }
    public void setTerrain(Terrain t) {
        this.terrain = t;
    }

    public boolean isHeroesNexus() {
        return terrain == Terrain.NEXUS_HERO;
    }

    public boolean isMonstersNexus() {
        return terrain == Terrain.NEXUS_MONSTER;
    }


    // get market
    public Market getMarket() {
        if (isMarket()) {
            return market;
        }
        return null;
    }


    public char getSymbol(boolean hasPartyHere) {
        if (hasPartyHere) {
            return 'P';
        }
        if(type == Type.INACCESSIBLE){
            return 'X';
        }
        if(type == Type.MARKET){
            return 'M';
        }
        if(type == Type.COMMON){
            return ' ';
        }

        return ' ';
    }
}
