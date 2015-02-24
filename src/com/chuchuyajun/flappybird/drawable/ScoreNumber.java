package com.chuchuyajun.flappybird.drawable;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.chuchuyajun.flappybird.ResourceLoader;
import com.chuchuyajun.flappybird.server.R;
import com.chuchuyajun.flappybird.server.R.RES;

public class ScoreNumber extends Actor implements Drawable {
	private List<TextureRegion> NumberTxtRegArray;
	private float showX;
	private float showY;
	private int showPower;
	private TextureRegion currentNumberRegion;
	public enum SCORE_TYPE {GAME_SCORE,BEST_SCORE}
	private SCORE_TYPE scoreType;

	public ScoreNumber(int[] scoreNumberPosition,SCORE_TYPE scoreType) {
		if (scoreNumberPosition.length < 2) {
			System.out.println("分数位置信息配置错误！！请在R.java文件中正确配置！");
			return;
		}
		this.scoreType = scoreType;
		this.NumberTxtRegArray = new ArrayList<TextureRegion>();
		ResourceLoader rLoader = new ResourceLoader();

		TextureRegion TR_NUM_0 = rLoader.gameTextureRegion(R.image.get(RES.IMG_NUM_0));
		TextureRegion TR_NUM_1 = rLoader.gameTextureRegion(R.image.get(RES.IMG_NUM_1));
		TextureRegion TR_NUM_2 = rLoader.gameTextureRegion(R.image.get(RES.IMG_NUM_2));
		TextureRegion TR_NUM_3 = rLoader.gameTextureRegion(R.image.get(RES.IMG_NUM_3));
		TextureRegion TR_NUM_4 = rLoader.gameTextureRegion(R.image.get(RES.IMG_NUM_4));
		TextureRegion TR_NUM_5 = rLoader.gameTextureRegion(R.image.get(RES.IMG_NUM_5));
		TextureRegion TR_NUM_6 = rLoader.gameTextureRegion(R.image.get(RES.IMG_NUM_6));
		TextureRegion TR_NUM_7 = rLoader.gameTextureRegion(R.image.get(RES.IMG_NUM_7));
		TextureRegion TR_NUM_8 = rLoader.gameTextureRegion(R.image.get(RES.IMG_NUM_8));
		TextureRegion TR_NUM_9 = rLoader.gameTextureRegion(R.image.get(RES.IMG_NUM_9));

		NumberTxtRegArray.add(TR_NUM_0);
		NumberTxtRegArray.add(TR_NUM_1);
		NumberTxtRegArray.add(TR_NUM_2);
		NumberTxtRegArray.add(TR_NUM_3);
		NumberTxtRegArray.add(TR_NUM_4);
		NumberTxtRegArray.add(TR_NUM_5);
		NumberTxtRegArray.add(TR_NUM_6);
		NumberTxtRegArray.add(TR_NUM_7);
		NumberTxtRegArray.add(TR_NUM_8);
		NumberTxtRegArray.add(TR_NUM_9);

		currentNumberRegion = TR_NUM_0;

		this.showX = scoreNumberPosition[0] * R.SCREEN_WIDTH_SCALE;
		this.showY = R.PHONE_HEIGHT - scoreNumberPosition[1]
				* R.SCREEN_HEIGHT_SCALE - currentNumberRegion.getRegionHeight()
				* R.SCREEN_HEIGHT_SCALE;
		this.showPower = scoreNumberPosition[2];

	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		this.draw(batch);
	}

	private List<TextureRegion> getScoreTRs(int gameScore) {
		List<TextureRegion> scoreTextRegions = new ArrayList<TextureRegion>();
		if (gameScore == 0)
			scoreTextRegions.add(NumberTxtRegArray.get(0));
		while (gameScore != 0) {
			scoreTextRegions.add(NumberTxtRegArray.get(gameScore % 10));
			gameScore = (gameScore - gameScore % 10) / 10;
		}
		return scoreTextRegions;
	}

	@Override
	public void tick(float deltaTime) {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(SpriteBatch batch) {
		// TODO Auto-generated method stub
		List<TextureRegion> resultScore = new ArrayList<TextureRegion>();
		if(this.scoreType == SCORE_TYPE.GAME_SCORE){
			resultScore = getScoreTRs(R.GameScore);
		} else if(this.scoreType == SCORE_TYPE.BEST_SCORE){
			resultScore = getScoreTRs(R.BestScore);
		}
		
		float currentTRWidth = 0;
		float currentTRHeight = 0;
		float currentShowX = this.showX;
		for (int i = resultScore.size() - 1; i >= 0; i--) {
			this.currentNumberRegion = resultScore.get(i);
			currentTRWidth = this.currentNumberRegion.getRegionWidth()
					* R.SCREEN_WIDTH_SCALE*this.showPower;
			currentTRHeight = this.currentNumberRegion.getRegionHeight()
					* R.SCREEN_HEIGHT_SCALE*this.showPower;
			batch.draw(this.currentNumberRegion, currentShowX, this.showY,
					currentTRWidth, currentTRHeight);
			currentShowX += currentTRWidth;
		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void screenTouched() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isInCollisionWithBird(Bird bird) {
		// TODO Auto-generated method stub
		return false;
	}
}
