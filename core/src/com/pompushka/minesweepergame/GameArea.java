package com.pompushka.minesweepergame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameArea implements InputProcessor{

	SpriteBatch batch = Core.game.batch;
	Viewport viewport;
	Background bg;
	float width, height;
	int rows, cols;
	float tileSize;
	
	int dirH,dirV;
	
	boolean gameOver = false;
	
	Random rm = new Random();
	
	char gameArray[][];
	boolean visible[][];
	
	ArrayList<Pos> tilesToCheck = new ArrayList<Pos>();
	
	Sprite noneSq = new Sprite(new Texture("tile2.png"));
	Sprite mineSq = new Sprite(new Texture("mine.png"));
	Sprite unknSq = new Sprite(new Texture("unknown.png"));
	Sprite oneSq = new Sprite(new Texture("num1.png"));
	Sprite twoSq = new Sprite(new Texture("num2.png"));
	Sprite threeSq = new Sprite(new Texture("num3.png"));
	Sprite fourSq = new Sprite(new Texture("num4.png"));	
	Sprite fiveSq = new Sprite(new Texture("num5.png"));		
	Sprite sixSq = new Sprite(new Texture("num6.png"));	
	
	public GameArea(Viewport viewport, int rows, int cols){
		this.viewport = viewport;
		this.width = viewport.getWorldWidth();
		this.height = viewport.getWorldWidth();
		tileSize = width/cols;
		/*
		bg = new Background(new Texture("tile2.png"), rows, cols);
		bg.setSize(viewport.getWorldWidth(),viewport.getWorldWidth());
		bg.setPos(0, (viewport.getWorldHeight() / 2) - (viewport.getWorldWidth() / 2));
		*/
		this.rows = rows;
		this.cols = cols;

		restart();
	}
	
	public void resize(){
		//bg.setSize(viewport.getWorldWidth(),viewport.getWorldWidth());
	}
	
	public void draw(){
		batch.begin();
		Sprite sprite = noneSq;
		for (int i=0;i<cols;i++)
			for (int j=0;j<rows;j++){
				sprite.setSize(tileSize, tileSize);
				sprite.setPosition(i*tileSize,j*tileSize);
				sprite.draw(batch);
			}
		for (int i=0;i<cols;i++)
			for (int j=0;j<rows;j++){
				if (visible[i][j])
					switch (gameArray[i][j]){
					case 255:sprite = mineSq;break;
					case 1  :sprite = oneSq;break;
					case 2  :sprite = twoSq;break;
					case 3  :sprite = threeSq;break;
					case 4  :sprite = fourSq;break;	
					case 5  :sprite = fiveSq;break;
					case 6  :sprite = sixSq;break;
					case 0  :sprite = noneSq;break;
					}
				else
					sprite = unknSq;
	
				sprite.setSize(tileSize, tileSize);
				sprite.setPosition(i*tileSize,j*tileSize);
				sprite.draw(batch);
			}
		batch.end();
	}
	
	private void restart(){
		addMines(10);
		addNumbers();
	}
	
	private void addMines(int number){
		gameArray = new char[rows][cols];
		visible = new boolean[rows][cols];
		for (int i=0;i<rows;i++)
			for (int j=0;j<cols;j++){
				gameArray[i][j] = 0;
				visible[i][j] = false;
			}

		for (int i=0;i<number;i++)
		{
			gameArray[rm.nextInt(cols-1)][rm.nextInt(rows-1)] = 255;
		}
	}
	
	private void addNumbers(){
		for (int i=1;i<cols-1;i++)
			for (int j=1;j<rows-1;j++)
				if (gameArray[i][j] == 0){
					if (gameArray[i+1][j] == 255)	gameArray[i][j]+=1;
					if (gameArray[i-1][j] == 255)	gameArray[i][j]+=1;
					if (gameArray[i][j+1] == 255)	gameArray[i][j]+=1;
					if (gameArray[i][j-1] == 255)	gameArray[i][j]+=1;
					if (gameArray[i+1][j+1] == 255)	gameArray[i][j]+=1;
					if (gameArray[i-1][j+1] == 255)	gameArray[i][j]+=1;
					if (gameArray[i+1][j-1] == 255)	gameArray[i][j]+=1;
					if (gameArray[i-1][j-1] == 255)	gameArray[i][j]+=1;
				}
		for (int j=1;j<rows-1;j++)
		{
			if (gameArray[0][j] == 0)
			{
				if (gameArray[1][j-1] == 255)	gameArray[0][j]+=1;
				if (gameArray[1][j] == 255)		gameArray[0][j]+=1;
				if (gameArray[1][j+1] == 255)	gameArray[0][j]+=1;
				if (gameArray[0][j-1] == 255)	gameArray[0][j]+=1;
				if (gameArray[0][j+1] == 255)	gameArray[0][j]+=1;
			}
			if (gameArray[cols-1][j] == 0)
			{
				if (gameArray[cols-2][j-1] == 255)	gameArray[cols-1][j]+=1;
				if (gameArray[cols-2][j] == 255)	gameArray[cols-1][j]+=1;
				if (gameArray[cols-2][j+1] == 255)	gameArray[cols-1][j]+=1;
				if (gameArray[cols-1][j-1] == 255)	gameArray[cols-1][j]+=1;
				if (gameArray[cols-1][j+1] == 255)	gameArray[cols-1][j]+=1;
			}
		}
		for (int i=1;i<cols-1;i++)
		{
			if (gameArray[i][0] == 0)
			{
				if (gameArray[i-1][1] == 255)	gameArray[i][0]+=1;
				if (gameArray[i][1] == 255)		gameArray[i][0]+=1;
				if (gameArray[i+1][1] == 255)	gameArray[i][0]+=1;
				if (gameArray[i-1][0] == 255)	gameArray[i][0]+=1;
				if (gameArray[i+1][0] == 255)	gameArray[i][0]+=1;
			}
			if (gameArray[i][rows-1] == 0)
			{
				if (gameArray[i-1][rows-2] == 255)	gameArray[i][rows-1]+=1;
				if (gameArray[i][rows-2] == 255)	gameArray[i][rows-1]+=1;
				if (gameArray[i+1][rows-2] == 255)	gameArray[i][rows-1]+=1;
				if (gameArray[i-1][rows-1] == 255)	gameArray[i][rows-1]+=1;
				if (gameArray[i+1][rows-1] == 255)	gameArray[i][rows-1]+=1;
			}
		}
		if (gameArray[0][0] == 0)	{
			if (gameArray[0][1] == 255)	gameArray[0][0]+=1;
			if (gameArray[1][1] == 255)	gameArray[0][0]+=1;
			if (gameArray[1][0] == 255)	gameArray[0][0]+=1;
		}
		if (gameArray[cols-1][0] == 0)	{
			if (gameArray[cols-1][1] == 255)	gameArray[cols-1][0]+=1;
			if (gameArray[cols-2][1] == 255)	gameArray[cols-1][0]+=1;
			if (gameArray[cols-2][0] == 255)	gameArray[cols-1][0]+=1;
		}
		if (gameArray[0][rows-1] == 0)	{
			if (gameArray[1][rows-1] == 255)	gameArray[0][rows-1]+=1;
			if (gameArray[1][rows-2] == 255)	gameArray[0][rows-1]+=1;
			if (gameArray[0][rows-2] == 255)	gameArray[0][rows-1]+=1;
		}
		if (gameArray[cols-1][rows-1] == 0)	{
			if (gameArray[cols-2][rows-1] == 255)	gameArray[cols-1][rows-1]+=1;
			if (gameArray[cols-2][rows-2] == 255)	gameArray[cols-1][rows-1]+=1;
			if (gameArray[cols-1][rows-2] == 255)	gameArray[cols-1][rows-1]+=1;
		}
		
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		int currentCol = (int)(((screenX*viewport.getWorldWidth())/viewport.getScreenWidth()));
		int currentRow = (int)((((viewport.getScreenHeight()-screenY)*viewport.getWorldHeight())/viewport.getScreenHeight()));
		Gdx.app.log("MineSweeper", "Col: " + currentCol + " Row: " + currentRow);
		
		visible[currentCol][currentRow] = true;
		
		if (gameArray[currentCol][currentRow] == 255)	{
			for (int i=0;i<rows;i++)
				for (int j=0;j<cols;j++)
					visible[i][j] = true;
			gameOver = true;
		}
		
		dirH = -1;dirV = 0;
		openClearTiles(currentCol,currentRow);
		/*
		int k=currentCol;
		while ((gameArray[k][currentRow] == 0)&&(k<cols-1)){
			k++;
			visible[k][currentRow] = true;
		}
		while ((gameArray[k][currentRow] == 0)&&(k>0)){
			k--;
			visible[k][currentRow] = true;
		}
		k=currentRow;
		while ((gameArray[currentCol][k] == 0)&&(k<rows-1)){
			k++;
			visible[currentCol][k] = true;
		}
		while ((gameArray[currentCol][k] == 0)&&(k>0)){
			k--;
			visible[currentCol][k] = true;
		}
		*/

		return false;
	}
	
	private ArrayList<Pos> checkClearTilesNear(int col, int row){
		visible[col][row] = true;
		ArrayList<Pos> retVal = new ArrayList<Pos>();
		if (col>0)
			if ((!visible[col-1][row])){
				if (gameArray[col-1][row] == 0)	retVal.add(new Pos(col-1,row));
				else if (gameArray[col-1][row] != 255) visible[col-1][row] = true;
			}
		if (col<cols-2)
			if (!visible[col+1][row]){
				if (gameArray[col+1][row] == 0)	retVal.add(new Pos(col+1,row));
				else if	(gameArray[col+1][row] != 255)	visible[col+1][row] = true;
			}
		if (row>0)
			if (!visible[col][row-1]){
				if (gameArray[col][row-1] == 0)	retVal.add(new Pos(col,row-1));
				else if (gameArray[col][row-1] != 255)	visible[col][row-1] = true;
			}
		if (row<rows-2)
			if (!visible[col][row+1]){
				if (gameArray[col][row+1] == 0)	retVal.add(new Pos(col,row+1));
				else if (gameArray[col][row+1] != 255)	visible[col][row+1] = true;
			}
		/*
		if (col>0)
			if ((!visible[col-1][row]) && (gameArray[col-1][row] == 0))	retVal.add(new Pos(col-1,row));
		if (col<cols-2)
			if ((!visible[col+1][row]) && (gameArray[col+1][row] == 0))	retVal.add(new Pos(col+1,row));
		if (row>0)
			if ((!visible[col][row-1]) && (gameArray[col][row-1] == 0))	retVal.add(new Pos(col,row-1));
		if (row<rows-2)
			if ((!visible[col][row+1]) && (gameArray[col][row+1] == 0))	retVal.add(new Pos(col,row+1));*/
		return retVal;
	}
	
	private void openClearTilesRec(int col, int row){
		
		ArrayList<Pos> tempVal = new ArrayList<Pos>();
		tempVal.addAll(tilesToCheck);
		
		for (Pos item : tempVal) {
			Gdx.app.log("MineSweeper", "Col: " + item.x + " Row: " + item.y);
			tilesToCheck.addAll(checkClearTilesNear(item.x,item.y));
			tilesToCheck.remove(item);
		}
		
		if (!tilesToCheck.isEmpty())openClearTilesRec(tilesToCheck.get(0).x,tilesToCheck.get(0).y);
		/*
		ListIterator<Pos> iterator = tilesToCheck.listIterator();
		while(iterator.hasNext()){
			if (iterator.next() == null)	break;
			Gdx.app.log("MineSweeper", "Col: " + col + " Row: " + row);
			Pos pos = iterator.next();
			col = pos.x;
			row = pos.y;
			visible[col][row] = true;*/
			
			/*
			if (col>0)
				if ((!visible[col-1][row]) && (gameArray[col-1][row] == 0))	iterator.add(new Pos(col-1,row));
			if (col<cols-2)
				if ((!visible[col+1][row]) && (gameArray[col+1][row] == 0))	iterator.add(new Pos(col+1,row));
			if (row>0)
				if ((!visible[col][row-1]) && (gameArray[col][row-1] == 0))	iterator.add(new Pos(col,row-1));
			if (row<rows-2)
				if ((!visible[col][row+1]) && (gameArray[col][row+1] == 0))	iterator.add(new Pos(col,row+1));
		}*/
				
				
				//iterator.hasNext();) {
		    //Pos pos = iterator.next();
		    	//iterator.
		        // Remove the current element from the iterator and the list.
		       // iterator.remove();
		   // }
		//}
		/*
		for (Pos item : tilesToCheck) {
			tilesToCheck.addAll(checkClearTilesNear(item.x,item.y));
			tilesToCheck.remove(item);
		}*/
		//tilesToCheck.addAll(tempVal);
	}
	
	private boolean openClearTiles(int col, int row){
		
		tilesToCheck.add(new Pos(col,row));
		openClearTilesRec(col,row);
		
		return true;	
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (gameOver){
			restart();
			gameOver = false;
		}
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	
	private class Pos{
		public int x;
		public int y;
		
		public Pos(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
}
