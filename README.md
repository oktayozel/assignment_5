# CS611-Assignment 5
## 
---------------------------------------------------------------------------
- Name: Oktay Ozel
- Email: oozel@bu.edu
- Student ID: U93822204



## Repository 
https://github.com/oktayozel/cs611_hw3



## Files 
---------------------------------------------------------------------------
assignment_4/
│
├── README.md
├── compile.sh
├── run.sh
├── Diagram for Assignment 4 Oktay Ozel.pdf
│
├── src/
│   ├── Main.java
│   │
│   ├── Battle/
│   │   └── Battle.java
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
│   │   └── MarketItemTemplates.java
│   │
│   ├── Interface/
│   │   ├── AttackStrategy.java
│   │   ├── Combatant.java
│   │   ├── Consumable.java
│   │   ├── Equippable.java
│   │   ├── Repairable.java
│   │   └── Tradeable.java
│   │
│   ├── Strategy/
│   │   ├── WarriorAttackStrategy.java
│   │   ├── SorcererAttackStrategy.java
│   │   └── PaladinAttackStrategy.java
│   │
│   ├── IO/
│   │   ├── Input.java
│   │   └── Output.java
│   │
│   ├── Statistics/
│   │   └── Statistics.java
│   │
│   ├── Default/
│   │   └── DefaultReader.java
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
I haven't retained the structure from the previous assignments mostly cause the project has changed a lot but I have preserved the core structure, input output and statistics

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



Output:

██╗     ███████╗ ██████╗ ███████╗███╗   ██╗██████╗ ███████╗                    
██║     ██╔════╝██╔════╝ ██╔════╝████╗  ██║██╔══██╗██╔════╝██╗                 
██║     █████╗  ██║  ███╗█████╗  ██╔██╗ ██║██║  ██║███████╗╚═╝                 
██║     ██╔══╝  ██║   ██║██╔══╝  ██║╚██╗██║██║  ██║╚════██║██╗                 
███████╗███████╗╚██████╔╝███████╗██║ ╚████║██████╔╝███████║╚═╝                 
╚══════╝╚══════╝ ╚═════╝ ╚══════╝╚═╝  ╚═══╝╚═════╝ ╚══════╝                    
                                                                               
███╗   ███╗ ██████╗ ███╗   ██╗███████╗████████╗███████╗██████╗ ███████╗        
████╗ ████║██╔═══██╗████╗  ██║██╔════╝╚══██╔══╝██╔════╝██╔══██╗██╔════╝        
██╔████╔██║██║   ██║██╔██╗ ██║███████╗   ██║   █████╗  ██████╔╝███████╗        
██║╚██╔╝██║██║   ██║██║╚██╗██║╚════██║   ██║   ██╔══╝  ██╔══██╗╚════██║        
██║ ╚═╝ ██║╚██████╔╝██║ ╚████║███████║   ██║   ███████╗██║  ██║███████║        
╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚══════╝   ╚═╝   ╚══════╝╚═╝  ╚═╝╚══════╝        
                                                                               
 █████╗ ███╗   ██╗██████╗     ██╗  ██╗███████╗██████╗  ██████╗ ███████╗███████╗
██╔══██╗████╗  ██║██╔══██╗    ██║  ██║██╔════╝██╔══██╗██╔═══██╗██╔════╝██╔════╝
███████║██╔██╗ ██║██║  ██║    ███████║█████╗  ██████╔╝██║   ██║█████╗  ███████╗
██╔══██║██║╚██╗██║██║  ██║    ██╔══██║██╔══╝  ██╔══██╗██║   ██║██╔══╝  ╚════██║
██║  ██║██║ ╚████║██████╔╝    ██║  ██║███████╗██║  ██║╚██████╔╝███████╗███████║
╚═╝  ╚═╝╚═╝  ╚═══╝╚═════╝     ╚═╝  ╚═╝╚══════╝╚═╝  ╚═╝ ╚═════╝ ╚══════╝╚══════╝















Enter your name: 

Input:
oktay



Output:
Enter the hero count (1-3): 

Input:
2

Output:
Select your hero 1(1: Warrior, 2: Sorcerer, 3: Paladin): 

Input:
2

Output:
Sorcerer:3

Input:
1

Output:

