package ui;

import model.Board;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.Scanner;

public class Game implements KeyListener {
    Board board = new Board();
    Scanner scanner = new Scanner(System.in);




    public Game() {
        runGame();

//        board.setBoard(0,0, 8);
//        board.setBoard(1, 0, 4);
//        board.setBoard(2, 0, 4);
//        board.setBoard(3, 0, 4);
//        printBoard();
//        System.out.println();
//        board.moveUp();
//        printBoard();
//        System.out.println();
//        board.moveUp();
//        printBoard();







    }

    public void runGame() {
        board.generateNewNumber();
        board.generateNewNumber();
        printBoard();
        while(true) {
            if (isGameOver()) {
                break;

            }
            System.out.println("type up, down, left or right");
            String instruction = scanner.nextLine();
            switch (instruction) {
                case "up":
                   board.moveUp();
                   printBoard();
                   break;
                case  "down" :
                    board.moveDown();
                    printBoard();
                    break;
                case "left":
                    board.moveLeft();
                    printBoard();
                    break;
                case "right":
                    board.moveRight();
                    printBoard();
                    break;
                default:
                    System.out.println("invalid typed");
            }


        }

    }

    public Boolean isGameOver() {
        return !(board.boardMoveRightAble() || board.boardMoveLeftAble() || board.boardMoveDownAble() || board.boardMoveUpAble());
    }

    public void printBoard() {
        for (int i = 0; i < 4; i++) {
            int[] row = board.getRow(i);
                System.out.println(Arrays.toString(row));
        }

    }


    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            board.moveUp();
            printBoard();
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            board.moveDown();
            printBoard();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            board.moveLeft();
            printBoard();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            board.moveRight();
            printBoard();
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
