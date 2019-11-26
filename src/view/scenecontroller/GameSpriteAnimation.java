package view.scenecontroller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentLinkedQueue;

import gameModelStructure.UnitToMake;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import view.sprite.AnimateMeleeUnitOnPane;
import view.sprite.FrameUnitBuilder;
import view.sprite.FrameUnitEnemyBuilder;
import view.sprite.FrameUnitManager;
import view.sprite.SpriteImpl;
import view.sprite.SpriteUnit;
import view.sprite.SpriteUnit.SpriteUnitBuilder;

public class GameSpriteAnimation extends SceneControllerImpl {
	
	private static int STARTING_UNIT_USER_X=53;
	private static int STARTING_UNIT_USER_Y=505;
	private static int STARTING_UNIT_ENEMY_X=1428;
	private static int STARTING_UNIT_ENEMY_Y=505;
	private List <FrameUnitManager> frameUnitManagerList;
	private List <FrameUnitManager> frameUnitEnemyManagerList;
	private List <AnimateUnitOnPane> spriteUnitOnScene;
	private List <AnimateUnitOnPane> spriteUnitEnemyOnScene;
	/*private List<Animation> spriteAnimationList;*/
	private SpriteImpl userTower;
	private SpriteImpl enemyTower;
	private Pane unitDisplayPane; 
	private UnitToMake unitToMake;
	
	/*public SpriteUnitAnimation() {
		super();
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
				.boundaryHeight(240).boundaryHeight(120).build();
		this.userTower=new SpriteImpl.SpriteImplBuilder().height(240).width(140).mainImage(userTowerImage)
				.positionX(-8).positionY(385).xOffset(0).yOffset(0)
				.boundaryHeight(240).boundaryHeight(120).build();
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
	

	
	public void createUnitSprite(int index) {
		//System.out.println("index in create unit sprite : "+index);
		FrameUnitManager unitFrame=this.frameUnitManagerList.get(index);
		SpriteUnitBuilder spriteBuilder;
		spriteBuilder=new SpriteUnit.SpriteUnitBuilder().mainImage(unitFrame.getBodyMovingFrameList().get(0))
				.boundaryHeight(unitFrame.getBoundaryHeight())
				.boundaryWidth(unitFrame.getBoundaryWidth())
				.xOffset(unitFrame.getxOffset())
				.yOffset(unitFrame.getyOffset())
				.positionX(STARTING_UNIT_USER_X).positionY(STARTING_UNIT_USER_Y)
				.weaponBoundaryHeight(unitFrame.getWeaponBoundaryHeight())
				.weaponBoundaryWidth(unitFrame.getWeaponBoundaryWidth())
				.weaponXOffset(unitFrame.getWeaponXOffset())
				.weaponYOffset(unitFrame.getWeaponYOffset());
				
		if(unitFrame.getArmorMovingFrameList().isPresent()) {
			spriteBuilder=spriteBuilder.armor(unitFrame.getArmorMovingFrameList().get().get(0));
		}
		
		if(unitFrame.getWeaponMovingFrameList().isPresent()) {
			spriteBuilder=spriteBuilder.weapon(unitFrame.getWeaponMovingFrameList().get().get(0));
		}
		SpriteUnit spriteUnit=spriteBuilder.build();
		this.spriteUnitOnScene.add(spriteUnit);
		/*this.spriteUnitOnScene.element().getImageGroup().setTranslateX(STARTING_UNIT_X);
		this.spriteUnitOnScene.element().getImageGroup().setTranslateY(STARTING_UNIT_Y);
		this.unitDisplayPane.getChildren().add(this.spriteUnitOnScene.element().getImageGroup());
		this.moveMultipleUnits(index,this.frameUnitManagerList, spriteUnit);
	}
	*/
	
		
	
