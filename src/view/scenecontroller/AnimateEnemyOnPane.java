package view.scenecontroller;

import view.sprite.FrameUnitManager;
import view.sprite.SpriteUnit;
import view.sprite.SpriteUnit.SpriteUnitBuilder;

public class AnimateEnemyOnPane extends AnimateUnitOnPane {

	public AnimateEnemyOnPane(int index, Boolean player) {
		super(index, player);

	}

	@Override
	void initializeSprite() {
		
		FrameUnitManager unitFrame=this.frameUnitEnemyManagerList.get(index);
		SpriteUnitBuilder spriteBuilder;
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
		
		this.spriteUnitEnemyOnScene.add(spriteUnit);
		/*this.spriteUnitOnScene.element().getImageGroup().setTranslateX(STARTING_UNIT_X);
		this.spriteUnitOnScene.element().getImageGroup().setTranslateY(STARTING_UNIT_Y);
		this.unitDisplayPane.getChildren().add(this.spriteUnitOnScene.element().getImageGroup());*/
		this.moveMultipleEnemyUnits(index,this.frameUnitEnemyManagerList,spriteUnit);
	}

}
