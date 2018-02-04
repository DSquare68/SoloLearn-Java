@echo off
@chcp 1250
if not exist bin mkdir bin
javac -d bin -cp bin src/Main/Fibonacci.java
java -cp bin Main.Fibonacci 
set /p key=Enter key: