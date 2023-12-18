package PaooGame.Items;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Date;

import PaooGame.Items.CharacterStateDesign.Attack;
import PaooGame.Items.CharacterStateDesign.Death;
import PaooGame.Items.CharacterStateDesign.EntityState;
import PaooGame.Items.CharacterStateDesign.Idle;
import PaooGame.RefLinks;
import PaooGame.Graphics.Assets;
import PaooGame.States.HeroIsDead;
import PaooGame.States.State;
import PaooGame.States.UserInputState;
import PaooGame.Tiles.Tile;

/*! \class public class Hero extends Character
    \brief Implementeaza notiunea de erou/player (caracterul controlat de jucator).

    Elementele suplimentare pe care le aduce fata de clasa de baza sunt:
        imaginea (acest atribut poate fi ridicat si in clasa de baza)
        deplasarea
        atacul
        dreptunghiul de coliziune

 */
public class Hero extends Character
{
    private BufferedImage image;    /*!< Referinta catre imaginea curenta a eroului.*/

    //nr de monede colectate de jucator.
    private int coins_collected = 0;
    private int bulletNumber;
    //timer
    public long timer = 0;
    //texture counter
    public int textureCounter = 0;
    public long lastClickTime = 0;
    //private long walkTimer = 0;
    public boolean isDead = false;
    private EntityState entityState;
    public int deathstate = 0;

    // PENTRU BAZA DE DATE.

    //USERNAME
    public String Username;
    // SCOR
    public int SCORE = 0;

    // LEVEL TIMER
    Date startDate = new Date();
    public int seconds;
    public int minutes;

    /*! \fn public Hero(RefLinks refLink, float x, float y)
        \brief Constructorul de initializare al clasei Hero.

        \param refLink Referinta catre obiectul shortcut (obiect ce retine o serie de referinte din program).
        \param x Pozitia initiala pe axa X a eroului.
        \param y Pozitia initiala pe axa Y a eroului.
     */
    public Hero(RefLinks refLink, float x, float y)
    {
            ///Apel al constructorului clasei de baza
        super(refLink, x,y, Character.DEFAULT_CREATURE_WIDTH, Character.DEFAULT_CREATURE_HEIGHT);
            ///Seteaza imaginea de start a eroului
        //image = Assets.heroLeft;
        LoadTexture(Assets.heroIdle, 4);
        //gunImage = Assets.heroLeftGun;
        width = width*2;
        height = height*2;
        bulletNumber = 30;

        this.life = 100;
            ///Stabilieste pozitia relativa si dimensiunea dreptunghiului de coliziune, starea implicita(normala)
        normalBounds.x = 32;
        normalBounds.y = 32;
        normalBounds.width = 32;
        normalBounds.height = 48;

        entityState = new Idle(this);

        this.Username = refLink.username;

    }
    /*! \fn public void Update()
        \brief Actualizeaza pozitia si imaginea eroului.
     */
    @Override
    public void Update()
    {

        //System.out.println(SCORE);
        Timer(this.startDate);
        if(deathstate == 35){
            State.SetState(new HeroIsDead(refLink));
            return;
        }
        if(life <= 0){
            setState(new Death(this));
        }
        ///Verifica daca a fost apasata o tasta
        GetInput();
        ///Actualizeaza pozitia
        Move();

        ///Actualizeaza imaginea
        if(refLink.GetKeyManager().left)
        {
            //apeleaza functia de animatie.
            LoadTexture(Assets.heroIsRunningToLeft, 5);

        }
        if(refLink.GetKeyManager().right) {

            //apeleaza functia de animatie.
            LoadTexture(Assets.heroIsRunningToRight, 5);

        }
        //LoadTexture(Assets.heroIdle, 4);

        switch(entityState.toString()){
            case "IdleState":
                entityState.IdleState();
                break;
            case "AttackState":
                entityState.AttackState();
                break;
            case "DeathState":
                entityState.DeathState();
                break;
        }
        if(refLink.GetKeyManager().shootUp || refLink.GetKeyManager().shootDown || refLink.GetKeyManager().shootLeft || refLink.GetKeyManager().shootRight) {

            long currentTime = System.currentTimeMillis();
            if (currentTime - lastClickTime > 200) {
                if (getBulletNumber() > 0) {
                    lastClickTime = currentTime;
                    setState(new Attack(this));
                }
            }
        }
    }
    /*
        Aceasta functie de mai jos are ca scop incarcarea texturilor dintr-o animatie.

        Se foloseste de variabila textureCounter ( care este actualizata o data la 200 ms )
        si o foloseste drept index in vectorul de texuturi primit ca parametru.

        In momentul in care textureCounter depaseste dimensiunea data prin parametru se reseteaza la 0.
     */
    public void LoadTexture(BufferedImage[] texture, int dimension){
        if(textureCounter >= dimension){
            textureCounter = 0;
        }
        long currentTime = System.currentTimeMillis();
        if(currentTime - timer > 120) {
            textureCounter++;
            image = texture[textureCounter];
            timer = currentTime;
            if(textureCounter >= dimension){
                textureCounter = 0;
            }
        }
    }


