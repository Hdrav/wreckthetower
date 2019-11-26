package view.sprite;

import java.util.List;

import gameModelStructure.Tower;
import gameModelStructure.Unit;
import gameModelStructure.UnitTemplate;
import javafx.animation.KeyFrame;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.util.Duration;
import view.scenecontroller.AnimateUnitOnPane;

public class AnimateMeleeUnitOnPane extends AnimateUnitOnPane {

	public AnimateMeleeUnitOnPane(int index, Boolean player, int startingUnitX, int startingUnitY) {
		super(index, player, startingUnitX, startingUnitY);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected  void startAttack(List<Unit> listEnemyUnitModel, List<Unit> listUserUnitModel,
			List<AnimateUnitOnPane>spriteUnitOnScene,List<AnimateUnitOnPane>spriteUnitEnemyOnScene, List<UnitTemplate> 
			unitTemplateList, SpriteImpl enemyTowerSprite, Tower enemyTower) {
		int frameIndex=1;
		for(Group group:this.getAttackGroup()) {
			this.getAttack().getKeyFrames().add(new KeyFrame(Duration.millis(frameIndex*450)
					,(ActionEvent event)->{	
						this.setAttacking(true);
						this.getSprite().setImageGroup(group);		
					}));
			frameIndex=frameIndex+1;
			}	
			this.getAttack().getKeyFrames().add(new KeyFrame(Duration.millis(frameIndex*450)
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
		this.getAttack().getKeyFrames().add(new KeyFrame(Duration.millis(450+frameIndex*450+1000*
				unitTemplateList.get(this.getIndex()).getWeapon().getWeaponDelay())
				,(ActionEvent event)->{		
				}));

	}

	@Override
	protected void startAttackEvent(List<AnimateUnitOnPane>spriteUnitOnScene,List<AnimateUnitOnPane>spriteUnitEnemyOnScene,
					SpriteImpl enemyTower) {
		getAttackEvent().getKeyFrames().add(new KeyFrame(Duration.millis(100)
				,(ActionEvent event)->{
	
					if(getSprite().weaponIntersectEnemy(enemyTower)) {
						getWalk().stop();
						this.getSlideTime().stop();
						getAttack().play();
					}else if(spriteUnitEnemyOnScene.size()==0) {				
						getAttack().stop();
						this.getSlideTime().play();
						getWalk().play();
	
					}
					else {
						if(!getSprite().weaponIntersectEnemy(spriteUnitEnemyOnScene.get(0).getSprite())) {
							getAttack().stop();
							getSlideTime().play();
							getWalk().play();
						}
						if(getSprite().weaponIntersectEnemy(spriteUnitEnemyOnScene.get(0).getSprite())) {						
							getWalk().stop();
							getSlideTime().stop();
							getAttack().play();
						}
					}
				}));	
		getAttackEvent().play();

	}



	
	
	
}
