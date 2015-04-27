package nerdlab.main;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.ListFragment;

import java.util.HashMap;

import base.BaseHandler;
import base.BaseHandlerFrag;
import base.BaseMessage;
import base.BaseTask;
import base.BaseTaskPool;
import list.AtysList;
import util.AppCache;

/**
 * Created by chizhang on 15/4/21.
 */
public class BaseListFragment  extends ListFragment{

    protected BaseTaskPool taskPool;
    protected BaseHandlerFrag handler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.taskPool=new BaseTaskPool(this);
        this.handler = new BaseHandlerFrag(this);
    }

    public Context getContext () {
        return this.getActivity();
    }

    public void sendMessage (int what) {
        Message m = new Message();
        m.what = what;
        handler.sendMessage(m);
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

    public void loadImage (final String url) {
        taskPool.addTask(0, new BaseTask(){
            @Override
            public void onComplete(){
                AppCache.getCachedImage(getContext(), url);
                sendMessage(BaseTask.LOAD_IMAGE);
            }
        }, 0);
    }

    public void doTaskAsync (int taskId, String taskUrl, HashMap<String, String> taskArgs) {
        taskPool.addTask(taskId, taskUrl, taskArgs, new BaseTask(){
            @Override
            public void onComplete (String httpResult) {
                sendMessage(BaseTask.TASK_COMPLETE, this.getId(), httpResult);
            }
            @Override
            public void onError (String error) {
                sendMessage(BaseTask.NETWORK_ERROR, this.getId(), null);
            }
        }, 0);
    }
    public void onTaskComplete (int taskId, BaseMessage message) {

    }
}
