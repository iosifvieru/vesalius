package PaooGame.Items.CharacterStateDesign;

import PaooGame.Items.Enemy;
import PaooGame.Items.Hero;

public abstract class EntityState {
    public Enemy enemy;
    public Hero hero;
    public EntityState(Enemy enemy){
        this.enemy = enemy;
        this.hero = null;
        //this.hero = null;
    }

    public EntityState(Hero hero){
        this.enemy = null;
        this.hero = hero;
    }

    public abstract void AttackState();
    public abstract void IdleState();
    public abstract void DeathState();
}
