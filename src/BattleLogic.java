import Enemies.Enemy;

//This class handles logic during battles
public class BattleLogic {

    private Enemy currentEnemy = null;

    public Enemy getCurrentEnemy() {
        return currentEnemy;
    }

    public void setCurrentEnemy(Enemy currentEnemy) {
        this.currentEnemy = currentEnemy;
    }


}
