package Project;
import Project.Items.Item;
import java.util.ArrayList;
import java.util.List;

//This class represents the player and all of the attributes associated with them
public class Player {

    private String playerName = "";
    private int playerLevel = 1;
    private double playerHealth = 100;
    private double playerAttack = 1;
    private double playerDefense = 1;
    private double playerMana = 10;
    private double maxPlayerHealth = 100;
    private double maxPlayerAttack = 1;
    private double maxPlayerDefense = 1;
    private double maxPlayerMana = 10;
    private List<Item> inventory = new ArrayList<>(null);

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

    public int getPlayerLevel() {
        return playerLevel;
    }

    public void setPlayerLevel(int playerLevel) {
        this.playerLevel = playerLevel;
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

    public double getPlayerMana() {
        return playerMana;
    }

    public void setPlayerMana(double playerMana) {
        this.playerMana = playerMana;
    }

    public double getMaxPlayerHealth() {
        return maxPlayerHealth;
    }

    public void setMaxPlayerHealth(double maxPlayerHealth) {
        this.maxPlayerHealth = maxPlayerHealth;
    }

    public double getMaxPlayerAttack() {
        return maxPlayerAttack;
    }

    public void setMaxPlayerAttack(double maxPlayerAttack) {
        this.maxPlayerAttack = maxPlayerAttack;
    }

    public double getMaxPlayerDefense() {
        return maxPlayerDefense;
    }

    public void setMaxPlayerDefense(double maxPlayerDefense) {
        this.maxPlayerDefense = maxPlayerDefense;
    }

    public double getMaxPlayerMana() {
        return maxPlayerMana;
    }

    public void setMaxPlayerMana(double maxPlayerMana) {
        this.maxPlayerMana = maxPlayerMana;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public void setInventory(List<Item> inventory) {
        this.inventory = inventory;
    }

    public void addItemToInventory(Item item){
        this.inventory.add(item);
    }

    public void removeItemFromInventory(Item item){

    }
}
