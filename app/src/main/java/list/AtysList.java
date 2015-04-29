package list;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guideapplication.R;

import java.util.ArrayList;

import base.BaseList;
import base.BaseUi;
import model.Atys;
import model.AtysListItem;
import model.Blog;
import nerdlab.main.BaseListFragment;
import util.AppCache;

/**
 * Created by chizhang on 15/4/24.
 */
public class AtysList extends BaseList {
    BaseListFragment blfragment;
    private ArrayList<Atys> atyList;
    private Context context;

    public AtysList (BaseListFragment blfragment,ArrayList<Atys> atyList) {
        this.blfragment=blfragment;
        this.atyList=atyList;
        this.context=blfragment.getActivity();
    }
    public Context getContext() {
        return this.getContext();
    }

    @Override
    public int getCount() {
        return atyList.size();
    }

    @Override
    public Object getItem(int position) {
        return atyList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int p, View v, ViewGroup parent) {
        // init tpl
        AtysListItem atysItem = null;
        // if cached expired
        if (v == null) {
            v= LayoutInflater.from(context).inflate(R.layout.aty_list_item,null);
            atysItem = new AtysListItem();
            atysItem.ivFace = (ImageView) v.findViewById(R.id.ivFace);
            atysItem.ivPicture = (ImageView) v.findViewById(R.id.ivPic);
            atysItem.tvName = (TextView) v.findViewById(R.id.tvName);
            atysItem.tvTime = (TextView) v.findViewById(R.id.tvTime);
            atysItem.tvTitle = (TextView) v.findViewById(R.id.tvTitle);
            atysItem.tvContent = (TextView) v.findViewById(R.id.tvContent);
            atysItem.btnLike = (Button) v.findViewById(R.id.btnLike);
            atysItem.btnLook = (Button) v.findViewById(R.id.btnLike);
            v.setTag(atysItem);
        } else {
            atysItem = (AtysListItem) v.getTag();
        }
        // fill data
        Atys atysList = atyList.get(p);
        atysItem.tvName.setText(atysList.getUsername());
        // fill html data
        atysItem.tvTime.setText(atysList.getContent());
        atysItem.tvTitle.setText(atysList.getTitle());
        atysItem.tvContent.setText(atysList.getContent());
        // load face image
        String faceUrl = atysList.getUserface();
        if (faceUrl != null && faceUrl.length() > 0) {
            Bitmap faceImage = AppCache.getImage(faceUrl);
            if (faceImage != null) {
                atysItem.ivFace.setImageBitmap(faceImage);
            }
        } else {
            atysItem.ivFace.setImageBitmap(null);
        }
        // load activity picture
        String picUrl = atysList.getPicture();
        if (picUrl != null && picUrl.length() > 0) {
            Bitmap picImage = AppCache.getImage(picUrl);//network cannot be opened in main thread
            if (picImage != null) {
                atysItem.ivPicture.setImageBitmap(picImage);
                atysItem.ivPicture.setVisibility(View.VISIBLE);
            }
        } else {
            atysItem.ivPicture.setImageBitmap(null);
            atysItem.ivPicture.setVisibility(View.GONE);
        }
        return v;
    }
}
