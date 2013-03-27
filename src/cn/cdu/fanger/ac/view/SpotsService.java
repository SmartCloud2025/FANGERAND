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

import cn.cdu.fanger.constant.ServerUrl;
import cn.cdu.fanger.constant.Task;
import cn.cdu.fanger.rest.entity.AndrSpot;
import cn.cdu.fanger.utill.NetUtil;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;


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
					int pageNow = (Integer) task.getTaskParam().get("pagenow");
					int pageSize = (Integer) task.getTaskParam().get("pagesize");
					try{
						String url = ServerUrl.spotList+pageNow+","+pageSize;
						
						System.out.println("---"+url);
						
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
						
						//请求图片
						this.publishGetIcon(resultData);
						
						msg.obj = resultData;
					}catch (Exception e) {
						Log.e(TAG, e.getMessage(),e);
					}
					break;
				case Task.GET_SPOT_ITEM_IMG:
					// 获取用户id
					int sid = (Integer) task.getTaskParam().get("uid");
					// 获取用户头像
					URL u = new URL((String) task.getTaskParam().get("url"));
					BitmapDrawable bd = NetUtil.getImageFromUrl(u);
					// 添加图片到用户头像集合
					alluserIcon.put(sid, bd);
					break;
				case Task.GET_SPOT_ITEM_CREATBY_IMG:
					// 获取用户id
					int ssid = (Integer) task.getTaskParam().get("uid");
					// 获取用户头像
					URL url = new URL((String) task.getTaskParam().get("url"));
					BitmapDrawable bmd = NetUtil.getImageFromUrl(url);
					// 添加图片到用户头像集合
					allspotIcon.put(ssid, bmd);
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
	
	// 娣诲姞涓�釜浠诲�?
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
}
