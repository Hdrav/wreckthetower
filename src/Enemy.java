
public class Enemy extends User {

	private static int STANDARD_AMOUNT=2000;
	private int money;
	private int spent;
	private final EnemyStrategy currentStrategy;
	private final Tower enemyTower;
	private final Lineup enemyLineup;
	
	public Enemy (int money) {
		super(money);
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
			currentStrategy.executeStrategy(this.enemyLineup);
			
		}
	}
}
