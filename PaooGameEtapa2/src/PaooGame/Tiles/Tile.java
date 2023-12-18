package PaooGame.Tiles;


import PaooGame.Tiles.Map_Tiles.*;

import java.awt.*;
import java.awt.image.BufferedImage;

/*! \class public class Tile
    \brief Retine toate dalele intr-un vector si ofera posibilitatea regasirii dupa un id.
 */
public class Tile
{
    private static final int NO_TILES   = 64;
    public static Tile[] tiles          = new Tile[NO_TILES];       /*!< Vector de referinte de tipuri de dale.*/

        /// De remarcat ca urmatoarele dale sunt statice si publice. Acest lucru imi permite sa le am incarcate
        /// o singura data in memorie

    public static Tile background = new BackgroundTile(0);


    //walls
    public static Tile wall0 = new Walls(1, 0);
    public static Tile wall1 = new Walls(2, 1);
    public static Tile wall2 = new Walls(3, 2);
    public static Tile wall3 = new Walls(4, 3);
    public static Tile wall4 = new Walls(5, 4);
    public static Tile wall5 = new Walls(6, 5);



    //borders
    public static Tile border0 = new Borders(7, 0);
    public static Tile border1 = new Borders(8, 1);
    public static Tile border2 = new Borders(9, 2);
    public static Tile border3 = new Borders(10, 3);
    public static Tile border4 = new Borders(11, 4);
    public static Tile border5 = new Borders(12, 5);
    public static Tile border6 = new Borders(13, 6);
    public static Tile border7 = new Borders(14, 7);

    //interior walls
    public static Tile interiorWall0 = new InteriorWalls(15, 0);
    public static Tile interiorWall1 = new InteriorWalls(16, 1);
    public static Tile interiorWall2 = new InteriorWalls(17, 2);
    public static Tile interiorWall3 = new InteriorWalls(18, 3);
    public static Tile interiorWall4 = new InteriorWalls(19, 4);
    public static Tile interiorWall5 = new InteriorWalls(20, 5);

    //open door
    public static Tile openDoor0 = new OpenDoor(21, 0);
    public static Tile openDoor1 = new OpenDoor(22, 1);
    public static Tile openDoor2 = new OpenDoor(23, 2);
    public static Tile openDoor3 = new OpenDoor(24, 3);
    public static Tile openDoor4 = new OpenDoor(25, 4);
    public static Tile openDoor5 = new OpenDoor(26, 5);

    //office
    public static Tile office0 = new Office(27, 0);
    public static Tile office1 = new Office(28, 1);
    public static Tile office2 = new Office(29, 2);
    public static Tile office3 = new Office(30, 3);
    public static Tile office4 = new Office(34, 4);

    //chairs
    public static Tile chair0 = new Chairs(31, 0);
    public static Tile chair1 = new Chairs(32, 1);
    public static Tile chair2 = new Chairs(33, 2);
    public static Tile chair3 = new Chairs(35, 3);
    public static Tile chair4 = new Chairs(36, 4);

    public static final int TILE_WIDTH  = 48;                       /*!< Latimea unei dale.*/
    public static final int TILE_HEIGHT = 48;                       /*!< Inaltimea unei dale.*/

    protected BufferedImage img;                                    /*!< Imaginea aferenta tipului de dala.*/
    protected final int id;                                         /*!< Id-ul unic aferent tipului de dala.*/

    /*! \fn public Tile(BufferedImage texture, int id)
        \brief Constructorul aferent clasei.

        \param image Imaginea corespunzatoare dalei.
        \param id Id-ul dalei.
     */
    public Tile(BufferedImage image, int idd)
    {
        img = image;
        id = idd;

        tiles[id] = this;
    }

    /*! \fn public void Update()
        \brief Actualizeaza proprietatile dalei.
     */
    public void Update()
    {

    }

    /*! \fn public void Draw(Graphics g, int x, int y)
        \brief Deseneaza in fereastra dala.

        \param g Contextul grafic in care sa se realizeze desenarea
        \param x Coordonata x in cadrul ferestrei unde sa fie desenata dala
        \param y Coordonata y in cadrul ferestrei unde sa fie desenata dala
     */
    public void Draw(Graphics g, int x, int y)
    {
            /// Desenare dala
        g.drawImage(img, x, y, TILE_WIDTH, TILE_HEIGHT, null);
    }

    /*! \fn public boolean IsSolid()
        \brief Returneaza proprietatea de dala solida (supusa coliziunilor) sau nu.
     */
    public boolean IsSolid()
    {
        return false;
    }

    /*! \fn public int GetId()
        \brief Returneaza id-ul dalei.
     */
    public int GetId()
    {
        return id;
    }
}
