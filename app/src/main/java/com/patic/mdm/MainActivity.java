package com.patic.mdm;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends Activity {

    JSONObject info = new JSONObject();

    String[] appsNecesarias = {"Gmail","Contactos","Calendario","TeamViewer","Documentos","Grabadora de voz","Hangouts","AndFTP","Business One","Maps","Skype","Dropbox","Notas de Keep"};
    ArrayList<String> appsInstaladas = new ArrayList<String>();
    ArrayList<String> appsPorInstalar = new ArrayList<String>(Arrays.asList(appsNecesarias));

    Button btnCorreo;
    Button btnContactos;
    Button btnCalendario;
    Button btnRemote;
    Button btnDocs;
    Button btnVozVideo;
    Button btnMessenger;
    Button btnFtp;
    Button btnSap;
    Button btnMaps;
    Button btnSkype;
    Button btnDropbox;
    Button btnKeep;

    EditText correo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apps_list);
        ImageView imgCorreo = (ImageView) findViewById(R.id.imgCorreo);
        imgCorreo.setImageResource(R.drawable.gmail_icon);
        ImageView imgContactos = (ImageView) findViewById(R.id.imgContactos);
        imgContactos.setImageResource(R.drawable.contactos_icon);
        ImageView imgCalendario = (ImageView) findViewById(R.id.imgCalendario);
        imgCalendario.setImageResource(R.drawable.calendario_icon);
        ImageView imgRemote = (ImageView) findViewById(R.id.imgRemote);
        imgRemote.setImageResource(R.drawable.remote_icon);
        ImageView imgDocs = (ImageView) findViewById(R.id.imgDocs);
        imgDocs.setImageResource(R.drawable.docs_icon);
        ImageView imgGrabadora = (ImageView) findViewById(R.id.imgVozVideo);
        imgGrabadora.setImageResource(R.drawable.grabadora_icon);
        ImageView imgMessenger = (ImageView) findViewById(R.id.imgMessenger);
        imgMessenger.setImageResource(R.drawable.hangouts_icon);
        ImageView imgFtp = (ImageView) findViewById(R.id.imgFTP);
        imgFtp.setImageResource(R.drawable.ftp_icon);
        ImageView imgSap = (ImageView) findViewById(R.id.imgSAP);
        imgSap.setImageResource(R.drawable.sap_icon);
        ImageView imgMaps = (ImageView) findViewById(R.id.imgMaps);
        imgMaps.setImageResource(R.drawable.maps_icon);
        ImageView imgSkype = (ImageView) findViewById(R.id.imgSkype);
        imgSkype.setImageResource(R.drawable.skype_icon);
        ImageView imgDropbox = (ImageView) findViewById(R.id.imgDropbox);
        imgDropbox.setImageResource(R.drawable.dropbox_icon);
        ImageView imgKeep = (ImageView) findViewById(R.id.imgKeep);
        imgKeep.setImageResource(R.drawable.keep_icon);

        EditText correo = (EditText) findViewById(R.id.editMail);

        btnCorreo = (Button) findViewById(R.id.btnCorreo);
        btnContactos = (Button) findViewById(R.id.btnContactos);
        btnCalendario = (Button) findViewById(R.id.btnCalendario);
        btnRemote = (Button) findViewById(R.id.btnRemote);
        btnDocs = (Button) findViewById(R.id.btnDocs);
        btnVozVideo = (Button) findViewById(R.id.btnVozVideo);
        btnMessenger = (Button) findViewById(R.id.btnMessenger);
        btnFtp = (Button) findViewById(R.id.btnFTP);
        btnSap = (Button) findViewById(R.id.btnSAP);
        btnMaps = (Button) findViewById(R.id.btnMaps);
        btnSkype = (Button) findViewById(R.id.btnSkype);
        btnDropbox = (Button) findViewById(R.id.btnDropbox);
        btnKeep = (Button) findViewById(R.id.btnKeep);
    }

    public void Analizar(View view){
        appsInstaladas.clear();
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> AppsList =   getPackageManager().queryIntentActivities( mainIntent, 0);
        for(ResolveInfo appInfo : AppsList){
            String name = appInfo.activityInfo.applicationInfo.loadLabel(getPackageManager()).toString();
            for(int i=0;i<appsNecesarias.length;i++){
                if(name.equals(appsNecesarias[i])){
                    appsInstaladas.add(name);
                }
            }
        }

        if(appsInstaladas.contains("Gmail"))
            btnCorreo.setEnabled(false);
        else
            btnCorreo.setEnabled(true);

        if(appsInstaladas.contains("Contactos"))
            btnContactos.setEnabled(false);
        else
            btnContactos.setEnabled(true);

        if(appsInstaladas.contains("Calendario"))
            btnCalendario.setEnabled(false);
        else
            btnCalendario.setEnabled(true);

        if(appsInstaladas.contains("TeamViewer"))
            btnRemote.setEnabled(false);
        else
            btnRemote.setEnabled(true);

        if(appsInstaladas.contains("Documentos"))
            btnDocs.setEnabled(false);
        else
            btnDocs.setEnabled(true);

        if(appsInstaladas.contains("Grabadora de voz"))
            btnVozVideo.setEnabled(false);
        else
            btnVozVideo.setEnabled(true);

        if(appsInstaladas.contains("Hangouts"))
            btnMessenger.setEnabled(false);
        else
            btnMessenger.setEnabled(true);

        if(appsInstaladas.contains("AndFTP"))
            btnFtp.setEnabled(false);
        else
            btnFtp.setEnabled(true);

        if(appsInstaladas.contains("Business One"))
            btnSap.setEnabled(false);
        else
            btnSap.setEnabled(true);

        if(appsInstaladas.contains("Maps"))
            btnMaps.setEnabled(false);
        else
            btnMaps.setEnabled(true);

        if(appsInstaladas.contains("Skype"))
            btnSkype.setEnabled(false);
        else
            btnSkype.setEnabled(true);

        if(appsInstaladas.contains("Dropbox"))
            btnDropbox.setEnabled(false);
        else
            btnDropbox.setEnabled(true);

        if(appsInstaladas.contains("Notas de Keep"))
            btnKeep.setEnabled(false);
        else
            btnKeep.setEnabled(true);

        modificarJSON(appsInstaladas,correo.getText().toString());
    }

    public void instalarCorreo(View view){
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.google.android.gm")));
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.gm")));
        }
    }

    public void instalarContactos(View view){
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.google.android.contacts")));
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.contacts")));
        }
    }

    public void instalarCalendario(View view){
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.google.android.calendar")));
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.calendar")));
        }
    }

    public void instalarRemote(View view){
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.teamviewer.teamviewer.market.mobile")));
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.teamviewer.teamviewer.market.mobile")));
        }
    }

    public void instalarDocs(View view){
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.google.android.apps.docs.editors.docs")));
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.docs.editors.docs")));
        }
    }

    public void instalarGrabadora(View view){
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.media.bestrecorder.audiorecorder")));
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.media.bestrecorder.audiorecorder")));
        }
    }

    public void instalarMessenger(View view){
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.google.android.talk")));
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.talk")));
        }
    }

    public void instalarFtp(View view){
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=lysesoft.andftp")));
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=lysesoft.andftp")));
        }
    }

    public void instalarSap(View view){
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=b1.mobile.android")));
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=b1.mobile.android")));
        }
    }

    public void instalarMaps(View view){
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.google.android.apps.maps")));
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps")));
        }
    }

    public void instalarSkype(View view){
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.skype.raider")));
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.skype.raider")));
        }
    }

    public void instalarDropbox(View view){
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.dropbox.android")));
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.dropbox.android")));
        }
    }

    public void instalarKeep(View view){
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.google.android.keep")));
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.keep")));
        }
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
