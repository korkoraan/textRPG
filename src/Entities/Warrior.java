package Entities;
import Game.EntityApi;
import common.C;
import common.Consts;

import java.util.Random;

public class Warrior extends Hero {

    private boolean enraged = false;

    public Warrior(EntityApi api, String name) {
        super(api, name, Consts.DEFAULT_HP * 2);
    }

    @Override
    public String listOptions() {
        return """
                    Choose action:\s
                    1. Hit with sword
                    2. Kick
                    3. Shout battle cry
                    Or press enter to do nothing
                    """;
    }

    @Override
    public void processInput(String inputString) {
        var enemy = api.getNearbyEntities(this).stream().filter(e -> e instanceof Enemy).findFirst();
        if(enemy.isEmpty())
            return;
        switch (inputString) {
            case "1" -> hitWithSword(enemy.get());
            case "2" -> kick(enemy.get());
            case "3" -> battleCry();
        }
    }

    public void battleCry() {
        enraged = true;
        C.log("You scream at the top of your lungs and become very ANGRYYYYY!!!");
    }

    public void hitWithSword(Entity enemy) {
        var dmgPts = new Random().nextInt(15, 20);
        if (enraged) dmgPts *= 2;
        api.attack(this, enemy, dmgPts);
        C.log("You strike with your mighty sword");
    }

    public void kick(Entity enemy) {
        var dmgPts = new Random().nextInt(1, 20);
        if (enraged) dmgPts *= 2;
        api.attack(this, enemy, dmgPts);
        C.log("You kick your enemy");
    }

    @Override
    public void update() {

    }
}