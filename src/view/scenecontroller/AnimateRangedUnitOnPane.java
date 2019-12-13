package view.scenecontroller;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import view.sprite.SpriteImpl;

public class AnimateRangedUnitOnPane extends AnimateUnitOnPane {
	/**
	 *class AnimateRangedUnitOnPane is a AnimateUnitOnPane
	 *specialized for the units that attack from big distances  
	 * 
	 */
	
	private double speed;
	private List<Group> chargingGroup;
	private List<Group> arrow;
	private double walkTimeStop=0;
	private Timeline arrowFly=new Timeline();

	public AnimateRangedUnitOnPane(int index, Boolean player, int startingUnitX, int startingUnitY) {
		super(index, player, startingUnitX, startingUnitY);
		this.chargingGroup=new ArrayList<>();
		this.chargingGroup=this.generateGroupOfImageCharging();
		this.arrowFly.setCycleCount(Animation.INDEFINITE);
	}

	
	
	
	
	@Override
	protected void startAttack(List<Unit> listEnemyUnitModel, List<Unit> listUserUnitModel,
			List<AnimateUnitOnPane> spriteUnitOnScene,List<AnimateUnitOnPane> spriteUnitEnemyOnScene, List<UnitTemplate> unitTemplateList, SpriteImpl enemyTowerSprite,
			Tower enemyTower) {

			this.getAttack().getKeyFrames().add(new KeyFrame(Duration.millis(1*450)
					,(ActionEvent event)->{
						this.setAttacking(true);
							if(walkTimeStop<=speed*700) {
								this.getSprite().setImageGroup(this.chargingGroup.get(0));
							}
							else {
								this.getSprite().setImageGroup(this.chargingGroup.get(1));
							}
						
						
					}));
			this.getAttack().getKeyFrames().add(new KeyFrame(Duration.millis(2*450)
					,(ActionEvent event)->{	
							if(walkTimeStop<=speed*700)
								this.getSprite().setImageGroup(this.getAttackGroup().get(0));
							else {
								this.getSprite().setImageGroup(this.getAttackGroup().get(1));
							}
					}));
			
				
			this.getAttack().getKeyFrames().add(new KeyFrame(Duration.millis(3*450)
				,(ActionEvent event)->{
					try {
				
					if(this.getSprite().weaponIntersectEnemy(enemyTowerSprite)) {
						enemyTower.reduceHealth(
								listUserUnitModel.get(spriteUnitOnScene.indexOf(this)).getDamage());
						this.getSprite().setImageGroup(this.getWalkGroup().get(0));
					}
					else {
						listEnemyUnitModel.get(0).reduceHealth(
								listUserUnitModel.get(spriteUnitOnScene.indexOf(this)).getDamage());
						this.getSprite().setImageGroup(this.getWalkGroup().get(0));
					}
				}catch(IndexOutOfBoundsException e) {}
				}));
		this.getAttack().getKeyFrames().add(new KeyFrame(Duration.millis(4*450+1000*
				unitTemplateList.get(this.getIndex()).getWeapon().getWeaponDelay())
				,(ActionEvent event)->{
				}));
	
	}

	@Override
	protected void startAttackEvent(List<AnimateUnitOnPane> spriteUnitOnScene,
			List<AnimateUnitOnPane> spriteUnitEnemyOnScene, SpriteImpl enemyTower) {
		
		getAttackEvent().getKeyFrames().add(new KeyFrame(Duration.millis(100)
				,(ActionEvent event)->{
	
					if(getSprite().weaponIntersectEnemy(enemyTower)) {
						this.walkTimeStop=getWalk().getCurrentTime().toMillis();
						getWalk().stop();
						this.getSlideTime().stop();
						getAttack().play();
					}else if(spriteUnitEnemyOnScene.size()==0) {				
						getAttack().stop();
						this.getSlideTime().play();
						getWalk().play();
	
					}
					else {
						if(this.getSprite().intersects(spriteUnitEnemyOnScene.get(0).getSprite())){
							this.setAttacking(true);
							this.getSlideTime().stop();
						}
						
						if(!getSprite().weaponIntersectEnemy(spriteUnitEnemyOnScene.get(0).getSprite())) {
							this.walkTimeStop=getWalk().getCurrentTime().toMillis();
							getAttack().stop();
							getSlideTime().play();
							getWalk().play();
						}
						if(getSprite().weaponIntersectEnemy(spriteUnitEnemyOnScene.get(0).getSprite())) {						
							getWalk().stop();
							getAttack().play();
						}
					}
				}));	
		getAttackEvent().play();
	}
	

