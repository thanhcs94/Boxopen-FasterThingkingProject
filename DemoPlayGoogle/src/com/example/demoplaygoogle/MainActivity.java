package com.example.demoplaygoogle;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.os.Build;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

    	LinearLayout layoutRoot;
    	ImageAdapter imgAdapter;
        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            String[] MOBILE_OS = new String[] { 
        		"Android", "iOS","Windows", "Blackberry" };
            layoutRoot = (LinearLayout) rootView.findViewById(R.id.root);

            ScrollView[] scrollViews = new ScrollView[10];
            GridView gridView[] =  new GridView[10];
            Button button[] = new Button[10];
            TextView text[] = new TextView[10];
            imgAdapter =new ImageAdapter(getActivity(), MOBILE_OS);
            for(int i = 0 ; i< 10 ; i++)
            {
            	 scrollViews[i] = new ScrollView(getActivity());
            	 gridView[i] = new GridView(getActivity());
            	 button[i] = new Button(getActivity());
            	 text[i] = new TextView(getActivity());
            	 //
            	 text[i].setText("Sach non "+i);
            	 text[i].setTextSize(20);
            	 text[i].setTextColor(Color.GREEN);
            	 text[i].setGravity(Gravity.LEFT);
            	 scrollViews[i].addView(gridView[i]);
            	 gridView[i].setAdapter(imgAdapter); 
            	 gridView[i].setColumnWidth(100);
                 gridView[i].setNumColumns(4);
            	 
                 button[i].setText("More "+i);
                 button[i].setWidth(100);
            
                 button[i].setGravity(Gravity.RIGHT);
                 layoutRoot.addView(text[i]);
                 layoutRoot.addView(scrollViews[i]);
                 layoutRoot.addView(button[i]);
                 
            }
            
            
            
            
            return rootView;
        }
    }

}
