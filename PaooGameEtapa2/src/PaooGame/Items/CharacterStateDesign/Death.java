package PaooGame.Items.CharacterStateDesign;

import PaooGame.Graphics.Assets;
import PaooGame.Items.Enemy;
import PaooGame.Items.Heart;
import PaooGame.Items.Hero;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

public class Death extends EntityState {

    //public boolean heartCreated = false;
    public Death(Enemy enemy){

        super(enemy);
      //  heartCreated = false;
    }
    public Death(Hero hero){
        super(hero);

    }


    @Override
    public void AttackState() {
        System.out.println("attack din death");
    }

    @Override
    public void IdleState() {
        System.out.println("idle din death");
    }

    @Override
    public void DeathState(){
        if(enemy!= null) {
            /*
            RefLinks refLink = enemy.getRefLink();
            if (!heartCreated){
                System.out.println("test");
                heartCreated = true;
                Heart tempHeart = new Heart(enemy.getRefLink(), enemy.GetX(), enemy.GetY(), Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
                refLink.getHearts().add(tempHeart);
            } */
            enemy.setCurrentAnimation(enemy.enemyDeath);

            //enemy.LoadTexture(Assets.enemyDeath, 7);
        }

        if(hero != null){
            if(hero.isDead == true){return;}

            hero.LoadTexture(Assets.heroIsDead, 7);
            hero.deathstate++;
            System.out.println(hero.deathstate);
            if(hero.deathstate == 35){
                hero.isDead = true;
            }
            //DeathState();
        }

    }

    @Override
    public String toString() {
        return "DeathState";
    }
}
