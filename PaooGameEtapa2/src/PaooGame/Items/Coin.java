package PaooGame.Items;

import PaooGame.Graphics.Assets;

import PaooGame.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;

/*
    Coin extinde Item.

    Ce aduce in plus sunt urmatoarele:
        image -> reprezinta textura obiectului.
        textureCounter -> folosit in incarcarea animatiilor.
        timer -> folosit in incetirirea animatiei.
 */

public class Coin extends Item {

    public BufferedImage image;
    public int textureCounter = 0;
    public long timer = 0;
    public Coin(RefLinks refLink, float x, float y, int width, int height){
        super(refLink, x, y, width, height);

        //imaginea initiala.
        image = Assets.coin[0];

    }
    @Override
    public void Update() {
        LoadTexture(Assets.coin, 8);
        //hasCollided();
    }

    @Override
    public void Draw(Graphics g) {
        g.drawImage(image, (int)x, (int)y, width, height, null);
    }

    public void LoadTexture(BufferedImage[] texture, int dimension){
        long currentTime = System.currentTimeMillis();
        if(currentTime - timer > 150) {
            textureCounter++;
            image = texture[textureCounter];
            timer = currentTime;
        }
        if(textureCounter >= dimension){
            textureCounter = 0;
        }
    }

    // aceasta functie are rolul de a verifica daca jucatorul a interactionat cu obiectul.
    // actualizeaza numarul monedelor detinute de jucator.
    public boolean hasCollided(){
        float playerX = refLink.GetHero().GetX();
        float playerY = refLink.GetHero().GetY();
        float playerWidth = refLink.GetHero().width;
        float playerHeight = refLink.GetHero().height;

        if(this.x >= playerX && this.x <= playerX + playerWidth &&
                this.y >= playerY && this.y <= playerY + playerHeight){
            refLink.GetHero().addCoins(1);
            refLink.GetGame().playSoundEffect(3);
            // adauga scor
            refLink.GetHero().SCORE += 10;

            return true;
        }
        return false;
    }
}
