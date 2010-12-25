package com.joy.tabview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ArtistActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        TextView tv = new TextView(getApplicationContext());
        tv.setText("Hello Joy, Artist Tab");
        setContentView(tv);
    }
}