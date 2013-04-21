package cn.cdu.fanger.ac.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import cn.cdu.fanger.activity.R;
import cn.cdu.fanger.constant.ServerUrl;
import cn.cdu.fanger.constant.Task;
import cn.cdu.fanger.rest.entity.AndrSpot;
import cn.cdu.fanger.utill.NetUtil;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.graphics.drawable.BitmapDrawable;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;


@SuppressLint("UseSparseArrays")
public class SpotsService extends Service implements Runnable{
	
	protected static final String TAG = SpotsService.class.getSimpleName();

	public static ArrayList<Activity> allActivity = new ArrayList<Activity>();
	public static ArrayList<Task> allTask = new ArrayList<Task>();
	public static boolean isRunning = false;
	private SpotsHandler handler = new SpotsHandler();
	
	public static HashMap<Integer, BitmapDrawable> alluserIcon = new HashMap<Integer, BitmapDrawable>();
	public static HashMap<Integer, BitmapDrawable> allspotIcon = new HashMap<Integer, BitmapDrawable>();
	
	
	@Override
	public void onCreate() {
		super.onCreate();
		isRunning = true;
		Thread t = new Thread(this);
		t.start();
	}
	
	
	public void doTask(Task task){
		Message msg = new Message();
		msg.what = task.getTaskID();
		try {
			switch (task.getTaskID()) {
				case Task.GET_SPOT_LIST:
					List<AndrSpot> resultData = this.requestSpots(task);
					this.publishGetIcon(resultData);
					msg.obj = resultData;
					break;
				case Task.GET_SPOT_ITEM_IMG:
					//get id
					int sid = (Integer) task.getTaskParam().get("uid");
					// get spot resource
					URL u = new URL(ServerUrl.appUrl + task.getTaskParam().get("url"));
					BitmapDrawable bd = NetUtil.getImageFromUrl(u);
					// add to list
					alluserIcon.put(sid, bd);
					break;
				case Task.GET_SPOT_ITEM_CREATBY_IMG:
					// get id
					int ssid = (Integer) task.getTaskParam().get("uid");
					// get port 
					URL url = new URL(ServerUrl.appUrl + task.getTaskParam().get("url"));
					BitmapDrawable bmd = NetUtil.getImageFromUrl(url);
					
					allspotIcon.put(ssid, bmd);
					break;	
				case Task.GET_MAP_SPOT_LIST:
					List<AndrSpot> resultData_map = this.requestSpots(task);
					this.publishGetIcon(resultData_map);
					msg.obj = resultData_map;
					break;
				default:
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		handler.sendMessage(msg);
		SpotsService.allTask.remove(task);
	}
	
	public void run() {
		while (isRunning) {			
			Task lastTask = null;
			synchronized (allTask) {
				if (allTask.size() > 0) {
					lastTask = allTask.get(0);
					doTask(lastTask);
				}
			}
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static Activity getActivityByName(String name) {
		for (Activity ac : allActivity) {
			if (ac.getClass().getName().indexOf(name) >= 0) {
				return ac;
			}
		}
		return null;
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		isRunning = false;
	}
	
	// add a new task
	public static void newTask(Task task) {
		allTask.add(task);
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	private void publishGetIcon(List<AndrSpot> resultData){
		// 图片获取
		for (AndrSpot spot : resultData) {
			BitmapDrawable uDrawable = alluserIcon.get(spot.getId());
			BitmapDrawable sDrawable = allspotIcon.get(spot.getId());
			if (uDrawable == null) {
				// 该用户的头像不存在集合中，则获取他
				Map<String,Object> hp = new HashMap<String,Object>();
				hp.put("url", spot.getCreateByResources());
				hp.put("uid", spot.getId());
				Task t = new Task(Task.GET_SPOT_ITEM_CREATBY_IMG, hp);
				// 发布任务
				SpotsService.newTask(t);
			}
			if(sDrawable == null){
				// 该用户的头像不存在集合中，则获取他
				Map<String,Object> hp = new HashMap<String,Object>();
				hp.put("url", spot.getImageUrl());
				hp.put("uid", spot.getId());
				Task t = new Task(Task.GET_SPOT_ITEM_IMG, hp);
				// 发布任务
				SpotsService.newTask(t);
			}
		}
	}
	
	private List<AndrSpot> requestSpots(Task task){
		int pageNow = (Integer) task.getTaskParam().get("pagenow");
		int pageSize = (Integer) task.getTaskParam().get("pagesize");
		String type = (String) task.getTaskParam().get("type");
		try{
			
			String url = ServerUrl.spotList+"?currentPage="+pageNow+"&pageSize="+pageSize+"&type="+type;
			
			Log.i(TAG,"@@@------------"+url);
			
			// Set the Accept header for "application/json"
			HttpHeaders requestHeaders = new HttpHeaders();
			List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
			acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
			requestHeaders.setAccept(acceptableMediaTypes);

			// Populate the headers in an HttpEntity object to use for the request
			HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);

			// Create a new RestTemplate instance
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

			// Perform the HTTP GET request
			ResponseEntity<AndrSpot[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity,
					AndrSpot[].class);
			// convert the array to a list and return it
			List<AndrSpot> resultData = Arrays.asList(responseEntity.getBody());
			
			//get Icon
			//this.publishGetIcon(resultData);
			return resultData;
		}catch (Exception e) {
			Log.e(TAG, e.getMessage(),e);
		}
		return null;
	}
	
	
	///////////////////////////////////
	//======退出程序
	///////////////////////////////////
	public static void exitApp(Activity context) {// 退出所有Activity
		for (int i = 0; i < allActivity.size(); i++) {
			((Activity) allActivity.get(i)).finish();//销毁所有activity
		}
		allActivity.clear();//释放list
		// 退出Service
		context.stopService(new Intent("cn.cdu.fanger.ac.view.SpotsService"));
	}

	// 提示是否退出应用程序
	public static void promptExitApp(final Activity context) {
		// 创建对话框
		AlertDialog.Builder ab = new AlertDialog.Builder(context);
		LayoutInflater li = LayoutInflater.from(context);
		View msgview = li.inflate(R.layout.exit_dialog, null);
		ab.setView(msgview);
		// 设定对话框显示的内容
		ab.setPositiveButton("确定退出", new OnClickListener() {
			public void onClick(DialogInterface dv, int id) {
				// TODO Auto-generated method stub
				dv.dismiss();
				exitApp(context);// 退出整个应用
			}
		});
		ab.setNegativeButton("返回", null);
		ab.show();
	}
}
