package gameModelStructure;

public class PathImpl implements Path {

	private static int PATH_LENGHT=125;
	private static int ENEMY_NOTFOUND=-1;
	
	private final Tile[] path=new Tile[PATH_LENGHT];
	private boolean isFull=false;
	
	
	public void insertUnit(Unit unit) {
		int bodySize=unit.getBodySize();
		
	}

	public void removeUnit(Unit unit) {
		int bodySize=unit.getBodySize();
		this.path[unit.getFootPosition()]=Tile.FREE_TILE;
		this.path[bodySize]=Tile.FREE_TILE;
		
	}

	public boolean canUnitAttack(Unit unit) {
		int unitMaxReach=unit.getFootPosition()+unit.getBodySize()+unit.getUnitReach();
		return true;
		
	}
	
	public int findNearestEnemy(Unit unit) {
		for(int i=unit.getFootPosition(); i<PATH_LENGHT; i++) {
			if(path[i]==Tile.ENEMY_UNIT)
				return i;
		}
	 return PathImpl.ENEMY_NOTFOUND;
	}
	

}

