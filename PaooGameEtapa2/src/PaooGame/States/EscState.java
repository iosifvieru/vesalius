package PaooGame.States;

import PaooGame.Items.UserInterface;
import PaooGame.RefLinks;

import java.awt.*;


//TBD
public class EscState extends State{
    private UserInterface UI;
    public EscState(RefLinks refLink) {
        super(refLink);

        this.UI = refLink.getUI();
    }

    @Override
    public void Update() {
        UI.Update();
    }

    @Override
    public void Draw(Graphics g) {
        UI.Draw(g);
    }

    @Override
    public String toString() {
        return "Esc";
    }
}
