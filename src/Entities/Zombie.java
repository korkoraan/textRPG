package Entities;
import Game.EntityApi;
import common.C;
import common.Consts;
import common.Mortal;

import java.util.Random;

public class Zombie extends Enemy implements Mortal {
    public Zombie(EntityApi entityApi) {
        super(entityApi, Consts.DEFAULT_HP);
    }

    @Override
    public void update() {
        var hero = api.getNearbyEntities(this).stream().filter(e -> e instanceof Hero).findFirst();
        if(hero.isPresent())
            bite(hero.get());
        else
            C.log("Zombie is mindlessly walking");
    }

    @Override
    public boolean isAlive() {
        return false;
    }

    @Override
    public void takeDamage(int dmgPts) {
        hp -= dmgPts;
        if(hp < 0 && new Random().nextBoolean()) {
            C.log("Zombie is brought back by unholy powers!!!");
            hp = 1;
        }
    }

    @Override
    public int getHealthPts() {
        return 0;
    }

    public void bite(Entity target) {
        var dmgPts = new Random().nextInt(0, 10);
        api.attack(this, target, dmgPts);
        C.log("Zombie bites nasty");
    }
}
