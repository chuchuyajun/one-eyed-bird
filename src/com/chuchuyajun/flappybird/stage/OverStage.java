package com.chuchuyajun.flappybird.stage;

import android.R.integer;
import android.renderscript.Element;
import android.widget.Switch;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.DelayAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.chuchuyajun.flappybird.ResourceLoader;
import com.chuchuyajun.flappybird.drawable.Medal;
import com.chuchuyajun.flappybird.drawable.ScoreNumber;
import com.chuchuyajun.flappybird.drawable.ScoreNumber.SCORE_TYPE;
import com.chuchuyajun.flappybird.server.R;
import com.chuchuyajun.flappybird.server.ShowStageListener;
import com.chuchuyajun.flappybird.server.R.RES;
import com.chuchuyajun.flappybird.sound.SoundController;
import com.chuchuyajun.flappybird.sound.SoundController.SOUND_TYPE;

public class OverStage extends Stage {
	
	private Image IMG_TXT_GAMEOVER;
	private Image IMG_RESULT_PANEL;
	private Image IMG_BUTTON_OK;
	private Image IMG_BUTTON_SHARE;
	private Medal MEDAL;
	private ScoreNumber GAMESCORE;
	private ScoreNumber BESTSCORE;
	
	public static final int STAGE_SHOW = 1;
	public static final int GAMEOVER_START = 2;
	public static final int GAMEOVER_COMPLETE = 3;
	public static final int PANEL_START = 4;
	public static final int PANEL_COMPLETE = 5;
	public static final int BOTTON_START = 6;
	public static final int BOTTON_COMPLETE = 7;
	public int STAGE_STATE = STAGE_SHOW;
	
//	private SoundController soundController;
//	private Boolean flag_soundToggle = false;
	
	private float bottonOkX;
	private float gameOverY;
	private float bottonShareX;
	private float resultPanelY;
	
	private float moveSpeed;
	private float speedGravity;
	
	public OverStage(){
		super();
		init();
	}
	private void init() {
		ResourceLoader RL = new ResourceLoader();
//		soundController = new SoundController();
		IMG_TXT_GAMEOVER = RL.GameImage(R.image.get(RES.IMG_TXT_GAMEOVER)); 
		IMG_RESULT_PANEL = RL.GameImage( R.image.get(RES.IMG_RESULT_PANEL));
		IMG_BUTTON_OK     = RL.GameImage(R.image.get(RES.IMG_BUTTON_OK));
	    IMG_BUTTON_SHARE  = RL.GameImage(R.image.get(RES.IMG_BUTTON_SHARE));
	    MEDAL = new Medal(R.image.get(RES.MEDAL_POSITION));
	    GAMESCORE = new ScoreNumber(R.image.get(RES.RESULT_PANEL_SCORE_POSITION),SCORE_TYPE.GAME_SCORE);
	    BESTSCORE = new ScoreNumber(R.image.get(RES.BEST_SCORE_POSITION),SCORE_TYPE.BEST_SCORE);

		gameOverY = IMG_TXT_GAMEOVER.getY();
		resultPanelY = IMG_RESULT_PANEL.getY();
		bottonOkX = IMG_BUTTON_OK.getX();
		bottonShareX = IMG_BUTTON_SHARE.getX();
		
	    this.addActor(IMG_TXT_GAMEOVER);
	    this.addActor(IMG_RESULT_PANEL);
	    this.addActor(IMG_BUTTON_OK);
	    this.addActor(IMG_BUTTON_SHARE);
	    
	    
	    
	    this.addActor(MEDAL);
	    this.addActor(GAMESCORE);
	    this.addActor(BESTSCORE);
	    
	    IMG_BUTTON_OK.addListener(new ShowStageListener(R.STATE_START));
	    System.out.println("开始的时候按钮Y坐标="+IMG_BUTTON_OK.getY());
	}
	
	public void initOverStage(){
		IMG_TXT_GAMEOVER.setY(R.PHONE_HEIGHT*1.5f);
		IMG_RESULT_PANEL.setY(-R.PHONE_HEIGHT*3);
		IMG_BUTTON_OK.setX(-IMG_BUTTON_OK.getWidth()*R.SCREEN_WIDTH_SCALE);
	    IMG_BUTTON_SHARE.setX(R.PHONE_WIDTH);
	    MEDAL.setVisible(false);
	    GAMESCORE.setVisible(false);
	    BESTSCORE.setVisible(false);
	    STAGE_STATE = STAGE_SHOW;
		moveSpeed = (float)R.PHONE_HEIGHT / 150;
		speedGravity = (float)R.PHONE_HEIGHT / 500;
	}
	
	
	
	public void resetState(){
		STAGE_STATE = STAGE_SHOW;
	}
	@Override
	public void draw() {
		moveSpeed += speedGravity;
		switch(STAGE_STATE){
			case STAGE_SHOW:
				initOverStage();
				STAGE_STATE = GAMEOVER_START;
			break;
			
			case GAMEOVER_START:
//				if(flag_soundToggle){
//					flag_soundToggle = true;
//					soundController.playSound(SOUND_TYPE.BUTTON_PRESS);
//				}
				if(IMG_TXT_GAMEOVER.getY() > gameOverY) {
					IMG_TXT_GAMEOVER.setY(IMG_TXT_GAMEOVER.getY() - moveSpeed);
				} else {
					IMG_TXT_GAMEOVER.setY(gameOverY);
					STAGE_STATE = GAMEOVER_COMPLETE;
				}
				break;
				
			case GAMEOVER_COMPLETE:
//				flag_soundToggle = false; 
//				for(int i=0;i<10000;i++);
				new DelayAction().setDuration(30);
				STAGE_STATE = PANEL_START;
				break;
				
			case PANEL_START:
//				if(flag_soundToggle){
//					flag_soundToggle = true;
//					soundController.playSound(SOUND_TYPE.BUTTON_PRESS);
//				}
				if(IMG_RESULT_PANEL.getY() < resultPanelY) {
					IMG_RESULT_PANEL.setY(IMG_RESULT_PANEL.getY() + moveSpeed);
				} else {
					IMG_RESULT_PANEL.setY(resultPanelY);
					STAGE_STATE = PANEL_COMPLETE;
				}
				break;
				
			case PANEL_COMPLETE:
//				flag_soundToggle = false; 
				if(!MEDAL.isVisible())MEDAL.setVisible(true);
				if(!GAMESCORE.isVisible())GAMESCORE.setVisible(true);
				if(!BESTSCORE.isVisible())BESTSCORE.setVisible(true);
//				for(int i=0;i<10000;i++);
				STAGE_STATE = BOTTON_START;
				break;
				
			case BOTTON_START:
//				soundController.playSound(SOUND_TYPE.BUTTON_PRESS);
				IMG_BUTTON_OK.setX(bottonOkX);
			    IMG_BUTTON_SHARE.setX(bottonShareX);
			    STAGE_STATE = BOTTON_COMPLETE;
				break;
				
			case BOTTON_COMPLETE:
				
				break;
				
		}
		super.draw();
	}
	
}























