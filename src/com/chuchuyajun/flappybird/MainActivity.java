package com.chuchuyajun.flappybird;

import net.nend.android.NendAdView;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.ad_stir.webview.AdstirWebView;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class MainActivity extends AndroidApplication {
	
//	public static final String MEDIA_ID = "MEDIA-f66bedca";
//	public static final int SPOT_NO = 1;
//	private AdstirWebView adstirView;
	
	public static final String NEND_API_KEY="bc8dcc1ecf917c5b251bf4b4195db7f31d3b2dbc";
	public static final int NEND_SPOT_KEY=124084;
	private NendAdView nendAdView;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.useGL20 = false;
            
        View gameView = initializeForView(new MainGame(), false);
        
        LayoutInflater inflater=(LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.ad_layout, null);
        RelativeLayout layout = new RelativeLayout(this);
        
        
//        RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
//        layoutParams.addRule(RelativeLayout.ALIGN_TOP);
//        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
		
//        adstirView = new AdstirWebView(this, MEDIA_ID, SPOT_NO);
//        adstirView.setLayoutParams(layoutParams);
//        layout.addView(adstirView);
        
//        nendAdView = new NendAdView(this, NEND_SPOT_KEY, NEND_API_KEY);
//        nendAdView.setLayoutParams(layoutParams);
//        layout.addView(nendAdView);
        
       
        
		layout.addView(gameView);
		 layout.addView(view);
		 
		 requestWindowFeature(Window.FEATURE_NO_TITLE);
		 getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(layout);
        
	}

	
	
}