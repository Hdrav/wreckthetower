package view.scenecontroller;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.unit.UnitToMake;
import view.sprite.FrameUnitBuilder;
import view.sprite.FrameUnitEnemyBuilder;
import view.sprite.FrameUnitManager;
import view.sprite.SpriteImpl;


public class GameSpriteAnimation extends SceneControllerImpl {
	/**
	 * controller class for the pane where the sprite are "rendered"
	 * 
	 */
	private static int STARTING_UNIT_USER_X=53;
	private static int STARTING_UNIT_USER_Y=505;
	private static int STARTING_UNIT_ENEMY_X=1428;
	private static int STARTING_UNIT_ENEMY_Y=505;
	private List <FrameUnitManager> frameUnitManagerList;
	private List <FrameUnitManager> frameUnitEnemyManagerList;
	private List <AnimateUnitOnPane> spriteUnitOnScene;
	private List <AnimateUnitOnPane> spriteUnitEnemyOnScene;
	private SpriteImpl userTower;
	private SpriteImpl enemyTower;
	private Pane unitDisplayPane; 
	private UnitToMake unitToMake;
	private Timeline generateEnemyUnit;
	
	
	
	/**
	 * constructor
	 */
	public GameSpriteAnimation() {
		this.spriteUnitOnScene = new ArrayList<>();
		this.spriteUnitEnemyOnScene=new ArrayList<>();
		this.frameUnitManagerList=new ArrayList<>();
		this.frameUnitEnemyManagerList=new ArrayList<>();
		this.unitDisplayPane=new Pane();
		this.unitDisplayPane.setPrefSize(800, 1600);
		Image userTowerImage= new Image("/map_of_the_game/tower_player.png");
		Image enemyTowerImage=new Image("/map_of_the_game/enemy_tower01.png");
		this.enemyTower=new SpriteImpl.SpriteImplBuilder().height(240).width(140).mainImage(enemyTowerImage)
				.positionX(1488).positionY(385).xOffset(0).yOffset(0)
				.boundaryHeight(240).boundaryWidth(120).build();
		this.userTower=new SpriteImpl.SpriteImplBuilder().height(240).width(140).mainImage(userTowerImage)
				.positionX(-8).positionY(385).xOffset(0).yOffset(0)
				.boundaryHeight(240).boundaryWidth(120).build();
		ImageView towerEnemyView= new ImageView(enemyTower.getMainImage());
		ImageView towerUserView= new ImageView(userTower.getMainImage());
		this.unitDisplayPane.getChildren().addAll(towerEnemyView,towerUserView);
		towerEnemyView.setTranslateX(this.enemyTower.getPositionX());
		towerEnemyView.setTranslateY(this.enemyTower.getPositionY());
		towerUserView.setTranslateX(this.userTower.getPositionX());
		towerUserView.setTranslateY(this.userTower.getPositionY());
		
		for(int i=0; i<this.getController().getUser().getNumberOfUnit(); i++) {
			FrameUnitManager frameManager= FrameUnitBuilder.newFrameUnitBuilder(i).build();
			frameUnitManagerList.add(frameManager);
		}
		for(int i=0; i<this.getController().getEnemy().getNumberOfUnit(); i++) {
			FrameUnitManager frameManager= FrameUnitEnemyBuilder.newFrameUnitBuilder(i).build();
			frameUnitEnemyManagerList.add(frameManager);
		}
		this.createUnitEnemy();
	}
	
