package view.sprite;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

public class SpriteStatic extends AbstractSpriteImpl {

	private static double BOUNDARY_WIDTH=120;
	private static double BOUNDARY_HEIGHT=240;
	private static double X_OFFSET=0;
	private static double Y_OFFSET=0;

	
	public SpriteStatic(Image mainImage, double positionX, double positionY, double width, double height) {
		super(mainImage, positionX, positionY, width, height);
	}

	
	
	@Override
	public Rectangle2D getBoundary() {
		return new Rectangle2D(this.getPositionX()+SpriteStatic.X_OFFSET,
				this.getPositionY()+SpriteStatic.Y_OFFSET,
				SpriteStatic.BOUNDARY_WIDTH,SpriteStatic.BOUNDARY_HEIGHT);
	}

}
