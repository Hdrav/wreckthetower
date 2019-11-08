package gameModelStructure;

import java.util.ArrayList;
import java.util.List;

import view.utilities.ArmorTypes;
import view.utilities.WeaponTypes;

public class User implements Player {

	private static int UNIT_NUMBER=3;
	private int money;
	private final Tower tower;
	private final LineUp lineup;
	private List<UnitTemplate> unitTemplateList=null;
	
	public User (int money, Tower tower, LineUp lineup) {
		this.money=money;
		this.lineup=lineup;
		this.tower=tower;
		this.unitTemplateList=new ArrayList <UnitTemplate>();
		for(int i=0; i<User.UNIT_NUMBER; i++)
			this.unitTemplateList.add(new UnitTemplate(WeaponTypes.NOTHING,ArmorTypes.NOTHING));
		
	}
	
	public int getNumberOfUnit() {
		return User.UNIT_NUMBER;
	}
	
	public int getMoney() {
		return this.money;

	}
	
	public Tower getTower() {
		return this.tower;
	}

	public LineUp getLineUp() {
		return this.lineup;
	}
	
	public List<UnitTemplate> getUnitTemplateList() {
		return this.unitTemplateList;
	}
	

	public void addMoney(int money) {
		this.money+=money;

	}

	
	public boolean buy(int id, int price) {
		if(this.money<price) { 
			System.out.println("monete insufficienti");
			return false;
		}
		else {
			this.money-=price;
			return true;
		}
	}


}
