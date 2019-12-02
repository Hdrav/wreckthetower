package view.scenecontroller;





import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import model.utilities.ArmorTypes;
import model.utilities.WeaponTypes;

import java.util.ArrayList;
import java.util.List;

import view.ImageName;

public class UnitChoiceMenu extends SceneControllerImpl  {
	
	
	private Label unitChoiceCost;
	private ImageView basicUnitImageView;
	private ImageView basicFrameImageView;
	private SplitMenuButton equipmentMenu;
	private Menu weaponSelectMenu=new Menu();
	private Menu armorSelectMenu= new Menu();
	private ImageView weaponImageView=null;
	private ImageView armorImageView=null;
	private ImageName weaponImage=null;
	private ImageName armorImage=null;
	private int unitIndex;
	
	public UnitChoiceMenu(Label unitChoiceCost,ImageView basicUnitImageView, ImageView basicFrameImageView,
					 	 SplitMenuButton equipmentMenu, AnchorPane pane, int unitIndex) {
		
		
		
		this.unitChoiceCost=unitChoiceCost;
		this.unitChoiceCost.setText("Cost:10");
		unitChoiceCost.setFont(Font.font("DejaVu Serif Bold",15));
		this.basicUnitImageView=basicUnitImageView;
		this.basicFrameImageView=basicFrameImageView;
		this.equipmentMenu=equipmentMenu;
		this.unitIndex=unitIndex;
		this.equipmentMenu.setText("Weapon & Armor");
		this.equipmentMenu.setFont(Font.font("DejaVu Serif Bold",15));
		
		
		this.weaponSelectMenu.setText("Weapon Selection");
		this.armorSelectMenu.setText("Armor Selection");
				
		final  List<MenuItem> weaponList=new ArrayList<>();
		final List<MenuItem> armorList= new ArrayList<>();
	
		
		for(int i=0; i<WeaponTypes.getNumberOfWeapon(); i++)
			weaponList.add((new MenuItem(WeaponTypes.getName(i))));
		
		for(int i=0; i<ArmorTypes.getNumberOfArmor(); i++)
			armorList.add((new MenuItem(ArmorTypes.getName(i))));
		
		this.weaponSelectMenu.getItems().addAll(weaponList);
		this.armorSelectMenu.getItems().addAll(armorList);
		this.equipmentMenu.getItems().addAll(this.weaponSelectMenu,this.armorSelectMenu);
		
		for(int i=0; i<WeaponTypes.getNumberOfWeapon(); i++) {
			MenuItem item= weaponList.get(i);
			
			weaponList.get(i).setOnAction(e->{this.drawWeapon(item.getText(),pane,this.unitIndex);});

		}
		
		for(int i=0; i<ArmorTypes.getNumberOfArmor(); i++) {
			MenuItem item= armorList.get(i);
			
			armorList.get(i).setOnAction(e->{this.drawArmor(item.getText(),pane,this.unitIndex);});

		}
		
		//weaponList.forEach(setOnAction(e->{ this.drawWeapon(e.getText()); }));
		//setOnAction(e ->  {
		    //prendi in ingresso la stringa dell'elemento del menu premuta
			//restituiscila al ChooseUnitsController che ha il campo "controller= new Controller.getLog();"
			//che eredita dalla classe SceneControllerImpl. Quel campo è l'istanza unica di Controller, a cui poi
			// possiamo restituire gli indici o la stringa (deciderò),e finalmente potrà settare nel model del User
			//l'arma e/o l'armatura selezionata in questo menù
		//});

	}


	public UnitChoiceMenu(Label unitChoiceCost,ImageView basicUnitImageView, ImageView basicFrameImageView) {


		this.unitChoiceCost=unitChoiceCost;
		this.unitChoiceCost.setText("Cost:100");
		unitChoiceCost.setFont(Font.font("DejaVu Serif Bold",15));
		this.basicUnitImageView=basicUnitImageView;
		this.basicFrameImageView=basicFrameImageView;
		this.equipmentMenu=null;
	}


