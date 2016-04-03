package com.pompushka.minesweepergame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MineSweeperStage extends Stage{

	Texture tile = new Texture("tile.png");
	
	public MineSweeperStage(Viewport viewport, SpriteBatch batch) {
		super(viewport, batch);
	}
	
	@Override
	public void draw(){
		super.draw();
		getBatch().begin();
		//batch.draw(img, 0, 0);
		getBatch().end();
	}
	
	public void resize(int width, int height){
		this.getViewport().update(width, height);
	}
	
	

}
