package view.scenecontroller;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.money.MoneyValue;
import model.tower.Tower;
import model.unit.Unit;
import model.unit.UnitTemplate;
import view.sprite.FrameUnitBuilder;
import view.sprite.FrameUnitEnemyBuilder;
import view.sprite.FrameUnitManager;
import view.sprite.SpriteImpl;
import view.sprite.SpriteUnit;
import view.sprite.SpriteUnit.SpriteUnitBuilder;

public abstract class AnimateUnitOnPane {
	
	/*
	 * abstract class of template method, containing 
	 * the logics of the sprite animation
	 * 
	 * 
	 */
	private SpriteUnit sprite;
	private int startingUnitX=53;
	private int startingUnitY=505;
	private  FrameUnitManager unitFrame;
	private Boolean player;
	private int index;
	private boolean stopSlide;
	private boolean stopWalk;
	private boolean attacking;
	private List<Group> walkGroup;
	private List<Group> attackGroup;
	private List<Group> deathGroup;
	private Timeline walk= new Timeline();
	private Timeline unitStop= new Timeline();
	private Timeline attackEvent= new  Timeline();
	private Timeline attack = new Timeline();
	private Timeline unitCheck= new Timeline();
	private Timeline death= new Timeline();
	private TranslateTransition slide;
	private Timeline slideTime= new Timeline();

	private int slidingIncrement;
	
	
	
	/**
	 * constructor
	 * @param index of the template unit
	 * @param player true if the unit is for the player
	 * @param startingUnitX the starting x of the unit
	 * @param startingUnitY the starting y of the unit
	 */
	public AnimateUnitOnPane(int index,Boolean player,int startingUnitX,int startingUnitY) {
		this.startingUnitX=startingUnitX;
		this.startingUnitY=startingUnitY;
		this.player=player;
		this.index=index;
		if(player) {
			this.slidingIncrement=3;
			this.unitFrame= FrameUnitBuilder.newFrameUnitBuilder(index).build();
		}else {
			this.slidingIncrement=-3;
			this.unitFrame=FrameUnitEnemyBuilder.newFrameUnitBuilder(index).build();
		}
		slide = new TranslateTransition();
		this.walkGroup=new ArrayList<>();
		this.attackGroup= new ArrayList<>();
		this.deathGroup= new ArrayList<>();
		death.setCycleCount(Timeline.INDEFINITE);
		unitCheck.setCycleCount(Timeline.INDEFINITE);
		walk.setCycleCount(Timeline.INDEFINITE);
		unitStop.setCycleCount(Timeline.INDEFINITE);
		attack.setCycleCount(Timeline.INDEFINITE);
		attackEvent.setCycleCount(Timeline.INDEFINITE);
		slideTime.setCycleCount(Timeline.INDEFINITE);
		SpriteUnitBuilder spriteBuilder;
		spriteBuilder=new SpriteUnit.SpriteUnitBuilder().mainImage(unitFrame.getBodyMovingFrameList().get(0))
				.boundaryHeight(unitFrame.getBoundaryHeight())
				.boundaryWidth(unitFrame.getBoundaryWidth())
				.xOffset(unitFrame.getxOffset())
				.yOffset(unitFrame.getyOffset())
				.positionX(startingUnitX).positionY(startingUnitY)
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
		this.sprite=spriteBuilder.build();
		
		walkGroup=this.generateGroupOfImage();
		attackGroup=this.generateGroupOfImageAttack();
		deathGroup= this.generateGroupOfImageDeath();
	}
	
	
	
