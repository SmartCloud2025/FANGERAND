package cn.cdu.fanger.activity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import cn.cdu.fanger.ac.map.MLocationManager;
import cn.cdu.fanger.ac.map.MLocationManager.LocationCallBack;
import cn.cdu.fanger.ac.map.MyItemizedOverlay;
import cn.cdu.fanger.ac.view.AbstractAsyncMapActivity;
import cn.cdu.fanger.ac.view.IBaseActivity;
import cn.cdu.fanger.ac.view.SpotsService;
import cn.cdu.fanger.constant.Task;
import cn.cdu.fanger.rest.entity.AndrSpot;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class MapMainActivity extends AbstractAsyncMapActivity  implements IBaseActivity, LocationCallBack ,OnClickListener{
	protected static final String TAG = MapMainActivity.class.getSimpleName();
	private int pageSize=4,pageNow=1;
	
	private MapView mapView;
	private MapController mapCtrl;
	
	private View popView;
	private Drawable myLocaltionDrawable,myLongPressDrawable;
	public GeoPoint locPoint;
	
	
	private List<Overlay> mapOverlays;
	private OverlayItem overlayitem = null;
	private MyItemizedOverlay myLocationOverlay;//我的位置 层
	private MyItemizedOverlay mLongPressOverlay; //长按时间层
	
	private MLocationManager localManager;
	
	public static final int MSG_LIST_SPOTS = 10001;//展示所有spot
	
	private Button swithBtn;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_map);
		mapView = (MapView) findViewById(R.id.MapView01);
		SpotsService.allActivity.add(this);
		
		View topBar = findViewById(R.id.map_top_view);
		swithBtn = (Button) topBar.findViewById(R.id.main_button_switch);
		// 设置为交通模式
		// mapView.setTraffic(true);
		// 设置为卫星模式
		// mapView.setSatellite(true);
		// 设置为街景模式
		mapView.setStreetView(false);
		// 取得MapController对象(控制MapView)
		mapCtrl = mapView.getController();
		mapView.setEnabled(true);
		mapView.setClickable(true);
		// 设置地图支持缩放
		mapView.setBuiltInZoomControls(true);


		// 添加Overlay，用于显示标注信息
		myLocaltionDrawable = getResources().getDrawable(R.drawable.point_current);
		myLongPressDrawable = getResources().getDrawable(R.drawable.point_where);
		
		initPopview();
		//当前位置
		myLocationOverlay = new MyItemizedOverlay(myLocaltionDrawable, this, mapView, popView, mapCtrl);
		//长按图层
		mLongPressOverlay = new MyItemizedOverlay(myLongPressDrawable,this, mapView, popView, mapCtrl);
				
		mapOverlays = mapView.getOverlays();
//		mapOverlays.add(new LongPressOverlay(MapMainActivity.this, mapView, handler, mapCtrl));
		
		//获取当前位置
		MLocationManager.init(MapMainActivity.this.getApplicationContext(), MapMainActivity.this);
		localManager = MLocationManager.getInstance();
		
		//release some task
		init();
		
		//=======设置成都为中心=========//
		locPoint = new GeoPoint(
				(int) (30.659259 * 1000000),
				(int) (104.065762 * 1000000));
		// 定位到成都
		mapCtrl.animateTo(locPoint);
		// 设置倍数(1-21)
		mapCtrl.setZoom(5);
		addListener();
	}

	protected boolean isRouteDisplayed() {
		return true;
	}
	
	private void initPopview(){
		if(null == popView){
			popView = getLayoutInflater().inflate(R.layout.map_overlay_popup, null);
			mapView.addView(popView, new MapView.LayoutParams(
					MapView.LayoutParams.WRAP_CONTENT,
					MapView.LayoutParams.WRAP_CONTENT, null,
					MapView.LayoutParams.BOTTOM_CENTER));
			popView.setVisibility(View.GONE);
    	}
	}
	
	@Override
	public void onCurrentLocation(Location location) {
		Log.d(TAG, "onCurrentLocationy");
		GeoPoint point = new GeoPoint(
				(int) (location.getLatitude() * 1E6),
				(int) (location.getLongitude() * 1E6));
		overlayitem = new OverlayItem(point, "我的位置", "");
		mapCtrl.setZoom(16);
		if(myLocationOverlay.size() > 0){
			myLocationOverlay.removeOverlay(0);
		}
		myLocationOverlay.addOverlay(overlayitem);
		mapOverlays.add(myLocationOverlay);
		mapCtrl.animateTo(point);
	}
	
	@Override
	public void onClick(View v) {
		
	}
	
	
	@SuppressLint("HandlerLeak")
	@SuppressWarnings("unused")
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
				case MSG_LIST_SPOTS:
					new Thread(new Runnable() {
						@Override
						public void run() {
							// TODO Auto-generated method stub
							
						}
					}).start();
					//处理数据
					
					break;
				default:
					break;
			}
			
		}
		
	};
	
	
	class MyLocationOverlay extends Overlay {
		@Override
		public boolean draw(Canvas canvas, MapView mapView, boolean shadow,
				long when) {
			super.draw(canvas, mapView, shadow);
			Paint paint = new Paint();
			Point myScreenCoords = new Point();
			// 将经纬度转换成实际屏幕坐标
			mapView.getProjection().toPixels(locPoint, myScreenCoords);
			paint.setStrokeWidth(1);
			paint.setARGB(255, 255, 0, 0);
			paint.setStyle(Paint.Style.STROKE);
			Bitmap bmp = BitmapFactory.decodeResource(getResources(),
					R.drawable.ic_launcher);
			canvas.drawBitmap(bmp, myScreenCoords.x, myScreenCoords.y, paint);
			canvas.drawText("天府广场", myScreenCoords.x, myScreenCoords.y, paint);
			return true;
		}
	}


	@Override
	public void init() {
		//send message to ask some spots data
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("pagenow", (Integer) pageNow);
		map.put("pagesize", (Integer) pageSize);
		
		Task task = new Task(Task.GET_MAP_SPOT_LIST, map);
		SpotsService.newTask(task);
		this.showLoadingProgressDialog();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void refresh(Object... param) {
		// add spots data to layers 
		this.dismissProgressDialog();
		//handle the result
		int type = ((Integer) (param[0])).intValue();
		List<AndrSpot> resultData = null;
		switch (type) {
			case MSG_LIST_SPOTS:
				resultData = (List<AndrSpot>) param[1];
				putData2Map(resultData,mLongPressOverlay);
				
				break;
			default: 
				break;
		}
	}
	
	private void putData2Map(List<AndrSpot> spots,MyItemizedOverlay overlay){
		for(AndrSpot spot : spots){
			GeoPoint point = new GeoPoint(
					(int) (spot.getLat() * 1E6),
					(int) (spot.getLng() * 1E6));
			overlayitem = new OverlayItem(point, spot.getSummary(), spot.getName());
			mapCtrl.setZoom(5);
			overlay.addOverlay(overlayitem);
		}
		mapOverlays.add(overlay);
	}
	
	private void addListener(){
		swithBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MapMainActivity.this, ListMainActivity.class));
			}
		});
	}
}