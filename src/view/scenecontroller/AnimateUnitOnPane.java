package view.scenecontroller;

import java.util.ArrayList;
import java.util.List;

import gameModelStructure.Tower;
import gameModelStructure.Unit;
import gameModelStructure.UnitTemplate;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import view.sprite.FrameUnitBuilder;
import view.sprite.FrameUnitEnemyBuilder;
import view.sprite.FrameUnitManager;
import view.sprite.SpriteImpl;
import view.sprite.SpriteUnit;
import view.sprite.SpriteUnit.SpriteUnitBuilder;

public abstract class AnimateUnitOnPane {
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
	
	
	
	public void setSprite(SpriteUnit sprite) {
		this.sprite = sprite;
	}

	public FrameUnitManager getUnitFrame() {
		return unitFrame;
	}

	


	public void setAttackGroup(List<Group> attackGroup) {
		this.attackGroup = attackGroup;
	}
	
	
	private List<Group> generateGroupOfImageDeath() {
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


	protected List<Group> generateGroupOfImageAttack() {
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


	private List<Group> generateGroupOfImage() {
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


	protected void startSlide(double speed) {
		KeyFrame stopFrame= new KeyFrame(Duration.millis(120*speed+650),(ActionEvent event)->{}); 
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


	protected void startUnitCheck(List<Unit> listUserUnitModel,List<AnimateUnitOnPane>spriteUnitOnScene) {
		unitCheck.getKeyFrames().add(new KeyFrame(Duration.millis(50)
				,(ActionEvent event)->{
						if(!listUserUnitModel.get(spriteUnitOnScene.indexOf(this)).isAlive()) {
							listUserUnitModel.remove(spriteUnitOnScene.indexOf(this));
							spriteUnitOnScene.remove(this);
							System.out.println("ahia");
							slideTime.stop();
							walk.stop();
							attack.stop();
							attackEvent.stop();
							unitStop.stop();
							unitCheck.stop();
							death.play();
						}
			
		}));
		unitCheck.play();
		
	}


	protected abstract void startAttack(List<Unit> listEnemyUnitModel, List<Unit> listUserUnitModel,
							List<AnimateUnitOnPane>spriteUnitOnScene,List<AnimateUnitOnPane>spriteUnitEnemyOnScene,List<UnitTemplate> 
							unitTemplateList, SpriteImpl enemyTowerSprite, Tower enemyTower);
		


	protected abstract void startAttackEvent(List<AnimateUnitOnPane>spriteUnitOnScene,List<AnimateUnitOnPane>spriteUnitEnemyOnScene,
								SpriteImpl enemyTower);
		


	protected void startUnitStop(List <AnimateUnitOnPane>spriteUnitOnScene) {
		unitStop.getKeyFrames().add(new KeyFrame(Duration.millis(100),(ActionEvent event)->{
			
			if(spriteUnitOnScene.indexOf(this)>=1) {
				if(sprite.intersects(spriteUnitOnScene.get(spriteUnitOnScene.indexOf(this)-1).getSprite())) {
					this.stopSlide=true;
					if(spriteUnitOnScene.get(spriteUnitOnScene.indexOf(this)-1).attacking==true||
							spriteUnitOnScene.get(spriteUnitOnScene.indexOf(this)-1).stopWalk==true) {
						this.walk.stop();
						this.stopWalk=true;
						sprite.setImageGroup(walkGroup.get(0));
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
	
	
	public SpriteUnit getSprite() {
		return this.sprite;
	}
	
	public void executeSpriteStrategy(double speed,List<Unit> listUserUnitModel, List<Unit> listEnemyUnitModel,
									List<AnimateUnitOnPane> spriteUnitOnScene, List<AnimateUnitOnPane> spriteUnitEnemyOnScene,
									SpriteImpl enemyTowerSprite, Tower enemyTower,List<UnitTemplate> unitTemplateList,
									Pane displayUnitPane) {
		this.startWalk(speed);
		this.startSlide(speed);
		this.startDeath(displayUnitPane);
		this.startUnitCheck(listUserUnitModel, spriteUnitOnScene);
		this.startUnitStop(spriteUnitOnScene);
		this.startAttack(listEnemyUnitModel, listUserUnitModel, spriteUnitOnScene, spriteUnitEnemyOnScene,
				unitTemplateList, enemyTowerSprite, enemyTower);
		this.startAttackEvent(spriteUnitOnScene, spriteUnitEnemyOnScene, enemyTowerSprite);
		
	}

	public List<Group> getDeathGroup() {
		return deathGroup;
	}

	
	
	
	public int getStartingUnitX() {
		return startingUnitX;
	}



	public int getStartingUnitY() {
		return startingUnitY;
	}



	public Boolean getPlayer() {
		return player;
	}



	public int getIndex() {
		return index;
	}



	public List<Group> getWalkGroup() {
		return walkGroup;
	}



	public List<Group> getAttackGroup() {
		return attackGroup;
	}



	public Timeline getWalk() {
		return walk;
	}



	public Timeline getUnitStop() {
		return unitStop;
	}



	public Timeline getAttackEvent() {
		return attackEvent;
	}



	public Timeline getAttack() {
		return attack;
	}



	public Timeline getUnitCheck() {
		return unitCheck;
	}



	public Timeline getDeath() {
		return death;
	}



	public Timeline getSlideTime() {
		return slideTime;
	}



	public int getSlidingIncrement() {
		return slidingIncrement;
	}



	public boolean isAttacking() {
		return attacking;
	}



	public void setAttacking(boolean attacking) {
		this.attacking = attacking;
	}


}
