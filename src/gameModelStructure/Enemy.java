package gameModelStructure;

import java.util.ArrayList;
import java.util.List;

import view.utilities.ArmorTypes;
import view.utilities.WeaponTypes;

public class Enemy extends User {

	private static int STANDARD_AMOUNT=90;
	private int unitNumber=3;
	private int money;
	private final Tower tower;
	private final LineUp lineup;
	private final List<UnitTemplate> unitTemplateList;
	private int spent;
	private EnemyStrategy currentStrategy;

	public Enemy (int money, Tower tower, LineUp lineup) {
		super(money,tower,lineup);
		this.money=money;
		this.tower=tower;
		this.lineup=lineup;
		this.spent=0;
		this.unitTemplateList=new ArrayList<UnitTemplate>();
		for(int i=0; i<this.unitNumber; i++)
			this.unitTemplateList.add(new UnitTemplate(WeaponTypes.NOTHING,ArmorTypes.NOTHING));
	}
	
	
	public void setBattleStrategy (EnemyStrategy currentStrategy) { 
		this.currentStrategy=currentStrategy;
	}
	
	
	public int getMoney() {
		return super.getMoney();
	}
	
	
	public int getSpent() {
		return this.spent;
	}

	public List<UnitTemplate> getUnitTemplateList() {
		return this.unitTemplateList;
	}
	
	@Override
	public void addMoney(int money) {
		super.addMoney(money);

	}

	
	public void enemyBuy(int id,int price) {
		
		if(this.money<price) {
			this.addMoney(STANDARD_AMOUNT);
		}
		this.money-=price;
	}

	
	public void battleStrategy() {
		
		if(this.currentStrategy!=null) {
			currentStrategy.executeStrategy(this.lineup, this.tower);			
		}
	}
}
