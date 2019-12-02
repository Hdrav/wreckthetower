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

public class Level2Strategy implements EnemyStrategy {

	private UnitToMake unitToMake;
	private List<UnitTemplate> unitTemplate= new ArrayList<>();
	
	public Level2Strategy() {
		this.unitTemplate.add(new UnitTemplate(WeaponTypes.SHORT_SWORD,ArmorTypes.IRON_MEDIUM_ARMOR));
		this.unitTemplate.add(new UnitTemplate(WeaponTypes.NOTHING,ArmorTypes.IRON_MEDIUM_ARMOR));
		this.unitTemplate.add(new UnitTemplate(WeaponTypes.SHORT_SWORD,ArmorTypes.LEATHER_LIGHT_ARMOR));
		this.unitToMake=new UnitToMake();
	}
	
	public void allInAttack() {
	}


	public void allInDefense() {
	}
	
	public UnitToMake enemyStrategyLoop(Tower enemyTower,int soldiersLost,List<Unit> enemyLineup) {
		Random generator= new Random();
		Random unit= new Random();
		int randomChoiceAttack=generator.nextInt(4);
		int randomUnit= unit.nextInt(3);
		this.unitToMake=new UnitToMake();
		if(enemyTower.getHealth()>250) {
			if(randomChoiceAttack==0 || randomChoiceAttack==1) {
				this.shortAttack();
				return this.unitToMake;
			}
			if(randomChoiceAttack==2) {
				this.shortAttack02();
				return this.unitToMake;
			}
		}
		if(enemyTower.getHealth()<=249) {
			
			if(randomChoiceAttack==1) {
				this.shortAttack();
				return this.unitToMake;
			}
			if(randomChoiceAttack==2) {
				this.normalAttack();
				return this.unitToMake;
			}
			if(randomChoiceAttack==3) {
				this.normalAttack02();
				return this.unitToMake;
			}
			if(randomChoiceAttack==4) {
				this.singleUnit(randomUnit);
				return this.unitToMake;
			}
			
		}
		return this.unitToMake;
	}


	public int getNumberOfUnit() {
		return 2;
	}

	public List<UnitTemplate> getUnitTemplateList() {
		return this.unitTemplate;
	}

	public UnitToMake getUnitToMake() {
		return this.unitToMake;
	}

	public void intenseAttack() {
	}
	

	public void normalAttack() {
		this.unitToMake.getTemplateIndexArray().add(0);
		this.unitToMake.getTemplateIndexArray().add(0);
		this.unitToMake.getTemplateIndexArray().add(2);
		this.unitToMake.setCoolDown(21);
	}

	
	public void normalAttack02() {
		this.unitToMake.getTemplateIndexArray().add(1);
		this.unitToMake.getTemplateIndexArray().add(2);
		this.unitToMake.getTemplateIndexArray().add(1);
		this.unitToMake.setCoolDown(14);
	}
	
	public void shortAttack() {
		this.unitToMake.getTemplateIndexArray().add(2);
		this.unitToMake.getTemplateIndexArray().add(2);
		this.unitToMake.getTemplateIndexArray().add(1);
		this.unitToMake.setCoolDown(24);
	}

	public void shortAttack02() {
		this.unitToMake.getTemplateIndexArray().add(2);
		this.unitToMake.getTemplateIndexArray().add(0);
		this.unitToMake.setCoolDown(20);
	}


	public void singleUnit(int randomUnit) {
		this.unitToMake.getTemplateIndexArray().add(randomUnit);
		this.unitToMake.setCoolDown(12);
	}
}

