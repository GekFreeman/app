package nerdlab.guide;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;


import nerdlab.main.DrawerActivity;
import ui.UiLogin;

import com.example.guideapplication.R;

import java.util.ArrayList;


public class GuideActivity extends Activity {

    private ViewPager viewPager;

    private ViewPagerAdapter viewPagerAdapter;

    private ArrayList<View> views;

    private ImageView image3;

    private AnimationView animationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        animationView=new AnimationView();
        LayoutInflater inflater=LayoutInflater.from(this);
        View view1=inflater.inflate(R.layout.guide_page01, null);
        View view2=inflater.inflate(R.layout.guide_page02,null);
        View view3=inflater.inflate(R.layout.guide_page03,null);
        View view4=inflater.inflate(R.layout.guide_page04,null);
        //View view5=findViewById(R.id.first_view);

        viewPager=(ViewPager)findViewById(R.id.guideUI);

        views=new ArrayList<>();
        views.add(view1);
        views.add(view2);
        views.add(view3);
        views.add(view4);

        viewPagerAdapter=new ViewPagerAdapter(views);

        final ImageView image1=(ImageView)findViewById(R.id.page01);
        final ImageView image2=(ImageView)findViewById(R.id.page02);
        image3=(ImageView)findViewById(R.id.page03);
        final ImageView image4=(ImageView)findViewById(R.id.page04);

        Button startbtn = (Button) view4.findViewById(R.id.button);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

                switch (i){
                    case 0:
                        animationView.setAlphaAnimation(image1.getContext());
                        image1.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_record_choosed));
                        image2.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_record));
                        image1.startAnimation(animationView.getAnimation());
                        break;
                    case 1:
                        animationView.setAlphaAnimation(image2.getContext());
                        image1.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_record));
                        image2.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_record_choosed));
                        image3.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_record));
                        image2.startAnimation(animationView.getAnimation());
                        break;
                    case 2:
                        animationView.setAlphaAnimation(image3.getContext());
                        image2.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_record));
                        image3.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_record_choosed));
                        image4.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_record));
                        image3.startAnimation(animationView.getAnimation());
                        break;
                    case 3:
                        animationView.setAlphaAnimation(image4.getContext());
                        image3.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_record));
                        image4.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_record_choosed));
                        image4.startAnimation(animationView.getAnimation());
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        viewPager.setAdapter(viewPagerAdapter);


        startbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent();
                intent.setClass(GuideActivity.this, DrawerActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
