
public class User implements Player {


	private int money;
	
	
	public User (int money) {
		this.money=money;
	
	}
	
	public int getMoney() {
		return this.money;

	}

	@Override
	public void addMoney(int money) {
		this.money+=money;

	}

	@Override
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
