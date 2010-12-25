package com.thesis.paymentdemo;



import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

public class PaymentHistory extends Activity {
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        
    	super.onCreate(savedInstanceState);
        
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        setContentView(R.layout.payment_history);
        
    }
}
