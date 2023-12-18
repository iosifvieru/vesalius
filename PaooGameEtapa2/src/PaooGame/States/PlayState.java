package PaooGame.States;
import PaooGame.Database;
import PaooGame.Items.*;
import PaooGame.Items.Coin;
import PaooGame.Items.Crate;
import PaooGame.LevelLoader;
import PaooGame.RefLinks;
import PaooGame.Maps.Map;
import PaooGame.Tiles.Tile;

import java.awt.*;
import java.util.ArrayList;

/*! \class public class PlayState extends State
    \brief Implementeaza/controleaza jocul.
 */
public class PlayState extends State
{
    private final Hero hero;  /*!< Referinta catre obiectul animat erou (controlat de utilizator).*/
    private final Map map;    /*!< Referinta catre harta curenta.*/
    private final UserInterface UI;
    private final ArrayList<Enemy> enemies;

    private final ArrayList<Bullet> bullets;

    private final ArrayList<Coin> coins;
    private final ArrayList<Crate>crates;

    private final ArrayList<Heart> hearts;
    private final String levelPath;
    private final LevelLoader Level;

    private final ArrayList<String> levels = new ArrayList<String>();

   // private final Coin coin;
    private int counter = 0;

    /*! \fn public PlayState(RefLinks refLink)
        \brief Constructorul de initializare al clasei

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */

    public PlayState(RefLinks refLink, String levelName){
            ///Apel al constructorului clasei de baza
        super(refLink);

        this.levelPath = getClass().getResource("/levels/" + levelName).getPath();
        //System.out.println(levelPath);

        map = new Map(refLink, levelPath);
            ///Referinta catre harta construita este setata si in obiectul shortcut pentru a fi accesibila si in alte clase ale programului.
        refLink.SetMap(map);

            ///Construieste eroul

        //hero = new Hero(refLink,77, 636);
        this.UI = new UserInterface(refLink);
        refLink.setUI(UI);

        // referinta catre erou.
        //refLink.SetHero(hero);

        //
        this.Level = new LevelLoader(refLink, levelName);

        hero = new Hero(refLink, Level.HeroX, Level.HeroY);
        refLink.SetHero(hero);
        //intierea listelor
        bullets = new ArrayList<>();
        //enemies = new ArrayList<>();
        enemies = Level.LoadEnemy();
        coins = Level.LoadCoins();
        crates = Level.LoadCrates();
        hearts = new ArrayList<>();

        //coin = (Coin) ItemFactory.getCoin(refLink, "Coin");

        // referinta catre lista de inamici
        refLink.setEnemy(enemies);
        // referinta catre lista de gloante
        refLink.setBullets(bullets);
        refLink.setHearts(hearts);


        levels.add("nivel1.txt");
        levels.add("nivel2.txt");
        levels.add("nivel3.txt");

    }

    public void saveLevelData(){
        refLink.levelTime.add(refLink.GetHero().minutes);
        refLink.levelTime.add(refLink.GetHero().seconds);
        refLink.score += refLink.GetHero().SCORE;
    }

    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a jocului.

        Update pe camera + harta + erou + enemy + gloante + coins
     */
    @Override
    public void Update()
    {
        map.Update();
        hero.Update();

        if(enemies.size() == 0) {
            refLink.currentLevel++;
            if(refLink.currentLevel < 3) {

                //loading next level
                refLink.GetHero().setLife(100);
                refLink.GetHero().setBullets(30);

                //save
                saveLevelData();

                //debug
                //System.out.println(refLink.GetHero().minutes);
                //System.out.println(refLink.GetHero().seconds);
                System.out.println(refLink.score);

                State.SetState(new PlayState(refLink, levels.get(refLink.currentLevel)));

                refLink.GetCamera().setMapHeight(refLink.GetMap().getHeight() * Tile.TILE_HEIGHT);
                refLink.GetCamera().setMapWidth(refLink.GetMap().getWidth() * Tile.TILE_WIDTH);
            }
            if(refLink.currentLevel == 3){
                saveLevelData();

                State.SetState(new WinState(refLink));

                // ADAUGA INFORMATIILE IN BAZA DE DATE
                // USERNAME TIME SCORE

                String username = refLink.username;
                //String time = refLink.GetHero().minutes + " : " + refLink.GetHero().seconds;
                // calcul timp final

                int minute_final = 0;
                int secunde_final = 0;

                for(int i = 0; i < refLink.levelTime.size(); i += 2){
                    minute_final += refLink.levelTime.get(i);
                }
                for(int i = 1; i < refLink.levelTime.size(); i += 2){
                    secunde_final += refLink.levelTime.get(i);
                }

                //
                while(secunde_final >= 60){
                    secunde_final = secunde_final % 60;
                    minute_final++;
                }

                String time = minute_final + " : " + secunde_final;
                System.out.println(time);

                int score = refLink.score;

                Database.InsertData(username, time, score);

            }
        }

        for(Enemy enemy: enemies){
            enemy.Update();
        }

        for(Crate crate: crates){
            crate.Update();
        }

        for (Bullet bullet : bullets) {
            bullet.Update();
        }

        for(Coin coin: coins){
            coin.Update();
        }

        for(Heart heart: hearts){

            heart.Update();
        }
        float playerX = hero.GetX();
        float playerY = hero.GetY();

        refLink.GetCamera().move(playerX, playerY);


        if(enemies.isEmpty()){
            System.out.println("YOU WON!!!");
        }
    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a jocului.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Draw(Graphics g)
    {
        map.Draw(g);

        for(int i = 0; i < coins.size(); i++){
            Coin coin = coins.get(i);
            if(coin.hasCollided() == true){
                coins.remove(coin);
            } else {
                coin.Draw(g);
            }
        }

        for(int i = 0 ; i < crates.size(); i++){
            Crate crate = crates.get(i);
            if(crate.isCollected){
                crates.remove(crate);
            } else {
                crate.Draw(g);
            }
        }

        for(int i = 0; i < hearts.size(); i++){
            Heart heart = hearts.get(i);
            if(heart.isCollected){
                hearts.remove(heart);
            } else {
                heart.Draw(g);
            }
        }

        hero.Draw(g);

        //test.Draw(g);

        map.DrawOverlay(g);

        for(int i = 0 ; i < enemies.size(); i++){
             Enemy enemy = enemies.get(i);
             if(enemy.isDead() == true){
                 counter++;
                 enemy.Draw(g);
                 //enemies.remove(enemies.get(i));
                 if(counter >= 58){
                     enemies.remove(enemies.get(i));
                     counter = 0;
                 }
             } else{
                 enemy.Draw(g);
             }
        }
        for(int i = 0; i < bullets.size(); i++){
            Bullet bullet = bullets.get(i);
            if(bullet.timer > 0 && bullet.isActive) {
                bullet.Draw(g);
            } else {
                bullets.remove(i);
            }
        }
        UI.Draw(g);
    }

    @Override
    public String toString() {
        return "PlayState";
    }
}
