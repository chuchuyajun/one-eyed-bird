package com.chuchuyajun.flappybird;


import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.chuchuyajun.flappybird.drawable.Background;
import com.chuchuyajun.flappybird.drawable.Bird;
import com.chuchuyajun.flappybird.drawable.Drawable;
import com.chuchuyajun.flappybird.drawable.PipeFactory;
import com.chuchuyajun.flappybird.server.R;
import com.chuchuyajun.flappybird.server.R.RES;
import com.chuchuyajun.flappybird.stage.OverStage;
import com.chuchuyajun.flappybird.stage.ReadyStage;
import com.chuchuyajun.flappybird.stage.ScoreStage;
import com.chuchuyajun.flappybird.stage.StartStage;


public class MainGame extends Game {
	private ResourceLoader RL;
	private OverStage overStage;
	private StartStage startStage;
	private ReadyStage readyStage;
	private ScoreStage scoreStage;
	private InputProcessor currentInputProcessor;
	private Bird bird;
	private PipeFactory pipes;
	private Background floor;
	private Background background;

	private OrthographicCamera camera;
	private SpriteBatch batch;

	private List<Drawable> drawables;
	public List<Texture> birdTextures;


	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		R.SCREEN_WIDTH_SCALE = (float)width/R.DESIGN_SCREEN_SIZE_WIDTH;
		R.SCREEN_HEIGHT_SCALE = (float)height/R.DESIGN_SCREEN_SIZE_HEIGHT;
		R.PHONE_WIDTH = width;
		R.PHONE_HEIGHT = height;
		camera = new OrthographicCamera(width, height);
		for (Drawable drawable : drawables) {
			drawable.resize(width, height);
		}
	}

	@Override
	public void create() {
		R.initData();
		this.RL = new ResourceLoader();
		this.batch = new SpriteBatch();
		this.drawables = new ArrayList<Drawable>();
		startStage = new StartStage();
		readyStage = new ReadyStage();
		overStage = new OverStage();
		scoreStage = new ScoreStage();

		this.birdTextures = new ArrayList<Texture>();
		birdTextures.add(getTextureFromImage(R.IMAGE_BIRD_1));
		birdTextures.add(getTextureFromImage(R.IMAGE_BIRD_2));
		birdTextures.add(getTextureFromImage(R.IMAGE_BIRD_3));
		birdTextures.add(getTextureFromImage(R.IMAGE_BIRD_4));

		//以下这句创建背景的代码为直接从大块的resource.png中切割出背景图片并
//		this.background = new Background(RL.gameTextureRegion(R.image.get(RES.IMG_BACKGROUND)), R.SCROLL_SPEED_BACKGROUND*R.SCREEN_WIDTH_SCALE,R.BACKGROUND_TYPE.BACKGROUND);
//		this.floor = new Background(RL.gameTextureRegion(R.image.get(RES.IMG_ROAD)), R.SCROLL_SPEED_FLOOR*R.SCREEN_WIDTH_SCALE,R.BACKGROUND_TYPE.FLOOR);
		
		//从分离出来的背景图片中切割
		Texture bgTexture = new Texture(R.IMG_BACKGROUND);
		bgTexture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
		this.background = new Background(RL.getBackground(R.image.get(RES.IMG_BACKGROUND)), R.SCROLL_SPEED_BACKGROUND*R.SCREEN_WIDTH_SCALE,R.BACKGROUND_TYPE.BACKGROUND);
		this.floor = new Background(RL.getBackground(R.image.get(RES.IMG_ROAD)), R.SCROLL_SPEED_FLOOR*R.SCREEN_WIDTH_SCALE,R.BACKGROUND_TYPE.FLOOR);
		
		this.bird = new Bird(birdTextures);
		this.pipes = new PipeFactory(Gdx.files.internal(R.IMAGE_PIPE),Gdx.files.internal(R.IMAGE_PIPE_TOP));
		
		
		drawables.add(background);
		drawables.add(pipes);
		drawables.add(bird);
		drawables.add(floor);
	}
	private Texture getTextureFromImage(String path) {
		return new Texture(Gdx.files.internal(path));
	}
	@Override
	public void render() {
		// TODO Auto-generated method stub
		doRender();
		if(R.GameState == R.STATE_RUNNING){
			if(inputTouched() ) { bird.screenTouched(); }
		}
//		checkAction();
		selectStage();
	}
	
	private void doRender(){
		Gdx.gl.glClearColor(1, 1, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		for (Drawable drawable : drawables) {
			drawable.draw(batch);
		}
		batch.end();
	}
	
	private void selectStage() {
		switch (R.GameState) {
		case R.STATE_START:
			// System.out.println("进入游戏，展示开场画面");
			this.resize(R.PHONE_WIDTH, R.PHONE_HEIGHT);
			R.GameScore = 0;
			
//			background.tick(Gdx.graphics.getDeltaTime());  //<=========================================开场界面不滚动
			floor.tick(Gdx.graphics.getDeltaTime());
			pipes.resetPipes();
			bird.resetBird();
			Gdx.input.setInputProcessor(startStage);
			startStage.act();
			startStage.draw();
			break;

		case R.STATE_READY:
			// System.out.println("准备开始游戏");
			background.tick(Gdx.graphics.getDeltaTime());
			floor.tick(Gdx.graphics.getDeltaTime());
			Gdx.input.setInputProcessor(readyStage);
			readyStage.act();
			readyStage.draw();
			break;

		case R.STATE_READYTORUN:
			// System.out.println("游戏初始化");
			bird.screenTouched();
			overStage.resetState();
			R.GameState = R.STATE_RUNNING;
			break;

		case R.STATE_RUNNING:
			// System.out.println("正在游戏");
			currentInputProcessor = Gdx.input.getInputProcessor();
			background.tick(Gdx.graphics.getDeltaTime());
			pipes.tick(Gdx.graphics.getDeltaTime());
			floor.tick(Gdx.graphics.getDeltaTime());
			bird.tick(Gdx.graphics.getDeltaTime());
			if(pipes.isInCollisionWithBird(bird)){
				Gdx.input.setInputProcessor(null);
				R.GameState = R.STATE_DEATH_THROES;
			}
			if(floor.isInCollisionWithBird(bird)){R.GameState = R.STATE_LOST;}
			scoreStage.act();
			scoreStage.draw();
			break;

		case R.STATE_DEATH_THROES:
//			 System.out.println("垂死挣扎");
			bird.tick(Gdx.graphics.getDeltaTime());
			if(floor.isInCollisionWithBird(bird)){
				R.GameState = R.STATE_LOST;
			}
			break;

		case R.STATE_LOST:
			Gdx.input.setInputProcessor(currentInputProcessor);
			R.GameState = R.STATE_OVER;
			break;

		case R.STATE_OVER:
			// System.out.println("游戏结束");
			Gdx.input.setInputProcessor(overStage);
			overStage.act();
			overStage.draw();
			break;
		}
	}

	@Override
	public void dispose() {
		batch.dispose();
	}
    
	private boolean inputTouched() {
			return Gdx.input.justTouched() || Gdx.input.isKeyPressed(Input.Keys.SPACE) || Gdx.input.isKeyPressed(Input.Keys.UP);
	}
}


