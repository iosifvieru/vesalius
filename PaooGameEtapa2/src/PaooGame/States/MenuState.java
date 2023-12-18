package PaooGame.States;

import PaooGame.Items.UserInterface;
import PaooGame.RefLinks;

import java.awt.*;
import java.util.ArrayList;

/*! \class public class MenuState extends State
    \brief Implementeaza notiunea de menu pentru joc.
 */
public class MenuState extends State
{
    /*! \fn public MenuState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */

    public UserInterface UI;
    public MenuState(RefLinks refLink)
    {
            ///Apel al constructorului clasei de baza.
        super(refLink);

        this.UI = new UserInterface(refLink);
        refLink.setUI(UI);
        refLink.currentLevel = 0;

        //reset data
        refLink.score = 0;
        refLink.levelTime = new ArrayList<>();
        refLink.username = null;

    }
    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a meniului.
     */
    @Override
    public void Update()
    {
        UI.Update();
    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a meniului.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Draw(Graphics g)
    {
        UI.Draw(g);
    }

    @Override
    public String toString() {
        return "MenuState";
    }
}
