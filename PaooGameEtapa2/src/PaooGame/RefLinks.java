package PaooGame;

import PaooGame.Items.*;
import PaooGame.Maps.Map;

import PaooGame.Input.KeyManager;

import java.util.ArrayList;

/*! \class public class RefLinks
    \brief Clasa ce retine o serie de referinte ale unor elemente pentru a fi usor accesibile.

    Altfel ar trebui ca functiile respective sa aiba o serie intreaga de parametri si ar ingreuna programarea.
 */
public class RefLinks {
    private Game game;          /*!< Referinta catre obiectul Game.*/
    private Map map;            /*!< Referinta catre harta curenta.*/

    private Hero hero;
    private ArrayList<Enemy> enemy;
    private ArrayList<Bullet> bullets;
    private ArrayList<Heart> hearts;
    private Camera camera;
    private UserInterface UI;
    public double Angle;
    public String username = null;
    public int currentLevel = 0;
    public ArrayList<Integer> levelTime = new ArrayList<>();
    public int score = 0;

    public boolean selectfromDB = false;

    /*! \fn public RefLinks(Game game)
        \brief Constructorul de initializare al clasei.

        \param game Referinta catre obiectul game.
     */
    public RefLinks(Game game) {
        this.game = game;
    }

    /*! \fn public KeyManager GetKeyManager()
        \brief Returneaza referinta catre managerul evenimentelor de tastatura.
     */
    public KeyManager GetKeyManager() {
        return game.GetKeyManager();
    }

    /*! \fn public int GetWidth()
        \brief Returneaza latimea ferestrei jocului.
     */
    public int GetWidth() {
        return game.GetWidth();
    }

    /*! \fn public int GetHeight()
        \brief Returneaza inaltimea ferestrei jocului.
     */
    public int GetHeight() {
        return game.GetHeight();
    }

    /*! \fn public Game GetGame()
        \brief Intoarce referinta catre obiectul Game.
     */
    public Game GetGame() {
        return game;
    }

    /*! \fn public void SetGame(Game game)
        \brief Seteaza referinta catre un obiect Game.

        \param game Referinta obiectului Game.
     */
    public void SetGame(Game game) {
        this.game = game;
    }

    /*! \fn public Map GetMap()
        \brief Intoarce referinta catre harta curenta.
     */
    public Map GetMap() {
        return map;
    }

    /*! \fn public void SetMap(Map map)
        \brief Seteaza referinta catre harta curenta.

        \param map Referinta catre harta curenta.
     */
    public void SetMap(Map map) {
        this.map = map;
    }

    public void SetHero(Hero hero) {
        this.hero = hero;
    }

    public Hero GetHero() {
        return this.hero;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public Camera GetCamera() {
        return this.camera;
    }

    public double CalculateAngle(float playerX, float playerY, float mouseX, float mouseY) {
        //double angle = Math.atan2(mouseY - playerY, mouseX - playerX);dddd
        //Point loc = game.getWindow().GetWndFrame().getMousePosition();
        //System.out.println("X: " + mouseX + "Y: " + mouseY);
        float offsetX = GetWidth() / 2 - playerX;
        float offsetY = GetHeight() / 2 - playerY;
        mouseX = mouseX - offsetX;
        mouseY = mouseY - offsetY;
        //double angle = Math.atan((mouseY - playerY) / (mouseX - playerX));
        double angle = Math.atan2(mouseY - playerY, mouseX - playerX);
        Angle = angle;
        return angle;
    }

    public ArrayList<Enemy> getEnemy() {
        return enemy;
    }

    public void setEnemy(ArrayList<Enemy> enemy) {
        this.enemy = enemy;
    }

    public void setBullets(ArrayList<Bullet> bullets) {
        this.bullets = bullets;
    }

    public ArrayList<Bullet> getBullets() {
        return this.bullets;
    }

    public void setUI(UserInterface ui) {
        this.UI = ui;
    }

    public UserInterface getUI() {
        return UI;
    }

    public ArrayList<Heart> getHearts() {
        return this.hearts;
    }

    public void setHearts(ArrayList<Heart> hearts) {
        this.hearts = hearts;
    }

}
