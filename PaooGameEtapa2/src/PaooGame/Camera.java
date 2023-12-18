package PaooGame;

import PaooGame.Tiles.Tile;

public class Camera {
    private float x;
    private float y;
    private final float width;
    private final float height;
    private float mapHeight;
    private float mapWidth;
    private final RefLinks refLink;

    //constructor
    public Camera(RefLinks reflink, float x, float y, float width, float height){
        this.refLink = reflink;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        mapHeight =  reflink.GetMap().getHeight() * Tile.TILE_HEIGHT;
        mapWidth =  reflink.GetMap().getWidth() * Tile.TILE_WIDTH;
    }

    public void move(float playerX, float playerY){

        float newX = playerX - getWidth() / 2 + Tile.TILE_HEIGHT;
        float newY = playerY - getHeight() / 2 + Tile.TILE_HEIGHT;

        newX = Math.max(0, Math.min(newX, mapWidth - getWidth()));
        newY = Math.max(0, Math.min(newY, mapHeight - getHeight()));

        x = newX;
        y = newY;
    }

    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }
    public float getWidth(){
        return width;
    }
    public float getHeight(){
        return height;
    }

    public void setMapHeight(float size){
        this.mapHeight = size;
    }

    public void setMapWidth(float size){
        this.mapWidth = size;
    }
}
