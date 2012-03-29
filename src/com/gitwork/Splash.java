package com.gitwork;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Splash extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		Thread timer = new Thread() {
			@Override
			public void run() {
				try {
					sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();

				} finally {
					Intent openStart = new Intent("com.gitwork.MAINMENU");
					startActivity(openStart);
				}
			}
		};
		timer.start();

	}
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

}
