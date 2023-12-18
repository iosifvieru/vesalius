package PaooGame.Items;

import PaooGame.Graphics.Assets;
import PaooGame.Items.CharacterStateDesign.EntityState;
import PaooGame.RefLinks;

public class EnemyTier3 extends Enemy {
    public EnemyTier3(RefLinks refLink, float x, float y) {
        super(refLink, x, y);

        this.tier = 3;
        image = Assets.enemyTier1Right[0];

        this.enemyisRunningToLeft = Assets.enemyTier3Left;
        this.enemyisRunningToRight = Assets.enemyTier3Right;
        this.enemyDeath = Assets.enemyTier3Death;
        this.life = 800;

        this.width *= 2;
        this.height *= 2;
        this.displayHearts = false;
    }

    @Override
    public void setState(EntityState state) {
        this.entityState = state;
    }

}
