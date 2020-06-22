package com.zyf.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 场景题，实现一个ActionManager，其中包括
 * registerAction(String action, IActionCallback callback)，
 * unregisterAction(IActionCallback callback),notifyChange(string action)三个方法。
 * 类似于BroadCastReceiver，一个action可能对应多个Callback，
 * notifyChange中要回调action对应的所有Callback对象的方法
 */
public class ActionManager {

    private HashMap<String, List<IActionCallback>> hashMap;

    //注册
    public void registerAction(String action, IActionCallback callback){
        if (hashMap == null) {
            hashMap = new HashMap<>();
        }
        if (hashMap.get(action) != null) {
            List<IActionCallback> list = hashMap.get(action);
            if (list != null) {
                list.add(callback);
            }
            return;
        }
        List<IActionCallback> list = new ArrayList<>();
        list.add(callback);
        hashMap.put(action, list);
    }

    //通知改变
    public void notifyChange(String action){
        if (hashMap.get(action) != null) {
            List<IActionCallback> list = hashMap.get(action);
            if (list != null && list.size() > 0) {
                for (IActionCallback callback : list) {
                    callback.onCallback();
                }
            }
        }
    }

    //取消注册
    public void unregisterAction(String action, IActionCallback callback){
        hashMap.remove(action);
        callback.onCallback();
    }

    public interface IActionCallback{
        void onCallback();
    }
}
