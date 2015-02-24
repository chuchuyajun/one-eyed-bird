package com.chuchuyajun.flappybird.drawable;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.chuchuyajun.flappybird.server.R;
import com.chuchuyajun.flappybird.sound.SoundController;
import com.chuchuyajun.flappybird.sound.SoundController.SOUND_TYPE;

public class Background implements Drawable {

	private final TextureRegion textureBackground;
	private final float speed;
	private float landLine = -1000f;
	private float scrollingX = 0;
	private float canvasHeight;
	private float canvasWidth;
	private float backgroundY;
	private float textureScale;
	private float textureWidthScaled;
	private float textureHeightScaled;

	private SoundController soundController;

	public static int DESIGN_BACKGROUND_HEIGHT = 1024;

	public R.BACKGROUND_TYPE MYSELF;

	public Background(TextureRegion tr, float speed, R.BACKGROUND_TYPE TYPE) {
		soundController = new SoundController();
		this.MYSELF = TYPE;
		this.textureBackground = tr;
		this.speed = speed;
	}

	@Override
	public void tick(float deltaTime) {
		scrollingX -= (deltaTime * speed);
	}

	@Override
	public void draw(SpriteBatch batch) {
		batch.draw(textureBackground, scrollingX, backgroundY,
				this.textureWidthScaled, this.textureHeightScaled);
		batch.draw(textureBackground, scrollingX - this.textureWidthScaled,
				backgroundY, this.textureWidthScaled, this.textureHeightScaled);
		if (scrollingX < (-this.canvasWidth / 2)) {
			scrollingX += this.textureWidthScaled;
		}
	}

	@Override
	public void resize(int width, int height) {
		this.canvasWidth = width;
		this.canvasHeight = height;
		this.backgroundY = 0 - (this.canvasHeight / 2);
		if (this.MYSELF == R.BACKGROUND_TYPE.BACKGROUND) {
			this.landLine = this.backgroundY;
			this.textureScale = this.canvasHeight
					/ this.textureBackground.getRegionHeight();
			this.textureWidthScaled = this.textureBackground.getRegionWidth()
					* textureScale;
			this.textureHeightScaled = height;
		} else if (this.MYSELF == R.BACKGROUND_TYPE.FLOOR) {
//			R.landLine = -((this.canvasHeight / 10) * 2.8128f);
			this.textureScale = this.canvasHeight / DESIGN_BACKGROUND_HEIGHT;
			this.textureWidthScaled = this.textureBackground.getRegionWidth()
					* textureScale;
			this.textureHeightScaled = this.textureBackground.getRegionHeight()
					* textureScale;
			this.landLine = this.textureHeightScaled - this.canvasHeight/2;
		}
	}

	@Override
	public void screenTouched() {
	}

	@Override
	public boolean isInCollisionWithBird(Bird bird) {
		if (bird.getY() < this.landLine) {
			soundController.playSound(SOUND_TYPE.BIRD_DIE);
			return true;
		}

		return false;
	}

}
