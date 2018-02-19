@echo off
@chcp 1250
echo enter size of matrix
if not exist bin mkdir bin
javac -d bin -cp bin src/Main/Matrix.java
java -cp bin Main.Matrix 
set /p key=Enter key: