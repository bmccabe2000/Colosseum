package Project;

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
            JOptionPane.showMessageDialog(null, "Save game loaded", "Game Loaded", JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            characterCreation(player);
            messages.clear();
            introduction(messages);
        }
    }

    //Writes out the introduction text
    public void introduction(TextArea messages){
        JOptionPane.showMessageDialog(null, "Welcome to Colosseum! Your goal is to win as many battles as you can.", "Welcome", JOptionPane.INFORMATION_MESSAGE);
    }

    //Takes the player through the character creation process
    public void characterCreation(Player player){
        player.setPlayerName(JOptionPane.showInputDialog(null,"Please enter a name for your player"));
        while(player.getPlayerName().isEmpty()){
            player.setPlayerName(JOptionPane.showInputDialog(null,"INVALID - Please enter a name for your player"));
        }
    }

}
