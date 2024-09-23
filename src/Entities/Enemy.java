package Entities;
import Game.EntityApi;
import common.Mortal;

public abstract class Enemy extends Entity implements Mortal {

    protected final int maxHp;
    protected int hp;

    public Enemy(EntityApi entityApi, int maxHp) {
        super(entityApi);
        this.maxHp = maxHp;
    }

    @Override
    public void takeDamage(int dmgPts) {
        hp -= dmgPts;
    }

    @Override
    public int getHealthPts() {
        return hp;
    }

    @Override
    public boolean isAlive() {
        return hp > 0;
    }
}
