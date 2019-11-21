package gameModelStructure;

import java.util.List;

public interface Player {

	public int getMoney();
	public void addMoney(int money);
	public void addUnit(int index);
	public boolean buy(int id, int price);
	
	
	public int getNumberOfUnit();
	
	
	public Tower getTower();
	

	public LineUp getLineUp();

	
	
	public List<UnitTemplate> getUnitTemplateList();
	
	


	


	
}
