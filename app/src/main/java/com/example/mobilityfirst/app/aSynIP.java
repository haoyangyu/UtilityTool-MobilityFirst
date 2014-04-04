package com.example.mobilityfirst.app;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.ref.SoftReference;
import java.net.URI;

/**
 * Created by haoyang on 4/4/14.
 */
public class aSynIP extends AsyncTask<Void, Void, String> {
    public aSynInterface delegate=null;
    @Override
    protected String doInBackground(Void... voids) {
        BufferedReader in=null;
        String data=null;
        try {
            HttpClient httpclient = new DefaultHttpClient();

            //Connect to the website
            URI website = new URI("http://checkip.dyndns.com/");
            HttpGet httpget = new HttpGet();
            httpget.setURI(website);
            HttpResponse response = httpclient.execute(httpget);

            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            //HttpEntity entity = response.getEntity();
            StringBuffer sb = new StringBuffer("");
            String l = "";
            String nl = System.getProperty("line.separator");
            while ((l = in.readLine()) != null) {
                sb.append(l + nl);
            }
            //String str = EntityUtils.toString(entity);
            in.close();
            data = sb.toString();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return data;
    }
    @Override
    protected void onPostExecute(String result){
        delegate.processFinish(result);
    }
}
