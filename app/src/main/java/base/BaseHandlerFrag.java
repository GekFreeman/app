package base;

import list.AtysList;
import nerdlab.main.AtyListFragment;
import nerdlab.main.BaseListFragment;
import util.AppUtil;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.ListFragment;

public class BaseHandlerFrag extends Handler {

    protected AtyListFragment list;

//	public BaseHandler (BaseUi ui) {
//		this.ui = ui;
//	}

    public BaseHandlerFrag (BaseListFragment list) {this.list= (AtyListFragment) list;}

    public BaseHandlerFrag (Looper looper) {
        super(looper);
    }

    @Override
    public void handleMessage(Message msg) {
        try {
            int taskId;
            String result;
            switch (msg.what) {
                case BaseTask.TASK_COMPLETE:
//					ui.hideLoadBar();
                    taskId = msg.getData().getInt("task");
                    result = msg.getData().getString("data");
                    if (result != null) {
						list.onTaskComplete(taskId, AppUtil.getMessage(result));
                    } else if (!AppUtil.isEmptyInt(taskId)) {
//						list.onTaskComplete(taskId);
                    } else {
                        list.toast(C.err.message);
                    }
                    break;
                case BaseTask.NETWORK_ERROR:
//					ui.hideLoadBar();
                    taskId = msg.getData().getInt("task");
//					ui.onNetworkError(taskId);
                    break;
                case BaseTask.SHOW_LOADBAR:
//					ui.showLoadBar();
                    break;
                case BaseTask.HIDE_LOADBAR:
//					ui.hideLoadBar();
                    break;
                case BaseTask.SHOW_TOAST:
//					ui.hideLoadBar();
                    result = msg.getData().getString("data");
//                    list.toast(result);
                    break;
                 case BaseTask.LOAD_IMAGE:

                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
//            list.toast(e.getMessage());
        }
    }

    public void handleMessage(Message msg, AtysList atysList) {
          atysList.notifyDataSetChanged();

    }

}