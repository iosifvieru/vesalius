package PaooGame.Items;

import PaooGame.Items.CharacterStateDesign.EntityState;
import PaooGame.RefLinks;

public class EnemyTier2 extends Enemy{

    public EnemyTier2(RefLinks refLink, float x, float y) {
        super(refLink, x, y);

        this.life = 200;
    }

    @Override
    public void setState(EntityState state) {
        this.entityState = state;
    }
}
