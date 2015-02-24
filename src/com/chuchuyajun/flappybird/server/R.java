package com.chuchuyajun.flappybird.server;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.Texture;

public class R {
	// =================================================================================
	// 常量及枚举类型声明
	// =================================================================================
	// 背景的类型。因为地面和背景都被我定义为Background类。所以需要有个标志来区分
	public enum BACKGROUND_TYPE {
		BACKGROUND, FLOOR
	};

	// 游戏状态，游戏会根据这几个状态决定场景的切换和角色的动作以及位置
	public static final int STATE_START = 1;
	public static final int STATE_READY = 2;
	public static final int STATE_READYTORUN = 3;
	public static final int STATE_RUNNING = 4;
	public static final int STATE_DEATH_THROES = 5;
	public static final int STATE_LOST = 6;
	public static final int STATE_OVER = 7;

	// 鸟闪动翅膀的模式
	public static enum FLAPPY_MODE {
		NO_FLAPPY, TOUCH_TO_FLAPPY, AUTO_FLAPPY
	}

	// 游戏元素集合
	public static enum RES {
		// 背景
		IMG_BACKGROUND,
		// 鸟
		IMG_BIRD_YELLOW_1, IMG_BIRD_YELLOW_2, IMG_BIRD_YELLOW_3,
		// 奖牌
		IMG_MEDAL_NONE, IMG_MEDAL_GOLD, IMG_MEDAL_SILVER, IMG_MEDAL_BRONZE, IMG_MEDAL_BLACK, IMG_MEDAL_WHITE,
		// 数字
		IMG_NUM_0, IMG_NUM_1, IMG_NUM_2, IMG_NUM_3, IMG_NUM_4, IMG_NUM_5, IMG_NUM_6, IMG_NUM_7, IMG_NUM_8, IMG_NUM_9,
		// 路面
		IMG_ROAD,
		// 土管
		IMG_PIPE_FLOOR, IMG_PIPE_TOP,
		// 灰色鸟
		IMG_BIRD_GRAY_1, IMG_BIRD_GRAY_2, IMG_BIRD_GRAY_3,
		// 结果面板
		IMG_RESULT_PANEL,
		// 手型
		IMG_HAND_POINTER,
		// TAP箭头
		IMG_TIP_TAP,
		// 文字图片
		IMG_TXT_GETREADY, IMG_TXT_GAMEOVER, IMG_TXT_COPYRIGHT, IMG_TXT_GAMENAME,
		// 按钮
		IMG_BUTTON_MENU, IMG_BUTTON_START, IMG_BUTTON_OK, IMG_BUTTON_SHARE, IMG_BUTTON_SCORE,
		// 正在游戏时，屏幕上方显示的分数位置
		RUNNING_SCREEN_SCORE_POSITION,
		// 游戏结束显示结果的时候结果面板上显示的分数位置
		RESULT_PANEL_SCORE_POSITION,BEST_SCORE_POSITION,
		// 游戏结束后奖牌显示的位置
		MEDAL_POSITION
	}

	// ===========================常量及枚举类型声明结束======================================

	// =================================================================================
	// 游戏资源定位和场景中各元素放置位置设置区域
	// =================================================================================
	// 图片素材路径，之所以把小鸟和土管的图片分离出来，是因为游戏已经实现代码和资源低耦合，
	// 可以直接替换小鸟和土管的图片来替换掉游戏中的英雄和敌人，而不需要做任何代码修改。
	// 即使是要修改掉游戏整个界面，也只需要替换掉resource.png后在这里进行定位设置即可
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
	public static final float AUDIO_VOLUME = 1.0f; // 音量大小[0,1f]

	// ============================游戏资源和画面设定结束=================================

	// =================================================================================
	// 游戏参数设置
	// =================================================================================
	// 初始屏幕大小，根据当初贴图设计时候手机屏幕尺寸的来，整个图片是按照640*1136来设计的，
	// 这里面的值只有当游戏中使用的背景尺寸和游戏场景当初设计是的尺寸不一致时，游戏才会采用
	// 下列设计时的尺寸来作为场景尺寸。不过一般设计游戏时，背景尺寸就应该是场景尺寸。所以下
	// 列两个值可以随便写。比如我的场景设计时是参照1136*640的屏幕设计素材做的定位和显示。而
	// 最终游戏的素材和背景都是1024*748。鄙人嫌麻烦，为了不更新后面定位的数据只替换了背景的
	// 数据。而游戏场景的数据还是按照1136*640来拉伸压缩成1024*748的。也就是拉胖了，压矮了。
	// 所以游戏画面会显得扁一点。不过既然下面集中提供了素材定位的地方。任何人都能diy这个游戏
	public final static int DESIGN_SCREEN_SIZE_WIDTH = 748;
	public final static int DESIGN_SCREEN_SIZE_HEIGHT = 1024;

