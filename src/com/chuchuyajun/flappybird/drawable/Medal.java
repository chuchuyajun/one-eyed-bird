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
			System.out.println("����λ����Ϣ���ô��󣡣�����R.java�ļ�����ȷ����MEDAL_POSITION������");
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
		// ���ƻ��ǰ��պ��������С�ɣ���Ȼ����������ֻ��ֱ��ʽ��ƿ������Ǳ��
		batch.draw(IMG_MEDA, this.x, this.y, IMG_MEDA.getRegionWidth()
				* R.SCREEN_WIDTH_SCALE, IMG_MEDA.getRegionHeight()
				* R.SCREEN_WIDTH_SCALE);
		// System.out.println("���ƺ�����Ϊ"+this.x+"������Ϊ"+this.y+"���Ϊ"+IMG_MEDA.getRegionWidth()*R.SCREEN_WIDTH_SCALE+"�߶�Ϊ"+IMG_MEDA.getRegionHeight()*R.SCREEN_HEIGHT_SCALE);
	}

	private int getMedalIndex() {
		if (R.GameScore >= 10 && R.GameScore <= 20) {// 10-20ͭ��
		// System.out.println("����Ϊ"+R.GameScore+",�䷢ͭ��");
			return 3;
		} else if (R.GameScore >= 21 && R.GameScore <= 40) {// 21-40 ����
		// System.out.println("����Ϊ"+R.GameScore+",�䷢����");
			return 2;
		} else if (R.GameScore >= 41 && R.GameScore <= 70) {// 41-70 ����
		// System.out.println("����Ϊ"+R.GameScore+",�䷢����");
			return 4;
		} else if (R.GameScore >= 71 && R.GameScore <= 100) {// 71-100�׽�
		// System.out.println("����Ϊ"+R.GameScore+",�䷢�׽���");
			return 1;
		} else if (R.GameScore >= 101) {// 101- ��ɫ
		// System.out.println("����Ϊ"+R.GameScore+",�䷢��ɫ����");
			return 4;
		} else {
			// System.out.println("����Ϊ"+R.GameScore+",��ë��ûһ��");
			return 0;
		}
	}
}
