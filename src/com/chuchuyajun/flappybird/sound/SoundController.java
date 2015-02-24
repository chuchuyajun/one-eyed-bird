package com.chuchuyajun.flappybird.sound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.chuchuyajun.flappybird.server.R;

public class SoundController {
	private Sound sound_birdDie;
	private Sound sound_birdFly;
	private Sound sound_birdPass;
	private Sound sound_birdCollison;
	private Sound sound_buttonPress;

	public SoundController() {
		this.sound_birdCollison = Gdx.audio.newSound(Gdx.files
				.internal(R.AUDIO_BIRD_COLLISION));
		this.sound_birdDie = Gdx.audio.newSound(Gdx.files
				.internal(R.AUDIO_BIRD_DIE));
		this.sound_birdFly = Gdx.audio.newSound(Gdx.files
				.internal(R.AUDIO_BIRD_FLAY));
		this.sound_birdPass = Gdx.audio.newSound(Gdx.files
				.internal(R.AUDIO_BIRD_PASS));
		this.sound_buttonPress = Gdx.audio.newSound(Gdx.files
				.internal(R.AUDIO_BUTTON_PRESS));

	}

	public enum SOUND_TYPE {
		BIRD_DIE, BIRD_FLAY, BIRD_PASS, BIRD_COLLISON, BUTTON_PRESS
	};

	public void playSound(SOUND_TYPE soundType) {
		if (soundType == SOUND_TYPE.BIRD_DIE) {
			this.sound_birdDie.play();
		} else if (soundType == SOUND_TYPE.BIRD_FLAY) {
			this.sound_birdFly.play();
		} else if (soundType == SOUND_TYPE.BIRD_PASS) {
			this.sound_birdPass.play();
		} else if (soundType == SOUND_TYPE.BUTTON_PRESS) {
			this.sound_buttonPress.play();
		} else if (soundType == SOUND_TYPE.BIRD_COLLISON) {
			this.sound_birdCollison.play();
		}
	}
}
