package PaooGame.Maps;

import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

import java.awt.*;
import java.io.*;
import java.util.Scanner;

/*! \class public class Map
    \brief Implementeaza notiunea de harta a jocului.
 */
public class Map
{
    private final RefLinks refLink;   /*!< O referinte catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.*/
    private int width;          /*!< Latimea hartii in numar de dale.*/
    private int height;         /*!< Inaltimea hartii in numar de dale.*/
    private final File mapFile;
    private int [][] tiles;     /*!< Referinta catre o matrice cu codurile dalelor ce vor construi harta.*/
    private int [][] tiles_overlay;     /*!< Referinta catre o matrice cu codurile dalelor ce vor construi harta.*/

    /*! \fn public Map(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinte catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public Map(RefLinks refLink, String levelName){
            /// Retine referinta "shortcut".
        this.refLink = refLink;

        this.mapFile = new File(levelName);
        ///incarca harta de start. Functia poate primi ca argument id-ul hartii ce poate fi incarcat.
        LoadWorld();
    }

    /*! \fn public  void Update()
        \brief Actualizarea hartii in functie de evenimente (un copac a fost taiat)
     */
    public  void Update()
    {

    }

    /*! \fn public void Draw(Graphics g)
        \brief Functia de desenare a hartii.

        \param g Contextl grafi in care se realizeaza desenarea.
     */

    public void Draw(Graphics g)
    {
            ///Se parcurge matricea de dale (codurile aferente) si se deseneaza harta respectiva
        //for(int y = 0; y < refLink.GetGame().GetHeight()/Tile.TILE_HEIGHT; y++)
        for(int y = 0 ; y < height; y++)
        {
            //for(int x = 0; x < refLink.GetGame().GetWidth()/Tile.TILE_WIDTH; x++)
            for(int x = 0 ; x < width; x++)
            {
                GetTile(x, y).Draw(g, x * Tile.TILE_HEIGHT, y * Tile.TILE_WIDTH);

                try {
                    GetTile(x, y, true).Draw(g, x * Tile.TILE_HEIGHT, y * Tile.TILE_WIDTH);
                } catch(Exception e){
                }
            }
        }
    }

    public void DrawOverlay(Graphics g){
        for(int y = 0 ; y < height; y++)
        {
            //for(int x = 0; x < refLink.GetGame().GetWidth()/Tile.TILE_WIDTH; x++)
            for(int x = 0 ; x < width; x++)
            {
                try {
                    GetTile(x, y, true).Draw(g, x * Tile.TILE_HEIGHT, y * Tile.TILE_WIDTH);
                } catch(Exception e){
                }
            }
        }
    }

    /*! \fn public Tile GetTile(int x, int y)
        \brief Intoarce o referinta catre dala aferenta codului din matrice de dale.

        In situatia in care dala nu este gasita datorita unei erori ce tine de cod dala, coordonate gresite etc se
        intoarce o dala predefinita (ex. grassTile, mountainTile)
     */
    public Tile GetTile(int x, int y)
    {
        if(x < 0 || y < 0 || x >= width || y >= height)
        {
            return Tile.background;
        }
        Tile t = Tile.tiles[tiles[x][y]];
        if(t == null)
        {
            return Tile.openDoor0;
        }
        return t;
    }

    public Tile GetTile(int x, int y, boolean Overlay)
    {
        if(x < 0 || y < 0 || x >= width || y >= height)
        {
            return null;
        }
        if(tiles_overlay[x][y] < 0){
            return null;
        }
        Tile t = Tile.tiles[tiles_overlay[x][y]];
        if(t == null)
        {
            return null;
        }
        return t;
    }

    /*! \fn private void LoadWorld()
        \brief Functie de incarcare a hartii jocului.
        Aici se poate genera sau incarca din fisier harta. Momentan este incarcata static.
     */

            /*
                Formatul fisierului.
                <MAP LENGHT>
                ....
                <MAP TILES>
                ...
                <MAP OVERLAY>
                ...

             */

    private void LoadWorld(){
        try {
            Scanner input = new Scanner(this.mapFile);
            String line = input.nextLine();
            System.out.println(line);
            if(line.equals("<MAP LENGHT>")){ // prima linia a fisierului.
                line = input.nextLine();
                String[] temp = line.split(" ");
                this.width = Integer.parseInt(temp[1]);
                this.height = Integer.parseInt(temp[0]);

            }

            tiles = new int[width][height];
            tiles_overlay = new int[width][height];

            if(input.nextLine().equals("<MAP TILES>")){
                int y = 0;
                while(input.hasNextLine()){
                    line = input.nextLine();
                    //System.out.println(line);
                    if(line.equals("<MAP OVERLAY>")){
                        break;
                    }
                    String[] numbers = line.split(" ");
                        //System.out.println(line);
                        for (int x = 0; x < width; x++) {
                            //System.out.println(Integer.parseInt(numbers[y]));
                            tiles[x][y] = Integer.parseInt(numbers[x]);
                            //tiles_overlay[x][y] = Level1_Overlay(y, x);
                        }
                    y++;
                }
                if(line.equals("<MAP OVERLAY>")) {

                    y = 0;
                    while (input.hasNextLine()) {
                        line = input.nextLine();
                        if(line.equals("<ENEMY>")){
                            return;
                        }
                        String[] numbers = line.split(" ");
                        for (int x = 0; x < width; x++) {
                            //tiles[x][y] = Integer.parseInt(numbers[x]);
                            tiles_overlay[x][y] = Integer.parseInt(numbers[x]);
                        }
                    y++;
                    }
                }
            }
            input.close();
        } catch(FileNotFoundException e){
            //System.out.println(e);
            e.printStackTrace();
        }
    }

    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
}