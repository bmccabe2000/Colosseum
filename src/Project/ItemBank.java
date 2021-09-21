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
        HealthPotion healthPotion = new HealthPotion("Health Potion", 15);
        ItemList.add(healthPotion);
    }

}
