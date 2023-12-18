package PaooGame.Items;

import PaooGame.RefLinks;

public class EnemyFactory {

    public static Enemy createEnemy(RefLinks refLink, float x, float y, int tier) {
        switch(tier){
            case 0:
                return new EnemyTier0(refLink, x, y);
            case 1:
                return new EnemyTier1(refLink, x, y);
            case 2:
                EnemyTier2 tempEnemy = new EnemyTier2(refLink, x, y);
                tempEnemy.setLife(200);
                return tempEnemy;
            case 3:
                return new EnemyTier3(refLink, x, y);
            default:
                return null;
        }
    }
}
