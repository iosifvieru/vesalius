package PaooGame.Items.CharacterStateDesign;

import PaooGame.Graphics.Assets;
import PaooGame.Items.Enemy;
import PaooGame.Items.Hero;
import PaooGame.RefLinks;

public class Idle extends EntityState {
    private RefLinks refLink;

    public Idle(Enemy enemy) {
        super(enemy);
    }

    public Idle(Hero hero) {
        super(hero);
        this.refLink = hero.getRefLink();
    }

    @Override
    public void AttackState() {

    }

    @Override
    public void IdleState() {
        if(this.enemy != null) {
            double size = Math.max(enemy.bounds.width, enemy.bounds.height);
         /*
             urmatoarele if-uri stabilesc patternul de miscare pe harta al inamicului.
             si incarca animatia corecta in functie de directia la care merge.
        */
            if (enemy.moving > 0) {
                enemy.moving++;
                enemy.SetX(enemy.GetX() + enemy.getSpeed());
                enemy.setCurrentAnimation(enemy.enemyisRunningToRight);
                //enemy.LoadTexture(Assets.enemyIsRunningToRight, 5);
            }
            if (enemy.moving >= enemy.MOVING_RANGE) {
                enemy.moving = -enemy.MOVING_RANGE;
                //enemy.image = Assets.enemyLeft;
                enemy.setImage(enemy.getImage());
                //enemy.LoadTexture(Assets.enemyLeft, 1);
            }
            if (enemy.moving < 0) {
                enemy.moving++;
                enemy.SetX(enemy.GetX() - enemy.getSpeed());
                enemy.setCurrentAnimation(enemy.enemyisRunningToLeft);
                //enemy.LoadTexture(Assets.enemyIsRunningToLeft, 5);
            }
            if (enemy.moving == 0) {
                enemy.setCurrentAnimation(enemy.enemyisRunningToRight);
                //enemy.LoadTexture(Assets.enemyIsRunningToRight, 0);
                //image = Assets.enemyIsRunningToRight[0];
                enemy.moving++;
            }


            // detecteaza daca jucatorul se afla in aria inamicului.
            // daca da-> inamicul trece in starea de attack.
            if (enemy.getDistance() <= size) {
                System.out.println("Enemy state: Attack;");
                enemy.setState(new Attack(enemy));
            }
        }

        if(this.hero != null){

            hero.LoadTexture(Assets.heroIdle, 4);

            if(hero.GetLife() <= 0 ){
                hero.setState(new Death(hero));
            }

        }
    }

    @Override
    public void DeathState() {

    }

    @Override
    public String toString() {
        return "IdleState";
    }
}
