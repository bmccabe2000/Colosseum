import Enemies.*;

import java.util.HashMap;

//TODO Add in logic for battling, could use a variable for dictating turns or possibly initiative but that would be a little more work to implement
//TODO Could add in a dummy enemy and set it as the default for current enemy so that its not just null.

//This class handles logic during battles
public class BattleLogic {

    private Enemy currentEnemy = null;
    private EnemyBank enemyBank = new EnemyBank();
    private HashMap<String, Enemy> enemyHashMap = new HashMap<>();

    public Enemy getCurrentEnemy() {
        return currentEnemy;
    }

    public void setCurrentEnemy(Enemy currentEnemy) {
        this.currentEnemy = currentEnemy;
    }

    //Populates the hashMap of enemies using the enemy bank class
    public void populateEnemyHashMap(){
        enemyBank.generateEnemies();
        enemyHashMap.putAll(enemyBank.getEnemyHashMap());
    }



}
