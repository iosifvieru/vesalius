package PaooGame.Items;

import PaooGame.Graphics.Assets;
import PaooGame.Items.CharacterStateDesign.Death;
import PaooGame.Items.CharacterStateDesign.EntityState;
import PaooGame.Items.CharacterStateDesign.Idle;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Enemy extends Character{
    //private
    protected BufferedImage image;

    public BufferedImage[] enemyisRunningToLeft;
    public BufferedImage[] enemyisRunningToRight;
    public BufferedImage[] enemyDeath;


    protected BufferedImage[] currentAnimation;
    //
    EntityState entityState;
    private long textureTimer = 0;
    private final float InitialX;
    private final float InitialY;
    private boolean isInInitialPos = true;
    private long shootDeelay = 0;

    //public
    public int tier;
    public int moving = 1;
    public final int MOVING_RANGE = 150;
    public long timerr = 0;
    public boolean displayHearts = true;

    public Enemy(RefLinks refLink, float x, float y) {
        super(refLink, x, y, Character.DEFAULT_CREATURE_WIDTH, Character.DEFAULT_CREATURE_HEIGHT);
        this.tier = 0;
        this.image = Assets.enemyLeft;


        //animatii
        enemyisRunningToLeft = Assets.enemyIsRunningToLeft;
        enemyisRunningToRight = Assets.enemyIsRunningToRight;
        enemyDeath = Assets.enemyDeath;
        currentAnimation = Assets.enemyIsRunningToLeft;

        entityState = new Idle(this);
        InitialX = this.x;
        InitialY = this.y;

        this.speed = tier + 1;

        width = width*2;
        height = height*2;

        life = 100;

        normalBounds.x = 32;
        normalBounds.y = 32;
        normalBounds.width = 128 * 2;
        normalBounds.height = 128 * 2;

    }

    @Override
    public void Update() {
        LoadTexture(currentAnimation, currentAnimation.length-1);
        //LoadTexture(currentAnimation, currentAnimation.length-1);
        switch(entityState.toString()){
            case "AttackState":
                entityState.AttackState();
                break;
            case "IdleState":
                entityState.IdleState();
                if(!isInInitialPos){
                    getToInitialPosition();
                }
                break;
            case "DeathState":
                entityState.DeathState();
                break;
        }
    }

    @Override
    public void Draw(Graphics g) {
        g.drawImage(image, (int) x, (int) y, width, height, null);

        if (displayHearts){
            refLink.getUI().drawHearts(g, (int) GetX() + 7, (int) GetY() - Tile.TILE_HEIGHT / 2, 15, 15, life / 20);
        }
        //g.setColor(Color.WHITE);
        //g.drawRect((int)(x - bounds.x/2), (int)(y - bounds.y/2), bounds.width, bounds.height);

    }

    public void LoadTexture(BufferedImage[] texture, int dimension){
        long currentTime = System.currentTimeMillis();
        if(currentTime - textureTimer > 200) {
            textureCounter++;
            image = texture[textureCounter];
            textureTimer = currentTime;
            if(textureCounter >= dimension){
                textureCounter = 0;
            }
        }
    }
    public double getDistance(){
        float playerX = refLink.GetHero().GetX();
        float playerY = refLink.GetHero().GetY();
        return Math.sqrt(Math.pow(playerX - x, 2) + Math.pow(playerY - y, 2));
    }

    /*
        Aceasta functie urmareste jucatorul pe harta.

        Compara pozitia inamicului cu cea a jucatorul si creste sau scade ( in functie de nevoi ) coordonatele acestuia.

     */

    public boolean hasCollided(){
        int tempX = (int) ((this.x + bounds.x) / Tile.TILE_WIDTH);
        int tempY = (int) ((this.y + bounds.y) / Tile.TILE_HEIGHT);

        if(refLink.GetMap().GetTile(tempX, tempY).IsSolid()){
            return true;
        }
        return false;
    }

    public void followPlayer(){
        float playerX = refLink.GetHero().GetX();
        float playerY = refLink.GetHero().GetY();
        isInInitialPos = false;

        if(this.x <= playerX){
            if(!hasCollided()) {
                x = x + speed;
            }
            currentAnimation = this.enemyisRunningToRight;
            //LoadTexture(Assets.enemyIsRunningToRight, 5);
        }
        if(this.y <= playerY){
            if(!hasCollided()) {
                y = y + speed;
            }
        }
        if(this.y >= playerY){
            if(!hasCollided()) {
                y = y - speed;
            }
        }
        if(this.x >= playerX){
            if(!hasCollided()) {
                x = x - speed;
            }
            currentAnimation = this.enemyisRunningToLeft;
            //LoadTexture(Assets.enemyIsRunningToLeft, 5);
        }
    }


    /*
        Aceasta functie seteaza coordonatele inamicului cu coordonatele initiale.
     */
    public void getToInitialPosition(){
        if(isInInitialPos) { return; }
        if(this.x == InitialX && this.y == InitialY){
            isInInitialPos = true;
            moving = 1;
        }

        //System.out.println("DEBUG: isInInitialPos: " + isInInitialPos + " InitialX: " + InitialX + " InitialY: " + InitialY + " CurrentX: " + this.x + " CurrentY: " + this.y);

        if(this.x < InitialX){
            //x = x + speed;
            x++;
            //LoadTexture(Assets.enemyIsRunningToRight, 5);
        }
        if(this.y < InitialY){
            y++;
            //y = y + speed;
        }
        if(this.y > InitialY){
            y--;
            //y = y - speed;
        }
        if(this.x > InitialX){
            x--;
            //x = x - speed;
            //LoadTexture(Assets.enemyIsRunningToLeft, 5);
        }
    }


    // verifica daca inamicul este mort.
    public boolean isDead(){
        if(this.life > 0){
            return false;
        }

        setState(new Death(this));

        /*
            timer de o sec, dupa care creeaza obiectul HEART, il adauga in lista si modifica scorul eroului.

         */
        long currentTime = System.currentTimeMillis();
        if(currentTime - timerr > 1000) {
            timerr = currentTime;

            Heart tempHeart = new Heart(refLink, GetX(), GetY(), Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
            refLink.getHearts().add(tempHeart);

            refLink.GetHero().SCORE += 15;
        }
        return true;
    }
    /*
        Aceasta functie implementeaza notiunea de atac al inamicului.

        Verifica initial daca inamicul este mort sau nu se afla in starea de ATTACK si paraseste functia in acest caz.

        Functia genereaza un glont ce porneste din x,y al inamicului cu destinatia jucatorului.
     */
    public void shootAtHero(){
        //if((enemyState.toString().compareTo("AttackState") != 0 || isDead())){
        if(!entityState.toString().equals("AttackState") || isDead()){
            return;
        }

        long currentTime = System.currentTimeMillis();
        if(currentTime - shootDeelay > 1200) {
            float offsetX = (float) refLink.GetWidth() / 2 - x;
            float offsetY = (float) refLink.GetHeight() / 2 - y;
            shootDeelay = currentTime;

            Bullet bullet = ItemFactory.createBullet(refLink, x + width/2, y + height/2, refLink.GetHero().GetX() + offsetX, refLink.GetHero().GetY() + offsetY, 15, 10);
            bullet.sentByPlayer = false;
            bullet.speed = 5f;
            refLink.getBullets().add(bullet);

            refLink.GetGame().playSoundEffect(2);
            //refLink.getEnemyBullets().add(bullet);
        }
    }

    public void setImage(BufferedImage image){
        this.image = image;
    }

    public void setState(EntityState state){
        this.entityState = state;
    }

    public void setCurrentAnimation(BufferedImage[] texture) {
        this.currentAnimation = texture;
    }
    public float getSpeed(){
        return this.speed;
    }

    public BufferedImage getImage(){
        return image;
    }
    public void setLife(int life){
        this.life = life;
    }
}
