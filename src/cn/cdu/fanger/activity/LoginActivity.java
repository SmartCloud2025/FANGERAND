package cn.cdu.fanger.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import cn.cdu.fanger.ac.view.AbstractAsyncActivity;

public class LoginActivity extends AbstractAsyncActivity{
	private EditText username;
	private EditText userpwd;
	private Button loginbtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		bindComponents();
		addListener();
	}

	private void bindComponents() {
		username = (EditText) findViewById(R.id.login_username);
		userpwd = (EditText) findViewById(R.id.login_password);
		loginbtn = (Button) findViewById(R.id.login_submit);
	}
	
	private void addListener() {
		loginbtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				startActivity(new Intent(LoginActivity.this, MainActivity.class));//转到请求页面
			}
		});
		
	}

}
