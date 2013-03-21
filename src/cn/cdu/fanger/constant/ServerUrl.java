package cn.cdu.fanger.constant;

public class ServerUrl {
	public final static String baseUrl = "http://fanger.cloudfoundry.com";//"http://10.0.2.2:8080/FANGER/rest/";
	
	
	public final static String userList = baseUrl + "/AnrUser/list";
	public final static String userLogin = baseUrl + "/AnrUser/login{loginname},{loginpassword}";
	
	public final static String spotList = baseUrl + "/spot/list/{star},{max}";
	
}
