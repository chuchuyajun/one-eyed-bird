package com.chuchuyajun.flappybird;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.chuchuyajun.flappybird.drawable.Bird;

public interface Drawable {

	public void tick(float deltaTime);

	public void draw(SpriteBatch batch);

	public void resize(int width, int height);

	public void screenTouched();

	public boolean isInCollisionWithBird(Bird bird);

}
