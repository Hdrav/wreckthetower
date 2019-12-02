package model.player;

import java.util.List;

import model.money.MoneyValue;
import model.tower.Tower;
import model.unit.Unit;
import model.unit.UnitTemplate;

public interface Player {
/**
 * 
 * @param money
 */
public void addMoney(int money);
	/**
	 * @param the index of the template unit to add to the list of unit
	 * */
	public void addUnit(int index);
	/**
	 * @param the index of the template unit of the unit to buy
	 * @return true if succeed false otherwise
	 * */
	public boolean buy(int id);
	/**
	 * player class that maintains all
	 * the model data required 
	 * */
	public MoneyValue getMoney();
	/**
	 *@return the number of template units the player can have
	*/
	public int getNumberOfUnit();
	/**
	 *@return the tower of the player
	 * */
	public Tower getTower();
	/**
	 * @return return the list of unit the player have
	 * */
	public List<Unit> getUnitQueue();
	/**
	 * @return the list of unit template
	 */
	public List<UnitTemplate> getUnitTemplateList();
	
	


	


	
}
