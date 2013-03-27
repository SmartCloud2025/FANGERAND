package cn.cdu.fanger.constant;

import java.util.Map;

public class Task {
	private int taskID;// 任务编号
	private Map<String,Object> taskParam;//任务参数
	
	public static final int GET_SPOT_LIST = 1;//获取spot数据
	public static final int GET_SPOT_ITEM_IMG = 2;//获取spot图片
	public static final int GET_SPOT_ITEM_CREATBY_IMG = 3;//获取spot创建人头像
	
	public Task(int id, Map<String,Object> param) {
		this.taskID = id;
		this.taskParam = param;
	}

	public int getTaskID() {
		return taskID;
	}

	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}

	public Map<String,Object> getTaskParam() {
		return taskParam;
	}

	public void setTaskParam(Map<String,Object> taskParam) {
		this.taskParam = taskParam;
	}
}