	/**
	 * method who start all the timelines
	 * 
	 * @param speed the unit speed constant
	 * @param listUserUnitModel the list of unit of the player
	 * @param listEnemyUnitModel the list of unit of the enemy player
	 * @param spriteUnitOnScene the list of sprite on scene
	 * @param spriteUnitEnemyOnScene	the list of enemy sprite on scene
	 * @param enemyMoney	the enemy money
	 * @param enemyTowerSprite the enemy tower sprite
	 * @param enemyTower	the enemy tower
	 * @param unitTemplateList	the player unit template list
	 * @param displayUnitPane	the pane of the game view
	 */
	public void executeSpriteStrategy(double speed,List<Unit> listUserUnitModel, List<Unit> listEnemyUnitModel,
									List<AnimateUnitOnPane> spriteUnitOnScene, List<AnimateUnitOnPane> spriteUnitEnemyOnScene,
									MoneyValue enemyMoney,
									SpriteImpl enemyTowerSprite, Tower enemyTower,List<UnitTemplate> unitTemplateList,
									Pane displayUnitPane) {
		this.startWalk(speed);
		this.startSlide(speed);
		this.startDeath(displayUnitPane);
		this.startUnitCheck(listUserUnitModel, spriteUnitOnScene,enemyMoney);
		this.startUnitStop(spriteUnitOnScene);
		this.startAttack(listEnemyUnitModel, listUserUnitModel, spriteUnitOnScene, spriteUnitEnemyOnScene,
				unitTemplateList, enemyTowerSprite, enemyTower);
		this.startAttackEvent(spriteUnitOnScene, spriteUnitEnemyOnScene, enemyTowerSprite);
		
	}

	/**
	 * 
	 * @return the list of group of images that represent the unit
	 * on screen moving
	 */
	@SuppressWarnings("unused")
	private final List<Group> generateGroupOfImage() {
		ArrayList<Group> listOfGroupOfImage= new ArrayList<>();
		List<ImageView> body=new ArrayList<ImageView>();
		List<ImageView> weapon=new ArrayList<ImageView>();
		List<ImageView> armor=new ArrayList<ImageView>();
		int imageIndex=0;
		
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
	}

	


