package PaooGame.Items.CharacterStateDesign;

import PaooGame.Items.Bullet;
import PaooGame.Items.Enemy;
import PaooGame.Items.Hero;
import PaooGame.Items.ItemFactory;
import PaooGame.RefLinks;

public class Attack extends EntityState {

    public RefLinks refLink;
    public long lastClickTime = 0;
    Bullet bullet = null;
    public Attack(Enemy enemy) {

        super(enemy);

    }

    public Attack(Hero hero) {
        super(hero);
        refLink = hero.getRefLink();
    }

    @Override
    public void AttackState() {
        //refLink.GetGame().playSoundEffect(2);

        if(this.enemy != null){
            double size = Math.max(enemy.normalBounds.width, enemy.normalBounds.height);
            enemy.followPlayer();
            enemy.shootAtHero();

            // daca jucatorul nu mai este in raza de actiune, inamicul trece in starea de IDLE
            // respectiv in pozitia initiala.
            if(enemy.getDistance() > size){
                System.out.println("Enemy state: Idle;");
                //enemy.getToInitialPosition();
                IdleState();
            }
        }

        if(this.hero != null){
            float posX = hero.GetX() + (hero.GetWidth() / 2);
            float posY = hero.GetY() + (hero.GetHeight() / 2);
            if(refLink.GetKeyManager().shootRight){
                bullet = ItemFactory.createBullet(refLink, posX, posY, 10, 10, "Right");
            }

            if(refLink.GetKeyManager().shootLeft){
                bullet = ItemFactory.createBullet(refLink, posX, posY, 10, 10, "Left");
            }

            if(refLink.GetKeyManager().shootUp){
                bullet = ItemFactory.createBullet(refLink, posX, posY, 10, 10, "Up");
            }
            if(refLink.GetKeyManager().shootDown){
                bullet = ItemFactory.createBullet(refLink, posX, posY, 10, 10, "Down");
            }
            if(bullet == null){return;}
            bullet.sentByPlayer = true;

            hero.addBullets(-1);
            //System.out.println(hero.getBulletNumber());

            refLink.getBullets().add(bullet);
            refLink.GetGame().playSoundEffect(2);

            if(hero.GetLife() <= 0){
                hero.setState(new Death(hero));
            }
            IdleState();
        }
    }

    @Override
    public void IdleState() {
        if(enemy != null){
            enemy.setState(new Idle(enemy));
        }

        if(hero != null){
            //System.out.println("IDLE DIN ATTACK");
            hero.setState(new Idle(hero));
        }
        //enemy.setState(new IdleState(enemy));
        //AttackState();
    }

    @Override
    public void DeathState() {

    }

    @Override
    public String toString() {
        return "AttackState";
    }
}
