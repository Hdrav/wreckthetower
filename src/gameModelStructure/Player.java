package gameModelStructure;

import java.util.List;

public interface Player {

	public int getMoney();
	public void addMoney(int money);
	public void addUnit(int index);
	public boolean buy(int id);
	
	
	public int getNumberOfUnit();
	public List<Unit> getUnitQueue();
	
	public Tower getTower();

	
	public List<UnitTemplate> getUnitTemplateList();
	
	


	


	
}
