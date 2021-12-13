package kp.game2048.game;

import java.util.Random;
import java.util.Scanner;

public class Field {

    private Tile[][] tiles;
    private Random random;
    private int gridSize;
    private int score;
    private int rowCount;
    private int columnCount;
    private boolean firstTime = true;
    private Scanner scanner;
    private GameState state = GameState.PLAYING;

    public Field() {
        System.out.println(
                "Hi, if u'd like to play 3x3 type: 3\n" +
                        "if u want to play 4x4, type: 4\n" +
                        "if u want to play 5x5, type: 5\n" +
                        "Good luck and have fun!\n");

        scanner = new Scanner(System.in);
        this.gridSize = scanner.nextInt();

        if (this.gridSize == 5) {
            this.columnCount = 5;
            this.rowCount = 5;
        } else if (this.gridSize == 4) {
            this.columnCount = 4;
            this.rowCount = 4;
        } else if (this.gridSize == 3) {
            this.columnCount = 3;
            this.rowCount = 3;
        } else {
            System.out.println("\tIncorrect input");
            System.exit(0);
        }

        random = new Random();
        tiles = new Tile[rowCount][columnCount];
        startField();
    }

    public void startField() {
        firstTime = true;

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                tiles[i][j] = new Tile();
                tiles[i][j].setValue(0);
            }

        }
        int row1 = random.nextInt(rowCount);
        int column1 = random.nextInt(columnCount);

        int row2 = random.nextInt(rowCount);
        int column2 = random.nextInt(columnCount);

        while ((row1 == row2) && (column1 == column2)) {

            row1 = random.nextInt(rowCount);
            column1 = random.nextInt(columnCount);

            row2 = random.nextInt(rowCount);
            column2 = random.nextInt(columnCount);
        }

        tiles[row1][column1].setValue(2);
        tiles[row2][column2].setValue(2);

    }

    public void reload() {
        this.score = 0;
        startField();
    }

    public void moveTilesLeft() {
        for (int row = 0; row < gridSize; row++) {

            for (int col = gridSize - 1; col > 0; col--) { // =
                if (this.tiles[row][col].getValue() > 0) {
                    if (this.tiles[row][col - 1].getValue() == 0) {
                        this.tiles[row][col - 1].setValue(this.tiles[row][col].getValue());
                        this.tiles[row][col].setValue(0);
                    }

                }
            }
        }

        for (int row = 0; row < gridSize; row++) {
            for (int col = 1; col < gridSize; col++) {
                if (this.tiles[row][col].getValue() > 0) {
                    if (this.tiles[row][col - 1].getValue() == this.tiles[row][col].getValue()) {
                        this.tiles[row][col - 1].setValue(2 * this.tiles[row][col].getValue());
                        //update the score ONCE!
                        int sum = this.tiles[row][col].getValue();
                        this.score += 2 * sum;
                        this.tiles[row][col].setValue(0);
                        break;
                    }
                }
            }
        }
    }

    public void moveTilesRight() {
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize - 1; col++) {
                if (this.tiles[row][col].getValue() > 0) {
                    if (this.tiles[row][col + 1].getValue() == 0) {
                        this.tiles[row][col + 1].setValue(this.tiles[row][col].getValue());
                        this.tiles[row][col].setValue(0);
                    }
                }
            }
        }

        for (int row = 0; row < gridSize; row++) {
            for (int col = gridSize - 2; col >= 0; col--) {
                if (this.tiles[row][col].getValue() > 0) {
                    if (this.tiles[row][col + 1].getValue() == this.tiles[row][col].getValue()) {
                        this.tiles[row][col + 1].setValue(2 * this.tiles[row][col].getValue());

                        //update the score ONCE!
                        int sum = this.tiles[row][col].getValue();
                        this.score += 2 * sum;

                        this.tiles[row][col].setValue(0);

                        break;
                    }
                }
            }
        }
    }

    public void moveTilesDown() {
        for (int col = gridSize - 1; col >= 0; col--) {
            for (int row = 0; row < gridSize - 1; row++) {
                if (this.tiles[row][col].getValue() > 0) {
                    if (this.tiles[row + 1][col].getValue() == 0) {
                        this.tiles[row + 1][col].setValue(this.tiles[row][col].getValue());
                        this.tiles[row][col].setValue(0);
                    }
                }
            }
        }

        for (int col = gridSize - 1; col >= 0; col--) {
            for (int row = gridSize - 2; row >= 0; row--) {
                if (this.tiles[row][col].getValue() > 0) {
                    if (this.tiles[row + 1][col].getValue() == this.tiles[row][col].getValue()) {
                        this.tiles[row + 1][col].setValue(2 * this.tiles[row][col].getValue());

                        //update the score ONCE!
                        int sum = this.tiles[row][col].getValue();
                        this.score += 2 * sum;

                        this.tiles[row][col].setValue(0);

                        break;
                    }
                }
            }
        }
    }

    public void moveTilesUp() {
        for (int col = gridSize - 1; col >= 0; col--) {
            for (int row = gridSize - 1; row > 0; row--) {
                if (this.tiles[row][col].getValue() > 0) {
                    if (this.tiles[row - 1][col].getValue() == 0) {
                        this.tiles[row - 1][col].setValue(this.tiles[row][col].getValue());
                        this.tiles[row][col].setValue(0);
                    }
                }
            }
        }

        for (int col = gridSize - 1; col >= 0; col--) {
            for (int row = 1; row < gridSize; row++) {
                if (this.tiles[row][col].getValue() > 0) {
                    if (this.tiles[row - 1][col].getValue() == this.tiles[row][col].getValue()) {
                        this.tiles[row - 1][col].setValue(2 * this.tiles[row][col].getValue());

                        int sum = this.tiles[row][col].getValue();
                        this.score += 2 * sum;

                        this.tiles[row][col].setValue(0);

                        break;
                    }
                }
            }
        }
    }


    public boolean isLost() {
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {

                if (tiles[i][j].getValue() == 0) {

                    return false;
                }
            }
        }
        state = GameState.FAILED;
        return true;
    }

    public boolean isWon() {
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                if (tiles[i][j].getValue() == 8) { // zmenil som tak , ze vyhras bude na 8 namiesto 2048, aby rychlesie ukazat' funkcnost
                    state = GameState.SOLVED;
                    return true;
                }
            }

        }
        return false;
    }

    public void printField() {

        if (!isLost()) {
            System.out.println("Score : " + getScore());


            if (!firstTime) {
                int a = random.nextInt(2);

                int row = random.nextInt(rowCount);
                int column = random.nextInt(columnCount);

                if (a == 0) {
                    while (tiles[row][column].getValue() != 0) {
                        row = random.nextInt(rowCount);
                        column = random.nextInt(columnCount);
                    }
                    tiles[row][column].setValue(2);
                } else {
                    while (tiles[row][column].getValue() != 0) {
                        row = random.nextInt(rowCount);
                        column = random.nextInt(columnCount);
                    }
                    tiles[row][column].setValue(4);
                }

            }
            firstTime = false;

            for (int i = 0; i < rowCount; i++) {
                for (int j = 0; j < columnCount; j++) {
                    if (this.tiles[i][j].getValue() == 0) {
                        System.out.print("       |  ");

                    } else {
                        System.out.printf("% 4d   |  ", tiles[i][j].getValue());

                    }
                }
                System.out.println();
            }

        }


    }

    public GameState getState() {
        return state;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}