package cn.cdu.fanger.ac.view;

import cn.cdu.fanger.activity.R;
import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;


public abstract class AbstractAsyncListActivity extends Activity implements AsyncActivity {

	protected static final String TAG = AbstractAsyncActivity.class.getSimpleName();

	private ProgressDialog progressDialog;

	private boolean destroyed = false;
	
	
	

	// ***************************************
	// Activity methods
	// ***************************************
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_activity_layout);

		final TextView textViewDescription = (TextView) this.findViewById(R.id.text_list_view_description);
		textViewDescription.setText(getDescription());

		final ListView listView = (ListView) this.findViewById(R.id.list_view_list_items);
		
		listView.setAdapter(getListAdapter());
		listView.setOnItemClickListener(getMenuOnItemClickListener());
	}

	// ***************************************
	// Abstract methods
	// ***************************************
	protected abstract String getDescription();

	//protected abstract String[] getMenuItems();

	protected abstract OnItemClickListener getMenuOnItemClickListener();
	
	protected abstract ListAdapter getListAdapter();//new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getMenuItems());
	
	// ***************************************
	// Activity methods
	// ***************************************
	@Override
	protected void onDestroy() {
		super.onDestroy();
		this.destroyed = true;
	}

	// ***************************************
	// Public methods
	// ***************************************
	public void showLoadingProgressDialog() {
		this.showProgressDialog("Loading. Please wait...");
	}

	public void showProgressDialog(CharSequence message) {
		if (this.progressDialog == null) {
			this.progressDialog = new ProgressDialog(this);
			this.progressDialog.setIndeterminate(true);
		}

		this.progressDialog.setMessage(message);
		this.progressDialog.show();
	}

	public void dismissProgressDialog() {
		if (this.progressDialog != null && !this.destroyed) {
			this.progressDialog.dismiss();
		}
	}

}
