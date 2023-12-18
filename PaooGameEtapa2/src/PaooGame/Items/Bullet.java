package PaooGame.Items;

import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

/*
        Clasa Bullet extinde clasa Item.

        Ce aduce in plus fata de clasa de baza sunt urmatoarele:
            destX, destY -> reprezinta coordonatele destinatiei glontului.
            speed -> viteza cu care se deplaseaza.
            timer -> reprezinta durata de viata a unui obiect, cu dorinta de a-l sterge dupa un anumit timp.
            bulletDeelay d-> aceasta variabila ajuta pentru a oferi un deelay in actualizarea la coliziune.
            sentByPlayer -> face diferenta dintre gloantele trimise de de jucator si cele trimise de inamici.
 */
public class Bullet extends Item{
    private final BufferedImage image;
    public float destX, destY, directionX, directionY;
    public float speed = 8f;
    public float timer = 2 * 45;
    public long bulletDeelay = 0;
    public boolean sentByPlayer = false;
    public boolean isActive = true;
    public String bulletDirection = "right";

    /*
            Constructor ce apeleaza constructorul din super.
     */

    protected Bullet(RefLinks refLink, float x, float y, float destX, float destY, int width, int height)
    {
        super(refLink, x, y, width, height);
        this.x = x;
        this.y = y;

        this.width = width;
        this.height = height;
        image = Assets.bullet;

        // offsetul este folosit pentru a corecta directia.
        float offsetX = (float) refLink.GetWidth() / 2 - x;
        float offsetY = (float) refLink.GetHeight() / 2 - y;

        this.destX = destX - offsetX;
        this.destY = destY - offsetY;

        //refLink.GetGame().playSoundEffect(2);

    }

    protected Bullet(RefLinks refLink, float x, float y, int width, int height, String direction){
        super(refLink, x, y, width, height);
        this.x = x;
        this.y = y;

        this.width = width;
        this.height = height;
        image = Assets.bullet;

        this.bulletDirection = direction;

        this.destX = 0;
        this.destY = 0;

        //System.out.println("CONSTRUCTOR");
    }
    @Override
    public void Update() {

        goToDestination();
        dealDamage();
        // scade lifespan-ul obiectului.
        timer--;
    }

    @Override
    public void Draw(Graphics g) {
        g.drawImage(image, (int)x, (int)y, width, height, null);

        //g.setColor(Color.RED);
        //g.fillRect((int)x, (int)y, 10, 10);
    }

    @Override
    public void LoadTexture(BufferedImage[] texture, int dimension) {}

     /*
                    Aceasta functie calculeaza directia in care se trebuie sa se indrepte glontul prin formula:

                    ___________________________________
                   /               2                 2
                  /    ( x2 - x1 )   +  ( y2 - y1 )
                \/

            Unde (x2,y2) reprezinta coordonatele init.
                (x1, y1) coord. mouse.
     */

    public void goToDestination(){

        if(this.sentByPlayer == true) {
            switch (bulletDirection) {
                case "Up":
                    if(!refLink.GetMap().GetTile((int) (this.x/ Tile.TILE_HEIGHT), (int) this.y / Tile.TILE_WIDTH).IsSolid()) {
                        this.y -= speed;
                    }
                    //System.out.println("TEAFASDAW");
                    break;
                case "Down":
                    if(!refLink.GetMap().GetTile((int) (this.x/ Tile.TILE_HEIGHT), (int) this.y / Tile.TILE_WIDTH).IsSolid()) {
                        this.y += speed;
                    }
                    break;
                case "Left":
                    if(!refLink.GetMap().GetTile((int) (this.x/ Tile.TILE_HEIGHT), (int) this.y / Tile.TILE_WIDTH).IsSolid()) {
                        this.x -= speed;
                    }
                    break;
                case "Right":
                    if(!refLink.GetMap().GetTile((int) (this.x/ Tile.TILE_HEIGHT), (int) this.y / Tile.TILE_WIDTH).IsSolid()) {
                        this.x += speed;
                    }
                    break;
            }
        } else {

            float deltaX = x - destX;
            float deltaY = y - destY;

            double dist = Math.sqrt(Math.pow((x - destX), 2) + Math.pow((y - destY), 2));

            directionX = deltaX / (float) dist;
            directionY = deltaY / (float) dist;

            if(!refLink.GetMap().GetTile((int) this.x / Tile.TILE_WIDTH, (int) this.y / Tile.TILE_HEIGHT).IsSolid()) {
                x -= speed * directionX;
                y -= speed * directionY;
            }
        }

        //double angle = Math.toDegrees(Math.atan2(destY - y, destX - x));

        //x = (float) (x + speed * Math.cos(angle));
        //y = (float) (y + speed * Math.sin(angle));


    }

    public void dealDamage() {
        if(!isActive){ return; }
        if (!this.sentByPlayer) { // trimis de inamic
            float playerX = refLink.GetHero().GetX();
            float playerY = refLink.GetHero().GetY();
            // verifica daca glontul se afla in patratul de coliziune al eroului.

            if (this.x >= playerX && this.x <= playerX + refLink.GetHero().width &&
                    this.y >= playerY && this.y <= playerY + refLink.GetHero().height) {
                long currentTime = System.currentTimeMillis();
                if (currentTime - bulletDeelay > 800) {
                    bulletDeelay = currentTime;
                    int life = refLink.GetHero().life;
                    refLink.GetHero().SetLife(life - 20);
                    System.out.println("Hero life: " + refLink.GetHero().GetLife());
                    isActive = false;
                }
            }
        } else {
            for (Enemy enemy : refLink.getEnemy()) {
                float enemyX = enemy.GetX();
                float enemyY = enemy.GetY();

                if(enemy.isDead()) {
                    return;
                }
                // verifica daca glontul se afla in patratul de coliziune al inamicului.

                if (this.x >= enemyX && this.x <= enemyX + enemy.width &&
                        this.y >= enemyY && this.y <= enemyY + enemy.height) {
                    long currentTime = System.currentTimeMillis();
                    if (currentTime - bulletDeelay > 800) {
                        bulletDeelay = currentTime;
                        //debug
                        //System.out.println("COLLISION DETECTED");

                        int life = enemy.GetLife();
                        enemy.SetLife(life - 20);
                        isActive = false;

                        //debug
                        System.out.println("enemy life: " + enemy.GetLife());
                    }
                }
            }
        }
    }
}
