package myApp;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class Game  {

    int score;
    boolean isWin = false;

    private Tile[][] gameTiles;

    private final Stack<Tile[][]> lastGrid;
    private final Stack<Integer> lastScore;

    public Game() {
        score = 0;
        lastGrid = new Stack<>();
        lastScore = new Stack<>();
        setGameTiles();
    }


    public Tile[][] getGameTiles() {
        return gameTiles;
    }

    public void setGameTiles() {
        gameTiles = new Tile[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                gameTiles[i][j] = new Tile();
            }
        }
        addTile();
        addTile();
    }

    private List<Tile> getEmptyTiles(){
        List<Tile> emptyTiles = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++){
                if (gameTiles[i][j].value == 0) emptyTiles.add(gameTiles[i][j]);
            }
        }
        return emptyTiles;
    }

    private void addTile(){
        List<Tile> empty = getEmptyTiles();
        int num = (int) (Math.random() * empty.size() % empty.size());
        Tile emptyTile = empty.get(num);
        emptyTile.value = Math.random() < 0.9 ? 2 : 4;
    }

    public boolean shift(Tile[] tiles) {
        boolean change = false;
        for (int i = 1; i < tiles.length; i++) {
            int ii = i;
            while (ii > 0) {
                if (tiles[ii - 1].value == 0 && tiles[ii].value != 0) {
                    Tile extra = tiles[ii - 1];
                    tiles[ii - 1] = tiles[ii];
                    tiles[ii] = extra;
                    change = true;
                }
                ii--;
            }
        }
      return change;
    }

    public boolean merge(Tile[] tiles) {
        boolean change = false;
        for (int i = 1; i < tiles.length; i++) {
                if (tiles[i - 1].value != 0) {
                    if (tiles[i].value == tiles[i - 1].value) {
                        tiles[i - 1].value *= 2;
                        score += tiles[i - 1].value;
                        if (tiles[i - 1].value == 2048) isWin = true;
                        tiles[i].value = 0;
                        change = true;
                    }
                }
        }
        shift(tiles);
        return change;
    }

    public void rotate() {
        Tile[][] rotateGrid = new Tile[4][4];
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    rotateGrid[j][3 - i] = gameTiles[i][j];
                }
            }
            gameTiles = rotateGrid;
    }

    public void movement() {
        boolean change = false;
        for (Tile[] tilesRow : gameTiles) {
            if (shift(tilesRow) | merge(tilesRow)) change = true;
        }
        if (change) addTile();
    }

    public void left() {
        saveGame(gameTiles);
        movement();
    }

    public void right() {
        saveGame(gameTiles);
        rotate();
        rotate();
        movement();
        rotate();
        rotate();
    }

    public void up() {
        saveGame(gameTiles);
        rotate();
        rotate();
        rotate();
        movement();
        rotate();
    }

    public void down() {
        saveGame(gameTiles);
        rotate();
        movement();
        rotate();
        rotate();
        rotate();
    }

    public void returnMove() {
        if (!lastScore.isEmpty() && !lastGrid.isEmpty()) {
            score = lastScore.pop();
            gameTiles = lastGrid.pop();
        }
    }

    public boolean canMove() {
        if (!getEmptyTiles().isEmpty()) {
            return true;
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i + 1 != 4 && gameTiles[i][j].value == gameTiles[i + 1][j].value ||
                        j + 1 != 4 && gameTiles[i][j].value == gameTiles[i][j + 1].value) {
                    return true;
                }
            }
        }
        return false;
    }

    private void saveGame(Tile[][] currentTiles) {
        Tile[][] lastTiles = new Tile[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                lastTiles[i][j] = new Tile();
                lastTiles[i][j].value = currentTiles[i][j].value;
            }
        }
        lastGrid.push(lastTiles);
        lastScore.push(score);
    }

}
