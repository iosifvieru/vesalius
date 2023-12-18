package PaooGame.Items;

import PaooGame.RefLinks;

/*

    Aceasta clasa creaza obiectele necesare programului.
 */


public class ItemFactory {
    public static Bullet createBullet(RefLinks refLink, float x, float y, float destX, float destY, int width, int height){
        return new Bullet(refLink, x, y, destX, destY, width, height);
    }

    public static Bullet createBullet(RefLinks refLink, float x, float y, int width, int height, String direction){
        return new Bullet(refLink, x, y, width, height, direction);
    }

    public static Coin createCoin(RefLinks refLink, float x, float y, int width, int height){
        return new Coin(refLink, x, y, width, height);
    }

    public static Crate createCrate(RefLinks refLink, float x, float y, int width, int height){
        return new Crate(refLink, x, y, width, height);
    }

    public static Heart createHeart(RefLinks refLinks, float x, float y, int width, int height){
        return new Heart(refLinks, x, y, width, height);
    }
}
