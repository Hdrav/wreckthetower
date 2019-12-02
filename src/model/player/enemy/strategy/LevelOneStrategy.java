package model.player.enemy.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.tower.Tower;
import model.unit.Unit;
import model.unit.UnitTemplate;
import model.unit.UnitToMake;
import model.utilities.ArmorTypes;
import model.utilities.WeaponTypes;

public class LevelOneStrategy implements EnemyStrategy {


	private UnitToMake unitToMake;
	private List<UnitTemplate> unitTemplate= new ArrayList<>();
	
	public LevelOneStrategy() {
		this.unitTemplate.add(new UnitTemplate(WeaponTypes.NOTHING,ArmorTypes.NOTHING));
		this.unitTemplate.add(new UnitTemplate(WeaponTypes.SHORT_SWORD,ArmorTypes.LEATHER_LIGHT_ARMOR));
		this.unitToMake=new UnitToMake();
	}
	
	@Override
	public void allInAttack() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void allInDefense() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public UnitToMake enemyStrategyLoop(Tower enemyTower,int soldiersLost,List<Unit> enemyLineup) {
		Random generator= new Random();
		Random unit= new Random();
		int randomChoiceAttack=generator.nextInt(3);
		int randomUnit= unit.nextInt(2);
		this.unitToMake=new UnitToMake();
		if(enemyTower.getHealth()>=250) {
			if(randomChoiceAttack==0 || randomChoiceAttack==1) {
				this.shortAttack();
				return this.unitToMake;
			}
			if(randomChoiceAttack==2) {
				this.shortAttack02();
				return this.unitToMake;
			}
		}
		if(enemyTower.getHealth()<250) {
			if(randomChoiceAttack==1) {
				this.shortAttack02();
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

	public int getNumberOfUnit() {
		return 2;
	}


	@Override
	public List<UnitTemplate> getUnitTemplateList() {
		
		return this.unitTemplate;
	}

	public UnitToMake getUnitToMake() {
		return this.unitToMake;
	}
	
	@Override
	public void intenseAttack() {
		
	}

	@Override
	public void normalAttack() {
		this.unitToMake.getTemplateIndexArray().add(1);
		this.unitToMake.getTemplateIndexArray().add(1);
		this.unitToMake.getTemplateIndexArray().add(0);
		this.unitToMake.setCoolDown(16);
	}//25
	
	public void shortAttack() {
		this.unitToMake.getTemplateIndexArray().add(0);
		this.unitToMake.getTemplateIndexArray().add(0);
		this.unitToMake.getTemplateIndexArray().add(0);
		this.unitToMake.setCoolDown(19);
	}//35

	public void shortAttack02() {
		this.unitToMake.getTemplateIndexArray().add(0);
		this.unitToMake.getTemplateIndexArray().add(0);
		this.unitToMake.setCoolDown(13);
	}

	public void singleUnit(int randomUnit) {
		this.unitToMake.getTemplateIndexArray().add(randomUnit);
		this.unitToMake.setCoolDown(10);
	}
}