	// 实际手机尺寸变量，此处的设置只是预设值，运行游戏后自动根据运行的手机更新下面两个值
	public static int PHONE_WIDTH = 480;
	public static int PHONE_HEIGHT = 320;

	// 游戏用到的资源，也就是当初用图片设计游戏画面的尺寸与实际运行游戏的手机尺寸的比例，游戏开始后会自动更新这个值。
	public static float SCREEN_WIDTH_SCALE = 1.0f;
	public static float SCREEN_HEIGHT_SCALE = 1.0f;
	
	// 地面的滚动速度和背景的滚动速度
	public static final int SCROLL_SPEED_FLOOR = 300;
	public static final int SCROLL_SPEED_BACKGROUND = 160;

	// 游戏中鸟闪动翅膀的模式，NO_FLAPPY为始终不煽动翅膀,TOUCH_TO_FLAPPY为当点击了屏幕后才会煽动翅膀
	// 这种模式比较贴近现实。因为之后小鸟煽动翅膀才会往上飞。而不是不停地闪动翅膀还会加速降落,AUTO_FLAPPY
	// 为始终不停地煽动翅膀。虽然这个模式让小鸟显得活泼些。但是不符合常理。不过游戏默认还是这个模式。
	public static FLAPPY_MODE birdFlappyMode = FLAPPY_MODE.AUTO_FLAPPY;

	// 游戏进入时的初始状态，可以以此来设置进入游戏后显示哪个画面
	public static int GameState = R.STATE_START;

	// 小鸟煽动翅膀动作间隔，设置得越小，煽动翅膀越快
	public static final int AUTO_FLAPYY_DELAY = 5;

	// 初始游戏分数，应该设置为零。设置成其他分数可以用于测试最后奖牌和分数的显示
	public static int GameScore = 0;
	
	// 初始游戏分数，应该设置为零。设置成其他分数可以用于测试最后奖牌和分数的显示
	public static int BestScore = 0;

	// 水平方向上两组土管之间的间隔占手机屏幕的比例
	public static final float PIPE_INTERVAL_OF_SCREEN = 0.3f;
	
	// 上下土管之间的间隔占手机屏幕的比例
	public static final float PIPES_GAP_OF_SCREEN = 0.3f;
	
	// 土管宽度占手机屏幕宽度的比例
	public static final float PIPE_WIDTH_OF_SCREEN = 0.1f;
	
	// 土管移动速度
	public static final float PIPE_SPEED_OF_SCREEN = 0.1f;
	
	// 小鸟的宽度占手机屏幕的比例
	public static final float BIRD_WIDTH_OF_SCREEN = 0.1f;

    //小鸟下降的速度权重，用来影响当前速度
    public static final float speedDownPro  = 0.28f;
    
    //小鸟上升的速度权重，用来影响当前速度
    public static final float speedUpPro = 0.28f;
	// ==========================================游戏参数设置结束=================================================

	// ========================================================================================================================
	// 游戏中用到的个元素在素材文件中的位置定位尺寸大小以及布局时放入的坐标
	// ========================================================================================================================

	public static Map<RES, int[]> image = new HashMap<RES, int[]>();

