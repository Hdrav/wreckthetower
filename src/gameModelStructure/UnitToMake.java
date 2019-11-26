package gameModelStructure;
import java.util.ArrayList;

public class UnitToMake {

	private ArrayList<Integer> templateIndexArray;
	private double coolDown;
	
	public UnitToMake() {
		this.templateIndexArray=new ArrayList<>();
	}

	public ArrayList<Integer> getTemplateIndexArray() {
		return templateIndexArray;
	}

	public void setTemplateIndexArray(ArrayList<Integer> templateIndexArray) {
		this.templateIndexArray = templateIndexArray;
	}

	public double getCoolDown() {
		return coolDown;
	}
	
	public void addUnit(Player player, int templateIndex) {
		player.getUnitQueue().add(new UnitImpl(player.getUnitTemplateList().get(templateIndex).getWeapon(),
				player.getUnitTemplateList().get(templateIndex).getArmor()));
	}

	public void setCoolDown(double coolDown) {
		this.coolDown = coolDown;
	}
	
	
	
}
