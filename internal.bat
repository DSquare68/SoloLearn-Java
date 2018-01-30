@echo off
@chcp 1250
if not exist bin mkdir bin
javac -d bin -cp bin src/Main/Integral.java
java -cp bin Main.Integral 
set /p key=Enter key: