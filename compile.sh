#!/bin/bash

mkdir -p out

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
      
echo "Compilation finished. "