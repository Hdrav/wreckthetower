package gameModelStructure;

import java.util.List;
import java.util.Random;

public class LevelOneStrategy implements EnemyStrategy {


	private UnitToMake unitToMake;
	
	public LevelOneStrategy() {
		this.unitToMake=new UnitToMake();
	}
	
	public UnitToMake getUnitToMake() {
		return this.unitToMake;
	}

	@Override
	public void intenseAttack() {
		// TODO Auto-generated method stub
		
	}
	
	public void shortAttack() {
		this.unitToMake.getTemplateIndexArray().add(0);
		this.unitToMake.getTemplateIndexArray().add(0);
		this.unitToMake.getTemplateIndexArray().add(0);
		this.unitToMake.setCoolDown(8);
	}//35

	@Override
	public void normalAttack() {
		this.unitToMake.getTemplateIndexArray().add(0);
		this.unitToMake.getTemplateIndexArray().add(1);
		this.unitToMake.getTemplateIndexArray().add(0);
		this.unitToMake.setCoolDown(8);
	}//25


	public void shortAttack02() {
		this.unitToMake.getTemplateIndexArray().add(0);
		this.unitToMake.getTemplateIndexArray().add(0);
		this.unitToMake.setCoolDown(22);
	}

	public void singleUnit(int randomUnit) {
		this.unitToMake.getTemplateIndexArray().add(randomUnit);
		this.unitToMake.setCoolDown(16);
	}
	
	@Override
	public void allInAttack() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void allInDefense() {
		// TODO Auto-generated method stub
		
	}
	
	public int getNumberOfUnit() {
		return 2;
	}

	@Override
	public UnitToMake enemyStrategyLoop(Tower enemyTower,int soldiersLost,List<Unit> enemyLineup) {
		Random generator= new Random();
		Random unit= new Random();
		int randomChoiceAttack=generator.nextInt(3);
		int randomUnit= unit.nextInt(2);
		this.unitToMake=new UnitToMake();
		if(soldiersLost<=20) {
			if(randomChoiceAttack==0 || randomChoiceAttack==1) {
				this.shortAttack();
				return this.unitToMake;
			}
			if(randomChoiceAttack==2) {
				this.shortAttack02();
				return this.unitToMake;
			}
		}
		if(soldiersLost>20) {
			
			if(randomChoiceAttack==1) {
				this.shortAttack();
				return this.unitToMake;
			}
			if(randomChoiceAttack==2) {
				this.normalAttack();
				return this.unitToMake;
			}
			if(randomChoiceAttack==3) {
				this.singleUnit(randomUnit);
				return this.unitToMake;
			}
			
		}
		return this.unitToMake;
	}
}
