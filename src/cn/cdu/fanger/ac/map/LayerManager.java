package cn.cdu.fanger.ac.map;

import java.util.List;

import android.graphics.Point;
import cn.cdu.fanger.rest.entity.AndrSpot;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class LayerManager {
	
	MyItemizedOverlay overlay;
	List<AndrSpot> spots;
	
	public LayerManager(MyItemizedOverlay overlay,List<AndrSpot> spots){
		this.overlay = overlay;
		this.spots = spots;
	}
	
	public MyItemizedOverlay buildData(){
		GeoPoint point; String title; String snippet; 
		for(AndrSpot spot : spots){
			point = new GeoPoint(
					(int) (spot.getLng() * 1E6), 
					(int) (spot.getLat() * 1E6)
					);
			title = spot.getName();
			snippet = spot.getSummary();
			OverlayItem item = new OverlayItem(point, title, snippet);
			this.overlay.addOverlay(item);
		}
		return overlay;
	}
	
}
