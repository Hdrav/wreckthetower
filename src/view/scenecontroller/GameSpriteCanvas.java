package view.scenecontroller;

import javafx.util.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import view.sprite.FrameUnitBuilder;
import view.sprite.FrameUnitManager;
import view.sprite.SpriteImpl;
import view.sprite.SpriteManager;
import view.sprite.SpriteUnit;
import view.sprite.SpriteUnit.SpriteUnitBuilder;
import javafx.animation.KeyFrame;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.concurrent.*;
import javafx.event.ActionEvent;

public class GameSpriteCanvas extends SceneControllerImpl {
	 
	private static int STARTING_UNIT_X=53;
	private static int STARTING_UNIT_Y=505;
	private GraphicsContext graphicsGameCanvas;
	private Canvas gameCanvas;
	/*private List <FrameUnitManager> frameUnitManagerList;
	private ConcurrentLinkedQueue<SpriteUnit> spriteUnitOnCanvas; //lista di tutti gli sprite delle unità in questo momento sulla mappa
											//serve per capire se un elemento della lista ha colliso con un altro*/
	
	private SpriteUnitAnimation spriteUnitAnimation;
	private SpriteImpl userTower;
	private SpriteImpl enemyTower;
	private Image groundTile;
	private Image userTowerImage;
	private Image enemyTowerImage;
	private Image background;
	private Timeline timeline;
//	private SpriteManager spriteManager;
//	private ParallelTransition parallelSpriteAnimation;
	
	
	
	public GameSpriteCanvas(){
		
		this.gameCanvas=new Canvas();
		this.graphicsGameCanvas=this.gameCanvas.getGraphicsContext2D();
		this.graphicsGameCanvas.drawImage(this.background, 0, 0);
		List<FrameUnitManager> frameUnitManagerList=new ArrayList<>();
		ConcurrentLinkedQueue<SpriteUnit> unitQueue=new ConcurrentLinkedQueue<>();
		this.background=new Image("/map_of_the_game/backgroundMainPane.png");
		this.groundTile=new Image("/map_of_the_game/terreno.png");
		this.userTowerImage= new Image("/map_of_the_game/tower_player.png");
		this.enemyTowerImage=new Image("/map_of_the_game/enemy_tower01.png");

		this.userTower=new SpriteImpl.SpriteImplBuilder().height(240).width(140).mainImage(enemyTowerImage)
				.positionX(1488).positionY(385).xOffset(0).yOffset(0)
				.boundaryHeight(240).boundaryHeight(120).build();
		this.userTower=new SpriteImpl.SpriteImplBuilder().height(240).width(140).mainImage(userTowerImage)
				.positionX(-8).positionY(385).xOffset(0).yOffset(0)
				.boundaryHeight(240).boundaryHeight(120).build();
			
		for(int i=0; i<this.getController().getUser().getNumberOfUnit(); i++) {
			FrameUnitManager frameManager= FrameUnitBuilder.newFrameUnitBuilder(i).build();
			frameUnitManagerList.add(frameManager);
		}
		this.spriteUnitAnimation=new SpriteUnitAnimation();
	
		
	}
	
	public void renderTowers() {
		
		this.renderTowerPlayer();
		this.renderTowerEnemy();
	}
	
	public void renderTowerPlayer() {
		this.graphicsGameCanvas.drawImage(this.background, 0, 0);
		this.graphicsGameCanvas.drawImage(this.groundTile, 0, 625);
		this.graphicsGameCanvas.drawImage(this.groundTile, 1000, 625);
		this.userTower.render(this.graphicsGameCanvas);
	}
	
	public void renderTowerEnemy() {
		this.userTower.render(this.graphicsGameCanvas);
		
		/*Image unit= new Image("/animations/basic_player_animations/unit_movement01.png");
		SpriteUnit sprite= new SpriteUnit.SpriteUnitBuilder().armor(null).boundaryHeight(0).boundaryWidth(0)
							.positionX(53).positionY(505).height(120).width(120).mainImage(unit)
							.weapon(null).xOffset(0).yOffset(0).build();
		sprite.render(this.graphicsGameCanvas);*/
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
		this.spriteUnitOnCanvas.add(spriteBuilder.build());
		spriteUnit.render(this.graphicsGameCanvas);
	//	new Thread(()->{this.updateGameCanvas();}).start();
	/*	Task task = new Task<Void>() {

			@Override
			protected Void call() throws Exception {
				
			}
			
		};*/
		//this.updateGameCanvas();
		
	}
	
	
	public void buildAndSetGameLoop(ActionEvent actionEvent) {
		
		updateSprite();
	/*	checkCollision();
		cleanUpSprite();*/
	}

	private void cleanUpSprite() {
		// TODO Auto-generated method stub
		
	}

	private void checkCollision() {
		for (SpriteUnit spriteA:spriteManager.getCollisionsToCheck()){
			for (SpriteUnit spriteB:spriteManager.getAllSprites()){
                if (handleCollision(spriteA, spriteB)) {
                    // The break helps optimize the collisions
                    //  The break statement means one object only hits another
                    // object as opposed to one hitting many objects.
                    // To be more accurate comment out the break statement.
                    break;
                }
			}
		}
	}
		
	protected boolean handleCollision(SpriteUnit spriteA, SpriteUnit spriteB) {
		if(spriteA.intersects(spriteB)) {
			if(spriteA.isEnemy()!=spriteA.isEnemy()) {
				
			}
		}
		return false;
	}
		 	
	private void updateSprite() {
		for(SpriteUnit sprite:this.spriteUnitOnCanvas) {
			handleUpdate(sprite);
			//this.renderUpdateSprite(sprite);
		}
	}
	
	/*private void renderUpdateSprite(SpriteUnit sprite) {
		sprite.moveHorizontally(5, 0);
		sprite.render(graphicsGameCanvas);
		
	}*/

	private void handleUpdate(SpriteUnit sprite) {
		if(sprite instanceof SpriteUnit) {
			SpriteUnit unit= sprite;
			//if(checkCollision())
			for(SpriteUnit spriteUnit:this.spriteUnitOnCanvas) {
				Duration duration=Duration.millis(500);
				this.parallelSpriteAnimation.getChildren().add();
		
			}
		}
	}
	
	
	public GraphicsContext getGraphicsGameCanvas() {
		return this.graphicsGameCanvas;
	}
	
	public Canvas getGameCanvas() {
		return this.gameCanvas;
	}
}
