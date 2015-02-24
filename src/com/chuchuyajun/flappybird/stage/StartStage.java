package com.chuchuyajun.flappybird.stage;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.chuchuyajun.flappybird.ResourceLoader;
import com.chuchuyajun.flappybird.server.R;
import com.chuchuyajun.flappybird.server.ShowStageListener;
import com.chuchuyajun.flappybird.server.R.RES;

public class StartStage extends Stage {
	
	public StartStage() {
		super();
		setScreenAdaptation();
		init();
		
	}
	

	public void init() {
		ResourceLoader RL = new ResourceLoader();
		Image IMG_TXT_GAMENAME = RL.GameImage( R.image.get(RES.IMG_TXT_GAMENAME));
		Image IMG_BUTTON_START = RL.GameImage( R.image.get(RES.IMG_BUTTON_START));
//		Image IMG_BUTTON_SCORE = RL.GameImage( R.image.get(RES.IMG_BUTTON_SCORE));
		Image IMG_TXT_COPYRIGHT = RL.GameImage( R.image.get(RES.IMG_TXT_COPYRIGHT));
//		System.out.println("调试信息--开始界面布局完毕");
		
		this.addActor(IMG_TXT_GAMENAME); 
		this.addActor(IMG_BUTTON_START);
//		this.addActor(IMG_BUTTON_SCORE);
		this.addActor(IMG_TXT_COPYRIGHT);
		
		IMG_BUTTON_START.addListener(new ShowStageListener(R.STATE_READY));

	}
	
	
	private void setScreenAdaptation(){
		R.PHONE_HEIGHT = (int) this.getHeight();
		R.PHONE_WIDTH = (int) this.getWidth();
		
		R.SCREEN_WIDTH_SCALE = this.getWidth()/R.DESIGN_SCREEN_SIZE_WIDTH;
		R.SCREEN_HEIGHT_SCALE = this.getHeight()/R.DESIGN_SCREEN_SIZE_HEIGHT;
	}
}
