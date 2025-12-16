# CS611-Assignment 5
## 
---------------------------------------------------------------------------
- Name: Oktay Ozel
- Email: oozel@bu.edu
- Student ID: U93822204
---------------------------------------------------------------------------
- Name: Alim Cemal Kura 
- Email: ackura@bu.edu
- Student ID: U86485144
---------------------------------------------------------------------------
- Name: Josue Vega
- Email: josuevl@bu.edu
- Student ID: U43284900
---------------------------------------------------------------------------



## Repository 
https://github.com/oktayozel/assignment_5


## Files 
---------------------------------------------------------------------------
assignment_5/
│
├── README.md
├── compile.sh
├── run.sh
├── Diagram for Assignment 5 Oktay Ozel.pdf
│
├── src/
│   ├── Main.java
│   │
│   ├── Battle/
│   │   ├── Battle.java
│   │   └── LoVCombatHandler.java
│   │
│   ├── Core/
│   │   ├── Board.java
│   │   ├── GameManager.java
│   │   ├── Party.java
│   │   ├── Piece.java
│   │   ├── Tile.java
│   │   └── User.java
│   │
│   ├── Hero/
│   │   ├── Hero.java
│   │   ├── Paladin.java
│   │   ├── Sorcerer.java
│   │   └── Warrior.java
│   │
│   ├── Monster/
│   │   ├── Monster.java
│   │   ├── Dragon.java
│   │   ├── Exoskeleton.java
│   │   ├── Spirit.java
│   │   ├── MonsterSpawner.java
│   │   └── MonsterFactory.java
│   │
│   ├── Item/
│   │   ├── Item.java
│   │   ├── Armor.java
│   │   ├── Potion.java
│   │   ├── Spell.java
│   │   └── Weapon.java
│   │
│   ├── Inventory/
│   │   ├── Inventory.java
│   │   └── InventoryEntry.java
│   │
│   ├── Market/
│   │   ├── Market.java
│   │   ├── LoVMarket.java
│   │   └── MaHMarket.java
│   │
│   ├── Games/
│   │   ├── LegendsOfValor/
│   │   │   ├── LoVGameManager.java
│   │   │   ├── LoVBoard.java
│   │   │   └── LoVMarket.java
│   │   │
│   │   └── MonstersAndHeroes/
│   │       ├── MaHGameManager.java
│   │       ├── MaHBoard.java
│   │       └── MaHMarket.java
│   │
│   ├── Utils/
│   │   ├── Interface/
│   │   │   ├── AttackStrategy.java
│   │   │   ├── Combatant.java
│   │   │   ├── Consumable.java
│   │   │   ├── Equippable.java
│   │   │   ├── Repairable.java
│   │   │   ├── Tradeable.java
│   │   │   └── SpellEffect.java
│   │   │
│   │   ├── Strategy/
│   │   │   ├── WarriorAttackStrategy.java
│   │   │   ├── SorcererAttackStrategy.java
│   │   │   ├── PaladinAttackStrategy.java
│   │   │   ├── FireSpellEffect.java
│   │   │   ├── IceSpellEffect.java
│   │   │   └── LightningSpellEffect.java
│   │   │
│   │   ├── IO/
│   │   │   ├── Input.java
│   │   │   └── Output.java
│   │   │
│   │   ├── Statistics/
│   │   │   └── Statistics.java
│   │   │
│   │   ├── Default/
│   │   │   └── DefaultReader.java
│   │   │
│   │   └── Settings/
│   │       └── Settings.java
│   │
│   ├── Description/
│   │   └── Monsters and Heroes - 2025.pdf
│   │
│   └── data/
│       └── new_defaults/
│           ├── Settings.txt
│           ├── Warriors.txt
│           ├── Sorcerers.txt
│           ├── Paladins.txt
│           ├── Dragons.txt
│           ├── Spirits.txt
│           ├── Exoskeletons.txt
│           ├── Weaponry.txt
│           ├── Armory.txt
│           ├── Potions.txt
│           ├── FireSpells.txt
│           ├── IceSpells.txt
│           └── LightningSpells.txt
│
└── out/
    └── (compiled .class files)







## Analysis of Previous Assignments:














## Notes
---------------------------------------------------------------------------
Please explain the cool features of your program. Anything that you feel like you did a good job at or were creative about, explain it in bullets here. Additionally, any design decisions should be made here.

Overall:
- Implemented a Statistics module which was not asked.
- I have implemented several animations to the game.
- I have implemented colored UI.
- I have implemented settings file that is not in requirements also
- You can type exit at any moment in the game and can gracefully.
- Seperating the components into subdirectories app, components, core, data, io. 
- In the Settings class, it enables dynamic configuration by loading properties like board size and supported games from an external file.
- Implemented Strategy Design Pattern
- Implemented several interfaces for the items namely Consumable, Equippable, Repairable, or Tradable
- Makes sure that game is playable.
- and lots of coool features.


## Source and Citations
We have used the following website for animations.
https://www.asciiart.eu/

We have used a royalty free music from the link:

https://freetouse.com/music/zambolino/wanderer-at-night



## How to compile and run
---------------------------------------------------------------------------
Your directions on how to run the code. Make sure to be as thorough as possible! Ideally should resemble the lines below

1. Navigate to the directory "assignment_4" after unzipping the files
2. Run the following instructions:

javac -encoding UTF-8 -d out \
      src/Main.java \
      src/Battle/*.java \
      src/Inventory/*.java \
      src/Market/*.java \
      src/Statistics/*.java \
      src/IO/*.java \
      src/Hero/*.java \
      src/Monster/*.java \
      src/Item/*.java \
      src/Core/*.java \
      src/Default/*.java \
      src/Interface/*.java \
      src/Strategy/*.java


java -Dfile.encoding=UTF-8 -cp out src.Main

NOTE: Instead you can simply run ./compile.sh and then ./run.sh

If you get a permission error you can simply give permission to those scripts with chmod +x compile.sh and  chmod +x run.sh 
NOTE 2: You need to create /out directory if it doesnt exist there.


## Input/Output Example
---------------------------------------------------------------------------
Please give us a full execution of what we should see on the screen. Label each text with input and output. For example:

