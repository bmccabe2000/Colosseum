package Enemies;

//This class is for enemies the player will face and is extended to more specific enemy classes
abstract public class Enemy {

    private String enemyName = "";
    private double enemyLevel = 1;
    private double enemyHealth = 100;
    private double enemyAttack = 1;
    private double enemyDefense = 1;
    private double enemyMana = 10;

    //Default constructor will set the enemy's name to just Enemy if no name is passed when an enemy object is created
    public Enemy(){
        this.enemyName = "Enemy";
    }

    public Enemy(String name){
        this.enemyName = name;
    }

    public String getEnemyName() {
        return enemyName;
    }

    public void setEnemyName(String enemyName) {
        this.enemyName = enemyName;
    }

    public double getEnemyLevel() {
        return enemyLevel;
    }

    public void setEnemyLevel(double enemyLevel) {
        this.enemyLevel = enemyLevel;
    }

    public double getEnemyHealth() {
        return enemyHealth;
    }

    public void setEnemyHealth(double enemyHealth) {
        this.enemyHealth = enemyHealth;
    }

    public double getEnemyAttack() {
        return enemyAttack;
    }

    public void setEnemyAttack(double enemyAttack) {
        this.enemyAttack = enemyAttack;
    }

    public double getEnemyDefense() {
        return enemyDefense;
    }

    public void setEnemyDefense(double enemyDefense) {
        this.enemyDefense = enemyDefense;
    }

    public double getEnemyMana() {
        return enemyMana;
    }

    public void setEnemyMana(double enemyMana) {
        this.enemyMana = enemyMana;
    }

    abstract public void specialAttack();


}
