package PaooGame.Graphics;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

/*! \class public class Assets
    \brief Clasa incarca fiecare element grafic necesar jocului.

    Game assets include tot ce este folosit intr-un joc: imagini, sunete, harti etc.
 */
public class Assets
{
        /// Referinte catre elementele grafice (dale) utilizate in joc.
    //public static BufferedImage heroLeft;
    //public static BufferedImage heroRight;
    public static BufferedImage[] heroIdle = new BufferedImage[5];
    public static BufferedImage[] heroIsRunningToLeft = new BufferedImage[6];
    public static BufferedImage[] heroIsRunningToRight = new BufferedImage[6];
    public static BufferedImage[] heroIsDead = new BufferedImage[8];

    //enemy
    public static BufferedImage enemyRight;
    public static BufferedImage enemyLeft;

    public static BufferedImage[] enemyIsRunningToRight = new BufferedImage[6];
    public static BufferedImage[] enemyIsRunningToLeft = new BufferedImage[6];
    public static BufferedImage[] enemyDeath = new BufferedImage[8];

    public static BufferedImage[] enemyTier1Right = new BufferedImage[6];
    public static BufferedImage[] enemyTier1Left = new BufferedImage[6];
    public static BufferedImage[] enemyTier1Death = new BufferedImage[8];

    public static BufferedImage[] enemyTier3Right = new BufferedImage[6];
    public static BufferedImage[] enemyTier3Left = new BufferedImage[6];
    public static BufferedImage[] enemyTier3Death = new BufferedImage[8];

    //map tiles
    public static BufferedImage background;
    public static BufferedImage[] walls = new BufferedImage[6];
    public static BufferedImage[] borders = new BufferedImage[8];
    public static BufferedImage[] interior_walls = new BufferedImage[6];
    public static BufferedImage[] open_door = new BufferedImage[6];
    public static BufferedImage[] office = new BufferedImage[5];
    public static BufferedImage[] coin = new BufferedImage[9];
    public static BufferedImage[] chairs = new BufferedImage[5];
    public static BufferedImage bullet = ImageLoader.LoadImage("/textures/SpongeBullet.png");
    public static BufferedImage crate = ImageLoader.LoadImage("/textures/16x16Crate.png");

    public static BufferedImage[] heart = new BufferedImage[2];


