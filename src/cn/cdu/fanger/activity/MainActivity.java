package cn.cdu.fanger.activity;


import cn.cdu.fanger.ac.view.AbstractAsyncListActivity;
import cn.cdu.fanger.ac.view.AbstractMenuActivity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

public class MainActivity extends AbstractAsyncListActivity {
	protected static final String TAG = MainActivity.class.getSimpleName();
	@Override
	protected String getDescription() {
		return getResources().getString(R.string.text_main);
	}

	@Override
	protected OnItemClickListener getMenuOnItemClickListener() {
		return new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View child, int position,
					long id) {
				Class<?> clazz = null;
				switch (position) {
				case 0:
					clazz=RestActivity.class;
					break;
				case 1:
					clazz=RestActivity.class;
					break;
				case 2:
					clazz=RestActivity.class;
					break;
				case 3:
					clazz=RestActivity.class;
					break;

				default:
					break;
				}
				startActivity(new Intent(parent.getContext(), clazz));//转到请求页面
			}
		};
	}

	@Override
	protected ListAdapter getListAdapter() {
		return new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.main_menu_items));
	}

}