	/*
	@SuppressWarnings("unlikely-arg-type")
	public void moveMultipleUnits(int index,List<FrameUnitManager> framelist, SpriteUnit sprite) {
		List<Group> animateGroup=this.generateGroupOfImage(index,framelist);
		List<Group> animateGroupAttack=this.generateGroupOfImageAttack(index,framelist);
		List<Group> animateGroupDeath= this.generateGroupOfImageDeath(index, framelist);
		Timeline walk= new Timeline();
		Timeline unitStop= new Timeline();
		Timeline attackEvent= new  Timeline();
		Timeline attack = new Timeline();
		Timeline playerUnitCheck= new Timeline();
		Timeline death= new Timeline();
	//	System.out.println("indice unità: "+this.spriteUnitOnScene.indexOf(sprite));
		/*System.out.println("attacco: "+this.getController().getUser().getUnitQueue().get(spriteUnitOnScene.indexOf(sprite)).getDamage());
		System.out.println("index"+spriteUnitOnScene.indexOf(sprite));
		
		death.setCycleCount(Timeline.INDEFINITE);
		walk.setCycleCount(Timeline.INDEFINITE);
		unitStop.setCycleCount(Timeline.INDEFINITE);
		attack.setCycleCount(Timeline.INDEFINITE);
		attackEvent.setCycleCount(Timeline.INDEFINITE);
		
		walk.getKeyFrames().add(new KeyFrame(Duration.millis(700*this.getController().getUser().getUnitTemplateList().get(index).getSpeed())
				,(ActionEvent event)->{
				//System.out.println("infliggerò:"+this.getController().getUser().getUnitQueue().get(spriteUnitOnScene.indexOf(0)).getDamage());
			sprite.setImageGroup(animateGroup.get(1));	
		}));
		walk.getKeyFrames().add(new KeyFrame(Duration.millis(1400*this.getController().getUser().getUnitTemplateList().get(index).getSpeed()),
				(ActionEvent event)->{
			sprite.setImageGroup(animateGroup.get(0));
		}));
		
		walk.play();		
	   TranslateTransition animationBody = new TranslateTransition(
                Duration.millis(120*this.getController().getUser().getUnitTemplateList().get(index).getSpeed()), 
                sprite.getImageGroup());
        animationBody.setCycleCount(1);
        animationBody.setFromX(sprite.getPositionX());
        animationBody.setToX(sprite.getPositionX()+3);
        	animationBody.setOnFinished(new EventHandler<ActionEvent>() {		
            @Override 
            public void handle(ActionEvent t) {
            	sprite.incrementPositionX(3);
            	animationBody.setFromX(sprite.getPositionX());
            	animationBody.setToX(sprite.getPositionX()+3);
            	animationBody.play();
            }  
        });
		animationBody.play();	
		unitStop.getKeyFrames().add(new KeyFrame(Duration.millis(100),(ActionEvent event)->{		
			if(spriteUnitOnScene.indexOf(sprite)>=1 ) {
				if(sprite.intersects(spriteUnitOnScene.get(spriteUnitOnScene.indexOf(sprite)-1))) {
        			animationBody.stop();
        		}
				if(!sprite.intersects(spriteUnitOnScene.get(spriteUnitOnScene.indexOf(sprite)-1))) {				
					animationBody.play();
				}
			}	
		}));
		unitStop.play();
		
		
		playerUnitCheck.setCycleCount(Animation.INDEFINITE);
		playerUnitCheck.getKeyFrames().add(new KeyFrame(Duration.millis(50)
				,(ActionEvent event)->{
						if(!this.getController().getUser().getUnitQueue().get(spriteUnitOnScene.indexOf(sprite)).isAlive()) {
							this.getController().getUser().getUnitQueue().remove(spriteUnitOnScene.indexOf(sprite));
							this.spriteUnitOnScene.remove((sprite));
							animationBody.stop();
							walk.stop();
							attack.stop();
							attackEvent.stop();
							death.play();
							unitStop.stop();
							playerUnitCheck.stop();
						}
			
		}));
		playerUnitCheck.play();
		
		int frameIndex=0;
		for(Group group:animateGroupDeath) {
			death.getKeyFrames().add(new KeyFrame(Duration.millis(1+frameIndex*600)
					,(ActionEvent event)->{	
						sprite.setImageGroup(group);		
			}));
			frameIndex=frameIndex+1;
		}
		frameIndex--;
		death.getKeyFrames().add(new KeyFrame(Duration.millis(10+frameIndex*600)
				,(ActionEvent event)->{
					this.unitDisplayPane.getChildren().remove(sprite.getImageGroup());
					death.stop();
					
		}));
		frameIndex=1;
		
		attackEvent.getKeyFrames().add(new KeyFrame(Duration.millis(100)
				,(ActionEvent event)->{
					
					if(sprite.weaponIntersectEnemy(enemyTower)) {
						walk.stop();
						animationBody.stop();
						attack.play();
					}else if(spriteUnitEnemyOnScene.size()==0) {				
							attack.stop();
							animationBody.play();
							walk.play();
						}
						else {
							if(!sprite.weaponIntersectEnemy(spriteUnitEnemyOnScene.get(0))) {
								attack.stop();
								animationBody.play();
								walk.play();
							}
							if(sprite.weaponIntersectEnemy(spriteUnitEnemyOnScene.get(0))) {						
						
								walk.stop();
								animationBody.stop();
								attack.play();
							}
						}	
		}));
		
		attackEvent.play();
		
		for(Group group:animateGroupAttack) {
			attack.getKeyFrames().add(new KeyFrame(Duration.millis(frameIndex*450)
					,(ActionEvent event)->{	
						sprite.setImageGroup(group);		
			}));
			frameIndex=frameIndex+1;
		}
		attack.getKeyFrames().add(new KeyFrame(Duration.millis(frameIndex*450)
				,(ActionEvent event)->{	
					if(sprite.weaponIntersectEnemy(enemyTower)) {
						this.getController().getEnemy().getTower().reduceHealth(
								this.getController().getUser().getUnitQueue().get(spriteUnitOnScene.indexOf(sprite)).getDamage());
						sprite.setImageGroup(animateGroup.get(0));
					}
					else {
						this.getController().getEnemy().getUnitQueue().get(0).reduceHealth(
								this.getController().getUser().getUnitQueue().get(spriteUnitOnScene.indexOf(sprite)).getDamage());
						sprite.setImageGroup(animateGroup.get(0));	
					}
		}));
		attack.getKeyFrames().add(new KeyFrame(Duration.millis(450+frameIndex*450+1000*
				this.getController().getUser().getUnitTemplateList().get(index).getWeapon().getWeaponDelay())
				,(ActionEvent event)->{	

		}));
		
		

		this.spriteUnitOnScene.get(spriteUnitOnScene.indexOf(sprite)).getImageGroup().setTranslateX(STARTING_UNIT_USER_X);
		this.spriteUnitOnScene.get(spriteUnitOnScene.indexOf(sprite)).getImageGroup().setTranslateY(STARTING_UNIT_USER_Y);
		this.unitDisplayPane.getChildren().add(this.spriteUnitOnScene.get(spriteUnitOnScene.indexOf(sprite)).getImageGroup());
	}*/
	