    public void Timer(Date startDate){
        Date endDate = new Date();

        // calcul

        this.seconds = (int) ((endDate.getTime() - startDate.getTime()) / 1000) % 60;
        this.minutes = (int) ((endDate.getTime() - startDate.getTime()) / 1000) / 60;

    }

    /*! \fn private void GetInput()
        \brief Verifica daca a fost apasata o tasta din cele stabilite pentru controlul eroului.
     */

    private void GetInput()
    {
            ///Implicit eroul nu trebuie sa se deplaseze daca nu este apasata o tasta
        xMove = 0;
        yMove = 0;


            ///Verificare apasare tasta "sus"
        if (refLink.GetKeyManager().up) {

            // DETECTARE DE COLIZIUNE.

            //    Codul de mai jos doreste sa calculeze pozitia urmatoare decat cea initiala pentru a verifica
            //    daca jucatorul se va afla sau nu intr-un tile solid.


            int nextX, nextY;
            nextY = (int) (y + bounds.y - speed) / Tile.TILE_HEIGHT;
            nextX = (int)this.x / Tile.TILE_HEIGHT;

            if(refLink.GetMap().GetTile(nextX, nextY).IsSolid()){
                yMove = 0;
            } else {
                yMove = -speed;
            }
        }
            ///Verificare apasare tasta "jos"
        if (refLink.GetKeyManager().down) {
            int nextX, nextY;
            nextY = (int) (y + bounds.y + bounds.y) / Tile.TILE_HEIGHT;
            nextX = (int)this.x / Tile.TILE_HEIGHT;
            if(refLink.GetMap().GetTile(nextX, nextY).IsSolid()){
                yMove = 0;
            } else {
                yMove = speed;
            }
        }
            ///Verificare apasare tasta "left"
        if (refLink.GetKeyManager().left) {
            int nextX, nextY;
            nextY = (int) (this.y) / Tile.TILE_HEIGHT;
            nextX = (int)(this.x + bounds.x) / Tile.TILE_HEIGHT;
            if(refLink.GetMap().GetTile(nextX, nextY).IsSolid()){
                xMove = 0;
            } else {
                xMove = -speed;
            }
        }
            ///Verificare apasare tasta "dreapta"
        if (refLink.GetKeyManager().right) {
            int nextX, nextY;
            nextY = (int) (this.y) / Tile.TILE_HEIGHT;
            nextX = (int)(this.x + bounds.x + bounds.x) / Tile.TILE_HEIGHT;
            if(refLink.GetMap().GetTile(nextX, nextY).IsSolid()){
                xMove = 0;
            } else {
                xMove = speed;
            }
        }
    }

    /*! \fn public void Draw(Graphics g)
        \brief Randeaza/deseneaza eroul in noua pozitie.

        \brief g Contextul grafic in care trebuie efectuata desenarea eroului.
     */
    @Override
    public void Draw(Graphics g)
    {

        g.drawImage(image, (int)x, (int)y, width, height, null);

        //g.setColor(Color.white);
        //g.drawRect((int)(x + bounds.x), (int)(y + bounds.y), bounds.width, bounds.height);
    }

    public void addBullets(int number){
        bulletNumber += number;
    }

    public int getBulletNumber(){
        return bulletNumber;
    }
    public void addCoins(int number){
        coins_collected += number;
    }

    public int getCoins(){
        return coins_collected;
    }

    public void setState(EntityState state){
        this.entityState = state;
    }
    public void setLife(int life){
        this.life = life;
    }
    public void setBullets(int bullets){
        this.bulletNumber = bullets;
    }
}
