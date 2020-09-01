package ui;

import model.Board;

import java.util.Arrays;

public class Game {
    Board board = new Board();




    public Game() {
        board.generateNewNumber();
        board.generateNewNumber();

        printBoard();
        board.moveUp();
        System.out.println();
        printBoard();
        System.out.println();
        board.moveDown();
        printBoard();
        System.out.println();
        board.moveLeft();
        printBoard();
        System.out.println();
        board.moveRight();
        printBoard();





    }

    public void printBoard() {
        for (int i = 0; i < 4; i++) {
            int[] row = board.getRow(i);
                System.out.println(Arrays.toString(row));
        }

    }



}
