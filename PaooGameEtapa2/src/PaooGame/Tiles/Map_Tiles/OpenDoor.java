package PaooGame.Tiles.Map_Tiles;

import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;

import java.awt.image.BufferedImage;

public class OpenDoor extends Tile {
    public OpenDoor(int id, int door) {

        super(Assets.open_door[door], id);
    }
}
