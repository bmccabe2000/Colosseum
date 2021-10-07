package Project.Items;

import Project.Enemies.Enemy;
import Project.Player;

public class HealthPotion extends Item{

    public HealthPotion(String name, int cost, String description, int levelAppearance) {
        super(name, cost, description, levelAppearance);
    }

    //The HealthPotion restores 10 health points
    //If the player is already at full health or the health potion put the player over 100 health points
    @Override
    public void effect(Player player, Enemy enemy) {
        player.setPlayerHealth(player.getPlayerHealth() + 10);
        if(player.getPlayerHealth() > 100){
            player.setPlayerHealth(100);
        }
    }
}
