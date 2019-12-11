package model;

import java.util.ArrayList;

import javafx.scene.image.ImageView;

public class Tower {
	protected int cost;
	protected Point point;
	protected int range;
	protected ImageView bullet;
	private int energy = 0;
	private	ArrayList <Point> rangePoint = new ArrayList<>();
	protected ImageView img;
	public Tower() {
	}

	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public Point getPoint() {
		return point;
	}
	public void setPos(Point point) {
		this.point = point;
	}
	public void setBulletImg(ImageView img) {
		this.bullet = img;
	}
	public ImageView getBulletImg() {
		return bullet;
	}
	public void setImg(ImageView img) {
		this.img = img;
	}
	public ImageView getImg() {
		return img;
	}
}
