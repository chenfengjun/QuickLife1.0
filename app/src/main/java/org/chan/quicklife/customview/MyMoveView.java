package org.chan.quicklife.customview;

import org.chan.quicklife.R;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * @Package org.gread.quicklife.customview.MyMoveView.java
 * 
 * @ClassName MyMoveView
 * 
 * @Description
 * 
 * @Author ChenFengjun
 * 
 * @Version v1.0
 */
public class MyMoveView extends ViewGroup {

	private final static int TOUCH_STATE_REST = 0;

	private final static int TOUCH_STATE_MOVING = 1;

	private final static int MOVE_TO_LEFT = 1;

	private final static int MOVE_TO_RIGHT = 2;

	private final static int MOVE_TO_REST = 0;

	public final static int MAIN = 0;
	public final static int RIGHT = 2;

	private int touch_state = TOUCH_STATE_REST;

	private int move_state = MOVE_TO_REST;

	private int now_state = MAIN;

	private final float WIDTH_RATE = 0.8f;
	private View main_show_view;
	private View right_show_view;

	private int min_distance = 200;

	private int screen_w;
	private int screen_h;

	private int move_x_v;

	private boolean isAimationMoving = false;

	private Handler mHandler = new Handler() {

		public void handleMessage(Message msg) {
			synchronized (MyMoveView.this) {
				isAimationMoving = true;
				int move_change = (int) (screen_w * WIDTH_RATE / 5);
				int left = main_show_view.getLeft();
				if (msg.what == 1) {
					move(move_change + left);
				}
				if (msg.what == 0) {
					if (now_state == MAIN) {
						move(-1 * move_x_v);
					} else {
						move(move_x_v);
					}
				}
				if (msg.what == 2) {
					move(-1 * move_change + left);
				}
				if (msg.what == 12) {
					isAimationMoving = false;
					moveToRight(false);
				}

				if (msg.what == 10) {
					isAimationMoving = false;
					moveToMain(false);
				}
			}
		}
	};

	public MyMoveView(Context context) {
		super(context);
	}

	public MyMoveView(Context context, AttributeSet attrs) {
		super(context, attrs);

		// TODO Auto-generated constructor stub
	}

	public MyMoveView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public void addView(View right_show_view, View main_show_view) {
		if (this.main_show_view == null) {
			this.main_show_view = main_show_view;

			this.right_show_view = right_show_view;
		}

		this.addView(right_show_view);
		this.addView(main_show_view);

	}

	public void initContent() {

	}

	public void move(int start) {
		int left = main_show_view.getLeft();
		if (now_state == MAIN) {
			if (left > 0) {
				if (move_state != MOVE_TO_LEFT) {
					move_state = MOVE_TO_LEFT;
				}
				right_show_view.setVisibility(View.GONE);
			} else if (left < 0) {
				if (move_state != MOVE_TO_RIGHT) {
					move_state = MOVE_TO_RIGHT;
				}
				right_show_view.setVisibility(View.VISIBLE);

			} else {
				move_state = MOVE_TO_REST;
			}
			main_show_view.layout(start, 0, start + screen_w, screen_h);
		} else {
			left = (int) (screen_w * WIDTH_RATE);
			if (now_state == RIGHT) {
				left = -1 * left;
			}
			left = left + start;
			main_show_view.layout(left, 0, left + screen_w, screen_h);
		}
	}

	@Override
	protected void onLayout(boolean arg0, int arg1, int arg2, int arg3, int arg4) {
		if (move_state == MOVE_TO_REST) {
			if (now_state == MAIN) {
				int w = (int) (screen_w * WIDTH_RATE);
				main_show_view.layout(0, 0, screen_w, screen_h);

				right_show_view.layout(screen_w - w, 0, screen_w, screen_h);
			} else {
				moveToRight(false);
			}
		}
	}

	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		main_show_view.measure(widthMeasureSpec, heightMeasureSpec);

		right_show_view.measure(MeasureSpec.UNSPECIFIED, heightMeasureSpec);

		LayoutParams p = right_show_view.getLayoutParams();
		p.width = (int) (screen_w * WIDTH_RATE);
		right_show_view.setLayoutParams(p);

