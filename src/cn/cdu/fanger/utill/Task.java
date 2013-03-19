package cn.cdu.fanger.utill;
import java.util.Map;

public class Task {
	public static final int TASK_1 = 1;
	public static final int TASK_2 = 2;
	
	public static final int TASK_LOGIN = 3;
	public static final int TASK_LOGIN_REFRESH_TEST = 4;
	public static final int TASK_TEST_ADD = 5;
	
	
	private int taskID;// 任务编号
	private Map taskParam;//任务参数

	public Task(int id, Map param) {
		this.taskID = id;
		this.taskParam = param;
	}

	public int getTaskID() {
		return taskID;
	}

	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}

	public Map getTaskParam() {
		return taskParam;
	}

	public void setTaskParam(Map taskParam) {
		this.taskParam = taskParam;
	}

}
