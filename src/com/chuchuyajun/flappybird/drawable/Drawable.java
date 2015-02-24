package com.chuchuyajun.flappybird.drawable;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Drawable {

	public void tick(float deltaTime);

	public void draw(SpriteBatch batch);

	public void resize(int width, int height);

	public void screenTouched();

	public boolean isInCollisionWithBird(Bird bird);

}