		ViewGroup.LayoutParams params = right_show_view.findViewById(
				R.id.menu_title_tv).getLayoutParams();
		params.width = p.width;
		right_show_view.findViewById(R.id.menu_title_tv)
				.setLayoutParams(params);
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

	}

	private int start_x;
	private int start_y;
	private boolean isMoved;

	public boolean dispatchTouchEvent(MotionEvent ev) {
		float x = ev.getX();
		float y = ev.getY();
		if (now_state == MAIN) {
			return super.dispatchTouchEvent(ev);
		}

		if (isAimationMoving) {
			return super.dispatchTouchEvent(ev);
		} else {
			int action = ev.getAction();
			switch (action) {
			case MotionEvent.ACTION_DOWN:
				if (now_state == RIGHT && x > this.screen_w * (1 - WIDTH_RATE)) {
					return super.dispatchTouchEvent(ev);
				}
				start_y = (int) y;
				move_x_v = 0;
				if (this.touch_state == TOUCH_STATE_REST) {
					this.touch_state = TOUCH_STATE_MOVING;
					start_x = (int) x;
					isMoved = false;
					move_state = MOVE_TO_REST;
				}
				break;
			case MotionEvent.ACTION_MOVE:
				int last_y = (int) y;
				int last_x = (int) x;
				super.dispatchTouchEvent(ev);
				if (!isMoved) {
					if (Math.abs(last_y - start_y) > Math.abs(last_x - start_x)) {
						super.onTouchEvent(ev);
						return true;
					} else {
						if (Math.abs(last_x - start_x) > 20) {
							isMoved = true;
						}
					}
				}
				if (isMoved) {
					if (this.touch_state == TOUCH_STATE_MOVING) {
						if (Math.abs(last_x - start_x) > 10) {
							isMoved = true;
							int move_x = last_x - start_x;
							if (move_x < 0 && now_state == RIGHT) {
								isMoved = false;
								break;
							}
							move(move_x);
						}
					}
					return false;
				}
				break;
			case MotionEvent.ACTION_UP:
				if (this.touch_state == TOUCH_STATE_MOVING) {
					if (isMoved) {
						last_x = (int) x;
						if (Math.abs(last_x - start_x) > min_distance) {
							if (now_state == MAIN) {

								if (move_state == MOVE_TO_RIGHT) {
									this.moveToRight(false);
								}
							} else {
								this.moveToMain(false);
							}
						} else {
							if (now_state == MAIN) {
								this.moveToMain(false);
							}
							if (now_state == RIGHT) {
								this.moveToRight(false);
							}
						}
						move_state = MOVE_TO_REST;
					} else {
						if (now_state == RIGHT
								&& x < this.screen_w * (1 - WIDTH_RATE)) {
							moveToMain(true);
							return true;
						}

						super.dispatchTouchEvent(ev);
						this.touch_state = TOUCH_STATE_REST;
						return false;
					}
				}
				if (now_state == RIGHT && x > this.screen_w * (1 - WIDTH_RATE)) {
					return super.dispatchTouchEvent(ev);
				}
				super.onTouchEvent(ev);
				this.touch_state = TOUCH_STATE_REST;
				break;
			}
			return true;
		}
	}

	public boolean getIsMoved() {
		return isMoved;
	}

	public void moveToRight(boolean b) {
		if (!b) {
			int move_x = (int) (screen_w * WIDTH_RATE) * -1;

			right_show_view.layout(screen_w + move_x, 0, screen_w, screen_h);
			main_show_view.layout(move_x, 0, move_x + screen_w, screen_h);
			now_state = RIGHT;
			touch_state=TOUCH_STATE_REST;
		} else {
			mHandler.postDelayed(new Runnable() {

				@Override
				public void run() {
					int move_change = (int) (screen_w * WIDTH_RATE / 5);
					int left = (int) (screen_w * WIDTH_RATE + main_show_view
							.getLeft());
					Message msg = new Message();
					if (left > move_change) {
						msg.what = 2;
						mHandler.sendMessage(msg);
						mHandler.postDelayed(this, 30);
					} else {
						msg.what = 12;
						mHandler.sendMessage(msg);
						mHandler.removeCallbacks(this);
					}
				}
			}, 0);
		}
	}

	public void moveToMain(boolean b) {
		if (!b) {
			right_show_view.setVisibility(View.VISIBLE);

			int w = (int) (screen_w * WIDTH_RATE);
			main_show_view.layout(0, 0, screen_w, screen_h);
			right_show_view.layout(screen_w - w, 0, screen_w, screen_h);
			now_state = MAIN;
			touch_state=TOUCH_STATE_REST;
		} else {
			move_x_v = 0;
			mHandler.postDelayed(new Runnable() {

				@Override
				public void run() {
					int move_change = (int) (screen_w * WIDTH_RATE / 5);
					int left = Math.abs(main_show_view.getLeft()) - 0;

					Message msg = new Message();
					if (left > move_change) {
						msg.what = 0;
						move_x_v = move_x_v + move_change;
						mHandler.sendMessage(msg);
						mHandler.postDelayed(this, 30);
						Log.v("move", left + ";change" + move_change);
					} else {
						msg.what = 10;
						mHandler.sendMessage(msg);
						mHandler.removeCallbacks(this);
					}
				}
			}, 0);
		}
	}

	public void initScreenSize(int w, int h, View main, View right) {
		this.screen_w = w;
		this.screen_h = h;
		min_distance = (int) (screen_w / 3.0);
		addView(main, right);
		initContent();
		moveToMain(false);
	}

	public int getNowState() {
		return this.now_state;
	}
}
