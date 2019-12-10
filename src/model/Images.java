package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Images {
	
	private ImageView health;
	private ImageView sell;
	private ImageView gold;
	private ImageView gameOver;
	private ImageView background;
	private Image backimg;
	private ImageView newBullet;
	private Image gameOverback;
	private ImageView gameOverbackV;
	
	public Images() {
		backimg = new Image("/img/background.jpg");
		background = new ImageView(backimg);
		health = new ImageView(new Image("/img/health.png"));
		sell = new ImageView(new Image("/img/sell.png"));
		gold = new ImageView(new Image("/img/gold.png"));
		gameOver = new ImageView(new Image("/img/gameover.png"));
		newBullet = new ImageView(new Image("/img/newbullet.png"));
		gameOverback = new Image("/img/gameoverback.png");
		gameOverbackV = new ImageView(gameOverback);
		
		
	}
	
	public Image getBackImg() {
		return backimg;
	}
	
	public ImageView getBackground() {
		return background;
	}
	
	public ImageView getHealth() {
		return health;
		
	}
	
	
	public ImageView getSell() {
		return sell;
	}
	
	
	public ImageView getGold() {
		return gold;
	}
	
	
	public ImageView getOver() {
		return gameOver;
	}
	
	
	public ImageView getNewbullet(){
		return newBullet;
	}
	
	public Image getgameOverback() {
		return gameOverback;
	}
	
	public ImageView getgameOverbackV() {
		return gameOverbackV;
	}
	

}
