package model.utilities.test;
import org.junit.Before;
import org.junit.Test;

import model.utilities.WeaponTypes;


public class WeaponTest {
	
	WeaponTypes weapon;
	String trueName;
	String falseName;
	int trueWeaponCost=10;
	
	@Before
	public void setUp() {
		this.weapon=WeaponTypes.SHORT_SWORD;
		this.trueName="short sword";
		this.falseName="golden spear";
	}
	
	
	@Test
	public void testName() {
		org.junit.Assert.assertFalse(WeaponTypes.getName(1).equals(this.weapon.toString()));
		org.junit.Assert.assertTrue(WeaponTypes.getName(0).equals(this.weapon.toString()));
		org.junit.Assert.assertFalse(this.weapon.toString().equals(this.falseName));
		org.junit.Assert.assertTrue(this.weapon.toString().equals(this.trueName));
	}
	
	@Test
	public void testCost() {
		org.junit.Assert.assertTrue(this.weapon.getWeaponCost()==this.trueWeaponCost);
	}
	
	

}
