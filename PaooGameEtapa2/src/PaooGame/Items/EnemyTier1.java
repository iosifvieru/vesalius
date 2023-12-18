package PaooGame.Items;

import PaooGame.Graphics.Assets;
import PaooGame.Items.CharacterStateDesign.EntityState;
import PaooGame.RefLinks;

public class EnemyTier1 extends Enemy {


    public EnemyTier1(RefLinks refLink, float x, float y) {
        super(refLink, x, y);

        this.tier = 1;
        image = Assets.enemyTier1Right[0];

        this.enemyisRunningToLeft = Assets.enemyTier1Left;
        this.enemyisRunningToRight = Assets.enemyTier1Right;
        this.enemyDeath = Assets.enemyTier1Death;
        this.life = 200;
    }

    @Override
    public void setState(EntityState state) {
        this.entityState = state;
    }

}
