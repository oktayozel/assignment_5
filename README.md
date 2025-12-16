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



Presentation

- Architecture
    - Core: Board, GameManager, Tile, Piece, Party, User
        - GameManager: orchestrates game flow
    - Games: LegendsOfValor and MonstersAndHeroes
    - Combatant sytem:
        - Hero and Monster superclasses implements Combatant
        - MonsterFactory for monster generation
    - Game Mechanics:
        - Battle: Battle (for MaH) and LoVCombatHandler (for simultaneous combat in LoV)
        - Market: Item trading system
        - Inventory: equipment and item management
        - Item: Armor, Weapon, Spell, Potion types
    - Key Decisions: 
        - Preserved MaH infrastructure where possible
        - Differences/Additions/Modifications for LoV:
            - Extended Tile system for new types (Bush, Cave, Koulou)
                - TerrainEffects class to apply terrain effects to heroes
            - Refactored combat system Battle in to LoVCombatHandler for simultaneous combat
            - Extended Board class as LoVBoard for new version 
            - Added Sound system for game music
            - Refactored Spell with SpellEffect interface for different type of effects for scalability (source: Alim’s MaH version)
- Design Patterns
    - Singleton to ensure only one instance exists
        - SoundManager, Statistics
    - Strategy 
        - SpellEffect: FireSpellEffect, IceSpellEffect, LightningSpellEffect
        - AttackStrategy: WarriorAttackStrategy, PaladinAttackStrategy, SorcererAttackStrategy
    - Factory:
        - MonsterFactory
    - Template:
        - GameManager
        - Market
- Why we chose Oktay’s code
    - Our code was mostly the same but Oktay’s code was more modular and easier to scale. He had also implemented ASCII art and colors which was easy to reuse in the new game.










## Notes
---------------------------------------------------------------------------
Please explain the cool features of your program. Anything that you feel like you did a good job at or were creative about, explain it in bullets here. Additionally, any design decisions should be made here.

- Sound 
- Ascii Art 

## Source and Citations
We have used the following website for animations.
https://www.asciiart.eu/

We have used a royalty free music from the link:

https://freetouse.com/music/zambolino/wanderer-at-night





## How to compile and run
---------------------------------------------------------------------------
Your directions on how to run the code. Make sure to be as thorough as possible! Ideally should resemble the lines below

1. Navigate to the directory "assignment_5" after unzipping the files
2. Run the following instructions:

javac -encoding UTF-8 -d out \
      src/Main.java \
      src/Battle/*.java \
      src/Inventory/*.java \
      src/Market/*.java \
      src/Hero/*.java \
      src/Monster/*.java \
      src/Item/*.java \
      src/Core/*.java \
      src/Games/MonstersAndHeroes/*.java \
      src/Games/LegendsOfValor/*.java \
      src/Utils/Statistics/*.java \
      src/Utils/IO/*.java \
      src/Utils/Default/*.java \
      src/Utils/Interface/*.java \
      src/Utils/Strategy/*.java \
      src/Utils/Sound/*.java
      


java -Dfile.encoding=UTF-8 -cp out src.Main


NOTE: Instead you can simply run ./compile.sh and then ./run.sh

If you get a permission error you can simply give permission to those scripts with chmod +x compile.sh and  chmod +x run.sh 
NOTE 2: You need to create /out directory if it doesnt exist there.


## Input/Output Example
---------------------------------------------------------------------------
Please give us a full execution of what we should see on the screen. Label each text with input and output. For example:

