package Project.Items;

import Project.Enemies.Enemy;
import Project.Player;

//This class defines how an item the player can use works
public abstract class Item {

    String name = "item";
    String description = "";
    int cost = 0;
    int levelAppearance = 0;

    public Item(String name, int cost, String description, int levelAppearance){
        this.name = name;
        this.cost = cost;
        this.description = description;
        this.levelAppearance = levelAppearance;
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

    public int getLevelAppearance() {
        return levelAppearance;
    }

    public void setLevelAppearance(int levelAppearance) {
        this.levelAppearance = levelAppearance;
    }

    //This function handles the effects of what an item does
    abstract public void effect(Player player, Enemy enemy);
}