	/*
	
	public void moveMultipleEnemyUnits(int index,List<FrameUnitManager> framelist, SpriteUnit sprite) {
		List<Group> animateGroup=this.generateGroupOfImage(index,framelist);
		List<Group> animateGroupAttack= this.generateGroupOfImageAttack(index,framelist);
		List<Group> animateGroupDeath= this.generateGroupOfImageDeath(index, framelist);
		Timeline walk= new Timeline();
		Timeline unitStop= new Timeline();
		Timeline attackEvent= new  Timeline();
		Timeline attack = new Timeline();
		Timeline playerUnitCheck= new Timeline();
		Timeline death= new Timeline();
		
		
		//int spriteUnitPosition=this.spriteUnitEnemyOnScene.size()-1;
		
		
		playerUnitCheck.setCycleCount(Timeline.INDEFINITE);
		death.setCycleCount(Timeline.INDEFINITE);
		walk.setCycleCount(Timeline.INDEFINITE);
		unitStop.setCycleCount(Timeline.INDEFINITE);
		attack.setCycleCount(Timeline.INDEFINITE);
		attackEvent.setCycleCount(Timeline.INDEFINITE);
		
		walk.getKeyFrames().add(new KeyFrame(Duration.millis(700*this.getController().getEnemy().getUnitTemplateList().get(index).getSpeed())
				,(ActionEvent event)->{
			sprite.setImageGroup(animateGroup.get(1));	
		}));
		walk.getKeyFrames().add(new KeyFrame(Duration.millis(1400*this.getController().getEnemy().getUnitTemplateList().get(index).getSpeed()),
				(ActionEvent event)->{
			sprite.setImageGroup(animateGroup.get(0));
		}));
		
		walk.play();
		
	   TranslateTransition animationBody = new TranslateTransition(
                Duration.millis(120*this.getController().getEnemy().getUnitTemplateList().get(index).getSpeed()), 
                sprite.getImageGroup());
        animationBody.setCycleCount(1);
        animationBody.setFromX(sprite.getPositionX());
        animationBody.setToX(sprite.getPositionX()-3);
        	animationBody.setOnFinished(new EventHandler<ActionEvent>() {		
            @Override 
            public void handle(ActionEvent t) {
            	sprite.incrementPositionX(-3);
            	animationBody.setFromX(sprite.getPositionX());
            	animationBody.setToX(sprite.getPositionX()-3);
            	animationBody.play();
            }  
        });
		animationBody.play();
		
	
		unitStop.getKeyFrames().add(new KeyFrame(Duration.millis(100),(ActionEvent event)->{
			
			if(spriteUnitEnemyOnScene.indexOf(sprite)>=1) {
				if(sprite.intersects(spriteUnitEnemyOnScene.get(spriteUnitEnemyOnScene.indexOf(sprite)-1))) {
        			animationBody.stop();
        		}

				if(!sprite.intersects(spriteUnitEnemyOnScene.get(spriteUnitEnemyOnScene.indexOf(sprite)-1))) {
					
					animationBody.play();
				}
			}	
		}));
		unitStop.play();
		
		playerUnitCheck.setCycleCount(Animation.INDEFINITE);
		playerUnitCheck.getKeyFrames().add(new KeyFrame(Duration.millis(50)
				,(ActionEvent event)->{
						if(!this.getController().getEnemy().getUnitQueue().get(spriteUnitEnemyOnScene.indexOf(sprite)).isAlive()) {
							this.getController().getEnemy().getUnitQueue().remove(spriteUnitEnemyOnScene.indexOf(sprite));
							this.spriteUnitEnemyOnScene.remove(spriteUnitEnemyOnScene.indexOf(sprite));
							animationBody.stop();
							walk.stop();
							attack.stop();
							attackEvent.stop();
							death.play();
							unitStop.stop();
							playerUnitCheck.stop();
						}
			
		}));
		playerUnitCheck.play();
		
		int frameIndex=0;
		for(Group group:animateGroupDeath) {
			death.getKeyFrames().add(new KeyFrame(Duration.millis(1+frameIndex*600)
					,(ActionEvent event)->{	
						sprite.setImageGroup(group);		
			}));
			frameIndex=frameIndex+1;
		}
		frameIndex--;
		death.getKeyFrames().add(new KeyFrame(Duration.millis(10+frameIndex*600)
				,(ActionEvent event)->{
					this.unitDisplayPane.getChildren().remove(sprite.getImageGroup());
					death.stop();
					
		}));
		frameIndex=1;
		
		
		attackEvent.getKeyFrames().add(new KeyFrame(Duration.millis(100)
				,(ActionEvent event)->{
					
					
					if(sprite.weaponIntersectEnemy(userTower)) {
						walk.stop();
						animationBody.stop();
						attack.play();
					}else if(spriteUnitOnScene.size()==0) {				
						attack.stop();
						animationBody.play();
						walk.play();
					
					}
					else {
						if(!sprite.weaponIntersectEnemy(spriteUnitOnScene.get(0))) {
							attack.stop();
							animationBody.play();
							walk.play();
						}
						if(sprite.weaponIntersectEnemy(spriteUnitOnScene.get(0))) {						
					
							walk.stop();
							animationBody.stop();
							attack.play();
						}
					}
		}));	
		attackEvent.play();
		
		for(Group group:animateGroupAttack) {
			attack.getKeyFrames().add(new KeyFrame(Duration.millis(frameIndex*450)
					,(ActionEvent event)->{	
						sprite.setImageGroup(group);		
			}));
			frameIndex=frameIndex+1;
		}	
		attack.getKeyFrames().add(new KeyFrame(Duration.millis(frameIndex*450)
				,(ActionEvent event)->{
					this.getController().getUser().getUnitQueue().get(0).reduceHealth(
							this.getController().getEnemy().getUnitQueue().get(spriteUnitEnemyOnScene.indexOf(sprite)).getDamage());
					sprite.setImageGroup(animateGroup.get(0));
		}));
		attack.getKeyFrames().add(new KeyFrame(Duration.millis(450+frameIndex*450+1000*
				this.getController().getEnemy().getUnitTemplateList().get(index).getWeapon().getWeaponDelay())
				,(ActionEvent event)->{		
		}));
	
		
		this.spriteUnitEnemyOnScene.get(spriteUnitEnemyOnScene.indexOf(sprite)).getImageGroup().setTranslateX(STARTING_UNIT_ENEMY_X);
		this.spriteUnitEnemyOnScene.get(spriteUnitEnemyOnScene.indexOf(sprite)).getImageGroup().setTranslateY(STARTING_UNIT_ENEMY_Y);
		this.unitDisplayPane.getChildren().add(this.spriteUnitEnemyOnScene.get(spriteUnitEnemyOnScene.indexOf(sprite)).getImageGroup());
	}
	
	
	public List<Group> generateGroupOfImageDeath(int index, List<FrameUnitManager> frameList){
		ArrayList<Group> listOfGroupOfImage= new ArrayList<>();
		List<ImageView> body=new ArrayList<ImageView>();
		List<ImageView> weapon=new ArrayList<ImageView>();
		List<ImageView> armor=new ArrayList<ImageView>();
		int imageIndex=0;
		
		FrameUnitManager unitFrame=frameList.get(index);
		
		
		for(Image image:unitFrame.getBodyDyingFrameList()) {	
			Group groupToAdd= new Group();
			body.add(new ImageView(unitFrame.getBodyDyingFrameList().get(imageIndex)));
			groupToAdd.getChildren().add(body.get(imageIndex));
			if(unitFrame.getArmorDyingFrameList().isPresent()&& unitFrame.getArmorDyingFrameList().get()
					.size()>=unitFrame.getBodyDyingFrameList().size()) {
				armor.add(new ImageView(unitFrame.getArmorDyingFrameList().get().get(imageIndex)));
				groupToAdd.getChildren().add(armor.get(imageIndex));
			}
			if(unitFrame.getWeaponAttackFrameList().isPresent() && unitFrame.getWeaponDyingFrameList().get()
					.size()>=unitFrame.getBodyDyingFrameList().size()) {
				weapon.add(new ImageView(unitFrame.getWeaponDyingFrameList().get().get(imageIndex)));
				groupToAdd.getChildren().add(weapon.get(imageIndex));
			}
			listOfGroupOfImage.add(groupToAdd);
			
			imageIndex++;
		}
		return listOfGroupOfImage;
	}
	
	
	
	public List<Group> generateGroupOfImageAttack(int index, List<FrameUnitManager> frameList){
		ArrayList<Group> listOfGroupOfImage= new ArrayList<>();
		List<ImageView> body=new ArrayList<ImageView>();
		List<ImageView> weapon=new ArrayList<ImageView>();
		List<ImageView> armor=new ArrayList<ImageView>();
		int imageIndex=0;
		
		FrameUnitManager unitFrame=frameList.get(index);
		for(Image image:unitFrame.getBodyAttackFrameList()) {
			Group groupToAdd= new Group();
			body.add(new ImageView(unitFrame.getBodyAttackFrameList().get(imageIndex)));
			groupToAdd.getChildren().add(body.get(imageIndex));
			if(unitFrame.getArmorAttackFrameList().isPresent()) {
				armor.add(new ImageView(unitFrame.getArmorAttackFrameList().get().get(imageIndex)));
				groupToAdd.getChildren().add(armor.get(imageIndex));
			}
			if(unitFrame.getWeaponAttackFrameList().isPresent()) {
				weapon.add(new ImageView(unitFrame.getWeaponAttackFrameList().get().get(imageIndex)));
				groupToAdd.getChildren().add(weapon.get(imageIndex));
			}
			listOfGroupOfImage.add(groupToAdd);
			
			imageIndex++;
		}
		return listOfGroupOfImage;
	}
	
	
	
	public List<Group> generateGroupOfImage(int index,List<FrameUnitManager> frameList){
		ArrayList<Group> listOfGroupOfImage= new ArrayList<>();
		List<ImageView> body=new ArrayList<ImageView>();
		List<ImageView> weapon=new ArrayList<ImageView>();
		List<ImageView> armor=new ArrayList<ImageView>();
		int imageIndex=0;
		
		FrameUnitManager unitFrame=frameList.get(index);
		for(Image image:unitFrame.getBodyMovingFrameList()) {
			Group groupToAdd= new Group();
			body.add(new ImageView(unitFrame.getBodyMovingFrameList().get(imageIndex)));
			groupToAdd.getChildren().add(body.get(imageIndex));
			if(unitFrame.getArmorMovingFrameList().isPresent()) {
				armor.add(new ImageView(unitFrame.getArmorMovingFrameList().get().get(imageIndex)));
				groupToAdd.getChildren().add(armor.get(imageIndex));
			}
			if(unitFrame.getWeaponMovingFrameList().isPresent()) {
				weapon.add(new ImageView(unitFrame.getWeaponMovingFrameList().get().get(imageIndex)));
				groupToAdd.getChildren().add(weapon.get(imageIndex));
			}
			listOfGroupOfImage.add(groupToAdd);
			
			imageIndex++;
		}
		return listOfGroupOfImage;
	}*/
	
	
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
				.boundaryHeight(240).boundaryHeight(120).build();
		this.userTower=new SpriteImpl.SpriteImplBuilder().height(240).width(140).mainImage(userTowerImage)
				.positionX(-8).positionY(385).xOffset(0).yOffset(0)
				.boundaryHeight(240).boundaryHeight(120).build();
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
	

