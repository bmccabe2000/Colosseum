package Project;
import Project.Items.*;
import java.util.ArrayList;
import java.util.List;

public class ItemBank {

    List<Item> ItemList = new ArrayList<Item>();

    public List<Item> getItemList() {
        return ItemList;
    }

    public void setItemList(List<Item> itemList) {
        ItemList = itemList;
    }

    public void generateItems(){
        ItemList.clear();
        HealthPotion healthPotion = new HealthPotion("Health Potion", 15, "A small vile filed with a thick red liquid. Heals for 10 HP", 0);
        ItemList.add(healthPotion);
        //TODO make more items
    }

}
