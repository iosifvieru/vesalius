package PaooGame.Items;

import PaooGame.Database;
import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;
import PaooGame.States.PlayState;
import PaooGame.States.State;
import PaooGame.Tiles.Tile;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class UserInterface {
    private final RefLinks refLinks;
    private final Font UIFont = new Font("Tahoma", Font.PLAIN, 32);
    private final Color UIColor = Color.WHITE;
    public int command = 0;
    public int deelay = 0;
    public ArrayList<String> input = new ArrayList<>();


    public UserInterface(RefLinks refLinks) {
        this.refLinks = refLinks;

        if (refLinks.selectfromDB) {
            input = Database.selectAll("players");
            refLinks.selectfromDB = false;
        }
    }

    public void Draw(Graphics g) {
       //PLAYSTATE UI
        if(State.GetState().toString() == "PlayState") {
            //hearts
            int i = refLinks.GetHero().GetLife() / 20;
            drawHearts(g, (int) refLinks.GetCamera().getX(), (int) refLinks.GetCamera().getY(), 30, 30, i);

            //no of bullets
            int x = (int) refLinks.GetCamera().getX() + refLinks.GetWidth() - g.getFontMetrics().stringWidth(String.valueOf(refLinks.GetHero().getBulletNumber())) - 25;
            int y = (int) refLinks.GetCamera().getY() + 30;

            drawString(g, x, y, String.valueOf(refLinks.GetHero().getBulletNumber()));

            //no of coins

            x = (int) refLinks.GetCamera().getX() + refLinks.GetWidth() - g.getFontMetrics().stringWidth(String.valueOf(refLinks.GetHero().getCoins())) - 25;
            y = (int) refLinks.GetCamera().getY() + refLinks.GetHeight() - 30;

            if (refLinks.GetHero().getCoins() > 0) {
                drawString(g, x, y, String.valueOf(refLinks.GetHero().getCoins()));
            }
        }

        // TITLE SCREEN
        if(State.GetState().toString() == "MenuState"){
            //refLinks.GetGame().getWindow().GetWndFrame().setBackground(Color.BLACK);
            //refLinks.GetGame().getWindow().GetCanvas().setBackground(Color.BLUE);
            //System.out.println("AJUNG AICI");
            String text = "Vesalius";
            g.setFont(new Font("Times New Roman", Font.BOLD, 96));

            // bg color
            g.setColor(new Color(79, 69, 87));
            g.fillRect(0, 0, refLinks.GetWidth(), refLinks.GetHeight());

            //pos text titlu
            int x = getCenteredXPos(g, text);
            int y = Tile.TILE_HEIGHT*4;
            //shadow
            g.setColor(Color.black);
            g.drawString(text, x+3, y+3);
            //titlu
            g.setColor(new Color(244, 238, 224));
            g.drawString(text, x, y);

            //
            g.setFont(new Font("Times New Roman", Font.BOLD, 36));
            // NEW GAME
            text = "NEW GAME";
            x = getCenteredXPos(g, text);
            y += Tile.TILE_HEIGHT*3;
            g.drawString(text, x, y);
            if(command == 0){
                g.drawString(">", x - Tile.TILE_WIDTH, y);
            }

            // HALL OF FAME
            text = "HALL OF FAME";
            x = getCenteredXPos(g, text);
            y+= Tile.TILE_HEIGHT;
            g.drawString(text, x, y);
            if(command == 1){
                g.drawString(">", x - Tile.TILE_WIDTH, y);
            }

            // OPTIONS
            text = "OPTIONS";
            x = getCenteredXPos(g, text);
            y+= Tile.TILE_HEIGHT;
            g.drawString(text, x, y);
            if(command == 2){
                g.drawString(">", x - Tile.TILE_WIDTH, y);
            }

            // QUIT
            text = "QUIT";
            x = getCenteredXPos(g, text);
            y+= Tile.TILE_HEIGHT;
            g.drawString(text, x, y);
            if(command == 3){
                g.drawString(">", x - Tile.TILE_WIDTH, y);
            }
        }

        // HERO DIED
        if(State.GetState().toString() == "HeroIsDead"){
            String text = "YOU DIED!";
            g.setFont(new Font("Times New Roman", Font.BOLD, 96));

            g.setColor(new Color(79, 69, 87));
            g.fillRect(0, 0, refLinks.GetWidth(), refLinks.GetHeight());

            //pos text titlu
            int x = getCenteredXPos(g, text);
            int y = refLinks.GetHeight()/2;

            //shadow
            g.setColor(Color.black);
            g.drawString(text, x+3, y+3);

            //titlu
            g.setColor(new Color(244, 238, 224));
            g.drawString(text, x, y);

            if(deelay < 50 && deelay >= 0) {
                g.setFont(new Font("Times New Roman", Font.BOLD, 24));
                text = "Press ENTER to return to main menu!";
                x = getCenteredXPos(g, text);
                y = y + Tile.TILE_HEIGHT * 2;
                g.drawString(text, x, y);

            }
            if(deelay > 50) {
                deelay = -35;
            }

        }

        //WIN STATE
        if(State.GetState().toString() == "WinState"){
            String text = "YOU WON!";
            g.setFont(new Font("Times New Roman", Font.BOLD, 96));

            // bg color
            g.setColor(new Color(79, 69, 87));
            g.fillRect(0, 0, refLinks.GetWidth(), refLinks.GetHeight());

            //pos text titlu
            int x = getCenteredXPos(g, text);
            int y = refLinks.GetHeight()/2;
            //shadow
            g.setColor(Color.black);
            g.drawString(text, x+3, y+3);
            //titlu
            g.setColor(new Color(244, 238, 224));
            g.drawString(text, x, y);
            if(deelay < 50 && deelay >= 0) {
                g.setFont(new Font("Times New Roman", Font.BOLD, 24));
                text = "Press ENTER to return to main menu!";
                x = getCenteredXPos(g, text);
                y = y + Tile.TILE_HEIGHT * 2;
                g.drawString(text, x, y);

            }
            if(deelay > 50) {
                deelay = -35;
            }
        }

        // USER INPUT
        if(State.GetState().toString() == "UserInputState"){
            String userInput = JOptionPane.showInputDialog(null, "Enter your username: ");
            if(userInput != null){
                refLinks.username = userInput;
                //System.out.println(refLinks.username);
                //this.username = userInput;
                State.SetState(new PlayState(refLinks, "nivel1.txt"));
            }
        }

        // HALL OF FAME
        if(State.GetState().toString() == "HallOfFame"){
            String text = "HALL OF FAME";
            g.setFont(new Font("Times New Roman", Font.BOLD, 72));

            // bg color
            g.setColor(new Color(79, 69, 87));
            g.fillRect(0, 0, refLinks.GetWidth(), refLinks.GetHeight());

            //pos text titlu
            int x = getCenteredXPos(g, text);
            int y = Tile.TILE_HEIGHT * 2;
            //shadow
            g.setColor(Color.black);
            g.drawString(text, x+3, y+3);
            //titlu
            g.setColor(new Color(244, 238, 224));
            g.drawString(text, x, y);

            // CAP DE TABEL
            g.setFont(new Font("Times New Roman", Font.BOLD, 20));
            text = "ID \t\t USERNAME \t\t TIME \t\t SCORE";
            y += Tile.TILE_HEIGHT * 2;
            x = getCenteredXPos(g, text);
            g.drawString(text, x, y);

            // TEXT
            //y = y + Tile.TILE_HEIGHT;
            g.setFont(new Font("Times New Roman", Font.BOLD, 15));
            for(int i = 0; i < input.size(); i++) {
                text = input.get(i);
                x = getCenteredXPos(g, text);
                y = y + Tile.TILE_HEIGHT/2;
                g.drawString(text, x, y);
            }
        }
    }

    public void Update(){
        //System.out.println(username);
        deelay++;
    }

    public void drawHearts(Graphics g, int x, int y, int width, int height, int heartNumber){
        int drawX = x;
        int drawY = y;
        while(heartNumber > 0){
            g.drawImage(Assets.heart[0], drawX, drawY, width, height, null);
            drawX += width;
            heartNumber--;
            if(heartNumber % 5 == 0){
                drawX = x;
                drawY += height;
            }
        }
    }

    public void drawString(Graphics g, int x, int y, String text){
        g.setColor(UIColor);
        g.setFont(UIFont);

        g.drawString(text, x, y);
    }

    public int getCenteredXPos(Graphics g ,String text){
        int lungime = (int) g.getFontMetrics().getStringBounds(text, g).getWidth();
        int x = refLinks.GetGame().getWindow().GetWndWidth()/2 - lungime/2;
        return x;
    }
}
