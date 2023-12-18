package PaooGame.Items;

import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Heart extends Item {

    private BufferedImage image;
    public boolean isCollected;
    public float timer = 0;
    public Heart(RefLinks refLink, float x, float y, int width, int height) {
        super(refLink, x, y, width, height);
        this.image = Assets.heart[0];
        isCollected = false;
    }

    @Override
    public void Update() {
        if(hasCollided() && !isCollected){
            long currentTime = System.currentTimeMillis();
            if (currentTime - timer > 200) {
                int currentLife = refLink.GetHero().life;
                refLink.GetHero().setLife(currentLife + 20);
                timer = currentTime;
            }
            isCollected = true;
        }
    }

    @Override
    public void Draw(Graphics g) {
        g.drawImage(image, (int) x, (int) y, width, height, null);
    }

    @Override
    public void LoadTexture(BufferedImage[] texture, int dimension) {
        //NULL
    }

    public boolean hasCollided(){
        float playerX = refLink.GetHero().GetX();
        float playerY = refLink.GetHero().GetY();
        float playerWidth = refLink.GetHero().width + 15;
        float playerHeight = refLink.GetHero().height + 15;

        if(this.x >= playerX && this.x <= playerX + playerWidth &&
                this.y >= playerY && this.y <= playerY + playerHeight){
            return true;
        }

        return false;
    }
}
