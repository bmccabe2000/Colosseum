import Enemies.Enemy;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import javax.swing.*;

//This class handles logic like the introduction and random other events that need handling and aren't battle events
public class AuxiliaryLogic {

    //Controls the start of the game, including loading a saved game the player might have.
    public void start(TextArea messages, TextArea playerStats, TextArea enemyStats, Button nxtBtn, Player player) {
        int reply = JOptionPane.showConfirmDialog(null, "Load a saved game?\n" + "WARNING - SAVE GAME WILL\nBE OVERRIDDEN IF A\nNEW GAME IS STARTED", "Save Operation", JOptionPane.YES_NO_OPTION);

        //TODO add loading function for player
        if (reply == JOptionPane.YES_OPTION) {
            messages.setText("Save game loaded\nPress next to continue");
            nxtBtn.setOnAction(e -> {
                messages.clear();
            });
        }
        else {
            characterCreation(player);
            messages.clear();
            introduction(messages);
        }
    }

    //Writes out the introduction text
    public void introduction(TextArea messages){
        messages.setText("Welcome to Colosseum! Your goal is to win as many battles as you can.");
    }

    //Takes the player through the character creation process
    public void characterCreation(Player player){
        player.setPlayerName(JOptionPane.showInputDialog(null,"Please enter a name for your player"));
        while(player.getPlayerName().isEmpty()){
            player.setPlayerName(JOptionPane.showInputDialog(null,"INVALID - Please enter a name for your player"));
        }
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
