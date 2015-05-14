/**
 * 
 */
package org.chan.quicklife.activity;

import java.util.ArrayList;
import java.util.List;

import org.chan.quicklife.R;

import android.app.Activity;
import android.app.ActivityGroup;
import android.graphics.BitmapFactory;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @Package com.example.surface.ActivityTab.java
 * 
 * @ClassName ActivityTab
 * 
 * @Description ��ǩҳ�����࣬�ṩ��ӱ�ǩ���л���ǩ�ȷ���
 * 
 * @Author ChenFengjun
 * 
 * @Version v1.0
 */
public class PagerActivity {

	private Activity group;
	/** View�������Ѿ���ӵ�View���� */
	private List<View> viewList;
	/** ��ű�ǩ����ť */
	private List<TextView> tabIndexList = new ArrayList<TextView>();
	private int currentTab = -1;
	// ����ͼƬ���
	private int ivCursorWidth;
	// ÿ��tabͷ�Ŀ��
	private int tabWidth;
	// tabͷ�Ŀ�ȼ�ȥ����ͼƬ�Ŀ���ٳ���2����֤����ͼƬ���tabͷ���У�
	private int offsetX;
	// �»���ͼƬ
	private ImageView ivCursor;
	private ViewPager mPager;

	/**
	 * �вι��췽��
	 * 
	 * @param group
	 *            {@link ActivityGroup}
	 */
	public PagerActivity(Activity group, ViewPager mPager, List<View> viewList) {
		super();
		this.group = group;
		this.mPager = mPager;
		this.viewList = viewList;
	}

	public void addTab(int tabId, View view) {

		currentTab += 1;
		TextView textView = (TextView) group.findViewById(tabId);
		// ����λ��
		textView.setTag(currentTab);
		// ע�����
		textView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Integer clickTab = (Integer) v.getTag();
				// �жϵ���İ�ť���ǵ�ǰ��ť���л�ҳ��
				if (clickTab != currentTab) {
					switchTab(clickTab);
					mPager.setCurrentItem(clickTab);
				}
			}
		});
		textView.setClickable(true);
		// ����ǩҳ������ӵ�����
		tabIndexList.add(textView);
		viewList.add(view);
	}

	/**
	 * �л���ǩ
	 * 
	 * @param clickTab
	 *            ��ǰ����ı�ǩ
	 */
	public void switchTab(int clickTab) {
		onChangeTab(clickTab);
		setCurrentTab(clickTab);
	}

	/**
	 * @param clickTab
	 */
	private void setCurrentTab(int clickTab) {
		this.currentTab = clickTab;
	}

	/**
	 * ��ǩ�л��¼��������л���ť״̬
	 * 
	 * @param tab
	 *            ��ǰ����ı�ǩ
	 */
	public void onChangeTab(int tab) {

		Animation animation = new TranslateAnimation(tabWidth * (currentTab)
				+ offsetX, tabWidth * (tab) + offsetX, 0, 0);
		// ͼƬͣ�ڶ�������λ��
		animation.setFillAfter(true);
		animation.setDuration(250);
		ivCursor.startAnimation(animation);
		// ��ȡҪ�л����İ�ť
		TextView oldview = tabIndexList.get(currentTab);
		oldview.setTextColor(group.getResources().getColor(
				R.color.text_black));

		// ��ȡҪ�л����İ�ť
		TextView view = tabIndexList.get(tab);
		view.setTextColor(group.getResources().getColor(
				R.color.child_title_white));
	}

	/**
	 * ��ʼ������
	 */
	public void initImageView(int ivId) {
		ivCursor = (ImageView) group.findViewById(ivId);

		DisplayMetrics dm = group.getResources().getDisplayMetrics();
		int screenW = dm.widthPixels;// ��ȡ�ֱ��ʿ��
		ivCursorWidth = BitmapFactory.decodeResource(group.getResources(),
				R.drawable.com_tenpay_android_current_bank_flag).getWidth();// ��ȡͼƬ���

		tabWidth = screenW / viewList.size();
		if (ivCursorWidth > tabWidth) {
			ivCursor.getLayoutParams().width = tabWidth;
			ivCursorWidth = tabWidth;
		}
		offsetX = (tabWidth - ivCursorWidth) / 2;
	}

	/**
	 * @Package com.contact.activity.PagerActivity.java
	 * 
	 * @ClassName MyOnPageChangeListener
	 * 
	 * @Description
	 * 
	 * @Author ChenFengjun
	 * 
	 * @Version v1.0
	 */
	class MyOnPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageSelected(int arg0) {
			switchTab(arg0);
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}
	}

	/**
	 * 
	 */
	public void setPageChangeListener() {
		this.mPager.setOnPageChangeListener(new MyOnPageChangeListener());
	}
}
