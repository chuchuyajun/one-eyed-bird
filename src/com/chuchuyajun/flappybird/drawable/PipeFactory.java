package com.chuchuyajun.flappybird.drawable;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.chuchuyajun.flappybird.server.R;
import com.chuchuyajun.flappybird.sound.SoundController;
import com.chuchuyajun.flappybird.sound.SoundController.SOUND_TYPE;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PipeFactory implements Drawable {

	private static final int MIN_PIPES_IN_MEMORY = 5;
	private int MIN_PIPE_Y;
	private int MAX_PIPE_Y;
	private float PIPE_MIN_HEIGHT = 0; 

	private final Texture texturePipe;
	private final Texture texturePipeTop;

	private SoundController soundController;

	private float screenWidth;
	private float screenHeight;
	public float pipeWidth;
	private float textureScale;

	private float pipeHeight;
	private List<Pipe> pipes = new ArrayList<Pipe>();

	private float pipeGap = 0;
	private Boolean flag_countScore = true;
	
	private float PIPE_INTERVAL_PIXELS = 400f;

	private final Random random = new Random(System.currentTimeMillis());

	public PipeFactory(FileHandle imageBottom, FileHandle imageTop) {
		this.texturePipe = new Texture(imageBottom);
		this.texturePipeTop = new Texture(imageTop);
		this.soundController = new SoundController();
		this.PIPE_MIN_HEIGHT = R.PHONE_HEIGHT * 0.2f;
		this.PIPE_INTERVAL_PIXELS = R.PHONE_WIDTH * R.PIPE_INTERVAL_OF_SCREEN;
//		this.pipes.add(new Pipe(2 * PIPE_INTERVAL_PIXELS, generatePipeY()));
		this.pipeGap = R.PHONE_WIDTH * R.PIPES_GAP_OF_SCREEN;
		this.MIN_PIPE_Y = (int)(-R.PHONE_HEIGHT*0.1f);
		this.MAX_PIPE_Y = (int)(R.PHONE_HEIGHT/2 - this.PIPE_MIN_HEIGHT - this.pipeGap);
		this.pipes.add(new Pipe(2 * PIPE_INTERVAL_PIXELS, generatePipeY()));
		System.out.println("pipeGap="+this.pipeGap+"---MIN_PIPE_Y="+this.MIN_PIPE_Y+"---MAX_PIPE_Y="+this.MAX_PIPE_Y
				+"---PHONE_HEIGHT="+R.PHONE_HEIGHT);
	}

	public void resetPipes() {
		flag_countScore = true;
		if(pipes.size() > 0){
			while (pipes.get(0).x < 2 * PIPE_INTERVAL_PIXELS) {
				pipes.remove(0);
			}
		}
	}

	private int generatePipeY() {
		return this.random.nextInt(MAX_PIPE_Y - MIN_PIPE_Y) + MIN_PIPE_Y ;
	}

	@Override
	public void tick(float deltaTime) {
		for (Pipe pipe : pipes) {
			pipe.x -= R.SCROLL_SPEED_FLOOR* R.SCREEN_WIDTH_SCALE * deltaTime;
		}
		
		if (this.pipes.get(0).hasBirdPassed(this.screenWidth, pipeWidth)) {
			if (flag_countScore) {
				R.GameScore++;
				if(R.GameScore > R.BestScore)R.BestScore=R.GameScore;
				soundController.playSound(SOUND_TYPE.BIRD_PASS);
				flag_countScore = false;
				System.out.println("\n当前游戏分数为：" + R.GameScore);
			}

		}
		while (this.pipes.size() > 0 && this.pipes.get(0).hasMovedOffScreen(this.screenWidth)) {
			this.pipes.remove(0);
			flag_countScore = true;
		}
		while (this.pipes.size() < MIN_PIPES_IN_MEMORY) {
			this.pipes.add(new Pipe(this.pipes.get(this.pipes.size() - 1).x
					+ PIPE_INTERVAL_PIXELS+pipeWidth, generatePipeY()));
		}
	}

	@Override
	public void draw(SpriteBatch batch) {
		for (Pipe pipe : this.pipes) {
			batch.draw(this.texturePipe, pipe.x, pipe.y - this.pipeHeight, pipeWidth, pipeHeight);
			batch.draw(this.texturePipeTop, pipe.x, pipe.y + pipeGap, pipeWidth, pipeHeight);
		}
	}

	@Override
	public void resize(int width, int height) {
		this.screenWidth = width;
		this.screenHeight = height;
		this.pipeWidth = this.screenHeight *R.PIPE_WIDTH_OF_SCREEN;
		this.textureScale = this.pipeWidth / this.texturePipe.getWidth();
		this.pipeHeight = this.texturePipe.getHeight() * textureScale;
	}

	@Override
	public void screenTouched() {
	}

	@Override
	public boolean isInCollisionWithBird(Bird bird) {
		for (Pipe pipe : pipes) {
			if (Intersector.intersectRectangles(bird.getRectangle(),pipe.getBottomRectangle(pipeWidth, pipeHeight))	|| 
					Intersector	.intersectRectangles(bird.getRectangle(), pipe.getTopRectangle(pipeWidth, pipeHeight,pipeGap))) {
				soundController.playSound(SOUND_TYPE.BIRD_COLLISON);
				return true;
			}
		}
		return false;
	}

	private class Pipe {
		float x;
		float y;

		private Pipe(float x, float y) {
			this.x = x;
			this.y = y;
		}

		public Rectangle getBottomRectangle(float pipeWidth, float pipeHeight) {
			return new Rectangle(this.x, this.y-pipeHeight, pipeWidth, pipeHeight);
		}

		public Rectangle getTopRectangle(float pipeWidth, float pipeHeight,
				float pipeGap) {
			return new Rectangle(this.x, this.y + pipeGap ,
					pipeWidth, pipeHeight);
		}

		public boolean hasMovedOffScreen(float screenWidth) {
			return this.x < -screenWidth;
		}

		public boolean hasBirdPassed(float screenWidth, float pipeWidth) {
			return this.x + pipeWidth < -(screenWidth / 4);
		}

	}

}
