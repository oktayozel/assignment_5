#!/bin/bash

mkdir -p out

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
      
echo "Compilation finished. "