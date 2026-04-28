@echo off
mkdir backup 2>nul
mkdir bin 2>nul
javac -d bin -cp "lib/*" src/main/java/database/*.java src/main/java/system/*.java src/main/java/ui/*.java
java -cp "bin;lib/*" system.App
pause
