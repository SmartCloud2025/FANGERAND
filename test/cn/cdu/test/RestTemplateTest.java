package cn.cdu.test;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.client.RestTemplate;

import cn.cdu.fanger.constant.ServerUrl;

public class RestTemplateTest {
	private static RestTemplate restTemplate;
	
	static {
		restTemplate = new RestTemplate();
	}
	public RestTemplateTest(){}
	
	public static String OAuth(String username,String password){
        Map<String, String> request = new HashMap<String, String>(); 
        request.put("loginname", username);
        request.put("loginpassword", password);
        return restTemplate.getForObject(ServerUrl.userList, String.class);
	}
	
	public static void main(String[] args){
		System.out.println("==============");
		System.out.println(restTemplate.getForObject(ServerUrl.userList, String.class));
		System.out.println("==============");
	}
}
