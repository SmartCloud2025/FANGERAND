package cn.cdu.fanger.view.adpter;

import java.util.List;

import cn.cdu.fanger.activity.R;
import cn.cdu.fanger.rest.entity.AndrSpot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 */
public class AndrSpotsListAdapter extends BaseAdapter {

	private List<AndrSpot> AndrSpots;
	private final LayoutInflater layoutInflater;

	public AndrSpotsListAdapter(Context context, List<AndrSpot> AndrSpots) {
		this.AndrSpots = AndrSpots;
		this.layoutInflater = LayoutInflater.from(context);
	}

	public int getCount() {
		return this.AndrSpots != null ? AndrSpots.size() : 0;
	}

	public AndrSpot getItem(int position) {
		return this.AndrSpots.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = this.layoutInflater.inflate(R.layout.main_list_item, parent, false);
		}

		AndrSpot andrSpot = getItem(position);
		if (andrSpot != null) {
			TextView tittle = (TextView) convertView.findViewById(R.id.main_list_item_title);
			tittle.setText(andrSpot.getName());
			
			TextView info = (TextView) convertView.findViewById(R.id.main_list_item_title);
			info.setText(andrSpot.getSummary());
			
//			TODO
//			ImageView img = (ImageView) convertView.findViewById(R.id.main_list_item_img);
//			
		}

		return convertView;
	}

}
