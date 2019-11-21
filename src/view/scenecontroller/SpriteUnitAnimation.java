package view.scenecontroller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import view.sprite.FrameUnitBuilder;
import view.sprite.FrameUnitManager;
import view.sprite.SpriteUnit;
import view.sprite.SpriteUnit.SpriteUnitBuilder;

public class SpriteUnitAnimation extends SceneControllerImpl {
	
	private static int STARTING_UNIT_X=53;
	private static int STARTING_UNIT_Y=505;
	private List <FrameUnitManager> frameUnitManagerList;
	private ArrayList<SpriteUnit> spriteUnitOnScene;
	private List<Animation> spriteAnimationList;
	private Timeline timeline;
	private Duration duration;
	private Pane unitDisplayPane; 
	
	public SpriteUnitAnimation() {
		super();
		this.spriteUnitOnScene = new ArrayList<>();
		this.frameUnitManagerList=new ArrayList<>();
		this.timeline=new Timeline();
		this.unitDisplayPane=new Pane();
		this.unitDisplayPane.setPrefSize(800, 1600);
		this.duration=Duration.millis(16);
		//KeyFrame key= new KeyFrame(this.duration, null);
		
		for(int i=0; i<this.getController().getUser().getNumberOfUnit(); i++) {
			FrameUnitManager frameManager= FrameUnitBuilder.newFrameUnitBuilder(i).build();
			frameUnitManagerList.add(frameManager);
		}
		


	}
	
	
	public void createUnitSprite(int index) {
		
		System.out.println("index:"+index);
		FrameUnitManager unitFrame=this.frameUnitManagerList.get(index);
		SpriteUnitBuilder spriteBuilder;
		spriteBuilder=new SpriteUnit.SpriteUnitBuilder().mainImage(unitFrame.getBodyMovingFrameList().get(0))
				.boundaryHeight(unitFrame.getBoundaryHeight())
				.boundaryWidth(unitFrame.getBoundaryWidth())
				.xOffset(unitFrame.getxOffset())
				.yOffset(unitFrame.getyOffset())
				.positionX(STARTING_UNIT_X).positionY(STARTING_UNIT_Y);
		if(unitFrame.getArmorMovingFrameList().isPresent()) {
			spriteBuilder=spriteBuilder.armor(unitFrame.getArmorMovingFrameList().get().get(0));
		}
		
		if(unitFrame.getWeaponMovingFrameList().isPresent()) {
			spriteBuilder=spriteBuilder.weapon(unitFrame.getWeaponMovingFrameList().get().get(0));
		}
		SpriteUnit spriteUnit=spriteBuilder.build();
		this.spriteUnitOnScene.add(spriteBuilder.build());
		/*this.spriteUnitOnScene.element().getImageGroup().setTranslateX(STARTING_UNIT_X);
		this.spriteUnitOnScene.element().getImageGroup().setTranslateY(STARTING_UNIT_Y);
		this.unitDisplayPane.getChildren().add(this.spriteUnitOnScene.element().getImageGroup());*/
		this.moveMultipleUnits(index);
	}
	
	
	
	
	public void moveMultipleUnits(int index) {
		List<Group> animateGroup=this.generateGroupOfImage(index);
		Timeline walk= new Timeline();
		Timeline slide= new Timeline();
		Path path = new Path();
		PathTransition pathTransition= new PathTransition();
		int spriteUnitPosition=this.spriteUnitOnScene.size()-1;
		walk.setCycleCount(Timeline.INDEFINITE);
		slide.setCycleCount(Timeline.INDEFINITE);
		walk.getKeyFrames().add(new KeyFrame(Duration.millis(1000*this.getController().getUser().getUnitTemplateList().get(index).getSpeed())
				,(ActionEvent event)->{
			
			this.spriteUnitOnScene.get(spriteUnitPosition).setImageGroup(animateGroup.get(1));	
		}));
		walk.getKeyFrames().add(new KeyFrame(Duration.millis(2000*this.getController().getUser().getUnitTemplateList().get(index).getSpeed()),
				(ActionEvent event)->{
			this.spriteUnitOnScene.get(spriteUnitPosition).setImageGroup(animateGroup.get(0));
		}));
		
		walk.play();
		
	   TranslateTransition animation = new TranslateTransition(
                Duration.millis(200*this.getController().getUser().getUnitTemplateList().get(index).getSpeed()), 
                this.spriteUnitOnScene.get(spriteUnitPosition).getImageGroup());
        animation.setCycleCount(1);
        animation.setFromX(this.spriteUnitOnScene.get(spriteUnitPosition).getPositionX());
        animation.setToX(this.spriteUnitOnScene.get(spriteUnitPosition).getPositionX()+3);
      //  animation.setAutoReverse(false);
        	animation.setOnFinished(new EventHandler<ActionEvent>() {
            @Override 
            public void handle(ActionEvent t) {
            	spriteUnitOnScene.get(spriteUnitPosition).incrementPositionX(3);
            	animation.setFromX(spriteUnitOnScene.get(spriteUnitPosition).getPositionX());
            	animation.setToX(spriteUnitOnScene.get(spriteUnitPosition).getPositionX()+3);
            	animation.play();
            }
        	});
        	
		animation.play();
		
		
		
		this.spriteUnitOnScene.get(spriteUnitPosition).getImageGroup().setTranslateX(STARTING_UNIT_X);
		this.spriteUnitOnScene.get(spriteUnitPosition).getImageGroup().setTranslateY(STARTING_UNIT_Y);
		this.unitDisplayPane.getChildren().add(this.spriteUnitOnScene.get(spriteUnitPosition).getImageGroup());
	}
	
	
	
