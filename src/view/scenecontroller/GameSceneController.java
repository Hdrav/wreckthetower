package view.scenecontroller;




import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import view.utilities.SceneTypes;


public class GameSceneController extends SceneControllerImpl{
	
	/**
	 * class that represent the control of the main game stage
	 * it has a mainPane of smaller dimension than the scrolledPane
	 * that shows the entire battlefield, whose length can be
	 * changed in futures versions.
	 * The userPane for now has only one tab for the normal units (the
	 * ones built in the choose control menu).
	 */
	
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
	@FXML
	private VBox goldLifeBox;
		
	private AnchorPane unitPane;
	@FXML
	private HBox infoPane;
	
	private HBox unitHBox;

	private GraphicsContext graphicsGameCanvas;
	
	private Canvas gameCanvas;
	
	private List<Button> unitCreationButtonList;
	
	private List<ImageView> unitDelayImageView;
	
	private List<Group> buttonBackgroundGroupList;
	
	private List<Timeline> timerDelayList;
	
	private Image groundTile;
	
	private Image userTowerImage;
	
	private Image enemyTowerImage;
	
	private Image background;
	
	private Image unitDelayImage;
	
	private GameSpriteAnimation gameSpriteAnimation;
	
	private Timeline musicTimeline;
	
	private Text towerUserHealth=new Text();
	
	private Text towerEnemyHealth= new Text();
	
	private Text gold= new Text();
	
	private Timeline updateGoldLife;
	
	private Timeline towerLife;
	
	private Pane youWinLose;


	/**
	 * creates the sprite and the model of a unit,and it represents the unit on the scene calling
	 * the method of gameSpriteAnimation 
	 * @param index of the unit template to create
	 */
	private void buttonCreateUnit(int index) {
		
		if(this.getController().canUserBuy(index)) {
		this.gameSpriteAnimation.createUnitUser(index);
		this.getController().getUser().buy(index);
		this.unitCreationButtonList.get(index).setDisable(true);
		this.unitDelayImageView.get(index).setImage(this.unitDelayImage);
		
		}

	}
	
	/**
	 * stops all the timelines used from the enemy, for animation, and disable buttons
	 */
	private void exit() {
		this.gameSpriteAnimation.getGenerateEnemyUnit().stop();
		this.gameSpriteAnimation.getSpriteUnitEnemyOnScene().forEach(f->f.stopGameAnimation());
		this.gameSpriteAnimation.getSpriteUnitOnScene().forEach(f->f.stopGameAnimation());
		this.towerLife.stop();
		this.getController().stopMusic();
		this.musicTimeline.stop();
		this.unitCreationButtonList.forEach(e->e.setDisable(true));
	}
	
	
	public Image getEnemyTowerImage() {
		return enemyTowerImage;
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

	public TabPane getUserPane() {
		return userPane;
	}

	public Image getUserTowerImage() {
		return userTowerImage;
	}
	
	public void initialize() {
		
		this.unitCreationButtonList=new ArrayList<Button>();
		this.buttonBackgroundGroupList= new ArrayList<>();
		this.unitDelayImageView= new ArrayList<>();
		this.timerDelayList=new ArrayList<>();
		
		this.infoPane.setBackground(new Background(new BackgroundImage(new Image ("/map_of_the_game/user_pane_background.png"),null,null,null,null)));
		this.initializeUserPane();
		this.gameCanvas= new Canvas();
		this.graphicsGameCanvas=this.gameCanvas.getGraphicsContext2D();
		
		this.background=new Image("/map_of_the_game/backgroundMainPane.png");
		this.groundTile=new Image("/map_of_the_game/terreno.png");
		this.userTowerImage= new Image("/map_of_the_game/tower_player.png");
		this.enemyTowerImage=new Image("/map_of_the_game/enemy_tower01.png");
		this.musicTimeline=new Timeline();
		this.musicTimeline.setCycleCount(Animation.INDEFINITE);
	
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
		this.getController().startMusic();
		this.musicTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(120000), (ActionEvent e)-> {
			this.getController().startMusic();
		}));
		
