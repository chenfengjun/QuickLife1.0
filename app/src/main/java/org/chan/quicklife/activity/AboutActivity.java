/**
 * 
 */
package org.chan.quicklife.activity;

import org.chan.quicklife.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;

/**
 * @Package org.gread.quicklife.activity.AboutActivity.java
 * 
 * @ClassName AboutActivity
 * 
 * @Description
 * 
 * @Author ChenFengjun
 * 
 * @Version v1.0
 */
public class AboutActivity extends Activity {
	/** 锟斤拷锟截帮拷钮 */
	private ImageView backBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 锟斤拷锟矫筹拷锟睫憋拷锟斤拷
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_about);
		backBtn = (ImageView) findViewById(R.id.about_back);
		backBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

}
