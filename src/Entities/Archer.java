package Entities;
import Game.EntityApi;
import common.C;

public class Archer extends Hero {

    private int maxArrows = 5;
    private int arrows = maxArrows;
    public Archer(EntityApi api, String name) {
        super(api, name);
    }

    @Override
    public String listOptions() {
        return """
                        Choose action:\s
                        1. Shoot arrow
                        2. Hit with dagger
                        3. Reload
                        """;
    }

    @Override
    public void processInput(String inputString) {
        var enemy = api.getNearbyEntities(this).stream().filter(e -> e instanceof Enemy).findFirst();
        if(enemy.isEmpty())
            return;
        switch (inputString) {
            case "1" -> shootArrowAt(enemy.get());
            case "2" -> hitWithDagger(enemy.get());
            case "3" -> reload();
        }
    }

    @Override
    public boolean isAlive() {
        return false;
    }

    public void shootArrowAt(Entity enemy) {
        if (arrows > 0) {
           api.attack(this, enemy, 15);
           arrows--;
            C.log("You shoot " + C.name(enemy) + " with your bow");
        }
        else
            C.log("Your quiver is empty, reload it with more arrows");
    }

    public void hitWithDagger(Entity enemy) {
        api.attack(this, enemy, 30);
        C.log("You slice " + C.name(enemy) + " with your dagger");
    }

    public void reload() {
        arrows = maxArrows;
        C.log("You replenish your quiver with " + maxArrows + " arrows");
    }

    @Override
    public void update() {

    }
}
