package gameModelStructure;

import java.util.ArrayList;
import java.util.List;

import view.utilities.ArmorTypes;
import view.utilities.WeaponTypes;

public class User implements Player {

	private  int unitNumber;
	private int money;
	private final Tower tower;
	private List<UnitTemplate> unitTemplateList=null;
	private List<Unit> unitQueue=new ArrayList<>();
	
	public User (int money, Tower tower,int unitNumber) {
		this.money=money;
		this.tower=tower;
		this.unitNumber=unitNumber;
		this.unitTemplateList=new ArrayList <UnitTemplate>();
		for(int i=0; i<unitNumber; i++)
			this.unitTemplateList.add(new UnitTemplate(WeaponTypes.NOTHING,ArmorTypes.NOTHING));
		
	}
	
	public void setUnitNumber(int unitNumber) {
		this.unitNumber=unitNumber;
	}
	
	public int getNumberOfUnit() {
		return unitNumber;
	}
	
	public int getMoney() {
		return this.money;

	}
	
	public Tower getTower() {
		return this.tower;
	}

	public void setUnitTemplateList(List<UnitTemplate> unitTemplateList){
		this.unitTemplateList=unitTemplateList;
	}
	
	public List<UnitTemplate> getUnitTemplateList() {
		return this.unitTemplateList;
	}
	
	public List<Unit>getUnitQueue(){
		return this.unitQueue;
	}

	public void addMoney(int money) {
		this.money+=money;

	}

	public void addUnit(int indexButton) {
		System.out.println("index button è:"+indexButton);
		UnitImpl unit= new UnitImpl(this.unitTemplateList.get(indexButton).getWeapon(),
				   this.unitTemplateList.get(indexButton).getArmor());
	//	System.out.println("danno unità: "+unit.getDamage());
		this.unitQueue.add(new UnitImpl(this.unitTemplateList.get(indexButton).getWeapon(),
						   this.unitTemplateList.get(indexButton).getArmor()));
	}		
	
	public void removeUnit(int indexButton) {
		this.unitQueue.remove(indexButton);
	}
	
	public boolean buy(int id) {
		int price= this.unitTemplateList.get(id).getCost();
		if(this.money<price) { 
			System.out.println("monete insufficienti");
			return false;
		}
		else {
			this.money-=price;
			this.addUnit(id);
			return true;
		}
	}


}
