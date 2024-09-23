package Environment;

import Entities.Entity;
import common.TimeBound;

import java.util.ArrayList;
import java.util.Arrays;

public class BattleGround extends Environment implements TimeBound {
    public BattleGround(ArrayList<Entity> entities) {
        this.entities = entities;
    }

    public BattleGround(Entity... entities) {
        this.entities = new ArrayList<>();
        this.entities.addAll(Arrays.asList(entities));
    }

    public void update() {
        for (Entity entity : entities) {
            entity.update();
        }
    }

    public void Spawn(Entity entity) {
        entities.add(entity);
    }
}
