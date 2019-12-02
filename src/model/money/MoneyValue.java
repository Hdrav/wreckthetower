package model.money;

public class MoneyValue {
	/**
	 * wrapper class for the primitive int of the field money
	 * 
	 * */
	private int money;
	
	public MoneyValue(int value) {
		this.money=value;
	}
	
	public void addMoney(int value) {
		this.money+=value;
	}
	
	public int getMoney() {
		return this.money;
	}
}
