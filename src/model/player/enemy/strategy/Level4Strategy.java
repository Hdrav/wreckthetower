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

public class Level4Strategy implements EnemyStrategy {
	
	

	private UnitToMake unitToMake;
	private List<UnitTemplate> unitTemplate= new ArrayList<>();
	
	public Level4Strategy(){
		this.unitTemplate.add(new UnitTemplate(WeaponTypes.NOTHING,ArmorTypes.GOLDEN_HEAVY_ARMOR));
		this.unitTemplate.add(new UnitTemplate(WeaponTypes.SHORT_SWORD,ArmorTypes.GOLDEN_HEAVY_ARMOR));
		this.unitTemplate.add(new UnitTemplate(WeaponTypes.SHORT_BOW,ArmorTypes.LEATHER_LIGHT_ARMOR));
		this.unitTemplate.add(new UnitTemplate(WeaponTypes.SHORT_SWORD,ArmorTypes.LEATHER_LIGHT_ARMOR));
		this.unitTemplate.add(new UnitTemplate(WeaponTypes.NOTHING,ArmorTypes.NOTHING));
		this.unitToMake=new UnitToMake();
	}

	
	public void allInAttack() {
	

	}

	public void allInDefense() {


	}

	@Override
	public UnitToMake enemyStrategyLoop(Tower enemyTower, int soldiersLost, List<Unit> enemyLineup) {
		Random generator= new Random();
		Random unit= new Random();
		int randomChoiceAttack=generator.nextInt(6);
		this.unitToMake=new UnitToMake();
		if(enemyTower.getHealth()>=370) {
			if(randomChoiceAttack<=2 ) {
				this.normalAttack();
				return this.unitToMake;
			}
			if(randomChoiceAttack>2) {
				this.normalAttack02();
				return this.unitToMake;
			}
		}
		if(enemyTower.getHealth()<370) {
			if(randomChoiceAttack==1) {
				this.normalAttack();
				return this.unitToMake;
			}
			if(randomChoiceAttack==2) {
				this.normalAttack03();
				return this.unitToMake;
				
			}
			
			if(randomChoiceAttack==3) {
				this.normalAttack02();
				return this.unitToMake;
				
			}
			if(randomChoiceAttack==4) {
				this.intenseAttack();
				return this.unitToMake;
			}
			
			if(randomChoiceAttack==5) {
				this.goldenSpear();
				return this.unitToMake;
			}
			
			
		}
		return this.unitToMake;
	}
	
	
	@Override
	public List<UnitTemplate> getUnitTemplateList() {
		return this.unitTemplate;
	}
	
	public void goldenSpear() {
		this.unitToMake.getTemplateIndexArray().add(1);
		this.unitToMake.getTemplateIndexArray().add(1);
		this.unitToMake.setCoolDown(26);
	}
	
	
	@Override
	public void intenseAttack() {
		this.unitToMake.getTemplateIndexArray().add(1);
		this.unitToMake.getTemplateIndexArray().add(3);
		this.unitToMake.getTemplateIndexArray().add(2);
		this.unitToMake.getTemplateIndexArray().add(4);
		this.unitToMake.setCoolDown(22);

	}

	@Override
	public void normalAttack() {
		this.unitToMake.getTemplateIndexArray().add(3);
		this.unitToMake.getTemplateIndexArray().add(0);
		this.unitToMake.setCoolDown(24);
	}

	public void normalAttack02() {
		
		this.unitToMake.getTemplateIndexArray().add(3);
		this.unitToMake.getTemplateIndexArray().add(2);
		this.unitToMake.setCoolDown(21);
	}

	public void normalAttack03() {
		this.unitToMake.getTemplateIndexArray().add(1);
		this.unitToMake.getTemplateIndexArray().add(2);
		this.unitToMake.setCoolDown(13);
	}

}
