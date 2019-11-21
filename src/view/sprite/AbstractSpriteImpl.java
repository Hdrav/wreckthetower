package view.sprite;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import view.sprite.SpriteImpl.Init;

public abstract class AbstractSpriteImpl implements Sprite {
	
	private static int INDEX_STATIC_SPRITE=0;
    private Image mainImage;
    private double positionX;
    private double positionY;
    private double width;
    private double height;

    
    public AbstractSpriteImpl(Image mainImage,double positionX,double positionY,
    				  double width,double height){
    	
    	this.mainImage=mainImage;
    	this.positionX=positionX;
    	this.positionY=positionY;
    	this.width=width;
    	this.height=height;
    	
    }


    
	public Image getMainImage() {
		return this.mainImage;
	}
	
	public double getPositionX() {
		return this.positionX;
	}


	public double getPositionY() {
		return this.positionY;
	}


	public double getWidth() {
		return this.width;
	}

	public double getHeight() {
		return this.height;
	}

	public void setImage(Image image) {
		this.mainImage=image;
	}
	public  void render(GraphicsContext gc) {
		gc.drawImage(mainImage, positionX, positionY);
	}
	
	public abstract Rectangle2D getBoundary();
		
	
	public boolean intersects(Sprite sprite) {
		 return sprite.getBoundary().intersects(this.getBoundary());
	}

}
