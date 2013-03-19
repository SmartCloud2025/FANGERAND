package cn.cdu.fanger.ac.view;

import android.os.Handler;
import android.os.Message;

public class MyHandler extends Handler {
	
	@Override
	public void handleMessage(Message msg) {
		super.handleMessage(msg);
		switch(msg.what){
			case 1:
				break;
			default:
				break;				
		}
	}
}
