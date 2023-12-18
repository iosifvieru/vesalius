package PaooGame;

import PaooGame.Items.*;
import PaooGame.Tiles.Tile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class LevelLoader {

    private File file;
    private RefLinks refLink;

    //private List<String> levels = new LinkedList<>();

    public int HeroX = 0;
    public int HeroY = 0;
    public LevelLoader(RefLinks refLinks, String levelName){
        this.file = new File(getClass().getResource("/levels/" + levelName).getPath());
        //LoadAllLevels("");
        //this.file = new File(levels.get(0));
        this.refLink = refLinks;
        LoadPlayerPosition();
    }

    public void LoadPlayerPosition(){
        try {
            Scanner input = new Scanner(file);
            while(input.hasNextLine()){
                String line = input.nextLine();
                if(line.equals("<HERO POS>")){
                    line = input.nextLine();
                    //System.out.printf(line);
                    String[] coords = line.split(" ");

                    HeroX = Integer.parseInt(coords[0]) * Tile.TILE_WIDTH;
                    HeroY = Integer.parseInt(coords[1]) * Tile.TILE_HEIGHT;
                }
            }
            input.close();
        } catch (IOException e){
            System.out.println(e);
        }
    }

    public ArrayList<Enemy> LoadEnemy(){
        ArrayList<Enemy> tempList = new ArrayList<>();
        try {
            Scanner input = new Scanner(file);
            while(input.hasNextLine()){
                String line = input.nextLine();
                if(line.equals("<ENEMY>")) {
                    line = input.nextLine();
                    int noOf = Integer.parseInt(line);
                    for (int i = 0; i < noOf; i++){
                        line = input.nextLine();
                        String[] data = line.split(" ");
                        Enemy tempEnemy = EnemyFactory.createEnemy(refLink, Integer.parseInt(data[0]) * Tile.TILE_HEIGHT, Integer.parseInt(data[1]) * Tile.TILE_WIDTH, Integer.parseInt(data[2]));
                        tempList.add(tempEnemy);
                    }
                }
            }
            input.close();
        } catch (IOException e){
            System.out.println(e);
        }
        return tempList;
    }
    public ArrayList<Coin> LoadCoins(){
        ArrayList<Coin> tempCoins = new ArrayList<>();
        try {
            Scanner input = new Scanner(file);
            while(input.hasNextLine()){
                String line = input.nextLine();
                if(line.equals("<COINS>")) {
                    line = input.nextLine();
                    int noOf = Integer.parseInt(line);
                    for (int i = 0; i < noOf; i++){
                        line = input.nextLine();
                        String[] data = line.split(" ");
                        Coin tempCoin = ItemFactory.createCoin(refLink, Integer.parseInt(data[0]) * Tile.TILE_WIDTH, Integer.parseInt(data[1]) * Tile.TILE_HEIGHT, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
                        tempCoins.add(tempCoin);
                    }
                }
            }
            input.close();
        } catch (IOException e){
            System.out.println(e);
        }
        return tempCoins;
    }
    public ArrayList<Crate> LoadCrates(){
        ArrayList<Crate> tempCrates = new ArrayList<>();
        try {
            Scanner input = new Scanner(file);
            while(input.hasNextLine()){
                String line = input.nextLine();
                if(line.equals("<CRATES>")) {
                    line = input.nextLine();
                    int noOf = Integer.parseInt(line);
                    for (int i = 0; i < noOf; i++){
                        line = input.nextLine();
                        String[] data = line.split(" ");
                        Crate tempCrate = ItemFactory.createCrate(refLink, Integer.parseInt(data[0]) * Tile.TILE_WIDTH, Integer.parseInt(data[1]) * Tile.TILE_HEIGHT, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
                        tempCrates.add(tempCrate);
                    }
                }
            }
            input.close();
        } catch (IOException e){
            System.out.println(e);
        }
        return tempCrates;
    }

}
