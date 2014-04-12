package com.example.hangtime;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.os.Build;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		
		LocationManager locManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        LocationListener locListener = new MyLocationListener();
        locManager.requestLocationUpdates( LocationManager.GPS_PROVIDER, 0, 0, locListener);
    
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}
	
	
	public class MyLocationListener implements LocationListener
    {
        TextView tv = (TextView) findViewById(R.id.gpsTextView);

        @Override
        public void onLocationChanged(Location loc){
            Log.d("tag", "Finding Latitude");
            double lat = loc.getLatitude();
            Log.d("tag", "Lat: "+String.valueOf(lat));
            Log.d("tag", "Finding Longitude");
            double lon = loc.getLongitude();
            Log.d("tag", "Lon: "+String.valueOf(lon));
            String Text = "My current location is: " +
            "\nLatitude = " + lat +
            "\nLongitude = " + lon;

            // Display location
            tv.setText(Text);
        }

        @Override
        public void onProviderDisabled(String provider){
            //Toast.makeText(getApplicationContext(), "Gps Disabled", Toast.LENGTH_SHORT ).show();
        	Log.e("ops", "GPS is disabled");
        }

        @Override
        public void onProviderEnabled(String provider){
            //Toast.makeText(getApplicationContext(), "Gps Enabled", Toast.LENGTH_SHORT).show();
        	//tv.setText("GPS is disabled");
        	Log.e("ops", "GPS is enabled");
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras){
        	Log.e("ops", "Status changed");

        }
    }

}
