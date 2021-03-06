import Enemies.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//This class creates and stores a retrievable list of enemies
public class EnemyBank {

    private List<Enemy> enemyList = new ArrayList<>();

    public List<Enemy> getEnemyList() {
        return enemyList;
    }

    public void setEnemyList(List<Enemy> enemyList) {
        this.enemyList.addAll(enemyList);
    }

    //This function generates all of the enemies in the game and adds them to the list of enemies
    public void generateEnemies() {
        Spider spider = new Spider();
        spider.populateStats("Spider", 1, 10,1,1,10);
        enemyList.add(spider);

        //TODO add more enemies
    }
}
