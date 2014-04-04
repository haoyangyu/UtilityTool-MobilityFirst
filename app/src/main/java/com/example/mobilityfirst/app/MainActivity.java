package com.example.mobilityfirst.app;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import java.lang.Exception;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.HttpResponse;
import org.apache.http.HttpEntity;
import org.apache.http.util.*;
import org.json.JSONObject;

public class MainActivity extends ActionBarActivity implements aSynInterface {

    //Variable inputs
    TextView textLat;
    TextView textLong;
    TextView textIP;

    aSynIP myAsyncTask=new aSynIP();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Define the field the block of Geo;
        textLat = (TextView) findViewById(R.id.textLat);
        textLong = (TextView) findViewById(R.id.textLong);

        //Initialize the locationManager object to do location service
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        LocationListener ll = new mylocationlistener();
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, ll);

        textIP=(TextView)findViewById(R.id.textIP);
        textIP.setText("Please wait...");
        myAsyncTask.delegate=this;
        /*IpRequest ip=new IpRequest();
        String returned_ip= null;
        try {
            returned_ip = ip.getIpAddress();
            textIP.setText(returned_ip);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
    @Override
    public void processFinish(String output) {
        textIP.setText(output);
    }

    //Implement mylocationlistener do What we want it to do
     class mylocationlistener implements LocationListener{

            @Override
            public void onLocationChanged(Location location) {
                if(location!=null){
                    double pLat=location.getLatitude();
                    double pLong=location.getLongitude();

                    textLat.setText(Double.toString(pLat));
                    textLong.setText(Double.toString(pLong));
                }
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}