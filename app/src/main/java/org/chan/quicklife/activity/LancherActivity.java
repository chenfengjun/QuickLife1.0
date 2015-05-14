/**
 * 
 */
package org.chan.quicklife.activity;

import org.chan.quicklife.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

/**
 * @Package org.great.quicklife.activity.LancherActivity.java
 *
 * @ClassName LancherActivity
 *
 * @Description
 * 
 * @Author ChenFengjun
 *
 * @Version v1.0
 */
public class LancherActivity extends Activity {
	
		private final int SPLASH_DISPLAY_LENGHT = 2000; 

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			getWindow().requestFeature(Window.FEATURE_NO_TITLE);
			setContentView(R.layout.activity_lancher);

			new Handler().postDelayed(new Runnable() {
				public void run() {
					Intent mainIntent = new Intent(LancherActivity.this,
							MainActivity.class);
					LancherActivity.this.startActivity(mainIntent);
					LancherActivity.this.finish();
				}

			}, SPLASH_DISPLAY_LENGHT);

	
	}
}
