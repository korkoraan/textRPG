package Entities;

import Game.EntityApi;
import common.C;
import common.Consts;

import java.util.Random;

public class Troll extends Enemy{
    public Troll(EntityApi entityApi) {
        super(entityApi, Consts.DEFAULT_HP * 3);
    }

    @Override
    public void update() {
        var hero = api.getNearbyEntities(this).stream().filter(e -> e instanceof Hero).findFirst();
        if(hero.isPresent())
            hitWithMace(hero.get());
        C.log("Troll's wounds heal by themselves");
        hp = maxHp;
    }

    public void hitWithMace(Entity target) {
        var dmgPts = new Random().nextInt(1, 30);
        api.attack(this, target, dmgPts);
        C.log("Troll swings his mace");
    }
}