	/**
	 * method that create the sprite of a unit of the enemy
	 * @param index the unit template index
	 */
	public void createEnemyUnitSprite(int index) {
		AnimateUnitOnPane unit=null;
		if(this.getController().getEnemy().getUnitTemplateList().get(index).getWeapon().isMelee()) { 
		unit= new AnimateMeleeUnitOnPane(index,false,GameSpriteAnimation.STARTING_UNIT_ENEMY_X,
				GameSpriteAnimation.STARTING_UNIT_ENEMY_Y );
		}
		else if(!this.getController().getEnemy().getUnitTemplateList().get(index).getWeapon().isMelee()) {
			unit= new AnimateRangedUnitOnPane(index,false,GameSpriteAnimation.STARTING_UNIT_ENEMY_X,
					GameSpriteAnimation.STARTING_UNIT_ENEMY_Y );
		}
		this.spriteUnitEnemyOnScene.add(unit);
		this.unitDisplayPane.getChildren().add(unit.getSprite().getImageGroup());
		unit.getSprite().getImageGroup().setTranslateX(STARTING_UNIT_ENEMY_X);
		unit.getSprite().getImageGroup().setTranslateY(STARTING_UNIT_ENEMY_Y);
		unit.executeSpriteStrategy(this.getController().getEnemy().getUnitTemplateList().get(index).getSpeed(),
			this.getController().getEnemy().getUnitQueue(), this.getController().getUser().getUnitQueue(),
			spriteUnitEnemyOnScene, spriteUnitOnScene, 
			this.getController().getUser().getMoney(),this.userTower, 
			this.getController().getUser().getTower(),
			this.getController().getEnemy().getUnitTemplateList(), 
			this.unitDisplayPane);	
	}
	
	
	/*
	 * method that like a timer, calls createEnemyUnitSprite periodically, based on the cooldown of the
	 * list of unit index contained in unitToMake
	 * */
	private void createTimer() {
	   if (this.generateEnemyUnit != null) {
	       this.generateEnemyUnit.stop();
	   }
	   this.generateEnemyUnit = new Timeline(new KeyFrame(Duration.millis(unitToMake.getCoolDown()*1000), (ActionEvent e)->{
		    for(int i=0; i<this.unitToMake.getTemplateIndexArray().size(); i++) {
				this.createEnemyUnitSprite(this.unitToMake.getTemplateIndexArray().get(i));
				this.getController().getEnemy().addUnit(this.unitToMake.getTemplateIndexArray().get(i));
				this.upgradeSeconds();
			}
	    }));
	    this.generateEnemyUnit.setCycleCount(Animation.INDEFINITE);
	    this.generateEnemyUnit.play();
	}

	/**
	 * starting method for the algorithm of enemy unit creation
	 */
	public void createUnitEnemy() {
		this.upgradeSeconds();
	}

	/**
	 * create a unit for the user
	 * @param index the index of the template unit to create
	 */
	public void createUnitUser(int index) { 
		AnimateUnitOnPane unit=null;
		if(this.getController().getUser().getUnitTemplateList().get(index).getWeapon().isMelee()) { 
		unit= new AnimateMeleeUnitOnPane(index,true,GameSpriteAnimation.STARTING_UNIT_USER_X,
				GameSpriteAnimation.STARTING_UNIT_USER_Y );
		}
		else if(!this.getController().getUser().getUnitTemplateList().get(index).getWeapon().isMelee()) {
			unit= new AnimateRangedUnitOnPane(index,true,GameSpriteAnimation.STARTING_UNIT_USER_X,
					GameSpriteAnimation.STARTING_UNIT_USER_Y );
		}
		this.spriteUnitOnScene.add(unit);
		this.unitDisplayPane.getChildren().add(unit.getSprite().getImageGroup());
		unit.getSprite().getImageGroup().setTranslateX(STARTING_UNIT_USER_X);
		unit.getSprite().getImageGroup().setTranslateY(STARTING_UNIT_USER_Y);
			
		unit.executeSpriteStrategy(this.getController().getUser().getUnitTemplateList().get(index).getSpeed(),
				this.getController().getUser().getUnitQueue(), this.getController().getEnemy().getUnitQueue(),
				spriteUnitOnScene, spriteUnitEnemyOnScene, this.getController().getEnemy().getMoney(),this.enemyTower, 
				this.getController().getEnemy().getTower(),
				this.getController().getUser().getUnitTemplateList(), 
				this.unitDisplayPane);
	}

	
	public List<FrameUnitManager> getFrameUnitManagerList() {
		return frameUnitManagerList;
	}	
	

	public Timeline getGenerateEnemyUnit() {
		return generateEnemyUnit;
	}

	public List<AnimateUnitOnPane> getSpriteUnitEnemyOnScene() {
		return spriteUnitEnemyOnScene;
	}
	
	public List<AnimateUnitOnPane> getSpriteUnitOnScene() {
		return spriteUnitOnScene;
	}

	public Pane getUnitDisplayPane() {
		return this.unitDisplayPane;
	}
	/**
	 * method to stop all the game view pane timeline
	 * 
	 */
	public void stopGame() {
		this.spriteUnitEnemyOnScene.forEach(e->e.stopGameAnimation());
		this.spriteUnitOnScene.forEach(e->e.stopGameAnimation());
		this.generateEnemyUnit.stop();
	}
	
	/**
	 * method that update the cooldown and the units for the enemy unit creation  
	 */
	private void upgradeSeconds() {
		this.unitToMake= this.getController().nextUnitToMake();
		while(unitToMake.getCoolDown()==0) {
			this.unitToMake= this.getController().nextUnitToMake();
		}
	    this.createTimer();
	}
	

}
