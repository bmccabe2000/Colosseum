package Project;

import Project.Enemies.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import javax.swing.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

//This class handles the logic of enemies and parts of the display of the GUI during battles
public class BattleLogic {

    private Enemy liveEnemy = null;
    private EnemyBank enemyBank = new EnemyBank();
    private List<Enemy> enemyList = new ArrayList<>();
    private DecimalFormat doubleFormat = new DecimalFormat("###.##");
    private int experienceEarned = 0;
    private int goldEarned = 0;

    //Populates the list of enemies using the enemy bank class
    //Clear is called first to prevent and duplicate values from being put in the list
    public void populateEnemyList(){
        enemyList.clear();
        enemyBank.generateEnemies();
        enemyList.addAll(enemyBank.getEnemyList());
    }

    //This function controls the battle phase of the game
    public void battle(TextArea messages, TextArea playerStats, TextArea enemyStats, Button nxtBtn, Button attackBtn, Button defendBtn, Button spellBtn, Player player){
        //Populating the enemy list to reset any statues or damage taken from previous battles
        populateEnemyList();

        //Getting a list of enemies that are within 2 levels of the players range and picking one randomly
        List<Enemy> enemiesInRange = generateEnemiesInRange(player);

        liveEnemy = enemiesInRange.get((int)(Math.random() * ((enemiesInRange.size() - 1))));

        //Setting both status fields prior to battle
        updateStatuses(enemyStats, playerStats, player);
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

    //This functions handles the event handling for all the buttons that the player can press on their turn
    public void enemyTurn(TextArea messages, TextArea playerStats, TextArea enemyStats, Button nxtBtn, Button attackBtn, Button defendBtn, Button spellBtn, Player player){
        double enemyDamage = Double.parseDouble(doubleFormat.format((Math.random() * ((liveEnemy.getEnemyAttack() + 2) - liveEnemy.getEnemyAttack()) + (liveEnemy.getEnemyAttack())) - (player.getPlayerDefense() * 0.5)));
        player.setPlayerHealth(Double.parseDouble(doubleFormat.format(player.getPlayerHealth() - enemyDamage)));
        messages.appendText("\n" + liveEnemy.getEnemyName() + " attacks you for " + enemyDamage + " damage.");
    }

    //Updates the status fields for the player and enemy
    public void updateStatuses(TextArea enemyStats, TextArea playerStats, Player player){
        setEnemyStatusField(enemyStats, liveEnemy);
        setPlayerStatusField(playerStats, player);

    }

    //Returns the current enemy
    public Enemy getCurrentEnemy() {
        return liveEnemy;
    }

    //Checks if the player or enemy has died and takes action accordingly
    //Ties favor the enemy since the player's health is checked first
    public void winLose(Player player, Enemy enemy, TextArea messages, Stage mainStage, Scene switchScene){
        if(player.getPlayerHealth() <= 0){
            JOptionPane.showMessageDialog(null, "You Lost!");
            System.exit(0);
        }
        //If a player wins they are shown how much experience and gold they earned
        else if(enemy.getEnemyHealth() <= 0){
            JOptionPane.showMessageDialog(null, "You Won!\nEXP Earned: " + experienceEarned + "\nGold Earned: " + goldEarned);
            player.setPlayerExperience(player.getPlayerExperience() + experienceEarned);
            player.setPlayerGold(player.getPlayerGold() + goldEarned);
            mainStage.setScene(switchScene);
            messages.clear();
        }
    }

    public int getExperienceEarned() {
        return experienceEarned;
    }

    public void setExperienceEarned(int experienceEarned) {
        this.experienceEarned = experienceEarned;
    }

    public void addExperienceEarned(int experience){
        this.experienceEarned += experience;
    }

    public int getGoldEarned() {
        return goldEarned;
    }

    public void setGoldEarned(int goldEarned) {
        this.goldEarned = goldEarned;
    }

    public void addGoldEarned(int gold){
        this.goldEarned += gold;
    }

    //Generates a random number of gold and experience earned
    //Modifiers will add to the default max (3) and min (1) of the fold and experience that can be earned per action
    public void generateGoldAndExperienceEarned(int goldModifier, int experienceModifier){
        addGoldEarned((int)Math.floor(Math.random()*((3 + goldModifier) - (1 + goldModifier))+ (1 + goldModifier)));
        addExperienceEarned((int)Math.floor(Math.random()*((3 + experienceModifier) - (1 + experienceModifier))+ (1 + experienceModifier)));
    }

    //Sets the current enemy
    public void setLiveEnemy(Enemy currentEnemy) {
        this.liveEnemy = currentEnemy;
    }

    //Returns the enemy bank
    public EnemyBank getEnemyBank() {
        return enemyBank;
    }

    //Sets the enemy bank
    public void setEnemyBank(EnemyBank enemyBank) {
        this.enemyBank = enemyBank;
    }

    //Returns the enemy list
    public List<Enemy> getEnemyList() {
        return enemyList;
    }

    //Sets the enemy list
    public void setEnemyList(List<Enemy> enemyList) {
        this.enemyList = enemyList;
    }

}
