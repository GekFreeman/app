package nerdlab.main;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.app.ActionBar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewOverlay;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guideapplication.R;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.ikimuhendis.ldrawer.ActionBarDrawerToggle;
import com.ikimuhendis.ldrawer.DrawerArrowDrawable;

import java.util.ArrayList;
import java.util.List;


public class DrawerActivity extends FragmentActivity{

    private DrawerLayout mDrawerLayout;

    private ListView mDrawerList;

    private ActionBarDrawerToggle mDrawerToggle;

    private DrawerArrowDrawable drawerArrow;

    private ActionBar actionBar;

    private DrawerAdapter drawerAdapter;

    private FloatingActionsMenu actionMenu;

    private List<NavDrawerItem> navDrawerItems=new ArrayList<NavDrawerItem>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        handleIntent(getIntent());
        if(savedInstanceState==null){
            Log.d("TEST", "NULL");
            getSupportFragmentManager().beginTransaction().add(R.id.content, new FragmentParent()).commit();

        }else {
            Log.d("TEST","NOT NULL");
        }
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        actionBar=getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.navdrawer);

        drawerArrow = new DrawerArrowDrawable(this) {
            @Override
            public boolean isLayoutRtl() {
                return false;
            }
        };

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                drawerArrow, R.string.drawer_open, R.string.drawer_close) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                actionMenu.collapse();
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        initNavItems();

        drawerAdapter=new DrawerAdapter(DrawerActivity.this,R.layout.drawer_list_item,navDrawerItems);
        mDrawerList.setAdapter(drawerAdapter);
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (findViewById(R.id.action_a).getVisibility() == View.VISIBLE) {
                    findViewById(R.id.multiple_actions).performClick();
                }
                switch (position) {
                    case 0:
                        getSupportFragmentManager().beginTransaction().replace(R.id.content, new Fragment1()).commit();
                        mDrawerLayout.closeDrawer(mDrawerList);
                        break;
                    case 1:
                        RelativeLayout relativeLayout = (RelativeLayout) mDrawerList.getAdapter().getView(position, null, null);
                        TextView textView = (TextView) relativeLayout.getChildAt(2);
                        textView.setTextAppearance(getApplicationContext(), R.style.boldText);
                        getSupportFragmentManager().beginTransaction().replace(R.id.content, new Fragment2()).commit();
                        mDrawerLayout.closeDrawer(mDrawerList);

                        break;
                    case 2:
                        mDrawerToggle.syncState();
                        getSupportFragmentManager().beginTransaction().replace(R.id.content, new FragmentParent()).commit();
                        mDrawerLayout.closeDrawer(mDrawerList);
                        break;
                    case 3:
                        mDrawerLayout.closeDrawer(mDrawerList);
                        break;
                    case 4:
                        mDrawerLayout.closeDrawer(mDrawerList);
                        break;
                }

            }
        });

        actionMenu=(FloatingActionsMenu)findViewById(R.id.multiple_actions);
//        FloatingActionsMenu.OnFloatingActionsMenuUpdateListener listener = new FloatingActionsMenu.OnFloatingActionsMenuUpdateListener() {
//            @Override
//            public void onMenuExpanded() { }
//
//            @Override
//            public void onMenuCollapsed() { }
//        };
//
//        actionMenu.setOnFloatingActionsMenuUpdateListener(listener);
        FloatingActionButton actionB = (FloatingActionButton )findViewById(R.id.action_b);
        actionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DrawerActivity.this, "Search", Toast.LENGTH_SHORT).show();
                actionMenu.collapse();
            }
        });
        FloatingActionButton actionA=(FloatingActionButton )findViewById(R.id.action_a);
        actionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DrawerActivity.this, "Post", Toast.LENGTH_SHORT).show();
                showDialog();
                actionMenu.collapse();
            }
        });
//        actionMenu.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getAction() == MotionEvent.ACTION_UP) {
//                    // Do what you want
//                    Toast.makeText(DrawerActivity.this, "Touch", Toast.LENGTH_SHORT).show();
//                    return true;
//                }
//                return true; // consume the event
//            }
//        });
    }

    private void showDialog()
    {
        DistributeDialogFragment fragment = new DistributeDialogFragment();

        //get the fragmentmanager
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();

        //create a dialog and show it
        fragment.show(fm, "dialog");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_drawer, menu);
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
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
        if(id==android.R.id.home){
            if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
                mDrawerLayout.closeDrawer(mDrawerList);
            } else {
                mDrawerLayout.openDrawer(mDrawerList);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @ Override
    public void onBackPressed()
    {
            actionMenu.collapse();
        super.onBackPressed();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        //super.onNewIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //use the query to search your data somehow
        }
    }

    private void initNavItems(){
        NavDrawerItem item1=new NavDrawerItem(R.drawable.test,"Tamako");
        navDrawerItems.add(item1);
        NavDrawerItem item2=new NavDrawerItem(0,"Messages");
        navDrawerItems.add(item2);
        NavDrawerItem item3=new NavDrawerItem(0,"Activities");
        navDrawerItems.add(item3);
        NavDrawerItem item4=new NavDrawerItem(0,"Freinds");
        navDrawerItems.add(item4);
        NavDrawerItem item5=new NavDrawerItem(0,"Notifactions");
        navDrawerItems.add(item5);
    }
}
