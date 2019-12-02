package view.sprite;


import javafx.geometry.Rectangle2D;
import javafx.scene.Group;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class SpriteUnit extends SpriteImpl {
	
	private Image armor;
	private Image weapon;
	private Group frameGroup;
	private ImageView armorView;
	private ImageView weaponView;
	private ImageView bodyView;
	private boolean justCreated;
	private double weaponHeight;
	private double weaponWidth;
	private double weaponBoundaryHeight;
	private double weaponBoundaryWidth;
	private double weaponXOffset;
	private double weaponYOffset;
	
	
	protected static abstract class Init<T extends Init<T>> extends SpriteImpl.Init<T> {
		
		private Image armor;
		private Image weapon;
		private double weaponHeight;
		private double weaponWidth;
		private double weaponBoundaryHeight;
		private double weaponBoundaryWidth;
		private double weaponXOffset;
		private double weaponYOffset;
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
	    
	    public T weaponHeight(double weaponHeight) {
	    	this.weaponHeight=weaponHeight;
	    	return self();
	    }
	    
	    
	    public T weaponBoundaryHeight(double BoundaryHeight) {
	    	this.weaponBoundaryHeight=BoundaryHeight;
	    	return self();
	    }
	   
	    public T weaponWidth(double weaponWidth) {
	    	this.weaponWidth=weaponWidth;
	    	return self();
	    }
	    
	    public T weaponBoundaryWidth(double weaponBoundaryWidth) {
	    	this.weaponBoundaryWidth=weaponBoundaryWidth;
	    	return self();
	    }
	    
	    public T weaponXOffset(double weaponXOffset) {
	    	this.weaponXOffset=weaponXOffset;
	    	return self();
	    }
	    
	    public T weaponYOffset(double weaponYOffset) {
	    	this.weaponYOffset=weaponYOffset;
	    	return self();
	    }
	    
	    
	    public SpriteUnit build() {
	            return new SpriteUnit(this);
	    }
	}
	
	
    public static class SpriteUnitBuilder extends Init<SpriteUnitBuilder> {
    	
    	private Image armor;
    	private Image weapon;
    	private double weaponHeight;
    	private double weaponWidth;
    	private double weaponBoundaryHeight;
    	private double weaponBoundaryWidth;
    	private double weaponXOffset;
    	private double weaponYOffset;
        @Override
        protected SpriteUnitBuilder self() {
            return this;
        }
		public Image getArmor() {
			return armor;
		}
		public Image getWeapon() {
			return weapon;
		}
		public double getWeaponHeight() {
			return weaponHeight;
		}
		public double getWeaponWidth() {
			return weaponWidth;
		}
		public double getWeaponBoundaryHeight() {
			return weaponBoundaryHeight;
		}
		public double getWeaponBoundaryWidth() {
			return weaponBoundaryWidth;
		}
		public double getWeaponXOffset() {
			return weaponXOffset;
		}
		public double getWeaponYOffset() {
			return weaponYOffset;
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
			this.weaponHeight=init.weaponHeight;
			this.weaponWidth=init.weaponWidth;
			this.weaponBoundaryHeight=init.weaponBoundaryHeight;
			this.weaponBoundaryWidth=init.weaponBoundaryWidth;
			this.weaponXOffset=init.weaponXOffset;
			this.weaponYOffset=init.weaponYOffset;
		
	}
	
	public Rectangle2D distanceBundary() {
		return new Rectangle2D(this.getPositionX()+this.getxOffset()-60,
				this.getPositionY()+this.getyOffset(),
				this.getBoundaryWidth()+60,this.getBoundaryHeight());
	}
	
	
	
	public boolean mantainDistance(Sprite sprite) {
		return sprite.getBoundary().intersects(this.distanceBundary());
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

		this.setPositionX(position);
	}
	
	public void incrementPositionY(double y) {
		double position= this.getPositionX()+y;
		this.setPositionY(position);
	}
	
	
	public double getWeaponBoundaryHeight() {
		return weaponBoundaryHeight;
	}


	public double getWeaponBoundaryWidth() {
		return weaponBoundaryWidth;
	}


	public double getWeaponXOffset() {
		return weaponXOffset;
	}


	public double getWeaponYOffset() {
		return weaponYOffset;
	}


	@Override
	public void setPositionY(double y) {
		this.setPositionY(this.getPositionY()+y);
	}
	
	public Group getImageGroup() {
		return this.frameGroup;
	}
	
	public Rectangle2D getBoundaryWeapon() {
		return new Rectangle2D(this.getPositionX()+this.weaponXOffset,
								this.getPositionY()+this.weaponYOffset,
								this.weaponBoundaryWidth,this.weaponBoundaryHeight);
	}
	
	@Override
	public String toString() {
		return "SpriteUnit [armor=" + armor + ", weapon=" + weapon + "]";
	}
	
	public boolean weaponIntersectEnemy(Sprite sprite) {
		return sprite.getBoundary().intersects(this.getBoundaryWeapon());
		
	}

	public double getWeaponWidth() {
		return weaponWidth;
	}

	public double getWeaponHeight() {
		return weaponHeight;
	}

}
