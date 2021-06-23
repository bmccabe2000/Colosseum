import Enemies.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.List;

//TODO Add in logic for battling, could use a variable for dictating turns or possibly initiative but that would be a little more work to implement
//TODO Could add in a dummy enemy and set it as the default for current enemy so that its not just null.

//This class handles logic during battles
public class BattleLogic {

    private Enemy currentEnemy = null;
    private EnemyBank enemyBank = new EnemyBank();
    private List<Enemy> enemyList = new ArrayList<>();

    public Enemy getCurrentEnemy() {
        return currentEnemy;
    }

    public void setCurrentEnemy(Enemy currentEnemy) {
        this.currentEnemy = currentEnemy;
    }

    //Populates the list of enemies using the enemy bank class
    public void populateEnemyList(){
        enemyBank.generateEnemies();
        enemyList.addAll(enemyBank.getEnemyList());
    }

    //This function controls the battle phase of the game
    public void initiateBattle(TextArea messages, TextArea playerStats, TextArea enemyStats, Button nxtBtn, Button attackBtn, Button defendBtn, Button fleeBtn, Button spellBtn, Button backBtn, Player player){
        int turn = 0;
        boolean playerTurn = false;
        boolean enemyTurn = false;

        //TODO 1.Generate an enemy and set it to the current enemy to fight (DONE) 2.Create a system for deciding who goes first 3.Write out the logic of battling
        //Checking if the enemyList is populated and populating it if its not
        if(enemyList.isEmpty()){
            populateEnemyList();
        }

        //Getting a list of enemies that are within 2 levels of the players range and picking one randomly
        List<Enemy> enemiesInRange = generateEnemiesInRange(player);

        currentEnemy = enemiesInRange.get((int)(Math.random() * (enemiesInRange.size() - 0) + 0));

        setEnemyStatusField(enemyStats, currentEnemy);

        //Updating the player status field in case of any player status changes
        setPlayerStatusField(playerStats, player);

        //Starting combat, using a coin flip for now to decide who goes first


    }

    //This function returns a list of enemies that are in a range of 2 levels from the player.
    public List<Enemy> generateEnemiesInRange(Player player){
        List<Enemy> enemiesInRangeOfPlayerLevel = new ArrayList<>();

        for(Enemy i : enemyList){
            if(Math.abs(i.getEnemyLevel() - player.getPlayerLevel()) <= 2){
                enemiesInRangeOfPlayerLevel.add(i);
            }
        }
        return enemiesInRangeOfPlayerLevel;
    }

    //Takes in the player class and outputs the player's stats to the playerStats field
    public void setPlayerStatusField(TextArea playerStats, Player player){
        playerStats.setText("Name: " + player.getPlayerName() + "\n" + "Health: " + player.getPlayerHealth()  + "\n" + "Attack: " + player.getPlayerAttack() + "\n" + "Defense: " + player.getPlayerDefense() + "\n" + "Mana: " + player.getPlayerMana());
    }

    //Takes in an enemy class and outputs the enemy's stats to the enemyStats field
    public void setEnemyStatusField(TextArea enemyStats, Enemy enemy){
        enemyStats.setText("Name: " + enemy.getEnemyName() + "\n" + "Health: " + enemy.getEnemyHealth()  + "\n" + "Attack: " + enemy.getEnemyAttack() + "\n" + "Defense: " + enemy.getEnemyDefense() + "\n" + "Mana: " + enemy.getEnemyMana());
    }

    //Takes a string input and sets that to the messages field
    public void setMessageField(TextArea messages, String message){
        messages.setText(message);
    }

}
