package cn.cdu.fanger.ac.view;

import java.util.ArrayList;
import cn.cdu.fanger.utill.Task;


import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Message;


public class IBaseService extends Service implements Runnable{
	public static ArrayList<Activity> allActivity = new ArrayList<Activity>();// Activity闆嗗�?
	public static ArrayList<Task> allTask = new ArrayList<Task>();//浠诲姟闆嗗悎
	public static boolean isRunning = false;
	private MyHandler handler = new MyHandler();
	
	
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
				case 1:
					break;
				
				default:
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		handler.sendMessage(msg);
		IBaseService.allTask.remove(task);
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
	public IBinder onBind(Intent arg0) {
		return null;
	}
}
