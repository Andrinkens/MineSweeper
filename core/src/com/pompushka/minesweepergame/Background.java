package com.pompushka.minesweepergame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Background implements GameObject{

	private SpriteBatch batch = Core.game.batch;
	private Texture texture;
	private int rows, cols;
	private float x, y, width, height;
	private Sprite tile;
	
	public Background(Texture texture, int rows, int cols){
		this.texture = texture;
		this.rows = rows;
		this.cols = cols;
		tile = new Sprite(texture);
	}
	
	public void setSize(float width, float height){
		this.width = width;
		this.height = height;
		tile.setSize(width/cols,width/cols);
	}
	
	public void setPos(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void draw() {
		for (int i=0;i<cols;i++)
			for (int j=0;j<rows;j++){
				tile.setPosition(x + i*tile.getWidth(),y + j*tile.getHeight());
				tile.draw(batch);
			}
	}
	
}
