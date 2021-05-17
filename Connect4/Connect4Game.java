package Connect4;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Random;

public class Connect4Game extends Application {

    // GAME
    Stage gameStage;
    Pane gameCanvas;
    public static int cols = 7;
    public static int rows = 6;
    public static int tileSize = 100;
    public Rectangle[] gameBoard = new Rectangle[cols];

    // PLAYER
    int currentPlayer;
    Color player1Color = Color.RED;
    Color player2Color = Color.YELLOW;

    @Override
    public void start(Stage gameStage) throws Exception{
        // Set game title
        gameStage.setTitle("Connect 4");

        // Main window setup
        Pane gameCanvas = new Pane();
        gameCanvas.setStyle("-fx-background-color:#004C99"); // Set highlight color
        Scene gameScene = new Scene(gameCanvas, cols * tileSize, rows * tileSize);

        // Player Controls
        gameCanvas.setOnMouseClicked(e -> {
            int mouseX = (int) e.getSceneX() / tileSize;
            int mouseY = (int) e.getSceneY() / tileSize;
            place(mouseX, mouseY, gameCanvas);
        });

        // Select starting player color
        Random rand = new Random();
        currentPlayer = rand.nextInt(2);
        currentPlayer += 1;

        // Setup game grid
        setupGameGrid(gameCanvas);
        printBoard();

        // Set global variables
        this.gameStage = gameStage;
        this.gameCanvas = gameCanvas;

        // Setup scene and show game
        gameStage.setScene(gameScene);
        gameStage.show();
    }

    void setupGameGrid(Pane gameCanvas) {
        for (int i = 0; i < cols; i++) {
            Rectangle tile = new Rectangle(tileSize, tileSize * rows, Color.valueOf("#0080FF")); // Set background color
            tile.setTranslateX(i * tileSize);
            gameBoard[i] = tile;

            tile.setOnMouseEntered(e -> {
                tile.setOpacity(0.5);
            });

            tile.setOnMouseExited(e -> {
                tile.setOpacity(1);
            });

            gameCanvas.getChildren().add(tile);
        }

        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                Tile newTile = new Tile(i, j);

                int finalI = i;
                newTile.setOnMouseEntered(e -> {
                    gameBoard[finalI].setOpacity(0.5);
                });
                newTile.setOnMouseExited(e -> {
                    gameBoard[finalI].setOpacity(1);
                });

                gameCanvas.getChildren().add(newTile);
            }
        }
    }

    void printBoard() {
        String[][] printedBoard = new String[cols][rows];
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                if (Piece.pieces[i][j] == null) {
                    printedBoard[i][j] = " ";
                }
                else {
                    // Print player 1 as 1
                    if (Piece.pieces[i][j].getFill().equals(player1Color)) {
                        printedBoard[i][j] = "1";
                    }
                    // Print player 2 as 2
                    if (Piece.pieces[i][j].getFill().equals(player2Color)) {
                        printedBoard[i][j] = "2";
                    }
                }
            }
        }

        // Print board
        System.out.println("-----------------------------");
        for (int i = 0; i < rows; i++) {
            System.out.println("| " + printedBoard[0][i] + " | " + printedBoard[1][i] + " | " + printedBoard[2][i] + " | " + printedBoard[3][i] + " | " + printedBoard[4][i] + " | " + printedBoard[5][i] + " | " + printedBoard[6][i] + " |");
            System.out.println("-----------------------------");
        }
        System.out.println("\n");
    }

    void place(int x, int y, Pane gameCanvas) {
        for (int i = rows-1; i >= 0; i--) {
            if (Piece.pieces[x][i] == null) {
                if (currentPlayer == 1) {
                    Piece piece = new Piece(x, i, player1Color);
                    Piece.pieces[x][i] = piece;
                    gameCanvas.getChildren().add(piece);
                    currentPlayer = 2;
                    printBoard();
                    if (checkForWin("1")) gameover("1");
                    return;
                }
                if (currentPlayer == 2) {
                    Piece piece = new Piece(x, i, player2Color);
                    Piece.pieces[x][i] = piece;
                    gameCanvas.getChildren().add(piece);
                    currentPlayer = 1;
                    printBoard();
                    if (checkForWin("2")) gameover("2");
                    return;
                }
            }
        }
    }

    boolean checkForWin(String player) {
        String[][] currentBoard = new String[cols][rows];
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                if (Piece.pieces[i][j] == null) {
                    currentBoard[i][j] = " ";
                }
                else {
                    // Print player 1 as 1
                    if (Piece.pieces[i][j].getFill().equals(player1Color)) {
                        currentBoard[i][j] = "1";
                    }
                    // Print player 2 as 2
                    if (Piece.pieces[i][j].getFill().equals(player2Color)) {
                        currentBoard[i][j] = "2";
                    }
                }
            }
        }

        // Horizontal
        for (int i = 0; i < cols-3; i++) {
            for (int j = 0; j < rows; j++) {
                if (currentBoard[i][j].equals(player) &&
                    currentBoard[i + 1][j].equals(player) &&
                    currentBoard[i + 2][j].equals(player) &&
                    currentBoard[i + 3][j].equals(player)) return true;
            }
        }

        // Vertical
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows-3; j++) {
                if (currentBoard[i][j].equals(player) &&
                        currentBoard[i][j + 1].equals(player) &&
                        currentBoard[i][j + 2].equals(player) &&
                        currentBoard[i][j + 3].equals(player)) return true;
            }
        }

        // Diagonals
        for (int i = 0; i < cols-3; i++) {
            for (int j = 0; j < rows-3; j++) {
                if (currentBoard[i][j].equals(player) &&
                        currentBoard[i + 1][j + 1].equals(player) &&
                        currentBoard[i + 2][j + 2].equals(player) &&
                        currentBoard[i + 3][j + 3].equals(player)) return true;
            }
        }
        for (int i = 0; i < cols-3; i++) {
            for (int j = 3; j < rows; j++) {
                if (currentBoard[i][j].equals(player) &&
                        currentBoard[i + 1][j - 1].equals(player) &&
                        currentBoard[i + 2][j - 2].equals(player) &&
                        currentBoard[i + 3][j - 3].equals(player)) return true;
            }
        }

        return false;
    }

    void gameover(String winner) {
        new Alert(Alert.AlertType.NONE, "Gameover Winner #" + winner, ButtonType.OK).showAndWait();
        restartGame();
    }

    void restartGame() {
        try {
            gameStage.close();
            Piece.emptyBoard();
            setupGameGrid(gameCanvas);
            start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
