package cn.cdu.fanger.activity;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import cn.cdu.fanger.ac.view.AbstractAsyncActivity;
import cn.cdu.fanger.ac.view.AbstractMenuActivity;
import cn.cdu.fanger.constant.ServerUrl;
import cn.cdu.fanger.rest.entity.AndrUser;

public class RestActivity extends AbstractMenuActivity{

	@Override
	protected void onCreateMethod() {
		setContentView(R.layout.rest_test_main);

		// Initiate the JSON POST request when the JSON button is clicked
		final Button buttonJson = (Button) findViewById(R.id.btn_rest_show_message);
		buttonJson.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				new PostMessageTask().execute();
			}
		});
	}
	
	@Override
	protected String[] getMenuNameArray() {
		return new String[]{"添加新户", "添加新人", "设置", "同步","下载"};
	}

	@Override
	protected int[] getMenuResourceArray() {
		return new int[]{ R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher };
	}

	@Override
	protected void handleMenuItemClick(AdapterView<?> parent, View children,
			int position, long id) {
		switch (position) {
		case 0:
			Toast.makeText(this, "现在处理正在处理同步的逻辑", Toast.LENGTH_SHORT).show();
			break;
		case 1:
			Toast.makeText(this, "现在处理正在处理同步的逻辑", Toast.LENGTH_SHORT).show();
			break;
		case 2:
			Toast.makeText(this, "现在处理正在处理同步的逻辑", Toast.LENGTH_SHORT).show();
			break;
		case 3:
			Toast.makeText(this, "现在处理正在处理同步的逻辑", Toast.LENGTH_SHORT).show();
			break;
		case 4:
			Toast.makeText(this, "现在处理正在处理同步的逻辑", Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}
	}
	
	//******************************************
	//Spring data
	//******************************************
	private class PostMessageTask extends AsyncTask<Void, Void, AndrUser> {
		private MultiValueMap<String, String> message;
		//执行前
		@Override
		protected void onPreExecute() {
			showLoadingProgressDialog();
			// assemble the map
			message = new LinkedMultiValueMap<String, String>();

			EditText editText = (EditText) findViewById(R.id.edit_text_message_name);
			message.add("name", editText.getText().toString());

			editText = (EditText) findViewById(R.id.edit_text_message_pwd);
			message.add("pwd", editText.getText().toString());

		}
		//后台执行
		@Override
		protected AndrUser doInBackground(Void... params) {
			try{
				// Create a new RestTemplate instance
				RestTemplate restTemplate = new RestTemplate(true);
				restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
				// Make the network request, posting the message, expecting a String in response from the server
				ResponseEntity<AndrUser> response = restTemplate.postForEntity(ServerUrl.userList, HttpMethod.GET, AndrUser.class);
				// Return the response body to display to the user
				return response.getBody();
			}catch (Exception e) {
				Log.e(TAG, e.getMessage(),e);
			}
			return null;
		}
		//执行后
		@Override
		protected void onPostExecute(AndrUser result) {
			dismissProgressDialog();
			showResult(result);
		}

		
	}
	
	private void showResult(AndrUser result) {
		if (result != null) {
			// display a notification to the user with the response message
			Toast.makeText(this, result.getEmail(), Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(this, "I got null, something happened!", Toast.LENGTH_LONG).show();
		}
	}
}
