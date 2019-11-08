package gameModelStructure;
import java.util.Random;

public abstract class AbstractStrategy implements EnemyStrategy {

		private static int LIFE_PERCENTAGE;
		private static int DEAD_PERCENTAGE;
		
		@Override
		public void callForBackup(int towerLife,LineUp enemyLineup) {
			
			if((towerLife<=LIFE_PERCENTAGE)&&(enemyLineup.areUnitLocked)) {
				enemyLineup.setUnlocked();
			}
		}

		@Override
		public void buyCatapult(int soldiersLost,Tower enemyTower) {
			
			if((DEAD_PERCENTAGE<=soldiersLost)&&(enemyTower.isArmed==false)) {
				enemyTower.buyWeapon();
			}
		}
		
		public void normalFormation(LineUp enemyLineup) {
			
			int unitId;
			Random randomGenerator = new Random();
			unitId=randomGenerator.nextInt(enemyLineup.getUnlocked);
			enemyLineup.placeUnit();
		
		}
		
		public abstract void defenseFormation(LineUp enemyLineup);
		
		public abstract void attackFormation(LineUp enemyLineup);
		
		
		public void executeStrategy( LineUp enemyLineup,
								    int soldiersLost, Tower enemyTower) {
		
			this.callForBackup(enemyTower.getLife(), enemyLineup);
			this.buyCatapult(soldiersLost, enemyTower);
			this.normalFormation(enemyLineup);
				attackFormation(enemyLineup);
				defenseFormation(enemyLineup);
			
		}
			
		
	}


