package Project;

import Project.Items.*;
import javafx.collections.ObservableList;

import javax.swing.*;
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

    //Searches the list of items for an item name and returns the description
    public String getItemDescription(String name){
        for(int i = 0; i < items.size(); i++){
            if(items.get(i).getName().equals(name)){
                return items.get(i).getDescription();
            }
        }
        return "Nothing for sale";
    }

    //Buys an item from the shop
    public void buyItem(Player player, String selection, ObservableList itemsForSale){
        for(int i = 0; i < items.size(); i++){
            if(items.get(i).getName().equals(selection)){
                if(items.get(i).getCost() <= player.getPlayerGold()){
                    player.setPlayerGold(player.getPlayerGold() - items.get(i).getCost());
                    player.addItemToInventory(items.get(i));
                    JOptionPane.showMessageDialog(null, "You have successfully bought " + items.get(i).getName(), "Item Purchased", JOptionPane.INFORMATION_MESSAGE);
                    itemsForSale.remove(items.get(i).getName());
                }
                else{
                    JOptionPane.showMessageDialog(null, "You need " + (items.get(i).getCost() - player.getPlayerGold()) + " more gold to purchase this item" , "Not Enough Gold", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }
}
