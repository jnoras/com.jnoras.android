package com.thesis.paymentdemo;

import android.app.Activity;
import android.os.Bundle;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class PaymentActivity extends Activity {

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

		List<Map<String, ?>> transaction = new LinkedList<Map<String, ?>>();
		transaction.add(createItem("Current Account Balance",
				"You have available balance: 12000.00"));
		transaction.add(createItem("Purchased Amount",
				"Purchased amount from credit limit: 5000.00kr"));
		transaction.add(createItem("Recent Payments",
				"View recent payments/purchase history"));

		List<Map<String, ?>> accountDetails = new LinkedList<Map<String, ?>>();
		accountDetails.add(createItem("Account Name:",
				"Name of account: ROBIN SOBER"));
		accountDetails.add(createItem("Account Number:",
				"IBAN number: 32323-00-92828282"));
		accountDetails.add(createItem("Account Type:",
				"Account Type: VISA credit card"));

		List<Map<String, ?>> cardInfo = new LinkedList<Map<String, ?>>();
		cardInfo.add(createItem("Credit Card Number:", "4637-3737-373-7733"));
		cardInfo.add(createItem("Issuer Details:",
				"Smart Bank Inc., 17772, Stockholm, Sweden"));
		cardInfo.add(createItem("Application Details:",
				"VISA V2.2, Expired on 2015-10-10"));
		cardInfo.add(createItem("Access Region:",
				"Access Permission: International"));

		List<Map<String, ?>> securityCodes = new LinkedList<Map<String, ?>>();
		securityCodes.add(createItem("Change PIN", "Change PIN code"));
		securityCodes.add(createItem("Get Sign in Code:",
				"Sign in code for online payments"));

		List<Map<String, ?>> settings = new LinkedList<Map<String, ?>>();
		settings.add(createItem("Set as Active",
				"Activate/deactivate card temporarily"));
		settings.add(createItem("Set as Default",
				"Make default for all payments"));
		settings.add(createItem("Payment Receipts",
				"Save receipts in phone memory"));

		// create our list and custom adapter
		adapter = new SeparatedListAdapter(this);
		// add section header and list items to the adapter

		adapter.addSection("Transaction Information", new SimpleAdapter(this,
				transaction, R.layout.list_complex, new String[] { ITEM_TITLE,
						ITEM_CAPTION }, new int[] { R.id.list_complex_title,
						R.id.list_complex_caption }));

		adapter.addSection("Account Information", new SimpleAdapter(this,
				accountDetails, R.layout.list_complex, new String[] {
						ITEM_TITLE, ITEM_CAPTION }, new int[] {
						R.id.list_complex_title, R.id.list_complex_caption }));

		adapter.addSection("Credit Card Information", new SimpleAdapter(this,
				cardInfo, R.layout.list_complex, new String[] { ITEM_TITLE,
						ITEM_CAPTION }, new int[] { R.id.list_complex_title,
						R.id.list_complex_caption }));

		adapter.addSection("Security Codes", new SimpleAdapter(this,
				securityCodes, R.layout.list_complex, new String[] {
						ITEM_TITLE, ITEM_CAPTION }, new int[] {
						R.id.list_complex_title, R.id.list_complex_caption }));

		adapter.addSection("Settings", new SimpleAdapter(this, settings,
				R.layout.list_checkbox, new String[] { ITEM_TITLE,
						ITEM_CAPTION }, new int[] { R.id.list_complex_title,
						R.id.list_complex_caption }));

		/**adapter.addSection("Array", new ArrayAdapter<String>(this,
				R.layout.list_item, new String[] { "First item", "Item two" })); **/

		list = new ListView(this);
		list.setAdapter(adapter);
		this.setContentView(list);
		// list.setItemsCanFocus(false);
		// list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

		// when a list item is clicked

		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Log.d(DebugTAG.LOG_TAG, "ON_ITEM_CLICK");

				// To test the List ID for each row.
				/**Object adapterObj = adapter.getItemId(position);
				String position_ = adapterObj.toString();

				Toast.makeText(getApplicationContext(), position_,
						Toast.LENGTH_SHORT).show(); **/

				// to start payment activity
				if (id == 3) {

					Intent intent = new Intent(
							"com.thesis.paymentdemo.paymenthistory");
					startActivity(intent);
				}
				//to change PIN code
				if (id == 14) {
					int a = (int) id;
					showDialog(a);

				} 
				// to get sign in code
				else if (id == 15) {
					showDialog(position);
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

		switch (id) {

		case 14:
			LayoutInflater factory = LayoutInflater.from(this);
			final View textEntryView = factory.inflate(
					R.layout.alert_change_pin, null);

			return new AlertDialog.Builder(PaymentActivity.this)
					// .setIcon(R.drawable.alert_dialog_icon)
					// .setTitle(R.string.alert_dialog_text_entry)
					.setTitle("Change PIN Code:").setView(textEntryView)
					.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {

									/* User clicked OK so do some stuff */
					
																	
									
									Toast toast = Toast.makeText(getApplicationContext(),
											"PIN CODE FOR CREDIT CARD HAS CHANGED",
											Toast.LENGTH_LONG);
									toast.setGravity(Gravity.CENTER_VERTICAL, 0,0);
									toast.show();
									toast.show();


								}
							}).setNegativeButton("Cancel",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {

									/* User clicked cancel so do some stuff */
								}
							}).create();

		case 15:

			LayoutInflater factory1 = LayoutInflater.from(this);
			final View textEntryView1 = factory1.inflate(
					R.layout.alert_sign_in, null);
			return new AlertDialog.Builder(PaymentActivity.this)
					// .setIcon(R.drawable.alert_dialog_icon)
					// .setTitle(R.string.alert_dialog_text_entry)
					.setTitle("Get Sign in code:").setView(textEntryView1)
					.setPositiveButton("Sign in",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {

									/* User clicked OK so do some stuff */
									
									
									Toast toast = Toast.makeText(getApplicationContext(),
											"SIGN IN CODE IS: 96632",
											Toast.LENGTH_LONG);
									toast.setGravity(Gravity.CENTER_VERTICAL, 0,0);
									toast.show();
									toast.show();

								}
							}).setNegativeButton("Cancel",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {

									/* User clicked cancel so do some stuff */
								}
							}).create();

		} // end Switch
		return null;
	}

}
