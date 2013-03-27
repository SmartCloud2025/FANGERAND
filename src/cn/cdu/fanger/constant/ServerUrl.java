package cn.cdu.fanger.constant;

public class ServerUrl {
	public final static String baseUrl = "http://10.0.2.2:8080/FANGER/rest";//"http://10.0.2.2:8080/FANGER/rest/";
	
	
	public final static String userList = baseUrl + "/AnrUser/list";
	public final static String userLogin = baseUrl + "/AnrUser/login";
	
	public final static String spotList = baseUrl + "/spot/list/";//{startPosition},{maxResult}
	
}
