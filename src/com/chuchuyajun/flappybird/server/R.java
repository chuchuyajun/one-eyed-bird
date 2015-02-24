package com.chuchuyajun.flappybird.server;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.Texture;

public class R {
	// =================================================================================
	// ������ö����������
	// =================================================================================
	// ���������͡���Ϊ����ͱ��������Ҷ���ΪBackground�ࡣ������Ҫ�и���־������
	public enum BACKGROUND_TYPE {
		BACKGROUND, FLOOR
	};

	// ��Ϸ״̬����Ϸ������⼸��״̬�����������л��ͽ�ɫ�Ķ����Լ�λ��
	public static final int STATE_START = 1;
	public static final int STATE_READY = 2;
	public static final int STATE_READYTORUN = 3;
	public static final int STATE_RUNNING = 4;
	public static final int STATE_DEATH_THROES = 5;
	public static final int STATE_LOST = 6;
	public static final int STATE_OVER = 7;

	// ����������ģʽ
	public static enum FLAPPY_MODE {
		NO_FLAPPY, TOUCH_TO_FLAPPY, AUTO_FLAPPY
	}

	// ��ϷԪ�ؼ���
	public static enum RES {
		// ����
		IMG_BACKGROUND,
		// ��
		IMG_BIRD_YELLOW_1, IMG_BIRD_YELLOW_2, IMG_BIRD_YELLOW_3,
		// ����
		IMG_MEDAL_NONE, IMG_MEDAL_GOLD, IMG_MEDAL_SILVER, IMG_MEDAL_BRONZE, IMG_MEDAL_BLACK, IMG_MEDAL_WHITE,
		// ����
		IMG_NUM_0, IMG_NUM_1, IMG_NUM_2, IMG_NUM_3, IMG_NUM_4, IMG_NUM_5, IMG_NUM_6, IMG_NUM_7, IMG_NUM_8, IMG_NUM_9,
		// ·��
		IMG_ROAD,
		// ����
		IMG_PIPE_FLOOR, IMG_PIPE_TOP,
		// ��ɫ��
		IMG_BIRD_GRAY_1, IMG_BIRD_GRAY_2, IMG_BIRD_GRAY_3,
		// ������
		IMG_RESULT_PANEL,
		// ����
		IMG_HAND_POINTER,
		// TAP��ͷ
		IMG_TIP_TAP,
		// ����ͼƬ
		IMG_TXT_GETREADY, IMG_TXT_GAMEOVER, IMG_TXT_COPYRIGHT, IMG_TXT_GAMENAME,
		// ��ť
		IMG_BUTTON_MENU, IMG_BUTTON_START, IMG_BUTTON_OK, IMG_BUTTON_SHARE, IMG_BUTTON_SCORE,
		// ������Ϸʱ����Ļ�Ϸ���ʾ�ķ���λ��
		RUNNING_SCREEN_SCORE_POSITION,
		// ��Ϸ������ʾ�����ʱ�����������ʾ�ķ���λ��
		RESULT_PANEL_SCORE_POSITION,BEST_SCORE_POSITION,
		// ��Ϸ����������ʾ��λ��
		MEDAL_POSITION
	}

	// ===========================������ö��������������======================================

	// =================================================================================
	// ��Ϸ��Դ��λ�ͳ����и�Ԫ�ط���λ����������
	// =================================================================================
	// ͼƬ�ز�·����֮���԰�С������ܵ�ͼƬ�������������Ϊ��Ϸ�Ѿ�ʵ�ִ������Դ����ϣ�
	// ����ֱ���滻С������ܵ�ͼƬ���滻����Ϸ�е�Ӣ�ۺ͵��ˣ�������Ҫ���κδ����޸ġ�
	// ��ʹ��Ҫ�޸ĵ���Ϸ�������棬Ҳֻ��Ҫ�滻��resource.png����������ж�λ���ü���
	public static final String IMG_RESOURCE = "images/resource.png";
	public static final String IMG_BACKGROUND = "images/background.png";
	public static final String IMAGE_BIRD_1 = "images/bird1.png";
	public static final String IMAGE_BIRD_2 = "images/bird2.png";
	public static final String IMAGE_BIRD_3 = "images/bird3.png";
	public static final String IMAGE_BIRD_4 = "images/bird4.png";
	public static final String IMAGE_PIPE = "images/pipe.png";
	public static final String IMAGE_PIPE_TOP = "images/pipe_top.png";
	// ---------------------------------------------------------------------------
	public static Texture res_Texture;
	public static Texture bg_Texture;
	// ---------------------------------------------------------------------------
	public static final String AUDIO_BIRD_DIE = "sounds/powerdown04.wav";
	public static final String AUDIO_BIRD_FLAY = "sounds/powerdown01.wav";
	public static final String AUDIO_BIRD_PASS = "sounds/pickup03.wav";
	public static final String AUDIO_BIRD_COLLISION = "sounds/collision.wav";
	public static final String AUDIO_BUTTON_PRESS = "sounds/poyo.wav";
	// ---------------------------------------------------------------------------
	public static final float AUDIO_VOLUME = 1.0f; // ������С[0,1f]

