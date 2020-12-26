//This class represents the player and all of the attributes associated with them
public class Player {
    private String playerName = "";
    private double playerHealth = 100;
    private double playerAttack = 1;
    private double playerDefense = 1;

    //If a player object is created with no name passed then a default "Player" name is used
    public Player(){
        this.playerName = "Player";
    }

    public Player(String name){
        this.playerName = name;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public double getPlayerHealth() {
        return playerHealth;
    }

    public void setPlayerHealth(double playerHealth) {
        this.playerHealth = playerHealth;
    }

    public double getPlayerAttack() {
        return playerAttack;
    }

    public void setPlayerAttack(double playerAttack) {
        this.playerAttack = playerAttack;
    }

    public double getPlayerDefense() {
        return playerDefense;
    }

    public void setPlayerDefense(double playerDefense) {
        this.playerDefense = playerDefense;
    }
}
