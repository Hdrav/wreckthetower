package model.unit;
import java.util.ArrayList;


public class UnitToMake {
	/*
	 * the list of the index of the unit template of the enemy 
	 * and the cooldown used to add them sequentially
	 * 
	 * */
	private ArrayList<Integer> templateIndexArray;
	private double coolDown;
	/**
	 * constructor
	 */
	public UnitToMake() {
		this.templateIndexArray=new ArrayList<>();
	}
	/**
	 * 
	 * @return templateIndexArray
	 */
	public ArrayList<Integer> getTemplateIndexArray() {
		return templateIndexArray;
	}
	/**
	 * 
	 * @param templateIndexArray to be set
	 */
	public void setTemplateIndexArray(ArrayList<Integer> templateIndexArray) {
		this.templateIndexArray = templateIndexArray;
	}
	/**
	 * 
	 * @return the cooldown of the next list
	 */
	public double getCoolDown() {
		return this.coolDown;
	}

	/**
	 * 
	 * @param coolDown to be set
	 */
	public void setCoolDown(double coolDown) {
		this.coolDown = coolDown;
	}
	
	
	
}
