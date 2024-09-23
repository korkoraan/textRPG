package Game;

import Entities.*;
import Environment.BattleGround;
import common.C;
import common.Mortal;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Game implements EntityApi {

    private BattleGround battleGround;

    public Game() {
        battleGround = new BattleGround();
    }

    public void run() {
        var scanner = new Scanner(System.in);
        var rand = new Random();
        Hero hero = null;


        System.out.println("""
                Basic controls:\s
                 press 1,2,3... to take listed actions\s
                 press ENTER to do nothing\s
                 press Q to exit game""");

        C.log("Pick a name for your hero: ");
        String name = scanner.nextLine();

        while (true) {
            C.log("""
                    Pick a class:\s
                    1. Warrior
                    2. Mage
                    3. Archer
                    """);
            switch (scanner.nextLine()) {
                case "1" -> hero = new Warrior(this, name);
                case "2" -> hero = new Mage(this, name);
                case "3" -> hero = new Archer(this, name);
                default -> {
                    continue;
                }
            }
            break;
        }
        C.log("Greetings, " + C.name(hero) + " " + name);
        battleGround.entities.add(new Zombie(this));
        battleGround.entities.add(new Troll(this));
        battleGround.entities.add(hero);

        C.log("You find yourself facing the a zombie and a cave troll");
        while(true) {
            C.log(hero.listOptions());
            var option = scanner.nextLine();
            if(option.equals("q"))
                break;
            if(!option.equals(""))
                hero.processInput(option);
            battleGround.update();
            var hp = hero.getHealthPts();
            if(hp < 1) {
                C.log("---------GAME OVER------------");
            }
            C.log("You have " + hp + " hp points");
        }
    }

    @Override
    public List<Entity> getNearbyEntities(Entity caller) {
        return battleGround.entities.stream().filter(e -> e != caller).collect(Collectors.toList());
    }

    @Override
    public void attack(Entity attacker, Entity target, int dmgPts) {
        if(target instanceof Mortal mortal){
            mortal.takeDamage(dmgPts);
            C.log(C.name(attacker) + " deals " + dmgPts + " to " + C.name(target) + ". It now has " + mortal.getHealthPts() + " hp");
            if(!mortal.isAlive()) {
                C.log(C.name(mortal) + " DIES!!");
                battleGround.entities.remove(mortal);
            }
        } else
            C.log(C.name(attacker) + " attacks " + C.name(target) + ", but it appears to be immortal");
    }
}
