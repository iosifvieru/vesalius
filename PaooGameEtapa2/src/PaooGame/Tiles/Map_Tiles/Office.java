package PaooGame.Tiles.Map_Tiles;

import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;

import java.awt.image.BufferedImage;

public class Office extends Tile {
    public Office(int id, int office) {

        super(Assets.office[office], id);
    }

    @Override
    public boolean IsSolid(){ return true; }
}
