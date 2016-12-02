package com.xyh.qrtest;

import com.xyh.listener.QRListener;
import com.xyh.receiver.QRReceiver;

import android.app.Activity;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
/**
 * csdn地址：http://blog.csdn.net/u010945409/article/details/53434266
 * github地址:https://github.com/xieyuhai/qr
 * @author xieyuhai
 * @date 2016年12月2日
 * @version 1.0
 *
 */
public class MainActivity extends Activity implements QRListener {

	private EditText qrEditText;
	private TextView deleteTextView;
	private QRReceiver receiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initView();
		initEvent();

		initReceiver();
	}

	private void initReceiver() {
		receiver = new QRReceiver();
		receiver.setListener(this);
		registerReceiver(receiver, new IntentFilter("com.barcode.sendBroadcast"));
	}

	private void initEvent() {
		deleteTextView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				qrEditText.setText("");
			}
		});
	}

	private void initView() {
		qrEditText = (EditText) findViewById(R.id.qrEditText);
		deleteTextView = (TextView) findViewById(R.id.deleteTextView);
	}

	@Override
	public void getQRData(String data) {
		qrEditText.setText(data);
	}

	@Override
	protected void onDestroy() {
		if (receiver != null) {
			unregisterReceiver(receiver);
			receiver = null;
			System.gc();
		}
		super.onDestroy();
	}
}
