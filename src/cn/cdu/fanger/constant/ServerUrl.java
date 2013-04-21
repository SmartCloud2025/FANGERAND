package cn.cdu.fanger.constant;

public class ServerUrl {
	
	public final static String appUrl = "http://fanger.cloudfoundry.com/";//"http://10.0.2.2:8080/FANGER/rest/";
	public final static String baseUrl = appUrl+"rest/";//"http://10.0.2.2:8080/FANGER/rest/";
	
	
	public final static String userList = baseUrl + "user/list";
	public final static String userLogin = baseUrl + "user/login";
	
	public final static String spotList = baseUrl + "spot/list";//?currentPage={currentPage}&pageSize={pageSize}&type={pageSize}
	
}
