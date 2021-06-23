/*
TODO 1.Create GUI (DONE), 2.Add game logic (Basic, includes event handling for GUI), 3.Add in more detail (Make if more interesting and fun), 4. Add a save system
*/
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import Enemies.*;

import java.util.HashMap;

public class Main extends Application {

    public void start(Stage primaryStage){
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
        Button nextBtn = new Button("Next");
        Button attackBtn = new Button("Attack");
        Button defendBtn = new Button("Defend");
        Button fleeBtn = new Button("Flee");
        Button spellBtn = new Button("Use Spell");
        Button backButton = new Button ("Back");

        //Adding buttons to the control pane and setting them along the bottom of the application
        battleControlButtonsPane.getChildren().addAll(nextBtn, attackBtn, defendBtn, fleeBtn, spellBtn);
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

        //Height binding the stat and message fields to the scene height
        playerStats.setPrefHeight(battleScene.getHeight());
        enemyStats.setPrefHeight(battleScene.getHeight());
        messages.setPrefHeight(battleScene.getHeight());

        //Width binding the stat fields to an eighth the width of the scene and the messages field to half
        playerStats.setPrefWidth(battleScene.getWidth() / 8);
        enemyStats.setPrefWidth(battleScene.getWidth() / 8);
        messages.setPrefWidth(battleScene.getWidth() / 2);

        //The following code creates an "between" scene that is used when the player is between battles
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

        //Creating a text area for the a description of their selection in the playerOptions listview
        TextArea optionDescriptions = new TextArea();
        optionDescriptions.setEditable(false);

        //Setting the text area's to the betweenMenuOrgPane
        betweenMenuOrgPane.setCenter(optionDescriptions);
        betweenMenuOrgPane.setRight(playerOptions);

        //Height binding the options and optionDescriptions to reasonable sizes
        playerOptions.setPrefHeight(betweenScene.getHeight()/5);

        //Creating a select button
        Button menuSelectButton = new Button("Select");

        //Setting up a simple HBox to house the select button
        HBox selectButtonBox = new HBox();
        selectButtonBox.getChildren().addAll(menuSelectButton, backButton);
        selectButtonBox.setAlignment(Pos.CENTER);
        selectButtonBox.setPadding(basicInsets);
        selectButtonBox.setSpacing(10);
        betweenMenuOrgPane.setBottom(selectButtonBox);

        //Creating a menu for the shop interface
        BorderPane shopOrgPane = new BorderPane();
        shopOrgPane.setPadding(basicInsets);

        //Setting up an HBox to host a select button at the bottom of the pane
        HBox shopMenuButtonBox = new HBox();
        Button shopSelectButton = new Button("Select");
        shopMenuButtonBox.getChildren().addAll(shopSelectButton, backButton);
        shopMenuButtonBox.setAlignment(Pos.CENTER);
        shopMenuButtonBox.setPadding(basicInsets);
        shopMenuButtonBox.setSpacing(10);
        shopOrgPane.setBottom(shopMenuButtonBox);

        //Setting up a list view that will hold items the shop is selling
        ObservableList<String> itemsForSale = FXCollections.observableArrayList("PlaceHolder");
        ListView<String> shopItems = new ListView(itemsForSale);
        shopItems.setEditable(false);
        shopItems.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        shopOrgPane.setLeft(shopItems);

        //Setting up a text box that will hold a description of items the shop is selling that they user has selected
        TextArea itemDescription = new TextArea();
        itemDescription.setEditable(false);
        shopOrgPane.setCenter(itemDescription);

        //Setting up a new scene for the shop
        Scene shopScene = new Scene(shopOrgPane, 1000, 500);

        //Creating one final menu for training
        BorderPane trainingOrgPane = new BorderPane();
        trainingOrgPane.setPadding(basicInsets);

        //Setting up an HBox to host a select button at the bottom of the pane
        HBox trainingMenuButtonBox = new HBox();
        Button trainingSelectButton = new Button("Select");
        trainingMenuButtonBox.getChildren().addAll(shopSelectButton, backButton);
        trainingMenuButtonBox.setAlignment(Pos.CENTER);
        trainingMenuButtonBox.setPadding(basicInsets);
        trainingMenuButtonBox.setSpacing(10);
        trainingOrgPane.setBottom(trainingSelectButton);

        //Setting up a list view that will hold items the shop is selling
        ObservableList<String> trainingOptionsList = FXCollections.observableArrayList("PlaceHolder");
        ListView<String> trainingOptions = new ListView(itemsForSale);
        trainingOptions.setEditable(false);
        trainingOptions.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        trainingOrgPane.setLeft(trainingOptions);

        //Setting up a text box that will hold a description of items the shop is selling that they user has selected
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

        //**************************
        //* Game driver starts here *
        //**************************
        //Starting off by setting up player and logic classes
        Player mainPlayer = new Player();
        BattleLogic battleLogic = new BattleLogic();
        AuxiliaryLogic auxiliaryLogic = new AuxiliaryLogic();
        auxiliaryLogic.start(messages, playerStats, enemyStats, nextBtn, mainPlayer);
        battleLogic.setPlayerStatusField(playerStats, mainPlayer);
    }

    public static void main(String[] args){
        launch();
    }
}
