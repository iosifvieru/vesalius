package PaooGame.Items;

import PaooGame.Graphics.Assets;
import PaooGame.Items.CharacterStateDesign.EntityState;
import PaooGame.RefLinks;

public class EnemyTier0 extends Enemy {
    public EnemyTier0(RefLinks refLink, float x, float y) {
        super(refLink, x, y);
        //this.image = Assets.enemyDeath[0];

        this.enemyisRunningToLeft = Assets.enemyIsRunningToLeft;
        this.enemyisRunningToRight = Assets.enemyIsRunningToRight;
        this.enemyDeath = Assets.enemyDeath;

        this.tier = 0;
    }

    @Override
    public void setState(EntityState state) {
        this.entityState = state;
    }
}
