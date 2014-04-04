package com.example.mobilityfirst.app;

import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

/**
 * Created by haoyang on 4/3/14.
 */
public class IpRequest {
    //Implement to try to find the Public Ip address
    public String getIpAddress() throws Exception{
        //Define the field of the block to get the IP address
        //object in used to store data temp.
        BufferedReader in=null;
        String data=null;
        try {
            HttpClient httpclient = new DefaultHttpClient();

            //Connect to the website
            URI website=new URI("http://checkip.dyndns.com/");
            HttpGet httpget = new HttpGet();
            httpget.setURI(website);
            HttpResponse response=httpclient.execute(httpget);

            in =new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            //HttpEntity entity = response.getEntity();
            StringBuffer sb=new StringBuffer("");
            String l="";
            String nl= System.getProperty("line.separator");
            while ((l=in.readLine())!=null){
                sb.append(l+nl);
            }
            //String str = EntityUtils.toString(entity);
            in.close();
            data=sb.toString();
            return data;
                    /*entity.getContentLength();
                    //Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
                    //JSONObject json_data = new JSONObject(str);
                    //ip = json_data.getString("ip");
                    Toast.makeText(getApplicationContext(), ip, Toast.LENGTH_LONG).show();*/
        }finally {
            if (in!=null){
                try{
                    in.close();
                    return data;
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
