package PaooGame.States;

import PaooGame.Items.UserInterface;
import PaooGame.RefLinks;

import java.awt.*;

public class HallOfFame extends State{
    private UserInterface UI;
    public HallOfFame(RefLinks refLink) {
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
        return "HallOfFame";
    }
}
