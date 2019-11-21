package view.sprite;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public interface Sprite {
	
	
	public Image getMainImage();
	public double getPositionX();
	public double getPositionY();
	public double getWidth();
	public double getHeight();
	public void render(GraphicsContext gc);
	public Rectangle2D getBoundary();
	public boolean intersects(Sprite spr);
}
