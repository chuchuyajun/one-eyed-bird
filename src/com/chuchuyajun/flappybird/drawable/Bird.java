package com.chuchuyajun.flappybird.drawable;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.chuchuyajun.flappybird.server.R;
import com.chuchuyajun.flappybird.server.R.FLAPPY_MODE;
import com.chuchuyajun.flappybird.sound.SoundController;
import com.chuchuyajun.flappybird.sound.SoundController.SOUND_TYPE;

public class Bird implements Drawable {

	private float velocityY = 0;
	private float y = 0;
	private int GRAVITY = 60;

	private Sprite spriteBird;
	private Boolean flag_touchDone = true;
	private SoundController soundController;

	private final static int flappyDetalTime = 20;
	private int flappyTime = 0;
	private static Boolean flag_startFlappy = false;
	public float x;
	private float birdHeight;
	public float birdWidth;
	public List<Texture> birdTextures;
	public Texture birdTexture;

	public Bird(List<Texture> birdTextures) {
		this.birdTextures = new ArrayList<Texture>();
		this.soundController = new SoundController();
		this.birdTextures = birdTextures;
		birdTexture = birdTextures.get(1);
		spriteBird = new Sprite(birdTexture);
		this.GRAVITY = R.PHONE_WIDTH/5;
	}

	@Override
	public void tick(float deltaTime) {
		velocityY += this.GRAVITY * deltaTime;
		this.y = this.y - (R.speedDownPro * velocityY);
	}

	@Override
	public void screenTouched() {
		if (R.GameState == R.STATE_RUNNING && flag_touchDone) {
			flag_touchDone = false;
			velocityY = -R.speedUpPro* this.GRAVITY;
			flag_startFlappy = true;
			soundController.playSound(SOUND_TYPE.BIRD_FLAY);
			System.out.print("ÎÒÌø¡­¡­");
			flag_touchDone = true;
		}
//		this.flappyTime = 0;
		
	}

	public void updateBirdTexture() {
		// TODO Auto-generated method stub
		if (R.birdFlappyMode == FLAPPY_MODE.AUTO_FLAPPY) {
			autoFlappy();
		} else if (R.birdFlappyMode == FLAPPY_MODE.TOUCH_TO_FLAPPY) {
			touchToFlappy();
		}

		if (R.GameState >= R.STATE_DEATH_THROES){
			spriteBird.setTexture(birdTextures.get(3));
		}
	}

	private void autoFlappy() {
		if (flappyTime % R.AUTO_FLAPYY_DELAY == 0){
			flappyTime++;
		spriteBird.setTexture(birdTextures.get(flappyTime % 3));
		}
	}

	private void touchToFlappy() {
		if (flag_startFlappy) {
			if (flappyTime == flappyDetalTime) {
				// System.out.println("³á°òÍùÉÏ");
				spriteBird.setTexture(birdTextures.get(0));
				soundController.playSound(SOUND_TYPE.BIRD_FLAY);
			} else if (flappyTime == flappyDetalTime * 3) {
				// System.out.println("³á°òË®Æ½£¬»¬Ïè");
				spriteBird.setTexture(birdTextures.get(1));
			}
		}
	}

	@Override
	public void draw(SpriteBatch batch) {
		flappyTime++;
		if(R.GameState < R.STATE_DEATH_THROES){
			updateBirdTexture();
		}
//		if(R.GameState < R.STATE_RUNNING){
//			this.y = 0;
//			this.velocityY = 0;
//		}
		
		spriteBird.setPosition(this.x, this.y);
		spriteBird.setRotation(getTiltDegrees());
		spriteBird.draw(batch);
		// }
	}

	@Override
	public void resize(int width, int height) {
		this.birdWidth = birdTexture.getWidth()*R.SCREEN_WIDTH_SCALE;
		this.birdHeight = birdTexture.getHeight()*R.SCREEN_HEIGHT_SCALE;
		
		this.x = -(width / 4);
		spriteBird.setSize(birdWidth, birdHeight);
		spriteBird.setOrigin(spriteBird.getWidth() / 2,
				spriteBird.getHeight() / 2);
	}

	@Override
	public boolean isInCollisionWithBird(Bird bird) {
		return false;
	}

	private float getTiltDegrees() {
		if (this.velocityY <= 0)
			return Math.min(20, -(this.velocityY/R.SCREEN_WIDTH_SCALE));
		if (this.velocityY > 0)
			return Math.max(-85, -(this.velocityY/R.SCREEN_WIDTH_SCALE));
		return 0;
	}

	public float getY() {
		return y;
	}

	public float getX() {
		return x;
	}

	public void resetBird() {
		this.y = 0;
		this.velocityY = 0;
		spriteBird.setRotation(0);
	}

	public Rectangle getRectangle() {
		return new Rectangle(this.x, this.y, this.birdWidth * 0.8f,
				this.birdHeight * 0.8f);
	}

}
