package PaooGame.Tiles.Map_Tiles;

import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;

import java.awt.image.BufferedImage;

public class InteriorWalls extends Tile {
    public InteriorWalls(int id, int wallNumber) {
        super(Assets.interior_walls[wallNumber], id);
    }

    @Override
    public boolean IsSolid(){ return false; }
}
