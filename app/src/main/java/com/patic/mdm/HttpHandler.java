package com.patic.mdm;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

public class HttpHandler {
    public String post(String posturl){
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(posturl);

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("data","Variable 1"));
            params.add(new BasicNameValuePair("info","Otro mensaje"));
            httppost.setEntity(new UrlEncodedFormEntity(params));

            HttpResponse resp = httpclient.execute(httppost);
            HttpEntity ent = resp.getEntity();

            String text = EntityUtils.toString(ent);
            return text;
        }
        catch(Exception e){
            return "error";
        }
    }
}