    /*! \fn public static void Init()
        \brief Functia initializaza referintele catre elementele grafice utilizate.

        Aceasta functie poate fi rescrisa astfel incat elementele grafice incarcate/utilizate
        sa fie parametrizate. Din acest motiv referintele nu sunt finale.
     */
    public static void Init()
    {
        /// Se creaza temporar un obiect SpriteSheet initializat prin intermediul clasei ImageLoader
        SpriteSheet tiles = new SpriteSheet(ImageLoader.LoadImage("/textures/tiles2.png"));

        //hero spritesheet

        //SpriteSheet heroSheet = new SpriteSheet(ImageLoader.LoadImage("/textures/Gunner_Blue_Idle2.png"));
        SpriteSheet heroIdleSheet = new SpriteSheet(ImageLoader.LoadImage("/textures/Vesalius_Idle.png"));
        SpriteSheet heroIsRunningtoR = new SpriteSheet(ImageLoader.LoadImage("/textures/Vesalius_Run_Right.png"));
        SpriteSheet heroIsRunningtoL = new SpriteSheet(ImageLoader.LoadImage("/textures/Vesalius_Run_Left.png"));
        SpriteSheet heroIsDeadSpriteSheet = new SpriteSheet(ImageLoader.LoadImage("/textures/Vesalius_Death.png"));
        SpriteSheet heartSpriteSheet = new SpriteSheet(ImageLoader.LoadImage("/textures/hearts.png"));

        //enemy spritesheet
        SpriteSheet enemySheet1 = new SpriteSheet(ImageLoader.LoadImage("/textures/Gunner_Yellow_Idle.png"));
        SpriteSheet enemyIsRunningR = new SpriteSheet(ImageLoader.LoadImage("/textures/Gunner_Yellow_Run_Right.png"));
        SpriteSheet enemyIsRunningL = new SpriteSheet(ImageLoader.LoadImage("/textures/Gunner_Yellow_Run_Left.png"));
        SpriteSheet enemyDeathSheet = new SpriteSheet(ImageLoader.LoadImage("/textures/Gunner_Yellow_Death.png"));

        // tier 1
        SpriteSheet enemyTier1SheetR = new SpriteSheet(ImageLoader.LoadImage("/textures/EnemyTier1/Gunner_Red_Run.png"));
        SpriteSheet enemyTier1SheetL = new SpriteSheet(ImageLoader.LoadImage("/textures/EnemyTier1/Gunner_Red_Run_Left.png"));
        SpriteSheet enemyTier1DeathSheet = new SpriteSheet(ImageLoader.LoadImage("/textures/EnemyTier1/Gunner_Red_Death.png"));

        //tier 3
        SpriteSheet enemyTier3SheetR = new SpriteSheet(ImageLoader.LoadImage("/textures/EnemyBOSS/Gunner_Black_Run.png"));
        SpriteSheet enemyTier3SheetL = new SpriteSheet(ImageLoader.LoadImage("/textures/EnemyBOSS/Gunner_Black_Run_Left.png"));
        SpriteSheet enemyTier3DeathSheet = new SpriteSheet(ImageLoader.LoadImage("/textures/EnemyBOSS/Gunner_Black_Death.png"));

        //coin spritesheet
        SpriteSheet coinSheet = new SpriteSheet(ImageLoader.LoadImage("/textures/coin.png"));

        /// Se obtin subimaginile corespunzatoare elementelor necesare.

        // imagini erou.
        //heroRight = heroSheet.crop(0, 0);
        //heroRightGun = heroSheet.crop(1, 0);
        //heroLeft = heroSheet.crop(2, 0);
        //heroLeftGun = heroSheet.crop(3, 0);

        heroIdle[0] = heroIdleSheet.crop(0,0);
        heroIdle[1] = heroIdleSheet.crop(1,0);
        heroIdle[2] = heroIdleSheet.crop(2,0);
        heroIdle[3] = heroIdleSheet.crop(3,0);
        heroIdle[4] = heroIdleSheet.crop(4,0);

        heroIsRunningToRight[0] = heroIsRunningtoR.crop(0,0);
        heroIsRunningToRight[1] = heroIsRunningtoR.crop(1,0);
        heroIsRunningToRight[2] = heroIsRunningtoR.crop(2,0);
        heroIsRunningToRight[3] = heroIsRunningtoR.crop(3,0);
        heroIsRunningToRight[4] = heroIsRunningtoR.crop(4,0);
        heroIsRunningToRight[5] = heroIsRunningtoR.crop(5,0);

        heroIsRunningToLeft[0] = heroIsRunningtoL.crop(0, 0);
        heroIsRunningToLeft[1] = heroIsRunningtoL.crop(1, 0);
        heroIsRunningToLeft[2] = heroIsRunningtoL.crop(2, 0);
        heroIsRunningToLeft[3] = heroIsRunningtoL.crop(3, 0);
        heroIsRunningToLeft[4] = heroIsRunningtoL.crop(4, 0);
        heroIsRunningToLeft[5] = heroIsRunningtoL.crop(5, 0);

        heroIsDead[0] = heroIsDeadSpriteSheet.crop(0, 0);
        heroIsDead[1] = heroIsDeadSpriteSheet.crop(1, 0);
        heroIsDead[2] = heroIsDeadSpriteSheet.crop(2, 0);
        heroIsDead[3] = heroIsDeadSpriteSheet.crop(3, 0);
        heroIsDead[4] = heroIsDeadSpriteSheet.crop(4, 0);
        heroIsDead[5] = heroIsDeadSpriteSheet.crop(5, 0);
        heroIsDead[6] = heroIsDeadSpriteSheet.crop(6, 0);
        heroIsDead[7] = heroIsDeadSpriteSheet.crop(7, 0);

        heart[0] = heartSpriteSheet.crop(0, 0);
        heart[1] = heartSpriteSheet.crop(1, 0);

        // enemy tier 0
        enemyRight = enemySheet1.crop(0,0);
        enemyLeft = enemySheet1.crop(1, 0);

        //enemy run tier0
        enemyIsRunningToRight[0] = enemyIsRunningR.crop(0, 0);
        enemyIsRunningToRight[1] = enemyIsRunningR.crop(1, 0);
        enemyIsRunningToRight[2] = enemyIsRunningR.crop(2, 0);
        enemyIsRunningToRight[3] = enemyIsRunningR.crop(3, 0);
        enemyIsRunningToRight[4] = enemyIsRunningR.crop(4, 0);
        enemyIsRunningToRight[5] = enemyIsRunningR.crop(5, 0);

        enemyIsRunningToLeft[0] = enemyIsRunningL.crop(0, 0);
        enemyIsRunningToLeft[1] = enemyIsRunningL.crop(1, 0);
        enemyIsRunningToLeft[2] = enemyIsRunningL.crop(2, 0);
        enemyIsRunningToLeft[3] = enemyIsRunningL.crop(3, 0);
        enemyIsRunningToLeft[4] = enemyIsRunningL.crop(4, 0);
        enemyIsRunningToLeft[5] = enemyIsRunningL.crop(5, 0);

        enemyDeath[0] = enemyDeathSheet.crop(0, 0);
        enemyDeath[1] = enemyDeathSheet.crop(1, 0);
        enemyDeath[2] = enemyDeathSheet.crop(2, 0);
        enemyDeath[3] = enemyDeathSheet.crop(3, 0);
        enemyDeath[4] = enemyDeathSheet.crop(4, 0);
        enemyDeath[5] = enemyDeathSheet.crop(5, 0);
        enemyDeath[6] = enemyDeathSheet.crop(6, 0);
        enemyDeath[7] = enemyDeathSheet.crop(7, 0);

        enemyTier1Right[0] = enemyTier1SheetR.crop(0, 0);
        enemyTier1Right[1] = enemyTier1SheetR.crop(1, 0);
        enemyTier1Right[2] = enemyTier1SheetR.crop(2, 0);
        enemyTier1Right[3] = enemyTier1SheetR.crop(3, 0);
        enemyTier1Right[4] = enemyTier1SheetR.crop(4, 0);
        enemyTier1Right[5] = enemyTier1SheetR.crop(5, 0);

        enemyTier1Left[0] = enemyTier1SheetL.crop(0, 0);
        enemyTier1Left[1] = enemyTier1SheetL.crop(1, 0);
        enemyTier1Left[2] = enemyTier1SheetL.crop(2, 0);
        enemyTier1Left[3] = enemyTier1SheetL.crop(3, 0);
        enemyTier1Left[4] = enemyTier1SheetL.crop(4, 0);
        enemyTier1Left[5] = enemyTier1SheetL.crop(5, 0);

        enemyTier1Death[0] = enemyTier1DeathSheet.crop(0, 0);
        enemyTier1Death[1] = enemyTier1DeathSheet.crop(1, 0);
        enemyTier1Death[2] = enemyTier1DeathSheet.crop(2, 0);
        enemyTier1Death[3] = enemyTier1DeathSheet.crop(3, 0);
        enemyTier1Death[4] = enemyTier1DeathSheet.crop(4, 0);
        enemyTier1Death[5] = enemyTier1DeathSheet.crop(5, 0);
        enemyTier1Death[6] = enemyTier1DeathSheet.crop(6, 0);
        enemyTier1Death[7] = enemyTier1DeathSheet.crop(7, 0);

        enemyTier3Left[0] = enemyTier3SheetL.crop(0, 0);
        enemyTier3Left[1] = enemyTier3SheetL.crop(1, 0);
        enemyTier3Left[2] = enemyTier3SheetL.crop(2, 0);
        enemyTier3Left[3] = enemyTier3SheetL.crop(3, 0);
        enemyTier3Left[4] = enemyTier3SheetL.crop(4, 0);
        enemyTier3Left[5] = enemyTier3SheetL.crop(5, 0);

        enemyTier3Right[0] = enemyTier3SheetR.crop(0, 0);
        enemyTier3Right[1] = enemyTier3SheetR.crop(1, 0);
        enemyTier3Right[2] = enemyTier3SheetR.crop(2, 0);
        enemyTier3Right[3] = enemyTier3SheetR.crop(3, 0);
        enemyTier3Right[4] = enemyTier3SheetR.crop(4, 0);
        enemyTier3Right[5] = enemyTier3SheetR.crop(5, 0);

        enemyTier3Death[0] = enemyTier3DeathSheet.crop(0, 0);
        enemyTier3Death[1] = enemyTier3DeathSheet.crop(1, 0);
        enemyTier3Death[2] = enemyTier3DeathSheet.crop(2, 0);
        enemyTier3Death[3] = enemyTier3DeathSheet.crop(3, 0);
        enemyTier3Death[4] = enemyTier3DeathSheet.crop(4, 0);
        enemyTier3Death[5] = enemyTier3DeathSheet.crop(5, 0);
        enemyTier3Death[6] = enemyTier3DeathSheet.crop(6, 0);
        enemyTier3Death[7] = enemyTier3DeathSheet.crop(7, 0);

        //coin tiles
        coin[0] = coinSheet.crop(0, 0, 32, 32);
        coin[1] = coinSheet.crop(1, 0, 32, 32);
        coin[2] = coinSheet.crop(2, 0, 32, 32);
        coin[3] = coinSheet.crop(3, 0, 32, 32);
        coin[4] = coinSheet.crop(4, 0, 32, 32);
        coin[5] = coinSheet.crop(5, 0, 32, 32);
        coin[6] = coinSheet.crop(6, 0, 32, 32);
        coin[7] = coinSheet.crop(7, 0, 32, 32);
        coin[8] = coinSheet.crop(8, 0, 32, 32);
        //coin[9] = coinSheet.crop(9, 0, 32, 32);

        // map tiles
        background = tiles.crop(1,1, 8, 8);
        //background = ImageLoader.LoadImage("/textures/floor_2.png");

        //walls
        walls[0] = tiles.crop(0, 4, 8, 8);
        walls[1] = tiles.crop(1, 4, 8, 8);
        walls[2] = tiles.crop(2, 4, 8, 8);
        walls[3] = tiles.crop(0, 5, 8, 8);
        walls[4] = tiles.crop(1, 5, 8, 8);
        walls[5] = tiles.crop(2, 5, 8,8 );

        //borders
        borders[0] = tiles.crop(0,0, 8, 8);
        borders[1] = tiles.crop(1, 0, 8, 8);
        borders[2] = tiles.crop(2, 0, 8, 8);
        borders[3] = tiles.crop(0, 1, 8, 8);
        borders[4] = tiles.crop(0, 2, 8, 8);
        borders[5] = tiles.crop(1, 2, 8, 8);
        borders[6] = tiles.crop(2, 2, 8, 8);
        borders[7] = tiles.crop(2, 1, 8, 8);

        //interior walls
        interior_walls[0] = tiles.crop(3, 0, 8, 8);
        interior_walls[1] = tiles.crop(3, 1, 8, 8);
        interior_walls[2] = tiles.crop(3, 2, 8, 8);
        interior_walls[3] = tiles.crop(0, 3, 8, 8);
        interior_walls[4] = tiles.crop(1, 3, 8, 8);
        interior_walls[5] = tiles.crop(2, 3, 8, 8);

        //open door
        open_door[0] = tiles.crop(1, 8, 8, 8);
        open_door[1] = tiles.crop(2, 8, 8, 8);
        open_door[2] = tiles.crop(3, 8, 8, 8);
        open_door[3] = tiles.crop(1, 9, 8, 8);
        open_door[4] = tiles.crop(2, 9, 8, 8);
        open_door[5] = tiles.crop(3, 9, 8, 8);

        //office
        office[0] = tiles.crop(1, 10, 8, 8);
        office[1] = tiles.crop(0, 11, 8, 8);
        office[2] = tiles.crop(1, 11, 8, 8);
        office[3] = tiles.crop(2, 11, 8, 8);
        office[4] = tiles.crop(0, 10, 8, 8);

        //chairs
        chairs[0] = tiles.crop(9, 8, 8, 8);
        chairs[1] = tiles.crop(8, 8, 8, 8);
        chairs[2] = tiles.crop(8, 9, 8, 8);
        chairs[3] = tiles.crop(6, 8 ,8 ,8);
        chairs[4] = tiles.crop(5, 9 ,8 ,8);

        //computers
        //computers[0] = tiles.crop(6, 7, 8, 7);
        //[1] = tiles.crop(7, 7, 8, 8);
    }
}
