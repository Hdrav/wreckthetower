

public abstract class AbstractStrategy implements EnemyStrategy {

		private static int LIFE_PERCENTAGE;
		private static int DEAD_PERCENTAGE;
		
		@Override
		public void callForBackup(int towerLife,Lineup enemyLineup) {
			
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
		
		public abstract void defenseFormation();
		
		public abstract void attackFormation();
		
		public void executeStrategy(int towerLife, Lineup enemyLineup,
								    int soldiersLost, Tower enemyTower) {
			
			this.callForBackup(towerLife, enemyLineup);
			this.buyCatapult(soldiersLost, enemyTower);
			if(enemyTower.isArmed==true) {
				this.attackFormation();
			}	
			else 
				defenseFormation();
		}
			
		
	}


