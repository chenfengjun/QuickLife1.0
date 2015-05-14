/**
 * 
 */
package org.chan.quicklife.activity;

import java.util.ArrayList;
import java.util.List;

import org.chan.quicklife.R;

import android.app.ActivityGroup;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * @Package com.example.surface.ActivityTab.java
 * 
 * @ClassName ActivityTab
 * 
 * @Description ��ǩҳ�����࣬�ṩ��ӱ�ǩ���л���ǩ�ȷ��������Ը��Activity�Ĳ����Զ�ѡ��ˮƽ�봹ֱ���ְ�ť�Ű�
 * 
 * @Author ChenFengjun
 * 
 * @Version v1.0
 */
public class TabActivity {

	private ActivityGroup activityGroup;
	private LocalActivityManager manager;
	private View mainView;
	/** View�������Ѿ���ӵ�View���� */
	private List<View> viewList = new ArrayList<View>();
	/** ��ű�ǩ����ť */
	private List<RelativeLayout> tabIndexList = new ArrayList<RelativeLayout>();
	private int currentTab = -1;
	private List<Intent> intents = new ArrayList<Intent>();
	private List<String> names = new ArrayList<String>();

	/**
	 * �вι��췽��
	 * 
	 * @param group
	 *            {@link ActivityGroup}
	 * @param mainView
	 *            TODO
	 */
	public TabActivity(ActivityGroup group, View mainView) {
		super();
		this.activityGroup = group;
		this.manager = group.getLocalActivityManager();
		this.mainView = mainView;
	}

	/**
	 * ��ͼ������ֵ���ӱ�ǩҳ
	 * 
	 * @param tabName
	 *            ��ǩҳ���
	 * @param resId
	 *            ��ǩҳͼ����ԴID
	 * @param btnBkRes
	 *            ��ť������Դ
	 * @param btnLayoutID
	 *            ��ť�������֣����ԣ�
	 * @param intent
	 *            ���ñ�ǩ��intent
	 */
	public void addTab(String tabName, int resId, int btnBkRes,
			int btnLayoutID, Intent intent) {

		currentTab += 1;
		// �½��ı�
		TextView textView = new TextView(activityGroup);
		textView.setText(tabName);
		textView.setId(11);
		// �����ı���ɫ
		textView.setTextColor(activityGroup.getResources().getColor(
				android.R.color.white));
		ImageView btn = new ImageView(activityGroup);
		btn.setImageDrawable(activityGroup.getResources().getDrawable(resId));
		btn.setId(12);

		// �����ǩ��֣���������ı���ͼ�꣬������Ϊ��ֱ�Ű�
		RelativeLayout relTitle = new RelativeLayout(activityGroup);
		// ����λ��
		relTitle.setTag(currentTab);
		// ע�����
		relTitle.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Integer clickTab = (Integer) v.getTag();
				// �жϵ���İ�ť���ǵ�ǰ��ť���л�ҳ��
				if (clickTab != currentTab) {
					switchTab(clickTab);
				}
			}
		});
		relTitle.setBackgroundResource((btnBkRes));
		relTitle.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN)
					v.setPressed(true);
				return false;
			}
		});
		// ������Բ��ֵĲ���
		RelativeLayout.LayoutParams rParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		RelativeLayout.LayoutParams rParams1 = new RelativeLayout.LayoutParams(
				rParams);

		rParams.addRule(RelativeLayout.CENTER_IN_PARENT);
		textView.setPadding(0, 40, 0, 0);
		rParams1.addRule(RelativeLayout.ALIGN_TOP, textView.getId());
		rParams1.addRule(RelativeLayout.CENTER_HORIZONTAL);
		rParams1.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		rParams.setMargins(2, 2, 2,2);
		rParams1.setMargins(0, 0, 0, 3);
		// �����Ԫ�أ�ָ���±�Ͳ��ֲ���
		relTitle.addView(btn, 0, rParams1);
		relTitle.addView(textView, 1, rParams);
		// ���ñ�ǩ����ɵ��
		relTitle.setClickable(true);

		// ����ǩҳ������ӵ�����
		tabIndexList.add(relTitle);

		// ��ȡ��ǩҳ����ͼ
		View view = manager.startActivity(tabName, intent).getDecorView();
		LinearLayout lineParent = (LinearLayout) mainView
				.findViewById(btnLayoutID);

		// ���ñ�ǩ��ť�Ĳ���
		LinearLayout.LayoutParams params = new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1);
		//
		// // ���÷ָ���
		// LinearLayout line = new LinearLayout(activityGroup);
		// line.setBackgroundColor(activityGroup.getResources().getColor(
		// android.R.color.background_light));
		// line.setPadding(0, 3, 0, 3);
		//
		// LinearLayout.LayoutParams params1 = null;
		// if (lineParent.getOrientation() == LinearLayout.HORIZONTAL) {
		// params1 = new LayoutParams(1, LayoutParams.MATCH_PARENT);
		// line.setOrientation(LinearLayout.HORIZONTAL);
		// } else if (lineParent.getOrientation() == LinearLayout.VERTICAL) {
		// params1 = new LayoutParams(LayoutParams.MATCH_PARENT, 1);
		// line.setOrientation(LinearLayout.HORIZONTAL);
		// }
		// ��ӱ�ǩ����ť
		lineParent.addView(relTitle, params);
		// if (params1 != null)
		// // ��ӷָ���
		// lineParent.addView(line, params1);
		viewList.add(view);
		names.add(tabName);
		intents.add(intent);
	}

	/**
	 * �л���ǩ
	 * 
	 * @param clickTab
	 *            ��ǰ����ı�ǩ
	 */
	public void switchTab(int clickTab) {
		Log.v("quicklife.LifeCycle", "�л�����------------------------------------");
		FrameLayout frame = (FrameLayout) mainView
				.findViewById(R.id.main_frame);
		View view = viewList.get(clickTab);
		// �Ƴ�������ͼ
		frame.removeAllViews();
		onChangeTab(clickTab);
		setCurrentTab(clickTab);
		// ��ӵ�ǰѡ�е���ͼ
		frame.addView(view);
		frame.postInvalidate();
		Log.v("quicklife.LifeCycle", "��ǰActivity��" + manager.getCurrentId());
		manager.startActivity(names.get(clickTab), intents.get(clickTab));
		Log.v("quicklife.LifeCycle", "�л���Activity��" + manager.getCurrentId());
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

		// ��ȡ��ǰ����ť
		RelativeLayout oldView = tabIndexList.get(currentTab);
		oldView.setSelected(false);

		// ��ȡҪ�л����İ�ť
		RelativeLayout view = tabIndexList.get(tab);
		view.setSelected(true);

	}

}
