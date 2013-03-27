package cn.cdu.fanger.utill;

import java.net.MalformedURLException;
import java.net.URL;

import junit.framework.TestCase;

public class NetUtilTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testGetImageFromUrl() throws MalformedURLException {
		URL url = new URL("http://fanger.cloudfoundry.com/resources/spot/1363867388816.jpg");//"http://fanger.cloudfoundry.com/resources/spot/1363867388816.jpg";
		System.out.println(NetUtil.getImageFromUrl(url));
	}

}
