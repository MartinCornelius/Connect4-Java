package Connect4;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Piece extends Circle {

    public static Piece[][] pieces = new Piece[Connect4Game.cols][Connect4Game.rows];

    public Piece(int x, int y, Color color) {
        super(x * Connect4Game.tileSize + (Connect4Game.tileSize / 2), y * Connect4Game.tileSize + (Connect4Game.tileSize / 2), Connect4Game.tileSize, color);
        this.setRadius(Connect4Game.tileSize * 0.4);
        pieces[x][y] = this;
    }

    public static void emptyBoard() {
        for (int i = 0; i < Connect4Game.cols; i++) {
            for (int j = 0; j < Connect4Game.rows; j++) {
                pieces[i][j] = null;
            }
        }
    }
}
