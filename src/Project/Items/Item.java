package Project.Items;

import Project.Enemies.Enemy;
import Project.Player;

//This class defines how an item the player can use works
public abstract class Item {

    String name = "item";
    int cost = 0;
    String description = "";

    public Item(String name, int cost, String description){
        this.name = name;
        this.cost = cost;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //This function handles the effects of what an item does
    abstract public void effect(Player player, Enemy enemy);
}
