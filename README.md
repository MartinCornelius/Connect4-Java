# **Connect 4 Game**
Java connect 4 game made with JavaFX (gui)

*Connect 4 for win, either in a row, column or diagonal*

### **Features**
- 2 Player Local (same computer)
- Column highlighting
- Prints board in console while playing

---

<img src="https://user-images.githubusercontent.com/62438208/118454123-4de61100-b6f8-11eb-80f0-edd4ae194204.png" width=350px>
<img src="https://user-images.githubusercontent.com/62438208/118454309-81c13680-b6f8-11eb-898e-f71221b6e7c6.png" width=350px>
<img src="https://user-images.githubusercontent.com/62438208/118454397-9bfb1480-b6f8-11eb-91ce-d40ec42e4c6c.png" width=350px>

---

### **Change Player Colors**
*In the **Connect4Game.java line 26-27** change 'player1Color' and 'player2Color' to desired colors*

**Example Player Colors**
```java
public class Connect4Game extends Application {
    ...
    // Current Colors
    Color player1Color = Color.RED;
    Color player2Color = Color.YELLOW;

    // Example
    Color player1Color = Color.AQUA;
    Color player2Color = Color.GREEN;
}
```

---

### **Change Background Color**
*In **Connect4Game.java line 66**, Change Color.valueOf("#0080FF") to whatever color you'd like. Can be set to hex or word like green or blue.

**Example Background Color**
```java
void setupGameGrid(Pane gameCanvas) {
    for (int i = 0; i < cols; i++) {
        // Current background color
        Rectangle tile = new Rectangle(tileSize, tileSize * rows, 
        Color.valueOf("#0080FF")); // #0080FF blue by default

        // Example
        ...Color.valueOf("green"); // Changed background color
    }
}
```

This won't change the color the column highlighting. This has to be done **Connect4Game.java line 36**. 

I would recommend to set this to a darker color than the background to make the column highlighting work. If you set it to the same color it wont work. 

You can also play around with other colors. 

---

### **Changing Column Highlight Color**

*In **Connect4Game.java line 36** change gameCanvas.setStyle("-fx-background-color:#004C99") to the desired color. Can be set to hex or word (like green or red)*

**Example Column Highlight Color**
```java
@Override
public void start(Stage gameStage) throws Exception {
    ...
    // Current column highlight color
    gameCanvas.setStyle("-fx-background-color:#004C99");

    // Example
    ...setStyle("-fx-background-color:darkgreen");
}
```
