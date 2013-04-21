package cn.cdu.fanger.activity;

import cn.cdu.fanger.ac.view.SpotsService;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends TabActivity{
	private RadioGroup group;
	private TabHost tabHost;
	public static final String TAB_HOME="tabHome";
	public static final String TAB_MES="tabMes";
	public static final String TAB_TOUCH="tab_touch";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_tabs);
		SpotsService.allActivity.add(MainActivity.this);
		
		group = (RadioGroup)findViewById(R.id.main_radio);
		tabHost = getTabHost();
		tabHost.addTab(tabHost.newTabSpec(TAB_HOME)
	                .setIndicator(TAB_HOME)
	                .setContent(new Intent(this,ListMainActivity.class)));
	    tabHost.addTab(tabHost.newTabSpec(TAB_MES)
	                .setIndicator(TAB_MES)
	                .setContent(new Intent(this,MapMainActivity.class)));
	    tabHost.addTab(tabHost.newTabSpec(TAB_TOUCH)
	    		.setIndicator(TAB_TOUCH)
	    		.setContent(new Intent(this,LoginActivity.class)));
	    group.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.radio_button0:
					tabHost.setCurrentTabByTag(TAB_HOME);
					break;
				case R.id.radio_button1:
					tabHost.setCurrentTabByTag(TAB_MES);
					break;
				case R.id.radio_button2:
					tabHost.setCurrentTabByTag(TAB_TOUCH);
					break;

				default:
					break;
				}
			}
		});
	}
}