	public static void initData() {
		/*以下数组长度为6的：
		 * 				  比如IMG_BACKGROUND的数组为{ 1300, 0, 748, 1024, 0, 0 }这表示
		 * 在images/resource.png中，横坐标为1300，纵坐标为0的地方开始截取宽度为748，高度为
		 * 1204的矩形区域出来。然后在需要显示的地方从横坐标为0，纵坐标也为0的地方开始绘制。绘制
		 * 的宽度和高度不需要自己设置。会根据手机分辨率来自适应。
		 * 
		 * 长度为4的，比如IMG_NUM_0的数组为 { 285, 0, 23, 37 }，表示从resource.png的285,0的
		 * 地方截取宽为23，高为37的矩形。这个和长度为6的数组前四位意思是一样的。之所以是只有4位
		 * 是因为这些素材显示的地方不定，或者多个素材显示在同一个地方，比如数字和奖牌。
		 * 
		 * 长度为2的。比如最后面的RUNNING_SCREEN_SCORE_POSITION数组为{ 278, 170 }表示显示的
		 * 位置为(278,170)
		 * 
		 * */
		// 背景
		image.put(RES.IMG_BACKGROUND, new int[] { 0, 0, 748, 1024, 0, 0 }); //这个不是在resource中,在background.png中。
		// 鸟
		image.put(RES.IMG_BIRD_YELLOW_1,new int[] { 317, 120, 58, 56, 256 , 394 });
		image.put(RES.IMG_BIRD_YELLOW_2, new int[] { 317, 181, 63, 42, 0, 0 });
		image.put(RES.IMG_BIRD_YELLOW_3, new int[] { 317, 231, 59, 44, 0, 0 });
		// 奖牌
		image.put(RES.IMG_MEDAL_NONE, new int[] { 0, 57, 56, 56 });
		image.put(RES.IMG_MEDAL_GOLD, new int[] { 0, 0, 56, 56 });
		image.put(RES.IMG_MEDAL_SILVER, new int[] { 56, 0, 56, 56 });
		image.put(RES.IMG_MEDAL_BRONZE, new int[] { 112, 0, 56, 56 });
		image.put(RES.IMG_MEDAL_BLACK, new int[] { 168, 0, 56, 56});
		image.put(RES.IMG_MEDAL_WHITE, new int[] { 224, 0, 56, 56});
		// 数字
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
		// 路面
		image.put(RES.IMG_ROAD, new int[] { 0, 800, 748, 224, 0, 0 });
		// 土管
		image.put(RES.IMG_PIPE_FLOOR, new int[] { 20, 100, 131, 720, 0, 0 });
		image.put(RES.IMG_PIPE_TOP, new int[] { 167, 100, 131, 720, 0, 0 });
		// 灰色鸟
		image.put(RES.IMG_BIRD_GRAY_1, new int[] { 317, 301, 59, 56, 0, 0 });
		image.put(RES.IMG_BIRD_GRAY_2, new int[] { 317, 371, 63, 42, 0, 0 });
		image.put(RES.IMG_BIRD_GRAY_3, new int[] { 317, 431, 59, 44, 335, 400 });
		// 结果面板
		image.put(RES.IMG_RESULT_PANEL, new int[] { 387, 101, 401, 219, 168,400 });
		// 手型
		image.put(RES.IMG_HAND_POINTER,	new int[] { 810, 121, 56, 103, 347, 480 });
		// TAP箭头 
		image.put(RES.IMG_TIP_TAP, new int[] { 880, 99, 116, 51, 427, 500 });
		// 文字图片
		image.put(RES.IMG_TXT_GAMENAME,	new int[] { 34, 898, 686, 88, 31, 187 });
		image.put(RES.IMG_TXT_GETREADY,	new int[] { 421, 781, 411, 89, 169, 187 });
		image.put(RES.IMG_TXT_GAMEOVER,	new int[] { 412, 358, 431, 75, 156, 187 });
		image.put(RES.IMG_TXT_COPYRIGHT, new int[] { 419, 470, 356, 36, 198,868 });
		// 按钮
		image.put(RES.IMG_BUTTON_MENU, new int[] { 316, 551, 251, 44, 0, 0 });
		image.put(RES.IMG_BUTTON_START,	new int[] { 316, 691, 251, 44, 250, 740 });
		image.put(RES.IMG_BUTTON_OK, new int[] { 616, 551, 250, 44, 51, 740 });
		image.put(RES.IMG_BUTTON_SHARE,	new int[] { 616, 621, 250, 44, 448, 740 });
		image.put(RES.IMG_BUTTON_SCORE,	new int[] { 316, 621, 251, 44, 448, 740 });
		// 正在游戏时，屏幕上方显示的分数位置
		image.put(RES.RUNNING_SCREEN_SCORE_POSITION, new int[] { 327, 200 ,3});
		// 游戏结束显示结果的时候结果面板上显示的分数位置
		image.put(RES.RESULT_PANEL_SCORE_POSITION, new int[] { 437, 463,1 });
		// 游戏结束显示结果的时候结果面板上最佳分数位置
		image.put(RES.BEST_SCORE_POSITION, new int[] { 437, 550 ,1});
		// 游戏结束后奖牌显示的位置
		image.put(RES.MEDAL_POSITION, new int[] { 245, 487});
	}
	// ========================================资源定位和布局配置结束===================================================

}
