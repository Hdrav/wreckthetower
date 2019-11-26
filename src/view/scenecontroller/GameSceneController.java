package view.scenecontroller;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import view.sprite.SpriteImpl;

public class GameSceneController extends SceneControllerImpl{
	
	@FXML
	private AnchorPane mainPane;
	@FXML
	private AnchorPane scrolledPane;
	@FXML
	private ScrollPane scrollPane;
	@FXML
	private TabPane userPane;
	@FXML
	private Tab unitTab;
	
	private AnchorPane unitPane;
	
	private HBox unitHBox;
	
	private GraphicsContext graphicsGameCanvas;
	
	private Canvas gameCanvas;
	
	private List<Button> unitCreationButtonList;
	
	private Image groundTile;
	
	private Image userTowerImage;
	
	private Image enemyTowerImage;
	
	private Image background;
	
	private GameSpriteAnimation gameSpriteAnimation;
	
	public void initialize() {
		
		this.unitCreationButtonList=new ArrayList<Button>();
		this.userPane.setBackground(new Background(new BackgroundImage(new Image ("/map_of_the_game/user_pane_background.png"),null,null,null,null)));
		this.initializeUserPane();
		this.gameCanvas= new Canvas();
		this.graphicsGameCanvas=this.gameCanvas.getGraphicsContext2D();
		
		this.background=new Image("/map_of_the_game/backgroundMainPane.png");
		this.groundTile=new Image("/map_of_the_game/terreno.png");
		this.userTowerImage= new Image("/map_of_the_game/tower_player.png");
		this.enemyTowerImage=new Image("/map_of_the_game/enemy_tower01.png");
	
		this.scrolledPane.getChildren().add(this.gameCanvas);
		this.gameCanvas.setHeight(800);
		this.gameCanvas.setWidth(1600);
		this.gameCanvas.setPickOnBounds(true);
		AnchorPane.setBottomAnchor(this.gameCanvas, 0.0);
		AnchorPane.setLeftAnchor(this.gameCanvas, 0.0);
		AnchorPane.setRightAnchor(this.gameCanvas, 0.0);
		this.renderBackground();
		this.gameSpriteAnimation=new GameSpriteAnimation();
		this.scrolledPane.getChildren().add(this.gameSpriteAnimation.getUnitDisplayPane());
	}
	
	private void initializeUserPane() {
		this.unitPane=new AnchorPane();
		this.unitPane.setPrefWidth(this.userPane.getWidth());
		this.unitPane.setPrefHeight(this.userPane.getHeight());
		this.unitHBox=new HBox();
		this.unitHBox.setPrefSize(this.userPane.getWidth(), this.userPane.getHeight());
		/*
		 * follows the algorithm of spacing in the hbox that gives the spacing following
		 * this principles:
		 * 1) the elements are many as the number of different unit the user can make
		 * 2) in the base case of 3 units the spacing is based on the width of the userPane/8
		 * 3) otherwise the spacing gets smaller of one unit of ratio for every other unit
		 * 
		 * */
		this.unitHBox.setSpacing(this.userPane.getPrefWidth()/(8+(3-this.getController().getUser().getNumberOfUnit())));
		this.initializeUnitButton();
		this.unitHBox.getChildren().addAll(unitCreationButtonList);
		
		this.unitPane.getChildren().add(this.unitHBox);
		unitTab.setContent(this.unitPane);
		
		
		
		
	}
	
	
	private void initializeUnitButton() {
		//System.out.println(""+this.getController().getUser().getNumberOfUnit());
		for(int i=0; i<this.getController().getUser().getNumberOfUnit(); i++) {
			Image weaponImage= new Image("/weapon_icon/"+this.getController().getUser()
														   .getUnitTemplateList().get(i).getWeapon().toString()+" icon.png");
			Image armorImage= new Image("/armors_icon/"+this.getController().getUser()
														   .getUnitTemplateList().get(i).getArmor().toString()+".png");
			Image basicImage= new Image("/armors_icon/basic_icon.png");
			Image basicFrame= new Image("/armors_icon/basic_frame.png");
			
			Canvas canvas= new Canvas(100,100);
			GraphicsContext grContext= canvas.getGraphicsContext2D();
			grContext.drawImage(basicImage, 0, 0,canvas.getWidth(),canvas.getHeight());
			grContext.drawImage(armorImage, 0, 0,canvas.getWidth(),canvas.getHeight());
			grContext.drawImage(weaponImage, 0, 0,canvas.getWidth(),canvas.getHeight());
			grContext.drawImage(basicFrame, 0, 0,canvas.getWidth(),canvas.getHeight());	
			Button creationButton= new Button("",canvas);		
			this.unitCreationButtonList.add(creationButton);
			int index=i;
			
			creationButton.setOnAction((e->{this.buttonCreateUnit(index);}));
			
			
		}
		
	}
	
	public void buttonCreateUnit(int index) {
		
		if(this.getController().canUserBuy(index)) {
		this.gameSpriteAnimation.createUnitUser(index);
		this.getController().getUser().buy(index);
		}
	}
	
	

	
	public void renderBackground() {
		this.graphicsGameCanvas.drawImage(this.background, 0, 0);
		this.graphicsGameCanvas.drawImage(this.groundTile, 0, 625);
		this.graphicsGameCanvas.drawImage(this.groundTile, 1000, 625);
	}
	
	
	

	public AnchorPane getMainPane() {
		return this.mainPane;
	}

	public AnchorPane getScrolledPane() {
		return scrolledPane;
	}

	public ScrollPane getScrollPane() {
		return this.scrollPane;
	}

	public ImageView getFirstGroundTile() {
		return firstGroundTile;
	}
	
	public void setScrollPane(ScrollPane pane) {
		this.scrollPane=pane;
	}

	public void setFirstGroundTile(ImageView firstGroundTile) {
		this.firstGroundTile = firstGroundTile;
	}

	public ImageView getSecondGroundTile() {
		return secondGroundTile;
	}

	public void setSecondGroundTile(ImageView secondGroundTile) {
		this.secondGroundTile = secondGroundTile;
	}

	public TabPane getUserPane() {
		return userPane;
	}

	public void setUserPane(TabPane userPane) {
		this.userPane = userPane;
	}
	
}
