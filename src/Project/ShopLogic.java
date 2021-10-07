package Project;

import Project.Items.*;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

//This class handles the logic for the in game shop
public class ShopLogic {

    private List<Item> items = new ArrayList<Item>();
    private ItemBank itemBank = new ItemBank();

    //Generates a list of items that are within +/- 2 levels of the player and sets them in the shops list of itemsForSale
    public void generateItemsWithinRange(Player player, ObservableList itemsForSale){
        itemBank.generateItems();

        itemsForSale.clear();

        for(Item i : itemBank.getItemList()){
            if(Math.abs(i.getLevelAppearance() - player.getPlayerLevel()) <= 2){
                items.add(i);
                itemsForSale.add(i.getName());
                //TODO get items to display their description when hovered over and make it possible to purchase items
            }
        }
    }

    //Returns the list of items within range
    public List<Item> getItems() {
        return items;
    }

    //Sets the list of items within range
    public void setItems(List<Item> items) {
        this.items = items;
    }
}
