package com.wl.uilib.utils;

import android.content.Context;
import android.content.SharedPreferences;



/**
 * Created by wyh on 2017/9/27.
 */

public class S {
    private static S s ;
    public static SharedPreferences sharedPreferences  ;
    private static SharedPreferences.Editor editor;

    public static S getInstance(Context context){
            if (s == null) {
                synchronized (StringUtil.class) {
                    if (s == null||null == sharedPreferences) {
                        s = new S();
                        sharedPreferences = context.getSharedPreferences("hyperx", Context.MODE_PRIVATE);
                        editor=sharedPreferences.edit();
                    }
                }
            }

        return s;
    }

    /**
     * 获取SharedPreferences实例
     * @return
     */
    public SharedPreferences getSP(){
        return sharedPreferences;
    }
    /**
     * 存储String元素
     * @param key
     * @param value
     */
    public  void putDate(String key, String value){
        editor.putString(key,value);
        editor.commit();
    }

    /**
     * 获取String 元素
     * @param key
     * @return
     */
    public  String getString(String key){
        return sharedPreferences.getString(key,"");
    }


    /**
     * 存储boolean元素
     * @param key
     * @param value
     */
    public  void putDate(String key, boolean value){
        //"token"
        editor.putBoolean(key,value);
        editor.commit();
    }

    /**
     * 获取boolean元素
     * @param key
     * @return
     */
    public  boolean getBoolean(String key){
        return sharedPreferences.getBoolean(key,false);
    }
    /**
     * 清除全部数据
     */
    public  void deleteData(){
        editor.clear();
        editor.commit();
    }

    /**
     * 删除指定元素
     * @param key
     */
    public  void deleteData(String key){
        editor.remove(key);
        editor.commit();
    }
}
