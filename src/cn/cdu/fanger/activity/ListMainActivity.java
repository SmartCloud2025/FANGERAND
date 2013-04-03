package cn.cdu.fanger.activity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import cn.cdu.fanger.ac.view.IBaseActivity;
import cn.cdu.fanger.ac.view.IMenuActivity;
import cn.cdu.fanger.ac.view.SpotsService;
import cn.cdu.fanger.constant.Task;
import cn.cdu.fanger.rest.entity.AndrSpot;
import cn.cdu.fanger.view.adpter.SpotsAdpter;

public class ListMainActivity extends IMenuActivity implements IBaseActivity{
	public final static int FRESH_SPOT_DATA = 1;
	public final static int FRESH_ADDMORE_DATA = 2;
	public final static int FRESH_ICON_DATA = 3;
	
	ListView listView;
	int pageSize=3,pageNow=1;
	private Button swithBtn;
	
	@Override
	protected void onCreateMethod() {
		setContentView(R.layout.activity_main_list);
		listView = (ListView) findViewById(R.id.main_listview);
		SpotsService.allActivity.add(this);//put the current install to service
		
		View topBar = findViewById(R.id.list_top_view);
		swithBtn = (Button) topBar.findViewById(R.id.main_button_switch);
		addListener();
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		init();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	public void init() {
		//release a task
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("pagenow", (Integer) pageNow);
		map.put("pagesize", (Integer) pageSize);
		
		Task task = new Task(Task.GET_SPOT_LIST, map);
		SpotsService.newTask(task);
		this.showLoadingProgressDialog();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void refresh(Object... param) {
		this.dismissProgressDialog();
		//handle the result
		int type = ((Integer) (param[0])).intValue();
		List<AndrSpot> resultData = null;
		switch (type) {
		case FRESH_SPOT_DATA:
			resultData = (List<AndrSpot>) param[1];
			SpotsAdpter adpter = new SpotsAdpter(this,resultData);
			listView.setAdapter(adpter);
			break;
		case FRESH_ADDMORE_DATA:
			resultData = (List<AndrSpot>) param[1];
			((SpotsAdpter)listView.getAdapter()).addMoreData(resultData);
			break;
		case FRESH_ICON_DATA:
			((SpotsAdpter)listView.getAdapter()).notifyDataSetChanged();
			break;
		default:
			break;
		}
	}
	
	
	private void addListener(){
		swithBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(ListMainActivity.this, MapMainActivity.class));
			}
		});
	}
}
