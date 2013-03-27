package cn.cdu.fanger.ac.view;

import java.util.*;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import cn.cdu.fanger.activity.R;

public abstract class IMenuActivity extends AbstractAsyncActivity {
	
	private AlertDialog menuDialog;
	private GridView menuGrid;
	private View menuView; 
//	
//	int[] menu_image_array = { R.drawable.ic_launcher, R.drawable.ic_launcher };
//	String[] menu_name_array = { "����»�", "�������", "����", "ͬ��","����"};
//	

	// ***************************************
	// Activity methods
	// ***************************************
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		onCreateMethod();//������д�������൱��oncreate����
		init();//�˵���ʼ��
	}

	
	private void init(){
		//�˵���ʼ��
        menuView = View.inflate(this, R.layout.gridview_menu, null);
 		menuDialog = new AlertDialog.Builder(this).create();
 		menuDialog.setView(menuView);

 		Window w = menuDialog.getWindow();
 		WindowManager.LayoutParams lp = w.getAttributes();
 		lp = w.getAttributes();
 		lp.x = 0;
 		lp.y = 120;
 		menuDialog.onWindowAttributesChanged(lp);

 		menuGrid = (GridView) menuView.findViewById(R.id.gridview);
 		menuGrid.setAdapter(getMenuAdapter(getMenuNameArray(), getMenuResourceArray()));
 		
 		//menu�¼�
    	menuGrid.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View children, int position,
					long id) {
				handleMenuItemClick(parent,children, position,id);
				//menu�˵���ʧ 
				menuDialog.dismiss();
			}
		});
	}
	
	 private SimpleAdapter getMenuAdapter(String[] menuNameArray,
	 			int[] imageResourceArray) {
	 		ArrayList<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
	 		for (int i = 0; i < menuNameArray.length; i++) {
	 			HashMap<String, Object> map = new HashMap<String, Object>();
	 			map.put("itemImage", imageResourceArray[i]);
	 			map.put("itemText", menuNameArray[i]);
	 			data.add(map);
	 		}
	 		SimpleAdapter simperAdapter = new SimpleAdapter(
	 				this, data,
	 				R.layout.item_menu, 
	 				new String[] { "itemImage", "itemText" },
	 				new int[] { R.id.menu_item_image, R.id.menu_item_text });
	 		return simperAdapter;
		}
	// *******************************************
	// abstract method
	// *******************************************
	
	//set contentView
	protected abstract void onCreateMethod();
	
//	protected abstract String[] getMenuNameArray();
//	protected abstract int[] getMenuResourceArray();
//	protected abstract void handleMenuItemClick(AdapterView<?> parent, View children, int position,long id);
	
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add("menu");
		return super.onCreateOptionsMenu(menu);
	}

	public boolean onMenuOpened(int featureId, Menu menu) {
		if (menuDialog == null) {
			menuDialog = new AlertDialog.Builder(this).setView(menuView).show();
		} else {
			menuDialog.show();
		}
		return false;
	}
	
	protected String[] getMenuNameArray() {
		return new String[]{"����»�", "�������", "����", "ͬ��","����"};
	}

	protected int[] getMenuResourceArray() {
		return new int[]{ R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher };
	}

	protected void handleMenuItemClick(AdapterView<?> parent, View children,
			int position, long id) {
		switch (position) {
		case 0:
			Toast.makeText(this, "���ڴ������ڴ���ͬ�����߼�", Toast.LENGTH_SHORT).show();
			break;
		case 1:
			Toast.makeText(this, "���ڴ������ڴ���ͬ�����߼�", Toast.LENGTH_SHORT).show();
			break;
		case 2:
			Toast.makeText(this, "���ڴ������ڴ���ͬ�����߼�", Toast.LENGTH_SHORT).show();
			break;
		case 3:
			Toast.makeText(this, "���ڴ������ڴ���ͬ�����߼�", Toast.LENGTH_SHORT).show();
			break;
		case 4:
			Toast.makeText(this, "���ڴ������ڴ���ͬ�����߼�", Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}
	}
}