	/**
	 * 
	 * @return the list of group of images that represent the unit
	 * on screen charging the arrow 
	 */
	@SuppressWarnings("unused")
	protected List<Group> generateGroupOfImageCharging() {
		ArrayList<Group> listOfGroupOfImage= new ArrayList<>();
		List<ImageView> body=new ArrayList<ImageView>();
		List<ImageView> weapon=new ArrayList<ImageView>();
		List<ImageView> armor=new ArrayList<ImageView>();
		int imageIndex=0;
		
		for(Image image:getUnitFrame().getBodyChargingFrameList().get()) {
			Group groupToAdd= new Group();
			body.add(new ImageView(getUnitFrame().getBodyChargingFrameList().get().get(imageIndex)));
			groupToAdd.getChildren().add(body.get(imageIndex));
			if(getUnitFrame().getArmorAttackFrameList().isPresent()) {
				armor.add(new ImageView(getUnitFrame().getArmorChargingFrameList().get().get(imageIndex)));
				groupToAdd.getChildren().add(armor.get(imageIndex));
			}
			if(getUnitFrame().getWeaponAttackFrameList().isPresent()) {
				weapon.add(new ImageView(getUnitFrame().getWeaponChargingFrameList().get().get(imageIndex)));
				groupToAdd.getChildren().add(weapon.get(imageIndex));
			}
			listOfGroupOfImage.add(groupToAdd);
			
			imageIndex++;
		}
		return listOfGroupOfImage;
	}
	
	/*
	 * still not implemented: the list of group of images of the arrow 
	 * 
	@SuppressWarnings("unused")
	protected List<Group> generateGroupOfImageArrow() {
		ArrayList<Group> listOfGroupOfImage= new ArrayList<>();
		List<ImageView> arrow=new ArrayList<ImageView>();
		int imageIndex=0;
		
		for(Image image:getUnitFrame().getArrowFrameList().get()) {
			Group groupToAdd= new Group();
			arrow.add(new ImageView(getUnitFrame().getBodyChargingFrameList().get().get(imageIndex)));
			groupToAdd.getChildren().add(arrow.get(imageIndex));
			listOfGroupOfImage.add(groupToAdd);
			
			imageIndex++;
		}
		return listOfGroupOfImage;
		
	}*/
	
	
	@Override
	public void executeSpriteStrategy(double speed,List<Unit> listUserUnitModel, List<Unit> listEnemyUnitModel,
			List<AnimateUnitOnPane> spriteUnitOnScene, List<AnimateUnitOnPane> spriteUnitEnemyOnScene,
			MoneyValue enemyMoney,
			SpriteImpl enemyTowerSprite, Tower enemyTower,List<UnitTemplate> unitTemplateList,
			Pane displayUnitPane) {
		this.speed=speed;
		this.startWalk(speed);
		this.startSlide(speed);
		this.startDeath(displayUnitPane);
		this.startUnitCheck(listUserUnitModel, spriteUnitOnScene,enemyMoney);
		this.startUnitStop(spriteUnitOnScene);
		this.startAttack(listEnemyUnitModel, listUserUnitModel, spriteUnitOnScene,spriteUnitEnemyOnScene,
				unitTemplateList, enemyTowerSprite, enemyTower);
		this.startAttackEvent(spriteUnitOnScene, spriteUnitEnemyOnScene, enemyTowerSprite);
		
	}





	public List<Group> getArrow() {
		return arrow;
	}

}
