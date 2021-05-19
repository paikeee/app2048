package myApp;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


class Tile {

    int value;

    public Tile() {
        this.value = 0;
    }

    public static ImageView getTile(int value) throws FileNotFoundException {
        switch (value) {
            case 2 -> {
                Image image = new Image(new FileInputStream("tiles/tile2.png"));
                return new ImageView(image);
            }
            case 4 -> {
                Image image = new Image(new FileInputStream("tiles/tile4.png"));
                return new ImageView(image);
            }
            case 8 -> {
                Image image = new Image(new FileInputStream("tiles/tile8.png"));
                return new ImageView(image);
            }
            case 16 -> {
                Image image = new Image(new FileInputStream("tiles/tile16.png"));
                return new ImageView(image);
            }
            case 32 -> {
                Image image = new Image(new FileInputStream("tiles/tile32.png"));
                return new ImageView(image);
            }
            case 64 -> {
                Image image = new Image(new FileInputStream("tiles/tile64.png"));
                return new ImageView(image);
            }
            case 128 -> {
                Image image = new Image(new FileInputStream("tiles/tile128.png"));
                return new ImageView(image);
            }
            case 256 -> {
                Image image = new Image(new FileInputStream("tiles/tile256.png"));
                return new ImageView(image);
            }
            case 512 -> {
                Image image = new Image(new FileInputStream("tiles/tile512.png"));
                return new ImageView(image);
            }
            case 1024 -> {
                Image image = new Image(new FileInputStream("tiles/tile1024.png"));
                return new ImageView(image);
            }
            case 2048 -> {
                Image image = new Image(new FileInputStream("tiles/tile2048.png"));
                return new ImageView(image);
            }
            default -> {
                Image image = new Image(new FileInputStream("tiles/tile0.png"));
                return new ImageView(image);
            }
        }
    }

}
