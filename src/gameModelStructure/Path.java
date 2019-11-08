package gameModelStructure;

public interface Path {

	public void insertUnit(Unit unit);
	public void removeUnit(Unit unit);
	public boolean canUnitAttack(Unit unit);
}