package PaooGame.Tiles.Map_Tiles;

import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;


public class BackgroundTile extends Tile {
    public BackgroundTile(int idd) {
        super(Assets.background, idd);
    }

    @Override
    public boolean IsSolid()
    {
        return false;
    }
}
