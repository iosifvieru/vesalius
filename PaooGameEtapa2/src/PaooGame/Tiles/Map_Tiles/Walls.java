package PaooGame.Tiles.Map_Tiles;

import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;

public class Walls extends Tile {
    public Walls(int id, int wallNumber) {
        super(Assets.walls[wallNumber], id);
    }

    @Override
    public boolean IsSolid(){ return true; }
}
