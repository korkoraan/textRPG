package Entities;

import Game.EntityApi;
import common.TimeBound;

public abstract class Entity implements TimeBound {
    protected EntityApi api;

    public Entity(EntityApi entityApi) {
        this.api = entityApi;
    }
}