	// ============================��Ϸ��Դ�ͻ����趨����=================================

	// =================================================================================
	// ��Ϸ��������
	// =================================================================================
	// ��ʼ��Ļ��С�����ݵ�����ͼ���ʱ���ֻ���Ļ�ߴ����������ͼƬ�ǰ���640*1136����Ƶģ�
	// �������ֵֻ�е���Ϸ��ʹ�õı����ߴ����Ϸ������������ǵĳߴ粻һ��ʱ����Ϸ�Ż����
	// �������ʱ�ĳߴ�����Ϊ�����ߴ硣����һ�������Ϸʱ�������ߴ��Ӧ���ǳ����ߴ硣������
	// ������ֵ�������д�������ҵĳ������ʱ�ǲ���1136*640����Ļ����ز����Ķ�λ����ʾ����
	// ������Ϸ���زĺͱ�������1024*748���������鷳��Ϊ�˲����º��涨λ������ֻ�滻�˱�����
	// ���ݡ�����Ϸ���������ݻ��ǰ���1136*640������ѹ����1024*748�ġ�Ҳ���������ˣ�ѹ���ˡ�
	// ������Ϸ������Եñ�һ�㡣������Ȼ���漯���ṩ���زĶ�λ�ĵط����κ��˶���diy�����Ϸ
	public final static int DESIGN_SCREEN_SIZE_WIDTH = 748;
	public final static int DESIGN_SCREEN_SIZE_HEIGHT = 1024;

	// ʵ���ֻ��ߴ�������˴�������ֻ��Ԥ��ֵ��������Ϸ���Զ��������е��ֻ�������������ֵ
	public static int PHONE_WIDTH = 480;
	public static int PHONE_HEIGHT = 320;

	// ��Ϸ�õ�����Դ��Ҳ���ǵ�����ͼƬ�����Ϸ����ĳߴ���ʵ��������Ϸ���ֻ��ߴ�ı�������Ϸ��ʼ����Զ��������ֵ��
	public static float SCREEN_WIDTH_SCALE = 1.0f;
	public static float SCREEN_HEIGHT_SCALE = 1.0f;
	
	// ����Ĺ����ٶȺͱ����Ĺ����ٶ�
	public static final int SCROLL_SPEED_FLOOR = 300;
	public static final int SCROLL_SPEED_BACKGROUND = 160;

	// ��Ϸ������������ģʽ��NO_FLAPPYΪʼ�ղ�ɿ�����,TOUCH_TO_FLAPPYΪ���������Ļ��Ż�ɿ�����
	// ����ģʽ�Ƚ�������ʵ����Ϊ֮��С��ɿ�����Ż����Ϸɡ������ǲ�ͣ��������򻹻���ٽ���,AUTO_FLAPPY
	// Ϊʼ�ղ�ͣ��ɿ�������Ȼ���ģʽ��С���Եû���Щ�����ǲ����ϳ���������ϷĬ�ϻ������ģʽ��
	public static FLAPPY_MODE birdFlappyMode = FLAPPY_MODE.AUTO_FLAPPY;

	// ��Ϸ����ʱ�ĳ�ʼ״̬�������Դ������ý�����Ϸ����ʾ�ĸ�����
	public static int GameState = R.STATE_START;

	// С��ɿ���������������õ�ԽС��ɿ�����Խ��
	public static final int AUTO_FLAPYY_DELAY = 5;

