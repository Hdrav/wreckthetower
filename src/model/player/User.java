package model.player;

import java.util.ArrayList;
import java.util.List;

import model.money.MoneyValue;
import model.tower.Tower;
import model.unit.Unit;
import model.unit.UnitImpl;
import model.unit.UnitTemplate;
import model.utilities.ArmorTypes;
import model.utilities.WeaponTypes;

public class User implements Player {

	private  int unitNumber;
	private MoneyValue money;
	private final Tower tower;
	private List<UnitTemplate> unitTemplateList=null;
	private List<Unit> unitQueue=new ArrayList<>();
	
	public User (int money, Tower tower,int unitNumber) {
		this.money=new MoneyValue(money);
		this.tower=tower;
		this.unitNumber=unitNumber;
		this.unitTemplateList=new ArrayList <UnitTemplate>();
		for(int i=0; i<unitNumber; i++)
			this.unitTemplateList.add(new UnitTemplate(WeaponTypes.NOTHING,ArmorTypes.NOTHING));
	}
	
	public void addMoney(int money) {
		this.money.addMoney(money);
	}
	
	public void addUnit(int indexButton) {
		UnitImpl unit= new UnitImpl(this.unitTemplateList.get(indexButton).getWeapon(),
				   this.unitTemplateList.get(indexButton).getArmor());
		this.unitQueue.add(unit);
	}
	
	public boolean buy(int id) {
		int price= this.unitTemplateList.get(id).getCost();
		if(this.money.getMoney()<price) { 
			return false;
		}
		else {
			this.money.addMoney(-price);
			this.addUnit(id);
			return true;
		}
	}
	
	public MoneyValue getMoney() {
		return this.money;

	}

	public int getNumberOfUnit() {
		return unitNumber;
	}
	
	public Tower getTower() {
		return this.tower;
	}
	
	public List<Unit>getUnitQueue(){
		return this.unitQueue;
	}

	public List<UnitTemplate> getUnitTemplateList() {
		return this.unitTemplateList;
	}	
	
	public void setUnitNumber(int unitNumber) {
		this.unitNumber=unitNumber;
	}
	
	public void setUnitTemplateList(List<UnitTemplate> unitTemplateList){
		this.unitTemplateList=unitTemplateList;
	}


}
