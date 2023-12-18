package PaooGame.Tiles.Map_Tiles;

import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;

import java.awt.image.BufferedImage;

public class Chairs extends Tile {
    public Chairs(int id, int chairId) {
        super(Assets.chairs[chairId], id);
    }

    @Override
    public boolean IsSolid(){ return true; }
}
