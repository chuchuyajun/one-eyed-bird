package com.chuchuyajun.flappybird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.chuchuyajun.flappybird.server.R;
import com.chuchuyajun.flappybird.server.R.RES;

public class ResourceLoader {

	public ResourceLoader() {
		R.res_Texture = new Texture(Gdx.files.internal(R.IMG_RESOURCE));
		R.bg_Texture = new Texture(R.IMG_BACKGROUND);
		R.bg_Texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
	}

	public Image GameImage(int[] positionInfo) {
		if (positionInfo.length < 4) {
			System.out.println("游戏元素位置信息配置错误！获取元素图片失败！请在R.java文件中正确配置！");
			return null;
		}
		Image image = new Image(new TextureRegion(R.res_Texture,
				positionInfo[0], positionInfo[1], positionInfo[2],
				positionInfo[3]));
		image.setScale(R.SCREEN_WIDTH_SCALE, R.SCREEN_HEIGHT_SCALE);
		float drawPositionX = positionInfo[4] * R.SCREEN_WIDTH_SCALE;
		float drawPositionY = R.PHONE_HEIGHT
				- (positionInfo[5] * R.SCREEN_HEIGHT_SCALE)
				- (image.getHeight() * R.SCREEN_HEIGHT_SCALE);
		image.setPosition(drawPositionX, drawPositionY);
		return image;
	}

	public TextureRegion gameTextureRegion(int[] positionInfo) {
		if (positionInfo.length < 4) {
			System.out.println("游戏元素位置信息配置错误！获取局部贴图失败！请在R.java文件中正确配置！");
			return null;
		}
		return new TextureRegion(R.res_Texture, positionInfo[0],
				positionInfo[1], positionInfo[2], positionInfo[3]);
	}
	
	public TextureRegion getBackground(int[] positionInfo){
		if (positionInfo.length < 4) {
			System.out.println("背景定位位置信息配置错误！获取局部贴图失败！请在R.java文件中正确配置！");
			return null;
		}
		return new TextureRegion(R.bg_Texture,positionInfo[0], positionInfo[1], positionInfo[2],positionInfo[3]);
	}

}