	public List<FrameUnitManager> getFrameUnitManagerList() {
		return frameUnitManagerList;
	}

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
				spriteUnitOnScene, spriteUnitEnemyOnScene, this.enemyTower, 
				this.getController().getEnemy().getTower(),
				this.getController().getUser().getUnitTemplateList(), 
				this.unitDisplayPane);
		
	}

	public void createUnitEnemy() {
		Timeline generateUnit= new Timeline();
		generateUnit.setCycleCount(Timeline.INDEFINITE);
		this.unitToMake=this.getController().nextUnitToMake();
		generateUnit.getKeyFrames().add(new KeyFrame(Duration.millis(1000*unitToMake.getCoolDown())
				,(ActionEvent event)->{
					for(int i=0; i<this.unitToMake.getTemplateIndexArray().size(); i++) {
						//System.out.println(this.unitToMake.getTemplateIndexArray().get(i));
						this.createEnemyUnitSprite(this.unitToMake.getTemplateIndexArray().get(i));
						this.getController().getEnemy().addUnit(this.unitToMake.getTemplateIndexArray().get(i));
					}
					
		}));
		generateUnit.getKeyFrames().add(new KeyFrame(Duration.millis(1000*unitToMake.getCoolDown()+100)
				,(ActionEvent event)->{
					this.unitToMake=this.getController().nextUnitToMake();
		}));
		generateUnit.play();
			
	}		
	
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
			spriteUnitEnemyOnScene, spriteUnitOnScene, this.userTower, 
			this.getController().getUser().getTower(),
			this.getController().getEnemy().getUnitTemplateList(), 
			this.unitDisplayPane);
	//	System.out.println("index:"+index);
	//	FrameUnitManager unitFrame=this.frameUnitEnemyManagerList.get(index);
		
		
		
		
		
		/*SpriteUnitBuilder spriteBuilder;
		System.out.println("boundary:"+unitFrame.getBoundaryHeight());
		spriteBuilder=new SpriteUnit.SpriteUnitBuilder().mainImage(unitFrame.getBodyMovingFrameList().get(0))
				.boundaryHeight(unitFrame.getBoundaryHeight())
				.boundaryWidth(unitFrame.getBoundaryWidth())
				.xOffset(unitFrame.getxOffset())
				.yOffset(unitFrame.getyOffset())
				.weaponBoundaryHeight(unitFrame.getWeaponBoundaryHeight())
				.weaponBoundaryWidth(unitFrame.getWeaponBoundaryWidth())
				.weaponXOffset(unitFrame.getWeaponXOffset())
				.weaponYOffset(unitFrame.getWeaponYOffset())
				.positionX(STARTING_UNIT_ENEMY_X).positionY(STARTING_UNIT_ENEMY_Y);
		if(unitFrame.getArmorMovingFrameList().isPresent()) {
			spriteBuilder=spriteBuilder.armor(unitFrame.getArmorMovingFrameList().get().get(0));
		}
		
		if(unitFrame.getWeaponMovingFrameList().isPresent()) {
			spriteBuilder=spriteBuilder.weapon(unitFrame.getWeaponMovingFrameList().get().get(0));
		}
		SpriteUnit spriteUnit=spriteBuilder.build();
		
		this.spriteUnitEnemyOnScene.add(spriteUnit);*/
		/*this.spriteUnitOnScene.element().getImageGroup().setTranslateX(STARTING_UNIT_X);
		this.spriteUnitOnScene.element().getImageGroup().setTranslateY(STARTING_UNIT_Y);
		this.unitDisplayPane.getChildren().add(this.spriteUnitOnScene.element().getImageGroup());*/
	//	this.moveMultipleEnemyUnits(index,this.frameUnitEnemyManagerList,spriteUnit);
		
	}

/*
	public List<Animation> getSpriteAnimationList() {
		return spriteAnimationList;
	}

*/
	public Pane getUnitDisplayPane() {
		return this.unitDisplayPane;
	}
	/*
	public void setSpriteAnimationList(List<Animation> spriteAnimationList) {
		this.spriteAnimationList = spriteAnimationList;
	}


	public Timeline getTimeline() {
		return timeline;
	}


	public void setTimeline(Timeline timeline) {
		this.timeline = timeline;
	}
	
	
	
	*/
	

}
