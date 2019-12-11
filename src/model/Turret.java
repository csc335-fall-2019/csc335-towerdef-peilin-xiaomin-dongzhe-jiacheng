package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Turret extends Tower {
	private int cost = 20;
	private int attack = 2;
	public Turret() {
		super.setCost(cost);
		super.setImg(new ImageView(new Image("/img/tower2.png")));
	}
	
}
