package com.patic.mdm;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends Activity {

    //LIST OF ARRAY STRINGS WHICH WILL SERVE AS LIST ITEMS
    ArrayList<String> listItems=new ArrayList<String>();

    //DEFINING A STRING ADAPTER WHICH WILL HANDLE THE DATA OF THE LISTVIEW
    ArrayAdapter<String> adapter;

    JSONObject info = new JSONObject();

    String[] appsNecesarias = {"Google Drive","Skype","Hangouts","Maps","Remote","Gmail","Google Keep","Fotos"};
    ArrayList<String> appsInstaladas = new ArrayList<String>();
    ArrayList<String> appsPorInstalar = new ArrayList<String>(Arrays.asList(appsNecesarias));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apps_list);
        //adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
        //setListAdapter(adapter);
    }

    public void Analizar(View view){
        listItems.clear();
        Context context = getApplicationContext();
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> AppsList =   getPackageManager().queryIntentActivities( mainIntent, 0);
        for(ResolveInfo appInfo : AppsList){
            String name = appInfo.activityInfo.applicationInfo.loadLabel(getPackageManager()).toString();
            for(int i=0;i<appsNecesarias.length;i++){
                if(name.equals(appsNecesarias[i])){
                    appsInstaladas.add(name);
                    listItems.add(name + " - Instalada");
                }
            }
        }
        for(int i=0;i<appsInstaladas.size();i++)
            appsPorInstalar.remove(appsInstaladas.get(i));

        for(int i=0;i<appsPorInstalar.size();i++)
            listItems.add(appsPorInstalar.get(i) + " - No instalada");

        adapter.notifyDataSetChanged();

        modificarJSON(appsInstaladas,"felipe@gmail.com");
    }

    public void modificarJSON(ArrayList appsInstaladas, String usuario){
        try {
            info.put("usuario", usuario);
            info.put("Google Drive", appsInstaladas.contains("Google Drive"));
            info.put("Skype", appsInstaladas.contains("Skype"));
            info.put("Hangouts", appsInstaladas.contains("Hangouts"));
            info.put("Maps", appsInstaladas.contains("Maps"));
            info.put("Remote", appsInstaladas.contains("Remote"));
            info.put("Gmail", appsInstaladas.contains("Gmail"));
            info.put("Google Keep", appsInstaladas.contains("Google Keep"));
            info.put("Fotos", appsInstaladas.contains("Fotos"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
