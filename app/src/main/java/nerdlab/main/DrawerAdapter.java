package nerdlab.main;

import java.util.List;

import android.content.Context;
import android.util.SizeF;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guideapplication.R;

public class DrawerAdapter extends ArrayAdapter<NavDrawerItem>{
	private int resourced;
	public DrawerAdapter(Context context, int textViewResourceId,List<NavDrawerItem>objects) {
		super(context, textViewResourceId,objects);
		resourced=textViewResourceId;
		// TODO Auto-generated constructor stub
	}
	public View getView(int position,View convertView,ViewGroup parent){
		NavDrawerItem navDrawerItem=getItem(position);
		View view;
		ViewHolder  viewHolder;
		if(convertView==null){
			view=LayoutInflater.from(getContext()).inflate(resourced, null);
			viewHolder=new ViewHolder();
			viewHolder.drawerImage=(ImageView)view.findViewById(R.id.profile_image);
			if(position==0){
				viewHolder.drawerImage.getLayoutParams().height=377;
				viewHolder.drawerImage.getLayoutParams().width=377;
			}
			//else {
		//		viewHolder.drawerImage=(ImageView)view.findViewById(R.id.navDrawerImageView);

		//	}
			viewHolder.drawerText=(TextView)view.findViewById(R.id.navDrawerTextView);
			view.setTag(viewHolder);
		}
		else{
			view=convertView;
			viewHolder = (ViewHolder) view.getTag();
		}
		viewHolder.drawerImage.setImageResource(navDrawerItem.getImageId());


		viewHolder.drawerText.setText(navDrawerItem.getName());
		return view;
	}
	class ViewHolder {
		ImageView drawerImage;
		TextView drawerText;
	}
}

	

