package Game;

import Entities.Entity;

import java.util.List;

public interface EntityApi {
    public List<Entity> getNearbyEntities(Entity caller);

    public void attack(Entity attacker, Entity target, int dmgPts);
}