Welcome to Legends: Monsters and Heroes, oktay!
Prepare yourself for new adventures and challenges ahead!
Your game will start shotly with following heroes

                               ____ 
                                .'* *.'
                             __/_*_*(_
                            / _______ \
                           _\_)/___\(_/_ 
                          / _((\- -/))_ \
                          \ \())(-)(()/ /
                           ' \(((()))/ '
                          / ' \)).))/ ' \
                         / _ \ - | - /_  \
                        (   ( .;''';. .'  )
                        _"__ /    )\ __"/_
                          \/  \   ' /  \/
                           .'  '...' ' )
                            / /  |  \ \
                           / .   .   . \
                          /   .     .   \
                         /   /   |   \   \
                       .'   /    b    '.  '
                   _.-'    /     Bb     '-. '
                          |      BBb       '-. 
                   _______\____.dBBBb.________)

              Hero 1: Rillifane_Rallathil I








                             {}
                            .--.
                           /.--.\
                           |====|
                           |`::`|
                       .-;`\..../`;_.-^-._
                      /  |...::..|`   :   `|
                     |   /'''::''|   .:.   |
                     ;--'\   ::  |..:::::..|
                     <__> >._::_.| ':::::' |
                     |  |/   ^^  |   ':'   |
                     \::/|       \    :    /
                     |||\|        \   :   /
                     ''' |___/\___|`-.:.-`
                          \_ || _/    `
                          <_ >< _>
                          |  ||  |
                          |  ||  |
                         _\.:||:./_
                        /____/\____\

              Hero 2: Sehanine_Monnbow II







I beg mercy to you and the heroes, cause monsters won't show any!

██╗     ███████╗ ██████╗ ███████╗███╗   ██╗██████╗ ███████╗       ███╗   ███╗      ██╗  ██╗        
██║     ██╔════╝██╔════╝ ██╔════╝████╗  ██║██╔══██╗██╔════╝██╗    ████╗ ████║      ██║  ██║        
██║     █████╗  ██║  ███╗█████╗  ██╔██╗ ██║██║  ██║███████╗╚═╝    ██╔████╔██║      ███████║        
██║     ██╔══╝  ██║   ██║██╔══╝  ██║╚██╗██║██║  ██║╚════██║██╗    ██║╚██╔╝██║ and  ██╔══██║        
███████╗███████╗╚██████╔╝███████╗██║ ╚████║██████╔╝███████║╚═╝    ██║ ╚═╝ ██║      ██║  ██║        
╚══════╝╚══════╝ ╚═════╝ ╚══════╝╚═╝  ╚═══╝╚═════╝ ╚══════╝       ╚═╝     ╚═╝      ╚═╝  ╚═╝        















+---+---+---+---+---+---+---+---+
| P |   | M | X | X | X | M |   |
+---+---+---+---+---+---+---+---+
|   |   | X |   |   | M | M | X |
+---+---+---+---+---+---+---+---+
| M |   |   | M |   |   |   | M |
+---+---+---+---+---+---+---+---+
|   |   |   | M |   | X |   | X |
+---+---+---+---+---+---+---+---+
| M |   |   | M |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | X | M |   | M |   |   | M |
+---+---+---+---+---+---+---+---+
| M |   |   |   |   |   | M |   |
+---+---+---+---+---+---+---+---+
| M |   |   |   | M |   |   |   |
+---+---+---+---+---+---+---+---+

Controls:
W/A/S/D - move
I/C - manage inventory (view info, equip/use items)
M - enter market (if on market tile)
Q - quit game
H - Help/Information
Your move > 

Input:
D



Output:
>
>
>
>
>
>
>
>
>
>

>--- The monsters seem to have retreated for now. You proceed with caution. ---



██╗     ███████╗ ██████╗ ███████╗███╗   ██╗██████╗ ███████╗       ███╗   ███╗      ██╗  ██╗        
██║     ██╔════╝██╔════╝ ██╔════╝████╗  ██║██╔══██╗██╔════╝██╗    ████╗ ████║      ██║  ██║        
██║     █████╗  ██║  ███╗█████╗  ██╔██╗ ██║██║  ██║███████╗╚═╝    ██╔████╔██║      ███████║        
██║     ██╔══╝  ██║   ██║██╔══╝  ██║╚██╗██║██║  ██║╚════██║██╗    ██║╚██╔╝██║ and  ██╔══██║        
███████╗███████╗╚██████╔╝███████╗██║ ╚████║██████╔╝███████║╚═╝    ██║ ╚═╝ ██║      ██║  ██║        
╚══════╝╚══════╝ ╚═════╝ ╚══════╝╚═╝  ╚═══╝╚═════╝ ╚══════╝       ╚═╝     ╚═╝      ╚═╝  ╚═╝        















+---+---+---+---+---+---+---+---+
|   | P | M | X | X | X | M |   |
+---+---+---+---+---+---+---+---+
|   |   | X |   |   | M | M | X |
+---+---+---+---+---+---+---+---+
| M |   |   | M |   |   |   | M |
+---+---+---+---+---+---+---+---+
|   |   |   | M |   | X |   | X |
+---+---+---+---+---+---+---+---+
| M |   |   | M |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | X | M |   | M |   |   | M |
+---+---+---+---+---+---+---+---+
| M |   |   |   |   |   | M |   |
+---+---+---+---+---+---+---+---+
| M |   |   |   | M |   |   |   |
+---+---+---+---+---+---+---+---+

Controls:
W/A/S/D - move
I/C - manage inventory (view info, equip/use items)
M - enter market (if on market tile)
Q - quit game
H - Help/Information
Your move > 

Input:
D

Output:
██╗     ███████╗ ██████╗ ███████╗███╗   ██╗██████╗ ███████╗       ███╗   ███╗      ██╗  ██╗        
██║     ██╔════╝██╔════╝ ██╔════╝████╗  ██║██╔══██╗██╔════╝██╗    ████╗ ████║      ██║  ██║        
██║     █████╗  ██║  ███╗█████╗  ██╔██╗ ██║██║  ██║███████╗╚═╝    ██╔████╔██║      ███████║        
██║     ██╔══╝  ██║   ██║██╔══╝  ██║╚██╗██║██║  ██║╚════██║██╗    ██║╚██╔╝██║ and  ██╔══██║        
███████╗███████╗╚██████╔╝███████╗██║ ╚████║██████╔╝███████║╚═╝    ██║ ╚═╝ ██║      ██║  ██║        
╚══════╝╚══════╝ ╚═════╝ ╚══════╝╚═╝  ╚═══╝╚═════╝ ╚══════╝       ╚═╝     ╚═╝      ╚═╝  ╚═╝        















+---+---+---+---+---+---+---+---+
|   |   | P | X | X | X | M |   |
+---+---+---+---+---+---+---+---+
|   |   | X |   |   | M | M | X |
+---+---+---+---+---+---+---+---+
| M |   |   | M |   |   |   | M |
+---+---+---+---+---+---+---+---+
|   |   |   | M |   | X |   | X |
+---+---+---+---+---+---+---+---+
| M |   |   | M |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | X | M |   | M |   |   | M |
+---+---+---+---+---+---+---+---+
| M |   |   |   |   |   | M |   |
+---+---+---+---+---+---+---+---+
| M |   |   |   | M |   |   |   |
+---+---+---+---+---+---+---+---+

Controls:
W/A/S/D - move
I/C - manage inventory (view info, equip/use items)
M - enter market (if on market tile)
Q - quit game
H - Help/Information
Your move > 

Input:
M

Output:

+-+-+-+-+-+-+         +-+-+-+-+-+-+        +-+-+-+-+-+-+        +-+-+-+-+-+-+       +-+-+-+-+-+-+
|M|a|r|k|e|t|         |M|a|r|k|e|t|        |M|a|r|k|e|t|        |M|a|r|k|e|t|       |M|a|r|k|e|t|
+-+-+-+-+-+-+         +-+-+-+-+-+-+        +-+-+-+-+-+-+        +-+-+-+-+-+-+       +-+-+-+-+-+-+
=====================================================================================================
Welcome to the Market! Here you can buy and sell items to enhance your heroes.
=================================================================================================
-- Primary Stock --
[1] [Potion] Iron_Tonic (Price: 650, Level: 5, Effect Type: Defense/Strength, Effect Amount: 90)
[2] [Weapon] Hammer (10/10) (Price: 600, Level: 4, Damage: 900, Hands: 2)
[3] [Spell] Chain_Lightning (Price: 800, Level: 7, Spell Type: Lightning, Damage: 1000, Mana Cost: 520)
[4] [Spell] Electric_Cyclone (Price: 800, Level: 7, Spell Type: Lightning, Damage: 1100, Mana Cost: 560)
[5] [Weapon] Saber (10/10) (Price: 550, Level: 4, Damage: 750, Hands: 1)
[6] [Weapon] Butcher_Knife (10/10) (Price: 130, Level: 1, Damage: 250, Hands: 1)
[7] [Weapon] Garden_Shears (10/10) (Price: 120, Level: 1, Damage: 240, Hands: 1)
[8] [Potion] Primal_Essence (Price: 950, Level: 8, Effect Type: Strength/Health/Defense, Effect Amount: 140)
[9] [Potion] Berserker_Brew (Price: 550, Level: 4, Effect Type: Strength/Agility, Effect Amount: 90)
[10] [Armor] Phantom_Armor (10/10) (Price: 560, Level: 5, Damage Reduction: 810)
Total items listed: 10
















Market Controls:
I - Show Hero Info
B - Buy item
S - Sell item
R - Repair broken equipment
E - Exit market
Q - quit game
Your choice >

Input:

B

Output:
Select hero to buy for:
(1) Rillifane_Rallathil I [Level: 1, Gold: 2500]
(2) Sehanine_Monnbow II [Level: 1, Gold: 2500]
Hero number: 

Input:

1

Output:

Hero Gold Budget: 2500
Enter item index to buy: 

Input:

5

Output:

Hero level too low for this item.
Purchase failed.


+-+-+-+-+-+-+         +-+-+-+-+-+-+        +-+-+-+-+-+-+        +-+-+-+-+-+-+       +-+-+-+-+-+-+
|M|a|r|k|e|t|         |M|a|r|k|e|t|        |M|a|r|k|e|t|        |M|a|r|k|e|t|       |M|a|r|k|e|t|
+-+-+-+-+-+-+         +-+-+-+-+-+-+        +-+-+-+-+-+-+        +-+-+-+-+-+-+       +-+-+-+-+-+-+
=====================================================================================================
Welcome to the Market! Here you can buy and sell items to enhance your heroes.
=================================================================================================
-- Primary Stock --
[1] [Potion] Iron_Tonic (Price: 650, Level: 5, Effect Type: Defense/Strength, Effect Amount: 90)
[2] [Weapon] Hammer (10/10) (Price: 600, Level: 4, Damage: 900, Hands: 2)
[3] [Spell] Chain_Lightning (Price: 800, Level: 7, Spell Type: Lightning, Damage: 1000, Mana Cost: 520)
[4] [Spell] Electric_Cyclone (Price: 800, Level: 7, Spell Type: Lightning, Damage: 1100, Mana Cost: 560)
[5] [Weapon] Saber (10/10) (Price: 550, Level: 4, Damage: 750, Hands: 1)
[6] [Weapon] Butcher_Knife (10/10) (Price: 130, Level: 1, Damage: 250, Hands: 1)
[7] [Weapon] Garden_Shears (10/10) (Price: 120, Level: 1, Damage: 240, Hands: 1)
[8] [Potion] Primal_Essence (Price: 950, Level: 8, Effect Type: Strength/Health/Defense, Effect Amount: 140)
[9] [Potion] Berserker_Brew (Price: 550, Level: 4, Effect Type: Strength/Agility, Effect Amount: 90)
[10] [Armor] Phantom_Armor (10/10) (Price: 560, Level: 5, Damage Reduction: 810)
Total items listed: 10
















Market Controls:
I - Show Hero Info
B - Buy item
S - Sell item
R - Repair broken equipment
E - Exit market
Q - quit game
Your choice >
Input:
B

Output:

Select hero to buy for:
(1) Rillifane_Rallathil I [Level: 1, Gold: 2500]
(2) Sehanine_Monnbow II [Level: 1, Gold: 2500]
Hero number: 

Input:

1

Output:
Hero Gold Budget: 2500
Enter item index to buy: 

Input:

6

Output:

6
Purchased: Butcher_Knife
Purchase successful.



Output:

+-+-+-+-+-+-+         +-+-+-+-+-+-+        +-+-+-+-+-+-+        +-+-+-+-+-+-+       +-+-+-+-+-+-+
|M|a|r|k|e|t|         |M|a|r|k|e|t|        |M|a|r|k|e|t|        |M|a|r|k|e|t|       |M|a|r|k|e|t|
+-+-+-+-+-+-+         +-+-+-+-+-+-+        +-+-+-+-+-+-+        +-+-+-+-+-+-+       +-+-+-+-+-+-+
=====================================================================================================
Welcome to the Market! Here you can buy and sell items to enhance your heroes.
=================================================================================================
-- Primary Stock --
[1] [Potion] Iron_Tonic (Price: 650, Level: 5, Effect Type: Defense/Strength, Effect Amount: 90)
[2] [Weapon] Hammer (10/10) (Price: 600, Level: 4, Damage: 900, Hands: 2)
[3] [Spell] Chain_Lightning (Price: 800, Level: 7, Spell Type: Lightning, Damage: 1000, Mana Cost: 520)
[4] [Spell] Electric_Cyclone (Price: 800, Level: 7, Spell Type: Lightning, Damage: 1100, Mana Cost: 560)
[5] [Weapon] Saber (10/10) (Price: 550, Level: 4, Damage: 750, Hands: 1)
[6] [Weapon] Butcher_Knife (10/10) (Price: 130, Level: 1, Damage: 250, Hands: 1)
[7] [Weapon] Garden_Shears (10/10) (Price: 120, Level: 1, Damage: 240, Hands: 1)
[8] [Potion] Primal_Essence (Price: 950, Level: 8, Effect Type: Strength/Health/Defense, Effect Amount: 140)
[9] [Potion] Berserker_Brew (Price: 550, Level: 4, Effect Type: Strength/Agility, Effect Amount: 90)
[10] [Armor] Phantom_Armor (10/10) (Price: 560, Level: 5, Damage Reduction: 810)
Total items listed: 10
















Market Controls:
I - Show Hero Info
B - Buy item
S - Sell item
R - Repair broken equipment
E - Exit market
Q - quit game

Input:

E


Output:


██╗     ███████╗ ██████╗ ███████╗███╗   ██╗██████╗ ███████╗       ███╗   ███╗      ██╗  ██╗        
██║     ██╔════╝██╔════╝ ██╔════╝████╗  ██║██╔══██╗██╔════╝██╗    ████╗ ████║      ██║  ██║        
██║     █████╗  ██║  ███╗█████╗  ██╔██╗ ██║██║  ██║███████╗╚═╝    ██╔████╔██║      ███████║        
██║     ██╔══╝  ██║   ██║██╔══╝  ██║╚██╗██║██║  ██║╚════██║██╗    ██║╚██╔╝██║ and  ██╔══██║        
███████╗███████╗╚██████╔╝███████╗██║ ╚████║██████╔╝███████║╚═╝    ██║ ╚═╝ ██║      ██║  ██║        
╚══════╝╚══════╝ ╚═════╝ ╚══════╝╚═╝  ╚═══╝╚═════╝ ╚══════╝       ╚═╝     ╚═╝      ╚═╝  ╚═╝        















+---+---+---+---+---+---+---+---+
|   |   | P | X | X | X | M |   |
+---+---+---+---+---+---+---+---+
|   |   | X |   |   | M | M | X |
+---+---+---+---+---+---+---+---+
| M |   |   | M |   |   |   | M |
+---+---+---+---+---+---+---+---+
|   |   |   | M |   | X |   | X |
+---+---+---+---+---+---+---+---+
| M |   |   | M |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | X | M |   | M |   |   | M |
+---+---+---+---+---+---+---+---+
| M |   |   |   |   |   | M |   |
+---+---+---+---+---+---+---+---+
| M |   |   |   | M |   |   |   |
+---+---+---+---+---+---+---+---+

Controls:
W/A/S/D - move
I/C - manage inventory (view info, equip/use items)
M - enter market (if on market tile)
Q - quit game
H - Help/Information
Your move > 

Input:

a

Output:

>--- Looks like you are very good at your job, no monsters here! ---











██╗     ███████╗ ██████╗ ███████╗███╗   ██╗██████╗ ███████╗       ███╗   ███╗      ██╗  ██╗        
██║     ██╔════╝██╔════╝ ██╔════╝████╗  ██║██╔══██╗██╔════╝██╗    ████╗ ████║      ██║  ██║        
██║     █████╗  ██║  ███╗█████╗  ██╔██╗ ██║██║  ██║███████╗╚═╝    ██╔████╔██║      ███████║        
██║     ██╔══╝  ██║   ██║██╔══╝  ██║╚██╗██║██║  ██║╚════██║██╗    ██║╚██╔╝██║ and  ██╔══██║        
███████╗███████╗╚██████╔╝███████╗██║ ╚████║██████╔╝███████║╚═╝    ██║ ╚═╝ ██║      ██║  ██║        
╚══════╝╚══════╝ ╚═════╝ ╚══════╝╚═╝  ╚═══╝╚═════╝ ╚══════╝       ╚═╝     ╚═╝      ╚═╝  ╚═╝        








+---+---+---+---+---+---+---+---+
|   | P | M | X | X | X | M |   |
+---+---+---+---+---+---+---+---+
|   |   | X |   |   | M | M | X |
+---+---+---+---+---+---+---+---+
| M |   |   | M |   |   |   | M |
+---+---+---+---+---+---+---+---+
|   |   |   | M |   | X |   | X |
+---+---+---+---+---+---+---+---+
| M |   |   | M |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | X | M |   | M |   |   | M |
+---+---+---+---+---+---+---+---+
| M |   |   |   |   |   | M |   |
+---+---+---+---+---+---+---+---+
| M |   |   |   | M |   |   |   |
+---+---+---+---+---+---+---+---+

Controls:
W/A/S/D - move
I/C - manage inventory (view info, equip/use items)
M - enter market (if on market tile)
Q - quit game
H - Help/Information
Your move > 

Input:

S

Output:


>--- Looks like you are very good at your job, no monsters here! ---











██╗     ███████╗ ██████╗ ███████╗███╗   ██╗██████╗ ███████╗       ███╗   ███╗      ██╗  ██╗        
██║     ██╔════╝██╔════╝ ██╔════╝████╗  ██║██╔══██╗██╔════╝██╗    ████╗ ████║      ██║  ██║        
██║     █████╗  ██║  ███╗█████╗  ██╔██╗ ██║██║  ██║███████╗╚═╝    ██╔████╔██║      ███████║        
██║     ██╔══╝  ██║   ██║██╔══╝  ██║╚██╗██║██║  ██║╚════██║██╗    ██║╚██╔╝██║ and  ██╔══██║        
███████╗███████╗╚██████╔╝███████╗██║ ╚████║██████╔╝███████║╚═╝    ██║ ╚═╝ ██║      ██║  ██║        
╚══════╝╚══════╝ ╚═════╝ ╚══════╝╚═╝  ╚═══╝╚═════╝ ╚══════╝       ╚═╝     ╚═╝      ╚═╝  ╚═╝        








+---+---+---+---+---+---+---+---+
|   |   | M | X | X | X | M |   |
+---+---+---+---+---+---+---+---+
|   | P | X |   |   | M | M | X |
+---+---+---+---+---+---+---+---+
| M |   |   | M |   |   |   | M |
+---+---+---+---+---+---+---+---+
|   |   |   | M |   | X |   | X |
+---+---+---+---+---+---+---+---+
| M |   |   | M |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | X | M |   | M |   |   | M |
+---+---+---+---+---+---+---+---+
| M |   |   |   |   |   | M |   |
+---+---+---+---+---+---+---+---+
| M |   |   |   | M |   |   |   |
+---+---+---+---+---+---+---+---+

Controls:
W/A/S/D - move
I/C - manage inventory (view info, equip/use items)
M - enter market (if on market tile)
Q - quit game
H - Help/Information
Your move > 


Input:

I


Output:

===============================================================================================
                         ===      ==      ==  ========  =========             
                          =       == ==   ==  ==        ==     ==   
                          =       ==  ==  ==  ======    ==     ==         
                          =       ==   == ==  ==        ==     ==  
                         ===      ==      ==  ==        =========                
===============================================================================================
===== HERO: Rillifane_Rallathil I =====
Class: Sorcerer
Level: 1
Health Points (HP): 100
Mana Points (MP): 1300
Strength: 750
Agility: 450
Dexterity: 500
Experience: 0
Gold: 2370
Equipped Weapon: Scythe (2H) (DMG:1100)
Equipped Armor:  None
Inventory:
  - Scythe x1
  - Butcher_Knife x1
=====================================
===== HERO: Sehanine_Monnbow II =====
Class: Warrior
Level: 1
Health Points (HP): 100
Mana Points (MP): 600
Strength: 700
Agility: 800
Dexterity: 500
Experience: 0
Gold: 2500
Equipped Weapon: Axe (1H) (DMG:850)
Equipped Armor:  None
Inventory:
  - Axe x1
=====================================

===== INVENTORY MENU =====
Select a hero to manage:
(1) Rillifane_Rallathil I [Level: 1, HP: 100, MP: 1300]
(2) Sehanine_Monnbow II [Level: 1, HP: 100, MP: 600]
(E) Exit inventory
(Q) Quit game
Your choice: 

Input:

1

Output:

===== Rillifane_Rallathil I's Inventory =====
(1) Equip Weapon
(2) Equip Armor
(3) Use Potion
(4) View Full Info
(E) Back to hero selection
(Q) Quit game
Your choice: 

Input:

1

Output:

--- Available Weapons ---
(1) Scythe - Damage: 1100, Hands: 2 [EQUIPPED]
(2) Butcher_Knife - Damage: 250, Hands: 1
(0) Cancel

Input:

22

Output:

Please enter a number between 0 and 2: 

Input:

2


Output:

Rillifane_Rallathil I equipped Butcher_Knife!

===== Rillifane_Rallathil I's Inventory =====
(1) Equip Weapon
(2) Equip Armor
(3) Use Potion
(4) View Full Info
(E) Back to hero selection
(Q) Quit game
Your choice: 

Input:

E


Output:

===============================================================================================
                         ===      ==      ==  ========  =========             
                          =       == ==   ==  ==        ==     ==   
                          =       ==  ==  ==  ======    ==     ==         
                          =       ==   == ==  ==        ==     ==  
                         ===      ==      ==  ==        =========                
===============================================================================================
===== HERO: Rillifane_Rallathil I =====
Class: Sorcerer
Level: 1
Health Points (HP): 100
Mana Points (MP): 1300
Strength: 750
Agility: 450
Dexterity: 500
Experience: 0
Gold: 2370
Equipped Weapon: Butcher_Knife (1H, using 2H) (DMG:250)
Equipped Armor:  None
Inventory:
  - Scythe x1
  - Butcher_Knife x1
=====================================
===== HERO: Sehanine_Monnbow II =====
Class: Warrior
Level: 1
Health Points (HP): 100
Mana Points (MP): 600
Strength: 700
Agility: 800
Dexterity: 500
Experience: 0
Gold: 2500
Equipped Weapon: Axe (1H) (DMG:850)
Equipped Armor:  None
Inventory:
  - Axe x1
=====================================

===== INVENTORY MENU =====
Select a hero to manage:
(1) Rillifane_Rallathil I [Level: 1, HP: 100, MP: 1300]
(2) Sehanine_Monnbow II [Level: 1, HP: 100, MP: 600]
(E) Exit inventory
(Q) Quit game
Your choice: 

Input:

E



Output:

(I fast forward a bit here )

Your move > a
>
>
>
>
>
>
>
>
>
>

>--- Looks like you are very good at your job, no monsters here! ---





































































































██╗     ███████╗ ██████╗ ███████╗███╗   ██╗██████╗ ███████╗       ███╗   ███╗      ██╗  ██╗        
██║     ██╔════╝██╔════╝ ██╔════╝████╗  ██║██╔══██╗██╔════╝██╗    ████╗ ████║      ██║  ██║        
██║     █████╗  ██║  ███╗█████╗  ██╔██╗ ██║██║  ██║███████╗╚═╝    ██╔████╔██║      ███████║        
██║     ██╔══╝  ██║   ██║██╔══╝  ██║╚██╗██║██║  ██║╚════██║██╗    ██║╚██╔╝██║ and  ██╔══██║        
███████╗███████╗╚██████╔╝███████╗██║ ╚████║██████╔╝███████║╚═╝    ██║ ╚═╝ ██║      ██║  ██║        
╚══════╝╚══════╝ ╚═════╝ ╚══════╝╚═╝  ╚═══╝╚═════╝ ╚══════╝       ╚═╝     ╚═╝      ╚═╝  ╚═╝        















+---+---+---+---+---+---+---+---+
|   |   | M | X | X | X | M |   |
+---+---+---+---+---+---+---+---+
|   |   | X |   |   | M | M | X |
+---+---+---+---+---+---+---+---+
| M |   |   | M |   |   |   | M |
+---+---+---+---+---+---+---+---+
|   |   |   | M |   | X |   | X |
+---+---+---+---+---+---+---+---+
| M | P |   | M |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | X | M |   | M |   |   | M |
+---+---+---+---+---+---+---+---+
| M |   |   |   |   |   | M |   |
+---+---+---+---+---+---+---+---+
| M |   |   |   | M |   |   |   |
+---+---+---+---+---+---+---+---+

Controls:
W/A/S/D - move
I/C - manage inventory (view info, equip/use items)
M - enter market (if on market tile)
Q - quit game
H - Help/Information
Your move > 

Input:
H

Output:

╔════════════════════════════════════════════════════════════╗
║          LEGENDS: MONSTERS AND HEROES - HELP               ║
╚════════════════════════════════════════════════════════════╝

=== OBJECTIVE ===
Battle monsters, gain experience, level up, and survive!

=== WORLD CONTROLS ===
  W/w - Move Up
  A/a - Move Left
  S/s - Move Down
  D/d - Move Right
  I/i - View party info & manage inventory
  C/c - Character/inventory menu
  M/m - Enter market (only on market tiles)
  H/h - Display this help
  Q/q - Quit game

=== TILE TYPES ===
  P - Your party
  M - Market (buy/sell items)
  X - Inaccessible
  (space) - Common (random battle chance)

=== BATTLE ACTIONS ===
  A - Attack with equipped weapon
  S - Cast spell (consumes MP and spell)
  P - Use potion (HP/MP/stat boost)
  E - Equip weapon or armor
  I - View battle info

=== HERO CLASSES ===
  Warrior  - Favors Strength & Agility
  Sorcerer - Favors Dexterity & Agility
  Paladin  - Favors Strength & Dexterity

=== LEVEL UP ===
  - Requires EXP = current_level × 10
  - All stats increase by 5%
  - Favored stats increase by additional 5%
  - HP resets to level × 100
  - MP increases by 10%

=== TIPS ===
  - Buy equipment at markets before battles
  - Use spells for damage + debuffs on monsters
  - Manage inventory (I/C) to equip items outside battle
  - Heroes regenerate 10% HP/MP each battle round
  - Fainted heroes revive with half HP/MP after victory

Press Enter to continue..
Input:
enter


Output:

I fast forward a bit more then this happens:
>
>
>
>
>
>
>
>
>
>

>--- OH NO! Monsters here! Battle starts now! ---





































































































=====================================================================================================================================================
 ▄▄▄▄▄▄▄▄▄▄   ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄            ▄▄▄▄▄▄▄▄▄▄▄      ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄        ▄  ▄▄▄▄▄▄▄▄▄▄▄ 
▐░░░░░░░░░░▌ ▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░▌          ▐░░░░░░░░░░░▌    ▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░▌      ▐░▌▐░░░░░░░░░░░▌
▐░█▀▀▀▀▀▀▀█░▌▐░█▀▀▀▀▀▀▀█░▌ ▀▀▀▀█░█▀▀▀▀  ▀▀▀▀█░█▀▀▀▀ ▐░▌          ▐░█▀▀▀▀▀▀▀▀▀     ▐░█▀▀▀▀▀▀▀█░▌▐░█▀▀▀▀▀▀▀█░▌▐░█▀▀▀▀▀▀▀▀▀ ▐░▌░▌     ▐░▌▐░█▀▀▀▀▀▀▀█░▌
▐░▌       ▐░▌▐░▌       ▐░▌     ▐░▌          ▐░▌     ▐░▌          ▐░▌              ▐░▌       ▐░▌▐░▌       ▐░▌▐░▌          ▐░▌▐░▌    ▐░▌▐░▌       ▐░▌
▐░█▄▄▄▄▄▄▄█░▌▐░█▄▄▄▄▄▄▄█░▌     ▐░▌          ▐░▌     ▐░▌          ▐░█▄▄▄▄▄▄▄▄▄     ▐░█▄▄▄▄▄▄▄█░▌▐░█▄▄▄▄▄▄▄█░▌▐░█▄▄▄▄▄▄▄▄▄ ▐░▌ ▐░▌   ▐░▌▐░█▄▄▄▄▄▄▄█░▌
▐░░░░░░░░░░▌ ▐░░░░░░░░░░░▌     ▐░▌          ▐░▌     ▐░▌          ▐░░░░░░░░░░░▌    ▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░▌  ▐░▌  ▐░▌▐░░░░░░░░░░░▌
▐░█▀▀▀▀▀▀▀█░▌▐░█▀▀▀▀▀▀▀█░▌     ▐░▌          ▐░▌     ▐░▌          ▐░█▀▀▀▀▀▀▀▀▀     ▐░█▀▀▀▀▀▀▀█░▌▐░█▀▀▀▀█░█▀▀ ▐░█▀▀▀▀▀▀▀▀▀ ▐░▌   ▐░▌ ▐░▌▐░█▀▀▀▀▀▀▀█░▌
▐░▌       ▐░▌▐░▌       ▐░▌     ▐░▌          ▐░▌     ▐░▌          ▐░▌              ▐░▌       ▐░▌▐░▌     ▐░▌  ▐░▌          ▐░▌    ▐░▌▐░▌▐░▌       ▐░▌
▐░█▄▄▄▄▄▄▄█░▌▐░▌       ▐░▌     ▐░▌          ▐░▌     ▐░█▄▄▄▄▄▄▄▄▄ ▐░█▄▄▄▄▄▄▄▄▄     ▐░▌       ▐░▌▐░▌      ▐░▌ ▐░█▄▄▄▄▄▄▄▄▄ ▐░▌     ▐░▐░▌▐░▌       ▐░▌
▐░░░░░░░░░░▌ ▐░▌       ▐░▌     ▐░▌          ▐░▌     ▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌    ▐░▌       ▐░▌▐░▌       ▐░▌▐░░░░░░░░░░░▌▐░▌      ▐░░▌▐░▌       ▐░▌
 ▀▀▀▀▀▀▀▀▀▀   ▀         ▀       ▀            ▀       ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀      ▀         ▀  ▀         ▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀        ▀▀  ▀         ▀ 
=====================================================================================================================================================















HEROES                                                                Round {1}                                                                   MONSTERS
=====================================================================================================================================================
Rillifane_Rallathil I (HP: 100, MP: 1300)                                                              X                                           Phantom_Wraith (HP: 100)                                                          
Sehanine_Monnbow II (HP: 100, MP: 600)                                                                 X                                           BigBad-Wolf (HP: 100)                                                             

--- Heroes' Turn ---
Action for Rillifane_Rallathil I (A=Attack, S=Spell, P=Potion, E=Equip, I=Info, Q=Quit): 


--- Heroes' Turn ---
Action for Rillifane_Rallathil I (A=Attack, S=Spell, P=Potion, E=Equip, I=Info, Q=Quit): a
Select target monster:
(1) Phantom_Wraith HP:100
(2) BigBad-Wolf HP:100
Input : 2
>
>
>
>
>
>
>
>
>
>

>--- >Rillifane_Rallathil I hits BigBad-Wolf for 63 damage.
 ---

Action for Sehanine_Monnbow II (A=Attack, S=Spell, P=Potion, E=Equip, I=Info, Q=Quit): 
Input : S
No spells in inventory.

--- Monsters' Turn ---
>
>
>
>
>
>
>
>
>
>

>--- Phantom_Wraith hits Sehanine_Monnbow II for 110 damage. ---

>--- Sehanine_Monnbow II has fainted! ---

>
>
>
>
>
>
>
>
>
>

>--- BigBad-Wolf hits Rillifane_Rallathil I for 55 damage. ---

>End of round regeneration applied. Round now 2

--- Monsters' Turn ---
>
>
>
>
>
>
>
>
>
>

>--- Rillifane_Rallathil I dodged Phantom_Wraith's attack! ---

>End of round regeneration applied. Round now 3





































































































=====================================================================================================================================================
 ▄▄▄▄▄▄▄▄▄▄   ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄            ▄▄▄▄▄▄▄▄▄▄▄      ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄        ▄  ▄▄▄▄▄▄▄▄▄▄▄ 
▐░░░░░░░░░░▌ ▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░▌          ▐░░░░░░░░░░░▌    ▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░▌      ▐░▌▐░░░░░░░░░░░▌
▐░█▀▀▀▀▀▀▀█░▌▐░█▀▀▀▀▀▀▀█░▌ ▀▀▀▀█░█▀▀▀▀  ▀▀▀▀█░█▀▀▀▀ ▐░▌          ▐░█▀▀▀▀▀▀▀▀▀     ▐░█▀▀▀▀▀▀▀█░▌▐░█▀▀▀▀▀▀▀█░▌▐░█▀▀▀▀▀▀▀▀▀ ▐░▌░▌     ▐░▌▐░█▀▀▀▀▀▀▀█░▌
▐░▌       ▐░▌▐░▌       ▐░▌     ▐░▌          ▐░▌     ▐░▌          ▐░▌              ▐░▌       ▐░▌▐░▌       ▐░▌▐░▌          ▐░▌▐░▌    ▐░▌▐░▌       ▐░▌
▐░█▄▄▄▄▄▄▄█░▌▐░█▄▄▄▄▄▄▄█░▌     ▐░▌          ▐░▌     ▐░▌          ▐░█▄▄▄▄▄▄▄▄▄     ▐░█▄▄▄▄▄▄▄█░▌▐░█▄▄▄▄▄▄▄█░▌▐░█▄▄▄▄▄▄▄▄▄ ▐░▌ ▐░▌   ▐░▌▐░█▄▄▄▄▄▄▄█░▌
▐░░░░░░░░░░▌ ▐░░░░░░░░░░░▌     ▐░▌          ▐░▌     ▐░▌          ▐░░░░░░░░░░░▌    ▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░▌  ▐░▌  ▐░▌▐░░░░░░░░░░░▌
▐░█▀▀▀▀▀▀▀█░▌▐░█▀▀▀▀▀▀▀█░▌     ▐░▌          ▐░▌     ▐░▌          ▐░█▀▀▀▀▀▀▀▀▀     ▐░█▀▀▀▀▀▀▀█░▌▐░█▀▀▀▀█░█▀▀ ▐░█▀▀▀▀▀▀▀▀▀ ▐░▌   ▐░▌ ▐░▌▐░█▀▀▀▀▀▀▀█░▌
▐░▌       ▐░▌▐░▌       ▐░▌     ▐░▌          ▐░▌     ▐░▌          ▐░▌              ▐░▌       ▐░▌▐░▌     ▐░▌  ▐░▌          ▐░▌    ▐░▌▐░▌▐░▌       ▐░▌
▐░█▄▄▄▄▄▄▄█░▌▐░▌       ▐░▌     ▐░▌          ▐░▌     ▐░█▄▄▄▄▄▄▄▄▄ ▐░█▄▄▄▄▄▄▄▄▄     ▐░▌       ▐░▌▐░▌      ▐░▌ ▐░█▄▄▄▄▄▄▄▄▄ ▐░▌     ▐░▐░▌▐░▌       ▐░▌
▐░░░░░░░░░░▌ ▐░▌       ▐░▌     ▐░▌          ▐░▌     ▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌    ▐░▌       ▐░▌▐░▌       ▐░▌▐░░░░░░░░░░░▌▐░▌      ▐░░▌▐░▌       ▐░▌
 ▀▀▀▀▀▀▀▀▀▀   ▀         ▀       ▀            ▀       ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀      ▀         ▀  ▀         ▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀        ▀▀  ▀         ▀ 
=====================================================================================================================================================















HEROES                                                                Round {3}                                                                   MONSTERS
=====================================================================================================================================================
Rillifane_Rallathil I (HP: 55, MP: 1573)                                                               X                                           Phantom_Wraith (HP: 100)                                                          
Sehanine_Monnbow II (HP: 0, MP: 600)                                                                   X                                           BigBad-Wolf (HP: 0)                                                               

--- Heroes' Turn ---
Action for Rillifane_Rallathil I (A=Attack, S=Spell, P=Potion, E=Equip, I=Info, Q=Quit): 


--- Monsters' Turn ---
>
>
>
>
>
>
>
>
>
>

>--- Rillifane_Rallathil I dodged Phantom_Wraith's attack! ---

>End of round regeneration applied. Round now 3





































































































=====================================================================================================================================================
 ▄▄▄▄▄▄▄▄▄▄   ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄            ▄▄▄▄▄▄▄▄▄▄▄      ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄        ▄  ▄▄▄▄▄▄▄▄▄▄▄ 
▐░░░░░░░░░░▌ ▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░▌          ▐░░░░░░░░░░░▌    ▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░▌      ▐░▌▐░░░░░░░░░░░▌
▐░█▀▀▀▀▀▀▀█░▌▐░█▀▀▀▀▀▀▀█░▌ ▀▀▀▀█░█▀▀▀▀  ▀▀▀▀█░█▀▀▀▀ ▐░▌          ▐░█▀▀▀▀▀▀▀▀▀     ▐░█▀▀▀▀▀▀▀█░▌▐░█▀▀▀▀▀▀▀█░▌▐░█▀▀▀▀▀▀▀▀▀ ▐░▌░▌     ▐░▌▐░█▀▀▀▀▀▀▀█░▌
▐░▌       ▐░▌▐░▌       ▐░▌     ▐░▌          ▐░▌     ▐░▌          ▐░▌              ▐░▌       ▐░▌▐░▌       ▐░▌▐░▌          ▐░▌▐░▌    ▐░▌▐░▌       ▐░▌
▐░█▄▄▄▄▄▄▄█░▌▐░█▄▄▄▄▄▄▄█░▌     ▐░▌          ▐░▌     ▐░▌          ▐░█▄▄▄▄▄▄▄▄▄     ▐░█▄▄▄▄▄▄▄█░▌▐░█▄▄▄▄▄▄▄█░▌▐░█▄▄▄▄▄▄▄▄▄ ▐░▌ ▐░▌   ▐░▌▐░█▄▄▄▄▄▄▄█░▌
▐░░░░░░░░░░▌ ▐░░░░░░░░░░░▌     ▐░▌          ▐░▌     ▐░▌          ▐░░░░░░░░░░░▌    ▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░▌  ▐░▌  ▐░▌▐░░░░░░░░░░░▌
▐░█▀▀▀▀▀▀▀█░▌▐░█▀▀▀▀▀▀▀█░▌     ▐░▌          ▐░▌     ▐░▌          ▐░█▀▀▀▀▀▀▀▀▀     ▐░█▀▀▀▀▀▀▀█░▌▐░█▀▀▀▀█░█▀▀ ▐░█▀▀▀▀▀▀▀▀▀ ▐░▌   ▐░▌ ▐░▌▐░█▀▀▀▀▀▀▀█░▌
▐░▌       ▐░▌▐░▌       ▐░▌     ▐░▌          ▐░▌     ▐░▌          ▐░▌              ▐░▌       ▐░▌▐░▌     ▐░▌  ▐░▌          ▐░▌    ▐░▌▐░▌▐░▌       ▐░▌
▐░█▄▄▄▄▄▄▄█░▌▐░▌       ▐░▌     ▐░▌          ▐░▌     ▐░█▄▄▄▄▄▄▄▄▄ ▐░█▄▄▄▄▄▄▄▄▄     ▐░▌       ▐░▌▐░▌      ▐░▌ ▐░█▄▄▄▄▄▄▄▄▄ ▐░▌     ▐░▐░▌▐░▌       ▐░▌
▐░░░░░░░░░░▌ ▐░▌       ▐░▌     ▐░▌          ▐░▌     ▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌    ▐░▌       ▐░▌▐░▌       ▐░▌▐░░░░░░░░░░░▌▐░▌      ▐░░▌▐░▌       ▐░▌
 ▀▀▀▀▀▀▀▀▀▀   ▀         ▀       ▀            ▀       ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀      ▀         ▀  ▀         ▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀        ▀▀  ▀         ▀ 
=====================================================================================================================================================















HEROES                                                                Round {3}                                                                   MONSTERS
=====================================================================================================================================================
Rillifane_Rallathil I (HP: 55, MP: 1573)                                                               X                                           Phantom_Wraith (HP: 100)                                                          
Sehanine_Monnbow II (HP: 0, MP: 600)                                                                   X                                           BigBad-Wolf (HP: 0)                                                               

--- Heroes' Turn ---
Action for Rillifane_Rallathil I (A=Attack, S=Spell, P=Potion, E=Equip, I=Info, Q=Quit): A
Select target monster:
(1) Phantom_Wraith HP:100
> 1
>
>
>
>
>
>
>
>
>
>

>--- >Rillifane_Rallathil I hits Phantom_Wraith for 63 damage.
 ---

--- Monsters' Turn ---
>
>
>
>
>
>
>
>
>
>

>--- Rillifane_Rallathil I dodged Phantom_Wraith's attack! ---

>End of round regeneration applied. Round now 4


=====================================================================================================================================================
 ▄▄▄▄▄▄▄▄▄▄   ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄            ▄▄▄▄▄▄▄▄▄▄▄      ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄        ▄  ▄▄▄▄▄▄▄▄▄▄▄ 
▐░░░░░░░░░░▌ ▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░▌          ▐░░░░░░░░░░░▌    ▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░▌      ▐░▌▐░░░░░░░░░░░▌
▐░█▀▀▀▀▀▀▀█░▌▐░█▀▀▀▀▀▀▀█░▌ ▀▀▀▀█░█▀▀▀▀  ▀▀▀▀█░█▀▀▀▀ ▐░▌          ▐░█▀▀▀▀▀▀▀▀▀     ▐░█▀▀▀▀▀▀▀█░▌▐░█▀▀▀▀▀▀▀█░▌▐░█▀▀▀▀▀▀▀▀▀ ▐░▌░▌     ▐░▌▐░█▀▀▀▀▀▀▀█░▌
▐░▌       ▐░▌▐░▌       ▐░▌     ▐░▌          ▐░▌     ▐░▌          ▐░▌              ▐░▌       ▐░▌▐░▌       ▐░▌▐░▌          ▐░▌▐░▌    ▐░▌▐░▌       ▐░▌
▐░█▄▄▄▄▄▄▄█░▌▐░█▄▄▄▄▄▄▄█░▌     ▐░▌          ▐░▌     ▐░▌          ▐░█▄▄▄▄▄▄▄▄▄     ▐░█▄▄▄▄▄▄▄█░▌▐░█▄▄▄▄▄▄▄█░▌▐░█▄▄▄▄▄▄▄▄▄ ▐░▌ ▐░▌   ▐░▌▐░█▄▄▄▄▄▄▄█░▌
▐░░░░░░░░░░▌ ▐░░░░░░░░░░░▌     ▐░▌          ▐░▌     ▐░▌          ▐░░░░░░░░░░░▌    ▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░▌  ▐░▌  ▐░▌▐░░░░░░░░░░░▌
▐░█▀▀▀▀▀▀▀█░▌▐░█▀▀▀▀▀▀▀█░▌     ▐░▌          ▐░▌     ▐░▌          ▐░█▀▀▀▀▀▀▀▀▀     ▐░█▀▀▀▀▀▀▀█░▌▐░█▀▀▀▀█░█▀▀ ▐░█▀▀▀▀▀▀▀▀▀ ▐░▌   ▐░▌ ▐░▌▐░█▀▀▀▀▀▀▀█░▌
▐░▌       ▐░▌▐░▌       ▐░▌     ▐░▌          ▐░▌     ▐░▌          ▐░▌              ▐░▌       ▐░▌▐░▌     ▐░▌  ▐░▌          ▐░▌    ▐░▌▐░▌▐░▌       ▐░▌
▐░█▄▄▄▄▄▄▄█░▌▐░▌       ▐░▌     ▐░▌          ▐░▌     ▐░█▄▄▄▄▄▄▄▄▄ ▐░█▄▄▄▄▄▄▄▄▄     ▐░▌       ▐░▌▐░▌      ▐░▌ ▐░█▄▄▄▄▄▄▄▄▄ ▐░▌     ▐░▐░▌▐░▌       ▐░▌
▐░░░░░░░░░░▌ ▐░▌       ▐░▌     ▐░▌          ▐░▌     ▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌    ▐░▌       ▐░▌▐░▌       ▐░▌▐░░░░░░░░░░░▌▐░▌      ▐░░▌▐░▌       ▐░▌
 ▀▀▀▀▀▀▀▀▀▀   ▀         ▀       ▀            ▀       ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀      ▀         ▀  ▀         ▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀        ▀▀  ▀         ▀ 
=====================================================================================================================================================















HEROES                                                                Round {4}                                                                   MONSTERS
=====================================================================================================================================================
Rillifane_Rallathil I (HP: 61, MP: 1730)                                                               X                                           Phantom_Wraith (HP: 37)                                                           
Sehanine_Monnbow II (HP: 0, MP: 600)                                                                   X                                           BigBad-Wolf (HP: 0)                                                               

--- Heroes' Turn ---
Action for Rillifane_Rallathil I (A=Attack, S=Spell, P=Potion, E=Equip, I=Info, Q=Quit): a
Select target monster:
(1) Phantom_Wraith HP:37
> 1
>
>
>
>
>
>
>
>
>
>

>--- >Rillifane_Rallathil I hits Phantom_Wraith for 63 damage.
 ---

>
>
>
>
>
>
>
>
>
>

>--- Phantom_Wraith is defeated!
 ---

>
>
>
>
>
>
>
>
>
>

>--- Sehanine_Monnbow II is revived with half HP/MP (no rewards).
 ---

Rewards distributed: +200 gold, +2 EXP (per surviving hero).
>
>
>
>
>
>
>
>
>
>

>--- Heroes won the battle!
 ---

===== BATTLE ENDS =====

██╗     ███████╗ ██████╗ ███████╗███╗   ██╗██████╗ ███████╗       ███╗   ███╗      ██╗  ██╗        
██║     ██╔════╝██╔════╝ ██╔════╝████╗  ██║██╔══██╗██╔════╝██╗    ████╗ ████║      ██║  ██║        
██║     █████╗  ██║  ███╗█████╗  ██╔██╗ ██║██║  ██║███████╗╚═╝    ██╔████╔██║      ███████║        
██║     ██╔══╝  ██║   ██║██╔══╝  ██║╚██╗██║██║  ██║╚════██║██╗    ██║╚██╔╝██║ and  ██╔══██║        
███████╗███████╗╚██████╔╝███████╗██║ ╚████║██████╔╝███████║╚═╝    ██║ ╚═╝ ██║      ██║  ██║        
╚══════╝╚══════╝ ╚═════╝ ╚══════╝╚═╝  ╚═══╝╚═════╝ ╚══════╝       ╚═╝     ╚═╝      ╚═╝  ╚═╝        















+---+---+---+---+---+---+---+---+
|   |   | M | X | X | X | M |   |
+---+---+---+---+---+---+---+---+
|   |   | X |   |   | M | M | X |
+---+---+---+---+---+---+---+---+
| M |   | P | M |   |   |   | M |
+---+---+---+---+---+---+---+---+
|   |   |   | M |   | X |   | X |
+---+---+---+---+---+---+---+---+
| M |   |   | M |   |   |   |   |
+---+---+---+---+---+---+---+---+
|   | X | M |   | M |   |   | M |
+---+---+---+---+---+---+---+---+
| M |   |   |   |   |   | M |   |
+---+---+---+---+---+---+---+---+
| M |   |   |   | M |   |   |   |
+---+---+---+---+---+---+---+---+

Controls:
W/A/S/D - move
I/C - manage inventory (view info, equip/use items)
M - enter market (if on market tile)
Q - quit game
H - Help/Information
Your move > 



Output:

==================================================
          GAME STATISTICS
==================================================
Total Games Played:       1
Total Battles Won:        1
Total Heroes Levelled Up: 0
Total Monsters Defeated:  2
==================================================

Press ENTER to exit...

Input:

enter

Output:
Exiting the game...



