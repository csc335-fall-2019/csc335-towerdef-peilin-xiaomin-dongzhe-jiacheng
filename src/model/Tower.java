package model;

import java.util.ArrayList;

import javafx.scene.image.ImageView;

public class Tower {
	protected int cost;
	protected Point point;
	protected int range;
	protected ImageView bullet;
	protected ImageView img;
	
	public Tower() {
	}
	
	public int getCost() {
		return cost;
	}
	
	public Point getPoint() {
		return point;
	}
	
	public int getRange() {
		return range;
	}
	
	
	public ImageView getImg() {
		return img;
	}
	
	
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	public void setPoint(Point point) {
		this.point = point;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public void setPos(Point point) {
		this.point = point;
	}
	
	public void setImg(ImageView img) {
		this.img = img;
	}

}
