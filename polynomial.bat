@echo off
@chcp 1250
if not exist bin mkdir bin
javac -d bin -cp bin src/Main/Polynomial.java
java -cp bin Main.Polynomial   
set /p key=Enter key: