package cn.cdu.fanger.activity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
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
	int pageSize=10,pageNow=0;
	private Button swithBtn;
	
	static String search_type = "createdAt";//缺省
	static Context context;
	
	@Override
	protected void onCreateMethod() {
		setContentView(R.layout.activity_main_list);
		context = ListMainActivity.this;
		
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
		map.put("type", search_type);
		
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
				//startActivity(new Intent(ListMainActivity.this, MapMainActivity.class));
				//打开一个alert选择搜索类型
				final String[] type = ListMainActivity.this.getResources().getStringArray(R.array.search_type);
				
				AlertDialog.Builder builder = new AlertDialog.Builder(context);
				builder.setTitle("搜索类型")
						.setIcon(R.drawable.ic_launcher)
						.setSingleChoiceItems(type, 0,
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
										switch (which) {
										case 0:
											search_type = "createdAt";//最新
											break;
										case 1:
											search_type = "downloadCount";//下载最多
											break;
										case 2:
											search_type = "commentsCount";//评论最多
											break;
										case 3:
											search_type = "likeCount";//最受欢迎
											break;

										default:
											break;
										}
										
										
										Toast.makeText(context, type[which],
												Toast.LENGTH_SHORT).show();
										
									}
								}).setPositiveButton("确定", null).create().show();
			}
		});
	}
}
