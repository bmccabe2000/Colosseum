import Enemies.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

//TODO Add in logic for battling, could use a variable for dictating turns or possibly initiative but that would be a little more work to implement
//TODO Could add in a dummy enemy and set it as the default for current enemy so that its not just null.

//This class handles logic during battles
public class BattleLogic {

    private Enemy currentEnemy = null;
    private EnemyBank enemyBank = new EnemyBank();
    private List<Enemy> enemyList = new ArrayList<>();
    private int turn = 0;
    private boolean playerTurn = false;
    private boolean enemyTurn = false;
    private DecimalFormat doubleFormat = new DecimalFormat("###.##");

    //Populates the list of enemies using the enemy bank class
    public void populateEnemyList(){
        enemyBank.generateEnemies();
        enemyList.addAll(enemyBank.getEnemyList());
    }

    //This function controls the battle phase of the game
    public void battle(TextArea messages, TextArea playerStats, TextArea enemyStats, Button nxtBtn, Button attackBtn, Button defendBtn, Button fleeBtn, Button spellBtn, Button backBtn, Player player){
        //TODO 1.Generate an enemy and set it to the current enemy to fight (DONE) 2.Create a system for deciding who goes first 3.Write out the logic of battling
        //Checking if the enemyList is populated and populating it if its not
        if(enemyList.isEmpty()){
            populateEnemyList();
        }

        //Getting a list of enemies that are within 2 levels of the players range and picking one randomly
        List<Enemy> enemiesInRange = generateEnemiesInRange(player);

        currentEnemy = enemiesInRange.get((int)(Math.random() * ((enemiesInRange.size() - 1)) + 0));

        //Setting both status fields prior to battle
        updateStatuses(enemyStats, playerStats, player);

        //Set of event handlers for each button press
        //After a button press the appropriate action is taken before the status fields are updated, then the enemy takes its turn and the status fields are updated again
        attackBtn.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                double damage = Double.parseDouble(doubleFormat.format(player.getPlayerAttack() - (currentEnemy.getEnemyDefense() * 0.5)));
                currentEnemy.setEnemyHealth(Double.parseDouble(doubleFormat.format(currentEnemy.getEnemyHealth() - damage)));
                messages.appendText("\nYou attacked " + currentEnemy.getEnemyName() + " for " + damage + " damage!");
                updateStatuses(enemyStats, playerStats, player);
                enemyTurn(messages, playerStats, enemyStats, nxtBtn, attackBtn, defendBtn, fleeBtn, spellBtn, backBtn, player);
                updateStatuses(enemyStats, playerStats, player);
            }
        });

        defendBtn.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                //TODO this probably needs to become a status that will be removed through a check statuses function after the enemies turn has ended.
                double healing = Double.parseDouble(doubleFormat.format(player.getPlayerDefense() * 0.1));
                double manaRegen = Double.parseDouble(doubleFormat.format(player.getPlayerDefense() * 0.2));
                player.setPlayerHealth(Double.parseDouble(doubleFormat.format(player.getPlayerHealth() + healing)));
                messages.appendText("\nYou defended and healed for " + healing + " health and " + manaRegen + " mana");
                updateStatuses(enemyStats, playerStats, player);
                enemyTurn(messages, playerStats, enemyStats, nxtBtn, attackBtn, defendBtn, fleeBtn, spellBtn, backBtn, player);
                updateStatuses(enemyStats, playerStats, player);
            }
        });

        spellBtn.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                if(player.getPlayerMana() >= 1){
                    double spellDamage = Double.parseDouble(doubleFormat.format((Math.random() * (5 - 1) + 1)));
                    player.setPlayerMana(player.getPlayerMana() - 1);
                    currentEnemy.setEnemyHealth(Double.parseDouble(doubleFormat.format(currentEnemy.getEnemyHealth() - spellDamage)));
                    messages.appendText("\nYou hurl a fireball at " + currentEnemy.getEnemyName() + " that does " + spellDamage + " damage");
                }
                else{
                    messages.appendText("\nYou try to throw a fireball but it fizzles out in your hand because you don't have enough mana");
                }
                updateStatuses(enemyStats, playerStats, player);
                enemyTurn(messages, playerStats, enemyStats, nxtBtn, attackBtn, defendBtn, fleeBtn, spellBtn, backBtn, player);
                updateStatuses(enemyStats, playerStats, player);
            }
        });

        fleeBtn.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                //TODO again, this needs to be a status that is handled and checked during battle
                updateStatuses(enemyStats, playerStats, player);
                enemyTurn(messages, playerStats, enemyStats, nxtBtn, attackBtn, defendBtn, fleeBtn, spellBtn, backBtn, player);
                updateStatuses(enemyStats, playerStats, player);
            }
        });

        backBtn.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                //TODO probably can remove this and replace it with something else.
            }
        });

        //TODO implement some kind of statuses possibly its own class that is assigned to the player and enemy that has a check function here.
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

    //This functions handles the event handling for all of the buttons that the player can press on their turn
    private void enemyTurn(TextArea messages, TextArea playerStats, TextArea enemyStats, Button nxtBtn, Button attackBtn, Button defendBtn, Button fleeBtn, Button spellBtn, Button backBtn, Player player){
        double enemyDamage = currentEnemy.getEnemyAttack() - (player.getPlayerDefense() * 0.25);
        player.setPlayerHealth(player.getPlayerHealth() - enemyDamage);
        messages.appendText("\n" + currentEnemy.getEnemyName() + " attacks you for " + enemyDamage + " damage.");
    }

    //Updates the status fields for the player and enemy
    private void updateStatuses(TextArea enemyStats, TextArea playerStats, Player player){
        setEnemyStatusField(enemyStats, currentEnemy);
        setPlayerStatusField(playerStats, player);

    }

}
