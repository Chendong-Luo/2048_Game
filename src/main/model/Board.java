package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {
    private int[][] board = {
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
    };
    private Random rand = new Random();


    public Board() {

    }

    public void setBoard(int x, int y, int value) {
        board[x][y] = value;
    }

    public int[] getRow(int index) {
        return board[index];
    }

    public void generateNewNumber() {
        List<Position> emptyGrid = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (isGridEmpty(i, j)) {
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
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != 0) {
                    if (gridMoveUpAble(i, j) != 3) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public Boolean boardMoveDownAble() {
        for (int i = board.length - 2; i >= 0; i--) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != 0) {
                    if (gridMoveDownAble(i, j) != 3) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public Boolean boardMoveLeftAble() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 1; j < board.length; j++) {
                if (board[i][j] != 0) {
                    if (gridMoveLeftAble(i, j) != 3) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public Boolean boardMoveRightAble() {
        for (int i = 0; i < board.length; i++) {
            for (int j = board.length - 2; j >= 0; j--) {
                if (board[i][j] != 0) {
                    if (gridMoveRightAble(i, j) != 3) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    public int gridMoveUpAble(int x, int y) {
        if (x > 0) {
            int thisGrid = board[x][y];
            int nextGrid = board[x - 1][y];
            if (nextGrid == 0) {
                return 1;
            } else if (nextGrid == thisGrid) {
                return 2;
            } else {
                return 3;
            }
        }
        return 3;
    }

    public int gridMoveDownAble(int x, int y) {
        if (x < 3) {
            int thisGrid = board[x][y];
            int nextGrid = board[x + 1][y];
            if (nextGrid == 0) {
                return 1;
            } else if (nextGrid == thisGrid) {
                return 2;
            } else {
                return 3;
            }
        }
        return 3;
    }


    public int gridMoveLeftAble(int x, int y) {
        if (y > 0) {
            int thisGrid = board[x][y];
            int nextGrid = board[x][y - 1];
            if (nextGrid == 0) {
                return 1;
            } else if (nextGrid == thisGrid) {
                return 2;
            } else {
                return 3;
            }
        }
        return 3;
    }

    public int gridMoveRightAble(int x, int y) {
        if (y < 3) {
            int thisGrid = board[x][y];
            int nextGrid = board[x][y + 1];
            if (nextGrid == 0) {
                return 1;
            } else if (nextGrid == thisGrid) {
                return 2;
            } else {
                return 3;
            }
        }
        return 3;
    }


    public void gridMoveUp(int x, int y, int counter, List<Integer> list) {
        if (gridMoveUpAble(x, y) != 3) {
            if (gridMoveUpAble(x, y) == 1) {
                board[x - 1][y] += board[x][y];
                board[x][y] = 0;
                gridMoveUp(x - 1, y, counter, list);
            } else {
                if (counter == 0 && !list.contains(x - 1)) {
                    board[x - 1][y] += board[x][y];
                    board[x][y] = 0;
                    gridMoveUp(x - 1, y, counter + 1, list);
                }
            }
        } if (counter != 0) {
            list.add(x);
        }
    }

    public void gridMoveDown(int x, int y, int counter, List<Integer> list) {
        if (gridMoveDownAble(x, y) != 3) {
            if (gridMoveDownAble(x, y) == 1) {
                board[x + 1][y] += board[x][y];
                board[x][y] = 0;
                gridMoveDown(x + 1, y, counter, list);
            } else {
                if (counter == 0 && !list.contains(x + 1)) {
                    board[x + 1][y] += board[x][y];
                    board[x][y] = 0;
                    gridMoveDown(x + 1, y, counter + 1, list);
                }
            }
        } if (counter != 0) {
            list.add(x);
        }
    }

    public void gridMoveLeft(int x, int y, int counter, List<Integer> list) {
        if (gridMoveLeftAble(x, y) != 3) {
            if (gridMoveLeftAble(x, y) == 1) {
                board[x][y - 1] += board[x][y];
                board[x][y] = 0;
                gridMoveLeft(x, y - 1, counter, list);
            } else {
                if (counter == 0 && !list.contains(y - 1)) {
                    board[x][y - 1] += board[x][y];
                    board[x][y] = 0;
                    gridMoveLeft(x, y - 1, counter + 1, list);
                }
            }
        } if (counter != 0) {
            list. add(y);
        }
    }

    public void gridMoveRight(int x, int y, int counter, List<Integer> list) {
        if (gridMoveRightAble(x, y) != 3) {
            if (gridMoveRightAble(x, y) == 1) {
                board[x][y + 1] += board[x][y];
                board[x][y] = 0;
                gridMoveRight(x, y + 1, counter, list);
            } else {
                if (counter == 0 && !list.contains(y + 1)) {
                    board[x][y + 1] += board[x][y];
                    board[x][y] = 0;
                    gridMoveRight(x, y + 1, counter + 1, list);
                }
            }
        } if (counter != 0) {
            list.add(y);
        }
    }


    public void moveUp() {
        if (boardMoveUpAble()) {
            for (int j = 0; j < board.length; j++) {
                List<Integer> listOfMerged = new ArrayList<>();
                for (int i = 1; i < board.length; i++) {
                    gridMoveUp(i, j, 0, listOfMerged);
                }
            }
            generateNewNumber();
        }
    }


    public void moveDown() {
        if (boardMoveDownAble()) {
            for (int j = 0; j < board.length; j++) {
                List<Integer> listOfMerged = new ArrayList<>();
                for (int i = board.length - 2; i >= 0; i--) {
                    gridMoveDown(i, j, 0, listOfMerged);
                }
            }
            generateNewNumber();
        }
    }

    public void moveLeft() {
        if (boardMoveLeftAble()) {
            for (int i = 0; i < board.length; i++) {
                List<Integer> listOfMerged = new ArrayList<>();
                for (int j = 1; j < board.length; j++) {
                    gridMoveLeft(i, j, 0, listOfMerged);
                }
            }
            generateNewNumber();
        }
    }


    public void moveRight() {
        if (boardMoveRightAble()) {
            for (int i = 0; i < board.length; i++) {
                List<Integer> listOfMerged = new ArrayList<>();
                for (int j = board.length - 2; j >= 0; j--) {
                    gridMoveRight(i, j, 0, listOfMerged);
                }
            }
            generateNewNumber();
        }
    }

}
