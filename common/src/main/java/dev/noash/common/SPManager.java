package dev.noash.common;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class SPManager {
    private static final String PREFS_NAME = "item_storage";
    private static final String KEY_ITEMS = "items";

    public static void saveItems(Context context, ArrayList<Item> items) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(items);
        editor.putString(KEY_ITEMS, json);
        editor.apply();
    }

    public static ArrayList<Item> loadItems(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String json = prefs.getString(KEY_ITEMS, null);
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Item>>() {}.getType();
        ArrayList<Item> tasks = gson.fromJson(json, type);
        return tasks != null ? tasks : new ArrayList<>();
    }
}