	public List<Group> generateGroupOfImage(int index){
		ArrayList<Group> listOfGroupOfImage= new ArrayList<>();
		List<ImageView> body=new ArrayList<ImageView>();
		List<ImageView> weapon=new ArrayList<ImageView>();
		List<ImageView> armor=new ArrayList<ImageView>();
		int imageIndex=0;
		
		FrameUnitManager unitFrame=this.frameUnitManagerList.get(index);
		for(Image image:unitFrame.getBodyMovingFrameList()) {
			Group groupToAdd= new Group();
			body.add(new ImageView(unitFrame.getBodyMovingFrameList().get(imageIndex)));
			groupToAdd.getChildren().add(body.get(imageIndex));
			if(unitFrame.getArmorMovingFrameList().isPresent()) {
				armor.add(new ImageView(unitFrame.getArmorMovingFrameList().get().get(imageIndex)));
				groupToAdd.getChildren().add(armor.get(imageIndex));
			}
			if(unitFrame.getWeaponMovingFrameList().isPresent()) {
				weapon.add(new ImageView(unitFrame.getWeaponMovingFrameList().get().get(imageIndex)));
				groupToAdd.getChildren().add(weapon.get(imageIndex));
			}
			listOfGroupOfImage.add(groupToAdd);
			
			imageIndex++;
		}
		return listOfGroupOfImage;
	}
	

	public List<FrameUnitManager> getFrameUnitManagerList() {
		return frameUnitManagerList;
	}


	public ConcurrentLinkedQueue<SpriteUnit> getSpriteUnitOnCanvas() {
		return spriteUnitOnCanvas;
	}


	public void setSpriteUnitOnCanvas(ConcurrentLinkedQueue<SpriteUnit> spriteUnitOnCanvas) {
		this.spriteUnitOnCanvas = spriteUnitOnCanvas;
	}


	public List<Animation> getSpriteAnimationList() {
		return spriteAnimationList;
	}


	public Pane getUnitDisplayPane() {
		return this.unitDisplayPane;
	}
	
	public void setSpriteAnimationList(List<Animation> spriteAnimationList) {
		this.spriteAnimationList = spriteAnimationList;
	}


	public Timeline getTimeline() {
		return timeline;
	}


	public void setTimeline(Timeline timeline) {
		this.timeline = timeline;
	}
	
	
	
	
	

}
