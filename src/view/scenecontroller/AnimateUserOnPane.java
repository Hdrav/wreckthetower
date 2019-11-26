package view.scenecontroller;

import view.sprite.SpriteUnit;
import view.sprite.SpriteUnit.SpriteUnitBuilder;


public class AnimateUserOnPane extends AnimateUnitOnPane{
	
	private static int STARTING_UNIT_USER_X=53;
	private static int STARTING_UNIT_USER_Y=505;

	public AnimateUserOnPane(int index, Boolean player) {
		super(index, player);
		// TODO Auto-generated constructor stub
	}

	@Override
	void initializeSprite() {
		SpriteUnitBuilder spriteBuilder;
		spriteBuilder=new SpriteUnit.SpriteUnitBuilder().mainImage(this.getUnitFrame().getBodyMovingFrameList().get(0))
				.boundaryHeight(this.getUnitFrame().getBoundaryHeight())
				.boundaryWidth(this.getUnitFrame().getBoundaryWidth())
				.xOffset(this.getUnitFrame().getxOffset())
				.yOffset(this.getUnitFrame().getyOffset())
				.positionX(STARTING_UNIT_USER_X).positionY(STARTING_UNIT_USER_Y)
				.weaponBoundaryHeight(this.getUnitFrame().getWeaponBoundaryHeight())
				.weaponBoundaryWidth(this.getUnitFrame().getWeaponBoundaryWidth())
				.weaponXOffset(this.getUnitFrame().getWeaponXOffset())
				.weaponYOffset(this.getUnitFrame().getWeaponYOffset());
				
		if(this.getUnitFrame().getArmorMovingFrameList().isPresent()) {
			spriteBuilder=spriteBuilder.armor(this.getUnitFrame().getArmorMovingFrameList().get().get(0));
		}
		
		if(this.getUnitFrame().getWeaponMovingFrameList().isPresent()) {
			spriteBuilder=spriteBuilder.weapon(this.getUnitFrame().getWeaponMovingFrameList().get().get(0));
		}
		this.setSprite(spriteBuilder.build());
	}
	

}