	// ��ʼ��Ϸ������Ӧ������Ϊ�㡣���ó����������������ڲ�������ƺͷ�������ʾ
	public static int GameScore = 0;
	
	// ��ʼ��Ϸ������Ӧ������Ϊ�㡣���ó����������������ڲ�������ƺͷ�������ʾ
	public static int BestScore = 0;

	// ˮƽ��������������֮��ļ��ռ�ֻ���Ļ�ı���
	public static final float PIPE_INTERVAL_OF_SCREEN = 0.3f;
	
	// ��������֮��ļ��ռ�ֻ���Ļ�ı���
	public static final float PIPES_GAP_OF_SCREEN = 0.3f;
	
	// ���ܿ��ռ�ֻ���Ļ��ȵı���
	public static final float PIPE_WIDTH_OF_SCREEN = 0.1f;
	
	// �����ƶ��ٶ�
	public static final float PIPE_SPEED_OF_SCREEN = 0.1f;
	
	// С��Ŀ��ռ�ֻ���Ļ�ı���
	public static final float BIRD_WIDTH_OF_SCREEN = 0.1f;

    //С���½����ٶ�Ȩ�أ�����Ӱ�쵱ǰ�ٶ�
    public static final float speedDownPro  = 0.28f;
    
    //С���������ٶ�Ȩ�أ�����Ӱ�쵱ǰ�ٶ�
    public static final float speedUpPro = 0.28f;
	// ==========================================��Ϸ�������ý���=================================================

	// ========================================================================================================================
	// ��Ϸ���õ��ĸ�Ԫ�����ز��ļ��е�λ�ö�λ�ߴ��С�Լ�����ʱ���������
	// ========================================================================================================================

	public static Map<RES, int[]> image = new HashMap<RES, int[]>();

