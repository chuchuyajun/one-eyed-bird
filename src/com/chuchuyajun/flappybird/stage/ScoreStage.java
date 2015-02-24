package com.chuchuyajun.flappybird.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.chuchuyajun.flappybird.drawable.ScoreNumber;
import com.chuchuyajun.flappybird.drawable.ScoreNumber.SCORE_TYPE;
import com.chuchuyajun.flappybird.server.R;
import com.chuchuyajun.flappybird.server.R.RES;

public class ScoreStage extends Stage {
	public ScoreStage(){
		super();
		this.addActor(new ScoreNumber(R.image.get(RES.RUNNING_SCREEN_SCORE_POSITION), SCORE_TYPE.GAME_SCORE));
	}

	@Override
	public void draw() {
		if(R.GameState == R.STATE_DEATH_THROES){
			System.out.println("ÆÁÄ»¶ÔÄã°×ÁËÒ»ÑÛ");
			int i = 0;
			while(i < 100){
				Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
				Gdx.gl.glClearColor(1, 1, 1,1);
				i++;
			}
//		R.GameState = R.STATE_OVER;
//		R.GameState = R.STATE_LOST;
		}
		super.draw();
	}
	
}























