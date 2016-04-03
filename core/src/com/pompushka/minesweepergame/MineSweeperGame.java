package com.pompushka.minesweepergame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MineSweeperGame extends Game {
	public SpriteBatch batch;
	public GameScreen gameScreen;
	
	@Override
	public void create () {
		Core.game = this;
		batch = new SpriteBatch();
		gameScreen = new GameScreen();
		setScreen(gameScreen);
	}

	@Override
	public void render () {
		super.render();
	}
}
