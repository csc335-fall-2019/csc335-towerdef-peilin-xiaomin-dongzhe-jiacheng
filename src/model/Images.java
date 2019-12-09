package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Images {
	
	private ImageView health;
	private ImageView sell;
	private ImageView gold;
	
	public Images() {
		health = new ImageView(new Image("/img/health.png"));
		sell = new ImageView(new Image("/img/sell.png"));
		gold = new ImageView(new Image("/img/gold.png"));
		
		
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
	
	

}