		this.musicTimeline.play();
		this.getSceneLoader().getMainWindowStage().setOnCloseRequest(event -> {
	        	this.getController().stopMusic();
	        	if(this.getController().getEnemy().getTower().getHealth()==0) {
	        		try {
						this.getController().setLevelReach(this.getController().getLevel()+1);
					} catch (FileNotFoundException e1) {}
	        	}
	        	this.exit();
         });
		
	}
	

	private void initializeGoldLifePane() {
		Image white=new Image("/map_of_the_game/whiteVeil.png");
		BackgroundImage whiteBackGround= new BackgroundImage(white, null, null, null, null);
		this.goldLifeBox.setBackground(new Background(whiteBackGround));
		this.goldLifeBox.setSpacing(15);
		this.goldLifeBox.setPadding(new Insets(35, 0,12, 20));
		this.towerUserHealth.setFont(new Font("DejaVu Serif Bold",19));
		this.towerUserHealth.setTextAlignment(TextAlignment.CENTER);
		this.towerUserHealth.setText("Blue Tower: "+Integer.toString(this.getController().getUser().getTower().getHealth()));
		this.towerEnemyHealth.setFont(new Font("DejaVu Serif Bold",19));
		this.towerEnemyHealth.setTextAlignment(TextAlignment.CENTER);
		this.towerEnemyHealth.setFill(Color.CRIMSON);
		this.towerEnemyHealth.setText("Red Tower: "+Integer.toString(this.getController().getEnemy().getTower().getHealth()));
		this.towerUserHealth.setFill(Color.MEDIUMBLUE);
		this.gold.setFont(new Font("DejaVu Serif Bold",22));
		this.gold.setTextAlignment(TextAlignment.CENTER);
		this.gold.setFill(Color.GOLD);
		this.gold.setText("Gold: "+Integer.toString(this.getController().getUser().getMoney().getMoney()));
		this.goldLifeBox.getChildren().addAll(towerUserHealth,towerEnemyHealth,gold);
		
		this.updateGoldLife=new Timeline();
		this.updateGoldLife.setCycleCount(Animation.INDEFINITE);
		this.updateGoldLife.getKeyFrames().add(new KeyFrame(Duration.millis(10),(ActionEvent e)->{
			this.towerUserHealth.setText("Blue Tower: "+Integer.toString(this.getController().getUser().getTower().getHealth()));
			this.towerEnemyHealth.setText("Red Tower: "+Integer.toString(this.getController().getEnemy().getTower().getHealth()));
			this.gold.setText("Gold: "+Integer.toString(this.getController().getUser().getMoney().getMoney()));
		}));
		updateGoldLife.play();
		
	}

	private void initializeUnitButton() {
		
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
			ImageView blackImageView = new ImageView();
			blackImageView.prefHeight(100);
			blackImageView.prefWidth(100);
			this.unitDelayImageView.add(blackImageView);
			this.buttonBackgroundGroupList.add(new Group(canvas,this.unitDelayImageView.get(i)));
			Button creationButton= new Button("",this.buttonBackgroundGroupList.get(i));
			this.unitCreationButtonList.add(creationButton);
			int index=i;
			Timeline time= new Timeline();
			time.getKeyFrames().add(new KeyFrame((Duration.millis(30)),(ActionEvent e)-> {
				this.buttonCreateUnit(index);
			}));
			
			time.getKeyFrames().add(new KeyFrame((Duration.millis(1000*this.getController()
					.getUser().getUnitTemplateList().get(index).getBuildingTime())),(ActionEvent e)-> {
					this.unitDelayImageView.get(index).setImage(null);
					this.unitCreationButtonList.get(index).setDisable(false);
					time.stop();
			}));
			
			this.timerDelayList.add(time);
			creationButton.setOnAction((e->{
				time.play();
			}));
		}
		this.unitDelayImage=new Image("/map_of_the_game/blackVeil.png");
		
		
		
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
		this.initializeGoldLifePane();
		this.towerLife();
	}

	public void renderBackground() {
		this.graphicsGameCanvas.drawImage(this.background, 0, 0);
		this.graphicsGameCanvas.drawImage(this.groundTile, 0, 625);
		this.graphicsGameCanvas.drawImage(this.groundTile, 1000, 625);
	}

	public void setScrollPane(ScrollPane pane) {
		this.scrollPane=pane;
	}

	public void setUserPane(TabPane userPane) {
		this.userPane = userPane;
	}
	/**
	 * initialize and starts the timeline that check periodically if the
	 * towers are alive and if they are destroyed the game ends, and 
	 * the method calls the youLose or youWin method
	 */
	public void towerLife() {
		this.towerLife= new Timeline();
		towerLife.setCycleCount(Animation.INDEFINITE);
		towerLife.getKeyFrames().add(new KeyFrame(Duration.millis(10),(ActionEvent e)->{
			if(this.getController().getUser().getTower().isDestroyed()) {	
				this.exit();
				this.youLose();
			}
			
			if(this.getController().getEnemy().getTower().isDestroyed()) {		
				this.exit();
				this.youWin();
			}
		}));
		towerLife.play();
		
	}
	/**
	 * opens a new pane on the stage with the button to return to menu
	 * or to retry the level
	 */
	private void youLose() {
		this.youWinLose= new Pane();
		this.youWinLose.setPrefSize(528,360);
		this.youWinLose.setLayoutX(376);
		this.youWinLose.setLayoutY(220);
		Button repeatLevel= new Button();
		Button returnMenu= new Button();
		repeatLevel.setFont(new Font("DejaVu Serif Bold",26));
		returnMenu.setFont(new Font("DejaVu Serif Bold",26));
		repeatLevel.setLayoutX(25);
		repeatLevel.setLayoutY(253);
		returnMenu.setLayoutX(308);
		returnMenu.setLayoutY(253);
		repeatLevel.setMnemonicParsing(false);
		returnMenu.setMnemonicParsing(false);
		repeatLevel.setText("Repeat Level");
		returnMenu.setText("Menu");
		repeatLevel.setOnAction(e->{
			try {
				this.getController().setLevel(this.getController().getLevel());
				this.exit();
				this.getController().initializeGame(this.getController().getLevel());
		    	this.getController().setEquipmentUnitTemplate();
		    	this.getSceneLoader().getMainWindowStage().close();
				this.getSceneLoader().load(SceneTypes.STARTWINDOW);
			} catch (FileNotFoundException e1) {} catch (IOException e1) {}
			
		});
		returnMenu.setOnAction(e->{
				this.exit();
				this.getSceneLoader().getMainWindowStage().close();
		});
		this.youWinLose.getChildren().addAll(repeatLevel,returnMenu);
		this.mainPane.getChildren().add(this.youWinLose);
		
	}
	/**
	 * opens a new pane on the stage with the button to return to menu
	 * or play the next level
	 */
	private void youWin() {
		this.youWinLose= new Pane();
		this.youWinLose.setPrefSize(528,360);
		this.youWinLose.setLayoutX(376);
		this.youWinLose.setLayoutY(220);
		Button nextLevel= new Button();
		Button returnMenu= new Button();
		nextLevel.setFont(new Font("DejaVu Serif Bold",26));
		returnMenu.setFont(new Font("DejaVu Serif Bold",26));
		nextLevel.setLayoutX(25);
		nextLevel.setLayoutY(253);
		returnMenu.setLayoutX(308);
		returnMenu.setLayoutY(253);
		nextLevel.setMnemonicParsing(false);
		returnMenu.setMnemonicParsing(false);
		nextLevel.setText("Next Level");
		returnMenu.setText("Menu");
		nextLevel.setOnAction(e->{
			try {
				this.getController().setLevelReach(this.getController().getLevel()+1);
				this.getController().setLevel(this.getController().getLevel()+1);
				this.exit();
				this.getController().initializeGame(this.getController().getLevel());
		    	this.getController().setEquipmentUnitTemplate();
		    	this.getSceneLoader().getMainWindowStage().close();
				this.getSceneLoader().load(SceneTypes.STARTWINDOW);
			} catch (FileNotFoundException e1) {} catch (IOException e1) {}
			
		});
		returnMenu.setOnAction(e->{
			try {
				this.getController().setLevelReach(this.getController().getLevel()+1);
			} catch (FileNotFoundException e1) {}
				this.exit();
				this.getSceneLoader().getMainWindowStage().close();
		});
		this.youWinLose.getChildren().addAll(nextLevel,returnMenu);
		this.mainPane.getChildren().add(this.youWinLose);
		
	}
	
}
