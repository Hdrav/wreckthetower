package view.scenecontroller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import view.scenecontroller.UnitChoiceMenu;
import view.utilities.WeaponTypes;

public class ChooseUnitsController extends SceneControllerImpl {

	@FXML
	private AnchorPane mainPane;
	@FXML
	private HBox unitSettingBox;
	@FXML
	private Button doneButton;
	
	private List<AnchorPane> secondaryPaneList =new ArrayList<>();
	
	private List<UnitChoiceMenu> unitChoiceMenuList= new ArrayList<>();

	
	public void initialize() {

		for(int i=0; i<this.getController().getUser().getNumberOfUnit(); i++) {
			secondaryPaneList.add(this.secondaryPaneInitialize(i));
		}
		this.unitSettingBox.getChildren().addAll(this.secondaryPaneList);
	}
	
	private AnchorPane secondaryPaneInitialize(int unitIndex) {
		
		final AnchorPane pane =new AnchorPane();
		pane.setPrefHeight(285);
		pane.setPrefWidth(284);
		
		final Label unitCostLabel =new Label();
		unitCostLabel.setFont(Font.font("DejaVu Serif Bold",15));
		unitCostLabel.setLayoutX(200);
		unitCostLabel.setLayoutY(6.0);
		unitCostLabel.setPrefHeight(35);
		unitCostLabel.setPrefWidth(84);
		final ImageView unitImageView= new ImageView();
		final ImageView frameImageView= new ImageView();
		SplitMenuButton equipmentMenu= new SplitMenuButton();
		equipmentMenu.setLayoutY(200);
		equipmentMenu.setPrefHeight(35.0);
		equipmentMenu.setPrefWidth(200);
		
		
		final Image unitFrame= new Image("/armors_icon/basic_frame.png");
		final Image unitImage= new Image("/armors_icon/basic_icon.png");
		unitImageView.setImage(unitImage);
		frameImageView.setImage(unitFrame);
		final UnitChoiceMenu unitChoiceBox=new UnitChoiceMenu(unitCostLabel,unitImageView,frameImageView,
														equipmentMenu,pane, unitIndex);
		this.unitChoiceMenuList.add(unitChoiceBox);
	
		
		pane.getChildren().add(unitChoiceBox.getBasicUnitImageView());
		pane.getChildren().add(unitChoiceBox.getBasicFrameImageView());
		pane.getChildren().add(unitChoiceBox.getUnitChoiceCost());
		
		try {
		pane.getChildren().add(unitChoiceBox.getWeaponImageView());
		pane.getChildren().add(unitChoiceBox.getArmorImageView());
		}catch(NullPointerException e) {};
		
		AnchorPane.setTopAnchor(unitChoiceBox.getUnitChoiceCost(),0.0);
		pane.getChildren().add(unitChoiceBox.getEquipmentMenu());
		return pane;
	}

	/*
	 * sets every line of the setting file
	 * 
	 * */
	private void settingFileUnit(String[] weapon,String[] armor) throws IOException {
		try {
		        BufferedReader file = new BufferedReader(new FileReader(new File("").getAbsolutePath()+"/res/setting_files/unit_setting.txt"));
		        StringBuffer inputBuffer = new StringBuffer();
		        String line;
		        String appendLine;
		        int index=0;
		        
		        while ((line = file.readLine()) != null) {
		        		appendLine = weapon[index]+","+armor[index]; // replace the line here
		        		inputBuffer.append(appendLine);
		        		inputBuffer.append('\n');	
		        		index=index+1;
		        }
		        file.close();
		 
		        // write the new string with the replaced line OVER the same file
		        FileOutputStream fileOut = new FileOutputStream(new File("").getAbsolutePath()+"/res/setting_files/unit_setting.txt");
		        fileOut.write(inputBuffer.toString().getBytes());
		        fileOut.close();
		        
		    } catch (Exception e) {
		        System.out.println("Problem reading file.");
		    }
		}
	
	
	/*
	 * when "done" is pressed, gets the weapon's and armor's name selected
	 * and call the private method "settingFileUnit
	 * */
	public void saveUnitSetting() {
		String[] weapon=new String[3];
		String[] armor=new String[3];
		for(int i=0; i<3; i++) {
			
				if(this.unitChoiceMenuList.get(i).getArmorImageName()==null) {
					armor[i]="nothing";
				}
				else armor[i]=this.unitChoiceMenuList.get(i).getArmorImageName().getImageName();
				
				if(this.unitChoiceMenuList.get(i).getWeaponImageName()==null) {
					weapon[i]="nothing";
				}
				else weapon[i]=this.unitChoiceMenuList.get(i).getWeaponImageName().getImageName();
		}
		
		try {
			this.settingFileUnit(weapon,armor);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		final Stage stage = (Stage) this.mainPane.getScene().getWindow();
		stage.close();
	}
	
	
	
}
