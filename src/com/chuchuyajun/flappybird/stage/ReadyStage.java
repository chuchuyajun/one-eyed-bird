package com.chuchuyajun.flappybird.stage;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.chuchuyajun.flappybird.ResourceLoader;
import com.chuchuyajun.flappybird.server.R;
import com.chuchuyajun.flappybird.server.R.RES;

public class ReadyStage extends Stage {

	public ReadyStage() { 
		super();
		init();
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		R.GameState = R.STATE_READYTORUN;
		return super.touchUp(screenX, screenY, pointer, button);
	}

	public void init() {
		ResourceLoader RL = new ResourceLoader();
		Image IMG_TXT_GETREADY =RL.GameImage( R.image.get(RES.IMG_TXT_GETREADY));
		Image IMG_BIRD_GRAY_3 = RL.GameImage( R.image.get(RES.IMG_BIRD_GRAY_3));
	    Image IMG_HAND_POINTER = RL.GameImage( R.image.get(RES.IMG_HAND_POINTER));
	    Image IMG_TIP_TAP = RL.GameImage( R.image.get(RES.IMG_TIP_TAP));
	    
		this.addActor(IMG_TXT_GETREADY);
		this.addActor(IMG_BIRD_GRAY_3);
		this.addActor(IMG_HAND_POINTER);
		this.addActor(IMG_TIP_TAP);
		

	}
}
