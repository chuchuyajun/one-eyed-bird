package com.chuchuyajun.flappybird.drawable;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.chuchuyajun.flappybird.ResourceLoader;
import com.chuchuyajun.flappybird.server.R;
import com.chuchuyajun.flappybird.server.R.RES;

public class Medal extends Actor {
	private List<TextureRegion> MEDALS;
	private TextureRegion IMG_MEDA;
	private float x;
	private float y;

	public Medal(int[] medalPosition) {
		if (medalPosition.length < 2) {
			System.out.println("奖牌位置信息配置错误！！请在R.java文件中正确配置MEDAL_POSITION常量！");
			return;
		}
		this.MEDALS = new ArrayList<TextureRegion>();
		ResourceLoader rLoader = new ResourceLoader();
		TextureRegion TR_MEDAL_NONE = rLoader.gameTextureRegion(R.image.get(RES.IMG_MEDAL_NONE));
		TextureRegion TR_MEDAL_GOLD = rLoader.gameTextureRegion(R.image
				.get(RES.IMG_MEDAL_GOLD));
		TextureRegion TR_MEDAL_SILVER = rLoader.gameTextureRegion(R.image
				.get(RES.IMG_MEDAL_SILVER));
		TextureRegion TR_MEDAL_BRONZE = rLoader.gameTextureRegion(R.image
				.get(RES.IMG_MEDAL_BRONZE));
		TextureRegion TR_MEDAL_BLACK = rLoader.gameTextureRegion(R.image
				.get(RES.IMG_MEDAL_BLACK));
		TextureRegion TR_MEDAL_WHITE = rLoader.gameTextureRegion(R.image
				.get(RES.IMG_MEDAL_WHITE));

		MEDALS.add(TR_MEDAL_NONE);
		MEDALS.add(TR_MEDAL_GOLD);
		MEDALS.add(TR_MEDAL_SILVER);
		MEDALS.add(TR_MEDAL_BRONZE);
		MEDALS.add(TR_MEDAL_BLACK);
		MEDALS.add(TR_MEDAL_WHITE);

		IMG_MEDA = MEDALS.get(0);

		this.x = medalPosition[0] * R.SCREEN_WIDTH_SCALE;
		this.y = R.PHONE_HEIGHT - medalPosition[1] * R.SCREEN_HEIGHT_SCALE
				- IMG_MEDA.getRegionHeight() * R.SCREEN_HEIGHT_SCALE;

	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		int medal_index = getMedalIndex();

		if (medal_index != -1)
			IMG_MEDA = MEDALS.get(medal_index);
		// 奖牌还是按照横向的来缩小吧，不然遇到奇葩的手机分辨率奖牌看起来是扁的
		batch.draw(IMG_MEDA, this.x, this.y, IMG_MEDA.getRegionWidth()
				* R.SCREEN_WIDTH_SCALE, IMG_MEDA.getRegionHeight()
				* R.SCREEN_WIDTH_SCALE);
		// System.out.println("奖牌横坐标为"+this.x+"横坐标为"+this.y+"宽度为"+IMG_MEDA.getRegionWidth()*R.SCREEN_WIDTH_SCALE+"高度为"+IMG_MEDA.getRegionHeight()*R.SCREEN_HEIGHT_SCALE);
	}

	private int getMedalIndex() {
		if (R.GameScore >= 10 && R.GameScore <= 20) {// 10-20铜牌
		// System.out.println("分数为"+R.GameScore+",颁发铜牌");
			return 3;
		} else if (R.GameScore >= 21 && R.GameScore <= 40) {// 21-40 银牌
		// System.out.println("分数为"+R.GameScore+",颁发银牌");
			return 2;
		} else if (R.GameScore >= 41 && R.GameScore <= 70) {// 41-70 金牌
		// System.out.println("分数为"+R.GameScore+",颁发金牌");
			return 4;
		} else if (R.GameScore >= 71 && R.GameScore <= 100) {// 71-100白金
		// System.out.println("分数为"+R.GameScore+",颁发白金奖牌");
			return 1;
		} else if (R.GameScore >= 101) {// 101- 黑色
		// System.out.println("分数为"+R.GameScore+",颁发黑色奖牌");
			return 4;
		} else {
			// System.out.println("分数为"+R.GameScore+",蛋毛都没一根");
			return 0;
		}
	}
}
