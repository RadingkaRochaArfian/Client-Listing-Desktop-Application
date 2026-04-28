#!/bin/bash
mkdir -p backup
mkdir -p bin
javac -d bin -cp "lib/*" src/main/java/database/*.java src/main/java/system/*.java src/main/java/ui/*.java
java -cp "bin:lib/*" system.App
