import Enemies.*;

import java.util.HashMap;

//This class creates and stores a retrievable hash map of enemies where the key is the enemies name.
//Names stored in the hashMap have the fist letter capitalized as do the actually names of the enemies.
public class EnemyBank {

    private HashMap<String, Enemy> enemyHashMap = new HashMap();

    public HashMap<String, Enemy> getEnemyHashMap() {
        return enemyHashMap;
    }

    public void setEnemyHashMap(HashMap<String, Enemy> enemyHashMap) {
        this.enemyHashMap = enemyHashMap;
    }

    //This function generates all of the enemies in the game and adds them to the hash map of enemies
    public void generateEnemies() {
        Spider spider = new Spider();
        spider.populateStats("Spider", 1, 10,1,1,10);
        enemyHashMap.put(spider.getEnemyName(), spider);

        //TODO add more enemies
    }
}
