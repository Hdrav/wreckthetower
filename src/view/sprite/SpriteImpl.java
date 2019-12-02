package view.sprite;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class SpriteImpl implements Sprite {
	
	private static double STANDARD_SPRITE_WIDTH=120;
	private static double STANDARD_SPRITE_HEIGHT=120;
	private static double STANDARD_TOWER_SPRITE_WIDTH=120;
	private static double STANDARD_TOWER_SPRITE_HEIGHT=240;
	private static double BOUNDARY_WIDTH_TOWER=120;
	private static double BOUNDARY_HEIGHT_TOWER=240;
	
	
	
	
	private  double boundaryWidth;
	private  double boundaryHeight;
	private  double xOffset;
	private  double yOffset;
	private  boolean enemy;
    private Image mainImage;
    private double positionX;
    private double positionY;
    private double width;
    private double height;

	
	
	
	
	protected static abstract class Init<T extends Init<T>> {
		
	    private double positionX;
	    private double positionY;
	    private double width;
	    private double height;
	    private Image mainImage;
		
		private  double boundaryWidth;
		private  double boundaryHeight;
		private  double xOffset;
		private  double yOffset;
		private  boolean enemy;
		/*
		 * abstract method to return the appropriate "this"
		 * depending of the Builder. every builder that extends "Init<T extends Init<T>>" has to
		 * implement it
		 * */
	    protected abstract T self(); 

	    public T boundaryWidth(double boundaryWidth) {
	    	this.boundaryWidth = boundaryWidth;
	        return self();
	    }
	    
	    public T boundaryHeight(double boundaryHeight) {
	    	this.boundaryHeight=boundaryHeight;
	    	return self();
	    }
	    
	    public T xOffset(double xOffset) {
	    	this.xOffset=xOffset;
	    	return self();
	    }
	    
	    public T yOffset(double yOffset) {
	    	this.yOffset=yOffset;
	    	return self();
	    }
	    
	    public T mainImage(Image mainImage) {
	    	this.mainImage=mainImage;
	    	return self();
	    }
	    
	    public T positionX(double positionX) {
	    	this.positionX=positionX;
	    	return self();
	    }
	    
	    public T positionY(double positionY) {
	    	this.positionY=positionY;
	    	return self();
	    }
	    
	    public T width(double width) {
	    	this.width=width;
	    	return self();
	    }
	    
	    public T height(double height) {
	    	this.height=height;
	    	return self();
	    }
	    
	    public T enemy(boolean enemy) {
	    	this.enemy=enemy;
	    	return self();
	    }
	    
	    public SpriteImpl build() {
	            return new SpriteImpl(this);
	    }
	}
	
	
    public static class SpriteImplBuilder extends Init<SpriteImplBuilder> {
        @Override
        protected SpriteImplBuilder self() {
            return this;
        }
    }

	
	
	public SpriteImpl(Init<?>init) {
		this.setBoundaryHeight(init.boundaryHeight);
		this.setBoundaryWidth(init.boundaryWidth);
		this.height=init.height;
		this.mainImage=init.mainImage;
		this.positionX=init.positionX;
		this.positionY=init.positionY;
		this.width=init.width;
		this.xOffset=init.xOffset;
		this.setyOffset(init.yOffset);
		this.enemy=init.enemy;
	}

	
	
	public Rectangle2D getBoundary() {
		return new Rectangle2D(this.getPositionX()+this.getxOffset(),
								this.getPositionY()+this.getyOffset(),
								this.getBoundaryWidth(),this.getBoundaryHeight());
	}



	public static double getBOUNDARY_WIDTH_TOWER() {
		return BOUNDARY_WIDTH_TOWER;
	}


	public static double getBOUNDARY_HEIGHT_TOWER() {
		return BOUNDARY_HEIGHT_TOWER;
	}



	public static double getSTANDARD_SPRITE_WIDTH() {
		return STANDARD_SPRITE_WIDTH;
	}



	public static double getSTANDARD_SPRITE_HEIGHT() {
		return STANDARD_SPRITE_HEIGHT;
	}



	public static double getSTANDARD_TOWER_SPRITE_WIDTH() {
		return STANDARD_TOWER_SPRITE_WIDTH;
	}



	public static double getSTANDARD_TOWER_SPRITE_HEIGHT() {
		return STANDARD_TOWER_SPRITE_HEIGHT;
	}


	public boolean isEnemy() {
		return this.enemy;
	}
	
	@Override
	public Image getMainImage() {
		
		return this.mainImage;
	}

	
	
	public void setMainImage(Image mainImage) {
		this.mainImage = mainImage;
	}



	@Override
	public double getPositionX() {
		return this.positionX;
	}



	@Override
	public double getPositionY() {
		return this.positionY;
	}



	@Override
	public double getWidth() {
		return this.width;
	}



	@Override
	public double getHeight() {
		return this.height;
	}



	@Override
	public void render(GraphicsContext gc) {
		gc.drawImage(mainImage, positionX, positionY);
		
	}



	public void setPositionX(double positionX) {
		this.positionX = positionX;
	}



	public void setPositionY(double positionY) {
		this.positionY = positionY;
	}

	public void moveHorizontally(double incX, double incY) {
		this.positionX=this.positionX+incX;
		this.positionY=this.positionY+incY;
	}

	@Override
	public boolean intersects(Sprite sprite) {
		return sprite.getBoundary().intersects(this.getBoundary());
	}



	public double getyOffset() {
		return yOffset;
	}



	public void setyOffset(double yOffset) {
		this.yOffset = yOffset;
	}



	public double getBoundaryWidth() {
		return boundaryWidth;
	}



	public void setBoundaryWidth(double boundaryWidth) {
		this.boundaryWidth = boundaryWidth;
	}



	public double getBoundaryHeight() {
		return boundaryHeight;
	}



	public void setBoundaryHeight(double boundaryHeight) {
		this.boundaryHeight = boundaryHeight;
	}



	public double getxOffset() {
		return xOffset;
	}


		
	

}
