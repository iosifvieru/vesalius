package PaooGame.Input;

import PaooGame.Database;
import PaooGame.Items.UserInterface;
import PaooGame.RefLinks;
import PaooGame.States.*;
import PaooGame.Tiles.Tile;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*! \class public class KeyManager implements KeyListener
    \brief Gestioneaza intrarea (input-ul) de tastatura.

    Clasa citeste daca au fost apasata o tasta, stabiliteste ce tasta a fost actionata si seteaza corespunzator un flag.
    In program trebuie sa se tina cont de flagul aferent tastei de interes. Daca flagul respectiv este true inseamna
    ca tasta respectiva a fost apasata si false nu a fost apasata.
 */
public class KeyManager implements KeyListener {
    private RefLinks reflink;
    private final boolean[] keys; /*!< Vector de flaguri pentru toate tastele. Tastele vor fi regasite dupa cod [0 - 255]*/
    public boolean up;      /*!< Flag pentru tasta "sus" apasata.*/
    public boolean down;    /*!< Flag pentru tasta "jos" apasata.*/
    public boolean left;    /*!< Flag pentru tasta "stanga" apasata.*/
    public boolean right;   /*!< Flag pentru tasta "dreapta" apasata.*/
    public boolean eKey; // flag pentru tasta E.
    public boolean shootUp, shootDown, shootLeft, shootRight;

    /*! \fn public KeyManager()
        \brief Constructorul clasei.
     */
    public KeyManager(RefLinks refLinks)
    {
            ///Constructie vector de flaguri aferente tastelor.
        keys = new boolean[256];
        this.reflink = refLinks;
    }


    public void Update()
    {
        up    = keys[KeyEvent.VK_W];
        down  = keys[KeyEvent.VK_S];
        left  = keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_D];
        shootUp = keys[KeyEvent.VK_UP];
        shootDown = keys[KeyEvent.VK_DOWN];
        shootLeft = keys[KeyEvent.VK_LEFT];
        shootRight = keys[KeyEvent.VK_RIGHT];
        eKey = keys[KeyEvent.VK_E];

    }

    /*! \fn public void keyPressed(KeyEvent e)
        \brief Functie ce va fi apelata atunci cand un un eveniment de tasta apasata este generat.

         \param e obiectul eveniment de tastatura.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        ///se retine in vectorul de flaguri ca o tasta a fost apasata.
        keys[e.getKeyCode()] = true;

        // MENU STATE
        if (State.GetState().toString() == "MenuState"){
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                if (reflink.getUI().command < 3) {
                    reflink.getUI().command++;
                    reflink.GetGame().playSoundEffect(3);
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                if (reflink.getUI().command > 0) {
                    reflink.getUI().command--;
                    reflink.GetGame().playSoundEffect(3);
                }
            }

            if(e.getKeyCode() == KeyEvent.VK_ENTER){
                switch (reflink.getUI().command){
                    case 0:
                        State.SetState(new UserInputState(reflink));
                        //State.SetState(new PlayState(reflink, "nivel2.txt"));
                        reflink.GetGame().playSoundEffect(3);
                        break;
                    case 1:
                        //
                        // Database.selectAll("players");
                        reflink.selectfromDB = true;
                        UserInterface tempui = new UserInterface(reflink);
                        reflink.setUI(tempui);
                        State.SetState(new HallOfFame(reflink));
                        reflink.GetGame().playSoundEffect(3);
                        break;
                    case 2:
                        System.out.println("NIMIC FR");
                        reflink.GetGame().playSoundEffect(3);
                        break;
                    case 3:
                        System.out.println("QUIT");
                        reflink.GetGame().playSoundEffect(3);
                        System.exit(0);
                        break;
                }
            }
        }

        // WINSTATE
        if(State.GetState().toString() == "WinState" || State.GetState().toString() == "HeroIsDead"){
            if(e.getKeyCode() == KeyEvent.VK_ENTER){
                reflink.GetGame().playSoundEffect(3);
                State.SetState(new MenuState(reflink));

            }
        }

        // debug
        if(State.GetState().toString() == "PlayState"){
            if(e.getKeyCode() == KeyEvent.VK_SPACE){
                System.out.println(reflink.GetHero().GetX() / Tile.TILE_HEIGHT + " " + reflink.GetHero().GetY() / Tile.TILE_WIDTH);
            }
        }

        if(State.GetState().toString() == "HallOfFame" || State.GetState().toString() == "Options"){
            if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
                reflink.GetGame().playSoundEffect(3);
                State.SetState(new MenuState(reflink));
            }
        }
    }

    /*! \fn public void keyReleased(KeyEvent e)
        \brief Functie ce va fi apelata atunci cand un un eveniment de tasta eliberata este generat.

         \param e obiectul eveniment de tastatura.
     */
    @Override
    public void keyReleased(KeyEvent e)
    {
            ///se retine in vectorul de flaguri ca o tasta a fost eliberata.
        keys[e.getKeyCode()] = false;
    }

    /*! \fn public void keyTyped(KeyEvent e)
        \brief Functie ce va fi apelata atunci cand o tasta a fost apasata si eliberata.
        Momentan aceasta functie nu este utila in program.
     */
    @Override
    public void keyTyped(KeyEvent e) {}
}
