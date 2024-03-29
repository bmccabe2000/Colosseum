package Project;

import Project.Enemies.*;
import java.util.ArrayList;
import java.util.List;

//This class creates and stores a retrievable list of all enemies
public class EnemyBank {

    private List<Enemy> enemyList = new ArrayList<>();

    public List<Enemy> getEnemyList() {
        return enemyList;
    }

    public void setEnemyList(List<Enemy> enemyList) {
        this.enemyList.addAll(enemyList);
    }

    //This function generates all the enemies in the game and adds them to the list of enemies
    //Clear is called on the enemyList before anything else to prevent duplicates from being added to the list and ensure
    //that each entry has its default values
    public void generateEnemies() {
        enemyList.clear();
        Spider spider = new Spider("Spider", 1, 10,1,1,10,10,1,1,10);
        enemyList.add(spider);

        //TODO add more enemies
    }
}
