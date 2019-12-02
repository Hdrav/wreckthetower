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

public class Level3Strategy implements EnemyStrategy {
	
	

	private UnitToMake unitToMake;
	private List<UnitTemplate> unitTemplate= new ArrayList<>();
	
	public Level3Strategy() {
		this.unitTemplate.add(new UnitTemplate(WeaponTypes.GOLDEN_SPEAR,ArmorTypes.GOLDEN_HEAVY_ARMOR));
		this.unitTemplate.add(new UnitTemplate(WeaponTypes.SHORT_SWORD,ArmorTypes.NOTHING));
		this.unitTemplate.add(new UnitTemplate(WeaponTypes.GOLDEN_SPEAR,ArmorTypes.NOTHING));
		this.unitTemplate.add(new UnitTemplate(WeaponTypes.SHORT_SWORD,ArmorTypes.IRON_MEDIUM_ARMOR));
		this.unitTemplate.add(new UnitTemplate(WeaponTypes.SHORT_BOW,ArmorTypes.NOTHING));
		this.unitToMake=new UnitToMake();
	}

	
	public void allInAttack() {
	

	}

	public void allInDefense() {


	}

	public UnitToMake enemyStrategyLoop(Tower enemyTower, int soldiersLost, List<Unit> enemyLineup) {
		Random generator= new Random();
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
	
	

	public List<UnitTemplate> getUnitTemplateList() {
		return this.unitTemplate;
	}
	
	public void goldenSpear() {
		this.unitToMake.getTemplateIndexArray().add(0);
		this.unitToMake.getTemplateIndexArray().add(1);
		this.unitToMake.setCoolDown(20);
	}
	
	

	public void intenseAttack() {
		this.unitToMake.getTemplateIndexArray().add(2);
		this.unitToMake.getTemplateIndexArray().add(1);
		this.unitToMake.getTemplateIndexArray().add(4);
		this.unitToMake.getTemplateIndexArray().add(4);
		this.unitToMake.setCoolDown(31);

	}


	public void normalAttack() {
		this.unitToMake.getTemplateIndexArray().add(1);
		this.unitToMake.getTemplateIndexArray().add(3);
		this.unitToMake.setCoolDown(24);
	}

	public void normalAttack02() {
		
		this.unitToMake.getTemplateIndexArray().add(3);
		this.unitToMake.getTemplateIndexArray().add(4);
		this.unitToMake.setCoolDown(23);
	}

	public void normalAttack03() {
		this.unitToMake.getTemplateIndexArray().add(1);
		this.unitToMake.getTemplateIndexArray().add(2);
		this.unitToMake.setCoolDown(25);
	}

}
