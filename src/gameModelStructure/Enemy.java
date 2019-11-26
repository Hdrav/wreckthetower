package gameModelStructure;


public class Enemy extends User {

	private static int STANDARD_AMOUNT=90;
	//private int money;
	

	//private int spent;
	private EnemyStrategy currentStrategy;
	private int soldierLost;

	public Enemy (int money, Tower tower,int unitNumber) {
		super(money,tower,unitNumber);
	//	this.money=money;
	//	this.spent=0;
//		this.unitTemplateList=new ArrayList<UnitTemplate>();
	/*	for(int i=0; i<this.unitNumber; i++)
			this.unitTemplateList.add(new UnitTemplate(WeaponTypes.NOTHING,ArmorTypes.NOTHING));*/
	}
	
	
	public void setBattleStrategy (EnemyStrategy currentStrategy) { 
		this.currentStrategy=currentStrategy;
	}
	
	
	public UnitToMake battleStrategy() {
		
		if(this.currentStrategy!=null) {
			return currentStrategy.enemyStrategyLoop(this.getTower(),this.soldierLost,this.getUnitQueue());			
		}
		else return null;
	}
}
