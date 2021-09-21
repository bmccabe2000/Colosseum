package Project.Enemies;

//This class is for enemies the player will face and is extended to more specific enemy classes
abstract public class Enemy {

    private String enemyName = "";
    private int enemyLevel = 1;
    private double enemyHealth = 100;
    private double enemyAttack = 1;
    private double enemyDefense = 1;
    private double enemyMana = 10;
    private double maxEnemyHealth = 100;
    private double maxEnemyAttack = 1;
    private double maxEnemyDefense = 1;
    private double maxEnemyMana = 10;

    //Default constructor
    public Enemy(String name, int level, double health, double attack, double defense, double mana, double maxHealth, double maxAttack, double maxDefense, double maxMana){
        this.enemyName = name;
        this.enemyLevel = level;
        this.enemyHealth = health;
        this.enemyAttack = attack;
        this.enemyDefense = defense;
        this.enemyMana = mana;
        this.maxEnemyHealth = maxHealth;
        this.maxEnemyAttack = maxAttack;
        this.maxEnemyDefense = maxDefense;
        this.maxEnemyMana = maxMana;
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

    public void setEnemyLevel(int enemyLevel) {
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

    public double getMaxEnemyHealth() {
        return maxEnemyHealth;
    }

    public void setMaxEnemyHealth(double maxEnemyHealth) {
        this.maxEnemyHealth = maxEnemyHealth;
    }

    public double getMaxEnemyAttack() {
        return maxEnemyAttack;
    }

    public void setMaxEnemyAttack(double maxEnemyAttack) {
        this.maxEnemyAttack = maxEnemyAttack;
    }

    public double getMaxEnemyDefense() {
        return maxEnemyDefense;
    }

    public void setMaxEnemyDefense(double maxEnemyDefense) {
        this.maxEnemyDefense = maxEnemyDefense;
    }

    public double getMaxEnemyMana() {
        return maxEnemyMana;
    }

    public void setMaxEnemyMana(double maxEnemyMana) {
        this.maxEnemyMana = maxEnemyMana;
    }

    //Every enemy has a special attack that uniquely effects the player
    abstract public void specialAttack();


}
