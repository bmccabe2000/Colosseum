package Project.Enemies;

//A spider enemy who's special is a poison attack that does damage over time
public class Spider extends Enemy {

    public Spider(String name, int level, double health, double attack, double defense, double mana, double maxHealth, double maxAttack, double maxDefense, double maxMana) {
        super(name, level, health, attack, defense, mana, maxHealth, maxAttack, maxDefense, maxMana);
    }

    @Override
    public void specialAttack() {
       //TODO Add in a poison attack or maybe a web attack that prevents a player from attacking for a turn?
    }
}
