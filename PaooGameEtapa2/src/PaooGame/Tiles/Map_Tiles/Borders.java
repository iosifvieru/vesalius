package PaooGame.Tiles.Map_Tiles;

import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;

public class Borders extends Tile {
    public Borders(int id, int borderNumber) {
        super(Assets.borders[borderNumber], id);
    }

    @Override
    public boolean IsSolid(){ return true; }
}
