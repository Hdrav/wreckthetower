package view.sprite;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import view.sprite.BasicUnitFrameMoving;
import view.sprite.SpriteImpl.Init;
import view.sprite.SpriteImpl.SpriteImplBuilder;

public class SpriteUnit extends SpriteImpl {
	
	private Image armor;
	private Image weapon;
	private Group frameGroup;
	private ImageView armorView;
	private ImageView weaponView;
	private ImageView bodyView;
	private boolean justCreated;
	
	protected static abstract class Init<T extends Init<T>> extends SpriteImpl.Init<T> {
		
		private Image armor;
		private Image weapon;
		/*
		 * abstract method to return the appropriate "this"
		 * depending of the Builder. every builder that extends "Init<T extends Init<T>>" has to
		 * implement it
		 * */
	    protected abstract T self(); 

	    public T armor(Image image) {
	    	this.armor=image;
	    	return self();
	    }
	    
	    public T weapon(Image image) {
	    	this.weapon=image;
	    	return self();
	    }
	    
	    public SpriteUnit build() {
	            return new SpriteUnit(this);
	    }
	}
	
	
    public static class SpriteUnitBuilder extends Init<SpriteUnitBuilder> {
    	
    	private Image armor;
    	private Image weapon;
    	
        @Override
        protected SpriteUnitBuilder self() {
            return this;
        }
    }

	
	
/*	public SpriteImpl(Image mainImage, double positionX, double positionY, double width, double height,
					 double boundaryWidth,double boundaryHeight,double xOffset,double yOffset) {*/
		
	
	
	
	protected SpriteUnit(Init<?>init) {
		super(init);
	//	if(this.armor!=null)
			this.armor=init.armor;
		//if(this.weapon!=null)
			this.weapon=init.weapon;
			this.bodyView=new ImageView(this.getMainImage());
			this.armorView=new ImageView(this.armor);
			this.weaponView=new ImageView(this.weapon);
			this.frameGroup=new Group(this.bodyView,this.armorView,this.weaponView);
			this.justCreated=true;
		
	}
	
	
	public boolean isJustCreated() {
		return this.justCreated;
	}
	
	
	public void setImageGroup(Group frameGroup) {
		this.justCreated=false;
		this.frameGroup.getChildren().setAll(frameGroup);
	}
	
	
	public void incrementPositionX(double x) {
		double position= this.getPositionX()+x;
	//	System.out.println(position);
		this.setPositionX(position);
	}
	
	public void incrementPositionY(double y) {
		double position= this.getPositionX()+y;
		this.setPositionY(position);
	}
	
	
	@Override
	public void setPositionY(double y) {
		this.setPositionY(this.getPositionY()+y);
	}
	
	public Group getImageGroup() {
		return this.frameGroup;
	}
	
	
	@Override
	public String toString() {
		return "SpriteUnit [armor=" + armor + ", weapon=" + weapon + "]";
	}
	
	

}
