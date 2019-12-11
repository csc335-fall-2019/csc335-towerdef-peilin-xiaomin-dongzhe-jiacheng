package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TrdTower extends Tower {
	private int cost =10;
	private int attack = 1;
	public TrdTower() {
		super.setCost(cost);
		super.setImg(new ImageView(new Image("/img/TOWER.png")));
	}
}