	public Label getUnitChoiceCost() {
		return this.unitChoiceCost;
	}

	public void setUnitChoiceCost(Label unitChoiceCost) {
		this.unitChoiceCost = unitChoiceCost;
	}

	public ImageView getBasicUnitImageView() {
		return this.basicUnitImageView;
	}

	public void setBasicUnitImageView(ImageView basicUnitImageView) {
		this.basicUnitImageView = basicUnitImageView;
	}

	public ImageView getBasicFrameImageView() {
		return this.basicFrameImageView;
	}
	
	public ImageView getWeaponImageView() {
		return this.weaponImageView;
	}
	
	public ImageName getWeaponImageName() {
		return this.weaponImage;
	}
	
	public ImageName getArmorImageName() {
		return this.armorImage;
	}
	
	
	public ImageView getArmorImageView() {
		return this.armorImageView;
	}
	
	public SplitMenuButton getEquipmentMenu() {
		return this.equipmentMenu;
	}
	
	public void setEquipmentMenu(SplitMenuButton equipmentMenu) {
		this.equipmentMenu=equipmentMenu;
	}
	
	@SuppressWarnings("unused")
	private void selectableWeaponsArmors() {
		this.armorSelectMenu.getItems().add(new MenuItem());
	}
	
	private void updateCost(int index) {
		this.unitChoiceCost.setText("Cost:"+this.getController().getUser()
												.getUnitTemplateList().get(index).getCost());
	}
	
	
	
	private void drawArmor(String armorName, Pane drawingPane,int index) {
		
		if(this.armorImageView==null) {
			this.armorImageView=new ImageView();
			drawingPane.getChildren().add(this.armorImageView);
		}
		
		if(armorName=="nothing") {
			this.armorImageView.imageProperty().set(null);
		}
		else {
			this.armorImage= new ImageName("/armors_icon/"+armorName+".png",armorName);
			this.armorImageView.setImage(this.armorImage.getImage());
			if(this.weaponImageView!=null) {
				this.redrawWeapon(drawingPane);
				this.redrawFrame(drawingPane);
			}
		
		}
		/*
		 * setting the unit template
		 * in the model
		 * 
		 * */
		this.getController().getUser().getUnitTemplateList().get(index)
		.setArmor(ArmorTypes.getArmor(armorName));
		//this.unitChoiceCost.setText(""+this.getController().getUser().getUnitTemplateList().get(index).getCost());
		this.updateCost(index);
	}
	
	private void redrawWeapon(Pane drawingPane) {
		this.weaponImageView.imageProperty().set(null);
		this.weaponImageView= new ImageView(this.weaponImage.getImage());
		drawingPane.getChildren().add(this.weaponImageView);
		
	}
	
	
	private void redrawFrame(Pane drawingPane) {
		this.basicFrameImageView=new ImageView();
		this.basicFrameImageView.setImage(new Image("/armors_icon/basic_frame.png"));
		drawingPane.getChildren().add(this.basicFrameImageView);
	}

	
	private void drawWeapon(String weaponName,Pane drawingPane, int index) {
		
		if(this.weaponImageView==null) {
			this.weaponImageView=new ImageView();
			drawingPane.getChildren().add(this.weaponImageView);
		}
		/*
		 * nothing item case
		 * */
		if(weaponName=="nothing") {
			this.weaponImageView.imageProperty().set(null);
		}
		else {
			this.weaponImage= new ImageName("/weapon_icon/"+weaponName+" icon.png",weaponName);
			this.weaponImageView.setImage(this.weaponImage.getImage());
			this.redrawFrame(drawingPane);
		}
		this.getController().getUser().getUnitTemplateList().get(index)
		.setWeapon(WeaponTypes.getWeapon(weaponName));
		this.updateCost(index);
	}
	
}