	public static void initData() {
		/*�������鳤��Ϊ6�ģ�
		 * 				  ����IMG_BACKGROUND������Ϊ{ 1300, 0, 748, 1024, 0, 0 }���ʾ
		 * ��images/resource.png�У�������Ϊ1300��������Ϊ0�ĵط���ʼ��ȡ���Ϊ748���߶�Ϊ
		 * 1204�ľ������������Ȼ������Ҫ��ʾ�ĵط��Ӻ�����Ϊ0��������ҲΪ0�ĵط���ʼ���ơ�����
		 * �Ŀ�Ⱥ͸߶Ȳ���Ҫ�Լ����á�������ֻ��ֱ���������Ӧ��
		 * 
		 * ����Ϊ4�ģ�����IMG_NUM_0������Ϊ { 285, 0, 23, 37 }����ʾ��resource.png��285,0��
		 * �ط���ȡ��Ϊ23����Ϊ37�ľ��Ρ�����ͳ���Ϊ6������ǰ��λ��˼��һ���ġ�֮������ֻ��4λ
		 * ����Ϊ��Щ�ز���ʾ�ĵط����������߶���ز���ʾ��ͬһ���ط����������ֺͽ��ơ�
		 * 
		 * ����Ϊ2�ġ�����������RUNNING_SCREEN_SCORE_POSITION����Ϊ{ 278, 170 }��ʾ��ʾ��
		 * λ��Ϊ(278,170)
		 * 
		 * */
		// ����
		image.put(RES.IMG_BACKGROUND, new int[] { 0, 0, 748, 1024, 0, 0 }); //���������resource��,��background.png�С�
		// ��
		image.put(RES.IMG_BIRD_YELLOW_1,new int[] { 317, 120, 58, 56, 256 , 394 });
		image.put(RES.IMG_BIRD_YELLOW_2, new int[] { 317, 181, 63, 42, 0, 0 });
		image.put(RES.IMG_BIRD_YELLOW_3, new int[] { 317, 231, 59, 44, 0, 0 });
		// ����
		image.put(RES.IMG_MEDAL_NONE, new int[] { 0, 57, 56, 56 });
		image.put(RES.IMG_MEDAL_GOLD, new int[] { 0, 0, 56, 56 });
		image.put(RES.IMG_MEDAL_SILVER, new int[] { 56, 0, 56, 56 });
		image.put(RES.IMG_MEDAL_BRONZE, new int[] { 112, 0, 56, 56 });
		image.put(RES.IMG_MEDAL_BLACK, new int[] { 168, 0, 56, 56});
		image.put(RES.IMG_MEDAL_WHITE, new int[] { 224, 0, 56, 56});
		// ����
		image.put(RES.IMG_NUM_0, new int[] { 285, 0, 23, 37 });
		image.put(RES.IMG_NUM_1, new int[] { 308, 0, 21, 37 });
		image.put(RES.IMG_NUM_2, new int[] { 329, 0, 24, 37 });
		image.put(RES.IMG_NUM_3, new int[] { 353, 0, 26, 37 });
		image.put(RES.IMG_NUM_4, new int[] { 379, 0, 25, 37 });
		image.put(RES.IMG_NUM_5, new int[] { 404, 0, 25, 37 });
		image.put(RES.IMG_NUM_6, new int[] { 430, 0, 25, 37 });
		image.put(RES.IMG_NUM_7, new int[] { 455, 0, 24, 37 });
		image.put(RES.IMG_NUM_8, new int[] { 479, 0, 25, 37 });
		image.put(RES.IMG_NUM_9, new int[] { 506, 0, 25, 37 });
		// ·��
		image.put(RES.IMG_ROAD, new int[] { 0, 800, 748, 224, 0, 0 });
		// ����
		image.put(RES.IMG_PIPE_FLOOR, new int[] { 20, 100, 131, 720, 0, 0 });
		image.put(RES.IMG_PIPE_TOP, new int[] { 167, 100, 131, 720, 0, 0 });
		// ��ɫ��
		image.put(RES.IMG_BIRD_GRAY_1, new int[] { 317, 301, 59, 56, 0, 0 });
		image.put(RES.IMG_BIRD_GRAY_2, new int[] { 317, 371, 63, 42, 0, 0 });
		image.put(RES.IMG_BIRD_GRAY_3, new int[] { 317, 431, 59, 44, 335, 400 });
		// ������
		image.put(RES.IMG_RESULT_PANEL, new int[] { 387, 101, 401, 219, 168,400 });
		// ����
		image.put(RES.IMG_HAND_POINTER,	new int[] { 810, 121, 56, 103, 347, 480 });
		// TAP��ͷ 
		image.put(RES.IMG_TIP_TAP, new int[] { 880, 99, 116, 51, 427, 500 });
		// ����ͼƬ
		image.put(RES.IMG_TXT_GAMENAME,	new int[] { 34, 898, 686, 88, 31, 187 });
		image.put(RES.IMG_TXT_GETREADY,	new int[] { 421, 781, 411, 89, 169, 187 });
		image.put(RES.IMG_TXT_GAMEOVER,	new int[] { 412, 358, 431, 75, 156, 187 });
		image.put(RES.IMG_TXT_COPYRIGHT, new int[] { 419, 470, 356, 36, 198,868 });
		// ��ť
		image.put(RES.IMG_BUTTON_MENU, new int[] { 316, 551, 251, 44, 0, 0 });
		image.put(RES.IMG_BUTTON_START,	new int[] { 316, 691, 251, 44, 250, 740 });
		image.put(RES.IMG_BUTTON_OK, new int[] { 616, 551, 250, 44, 51, 740 });
		image.put(RES.IMG_BUTTON_SHARE,	new int[] { 616, 621, 250, 44, 448, 740 });
		image.put(RES.IMG_BUTTON_SCORE,	new int[] { 316, 621, 251, 44, 448, 740 });
		// ������Ϸʱ����Ļ�Ϸ���ʾ�ķ���λ��
		image.put(RES.RUNNING_SCREEN_SCORE_POSITION, new int[] { 327, 200 ,3});
		// ��Ϸ������ʾ�����ʱ�����������ʾ�ķ���λ��
		image.put(RES.RESULT_PANEL_SCORE_POSITION, new int[] { 437, 463,1 });
		// ��Ϸ������ʾ�����ʱ�����������ѷ���λ��
		image.put(RES.BEST_SCORE_POSITION, new int[] { 437, 550 ,1});
		// ��Ϸ����������ʾ��λ��
		image.put(RES.MEDAL_POSITION, new int[] { 245, 487});
	}
	// ========================================��Դ��λ�Ͳ������ý���===================================================

}
