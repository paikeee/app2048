package myApp;

import java.io.FileNotFoundException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;


public class Controller {

    Game game;
    boolean gameStarted = false;

    @FXML
    private Label currentScore;

    @FXML
    private GridPane gameGrid;

    @FXML
    private Button newGameButton;

    @FXML
    private Label gameOver;

    @FXML
    private Label startLabelNum;

    @FXML
    private Label startLabelNew;

    @FXML
    private Label winLabel;

    @FXML
    void initialize() {
        newGameButton.setOnAction(event -> {
            startLabelNew.setVisible(false);
            startLabelNum.setVisible(false);
            gameOver.setVisible(false);
            winLabel.setVisible(false);
            gameGrid.setVisible(true);
            currentScore.setText("0");
            game = new Game();
            gameStarted = true;
            gameGrid.getChildren().add(gridFiller());
        });
        if (gameStarted) {
            currentScore.setText(String.valueOf(game.score));
        }

    }


    private GridPane gridFiller() {
        GridPane current = new GridPane();
        Tile[][] gameTiles = game.getGameTiles();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                try {
                    gameGrid.add(Tile.getTile(gameTiles[i][j].value), j, i);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return current;
    }

    public void keyPressed(KeyEvent event) {
        if (gameStarted) {

            if (event.getCode().equals(KeyCode.A)) game.left();
            if (event.getCode().equals(KeyCode.W)) game.up();
            if (event.getCode().equals(KeyCode.S)) game.down();
            if (event.getCode().equals(KeyCode.D)) game.right();
            if (event.getCode().equals(KeyCode.R)) game.returnMove();

            if (!game.canMove()) {
                gameStarted = false;
                gameGrid.setVisible(false);
                gameOver.setVisible(true);
            }
            if (game.isWin) {
                gameGrid.setVisible(false);
                gameStarted = false;
                winLabel.setVisible(true);
                game.isWin = false;
            }
            gameGrid.getChildren().clear();
            gameGrid.getChildren().add(gridFiller());
            currentScore.setText(String.valueOf(game.score));

        }
    }

}
