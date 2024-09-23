package Entities;

import Game.EntityApi;
import common.Consts;
import common.Mortal;

public abstract class Hero extends Entity implements Mortal {
    public String name;
    protected int hp;
    protected final int maxHp;

    public Hero(EntityApi api, String name) {
        super(api);
        this.name = name;
        this.maxHp = Consts.DEFAULT_HP;
        this.hp = maxHp;
    }

    public Hero(EntityApi api, String name, int maxHp) {
        super(api);
        this.name = name;
        this.maxHp = maxHp;
        this.hp = maxHp;
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

    public abstract String listOptions();
    /**
     * Returns common.Action based on string input
     * @param  inputString string that player enters
     * @return common.Action that player takes
     */
    public abstract void processInput(String inputString);
}
