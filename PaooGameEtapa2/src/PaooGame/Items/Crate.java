package PaooGame.Items;

import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;
/*
        Crate extinde clasa Item.

            Ce aduce in plus fata de clasa Item sunt urmatoarele:

            -image -> textura obiectului.
            -isCollected -> reprezinta starea crate-ului, daca este colectat sau nu de catre jucator.
 */
public class Crate extends Item {
    public BufferedImage image; // textura crate-ului.
    public boolean isCollected = false;
    public Crate(RefLinks refLinks, float x, float y, int width, int height){
        super(refLinks, x, y, width, height);

        //imaginea initiala.
        image = Assets.crate;
    }

    //in momentul in care obiectul este colecata de jucator
    //acesta primeste 30 de gloante si se actualizeaza starea crate-ului.
    @Override
    public void Update() {
        //if(hasCollided() && refLink.GetKeyManager().eKey){
        if(hasCollided()){

            refLink.GetHero().addBullets(30);
            refLink.GetHero().SCORE += 5;

            refLink.GetGame().playSoundEffect(3);
            isCollected = true;
        }
    }

    // deseneaza obiectul pe harta.
    @Override
    public void Draw(Graphics g) {

        g.drawImage(image, (int)x, (int)y, width, height, null);
    }

    @Override
    public void LoadTexture(BufferedImage[] texture, int dimension) {
        // null
    }

    // Aceasta functie returneaza daca jucatorul se afla in zona crate-ului.
    public boolean hasCollided(){
        float playerX = refLink.GetHero().GetX();
        float playerY = refLink.GetHero().GetY();
        float playerWidth = refLink.GetHero().width + 10;
        float playerHeight = refLink.GetHero().height + 10;

        if(this.x >= playerX && this.x <= playerX + playerWidth &&
                this.y >= playerY && this.y <= playerY + playerHeight){
            return true;
        }

        return false;
    }

}
