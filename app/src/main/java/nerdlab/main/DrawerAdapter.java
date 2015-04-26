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
		final ViewHolder  viewHolder;
		if(convertView==null){
			view=LayoutInflater.from(getContext()).inflate(resourced, null);
			viewHolder=new ViewHolder();
			viewHolder.drawerImage=(ImageView)view.findViewById(R.id.profile_image);
			viewHolder.drawerText=(TextView)view.findViewById(R.id.navDrawerTextView);
			viewHolder.drawerText.setTextAppearance(getContext(), R.style.normalText);
			if(position==0){
				viewHolder.drawerImage.getLayoutParams().height=77;
				viewHolder.drawerImage.getLayoutParams().width=77;
				viewHolder.drawerText.setTextAppearance(getContext(),R.style.boldText);
				TextView textView=(TextView)view.findViewById(R.id.navDrawerProfile);
				textView.setText("zerowxm@gmail.com");
			}

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

	

