package com.xyh.receiver;

import com.xyh.listener.QRListener;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
/**
 * 
 * @author xieyuhai
 * @date 2016年12月2日
 * @version 1.0
 *
 */
public class QRReceiver extends BroadcastReceiver {
	private QRListener listener;

	public void setListener(QRListener listener) {
		this.listener = listener;
	}

	@Override
	public void onReceive(Context context, Intent intent) {

		String action = intent.getAction();

		if (action != null && action.startsWith("com.barcode.sendBroadcast")) {

			String data = intent.getStringExtra("BARCODE");
			if (listener != null) {
				listener.getQRData(data);
			}

			Toast.makeText(context, data, Toast.LENGTH_SHORT).show();
		}
	}
}
