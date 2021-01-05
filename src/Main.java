/*
TODO 1.Create GUI (DONE), 2.Add game logic (Basic, includes event handling for GUI), 3.Add in more detail (Make if more interesting and fun), 4. Add a save system
*/
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javax.swing.*;

public class Main extends Application {

    //Three message boxes that will display player and enemy states on each side
    //and messages to the player in the center (Declared here for use in methods later)
    TextArea playerStats = new TextArea();
    TextArea enemyStats = new TextArea();
    TextArea messages = new TextArea();

    public void start(Stage primaryStage){
        //Border pane will be used for organizing the other panes
        BorderPane orgPane = new BorderPane();
        orgPane.setPadding(new Insets(10,10,10,10));

        //This HBox pane will host the main control buttons at the bottom of the application
        HBox controlButtonsPane = new HBox();
        controlButtonsPane.setPadding(new Insets(10,10,10,10));
        controlButtonsPane.setSpacing(50);

        //Control buttons
        Button nextBtn = new Button("Next");
        Button attackBtn = new Button("Attack");
        Button defendBtn = new Button("Defend");
        Button fleeBtn = new Button("Flee");
        Button spellBtn = new Button("Use Spell");

        //Adding buttons to the control pane and setting them along the bottom of the application
        controlButtonsPane.getChildren().addAll(nextBtn, attackBtn, defendBtn, fleeBtn, spellBtn);
        controlButtonsPane.setAlignment(Pos.CENTER);
        orgPane.setBottom(controlButtonsPane);

        //Setting text fields as in-editable
        playerStats.setEditable(false);
        enemyStats.setEditable(false);
        messages.setEditable(false);

        //Setting the status fields to either side of the main display
        //and messages to the center
        orgPane.setLeft(playerStats);
        orgPane.setRight(enemyStats);
        orgPane.setCenter(messages);

        //Creating a scene and assigning the overlaying orgPane to it
        Scene scene = new Scene(orgPane, 1000, 500);
        primaryStage.setTitle("Library");
        primaryStage.setScene(scene);
        primaryStage.show();

        //Height binding the stat and message fields to the scene height
        playerStats.setPrefHeight(scene.getHeight());
        enemyStats.setPrefHeight(scene.getHeight());
        messages.setPrefHeight(scene.getHeight());

        //Width binding the stat fields to an eighth the width of the scene and the messages field to half
        playerStats.setPrefWidth(scene.getWidth() / 8);
        enemyStats.setPrefWidth(scene.getWidth() / 8);
        messages.setPrefWidth(scene.getWidth() / 2);


        //Game logic starts here
        Player mainPlayer = new Player();
        start(mainPlayer);
        setPlayerStatusField(mainPlayer);
    }

    //Controls the start of the game, including loading a saved game the player might have.
    public void start(Player player){

        int reply = JOptionPane.showConfirmDialog(null, "Load a saved game?\n" + "WARNING - SAVE GAME WILL\nBE OVERRIDDEN IF A\nNEW GAME IS STARTED", "Save Operation", JOptionPane.YES_NO_OPTION);

        //TODO add loading function for player
        if (reply == JOptionPane.YES_OPTION) {
            messages.setText("Save game loaded");

        }
        else {
            characterCreation(player);
            messages.clear();
            introduction();
        }

    }

    //Lets the user create a character if they didn't load one
    public void characterCreation(Player player){
        player.setPlayerName(JOptionPane.showInputDialog(null,"Please enter a name for your player"));
    }

    //TODO Write an introduction
    //A short introduction that explains what the game is
    public void introduction(){
        messages.setText("");
    }

    //Takes in the player class and outputs the player's stats to the playerStats field
    public void setPlayerStatusField(Player player){
        playerStats.setText("Name: " + player.getPlayerName() + "\n" + "Health: " + player.getPlayerHealth()  + "\n" + "Attack: " + player.getPlayerAttack() + "\n" + "Defense: " + player.getPlayerDefense() + "\n" + "Mana: " + player.getPlayerMana());
    }

    //Takes in an enemy class and outputs the enemy's stats to the enemyStats field
    public void setEnemyStatusField(Enemy enemy){
        enemyStats.setText("Name: " + enemy.getEnemyName() + "\n" + "Health: " + enemy.getEnemyHealth()  + "\n" + "Attack: " + enemy.getEnemyAttack() + "\n" + "Defense: " + enemy.getEnemyDefense() + "\n" + "Mana: " + enemy.getEnemyMana());
    }

    //Takes a string input and sets that to the messages field
    public void setMessageField(String message){
        messages.setText(message);
    }

    public static void main(String[] args){
        launch();
    }
}
