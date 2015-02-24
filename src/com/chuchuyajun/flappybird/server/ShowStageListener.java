package com.chuchuyajun.flappybird.server;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.chuchuyajun.flappybird.sound.SoundController;
import com.chuchuyajun.flappybird.sound.SoundController.SOUND_TYPE;

public class ShowStageListener extends InputListener {
	private int flag;
	private SoundController soundController;

	public ShowStageListener(int flag_Stage) {
		this.flag = flag_Stage;
		soundController = new SoundController();
	}

	@Override
	public boolean touchDown(InputEvent event, float x, float y, int pointer,
			int button) {
		// TODO Auto-generated method stub
			soundController.playSound(SOUND_TYPE.BUTTON_PRESS);
		return true;
	}

	@Override
	public void touchUp(InputEvent event, float x, float y, int pointer,
			int button) {
		// TODO Auto-generated method stub
		R.GameState = flag;
		super.touchUp(event, x, y, pointer, button);
	}

}
