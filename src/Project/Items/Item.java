package Project.Items;

import Project.Enemies.Enemy;
import Project.Player;

//This class defines how an item the player can use works
public abstract class Item {

    String name = "item";
    int cost = 0;

    public Item(String name, int cost){
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    //This function handles the effects of what an item does
    abstract public void effect(Player player, Enemy enemy);
}
