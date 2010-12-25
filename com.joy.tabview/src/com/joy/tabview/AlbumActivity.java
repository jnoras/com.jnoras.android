package com.joy.tabview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class AlbumActivity extends Activity{
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        TextView tv = new TextView(getApplicationContext());
        tv.setText("Hello Joy, Album Tab");
        setContentView(tv);
    }

}
