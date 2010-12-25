package com.thesis.transportdemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class TransportActivity extends Activity {

	public final static String ITEM_TITLE = "title";
	public final static String ITEM_CAPTION = "caption";
	private SeparatedListAdapter adapter;
	private ListView list;

	public Map<String, ?> createItem(String title, String caption) {
		Map<String, String> item = new HashMap<String, String>();
		item.put(ITEM_TITLE, title);
		item.put(ITEM_CAPTION, caption);
		return item;
	}

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		/**
		 * requestWindowFeature(Window.FEATURE_NO_TITLE);
		 * getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		 * WindowManager.LayoutParams.FLAG_FULLSCREEN);
		 **/

		List<Map<String, ?>> transportInfo = new LinkedList<Map<String, ?>>();
		transportInfo.add(createItem("Ticket Validity",
				"Ticket is valid until: 2011-01-31"));
		transportInfo.add(createItem("Ticket Access Restriction",
				"Access allowed: Stockholm Zone-A & B"));
		transportInfo.add(createItem("Recent Access",
				"Last accessed on: 2010-12-18, 13:45"));

		List<Map<String, ?>> buyTicket = new LinkedList<Map<String, ?>>();
		buyTicket.add(createItem("Buy Ticket",
				"Buy and load ticket automatically"));

		List<Map<String, ?>> cardInfo = new LinkedList<Map<String, ?>>();

		cardInfo.add(createItem("Issuer Contact Info",
				"Smart Transport, 14453, Stockholm, Sweden"));
		cardInfo.add(createItem("Application Details",
				"Smart app v2.2, Expired on 2015-10-10"));
		cardInfo.add(createItem("Application Permission",
				"Any city where Smart Transport operates"));

		List<Map<String, ?>> settings = new LinkedList<Map<String, ?>>();
		settings.add(createItem("Auto buy ticket",
				"Allow to purchase ticket automatically"));
		settings.add(createItem("Save purchase receipts",
				"Save receipts in phone memory"));

		List<Map<String, ?>> feedback = new LinkedList<Map<String, ?>>();
		feedback.add(createItem("Feedbacks",
				"Feedback style before ticket expires"));

		// create our list and custom adapter
		adapter = new SeparatedListAdapter(this);
		// add section header and list items to the adapter

		adapter.addSection("Transport Info", new SimpleAdapter(this,
				transportInfo, R.layout.list_complex, new String[] {
						ITEM_TITLE, ITEM_CAPTION }, new int[] {
						R.id.list_complex_title, R.id.list_complex_caption }));

		adapter.addSection("Buy Ticket", new SimpleAdapter(this, buyTicket,
				R.layout.list_item_sms,
				new String[] { ITEM_TITLE, ITEM_CAPTION }, new int[] {
						R.id.list_title, R.id.list_caption }));

		adapter.addSection("Card Info", new SimpleAdapter(this, cardInfo,
				R.layout.list_complex,
				new String[] { ITEM_TITLE, ITEM_CAPTION }, new int[] {
						R.id.list_complex_title, R.id.list_complex_caption }));

		adapter.addSection("Settings", new SimpleAdapter(this, settings,
				R.layout.list_checkbox,
				new String[] { ITEM_TITLE, ITEM_CAPTION }, new int[] {
						R.id.list_complex_title, R.id.list_complex_caption }));

		adapter.addSection("Feedback", new SimpleAdapter(this, feedback,
				R.layout.list_item, new String[] { ITEM_TITLE, ITEM_CAPTION },
				new int[] { R.id.list_title, R.id.list_caption }));

		Log.d(TransportActivity.class.getName(), "ON_ITEM_CLICK");

		/**
		 * adapter.addSection("Feedback", new ArrayAdapter<String>(this,
		 * R.layout.feedback, new String[] { "Select Feedback Style"}));
		 **/

		this.setContentView(R.layout.list_view);
		list = (ListView) findViewById(R.id.main_listview);
		list.setAdapter(adapter);

		// list = new ListView(this);
		// list.setAdapter(adapter);
		// this.setContentView(list);
		// list.setItemsCanFocus(false);
		// list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

		// when a list item is clicked

		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Log.d(TransportActivity.class.getName(), "ON_ITEM_CLICK");

				// To test the List ID for each row.
				/**
				 * Object adapterObj = adapter.getItemId(position); String
				 * position_ = adapterObj.toString();
				 * 
				 * Toast.makeText(getApplicationContext(), position_,
				 * Toast.LENGTH_SHORT).show();
				 **/

				// to start payment activity

				if (id == 5) {

					/**
					 * SmsManager m = SmsManager.getDefault(); String
					 * destination = "+4670772312"; String text = "BUY: ";
					 * m.sendTextMessage(destination, null, text, null, null);
					 **/

					Uri smsUri = Uri.parse("sms:0765830361");
					Intent intent = new Intent(Intent.ACTION_VIEW, smsUri);
					intent.putExtra("sms_body","BUY: <DURATION[1, 3 or 6 mon]><ZONE[a, b or c]>");
					intent.setType("vnd.android-dir/mms-sms");
					startActivity(intent);
				} else if (id == 14) {

					int a = (int) id;
					showDialog(a);
				}

				/**
				 * if (id == 17){
				 * 
				 * if(chkbox.isChecked()){ AlertDialog.Builder adb=new
				 * AlertDialog.Builder(ListHeaderView.this);
				 * adb.setTitle("Fria");
				 * adb.setMessage("Your card is Activated"); } }
				 **/

				/**
				 * completely working Object o = adapter.getItem(position);
				 * String pen = o.toString(); ;
				 **/

				/**
				 * completely working AlertDialog.Builder adb=new
				 * AlertDialog.Builder(ListHeaderView.this);
				 * adb.setTitle("LVSelectedItemExample");
				 * adb.setMessage("Selected Item is = "
				 * list.getItemAtPosition(position));
				 * adb.setPositiveButton("Ok", null); adb.show();
				 **/

			}

		});

	} // end onCreate()

	/**
	 * This method
	 */

	protected Dialog onCreateDialog(int id) {

		// This example shows how to add a custom layout to an AlertDialog

		return new AlertDialog.Builder(TransportActivity.this).setIcon(
				R.drawable.info)
				.setTitle(R.string.alert_dialog_multiple_choice)
				.setMultiChoiceItems(
						R.array.select_dialog_item,
						new boolean[] { false, true, false, true, false, false,
								false },
						new DialogInterface.OnMultiChoiceClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton, boolean isChecked) {

								/* User clicked on a check box do some stuff */
							}
						}).setPositiveButton(R.string.alert_dialog_ok,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {

								/* User clicked Yes so do some stuff */
							}
						}).setNegativeButton(R.string.alert_dialog_cancel,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {

								/* User clicked No so do some stuff */
							}
						}).create();
	}
}