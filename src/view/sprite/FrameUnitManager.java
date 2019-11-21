package view.sprite;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javafx.scene.image.Image;
import view.scenecontroller.SceneControllerImpl;

public class FrameUnitManager extends SceneControllerImpl {
	
	private String armorName;
	private String weaponName;
	private List<Image> bodyMovingFrameList;
	private Optional <List<Image>> armorMovingFrameList;
	private Optional <List<Image>> weaponMovingFrameList;
	
	private List<Image> bodyAttackFrameList;
	private Optional<List<Image>> armorAttackFrameList;
	private Optional<List<Image>> weaponAttackFrameList;
	
	private List<Image> bodyDyingFrameList;
	private Optional<List<Image>> armorDyingFrameList;
	private Optional<List<Image>> weaponDyingFrameList;
	
	private double height;
	private double width;
	private double boundaryHeight;
	private double boundaryWidth;
	private double xOffset;
	private double yOffset;
	
	public FrameUnitManager(String weaponName,String armorName,List<Image> bodyMovingFrameList, List<Image> armorMovingFrameList,
			 				List<Image> weaponMovingFrameList,List<Image> bodyAttackFrameList,
			 				List<Image> armorAttackFrameList,List<Image> weaponAttackFrameList,
			 				List<Image> bodyDyingFrameList,List<Image> armorDyingFrameList,
			 				List<Image> weaponDyingFrameList,double boundaryHeight,double boundaryWidth,
			 				double height,double width, double xOffset,double yOffset) {
		
		
		this.weaponName=weaponName;
		this.armorName=armorName;
		this.bodyMovingFrameList=bodyMovingFrameList;
		this.armorMovingFrameList=Optional.ofNullable(armorMovingFrameList);
		this.weaponMovingFrameList=Optional.ofNullable(weaponMovingFrameList);
		this.bodyAttackFrameList=bodyAttackFrameList;
		this.armorAttackFrameList=Optional.ofNullable(armorAttackFrameList);
		this.weaponAttackFrameList=Optional.ofNullable(weaponAttackFrameList);
		this.bodyDyingFrameList=bodyDyingFrameList;
		this.armorDyingFrameList=Optional.ofNullable(armorDyingFrameList);
		this.weaponDyingFrameList=Optional.ofNullable(weaponDyingFrameList);
		this.height=height;
		this.width=width;
		this.boundaryHeight=boundaryHeight;
		this.boundaryWidth=boundaryWidth;
		this.xOffset=xOffset;
		this.yOffset=yOffset;
	}


	public double getHeight() {
		return height;
	}


	public double getWidth() {
		return width;
	}


	public double getBoundaryHeight() {
		return boundaryHeight;
	}


	public double getBoundaryWidth() {
		return boundaryWidth;
	}


	public double getxOffset() {
		return xOffset;
	}


	public double getyOffset() {
		return yOffset;
	}


	public String getArmorName() {
		return armorName;
	}


	public String getWeaponName() {
		return weaponName;
	}


	public List<Image> getBodyMovingFrameList() {
		return bodyMovingFrameList;
	}


	public Optional<List<Image>> getArmorMovingFrameList() {
		return armorMovingFrameList;
	}


	public Optional<List<Image>> getWeaponMovingFrameList() {
		return weaponMovingFrameList;
	}


	public List<Image> getBodyAttackFrameList() {
		return bodyAttackFrameList;
	}


	public Optional <List<Image>> getArmorAttackFrameList() {
		return armorAttackFrameList;
	}


	public Optional <List<Image>>getWeaponAttackFrameList() {
		return weaponAttackFrameList;
	}


	public List<Image> getBodyDyingFrameList() {
		return bodyDyingFrameList;
	}


	public Optional<List<Image>> getArmorDyingFrameList() {
		return armorDyingFrameList;
	}


	public Optional<List<Image>> getWeaponDyingFrameList() {
		return weaponDyingFrameList;
	}
	

	
}
	
	
	

