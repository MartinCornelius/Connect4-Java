package Connect4;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Tile extends Circle {

    public static Tile[][] board = new Tile[Connect4Game.cols][Connect4Game.rows];

    public Tile(int x, int y) {
        super(x * Connect4Game.tileSize + (Connect4Game.tileSize / 2), y * Connect4Game.tileSize + (Connect4Game.tileSize / 2), Connect4Game.tileSize, Color.WHITE);
        this.setRadius(Connect4Game.tileSize * 0.4);
        board[x][y] = this;
    }
}
