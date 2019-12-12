package view;
/**
 * @author Peilin Feng
 * @author Xiaomin Zhao
 * @author Dongzhe Chen
 * @author Jiacheng He
 * CSC 335, Fall 2019
 * team project, Images.java
 * 
 * purpose: this is Images class which just for our different stage to
 * 			retrieve the pictures easily
 */


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
	
	/**
	 * get method directly get the win image
	 * @return a win image
	 */
	public Image getWin() {
		return win;
	}
	
	/**
	 * get method directly get the win imageview 
	 * @return a winV imageview
	 */
	public ImageView getWinV() {
		return winV;
	}
	
	/**
	 * get method directly get the pause image
	 * @return a pause image
	 */
	public Image getPause() {
		return pause;
	}
	
	/**
	 * get method directly get the pause imageview
	 * @return a pause imageview
	 */
	public ImageView getPauseV() {
		return pauseV;
	}
	
	/**
	 * get method directly get the newGame image
	 * @return a newGame image
	 */
	public Image getnewGame() {
		return newgame;
	}
	
	/**
	 * get method directly get the newGame imageview
	 * @return a newGame imageview
	 */
	public ImageView getnewGameV() {
		return newgameV;
	}
	
	/**
	 * get method directly get the home image
	 * @return a home image
	 */
	public Image getHome() {
		return home;
	}
	
	/**
	 * get method directly get the home imageview
	 * @return a home imageview
	 */
	public ImageView getHomeV() {
		return homeV;
	}
	
	/**
	 * get method directly get the road end image
	 * @return a road end image
	 */
	public Image getHomeend() {
		return homeend;
	}
	
	/**
	 * get method directly get the road image
	 * @return a road image
	 */
	public Image getRoad2() {
		return road2;
	}
	
	/**
	 * get method directly get the background image
	 * @return a background image
	 */
	public Image getBackImg() {
		return backimg;
	}
	
	/**
	 * get method directly get the background imageview
	 * @return a background imageview
	 */
	public ImageView getBackground() {
		return background;
	}
	
	/**
	 * get method directly get the health imageview
	 * @return a health imageview
	 */
	public ImageView getHealth() {
		return health;
	}
	
	/**
	 * get method directly get the sell imageview
	 * @return a sell imageview
	 */
	public ImageView getSell() {
		return sell;
	}
	
	/**
	 * get method directly get the gold imageview
	 * @return a gold imageview
	 */
	public ImageView getGold() {
		return gold;
	}
	
	/**
	 * get method directly get the game over imageview
	 * @return a game over imageview
	 */
	public ImageView getOver() {
		return gameOver;
	}
	
	/**
	 * get method directly get the new bullet imageview
	 * @return a new bullet imageview
	 */
	public ImageView getNewbullet(){
		return newBullet;
	}
	
	/**
	 * get method directly get the game over background image
	 * @return a game over background image
	 */
	public Image getgameOverback() {
		return gameOverback;
	}
	
	/**
	 * get method directly get the game over background imageview
	 * @return a game over background imageview
	 */
	public ImageView getgameOverbackV() {
		return gameOverbackV;
	}
	

}
