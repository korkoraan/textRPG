package common;

public interface Mortal {
    default boolean isAlive() {
        return getHealthPts() > 0;
    }
    void takeDamage(int dmgPts);
    int getHealthPts();
}
