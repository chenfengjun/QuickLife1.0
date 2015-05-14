/**
 * 
 */
package org.chan.quicklife.util;

import java.text.SimpleDateFormat;

/**
 * @Package org.great.quicklife.util.TimeFormat.java
 * 
 * @ClassName TimeFormat
 * 
 * @Description
 * 
 * @Author ChenFengjun
 * 
 * @Version v1.0
 */
public class TimeFormat {
	/**
	 * ʱ��ת����������ʱ�������ת����"yyyy-MM-dd HH:mm:ss"��ʽ���ַ�
	 * 
	 * @param time
	 *            ʱ�������
	 * @return
	 */
	public static String formatTime(long time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timeStr = sdf.format(time);
		return timeStr;
	}
}
