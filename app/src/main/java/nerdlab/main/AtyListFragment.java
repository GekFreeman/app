package nerdlab.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.guideapplication.R;

import org.apache.http.NameValuePair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import base.BaseHandler;
import base.BaseHandlerFrag;
import base.BaseMessage;
import base.BaseTask;
import base.BaseTaskPool;
import base.BaseUi;

import base.C;
import model.Atys;
import nerdlab.main.dummy.DummyContent;
import ui.UiBlog;
import ui.UiIndex;


public class AtyListFragment extends BaseListFragment {

    private ListView lv;

    private AtyAdapter adapter;

    protected BaseTaskPool taskPool;

    protected BaseHandlerFrag handler;

    private  ArrayList<Atys> atylist;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public AtyListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.aty_list_layout, container, false);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        this.setHandler(new IndexHandler(this));

        this.taskPool = new BaseTaskPool(this);
        this.handler=new BaseHandlerFrag(this);

//        atylist=new ArrayList<Atys>();
//
//        atylist.add(new Atys("id:1", "user_name", "user_face", "title", "content", "comment_id", "pubtime", "picture", "join_count", "like_count"));
//        atylist.add(new Atys("id:2", "user_name", "user_face", "title", "content", "comment_id", "pubtime", "picture", "join_count", "like_count"));
//
//        adapter=new AtyAdapter(this.getActivity(), atylist);//调试ListView
////
//        setListAdapter(adapter);

    }

    public void onStart(){
        super.onStart();

        // show all blog list
        HashMap<String, String> blogParams = new HashMap<String, String>();
        blogParams.put("typeId", "0");
        blogParams.put("pageId", "0");
        this.doTaskAsync(C.task.blogList, C.api.blogList, blogParams);
//        atylist=new ArrayList<Atys>();
//
//        atylist.add(new Atys("id:1", "user_name", "user_face", "title", "content", "comment_id", "pubtime", "picture", "join_count", "like_count"));
//        atylist.add(new Atys("id:2", "user_name", "user_face", "title", "content", "comment_id", "pubtime", "picture", "join_count", "like_count"));
//
//        adapter=new AtyAdapter(this.getActivity(), atylist);//调试ListView
//        setListAdapter(adapter);
    }

    public void sendMessage (int what, int taskId, String data) {
        Bundle b = new Bundle();
        b.putInt("task", taskId);
        b.putString("data", data);
        Message m = new Message();
        m.what = what;
        m.setData(b);
        handler.sendMessage(m);
    }

    public void toast (String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }



    public void onTaskComplete(int taskId, BaseMessage message) {

        super.onTaskComplete(taskId, message);
        switch (taskId) {
            case C.task.blogList:
                try {
//                    @SuppressWarnings("unchecked")
                    Log.w("onTaskComplete is runn", "onTaskCompleteisrunnning!!");
                    atylist = (ArrayList<Atys>) message.getResultList("Atys");
                    adapter=new AtyAdapter(getActivity(), atylist);
                    adapter.notifyDataSetChanged();
//                    lv= (ListView) getActivity().findViewById(android.R.id.list);
//                    adapter=new AtyAdapter(getActivity(), atylist);
                    setListAdapter(adapter);
//                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//                        @Override
//                        public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
//
//                            Toast.makeText(getActivity(),
//                                    "You have selected " ,
//                                    Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                    adapter=new AtyAdapter(this.getActivity(), atylist);
//                  this.setListAdapter(adapter);

                } catch (Exception e) {
                    e.printStackTrace();
//                    toast(e.getMessage());
                }
                break;
        }
    }

    public void setHandler (BaseHandlerFrag handler) {
        this.handler = handler;
    }
    private class IndexHandler extends BaseHandlerFrag {
        public IndexHandler(AtyListFragment list) {
            super(list);
        }
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            try {
                switch (msg.what) {
                    case BaseTask.LOAD_IMAGE:
                        adapter.notifyDataSetChanged();
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
                list.toast(e.getMessage());
            }
        }
    }
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Toast.makeText(getActivity(),
                "You have selected " ,
                Toast.LENGTH_SHORT).show();
    }





}