	/**
	 * 
	 * @return the list of group of images that represent the unit
	 * on screen attacking
	 */
	@SuppressWarnings("unused")
	protected final List<Group> generateGroupOfImageAttack() {
		ArrayList<Group> listOfGroupOfImage= new ArrayList<>();
		List<ImageView> body=new ArrayList<ImageView>();
		List<ImageView> weapon=new ArrayList<ImageView>();
		List<ImageView> armor=new ArrayList<ImageView>();
		int imageIndex=0;
		
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
	
	
	@SuppressWarnings("unused")
	private final List<Group> generateGroupOfImageDeath() {
		ArrayList<Group> listOfGroupOfImage= new ArrayList<>();
		List<ImageView> body=new ArrayList<ImageView>();
		List<ImageView> weapon=new ArrayList<ImageView>();
		List<ImageView> armor=new ArrayList<ImageView>();
		int imageIndex=0;	
		
		for(Image image:unitFrame.getBodyDyingFrameList()) {	
			Group groupToAdd= new Group();
			body.add(new ImageView(unitFrame.getBodyDyingFrameList().get(imageIndex)));
			groupToAdd.getChildren().add(body.get(imageIndex));
			if(unitFrame.getArmorDyingFrameList().isPresent()) {
				if(unitFrame.getArmorDyingFrameList().get().size()-1>=imageIndex) {
					armor.add(new ImageView(unitFrame.getArmorDyingFrameList().get().get(imageIndex)));
					groupToAdd.getChildren().add(armor.get(imageIndex));
				}
			}
			if(unitFrame.getWeaponDyingFrameList().isPresent()) {
				if(unitFrame.getWeaponDyingFrameList().get().size()-1>=imageIndex) {
					weapon.add(new ImageView(unitFrame.getWeaponDyingFrameList().get().get(imageIndex)));
					groupToAdd.getChildren().add(weapon.get(imageIndex));
				}
			}
			listOfGroupOfImage.add(groupToAdd);
			
			imageIndex++;
		}
		return listOfGroupOfImage;
	}

	public Timeline getAttack() {
		return attack;
	}

	public Timeline getAttackEvent() {
		return attackEvent;
	}

	public List<Group> getAttackGroup() {
		return attackGroup;
	}
	
	public Timeline getDeath() {
		return death;
	}

	public List<Group> getDeathGroup() {
		return deathGroup;
	}

	public int getIndex() {
		return index;
	}
		

	public Boolean getPlayer() {
		return player;
	}
		
	public Timeline getSlideTime() {
		return slideTime;
	}

	public int getSlidingIncrement() {
		return slidingIncrement;
	}
	
	
	public SpriteUnit getSprite() {
		return this.sprite;
	}
	
	public int getStartingUnitX() {
		return startingUnitX;
	}
	public int getStartingUnitY() {
		return startingUnitY;
	}

	public Timeline getUnitCheck() {
		return unitCheck;
	}

	
	
	
	public FrameUnitManager getUnitFrame() {
		return unitFrame;
	}



	public Timeline getUnitStop() {
		return unitStop;
	}



	public Timeline getWalk() {
		return walk;
	}



	public List<Group> getWalkGroup() {
		return walkGroup;
	}



	public boolean isAttacking() {
		return attacking;
	}



	public void setAttackGroup(List<Group> attackGroup) {
		this.attackGroup = attackGroup;
	}



	public void setAttacking(boolean attacking) {
		this.attacking = attacking;
	}



	public void setSprite(SpriteUnit sprite) {
		this.sprite = sprite;
	}



	/**
	 * abstract method used to attack other unit or the tower
	 * 
	 * @param listEnemyUnitModel reference of the list of unit of the enemy player
	 * @param listUserUnitModel reference of the list of unit of the player
	 * @param spriteUnitOnScene reference of the list of sprite on scene
	 * @param spriteUnitEnemyOnScene reference of the list of enemy sprite on scene
	 * @param unitTemplateList player unit template list
	 * @param enemyTowerSprite reference of the enemy tower sprite
	 * @param enemyTower	reference of the enemy tower
	 */
	protected abstract void startAttack(List<Unit> listEnemyUnitModel, List<Unit> listUserUnitModel,
							List<AnimateUnitOnPane>spriteUnitOnScene,List<AnimateUnitOnPane>spriteUnitEnemyOnScene,List<UnitTemplate> 
							unitTemplateList, SpriteImpl enemyTowerSprite, Tower enemyTower);



	/**
	 * abstract method used to check if the unit can attack
	 * 
	 * @param spriteUnitOnScene reference of the list of sprite on scene
	 * @param spriteUnitEnemyOnScene reference of the list of enemy sprite on scene
	 * @param enemyTower reference of the enemy tower
	 */
	protected abstract void startAttackEvent(List<AnimateUnitOnPane>spriteUnitOnScene,List<AnimateUnitOnPane>spriteUnitEnemyOnScene,
								SpriteImpl enemyTower);



	/**
	 * method that initialize the timeline used to show the death of a sprite
	 * @param displayUnitPane the pane on which the sprite has to be removed
	 * 
	 */
	protected void startDeath(Pane displayUnitPane) {
		int frameIndex=0;
		for(Group group:deathGroup) {
			death.getKeyFrames().add(new KeyFrame(Duration.millis(1+frameIndex*600)
					,(ActionEvent event)->{	
						sprite.setImageGroup(group);		
			}));
			frameIndex=frameIndex+1;
		}
		frameIndex--;
		death.getKeyFrames().add(new KeyFrame(Duration.millis(10+frameIndex*600)
				,(ActionEvent event)->{
					displayUnitPane.getChildren().remove(sprite.getImageGroup());
					death.stop();
					
		}));
		
	}



	/**
	 * method to initialize the timeline used to
	 * translate the sprite on screen 
	 * @param speed: the speed constant of the unit 
	 * 
	 */
	protected void startSlide(double speed) {
		slideTime.getKeyFrames().add(new KeyFrame(Duration.millis(120*speed)
				,(ActionEvent event)->{
					if(stopSlide==false) { 
					this.setAttacking(false);
					slide = new TranslateTransition(
			                Duration.millis(120*speed), 
			                sprite.getImageGroup());
			        slide.setCycleCount(1);
			        slide.setFromX(sprite.getPositionX());
			        slide.setToX(sprite.getPositionX()+slidingIncrement);
					slide.play();
					}
			
		}));
		slideTime.getKeyFrames().add(new KeyFrame(Duration.millis(120*speed+5)
				,(ActionEvent event)->{
					if(stopSlide==false) 
					sprite.incrementPositionX(slidingIncrement);
					
		}));

		slideTime.play();
	}



	/**
	 * 	 * method that initialize the timeline used to periodically check the unit's status
	 * and reward the player who killed one
	 * 
	 * @param listUserUnitModel	reference of the list of unit of the player
	 * @param spriteUnitOnScene	reference of the list of sprite of the player on scene
	 * @param enemyMoney the money of the enemy
	 * 
	 */
	protected void startUnitCheck(List<Unit> listUserUnitModel,List<AnimateUnitOnPane>spriteUnitOnScene,MoneyValue enemyMoney)   {
	
		unitCheck.getKeyFrames().add(new KeyFrame(Duration.millis(50)
				,(ActionEvent event)->{
					try {
						if(!listUserUnitModel.get(spriteUnitOnScene.indexOf(this)).isAlive()) {
							enemyMoney.addMoney(listUserUnitModel.get(spriteUnitOnScene.indexOf(this)).getValue());
							listUserUnitModel.remove(spriteUnitOnScene.indexOf(this));
							spriteUnitOnScene.remove(this);
							slideTime.stop();
							walk.stop();
							attack.stop();
							attackEvent.stop();
							unitStop.stop();
							unitCheck.stop();
							death.play();
							
						}
				
					}catch(IndexOutOfBoundsException e) {}
		}));
		
		unitCheck.play();
		
	}



	/**method used to initialize a timeline to check periodically if the unit has to stop
	 * 
	 * @param spriteUnitOnScene reference of the list of sprite on scene
	 */
	protected void startUnitStop(List <AnimateUnitOnPane>spriteUnitOnScene) {
		unitStop.getKeyFrames().add(new KeyFrame(Duration.millis(100),(ActionEvent event)->{
			
			if(spriteUnitOnScene.indexOf(this)>=1) {
				if(sprite.intersects(spriteUnitOnScene.get(spriteUnitOnScene.indexOf(this)-1).getSprite())) {
					this.stopSlide=true;
					if(spriteUnitOnScene.get(spriteUnitOnScene.indexOf(this)-1).attacking==true||
							spriteUnitOnScene.get(spriteUnitOnScene.indexOf(this)-1).stopWalk==true) {
						this.walk.stop();
						this.stopWalk=true;
					}
        		}

				if(!sprite.intersects(spriteUnitOnScene.get(spriteUnitOnScene.indexOf(this)-1).getSprite())) {
						this.walk.play();
						this.stopWalk=false;
						this.stopSlide=false;
				}

			}
			if(spriteUnitOnScene.indexOf(this)==0) {
				this.stopSlide=false;
				this.stopWalk=false;
				this.walk.play();
			}
			
		}));	unitStop.play();	
	}



	/**
	 * method used to initialize a timeline to make the sprite make a walking animation
	 * @param speed the speed constant of the unit
	 */
	protected void startWalk(double speed) {
		
		walk.getKeyFrames().add(new KeyFrame(Duration.millis(700*speed)
				,(ActionEvent event)->{
			if(stopWalk==false)
				sprite.setImageGroup(walkGroup.get(1));	
		}));
		walk.getKeyFrames().add(new KeyFrame(Duration.millis(1400*speed),
				(ActionEvent event)->{
			sprite.setImageGroup(walkGroup.get(0));
		}));
		walk.play();
	}



	/**
	 * method used to stop all sprite possibile timeline
	 */
	public void stopGameAnimation() {
		this.attack.stop();
		this.attackEvent.stop();
		this.death.stop();
		this.unitStop.stop();
		this.walk.stop();
		this.slideTime.stop();
		this.unitCheck.stop();
		
	}


}
