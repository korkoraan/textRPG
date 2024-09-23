package Entities;

import Game.EntityApi;
import common.C;
import common.Consts;

import java.util.Random;

public class Mage extends Hero {

    private final int maxMana = 100;
    private int mana = maxMana;
    public Mage(EntityApi api, String name) {
        super(api, name, Consts.DEFAULT_HP / 2);
    }

    @Override
    public String listOptions() {
        return "Choose action: { 1. Cast fireball }  { 2. Cast twist of fate } { 3. Smoke pipe }";
    }

    @Override
    public void processInput(String inputString) {
        var enemy = api.getNearbyEntities(this).stream().filter(e -> e instanceof Enemy).findFirst();
        if(enemy.isEmpty())
            return;
        switch (inputString) {
            case "1" -> castFireball(enemy.get());
            case "2" -> castTwistOfFate(enemy.get());
            case "3" -> smokePipe();
        }
    }

    public void castFireball(Entity enemy) {
        api.attack(this, enemy, new Random().nextInt(10, 51));
    }

    public void castTwistOfFate(Entity enemy) {
        C.log("You cast twist of fate and...");
        switch (new Random().nextInt(0, 2)) {
            case 0:
                api.attack(this, enemy, 1000);
                C.log("...it chose " + C.name(enemy));
                break;
            case 1:
                api.attack(this, this, 1000);
                C.log("...it chose " + C.name(this));
                break;
        }
    }

    public void smokePipe() {
        mana = Math.min(mana + 25, maxMana);
        C.log("Inhaling pipe restores some of your mana. You now have " + mana + " points of mana");
    }

    @Override
    public void update() {
        mana += 25;
    }
}
