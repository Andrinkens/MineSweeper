package com.pompushka.minesweepergame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen implements Screen{
	private SpriteBatch batch;
	private OrthographicCamera camera;
	//private Stage stage;
	private GameArea gameArea;
	private Viewport viewport;

	public GameScreen(){
		this.batch = Core.game.batch;
		
		camera = new OrthographicCamera(Core.viewPortWidth, Core.viewPortHeight);
		
		viewport = new FitViewport(Core.viewPortWidth,Core.viewPortHeight,camera);
	    viewport.apply();
	    
	    camera.position.set(Core.viewPortWidth*0.5f, Core.viewPortHeight*0.5f, 0f);
	    
	    gameArea = new GameArea(viewport,10,10);
	    
	    Gdx.input.setInputProcessor(gameArea);
		
	}
	
	@Override
	public void show() {
		//Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render(float delta) {
		camera.update();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(camera.combined);
		
		gameArea.draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		Core.resize(width, height);
		viewport.update(width,height);
		gameArea.resize();
		camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
