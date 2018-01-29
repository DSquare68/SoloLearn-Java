@echo off
@chcp 1250
if not exist bin mkdir bin
javac -d bin -cp bin src/Main/Matrix.java
java -cp bin Main.Matrix 
set /p key=Enter key: