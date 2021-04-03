/*
TODO 1.Create GUI (DONE), 2.Add game logic (Basic, includes event handling for GUI), 3.Add in more detail (Make if more interesting and fun), 4. Add a save system
*/
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

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
        AuxiliaryLogic auxiliaryLogic = new AuxiliaryLogic();
        auxiliaryLogic.start(messages, playerStats, enemyStats, nextBtn, mainPlayer);
        auxiliaryLogic.setPlayerStatusField(playerStats, mainPlayer);
    }

    public static void main(String[] args){
        launch();
    }
}
