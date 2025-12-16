# Legends of Valor

A strategic RPG featuring two game modes: the classic Monsters and Heroes and the new lane-based Legends of Valor. Heroes battle monsters, buy equipment, and level up in turn-based combat. Developed for CS 611: Object-Oriented Principles.

## Team Members

| Name | Email | Student ID |
|------|-------|------------|
| Oktay Ozel | oozel@bu.edu | U93822204 |
| Alim Cemal Kura | ackura@bu.edu | U86485144 |
| Josue Vega | josuevl@bu.edu | U43284900 |

## Repository

[https://github.com/oktayozel/assignment_5](https://github.com/oktayozel/assignment_5)

## Quick Start

### Compile & Run
```bash
chmod +x compile.sh run.sh    # Make executable (first time only)
./compile.sh
./run.sh
```

These scripts compile all Java files with UTF-8 encoding and run the game. Class files are created in the `out/` directory.

### Manual Compilation
```bash
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
```

### Note
- Run all commands from the project root directory (`assignment_5/`)
- Create the `out/` directory if it doesn't exist: `mkdir out`

## Game Modes

### Monsters and Heroes (MaH)
Classic turn-based RPG where heroes explore a grid world, battle monsters, and shop at markets.

### Legends of Valor (LoV)
New strategic lane-based mode where heroes defend their nexus from monster waves advancing through three lanes.

## Project Structure
```
assignment_5/
├── src/
│   ├── Main.java           # Entry point
│   │
│   ├── Battle/            # Combat systems
│   │   ├── Battle.java            # MaH combat handler
│   │   └── LoVCombatHandler.java  # LoV simultaneous combat
│   │
│   ├── Core/              # Game foundations
│   │   ├── Board.java
│   │   ├── GameManager.java       # Game orchestration
│   │   ├── Party.java
│   │   ├── Piece.java
│   │   ├── Tile.java
│   │   └── User.java
│   │
│   ├── Hero/              # Hero classes
│   │   ├── Hero.java
│   │   ├── Paladin.java
│   │   ├── Sorcerer.java
│   │   └── Warrior.java
│   │
│   ├── Monster/           # Monster types
│   │   ├── Monster.java
│   │   ├── Dragon.java
│   │   ├── Exoskeleton.java
│   │   ├── Spirit.java
│   │   ├── MonsterSpawner.java
│   │   └── MonsterFactory.java
│   │
│   ├── Item/              # Equipment and consumables
│   │   ├── Item.java
│   │   ├── Armor.java
│   │   ├── Potion.java
│   │   ├── Spell.java
│   │   └── Weapon.java
│   │
│   ├── Inventory/         # Item management
│   │   ├── Inventory.java
│   │   └── InventoryEntry.java
│   │
│   ├── Market/            # Trading system
│   │   ├── Market.java
│   │   ├── LoVMarket.java
│   │   └── MaHMarket.java
│   │
│   ├── Games/             # Game implementations
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
│   ├── Utils/             # Utilities and patterns
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
│   │   ├── Sound/
│   │   │   └── SoundManager.java
│   │   │
│   │   └── Settings/
│   │       └── Settings.java
│   │
│   └── data/              # Game data
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
├── out/                   # Compiled .class files
├── compile.sh
├── run.sh
├── README.md
└── Diagram for Assignment 5 Oktay Ozel.pdf
```

## Architecture

### Core Design
Seven-domain architecture with clear separation of concerns:

| Domain | Purpose |
|--------|---------|
| **Core** | Game foundations (Board, GameManager, Tile, Piece, Party, User) |
| **Games** | Game-specific implementations (MaH and LoV) |
| **Battle** | Combat systems (turn-based for MaH, simultaneous for LoV) |
| **Hero/Monster** | Entity definitions and behavior |
| **Item** | Equipment and consumables |
| **Inventory** | Item storage and equipment management |
| **Market** | Trading and repair system |

### Key Design Decisions

**Preserved MaH Infrastructure**
- Reused existing architecture where possible
- Extended rather than replaced core systems

**Legends of Valor Extensions**
- **Extended Tile System**: Added Bush, Cave, and Koulou terrain types
- **TerrainEffects Class**: Applies terrain bonuses to heroes
- **Refactored Combat**: Created `LoVCombatHandler` for simultaneous combat
- **Extended Board**: `LoVBoard` class for lane-based gameplay
- **Sound System**: `SoundManager` for game music
- **Spell System**: Refactored with `SpellEffect` interface for scalable effect types

**Code Base Selection**
- Built on Oktay's MaH implementation for superior modularity
- ASCII art and color system easily adapted to LoV
- Cleaner architecture for scaling to new game mode

## Design Patterns

| Pattern | Implementation | Classes |
|---------|----------------|---------|
| **Singleton** | Single instance management | `SoundManager`, `Statistics` |
| **Strategy** | Spell and attack behaviors | `SpellEffect` (Fire/Ice/Lightning), `AttackStrategy` (Warrior/Paladin/Sorcerer) |
| **Factory** | Monster generation | `MonsterFactory` |
| **Template Method** | Game flow control | `GameManager`, `Market` |

## Key Features

### Combat System
- **Three hero classes** with unique attack strategies (Warrior, Sorcerer, Paladin)
- **Three monster types** with distinct abilities (Dragon, Exoskeleton, Spirit)
- **Turn-based combat** for Monsters and Heroes
- **Simultaneous combat** for Legends of Valor

### Equipment System
- **Weapons and Armor** with level requirements
- **Spells** with damage and debuff effects
- **Potions** for stat restoration
- **Durability and repair** mechanics

### World and Terrain
- **MaH**: Procedurally generated grid with markets and common spaces
- **LoV**: Three-lane battlefield with strategic terrain (Bush, Cave, Koulou)
- **Terrain effects** modify hero stats



## Requirements

- Java 8
- Terminal/console with UTF-8 support
- Sound support (optional, for background music)

## Input/Output System Enhancements

The `Input.java` and `Output.java` files form the core user interaction layer, with significant modifications for supporting both game modes.

### Input.java Modifications

**Dual Game Mode Support**
- `getInput()` - Handles MaH controls (W/A/S/D movement, I for inventory, M for market)
- `getInputLoV()` - Handles LoV controls with extended action set (movement, combat, teleport, recall)
- Both methods support consistent 'Q' to quit with statistics display

**Enhanced Battle System**
- `getHeroBattleAction()` - Per-hero action prompts for multi-hero battles
- Support for Attack, Spell, Potion, Equipment changes during combat
- Info command (I) allows mid-battle status checks without consuming turn

**LoV-Specific Features**
- `tryMoveLoVWithObstaclePrompt()` - Interactive obstacle removal system
  - Prompts player before breaking obstacles (Y/N choice)
  - Breaking obstacles consumes turn, canceling doesn't
  - Integrates with terrain system (OBSTACLE → PLAIN conversion)
- `getLoVDifficulty()` - Difficulty selection (Easy/Medium/Hard spawn rates)
- Movement validation with victory condition checks after each action

**Market System**
- `handleBuy()` / `handleSell()` / `handleRepair()` - Unified market transaction handlers
- Repair system uses `Repairable` interface to identify broken equipment
- Calculates repair costs and validates hero gold before transactions

**Utility Methods**
- `readInt(min, max)` - Robust integer input with range validation and Q-to-quit support
- `isGameExit()` - Consistent exit handling with statistics display
- `waitForEnter()` - Pause system for narrative moments

### Output.java Modifications

**Visual Enhancements**
- **ASCII Art Heroes**: Pre-defined art for Paladin, Sorcerer, and Warrior
- **Color System**: 13 color constants (ANSI escape codes) for rich terminal output
  - Bright colors for emphasis (BRIGHT_GREEN, BRIGHT_CYAN, BRIGHT_YELLOW, etc.)
  - Standard colors for regular text
  - Color-coded game elements (heroes, monsters, items, terrain)

**Game-Specific Banners**
- `boardBanner()` - Displays game title in ASCII art
  - "LEGENDS: M and H" for Monsters and Heroes mode
  - "LEGENDS OF VALOR" for LoV mode
- `gameInitializationMessage()` - Animated hero selection screens with ASCII art

**Menu Systems**
- `printMenu()` - Context-aware control display
  - MaH: W/A/S/D, I/C (inventory), M (market), Q (quit), H (help)
  - LoV: Movement + combat actions (F/C/U), teleport (T), recall (R), pass (P)
- `printBattleMenu()` - In-battle action menu with color-coded options
- `printMarketMenu()` - Market transaction options

**Narrative System**
- `narrative()` - Animated story moments with optional color
- `printRandomNoBattleMessage()` - 7 randomized "no encounter" messages with color variety
- Animated text scrolling for dramatic effect

**Information Display**
- `displayStatistics()` - Game statistics with color formatting
- `displayHeroSelections()` - Animated hero reveal with class-specific ASCII art
- `displayInstructions()` - Contextual help text for MaH and LoV


### External Resources
- **ASCII Art**: [asciiart.eu](https://www.asciiart.eu/)
- **Background Music**: "Wanderer at Night" by Zambolino - [freetouse.com](https://freetouse.com/music/zambolino/wanderer-at-night)

