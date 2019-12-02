package model.player.enemy.strategy.test;

import org.junit.Before;
import org.junit.Test;

import model.player.enemy.Enemy;
import model.player.enemy.strategy.LevelOneStrategy;
import model.tower.TowerImpl;
import model.utilities.ArmorTypes;
import model.utilities.WeaponTypes;


public class LevelOneStrategyTest {
	
	private LevelOneStrategy strategy;
	private Enemy enemy;
	
	@Before
	 public void setUp() {
		this.strategy= new LevelOneStrategy();
		this.enemy= new Enemy(0, new TowerImpl(500), 0);
		this.enemy.setBattleStrategy(strategy);
	}
	
	@Test
	public void attackCooldownTest() {
		// if life is over 250 must cooldown <= 19
		org.junit.Assert.assertTrue(this.strategy.enemyStrategyLoop(this.enemy.getTower(),0,this.enemy.getUnitQueue())
		.getCoolDown()<=19);
		// if life is below 250 must be cooldown <= 16
		this.enemy.getTower().reduceHealth(300);
		org.junit.Assert.assertTrue(this.strategy.enemyStrategyLoop(this.enemy.getTower(),0,this.enemy.getUnitQueue())
				.getCoolDown()<=16);
	}
		
}
