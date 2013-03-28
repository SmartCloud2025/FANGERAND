package cn.cdu.fanger.ac.view;

import cn.cdu.fanger.activity.ListMainActivity;
import cn.cdu.fanger.constant.Task;
import android.os.Handler;
import android.os.Message;

public class SpotsHandler extends Handler {
	protected static final String TAG = SpotsHandler.class.getSimpleName();

	static IBaseActivity resultAc;
	
	@Override
	public void handleMessage(Message msg) {
		super.handleMessage(msg);
		switch(msg.what){
			case Task.GET_SPOT_LIST:
				resultAc = (IBaseActivity) SpotsService.getActivityByName("ListMainActivity");
				resultAc.refresh(ListMainActivity.FRESH_SPOT_DATA,msg.obj);
				break;
			case Task.GET_SPOT_ITEM_CREATBY_IMG:
				resultAc = (IBaseActivity) SpotsService.getActivityByName("ListMainActivity");
				resultAc.refresh(ListMainActivity.FRESH_ICON_DATA);
				break;
			case Task.GET_SPOT_ITEM_IMG:
				resultAc = (IBaseActivity) SpotsService.getActivityByName("ListMainActivity");
				resultAc.refresh(ListMainActivity.FRESH_ICON_DATA);
				break;
			default:
				break;				
		}
	}
}
