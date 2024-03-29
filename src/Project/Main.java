package Project;/*
TODO 1.Create GUI (DONE), 2.Add game logic (Basic, includes event handling for GUI), 3.Add in more detail (Make if more interesting and fun), 4. Add a save system
*/
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import Project.Enemies.*;

import javax.swing.*;
import java.text.DecimalFormat;

public class Main extends Application {

    public void start(Stage primaryStage) {
        //Creating some basic insets to use for padding as a lot of the panes use the same padding
        Insets basicInsets = new Insets(10,10,10,10);

        //Border pane will be used for organizing the other panes
        BorderPane battleOrgPane = new BorderPane();
        battleOrgPane.setPadding(basicInsets);

        //This HBox pane will host the main control buttons at the bottom of the application
        HBox battleControlButtonsPane = new HBox();
        battleControlButtonsPane.setPadding(basicInsets);
        battleControlButtonsPane.setSpacing(50);

        //Control buttons
        Button attackBtn = new Button("Attack");
        Button defendBtn = new Button("Defend");
        Button spellBtn = new Button("Use Spell");
        Button itemBtn = new Button("Use Item");

        //Adding buttons to the control pane and setting them along the bottom of the application
        battleControlButtonsPane.getChildren().addAll(attackBtn, defendBtn, spellBtn, itemBtn);
        battleControlButtonsPane.setAlignment(Pos.CENTER);
        battleOrgPane.setBottom(battleControlButtonsPane);

        //Three message boxes that will display player and enemy states on each side
        //and messages to the player in the center
        TextArea playerStats = new TextArea();
        TextArea enemyStats = new TextArea();
        TextArea messages = new TextArea();

        //Setting text fields as in-editable
        playerStats.setEditable(false);
        enemyStats.setEditable(false);
        messages.setEditable(false);

        //Setting the status fields to either side of the main display
        //and messages to the center
        battleOrgPane.setLeft(playerStats);
        battleOrgPane.setRight(enemyStats);
        battleOrgPane.setCenter(messages);

        //Creating a scene and assigning the overlaying orgPane to it
        Scene battleScene = new Scene(battleOrgPane, 1000, 500);

        //Width binding the stat fields to an eighth the width of the scene and the messages field to half
        playerStats.setPrefWidth(battleScene.getWidth() / 8);
        enemyStats.setPrefWidth(battleScene.getWidth() / 8);
        messages.setPrefWidth(battleScene.getWidth() / 2);

        //The following code creates a "between" scene that is used when the player is between battles
        //Most of the coder is similar to that used for the battle scene but with different objects
        BorderPane betweenMenuOrgPane = new BorderPane();
        betweenMenuOrgPane.setPadding(basicInsets);

        //Setting up a new scene for the between phase
        Scene betweenScene = new Scene(betweenMenuOrgPane, 1000, 500);

        //Creating a list view with a static list that will list out options the player has to select from
        ObservableList<String> options = FXCollections.observableArrayList("Shop", "Training", "Next Battle", "Exit Game");
        ListView<String> playerOptions = new ListView(options);
        playerOptions.setEditable(false);
        playerOptions.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        //Creating a text area for the description of their selection in the playerOptions listview
        TextArea optionDescription = new TextArea();
        optionDescription.setEditable(false);

        //Setting the text area's to the betweenMenuOrgPane
        betweenMenuOrgPane.setCenter(optionDescription);
        betweenMenuOrgPane.setRight(playerOptions);

        //Creating a select button
        Button betweenSelectButton = new Button("Select");

        //Setting up a simple HBox to house the select button
        HBox selectButtonBox = new HBox();
        selectButtonBox.getChildren().addAll(betweenSelectButton);
        selectButtonBox.setAlignment(Pos.CENTER);
        selectButtonBox.setPadding(basicInsets);
        selectButtonBox.setSpacing(10);
        betweenMenuOrgPane.setBottom(selectButtonBox);

        //Creating a menu for the shop interface
        BorderPane shopOrgPane = new BorderPane();
        shopOrgPane.setPadding(basicInsets);

        //Setting up a select and back button for the shop
        Button shopSelectButton = new Button("Select");
        Button shopBackBtn = new Button ("Back");

        //Setting up an HBox to host a select button at the bottom of the pane
        HBox shopMenuButtonBox = new HBox();
        shopMenuButtonBox.getChildren().addAll(shopSelectButton, shopBackBtn);
        shopMenuButtonBox.setAlignment(Pos.CENTER);
        shopMenuButtonBox.setPadding(basicInsets);
        shopMenuButtonBox.setSpacing(10);
        shopOrgPane.setBottom(shopMenuButtonBox);

        //Setting up a list view that will hold items the shop is selling
        ObservableList<String> itemsForSale = FXCollections.observableArrayList("PlaceHolder");
        ListView<String> shopItems = new ListView(itemsForSale);
        shopItems.setEditable(false);
        shopItems.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        shopOrgPane.setCenter(shopItems);

        //Setting up a text box that will hold a description of items the shop is selling that the user has selected
        TextArea itemDescription = new TextArea();
        itemDescription.setEditable(false);
        shopOrgPane.setRight(itemDescription);

        //Setting up a new scene for the shop
        Scene shopScene = new Scene(shopOrgPane, 1000, 500);

        //Height binding the itemDescription and shopItems to reasonable heights
        shopItems.setPrefHeight(shopScene.getHeight()/5);
        itemDescription.setPrefHeight(shopScene.getHeight()/5);

        //Creating one final menu for training
        BorderPane trainingOrgPane = new BorderPane();
        trainingOrgPane.setPadding(basicInsets);

        //Setting up a select and back button for the training scene
        Button trainingSelectButton = new Button("Select");
        Button trainingBackBtn = new Button("Back");

        //Setting up an HBox to host a select button at the bottom of the pane
        HBox trainingMenuButtonBox = new HBox();
        trainingMenuButtonBox.getChildren().addAll(trainingSelectButton, trainingBackBtn);
        trainingMenuButtonBox.setAlignment(Pos.CENTER);
        trainingMenuButtonBox.setPadding(basicInsets);
        trainingMenuButtonBox.setSpacing(10);
        trainingOrgPane.setBottom(trainingMenuButtonBox);

        //Setting up a list view that will hold items the shop is selling
        ObservableList<String> trainingOptionsList = FXCollections.observableArrayList("Health", "Attack", "Defense", "Mana");
        ListView<String> trainingOptions = new ListView(trainingOptionsList);
        trainingOptions.setEditable(false);
        trainingOptions.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        trainingOrgPane.setLeft(trainingOptions);

        //Setting up a text box that will hold a description of items the shop is selling that the user has selected
        TextArea trainingDescription = new TextArea();
        itemDescription.setEditable(false);
        trainingOrgPane.setCenter(trainingDescription);

        //Setting up a new scene for the shop
        Scene trainingScene = new Scene(trainingOrgPane, 1000, 500);

        //Setting up the stage
        //Available scenes: battleScene, betweenScene, shopScene, trainingScene
        primaryStage.setTitle("Colosseum");
        primaryStage.setScene(battleScene);
        primaryStage.show();

        //***************************
        //* Game driver starts here *
        //***************************
        //Starting off by setting up player and logic classes
        Player mainPlayer = new Player();
        BattleLogic battleLogic = new BattleLogic();
        AuxiliaryLogic auxiliaryLogic = new AuxiliaryLogic();
        auxiliaryLogic.start(messages, playerStats, enemyStats, itemBtn, mainPlayer);
        battleLogic.setPlayerStatusField(playerStats, mainPlayer);
        //Testing out battle mechanics**************************************************************************************************
        battleLogic.battle(messages, playerStats, enemyStats, itemBtn, attackBtn, defendBtn, spellBtn, mainPlayer);
        //Pulling the enemy from the battle and setting it here to use for checks
        Enemy currentEnemy = battleLogic.getCurrentEnemy();
        DecimalFormat doubleFormat = new DecimalFormat("###.##");

        //*********************************************
        //Declaring button handlers for battle buttons*
        //*********************************************

        //After a button press the appropriate action is taken before the status fields are updated, then the enemy takes its turn and the status fields are updated again
        //Attacks are based off of the players attack - half of the enemies defense
        attackBtn.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                //TODO Add more randomness to attacking and defending just by a little so that attacks arent always the same strength
                double damage = Double.parseDouble(doubleFormat.format((Math.random() * ((mainPlayer.getPlayerAttack() + 2) - mainPlayer.getPlayerAttack()) + (mainPlayer.getPlayerAttack()) - (currentEnemy.getEnemyDefense() * 0.5))));
                currentEnemy.setEnemyHealth(Double.parseDouble(doubleFormat.format(currentEnemy.getEnemyHealth() - damage)));
                messages.appendText("\nYou attacked " + currentEnemy.getEnemyName() + " for " + damage + " damage!");
                battleLogic.updateStatuses(enemyStats, playerStats, mainPlayer);
                battleLogic.enemyTurn(messages, playerStats, enemyStats, itemBtn, attackBtn, defendBtn, spellBtn, mainPlayer);
                battleLogic.updateStatuses(enemyStats, playerStats, mainPlayer);
                battleLogic.generateGoldAndExperienceEarned(0,0);
                battleLogic.winLose(mainPlayer,currentEnemy,messages,primaryStage,betweenScene);
            }
        });

        //Defending will heal the player and also give them some mana
        //It will also up their defence for a turn
        defendBtn.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                //TODO this probably needs to become a status that will be removed through a check statuses function after the enemies turn has ended.
                double healing = Double.parseDouble(doubleFormat.format(Math.random() * (mainPlayer.getPlayerDefense() - 0.5) + 0.5));
                double manaRegen = Double.parseDouble(doubleFormat.format(Math.random() * (mainPlayer.getPlayerDefense() - 0.5) + 0.5));
                mainPlayer.setPlayerHealth(Double.parseDouble(doubleFormat.format(mainPlayer.getPlayerHealth() + healing)));
                mainPlayer.setPlayerDefense(mainPlayer.getPlayerDefense() + 1);
                messages.appendText("\nYou defended and healed for " + healing + " health and " + manaRegen + " mana");
                battleLogic.updateStatuses(enemyStats, playerStats, mainPlayer);
                battleLogic.enemyTurn(messages, playerStats, enemyStats, itemBtn, attackBtn, defendBtn, spellBtn, mainPlayer);
                mainPlayer.setPlayerDefense(mainPlayer.getPlayerDefense() - 1);
                battleLogic.updateStatuses(enemyStats, playerStats, mainPlayer);
                battleLogic.generateGoldAndExperienceEarned(0,0);
                battleLogic.winLose(mainPlayer,currentEnemy,messages,primaryStage,betweenScene);
            }
        });

        //When a spell is cast, first it is determined if the user has enough mana to cast a spell, then a damage calculations are done and a spell is cast
        //Could possibly add different spells in the future with a separate interface to pick spells from.
        spellBtn.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                if(mainPlayer.getPlayerMana() >= 1){
                    double spellDamage = Double.parseDouble(doubleFormat.format((Math.random() * (5 - 1) + 1)));
                    mainPlayer.setPlayerMana(mainPlayer.getPlayerMana() - 1);
                    currentEnemy.setEnemyHealth(Double.parseDouble(doubleFormat.format(currentEnemy.getEnemyHealth() - spellDamage)));
                    messages.appendText("\nYou hurl a fireball at " + currentEnemy.getEnemyName() + " that does " + spellDamage + " damage");
                }
                else{
                    messages.appendText("\nYou try to throw a fireball but it fizzles out in your hand because you don't have enough mana");
                }
                battleLogic.updateStatuses(enemyStats, playerStats, mainPlayer);
                battleLogic.enemyTurn(messages, playerStats, enemyStats, itemBtn, attackBtn, defendBtn, spellBtn, mainPlayer);
                battleLogic.updateStatuses(enemyStats, playerStats, mainPlayer);
                battleLogic.generateGoldAndExperienceEarned(0,0);
                battleLogic.winLose(mainPlayer,currentEnemy,messages,primaryStage,betweenScene);
            }
        });

        //TODO create a handler for using items
        //End of battle testing******************************************************************************************************************

        //Start of between testing******************************************************************************************************************

        //Handles the select button on the inBetween stage. Goes to different menus, the next battle, or exits the game based off what the player has selected.
        //If nothing is selected an error message appears
        betweenSelectButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if(playerOptions.getSelectionModel().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Please select an option", "Nothing Selected", JOptionPane.WARNING_MESSAGE);
                }
                else{
                    switch(playerOptions.getSelectionModel().getSelectedItem()){
                        case "Shop":
                            primaryStage.setScene(shopScene);
                            break;
                        case "Training":
                            primaryStage.setScene(trainingScene);
                            break;
                        case "Next Battle":
                            //TODO make it so the next battle works, currently the enemy dies and the battle instantly ends, investigate this
                            battleLogic.battle(messages, playerStats, enemyStats, itemBtn, attackBtn, defendBtn, spellBtn, mainPlayer);
                            primaryStage.setScene(battleScene);
                            break;
                        case "Exit Game":
                            //TODO save before exit
                            System.exit(0);
                            break;
                    }
                }
            }
        });

        //Sets the description of the selected menu option to the optionDescription box
        playerOptions.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                switch(playerOptions.getSelectionModel().getSelectedItem()){
                    case "Shop":
                        optionDescription.clear();
                        optionDescription.appendText("The shop is where you can buy items to help you in battle");
                        break;
                    case "Training":
                        optionDescription.clear();
                        optionDescription.appendText("The training grounds allow you to spend experience to upgrade your gladiator");
                        break;
                    case "Next Battle":
                        optionDescription.clear();
                        optionDescription.appendText("Starts the next battle");
                        break;
                    case "Exit Game":
                        optionDescription.clear();
                        optionDescription.appendText("Saves and Exits the game");
                        break;
                }
            }
        });

        //Takes the player back to the inBetween menu
        shopBackBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            primaryStage.setScene(betweenScene);
            }
        });

        //Takes the player back to the inBetween menu
        trainingBackBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                primaryStage.setScene(betweenScene);
            }
        });

        //End of between testing******************************************************************************************************************

        //Start of shop testing******************************************************************************************************************
        //Generating items for the shop
        ShopLogic shopLogic = new ShopLogic();
        shopLogic.generateItemsWithinRange(mainPlayer, itemsForSale);

        //Making it so that when an item is selected a description of the item is displayed
        shopItems.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                itemDescription.clear();
                itemDescription.appendText(shopLogic.getItemDescription(shopItems.getSelectionModel().getSelectedItem()));
            }
        });

        //When the shop button is clicked the buyItem function is called from the shopLogic class
        shopSelectButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                shopLogic.buyItem(mainPlayer, shopItems.getSelectionModel().getSelectedItem(), itemsForSale);
            }
        });

        //End of shop testing******************************************************************************************************************

        //Start of training testing************************************************************************************************************

        //Describes exactly what each training option does when the user has one selected
        trainingOptions.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                switch(trainingOptions.getSelectionModel().getSelectedItem()){
                    case("Health"):
                        trainingDescription.clear();
                        trainingDescription.appendText("Increase your health by 10 points");
                        break;
                    case("Attack"):
                        trainingDescription.clear();
                        trainingDescription.appendText("Increase your attack by 1");
                        break;
                    case("Defense"):
                        trainingDescription.clear();
                        trainingDescription.appendText("Increase your defense by 1");
                        break;
                    case("Mana"):
                        trainingDescription.clear();
                        trainingDescription.appendText("Increase your mana by 5");
                        break;
                }
            }
        });

        //Checks if the user has enough experience points to select the training option they want and if they do increase their stats accordingly
        //If a user does not have an option selected or does not have enough experience points then a warning message is displayed with relevant information
        trainingSelectButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(trainingOptions.getSelectionModel().getSelectedItem().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Please select an option", "Nothing Selected", JOptionPane.WARNING_MESSAGE);
                }
                else{
                    switch(trainingOptions.getSelectionModel().getSelectedItem()){
                        case("Health"):
                            if(mainPlayer.getPlayerExperience() >= 75){
                                mainPlayer.setMaxPlayerHealth(mainPlayer.getMaxPlayerHealth() + 10);
                                mainPlayer.setPlayerHealth(mainPlayer.getMaxPlayerHealth());
                                mainPlayer.setPlayerExperience(mainPlayer.getPlayerExperience() - 75);
                            }
                            else{
                                JOptionPane.showMessageDialog(null,"You need " + (75 - mainPlayer.getPlayerExperience()) + " experience points", "Not enough Experience", JOptionPane.WARNING_MESSAGE);
                            }
                            break;
                        case("Attack"):
                            if(mainPlayer.getPlayerExperience() >= 50){
                                mainPlayer.setMaxPlayerAttack(mainPlayer.getMaxPlayerAttack() + 1);
                                mainPlayer.setPlayerAttack(mainPlayer.getMaxPlayerAttack());
                                mainPlayer.setPlayerExperience(mainPlayer.getPlayerExperience() - 50);
                            }
                            else{
                                JOptionPane.showMessageDialog(null,"You need " + (50 - mainPlayer.getPlayerExperience()) + " experience points", "Not enough Experience", JOptionPane.WARNING_MESSAGE);
                            }
                            break;
                        case("Defense"):
                            if(mainPlayer.getPlayerExperience() >= 50){
                                mainPlayer.setMaxPlayerDefense(mainPlayer.getMaxPlayerDefense() + 1);
                                mainPlayer.setPlayerDefense(mainPlayer.getMaxPlayerDefense());
                                mainPlayer.setPlayerExperience(mainPlayer.getPlayerExperience() - 50);
                            }
                            else{
                                JOptionPane.showMessageDialog(null,"You need " + (50 - mainPlayer.getPlayerExperience()) + " experience points", "Not enough Experience", JOptionPane.WARNING_MESSAGE);
                            }
                            break;
                        case("Mana"):
                            if(mainPlayer.getPlayerExperience() >= 100){
                                mainPlayer.setMaxPlayerMana(mainPlayer.getMaxPlayerMana() + 5);
                                mainPlayer.setPlayerMana(mainPlayer.getMaxPlayerMana());
                                mainPlayer.setPlayerExperience(mainPlayer.getPlayerExperience() - 100);
                            }
                            else{
                                JOptionPane.showMessageDialog(null,"You need " + (100 - mainPlayer.getPlayerExperience()) + " experience points", "Not enough Experience", JOptionPane.WARNING_MESSAGE);
                            }
                            break;
                    }
                }
            }
        });

        //End of training testing************************************************************************************************************
    }

    public static void main(String[] args){
        launch();
    }
}
