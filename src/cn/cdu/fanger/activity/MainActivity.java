package cn.cdu.fanger.activity;


import cn.cdu.fanger.ac.view.AbstractMenuActivity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends AbstractMenuActivity {
	protected static final String TAG = MainActivity.class.getSimpleName();
	@Override
	protected String getDescription() {
		return getResources().getString(R.string.text_main);
	}

	@Override
	protected String[] getMenuItems() {
		return getResources().getStringArray(R.array.main_menu_items);
	}

	@Override
	protected OnItemClickListener getMenuOnItemClickListener() {
		return new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View child, int position,
					long id) {
				Class<?> clazz = null;
				switch (position) {
				case 1:
					clazz=RestActivity.class;
					break;
				case 2:
					clazz=RestActivity.class;
					break;
				case 3:
					clazz=RestActivity.class;
					break;
				case 4:
					clazz=RestActivity.class;
					break;

				default:
					break;
				}
				startActivity(new Intent(parent.getContext(), clazz));//转到请求页面
			}
		};
	}

}
