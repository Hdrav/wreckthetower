package view.scenecontroller;

import java.util.ArrayList;
import java.util.List;

import gameModelStructure.Tower;
import gameModelStructure.Unit;
import gameModelStructure.UnitTemplate;
import javafx.animation.KeyFrame;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import view.sprite.SpriteImpl;

public class AnimateRangedUnitOnPane extends AnimateUnitOnPane {
	private double speed;
	private List<Group> chargingGroup;
	private List<Group> arrow;
	private Double walkTimeStop;

	public AnimateRangedUnitOnPane(int index, Boolean player, int startingUnitX, int startingUnitY) {
		super(index, player, startingUnitX, startingUnitY);
		this.chargingGroup=new ArrayList<>();
		this.chargingGroup=this.generateGroupOfImageCharging();
		this.arrow=this.generateGroupOfImageArrow();
	}

	@Override
	protected void startAttack(List<Unit> listEnemyUnitModel, List<Unit> listUserUnitModel,
			List<AnimateUnitOnPane> spriteUnitOnScene,List<AnimateUnitOnPane> spriteUnitEnemyOnScene, List<UnitTemplate> unitTemplateList, SpriteImpl enemyTowerSprite,
			Tower enemyTower) {

			this.getAttack().getKeyFrames().add(new KeyFrame(Duration.millis(1*450)
					,(ActionEvent event)->{
						if(this.getSprite().intersects(spriteUnitEnemyOnScene.get(0).getSprite())){
							this.setAttacking(true);
							this.getSlideTime().stop();
							System.out.println("lo tocca");
							this.getSprite().setImageGroup(this.chargingGroup.get(0));
						}else {
							if(walkTimeStop<=speed*700) {
								this.getSprite().setImageGroup(this.chargingGroup.get(0));
							}
							else {
								this.getSprite().setImageGroup(this.chargingGroup.get(1));
							}
						}
					}));
			this.getAttack().getKeyFrames().add(new KeyFrame(Duration.millis(2*450)
					,(ActionEvent event)->{	
						if(this.getSprite().intersects(spriteUnitEnemyOnScene.get(0).getSprite())){
							this.getSlideTime().stop();
							System.out.println("lo tocca2");
							this.getSprite().setImageGroup(this.getAttackGroup().get(1));
						}else {
							if(walkTimeStop<=speed*700)
								this.getSprite().setImageGroup(this.getAttackGroup().get(0));
							else {
								this.getSprite().setImageGroup(this.getAttackGroup().get(1));
							}
						}
					}));
			
				
			this.getAttack().getKeyFrames().add(new KeyFrame(Duration.millis(3*450)
				,(ActionEvent event)->{
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
				}));
		this.getAttack().getKeyFrames().add(new KeyFrame(Duration.millis(4*450+1000*
				unitTemplateList.get(this.getIndex()).getWeapon().getWeaponDelay())
				,(ActionEvent event)->{
					if(!this.getSprite().intersects(spriteUnitEnemyOnScene.get(0).getSprite())){
						//System.out.println("prova");
						this.getWalk().play();
					}
				/*	if(!this.getSprite().intersects(spriteUnitEnemyOnScene.get(0).getSprite())) {
						this.getWalk().stop();
					}*/
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
						//this.getSlideTime().stop();
						getAttack().play();
					}else if(spriteUnitEnemyOnScene.size()==0) {				
						getAttack().stop();
						this.getSlideTime().play();
						getWalk().play();
	
					}
					else {
						if(!getSprite().weaponIntersectEnemy(spriteUnitEnemyOnScene.get(0).getSprite())) {
							this.walkTimeStop=getWalk().getCurrentTime().toMillis();
							getAttack().stop();
							getSlideTime().play();
							getWalk().play();
						}
						if(getSprite().weaponIntersectEnemy(spriteUnitEnemyOnScene.get(0).getSprite())) {						
							getWalk().stop();
						//	getSlideTime().stop();
							getAttack().play();
						}
					}
				}));	
		getAttackEvent().play();
	}
	
	@Override
	protected List<Group> generateGroupOfImageAttack() {
		ArrayList<Group> listOfGroupOfImage= new ArrayList<>();
		List<ImageView> body=new ArrayList<ImageView>();
		List<ImageView> weapon=new ArrayList<ImageView>();
		List<ImageView> armor=new ArrayList<ImageView>();
		int imageIndex=0;
		
		for(Image image:getUnitFrame().getBodyAttackFrameList()) {
			Group groupToAdd= new Group();
			body.add(new ImageView(getUnitFrame().getBodyAttackFrameList().get(imageIndex)));
			groupToAdd.getChildren().add(body.get(imageIndex));
			if(getUnitFrame().getArmorAttackFrameList().isPresent()) {
				armor.add(new ImageView(getUnitFrame().getArmorAttackFrameList().get().get(imageIndex)));
				groupToAdd.getChildren().add(armor.get(imageIndex));
			}
			if(getUnitFrame().getWeaponAttackFrameList().isPresent()) {
				weapon.add(new ImageView(getUnitFrame().getWeaponAttackFrameList().get().get(imageIndex)));
				groupToAdd.getChildren().add(weapon.get(imageIndex));
			}
			listOfGroupOfImage.add(groupToAdd);
			
			imageIndex++;
		}
		return listOfGroupOfImage;
	}

	
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
	
	
	protected List<Group> generateGroupOfImageArrow() {
		ArrayList<Group> listOfGroupOfImage= new ArrayList<>();
		List<ImageView> arrow=new ArrayList<ImageView>();
		int imageIndex=0;
		
	/*	for(Image image:getUnitFrame().getArrowFrameList().get()) {
			Group groupToAdd= new Group();
			arrow.add(new ImageView(getUnitFrame().getBodyChargingFrameList().get().get(imageIndex)));
			groupToAdd.getChildren().add(arrow.get(imageIndex));
			listOfGroupOfImage.add(groupToAdd);
			
			imageIndex++;
		}
		return listOfGroupOfImage;*/
		return null;
	}
	
	
	@Override
	public void executeSpriteStrategy(double speed,List<Unit> listUserUnitModel, List<Unit> listEnemyUnitModel,
			List<AnimateUnitOnPane> spriteUnitOnScene, List<AnimateUnitOnPane> spriteUnitEnemyOnScene,
			SpriteImpl enemyTowerSprite, Tower enemyTower,List<UnitTemplate> unitTemplateList,
			Pane displayUnitPane) {
		this.speed=speed;
		this.startWalk(speed);
		this.startSlide(speed);
		this.startDeath(displayUnitPane);
		this.startUnitCheck(listUserUnitModel, spriteUnitOnScene);
		this.startUnitStop(spriteUnitOnScene);
		this.startAttack(listEnemyUnitModel, listUserUnitModel, spriteUnitOnScene,spriteUnitEnemyOnScene,
				unitTemplateList, enemyTowerSprite, enemyTower);
		this.startAttackEvent(spriteUnitOnScene, spriteUnitEnemyOnScene, enemyTowerSprite);
		
}

}
