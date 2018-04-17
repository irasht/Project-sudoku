
import java.util.Random;

public class Sudoku {
    protected int grid[][];
    private int nextX = 0;
    private int nextY = 0;
    private SudokuLevel sLevel;
    Random randomNumb = new Random();

    protected int[][] createSudoku() {
        sLevel = SudokuLevel.EASY;
        this.grid = new int[9][9];
        nextCell(0, 0);
        deleteNubmers(sLevel);
        return grid;
    }

    private boolean nextCell(int x, int y) {
        int position = 0;
        int currentPosition = 0;
        int[] check = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        for (int i = check.length - 1; i > 0; i--) {
            currentPosition = randomNumb.nextInt(i);
            position = check[currentPosition];
            check[currentPosition] = check[i];
            check[i] = position;
        }

        for (int i = 0; i < check.length; i++) {
            if (checkNumber(x, y, check[i])) {
                grid[x][y] = check[i];
                if (x == 8) {
                    if (y == 8) {
                        return true;
                    } else {
                        nextX = 0;
                        nextY = y + 1;
                    }
                } else {
                    nextX = x + 1;
                }
                if (nextCell(nextX, nextY)) {
                    return true;
                }
            }
        }
        grid[x][y] = 0;
        return false;
    }

    private boolean checkNumber(int x, int y, int currentPosition) {
        int cornerX = 0;
        int cornerY = 0;
        for (int i = 0; i < 9; i++) {
            if (currentPosition == grid[x][i]) {
                return false;
            }
        }
        for (int i = 0; i < 9; i++) {
            if (currentPosition == grid[i][y]) {
                return false;
            }
        }

        if (x > 2) {
            if (x > 5) {
                cornerX = 6;
            } else {
                cornerX = 3;
            }
        }
        if (y > 2) {
            if (y > 5) {
                cornerY = 6;
            } else {
                cornerY = 3;
            }
        }
        for (int i = cornerX; i < 10 && i < cornerX + 3; i++) {
            for (int j = cornerY; j < 10 && j < cornerY + 3; j++) {
                if (currentPosition == grid[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private int getNumberOfHoles(SudokuLevel sLevel) {
        sLevel = SudokuLevel.EASY;
        int numOfEmptyCell = randomNumb.nextInt((sLevel.getMaximum() - sLevel.getMinimum()) + 1) + sLevel.getMinimum();
        return numOfEmptyCell;
    }

    private void deleteNubmers(SudokuLevel sLevel) {
        double remainingSquares = 81;
        double remainingHoles = (double) getNumberOfHoles(sLevel); //5;
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++) {
                double holeChance = remainingHoles / remainingSquares;
                if (Math.random() <= holeChance) {
                    grid[i][j] = 0;
                    remainingHoles--;
                }
                remainingSquares--;
            }
    }

    private void print() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(grid[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public boolean isSudokuValid(int grid[][]) {
        boolean result = false;
        int sum = 0;
        if (grid == null || grid.length != 9 || grid[0].length != 9) {
            return result;
        } else {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sum += grid[j][i];
                }
                if (sum == 45) {
                    result = true;
                }
            }

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sum += grid[i][j];
                }
                if (sum == 45) {
                    result = true;
                }
            }
            for (int i = 0; i < 9; i += 3) {
                for (int j = 0; j < 9; j += 3) {
                    for (int ii = i; ii < i + 3; ii++) {
                        for (int jj = j; jj < j + 3; jj++) {
                            sum += grid[ii][jj];
                        }
                    }
                    if (sum == 45) {
                        result = true;
                    }
                }
            }
        }
        return result;
    }

//    public static void main(String args[]) {
//        Sudoku sudoku = new Sudoku();
//        sudoku.createSudoku();
//        sudoku.print();
//    }
}