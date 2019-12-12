package view;

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
	private Image road2;
	private Image homeend;
	private Image home;
	private ImageView homeV;
	private Image newgame;
	private ImageView newgameV;
	
	private Image pause;
	private ImageView pauseV;
	
	private Image win;
	private ImageView winV;
	
	
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
		road2 = new Image("/img/road2.jpg");
		homeend = new Image("/img/homeEnd.png");
		home = new Image("/img/home.png");
		homeV = new ImageView(home);
		
		newgame = new Image("/img/newgame.png");
		newgameV = new ImageView(newgame);
		
		pause = new Image("/img/pause.png");
		pauseV = new ImageView(pause);
		
		win = new Image("/img/win.png");
		winV = new ImageView(win);
		
	}
	
	public Image getWin() {
		return win;
	}
	
	public ImageView getWinV() {
		return winV;
	}
	
	public Image getPause() {
		return pause;
	}
	
	public ImageView getPauseV() {
		return pauseV;
	}
	
	public Image getnewGame() {
		return newgame;
	}
	
	public ImageView getnewGameV() {
		return newgameV;
	}
	
	public Image getHome() {
		return home;
	}
	
	public ImageView getHomeV() {
		return homeV;
	}
	
	public Image getHomeend() {
		return homeend;
	}
	
	public Image getRoad2() {
		return road2;
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
