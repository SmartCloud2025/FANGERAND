package cn.cdu.fanger.view.adpter;

import java.util.List;

import cn.cdu.fanger.ac.view.SpotsService;
import cn.cdu.fanger.activity.R;
import cn.cdu.fanger.rest.entity.AndrSpot;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SpotsAdpter extends BaseAdapter {
	public List<AndrSpot> allList;
	public Context context;

	public SpotsAdpter(Context text, List<AndrSpot> list) {
		allList = list;
		context = text;
	}

	public int getCount() {
		return allList.size() + 1;
	}

	public long getItemId(int index) {
		if (index >= 0 && (index < this.getCount() - 1)) {
			return allList.get(index - 1).getId();// 如果用户选中了中间项
		} else {
			return -1;// 表示用户选中最后一项
		}
	}

	public Object getItem(int position) {
		return allList.get(position);
	}

	// 增加更多的数据
	public void addMoreData(List<AndrSpot> moreSpots) {
		// 把新数据增加到原有集合
		this.allList.addAll(moreSpots);
		// 更新list
		this.notifyDataSetChanged();
	}

	
	public View getView(int position, View convertView, ViewGroup parent) {
		View spotsView=null;	
		if (position == this.getCount() - 1) {
			spotsView = LayoutInflater.from(context).inflate(
					R.layout.activity_main_list_more_item, null);
			TextView tv = (TextView) spotsView.findViewById(R.id.list_more_text);
			tv.setText("更多 ..");
			return spotsView;
		}else {
			return showView(spotsView,position, convertView, parent);
		}
	}
	
	//中间项的显示
	private View showView(View spotsView,int position, View convertView, ViewGroup parent){
		LayoutInflater.from(context).inflate(R.layout.activity_main_list_item, null);
		// 设定这个条目显示的内容
		ListViewHolder holder = null;
		holder = new ListViewHolder();
		holder.ivItemPortrait = (ImageView) spotsView
				.findViewById(R.id.ivItemPortrait);
		holder.tvItemName = (TextView) spotsView
				.findViewById(R.id.tvItemName);
		holder.tvItemContent = (TextView) spotsView
				.findViewById(R.id.tvItemContent);
		holder.tvItemDate = (TextView) spotsView
				.findViewById(R.id.tvItemDate);
		holder.contentPic = (ImageView) spotsView.findViewById(R.id.ivcontentPic);
		
		////////////////////////////////////////
		//	初始化相关内容	
		////////////////////////////////////////
		
		// 设定昵称
		holder.tvItemName.setText(allList.get(position - 1).getCreateBy());
		// 设定内容
		holder.tvItemContent.setText(allList.get(position - 1).getSummary());
		//时间
		holder.tvItemDate.setText(allList.get(position - 1).getCreateAt());
		
		//TODO 图片设置
		if (SpotsService.alluserIcon.get(allList.get(position - 1).getId()) != null) {
			holder.ivItemPortrait.setImageDrawable(SpotsService.alluserIcon.get(allList.get(position - 1).getId()));
		}
		if (SpotsService.allspotIcon.get(allList.get(position - 1).getId()) != null) {
			holder.contentPic.setImageDrawable(SpotsService.allspotIcon.get(allList.get(position - 1).getId()));
		}
		
		return spotsView;
	}
}
