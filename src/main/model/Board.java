package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {
    private int[][] board = {
            {0,0,0,0},
            {0,0,0,0},
            {0,0,0,0},
            {0,0,0,0}
    };
    private Random rand = new Random();


    public Board() {

    }

    public int[] getRow(int index) {
        return board[index];
    }

    public void generateNewNumber() {
        List<Position> emptyGrid = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if(isGridEmpty(i, j)) {
                    emptyGrid.add(new Position(i, j));
                }
            }
        }
        int index = rand.nextInt(emptyGrid.size());
        Position position = emptyGrid.get(index);
        Boolean twoOrFour = rand.nextBoolean();

        if (twoOrFour) {
            board[position.getX()][position.getY()] = 2;
        } else {
            board[position.getX()][position.getY()] = 4;
        }

    }

    public Boolean isGridEmpty(int row, int column) {
        return board[row][column] == 0;
    }

    public Boolean boardMoveUpAble() {
        for (int i = 1; i < board.length; i++) {
            for(int j = 0; j < board.length; j++) {
                if (board[i][j] != 0) {
                    if (gridMoveUpAble(i, j)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public Boolean boardMoveDownAble() {
        for (int i = board.length - 2; i >= 0; i--) {
            for(int j = 0; j < board.length; j++) {
                if (board[i][j] != 0) {
                    if (gridMoveDownAble(i, j)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public Boolean boardMoveLeftAble() {
        for (int i = 0; i < board.length; i++) {
            for(int j = 1; j < board.length; j++) {
                if (board[i][j] != 0) {
                    if (gridMoveLeftAble(i, j)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public Boolean boardMoveRightAble() {
        for (int i = 0; i < board.length; i++) {
            for(int j = board.length - 2; j >= 0; j--) {
                if (board[i][j] != 0) {
                    if (gridMoveRightAble(i, j)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    public Boolean gridMoveUpAble(int x, int y) {
        int thisGrid = board[x][y];
        int nextGrid = board[x - 1][y];
        return nextGrid == 0 || thisGrid == nextGrid;

    }

    public Boolean gridMoveDownAble(int x, int y) {
        int thisGrid = board[x][y];
        int nextGrid = board[x + 1][y];
        return nextGrid == 0 || thisGrid == nextGrid;
    }

    public Boolean gridMoveLeftAble(int x, int y) {
        int thisGrid = board[x][y];
        int nextGrid = board[x][y - 1];
        return nextGrid == 0 || thisGrid == nextGrid;
    }

    public Boolean gridMoveRightAble(int x, int y) {
        int thisGrid = board[x][y];
        int nextGrid = board[x][y + 1];
        return nextGrid == 0 || thisGrid == nextGrid;
    }



    public void moveUp() {
        if (boardMoveUpAble()) {
            for (int k = 0; k < board.length - 1; k++) {
                for (int i = 1; i < board.length; i++) {
                    for (int j = 0; j < board.length; j++) {
                        if (board[i][j] != 0) {
                            if (gridMoveUpAble(i, j)) {
                                board[i - 1][j] += board[i][j];
                                board[i][j] = 0;
                            }
                        }
                    }

                }

            }
            generateNewNumber();
        }
    }


    public void moveDown() {
        if (boardMoveDownAble()) {
            for (int k = 0; k < board.length - 1; k++) {
                for (int i = board.length - 2; i >= 0; i--) {
                    for (int j = 0; j < board.length; j++) {
                        if (board[i][j] != 0) {
                            if (gridMoveDownAble(i, j)) {
                                board[i + 1][j] += board[i][j];
                                board[i][j] = 0;
                            }
                        }
                    }

                }

            }
            generateNewNumber();
        }
    }

    public void moveLeft() {
        if (boardMoveLeftAble()) {
            for (int k = 0; k < board.length - 1; k++) {
                for (int i = 0; i < board.length; i++) {
                    for (int j = 1; j < board.length; j++) {
                        if (board[i][j] != 0) {
                            if (gridMoveLeftAble(i, j)) {
                                board[i][j - 1] += board[i][j];
                                board[i][j] = 0;
                            }
                        }
                    }

                }

            }
            generateNewNumber();
        }
    }


    public void moveRight() {
        if (boardMoveRightAble()) {
            for (int k = 0; k < board.length - 1; k++) {
                for (int i = 0; i < board.length; i++) {
                    for (int j = board.length - 2; j >= 0; j--) {
                        if (board[i][j] != 0) {
                            if (gridMoveRightAble(i, j)) {
                                board[i][j + 1] += board[i][j];
                                board[i][j] = 0;
                            }
                        }
                    }

                }

            }
            generateNewNumber();
        }
    }

}
