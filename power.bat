@echo off
@chcp 1250
echo enter two numbers(double) base and exponent
if not exist bin mkdir bin
javac -d bin -cp bin src/Main/Power.java
java -cp bin Main.Power   
set /p key=Enter key: