package com.gitwork;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Git_workActivity extends Activity implements OnClickListener {
	/** Called when the activity is first created. */
	Button categories, registration, resources, calender, map, groups;
	Intent newIntent;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_menu);
		initialize();
		categories.setOnClickListener(this);
		registration.setOnClickListener(this);
		resources.setOnClickListener(this);
		calender.setOnClickListener(this);
		map.setOnClickListener(this);
		groups.setOnClickListener(this);
	}

	public void initialize() {
		categories = (Button) findViewById(R.id.btncategories);
		registration = (Button) findViewById(R.id.btnuserAccount);
		resources = (Button) findViewById(R.id.btnResources);
		calender = (Button)findViewById(R.id.btncalender);
		map = (Button)findViewById(R.id.btnviewmap);
		groups = (Button)findViewById(R.id.btnusergroups);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.btncategories:
			newIntent = new Intent("com.gitwork.CATEGORIES");
			startActivity(newIntent);
			break;

		case R.id.btnuserAccount:
			newIntent = new Intent("com.gitwork.REGISTRATION");
			startActivity(newIntent);
			break;

		case R.id.btnResources:
			newIntent = new Intent("com.gitwork.RESOURCES");
			startActivity(newIntent);
			break;

		case R.id.btncalender:
			newIntent = new Intent("com.gitwork.CALENDER");
			startActivity(newIntent);
			break;
		
		case R.id.btnviewmap:
			newIntent = new Intent("com.gitwork.MAP");
			startActivity(newIntent);
			break;
			
		case R.id.btnusergroups:
			newIntent = new Intent("com.gitwork.USERGROUPS");
			startActivity(newIntent);
			break;
		}
	}
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		MenuInflater blowUp = getMenuInflater();
		blowUp.inflate(R.menu.cool_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub

		switch (item.getItemId()) {
		case R.id.watchlist:
			Intent i = new Intent("");
			startActivity(i);
			break;
		case R.id.preferences:
			Intent prefs = new Intent("");
			startActivity(prefs);
			break;
		case R.id.exit:
			finish();
			break;
		}
		return false;
	}
}